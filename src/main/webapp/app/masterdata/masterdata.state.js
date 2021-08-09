(function(){
    'use strict';
    angular.module('erpApp')
        .config(stateConfig);
    stateConfig.$inject = ['$stateProvider']

    function stateConfig($stateProvider) {

        $stateProvider
            .state('masterdata',{
                parent:'restricted',
                template:"<div ui-view></div>",
                abstract: true,
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('errors');
                        $translatePartialLoader.addPart('success');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('products',{
                parent: 'inventory',
                url: '/products',
                templateUrl: 'app/masterdata/product/products.html',
                controller: 'ProductController',
                controllerAs: 'vm',
                params: {
                    organizationId: null
                },
                data: {
                    pageTitle: 'Cấu hình hàng hóa',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER', 'product_view'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'lazy_tree',
                            'app/masterdata/product/product.controller.js'
                        ]);
                    }]
                }
            })
            .state('pricing',{
                parent: 'inventory',
                url: '/pricing',
                templateUrl: 'app/masterdata/pricing/pricing.html',
                controller: 'PricingController',
                controllerAs: 'vm',
                params: {
                    organizationId: null
                },
                data: {
                    pageTitle: 'Gói cước',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER', 'pricing_view'], //TODO change role,
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_parsleyjs',
                            'lazy_KendoUI',
                            'lazy_tree',
                            'app/masterdata/pricing/pricing.controller.js'
                        ]);
                    }]
                }
            })
    }
})();