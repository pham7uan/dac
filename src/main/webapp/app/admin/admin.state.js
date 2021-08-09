(function(){
    'use strict';
    angular.module('erpApp')
        .config(stateConfig);
    stateConfig.$inject = ['$stateProvider']

    function stateConfig($stateProvider) {
        $stateProvider
            .state('admin',{
                parent:'restricted',
                template:"<div ui-view></div>",
                abstract: true,
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('admin');
                        $translatePartialLoader.addPart('errors');
                        $translatePartialLoader.addPart('success');
                        $translatePartialLoader.addPart('global');
                        $translatePartialLoader.addPart('inventory');
                        return $translate.refresh();
                    }]
                }
            })
            .state('users',{
                parent: 'inventory',
                url: '/users',
                templateUrl: 'app/admin/user.home.html',
                controller: 'UserHomeController',
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Danh sách nhân viên',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER','danh_sach_nhan_vien','ROLE_USER'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                params: {
                    organizationId: null,
                    organizationName: null
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/admin/user.home.controller.js'
                        ]);
                    }]
                }
            })
            .state('users-create',{
                url:'/users/create',
                templateUrl:'app/admin/user.create.html',
                parent:'inventory',
                data: {
                    pageTitle: 'Tạo mới nhân viên',
                    authorities:['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','user_create','ROLE_USER'],
                    sideBarMenu: 'inventory'
                },
                controller: 'UserCreateController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'assets/js/custom/uikit_fileinput.min.js',
                            'app/admin/user.create.controller.js'
                        ]);
                    }]
                }

            })
            .state('users-edit',{
                url:'/users/{userId:[0-9]{1,9}}/edit',
                templateUrl:'app/admin/user.edit.html',
                parent:'inventory',
                data: {
                    pageTitle: 'Chi tiết nhân viên',
                    authorities:['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','user_edit','ROLE_USER'],
                    sideBarMenu: 'inventory'
                },
                controller: 'UserEditController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/admin/user.edit.controller.js',
                            'assets/js/custom/uikit_fileinput.min.js'
                        ]);
                    }]
                }

            })
            .state('users-detail',{
                url:'/users/{userId:[0-9]{1,9}}/detail',
                templateUrl:'app/admin/user.detail.html',
                parent:'inventory',
                data: {
                    pageTitle: 'Chi tiết nhân viên',
                    authorities:['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','danh_sach_nhan_vien','ROLE_USER'],
                    sideBarMenu: 'inventory'
                },
                controller: 'UserDetailController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/admin/user.detail.controller.js',
                            'assets/js/custom/uikit_fileinput.min.js'
                        ]);
                    }]
                }

            })
            .state('privileges',{
                parent: 'inventory',
                url: '/privileges',
                templateUrl: 'app/admin/privilege.home.html',
                controller: 'PrivilegeHomeController',
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Danh sách quyền',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','phan_quyen'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/admin/privilege.home.controller.js'
                        ]);
                    }]
                }
            })
            .state('privileges-detail',{
                url:'/privileges/{privilegeName}/detail',
                templateUrl:'app/admin/privilege.detail.html',
                parent:'inventory',
                data: {
                    pageTitle: 'Chi tiết quyền',
                    authorities:['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','phan_quyen'],
                    sideBarMenu: 'inventory'
                },
                controller: 'PrivilegeDetailController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'app/admin/privilege.detail.controller.js'
                        ]);
                    }]
                }

            })
            .state('roles',{
                parent: 'inventory',
                url: '/roles',
                templateUrl: 'app/admin/role.home.html',
                controller: 'RoleHomeController',
                controllerAs: 'vm',
                data: {
                    pageTitle: 'Vai trò',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','role_view'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/admin/role.home.controller.js'
                        ]);
                    }]
                }
            })
            .state('roles-create',{
                url:'/roles/create',
                templateUrl:'app/admin/role.create.html',
                parent:'inventory',
                data: {
                    pageTitle: 'Vai trò',
                    authorities:['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','role_create'],
                    sideBarMenu: 'inventory'
                },
                controller: 'RoleCreateController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'lazy_tree',
                            'app/admin/role.create.controller.js'
                        ]);
                    }]
                }

            })
            .state('roles-edit',{
                url:'/roles/{roleId:[0-9]{1,9}}/edit',
                templateUrl:'app/admin/role.edit.html',
                parent:'inventory',
                data: {
                    pageTitle: 'Vai trò',
                    authorities:['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','role_edit'],
                    sideBarMenu: 'inventory'
                },
                controller: 'RoleEditController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'lazy_tree',
                            'app/admin/role.edit.controller.js'
                        ]);
                    }]
                }

            })
            .state('roles-detail',{
                url:'/roles/{roleId:[0-9]{1,9}}/detail\'',
                templateUrl:'app/admin/role.detail.html',
                parent:'inventory',
                data: {
                    pageTitle: 'Vai trò',
                    authorities:['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','role_view'],
                    sideBarMenu: 'inventory'
                },
                controller: 'RoleDetailController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'lazy_tree',
                            'app/admin/role.detail.controller.js'
                        ]);
                    }]
                }

            })
            .state('user-profile',{
                url:'/user-profile',
                templateUrl:'app/admin/user.editProfile.html',
                parent:'inventory',
                data: {
                    pageTitle: 'admin.user.detail',
                    sideBarMenu: 'inventory'
                },
                controller: 'UserProfileEditController',
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/admin/userProfile.edit.controller.js'
                        ]);
                    }]
                }

            })
            .state('dashboard',{
                parent: 'inventory',
                url: '/dashboard',
                templateUrl: 'app/dashboard/dashboard.html',
                controller: 'DashboardController',
                controllerAs: 'vm',
                params: {
                    from: null
                },
                data: {
                    pageTitle: 'admin.menu.dashboard',
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_iCheck',
                            'lazy_tree',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/dashboard/dashboard.controller.js'
                        ]);
                    }]
                }
            })
    }
})();