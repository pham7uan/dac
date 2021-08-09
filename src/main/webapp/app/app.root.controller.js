(function(){
    'use strict';
    angular.module('erpApp')
        .controller('RootController', RootController);

    RootController.$inject = [
        '$rootScope',
        '$scope',
        '$state',
        '$filter',
        '$timeout',
        'Transfer',
        'LogsService',
        'User',
        'ErrorHandle',
        '$translate',
        '$window'];
    function RootController($rootScope, $scope, $state, $filter, $timeout, Transfer, LogsService, User, ErrorHandle, $translate, $window) {

        // console.log("ROOT!");

        $scope.MESSAGE_TYPE = ['userLogin', 'userLogout', 'normal', 'updateNotifications', 'hideNotifications'];

        $scope.TIME_DISPLAY = {
            "hour": $translate.instant('inventory.logs.time.hour'),
            "minute": $translate.instant('inventory.logs.time.minute'),
            "at": $translate.instant('inventory.logs.time.at'),
            "ago": $translate.instant('inventory.logs.time.ago'),
            "yesterday": $translate.instant('inventory.logs.time.yesterday')
        }

        $scope.user = null;
        $scope.userLogin = false;
        $scope.firstLogin = false;
        $scope.timeDelayToCallUpdate = 30000;
        $scope.timeDelayToShowPopup = 100;

        var height = $( window ).height() - 0.2*$( window ).height() - (1/$( window ).height())*38000 -16 ;
        $scope.fullsize = {
            "height":height
        }

        // Check if data is changing, notify other users
        var TimeoutF;
        $scope.lastNotReadNumberNotifications = 0;
        $scope.lastHideNumberNotifications = 0;
        $scope.lastShowNotificationTime = 0;
        $scope.callInterval = function () {
            TimeoutF = setInterval(function () {
                if($scope.user === null || !$scope.userLogin) return;
                $scope.sendUpdateNotificationEvent({from: 'rootController'});

            }, $scope.timeDelayToCallUpdate)
        }

        //tuannp comment this code
        User.current().then(function (data) {
            $rootScope.currentUser = data;
            $scope.user = data;
            $scope.userLogin = true;
            $scope.firstLogin = true;
            // $scope.callInterval();
        }).catch(function (data) {

        })

        // Listen for events pass from child components
        $scope.$on('heyRoot', function (event, args) {
            switch (args.type) {
                case $scope.MESSAGE_TYPE[0]:
                    // Login events
                    $scope.userLogin = true;
                    $scope.firstLogin = true;
                    User.current().then(function (data) {
                        $scope.user = data;
                    }).catch(function (data) {

                    })
                    break;
                case $scope.MESSAGE_TYPE[1]:
                    // Logout events
                    $scope.userLogin = false;
                    $scope.firstLogin = false;
                    $scope.user = null;
                    clearInterval(TimeoutF);
                    break;
                case $scope.MESSAGE_TYPE[2]:
                    // Normal events
                    break;
                case $scope.MESSAGE_TYPE[3]:
                    // Send update notifications event
                    $scope.sendUpdateNotificationEvent(args);
                    break;
                case $scope.MESSAGE_TYPE[4]:
                    if(args.data != undefined && args.data != null && args.data.length > 0) {
                        //console.log(args.data)
                        LogsService.hideManyNotification(args.data).then(function (data) {
                            $scope.sendUpdateNotificationEvent(args);
                        }).catch(function (data) {
                            ErrorHandle.handleError(data);
                        })
                    }

                    break;
            }
        });
        
        // Send events which listened from child controllers to all child controllers
        $scope.sendUpdateNotificationEvent = function (args) {
            $scope.$broadcast('updateNotifications', {
                message: "Update notifications!!!",
                from: args.from
            });
        }

        // Growl Notifications
        $scope.avatarImg = 'assets/img/avatars/avatar.jpg';
        $scope.listNotifications = {};
        var index = 0;

        $scope.addNewNotification = function (notification) {
            for(var key in $scope.listNotifications) {
                if($scope.listNotifications[key].id == notification.id) {
                    return;
                }
            }

            var i = index++;
            setTimeout(function () {
                for(var key in $scope.listNotifications) {
                    if($scope.listNotifications[key].id == notification.id) {
                        return;
                    }
                }
                $scope.listNotifications[i] = notification;
                if(!notification.created)
                    $scope.listNotifications[i].showTime = 3000;
                else
                    $scope.listNotifications[i].showTime = 10000;
                $scope.generateNotificationTimeString($scope.listNotifications[i]);
                $scope.markAsShowPopup(i);
            }, $scope.timeDelayToShowPopup * i)
        }

        function isEmpty(obj) {
            for(var key in obj) {
                if(obj.hasOwnProperty(key))
                    return false;
            }
            return true;
        }

        $scope.removeNotification = function (id) {
            delete $scope.listNotifications[id];
            if(isEmpty($scope.listNotifications)) {
                index = 0;
            }
        }

        $scope.markAsRead = function (id) {
            //console.log("Mark As Read: "+ index)
            if($scope.listNotifications.hasOwnProperty(id)) {
                LogsService.markAsRead($scope.listNotifications[id].id).then(function (data) {
                    if($scope.user != undefined && $scope.user != null) {
                        $scope.sendUpdateNotificationEvent({from: 'rootController'});
                    }
                }).catch(function (data) {
                    ErrorHandle.handleError(data);
                })
            }
        }

        $scope.markAsShowPopup = function (id) {
            //console.log("Mark As Read: "+ index)
            if($scope.listNotifications.hasOwnProperty(id)) {
                LogsService.markAsShowPopup($scope.listNotifications[id].id).then(function (data) {

                }).catch(function (data) {
                    ErrorHandle.handleError(data);
                })
            }
        }

        $scope.markAllAsShowPopup = function () {
            if($scope.user != undefined && $scope.user != null) {
                LogsService.markAllAsShowPopup($scope.user.id).then(function (data) {
                    $scope.firstLogin = false;
                }).catch(function (data) {
                    ErrorHandle.handleError(data);
                })
            }
        }

        // $scope.goToNotification = function (id) {
        //     if($scope.listNotifications.hasOwnProperty(id)) {
        //         var objectUrl = $scope.listNotifications[id].objectUrl;
        //         if(objectUrl != undefined && objectUrl != null) {
        //             // check if object is deleted
        //             if($scope.listNotifications[id].objectType === "Transfer") {
        //                 Transfer.getOne($scope.listNotifications[id].objectId).then(function (data) {
        //                     window.location.href = objectUrl;
        //                     window.location.reload();
        //                     $timeout(function () {
        //                         $("#closeNotification_"+id).trigger('click');
        //                     })
        //                 }).catch(function (data) {
        //                     UIkit.modal.alert($translate.instant("error.transfer.transfer_is_delete"), {
        //                         labels: {
        //                             'Ok': "OK"
        //                         }
        //                     });
        //                 })
        //             } else if($scope.listNotifications[id].objectType === "Inventory") {
        //                 Adjustment.checkOne($scope.listNotifications[id].objectId).then(function (data) {
        //                     if(data.length == 0) {
        //                         UIkit.modal.alert($translate.instant("error.adjustment.adjustment_is_delete"), {
        //                             labels: {
        //                                 'Ok': "OK"
        //                             }
        //                         });
        //                     } else {
        //                         window.location.href = objectUrl;
        //                         window.location.reload();
        //                         $timeout(function () {
        //                             $("#closeNotification_" + id).trigger('click');
        //                         })
        //                     }
        //                 }).catch(function (data) {
        //                     UIkit.modal.alert($translate.instant("error.adjustment.adjustment_is_delete"), {
        //                         labels: {
        //                             'Ok': "OK"
        //                         }
        //                     });
        //                 })
        //             } else {
        //                 window.location.href = objectUrl;
        //                 window.location.reload();
        //                 $timeout(function () {
        //                     $("#closeNotification_"+id).trigger('click');
        //                 })
        //             }
        //         }
        //         $scope.markAsRead(id);
        //     }
        // }

        $scope.generateNotificationTimeString = function (data) {
            if(data != undefined && data != null) {
                var date = new Date(data['created']);
                var presentTime = new Date();

                var diffDate = $scope.findDifferentDate(date, presentTime);
                if(diffDate == 0) {
                    // Today
                    var oneHour = 60 * 60 * 1000;
                    var diffTime = Math.abs(presentTime.getTime() - date.getTime());
                    if(diffTime < oneHour) {
                        var minDiff = Math.floor(diffTime / 60 / 1000);
                        data['timeString'] = minDiff + " " + $scope.TIME_DISPLAY['minute'] + " " + $scope.TIME_DISPLAY['ago'];
                    } else {
                        var hourDiff = Math.floor(diffTime / 60 / 60 / 1000);
                        var minDiff = Math.floor((diffTime - hourDiff * 60 * 60 * 1000) / 60 / 1000);
                        data['timeString'] = hourDiff + " " + $scope.TIME_DISPLAY['hour'] + " " +
                            minDiff + " " + $scope.TIME_DISPLAY['minute'] + " " + $scope.TIME_DISPLAY['ago'];
                    }
                } else if(diffDate == -1) {
                    // Yesterday
                    data['timeString'] = $scope.TIME_DISPLAY['yesterday'] + " " + $scope.TIME_DISPLAY['at'] + " " + genTime(date.getTime());
                } else {
                    // Display full date
                    data['timeString'] = genDate(date.getTime()) + " " + $scope.TIME_DISPLAY['at'] + " " + genTime(date.getTime());
                }
            }
        }

        function genDate(time) {
            return $filter('date')(time, 'dd/MM/yyyy');
        }

        function genTime(time) {
            return $filter('date')(time, 'hh:mm:ss');
        }

        $scope.findDifferentDate = function (firstDate, secondDate) {
            var oneDay = 24 * 60 * 60 * 1000;
            var diffTime = firstDate.getTime() - secondDate.getTime();
            return diffTime >= 0 ? Math.round(Math.abs( diffTime / oneDay )) : -Math.round(Math.abs( diffTime / oneDay ));
        }
    }

})();