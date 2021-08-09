angular
    .module('erpApp')
    .controller('main_headerCtrl', [
        '$timeout',
        '$scope',
        '$window',
        '$state',
        'Auth',
        'LogsService',
        'NotificationService',
        'User',
        'ErrorHandle',
        '$filter',
        '$translate',
        '$rootScope',
        'Transfer',
        'Principal',
        function ($timeout,$scope,$window,$state,Auth,LogsService,NotificationService,User,ErrorHandle,$filter,$translate,$rootScope,Transfer,Principal) {
            $scope.moduleTitle = {
                "admin": $translate.instant("admin.menu.administration"),
                "inventory":$translate.instant("admin.menu.sourceTrace"),
            }
            $scope.module = $scope.moduleTitle[$rootScope.toState.data.sideBarMenu]
            $rootScope.$on('$stateChangeSuccess', function () {
                $scope.module = $scope.moduleTitle[$rootScope.toState.data.sideBarMenu]
            });

            $scope.resolutionX = screen.width;
            $scope.organization = null;
            $scope.organizationName = $window.localStorage.getItem("organizationName");
            $scope.mobile = $window.localStorage.getItem("mcMobile");

            $scope.bigHead = false
            if($scope.resolutionX < 375){
                $scope.bigHead = true;
            }

            $('#menu_top').children('[data-uk-dropdown]').on('show.uk.dropdown', function(){
                $timeout(function() {
                    $($window).resize();
                },280)
            });

            // autocomplete
            $('.header_main_search_form').on('click','#autocomplete_results .item', function(e) {
                e.preventDefault();
                var $this = $(this);
                $state.go($this.attr('href'));
                $('.header_main_search_input').val('');
            })

            $scope.hideDropdown = function(){
                $timeout(function () {
                    angular.element("#anywhere").trigger("click");
                });
            }


            $scope.logout = function() {
                var mobile = $window.localStorage.getItem("mcMobile");
                Auth.logout();
                $scope.$emit('heyRoot', {
                    type: "userLogout",
                    message: "User Logout!!!",
                    from: "headerController"
                });
                if(mobile == 0){
                    $state.go('login',{reload:true});
                } else {
                    $state.go('login-m',{reload:true});
                }
            }

            /*============================================= NOTIFICATIONS ============================================*/
            $scope.hideNotificationTitle = $translate.instant('inventory.logs.hideNotification')
            $scope.markAsReadTitle = $translate.instant('inventory.logs.markAsRead')
            $scope.markAsUnReadTitle = $translate.instant('inventory.logs.markAsUnRead')
            $scope.notificationTitle = $translate.instant('inventory.logs.notification')
            $scope.seenTitle = $translate.instant('inventory.logs.seen')

            $scope.TIME_DISPLAY = {
                "hour": $translate.instant('inventory.logs.time.hour'),
                "minute": $translate.instant('inventory.logs.time.minute'),
                "at": $translate.instant('inventory.logs.time.at'),
                "ago": $translate.instant('inventory.logs.time.ago'),
                "yesterday": $translate.instant('inventory.logs.time.yesterday')
            }

            $scope.logDatas = {}
            LogsService.init($scope);
            //$scope.logDatas = LogsService.getAllNotification();

            $scope.notifications = [];
            $scope.avatarImg = 'assets/img/avatars/avatar.jpg';

            $scope.userData = {};
            $scope.userData = $rootScope.currentUser;
            
            $scope.selectize_organizations_options = $scope.userData.organizations;
            $scope.mouseHoverNotification = function(index, status) {
                $scope.notifications[index]['mouse_hover'] = status;
            }

            $scope.markAsRead = function (index) {
                //console.log("Mark As Read: "+ index)
                if($scope.notifications[index].id != undefined && $scope.notifications[index].id != null) {
                    LogsService.markAsRead($scope.notifications[index].id).then(function (data) {
                        if($scope.userData.id != undefined && $scope.userData.id != null) {
                            $scope.getNotifications($scope.userData.id);
                            $scope.sendUpdateNotificationEvent();
                        }
                    }).catch(function (data) {
                        ErrorHandle.handleError(data);
                    })
                }
            }

            $scope.markAsUnRead = function (index) {
                //console.log("Mark As Read: "+ index)
                if($scope.notifications[index].id != undefined && $scope.notifications[index].id != null) {
                    LogsService.markAsUnRead($scope.notifications[index].id).then(function (data) {
                        if($scope.userData.id != undefined && $scope.userData.id != null) {
                            $scope.getNotifications($scope.userData.id);
                            $scope.sendUpdateNotificationEvent();
                        }
                    }).catch(function (data) {
                        ErrorHandle.handleError(data);
                    })
                }
            }

            $scope.markAllAsRead = function () {
                //console.log("Mark All As Read!")
                if($scope.notifications.length == 0)
                    return;

                if($scope.userData.id != undefined && $scope.userData.id != null) {
                    LogsService.markAllAsRead($scope.userData.id).then(function (data) {
                        if($scope.userData.id != undefined && $scope.userData.id != null) {
                            $scope.getNotifications($scope.userData.id);
                            $scope.sendUpdateNotificationEvent();
                        }
                    }).catch(function (data) {
                        ErrorHandle.handleError(data);
                    })
                }
            }

            $scope.hideNotification = function (index) {
                //console.log("Hide Notification: "+ index)
                if($scope.notifications[index].id != undefined && $scope.notifications[index].id != null) {
                    $scope.notifications[index].isHide = true;
                    // LogsService.hideNotification($scope.notifications[index].id).then(function (data) {
                    //     if($scope.userData.id != undefined && $scope.userData.id != null) {
                    //         $scope.getNotifications($scope.userData.id);
                    //         $scope.sendUpdateNotificationEvent();
                    //     }
                    // }).catch(function (data) {
                    //     ErrorHandle.handleError(data);
                    // })
                }
            }

            $scope.markAsDropdown = function () {
                if($scope.logDatas.totalNotifications == 0 || $("#notificationDropdown").hasClass('uk-dropdown-shown'))
                    return;
                if($scope.userData.id != undefined && $scope.userData.id != null) {
                    LogsService.markAllAsShowDropdown($scope.userData.id).then(function (data) {
                        $scope.getNotifications($scope.userData.id);
                        $scope.sendUpdateNotificationEvent();
                    }).catch(function (data) {
                        ErrorHandle.handleError(data);
                    })
                }
            }

            $scope.showAllNotification = function () {
                //console.log("Show All Notification!")
                $state.go('notifications');
            }

            $scope.pageNum = 0;
            $scope.pageSizePerLoad = 10;
            $scope.showMoreNotify = false;
            $scope.showMoreNotifications = function () {
                $scope.pageNum++;
                $scope.showMoreNotify = true;
                if($scope.userData.id != undefined && $scope.userData.id != null) {
                    setTimeout(function () {
                        $scope.getPagingNotifications($scope.userData.id, $scope.pageNum, $scope.pageSizePerLoad);
                    }, 700)
                }
            }

            $scope.getPagingNotifications = function(userId, pageNum, pageSize) {
                if(pageNum == 0)
                    $scope.notifications = [];
                $scope.showMoreNotify = true;
                LogsService.getByRecipientPaging(userId, pageNum, pageSize).then(function (data) {
                    //$scope.notifications = data.data;
                    if(data.data.length == 0) {
                        $scope.noMoreItemRemain = true;
                    } else {
                        for(var i = 0; i < data.data.length; i++) {
                            $scope.notifications.push(data.data[i]);
                        }
                        $scope.generateNotificationTimeString($scope.notifications);
                    }
                    $scope.showMoreNotify = false;

                }).catch(function (data) {
                    ErrorHandle.handleError(data);
                })
            };

            $('#notificationDropdownContainer').on('show.uk.dropdown', function(){
                //console.log("open")
            });

            $('#notificationDropdownContainer').on('hide.uk.dropdown', function(){
                //console.log("close")
                var need_hide = [];
                for(var i = 0; i < $scope.notifications.length; i++) {
                    if($scope.notifications[i].isHide)
                        need_hide.push($scope.notifications[i].id);
                }
                if(need_hide.length > 0) {
                    $scope.$emit('heyRoot', {
                        type: "hideNotifications",
                        message: "Hello Root!!!",
                        from: "headerController",
                        data: need_hide
                    });
                }
            });

            $scope.generateNotificationTimeString = function (data) {
                if(data != undefined && data != null) {
                    for(var i = 0; i < data.length; i++) {
                        var date = new Date(data[i]['created']);
                        var presentTime = new Date();

                        var diffDate = $scope.findDifferentDate(date, presentTime);
                        if(diffDate == 0) {
                            // Today
                            var oneHour = 60 * 60 * 1000;
                            var diffTime = Math.abs(presentTime.getTime() - date.getTime());
                            if(diffTime < oneHour) {
                                var minDiff = Math.floor(diffTime / 60 / 1000);
                                data[i]['timeString'] = minDiff + " " + $scope.TIME_DISPLAY['minute'] + " " + $scope.TIME_DISPLAY['ago'];
                            } else {
                                var hourDiff = Math.floor(diffTime / 60 / 60 / 1000);
                                var minDiff = Math.floor((diffTime - hourDiff * 60 * 60 * 1000) / 60 / 1000);
                                data[i]['timeString'] = hourDiff + " " + $scope.TIME_DISPLAY['hour'] + " " +
                                    minDiff + " " + $scope.TIME_DISPLAY['minute'] + " " + $scope.TIME_DISPLAY['ago'];
                            }
                        } else if(diffDate == -1) {
                            // Yesterday
                            data[i]['timeString'] = $scope.TIME_DISPLAY['yesterday'] + " " + $scope.TIME_DISPLAY['at'] + " " + genTime(date.getTime());
                        } else {
                            // Display full date
                            data[i]['timeString'] = genDate(date.getTime()) + " " + $scope.TIME_DISPLAY['at'] + " " + genTime(date.getTime());
                        }
                    }
                }
            }

            function sortArrayByIntegerField(arrays, field, sort) {
                // field is an integer
                arrays.sort(function (a, b) {
                    if(sort === 'asc')
                        return a[field] - b[field]
                    else
                        return b[field] - a[field]
                })
            }

            function genDate(time) {
                return $filter('date')(time, 'dd/MM/yyyy');
            }

            function genTime(time) {
                return $filter('date')(time, 'HH:mm:ss');
            }

            $scope.findDifferentDate = function (firstDate, secondDate) {
                var oneDay = 24 * 60 * 60 * 1000;
                var diffTime = firstDate.getTime() - secondDate.getTime();
                return diffTime >= 0 ? Math.round(Math.abs( diffTime / oneDay )) : -Math.round(Math.abs( diffTime / oneDay ));
            }

            $scope.goToNotification = function (index) {

            }

            // Listen for events from root controller
            $scope.$on('updateNotifications', function (event, args) {
                console.log("xxxx")
                if(args.from !== 'headerController') {
                    if($scope.userData.id != undefined && $scope.userData.id != null) {
                        $scope.getNotifications()
                    }
                }
            });

            // Send event to root controller
            $scope.sendUpdateNotificationEvent = function () {
                $scope.$emit('heyRoot', {
                    type: "updateNotifications",
                    message: "Hello Root!!!",
                    from: "headerController"
                });
            }

            /*=========================================== END NOTIFICATIONS ==========================================*/
        }
    ])
;