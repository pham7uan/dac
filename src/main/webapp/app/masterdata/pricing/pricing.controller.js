(function(){
    'use strict';
    angular.module('erpApp')
        .controller('PricingController',PricingController);

    PricingController.$inject = ['$rootScope','$scope','$state','$http','$window',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController'];
    function PricingController($rootScope,$scope, $state,$http,$window,
                               $timeout, AlertService,
                               $translate, ErrorHandle,
                               TableController,ComboBoxController) {

    }
})();