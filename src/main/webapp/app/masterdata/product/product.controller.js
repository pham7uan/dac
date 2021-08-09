(function(){
    'use strict';
    angular.module('erpApp')
        .controller('ProductController',ProductController);

    ProductController.$inject = ['$rootScope','$scope','$state','$http','$window','$filter',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController'];
    function ProductController($rootScope,$scope, $state,$http,$window,$filter,
                               $timeout, AlertService,
                               $translate, ErrorHandle,
                               TableController,ComboBoxController) {

    }
})();