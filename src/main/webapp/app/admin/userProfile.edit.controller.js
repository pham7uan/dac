(function(){
    'use strict';
    angular.module('erpApp')
        .controller('UserProfileEditController',UserProfileEditController);

    UserProfileEditController.$inject = ['$rootScope','$scope','$state','$stateParams','User', 'Role', 'AlertService', 'ErrorHandle', '$translate',
        'Organization','$timeout','$filter', 'ComboBoxController','KpiUser','TableController'];
    function UserProfileEditController($rootScope,$scope, $state, $stateParams, User, Role, AlertService,
                                       ErrorHandle, $translate,Organization,$timeout,$filter, ComboBoxController,KpiUser,TableController ) {
        var vm = this;
        $scope.blockModal;
        $scope.blockUI = function () {
            if($scope.blockModal != null)
                $scope.blockModal.hide();
            $scope.blockModal = null;
            $scope.blockModal = UIkit.modal.blockUI('<div class=\'uk-text-center\'>Đang xử lý ...<br/><img class=\'uk-margin-top\' src=\'assets/img/spinners/spinner_success.gif\' alt=\'\'>');
        };
        $scope.user = {};
        $scope.user.oldAvatar = "";
        $scope.allRoles = {};

        var statusStyle = {
            true: "uk-text-success uk-text-bold",
            false: "uk-text-danger uk-text-bold"
        }

        User.current().then(function (data) {
            $scope.user = data;
        });

        $scope.btnDisable = false;
        $scope.changePass = function () {
            if($scope.btnDisable) return;

            var $form = $("#changePassForm");
            $('#changePassForm').parsley();
            if(!$scope.changePassForm.$valid) return;
            if(!ComboBoxController.checkIsValidForm($form)) return;

            var user = {
                id: $scope.user.id,
                password: $scope.currentPassword,
                newPassword: $scope.newPassword,
                confirmPassword: $scope.confirmPassword
            };

            $scope.btnDisable = true;
            User.changePassword(user).then(function () {
                $scope.resetPasswordForm();
                AlertService.success('admin.messages.changePasswordSuccess');
                $timeout(function () {
                    angular.element("#btnCancel").trigger("click");
                    $scope.btnDisable = false;
                });
            }).catch(function(data){
                $scope.resetPasswordForm();
                ErrorHandle.handleError(data);
                $scope.btnDisable = false;
            })

        }

        $scope.resetPasswordForm = function () {
            $scope.currentPassword = null;
            $scope.newPassword = null;
            $scope.confirmPassword = null;

            $("#changePassForm").parsley().reset();
        }

        $scope.btnDisable = false;
        $scope.submit = function() {
            if($scope.btnDisable) return;
            $scope.btnDisable = true;

            $scope.blockUI();
            updateUser();

        }
        function genDateTime(time) {
            var date = $filter('date')(time, 'dd/MM/yyyy');
            return date
        }

        function resetOverFlow() {
            $timeout(function () {
                $('.uk-modal-page').css({'overflow-y': 'scroll'});
                $('.uk-modal-page body').css({ 'overflow-y': 'unset', 'padding-right': 0});
            }, 1000);
        }
        var today = new Date();
        $scope.firstDay = new Date(today.getFullYear(), today.getMonth(), 1);
        $scope.todayTime = genDateTime(today.getTime());

        var columns = {
            'id':'Number',
            'kpiName':'Text',
            'note':'Text',
            'point':'Number',
            'date':'DateTime'
        };

        // khai bao cau hinh cho bang
        var tableConfig = {
            tableId: "main",               //table Id
            model: "kpiusers",                 //model
            defaultSort:"date",          //sap xep mac dinh theo cot nao
            sortType: "desc",                //kieu sap xep
            loadFunction: KpiUser.getPage,     //api load du lieu
            columns: columns,               //bao gom cac cot nao
            handleAfterReload: null,        //xu ly sau khi load du lieu
            handleAfterReloadParams: null,  //tham so cho xu ly sau khi load
            deleteCallback: null,           //delete callback
            customParams: 'userId==' + $stateParams.userId,               //dieu kien loc ban dau
            pager_id: "table_user_pager",   //phan trang
            page_id: "user_selectize_page", //phan trang
            page_number_id: "user_selectize_pageNum",   //phan trang
            page_size_option: ["5", "50", "25", "50"]   //lua chon size cua 1 page
        };

        TableController.initTable($scope, tableConfig);     //khoi tao table
        TableController.sortDefault(tableConfig.tableId);   //set gia tri sap xep mac dinh

        $timeout(function () {
            $("#startDatePicker").kendoDatePicker({
                format: "dd/MM/yyyy",
                change: function() {
                    var value = this.value();
                    if(value !=null){
                        $scope.searchKpi.start = value.getTime()
                    } else {
                        $scope.searchKpi.start = null;
                    }
                }
            });

            $("#endDatePicker").kendoDatePicker({
                format: "dd/MM/yyyy",
                change: function() {
                    var value = this.value();
                    if(value !=null){
                        $scope.searchKpi.end = value.getTime()
                    } else {
                        $scope.searchKpi.end = null;
                    }
                }
            });
            var startDatePicker = $("#startDatePicker").data("kendoDatePicker");
            startDatePicker.value($scope.firstDay);
            startDatePicker.trigger("change");

            var endDatePicker = $("#endDatePicker").data("kendoDatePicker");
            endDatePicker.value($scope.todayTime);
            endDatePicker.trigger("change");

            $scope.searchKpiUser();
        });

        $scope.searchKpi = {}
        $scope.searchKpiUser =function () {
            $scope.TABLES[tableConfig.tableId].customParams = "userId==" +$scope.user.id;
            if($scope.searchKpi.start){
                $scope.TABLES[tableConfig.tableId].customParams +=";date>=" + $scope.searchKpi.start
            }
            if($scope.searchKpi.end){
                $scope.TABLES[tableConfig.tableId].customParams +=";date<=" + $scope.searchKpi.end
            }
            TableController.reloadPage(tableConfig.tableId);
        }

        $timeout(function () {
            $scope.searchKpiUser();
        },1000);



        function updateUser(){
            User.update($scope.user).then(function(data){
                if($scope.blockModal != null) $scope.blockModal.hide();
                AlertService.success("success.msg.update");
                $timeout(function () {
                    $state.go('user-profile',{userId: $scope.user.id });
                },1000);
            })
            .catch(function(data){
                if($scope.blockModal != null) $scope.blockModal.hide();
                AlertService.error('admin.messages.errorUpdateUser');
                $scope.btnDisable = false;
            })
        }


        if ( angular.element('#form_createuser').length ) {
            $scope.email_msg = $translate.instant('admin.messages.invalidEmail');
            $scope.required_msg = $translate.instant('admin.messages.required');
            $scope.phone = $translate.instant('global.messages.phone');
            $scope.email_msg = $translate.instant('admin.messages.invalidEmail');
            $scope.maxLength255 = $translate.instant('global.messages.maxLength255');
            $scope.phoneMaxLength = $translate.instant('global.messages.phoneMaxLength');
            $scope.phone = $translate.instant('global.messages.phone');
            var $formValidate = $('#form_createuser');
            $formValidate
                .parsley({
                    'excluded': 'input[type=button], input[type=submit], input[type=reset], input[type=hidden], .selectize-input > input'
                })
                .on('form:validated',function() {
                    $scope.$apply();
                })
                .on('field:validated',function(parsleyField) {
                    if($(parsleyField.$element).hasClass('md-input')) {
                        $scope.$apply();
                    }
                });
        }
    }

})();