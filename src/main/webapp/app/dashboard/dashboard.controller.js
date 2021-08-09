(function(){
    'use strict';
    angular.module('erpApp')
        .controller('DashboardController', DashboardController);

    DashboardController.$inject = ['$rootScope','$scope','$stateParams', 'AlertService', 'ErrorHandle', '$window', '$timeout'];
    function DashboardController($rootScope,$scope,$stateParams, AlertService, ErrorHandle, $window,$timeout) {
        $scope.user = $rootScope.currentUser;
        $scope.organizationId = $window.localStorage.getItem("organizationId");
        $scope.mobile = $window.localStorage.getItem("mcMobile");
        $scope.resolutionX = screen.width;

        $scope.blockModal = null;
        $scope.blockUI = function () {
            if ($scope.blockModal != null) $scope.blockModal.hide();
            $scope.blockModal = null;
            $scope.blockModal = UIkit.modal.blockUI('<div class=\'uk-text-center\'>Đang xử lý ...<br/><img class=\'uk-margin-top\' src=\'assets/img/spinners/spinner_success.gif\' alt=\'\'>');
        };

    }
})();