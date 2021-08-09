(function () {
    'use strict';
    angular.module('erpApp')
        .config(stateConfig);
    stateConfig.$inject = ['$stateProvider']

    function stateConfig($stateProvider) {

        $stateProvider
            .state('inventory', {
                parent: 'restricted',
                template: "<div ui-view></div>",
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
            //stock
            .state('transfer',{
                parent: 'inventory',
                url: '/buy',
                templateUrl: 'app/inventory/transfer/transfers.html',
                controller: 'TransferController',
                controllerAs: 'vm',
                params: {
                    tenantId: null,
                    transferType: 1,
                    notification: false,
                    transferId: null,
                    state:null,
                    internal:false,
                },
                data: {
                    pageTitle: 'Đơn hàng',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER','transfer_view'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/inventory/transfer/transfer.controller.js'
                        ]);
                    }]
                }
            })
            .state('transfer-edit',{
                parent: 'inventory',
                url: '/buy/{id}/edit',
                templateUrl: 'app/inventory/transfer/transfer.edit.html',
                controller: 'TransferEditController',
                controllerAs: 'vm',
                params: {
                    tenantId: null,
                    transferType: 1,
                    notification: false,
                    transferId: null,
                    state:null,
                    internal:false,
                },
                data: {
                    pageTitle: 'Đơn hàng',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER','transfer_edit'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/inventory/transfer/transfer.edit.controller.js'
                        ]);
                    }]
                }
            })
            .state('transfer-create',{
                parent: 'inventory',
                url: '/buy/create',
                templateUrl: 'app/inventory/transfer/transfer.edit.html',
                controller: 'TransferEditController',
                controllerAs: 'vm',
                params: {
                    tenantId: null,
                    transferType: 1,
                    notification: false,
                    transferId: null,
                    state:null,
                    internal:false,
                },
                data: {
                    pageTitle: 'Đơn hàng',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER','transfer_create'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/inventory/transfer/transfer.edit.controller.js'
                        ]);
                    }]
                }
            })
            .state('transfer-detail',{
                parent: 'inventory',
                url: '/buy/{id}/detail',
                templateUrl: 'app/inventory/transfer/transfer.detail.html',
                controller: 'TransferDetailController',
                controllerAs: 'vm',
                params: {
                    tenantId: null,
                    transferType: 1,
                    notification: false,
                    transferId: null,
                    state:null,
                    internal:false,
                },
                data: {
                    pageTitle: 'Đơn hàng',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER','transfer_view'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'app/inventory/transfer/transfer.detail.controller.js'
                        ]);
                    }]
                }
            })

    }
})();