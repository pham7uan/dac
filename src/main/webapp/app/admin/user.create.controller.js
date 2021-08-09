(function(){
    'use strict';
    angular.module('erpApp')
        .controller('UserCreateController',UserCreateController);

    UserCreateController.$inject = ['$rootScope','$scope','$state','User', 'Role', 'AlertService','AlertModalService','$translate','variables','ErrorHandle', '$window','$timeout', 'ComboBoxController','FileService'];
    function UserCreateController($rootScope,$scope, $state, User, Role, AlertService,AlertModalService,$translate, variables, ErrorHandle, $window,$timeout, ComboBoxController,FileService) {
        var vm = this;
        $scope.ComboBox = {};
        $scope.blockModal;
        $scope.blockUI = function () {
            if($scope.blockModal != null)
                $scope.blockModal.hide();
            $scope.blockModal = null;
            $scope.blockModal = UIkit.modal.blockUI('<div class=\'uk-text-center\'>Đang xử lý ...<br/><img class=\'uk-margin-top\' src=\'assets/img/spinners/spinner_success.gif\' alt=\'\'>');
        };

        $scope.user = {
            ctv:0
        };
        $scope.user.password = null;
        $scope.organizations = [];
        $scope.areaIds = [];
        $scope.status = "Active"

        $("#table_user").css('min-height', $( window ).height() - 300);
        $("#table_user").css('max-height', $( window ).height() - 300);
        angular.element($window).bind('resize', function(){
            $("#table_user").css('min-height', $( window ).height() - 300);
            $("#table_user").css('max-height', $( window ).height() - 300);
        });

        var $formValidate = $('#form_createuser');

        Role.getAll().then(function (data) {
            $scope.selectize_roles_options = data;
            $scope.allRoles = {};
            for(var i=0; i< data.length; i++){
                $scope.allRoles[data[i].id] = data[i];
            }
        });

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
                    createUser();
                }).catch(function (data) {
                    if($scope.blockModal != null) $scope.blockModal.hide();
                    ErrorHandle.handleError(data);
                    $scope.btnDisable = false;
                });
            } else{
                // if dont have file: update immedimately
                createUser();
            }
        }

        function createUser(){
            $scope.user.active = $scope.active
            $scope.user.roles = [];
            for(var i = 0; i < $scope.forms_advanced.selectize_roles.length; i++) {
                var role_id = $scope.forms_advanced.selectize_roles[i];
                $scope.user.roles.push($scope.allRoles[role_id]);
            }
            User.create($scope.user).then(function(data){
                if($scope.blockModal != null) $scope.blockModal.hide();
                AlertModalService.popup("success.msg.create");
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

        $scope.selectize_organizations_config = {
            plugins: {
                'remove_button': {
                    label     : ''
                }
            },
            maxItems: null,
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            create: false,
            render: {
                option: function(organizations_data, escape) {
                    return  '<div class="option">' +
                        '<span class="title">' + escape(organizations_data.name) + '</span>' +
                        '</div>';
                },
                item: function(organizations_data, escape) {
                    return '<div class="item longTextShowToolTip" title="'+escape(organizations_data.name)+'"><a href="/#/organizations/'+ escape(organizations_data.id) + '/detail">'  + escape(organizations_data.name) + '</a></div>';
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