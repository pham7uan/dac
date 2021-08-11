(function (){
    'use strict';
    angular.module('erpApp')
        .config(stateConfig);
    stateConfig.$inject = ['$stateProvider']

    function stateConfig($stateProvider){

        $stateProvider.
            state('report-module', {
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
            .state('report-active',{
                parent: 'report-module',
                url: '/report-active',
                templateUrl: 'app/report/report-active.html',
                controller: 'ReportActiveController',
                params: {
                    packageId: null
                },
                data: {
                    pageTitle: 'Báo cáo số lượng thiết bị',
                    authorities: ['ROLE_SYSTEM_ADMIN', 'ROLE_ORGANIZATION','ROLE_MANAGER','report_view', 'ROLE_USER'],
                    sideBarMenu: 'inventory'
                },
                resolve: {
                    deps: ['$ocLazyLoad', function ($ocLazyLoad){
                        return $ocLazyLoad.load([
                            'lazy_ionRangeSlider',
                            'lazy_tablesorter',
                            'lazy_KendoUI',
                            'lazy_parsleyjs',
                            'app/report/report.active.controller.js'
                        ]);
                    }]
                }
            })
    }
})();