angular
    .module('erpApp')
    .controller('main_sidebarCtrl', [
        '$timeout',
        '$scope',
        '$rootScope',
        '$state',
        '$translate', 'JhiLanguageService', 'tmhDynamicLocale','$window','AlertService','Auth',
        function ($timeout,$scope,$rootScope,$state,$translate, JhiLanguageService, tmhDynamicLocale,$window,AlertService,Auth) {
            $scope.$on('onLastRepeat', function (scope, element, attrs) {
                $timeout(function() {
                    if(!$rootScope.miniSidebarActive) {
                        // activate current section
                        $('#sidebar_main').find('.current_section > a').trigger('click');
                    } else {
                        // add tooltips to mini sidebar
                        var tooltip_elem = $('#sidebar_main').find('.menu_tooltip');
                        tooltip_elem.each(function() {
                            var $this = $(this);

                            $this.attr('title',$this.find('.menu_title').text());
                            UIkit.tooltip($this, {
                                pos: 'right'
                            });
                        });
                    }
                })
            });
            $scope.mobile = $window.localStorage.getItem("mcMobile");
            $scope.currentOrganizationId = null;
            $scope.selectize_organizations_options = [];

            $scope.selectOrganization = function (oId){
                $(this).blur();
                $window.localStorage.setItem("organizationId", oId);
                var orgName ="";
                for(var i =0; i< $scope.selectize_organizations_options.length; i ++){
                    if(oId == $scope.selectize_organizations_options[i].id){
                        orgName =  $scope.selectize_organizations_options[i].name
                        $window.localStorage.setItem("organizationName", orgName);
                        break;
                    }
                }
                AlertService.success("???? chuy???n sang: " + orgName)
                $timeout(function () {
                    $window.location.reload();
                },500)
            };

            $scope.selectize_organizations_config = {
                plugins: {
                    'remove_button': {
                        label     : ''
                    }
                },
                maxItems: 1,
                valueField: 'id',
                labelField: 'name',
                searchField: 'name'
            };

            $scope.userData = {};
            $scope.userData = $rootScope.currentUser;
            $scope.selectize_organizations_options = $scope.userData.organizations;
            if($scope.selectize_organizations_options && $scope.selectize_organizations_options.length > 0){
                // co?? localStorage thi?? l????y ra ??a??
                var defaultOrganizationId = $window.localStorage.getItem("organizationId");
                if(defaultOrganizationId != null){
                    for(var i = 0; i < $scope.selectize_organizations_options.length; i++){
                        if($scope.selectize_organizations_options[i].id == defaultOrganizationId){
                            var defaultO = $scope.selectize_organizations_options[i];
                            $scope.currentOrganizationId = defaultO.id;
                            $rootScope.organizationId = defaultO.id;
                            $scope.organizationName = defaultO.name;
                            $window.localStorage.setItem("organizationName", defaultO.name);
                            break;
                        }
                    }
                } else{
                    var defaultO = $scope.selectize_organizations_options[0];
                    $scope.currentOrganizationId = defaultO.id;
                    $rootScope.organizationId = defaultO.id;
                    $scope.organizationName = defaultO.name
                    $window.localStorage.setItem("organizationId", defaultO.id);
                    $window.localStorage.setItem("organizationName", defaultO.name);
                }
            }

            // language switcher
            if($window.localStorage.getItem("lang") !=null){
                $scope.langSwitcherModel = $window.localStorage.getItem("lang")
            } else {
                $scope.langSwitcherModel = 'vn';
            }
            var langData = $scope.langSwitcherOptions = [
                {id: 2, title: 'Ti???ng Vi???t', value: 'vn'},
                {id: 1, title: 'English', value: 'gb'}

            ];

            $scope.langSwitcherConfig = {
                maxItems: 1,
                render: {
                    option: function(langData, escape) {
                        return  '<div class="option">' +
                            '<i class="item-icon flag-' + escape(langData.value).toUpperCase() + '"></i>' +
                            '<span>' + escape(langData.title) + '</span>' +
                            '</div>';
                    },
                    item: function(langData, escape) {
                        return '<div class="item"><i class="item-icon flag-' + escape(langData.value).toUpperCase() + '"></i></div>';
                    }
                },
                valueField: 'value',
                labelField: 'title',
                searchField: 'title',
                create: false,
                onInitialize: function(selectize) {
                    $('#lang_switcher').next().children('.selectize-input').find('input').attr('readonly',true);
                },
                onChange: function(value) {
                    var langKey = value==='gb' ? 'en' : (value==='vn'? 'vi' : 'en');
                    $translate.use(langKey);
                    tmhDynamicLocale.set(langKey);
                    $window.localStorage.setItem("lang",value)
                }
            };
            $scope.$watch('langSwitcherModel', function() {
                var value = $scope.langSwitcherModel;
                var langKey = value==='gb' ? 'en' : (value==='vn'? 'vi' : 'en');
                $translate.use(langKey);
                tmhDynamicLocale.set(langKey);
            });

            $scope.logOut = function(){
                Auth.logout();
                console.log("logout")
                $timeout(function() {
                    $state.go('login-m');
                })

            }

            // menu entries
            var menu = {
                'inventory': [
                    {
                        id: 0,
                        title: 'admin.menu.dashboard',
                        icon: '&#xE871;',
                        link:'dashboard'
                    },
                    {
                        id: 5,
                        title: 'Thi???t b???',
                        icon:"table_view",
                        privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER,device_view",
                        submenu:[
                            {
                                id: 11,
                                title: 'Danh s??ch thi???t b???',
                                link:'device',
                                privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER"
                            }
                        ]
                    },
                    {
                        id: 5,
                        title: '????n h??ng',
                        icon:"assignment",
                        privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER,transfer_view",
                        link:'transfer'
                    },

                    {
                        id: 19,
                        title: 'B??o c??o',
                        icon: 'date_range',
                        privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER,report_view",
                        submenu:[
                            {
                                id: 20,
                                title: 'Thi???t b??? k??ch ho???t theo khu v???c',
                                link:'report-active',
                                privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER"
                            },
                            {
                                id: 20,
                                title: 'T???ng h???p thi???t b???',
                                link:'report-summary',
                                privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER"
                            },
                            {
                                id: 20,
                                title: 'S??? l?????ng thi???t b??? ',
                                link:'report-quantity',
                                privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER"
                            },
                            {
                                id: 21,
                                title: 'Thu?? bao t???n kho, s???a ch???a, t??i s??? d???ng',
                                link:'report-scrap',
                                privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER"
                            },
                            {
                                id: 21,
                                title: 'Doanh s??? thi???t b??? ph??t sinh',
                                link:'report-addon',
                                privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER"
                            },
                            {
                                id: 21,
                                title: 'Thi???t b??? kh??ng tr??n th??ng/chu k??? ph??t sinh',
                                link:'report-less',
                                privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER"
                            }
                        ]
                    },

                    {
                        id: 15,
                        title: 'Qu???n l?? ng?????i d??ng',
                        icon: 'account_circle',
                        privilege:"ROLE_SYSTEM_ADMIN, ROLE_ORGANIZATION,ROLE_MANAGER,user_view,role_view",
                        submenu:[
                            {
                                id: 16,
                                title: 'Ng?????i d??ng',
                                link:'users',
                                privilege:"ROLE_SYSTEM_ADMIN,ROLE_ORGANIZATION,ROLE_MANAGER,user_view"
                            },
                            {
                                id: 16,
                                title: 'Vai tr??',
                                link:'roles',
                                privilege:"ROLE_SYSTEM_ADMIN,ROLE_ORGANIZATION,ROLE_MANAGER,role_view"
                            }

                        ]
                    }
                ]
            };
            $scope.sections = menu[$rootScope.toState.data.sideBarMenu];
            $rootScope.$on('$stateChangeSuccess', function () {
                $scope.sections = menu[$rootScope.toState.data.sideBarMenu];
            });
        }
    ])
;