(function(){
    'use strict';
    angular.module('erpApp')
        .controller('TransferController',TransferController);

    TransferController.$inject = ['$rootScope','$scope','$state','$http','$window','$stateParams','$filter',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController'];
    function TransferController($rootScope,$scope, $state,$http,$window,$stateParams,$filter,
    $timeout, AlertService,
                               $translate, ErrorHandle,
                               TableController,ComboBoxController) {
        var vm = this;
        $scope.resolutionX = screen.width;
        $scope.ComboBox = {};
    }
})();