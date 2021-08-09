(function(){
    'use strict';
    angular.module('erpApp')
        .controller('UserDetailController',UserDetailController);

    UserDetailController.$inject = ['$rootScope','$scope','$state','$stateParams','$window','$filter','User', 'Role', 'AlertService','AlertModalService',
        'ErrorHandle', '$translate','$timeout', 'ComboBoxController'];
    function UserDetailController($rootScope,$scope, $state, $stateParams,$window,$filter, User, Role, AlertService,AlertModalService,
                                  ErrorHandle, $translate,$timeout, ComboBoxController ) {
        var vm = this;
        $scope.isCurrentUser = false;
        $scope.ComboBox = {};
        $scope.blockModal;
        function resetOverFlow() {
            $timeout(function () {
                $('.uk-modal-page').css({'overflow-y': 'scroll'});
                $('.uk-modal-page body').css({ 'overflow-y': 'unset', 'padding-right': 0});
            }, 1000);
        }
        var today = new Date();
        $scope.firstDay = new Date(today.getFullYear(), today.getMonth(), 1);
        $scope.todayTime = genDateTime(today.getTime());

        $scope.isChangePass = false;
        $scope.editPass = function(){
            if(!$scope.isChangePass){
                $scope.isChangePass = true;
            } else {
                $scope.isChangePass = false;
                $scope.user.password = null;
                $scope.user.confirm_password = null;
            }
        }
        $scope.blockUI = function () {
            if($scope.blockModal != null)
                $scope.blockModal.hide();
            $scope.blockModal = null;
            $scope.blockModal = UIkit.modal.blockUI('<div class=\'uk-text-center\'>Đang xử lý ...<br/><img class=\'uk-margin-top\' src=\'assets/img/spinners/spinner_success.gif\' alt=\'\'>');
        };

        $scope.email_msg = $translate.instant('admin.messages.invalidEmail');
        $scope.required_msg = $translate.instant('admin.messages.required');
        $scope.maxLength255 = $translate.instant('global.messages.maxLength255')
        $scope.phoneMaxLength = $translate.instant('global.messages.phoneMaxLength')
        $scope.phone = $translate.instant('global.messages.phone')

        $scope.user = {};
        $scope.organizationIds = [];
        $scope.organizations = [];
        $scope.user.oldAvatar = "";
        $scope.allRoles = {};

        $scope.showRole = true;
        Role.getAll().then(function (data) {
            $scope.selectize_roles_options = data;
            $scope.allRoles = {};
            for(var i=0; i< data.length; i++){
                $scope.allRoles[data[i].id] = data[i];
            }
        });
        User.getUserById($stateParams.userId).then(function (data) {
            $scope.user = data;
            if($scope.user.roles && $scope.user.roles.length > 0){
                for(var j = 0; j < $scope.user.roles.length; j++) {
                    if($scope.user.roles[j].name == "ROLE_SYSTEM_ADMIN"){
                        $scope.showRole = false;
                    }
                    $scope.forms_advanced.selectize_roles.push($scope.user.roles[j].id);
                }
            }
            if(data.birthday !=null){
                var birthDatePicker = $("#birthDatePicker").data("kendoDatePicker");
                birthDatePicker.value(genDateTime(data.birthday));
                birthDatePicker.trigger("change");
            }
        });
        function genDateTime(time) {
            var date = $filter('date')(time, 'dd/MM/yyyy');
            return date
        }

        $scope.forms_advanced = {
            selectize_roles: [],
        };

        var roles_data = $scope.selectize_roles_options = [];

        $scope.selectize_roles_config = {
            maxItems: null,
            valueField: 'id',
            labelField: 'displayName',
            searchField: 'displayName',
            create: false,
            render: {
                option: function(roles_data, escape) {
                    return  '<div class="option">' +
                        '<span class="title">' + escape(roles_data.displayName) + '</span>' +
                        '</div>';
                },
                item: function(roles_data, escape) {
                    return '<div class="item longTextShowToolTip" title="'+escape(roles_data.displayName)+'"><a  target="_blank" href="/#/roles/'+ escape(roles_data.id) + '/detail">'  + escape(roles_data.displayName) + '</a></div>';
                }
            }
        };
    }

})();