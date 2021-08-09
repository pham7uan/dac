(function(){
    'use strict';
    angular.module('erpApp')
        .controller('TransferDetailController',TransferDetailController);

    TransferDetailController.$inject = ['$rootScope','$scope','$state','$http','$window','$stateParams','$filter',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController'];
    function TransferDetailController($rootScope,$scope, $state,$http,$window,$stateParams,$filter,
    $timeout, AlertService,
                               $translate, ErrorHandle,
                               TableController,ComboBoxController) {
        var vm = this;
        $scope.resolutionX = screen.width;
        $scope.ComboBox = {};
        $scope.user = $rootScope.currentUser;
        var today = new Date();
        $scope.todayTime = genDateTime(today.getTime());
        $scope.organizationId = $window.localStorage.getItem("organizationId");

        $scope.blockUI = function () {
            if($scope.blockModal != null) $scope.blockModal.hide();
            $scope.blockModal = null;
            $scope.blockModal = UIkit.modal.blockUI('<div class=\'uk-text-center\'>Đang xử lý ...<br/><img class=\'uk-margin-top\' src=\'assets/img/spinners/spinner_success.gif\' alt=\'\'>');
        };
        var $formValidate = $('#form_edit');
        $formValidate.parsley({
            'excluded': 'input[type=button], input[type=submit], input[type=reset], input[type=hidden], .selectize-input > input'
        }).on('form:validated',function() {
            $scope.$apply();
        }).on('field:validated',function(parsleyField) {
            if($(parsleyField.$element).hasClass('md-input')) {
                $scope.$apply();
            }
        });


    }
})();