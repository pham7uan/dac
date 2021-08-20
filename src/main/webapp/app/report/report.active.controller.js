(function(){
    'use strict';
    angular.module('erpApp')
        .controller('ReportActiveController',ReportActiveController);

    ReportActiveController.$inject = ['$rootScope','$scope','$state','$http','$window','$filter',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController','Report'];
    function ReportActiveController($rootScope,$scope, $state, $http, $window, $filter,
                                    $timeout, AlertService,
                                    $translate, ErrorHandle,
                                    TableController, ComboBoxController, Report) {
        //Lấy ngày hiện tại
        function genDateTime(time){
            var date= $filter('date')(time, 'dd/MM/yyyy');
            return date;
        }
        var today = new Date();
        $scope.todayTime = genDateTime(today.getTime());

        $scope.ComboBox = {};
        $scope.blockModal = null;
        $scope.blockUI = function (){
          if ($scope.blockModal != null)
              $scope.blockModal.hide();
          $scope.blockModal = null;
          $scope.blockModal = UIkit.modal.blockUI('<div class=\'uk-text-center\'>Đang xử lý...<br><img class=\'uk - margin - top\' src=\'assets/img/spinners/spinner_success.gif\' alt=\'\'>');
        };
        $scope.searchInfo = {
            hasActive: 0,
            areaIds: [],
            contractIds: [],
            projectIds: [],
            activeStartDate: null,
            activeEndDate: null
        }

        var areaCbb = {
            id: 'area',
            url: '/api/area',
            originParams: "",
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            table: null,
            column: null,
            maxItems: null,
            ngModel: [],
            options: [],
            placeholder: "Nhập khu vực...",
            orderBy: 'name,asc'
        };
        ComboBoxController.init($scope, areaCbb);

        var contractCbb = {
            id: 'contract',
            url: '/api/contract',
            originParams: "",
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            table: null,
            column: null,
            maxItems: null,
            ngModel: [],
            options: [],
            placeholder: "Hợp đồng...",
            orderBy: 'name,asc'
        }
        ComboBoxController.init($scope, contractCbb);

        var projectCbb = {
            id: 'project',
            url: '/api/project',
            originParams: "",
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            table: null,
            column: null,
            maxItems: null,
            ngModel: [],
            options: [],
            placeholder: "Dự án...",
            orderBy: 'name,asc'
        }
        ComboBoxController.init($scope, projectCbb);

        var areaIdList = "";


        let params = areaIdList.length > 0 ? "areaId=in=("+areaIdList+")":"";
        $scope.deviceReportInfo = {
            "totalDeviceImport": 0,
            "totalHasDeviceActive": 0,
            "totalHasNotDeviceActive": 0,
            "totalHasContract": 0,
            "deviceImports": []
        }

        var tableConfig = {
            tableId: "deviceImports",               //table Id
            model: "deviceImports",                 //model
            defaultSort:"areaName",          //sap xep mac dinh theo cot nao
            sortType: "asc",                //kieu sap xep
            loadFunction: null,     //api load du lieu
            paramBody: null,
            columns: null,               //bao gom cac cot nao
            handleAfterReload: null,        //xu ly sau khi load du lieu
            handleAfterReloadParams: null,  //tham so cho xu ly sau khi load
            deleteCallback: null,           //delete callback
            loading:false,
            customParams: params,        //dieu kien loc ban dau
            pager_id: "table_user_pager",   //phan trang
            page_id: "user_selectize_page", //phan trang
            page_number_id: "user_selectize_pageNum",   //phan trang
            page_size_option: ["5", "10", "25", "50"]   //lua chon size cua 1 page
        };

        TableController.initTable($scope, tableConfig); //khởi tạo table

        $scope.exportPdf = function (){
            $scope.searchInfo.typeExport = "pdf";
            $scope.blockUI();
            Report.exportReportActive($scope.searchInfo).then(function (res){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                $scope.downloadUrl = res.fileName + "/api/download?filePath=" + res.url;
                $timeout(function (){
                    angular.element("#exportReport").trigger("click");
                });
            }).catch(function (error){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                console.log(error);
            });
        }

        $scope.exportExcel = function (){
            $scope.searchInfo.typeExport = "excel";
            $scope.blockUI();
            Report.exportReportActive($scope.searchInfo).then(function (res){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                $scope.downloadUrl = res.fileName + "/api/download?filePath=" + res.url;
                $timeout(function (){
                    angular.element("#exportReport").trigger("click");
                });
            }).catch(function (error){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                console.log(error);
            });
        }


        $("#activeStartDatePicker").kendoDatePicker({
            format:"dd/MM/yyyy",
            change: function (){
                var value = this.value();
                if (value != null){
                    $scope.searchInfo.activeStartDate = value.getTime() - 1;
                }else {
                    $scope.searchInfo.activeStartDate = null;
                }
            }
        });

        $("#activeEndDatePicker").kendoDatePicker({
            format: "dd/MM/yyyy",
            change: function() {
                var value = this.value();
                if(value !=null){
                    $scope.searchInfo.activeEndDate = value.getTime() + 86400000;
                } else {
                    $scope.searchInfo.activeEndDate = null;
                }
            }
        });

        let validate = function (searchInfo) {
            let isValid = true;
            if(searchInfo.activeStartDate != null && searchInfo.activeEndDate != null && searchInfo.activeEndDate < searchInfo.activeStartDate) {
                isValid = false;
                $("#activeEndDatePicker").parent().css("border", "1px solid red");
            } else {
                $("#activeEndDatePicker").parent().css("border", "1px solid #e6e6e6");
            }
            return isValid;
        };

        $scope.isSearching = false;
        $scope.processSearch = function() {
            if(!validate($scope.searchInfo)) {
                AlertService.error("Ngày kết thúc phải lớn hơn ngày bắt đầu");
                return;
            }
            $scope.isSearching = true;
            $scope.blockUI();
            Report.getReportDevice($scope.searchInfo).then(function (data) {
                $scope.deviceReportInfo.totalDeviceImport = data.totalDeviceImport;
                $scope.deviceReportInfo.totalHasDeviceActive = data.totalHasDeviceActive;
                $scope.deviceReportInfo.totalHasNotDeviceActive = data.totalHasNotDeviceActive;
                $scope.deviceReportInfo.totalHasContract = data.totalHasContract;
                $scope.deviceReportInfo.deviceImports = data.deviceImports;
                $scope.isSearching = false;
                if ($scope.blockModal != null) {$scope.blockModal.hide();}
            }).catch(function (data) {
                ErrorHandle.handleOneError(data);
                if ($scope.blockModal != null){$scope.blockModal.hide();}
            });

            $scope.isEmptyData = false;
            tableConfig.customParams = customParams();
            tableConfig.page_size_option = ["5", "10", "25", "50"] ;
            tableConfig.selectize_pageNum = $scope.pageNum;
            tableConfig.loadFunction = Report.getPage;
            TableController.initTable($scope, tableConfig);
            TableController.sortDefault(tableConfig.tableId);
            TableController.reloadPage(tableConfig.tableId);
        }

        let customParams = function() {
            let params = "";
            if($scope.searchInfo.hasActive == 1) {
                params += "pricingCode!=null;";
            }
            if($scope.searchInfo.hasActive == 2) {
                params += "pricingCode==null;";
            }
            if($scope.searchInfo.areaIds.length > 0) {
                params += "areaId=in=("+$scope.searchInfo.areaIds+");";
            }
            if($scope.searchInfo.activeStartDate != null && $scope.searchInfo.activeStartDate != "") {
                params += "activeDate>=("+$scope.searchInfo.activeStartDate+");";
            }
            if($scope.searchInfo.activeEndDate != null && $scope.searchInfo.activeEndDate != "") {
                params += "activeDate<=("+$scope.searchInfo.activeEndDate+");";
            }
            if($scope.searchInfo.contractIds.length > 0) {
                params += "contractId=in=("+$scope.searchInfo.contractIds+");";
            }
            if($scope.searchInfo.projectIds.length > 0) {
                params += "projectId=in=("+$scope.searchInfo.projectIds+");";
            }
            return params;
        }


        $scope.selectize_roles_config = {
            plugins: {
                'remove_button': {
                    label: ''
                }
            },
            maxItems: 1,
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            create: false
        };

        $scope.selectize_roles_options = [
            {id: 0, name: "Tất cả"},
            {id: 1, name: "Thiết bị có gói cước"},
            {id: 2, name: "Thiết bị không có gói cước"}
        ];
    }
})();