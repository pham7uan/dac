(function(){
    'use strict';
    angular.module('erpApp')
        .controller('UserEditController',UserEditController);

    UserEditController.$inject = ['$rootScope','$scope','$state','$stateParams','$window','$filter','User', 'Role', 'AlertService','AlertModalService',
        'ErrorHandle', '$translate','$timeout', 'ComboBoxController'];
    function UserEditController($rootScope,$scope, $state, $stateParams,$window,$filter, User, Role, AlertService,AlertModalService,
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
        var $formValidate = $('#form_createuser');
        $scope.btnDisable = false;
        $scope.submit = function() {
            var $form = $("#form_createuser");
            $('#form_createuser').parsley();
            if(!$scope.form_createuser.$valid) return;
            if(!ComboBoxController.checkIsValidForm($form)) return;
            if($scope.btnDisable) return;
            $scope.btnDisable = true;

            $scope.blockUI();
            var file = $("#user-input-form-file")[0].files[0];
            if(file && file != null){
                FileService.uploadAvatar(file,1).then(function (data) {
                    $scope.user.userAvatar = data.data.fileName;
                    updateUser();
                }).catch(function (data) {
                    if($scope.blockModal != null) $scope.blockModal.hide();
                    ErrorHandle.handleError(data);
                    $scope.btnDisable = false;
                });
            } else{
                // if dont have file: update immedimately
                updateUser();
            }
        }

        function updateUser(){
            $scope.user.active = $scope.active
            $scope.user.roles = [];
            for(var i = 0; i < $scope.forms_advanced.selectize_roles.length; i++) {
                var role_id = $scope.forms_advanced.selectize_roles[i];
                $scope.user.roles.push($scope.allRoles[role_id]);
            }
            User.update($scope.user).then(function(data){
                if($scope.blockModal != null) $scope.blockModal.hide();
                AlertModalService.popup("success.msg.update");
                $timeout(function () {
                    $state.go('users-detail',{userId: data.id});
                },1100);
            }).catch(function(data){
                if($scope.blockModal != null) $scope.blockModal.hide();
                ErrorHandle.handleError(data);
                $scope.btnDisable = false;
            });
        }

        $scope.forms_advanced = {
            selectize_roles: [],
            selectize_organizations:[]
        };

        $scope.selectize_roles_config = {
            plugins: {
                'remove_button': {
                    label     : ''
                }
            },
            maxItems: null,
            valueField: 'id',
            labelField: 'displayName',
            searchField: 'displayName',
            create: false,
            render: {
                option: function(roles_data, escape) {
                    return  '<div class="option" >' +
                        '<span class="title">' + escape(roles_data.displayName) + '</span>' +
                        '</div>';
                },
                item: function(roles_data, escape) {
                    return '<div class="item longTextShowToolTip" title="'+escape(roles_data.displayName)+'"><a href="/#/roles/'+ escape(roles_data.id) + '/detail">'  + escape(roles_data.displayName) + '</a></div>';
                }
            }
        };

        $formValidate.parsley({
            'excluded': 'input[type=button], input[type=submit], input[type=reset], input[type=hidden], .selectize-input > input'
        }).on('form:validated',function() {
            $scope.$apply();
        }).on('field:validated',function(parsleyField) {
            if($(parsleyField.$element).hasClass('md-input')) {
                $scope.$apply();
            }
        });
        $scope.deleteUserAvatar = function () {
            $scope.user.userAvatar = $scope.user.userAvatarBase64 = "";
            $("#user-input-form-file").val("");
        }
    }

})();