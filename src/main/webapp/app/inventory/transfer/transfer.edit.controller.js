(function(){
    'use strict';
    angular.module('erpApp')
        .controller('TransferEditController',TransferEditController);

    TransferEditController.$inject = ['$rootScope','$scope','$state','$http','$window','$stateParams','$filter',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController'];
    function TransferEditController($rootScope,$scope, $state,$http,$window,$stateParams,$filter,
    $timeout, AlertService,
                               $translate, ErrorHandle,
                               TableController,ComboBoxController) {
        var vm = this;
        $scope.resolutionX = screen.width;
        $scope.ComboBox = {};
        function resetOverFlow() {
            $timeout(function () {
                $('.uk-modal-page').css({'overflow-y': 'scroll'});
                $('.uk-modal-page body').css({ 'overflow-y': 'unset', 'padding-right': 0});
            }, 1000);
        }
        function genDateTime(time) {
            var date = $filter('date')(time, 'dd/MM/yyyy');
            return date
        }
        var today = new Date();
        $scope.todayTime = genDateTime(today.getTime());
        $scope.organizationId = $window.localStorage.getItem("organizationId");

        $scope.blockUI = function () {
            if($scope.blockModal != null) $scope.blockModal.hide();
            $scope.blockModal = null;
            $scope.blockModal = UIkit.modal.blockUI('<div class=\'uk-text-center\'>Đang xử lý ...<br/><img class=\'uk-margin-top\' src=\'assets/img/spinners/spinner_success.gif\' alt=\'\'>');
        };

    }
})();