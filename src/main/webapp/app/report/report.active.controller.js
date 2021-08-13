(function(){
    'use strict';
    angular.module('erpApp')
        .controller('ReportActiveController',ReportActiveController);

    ReportActiveController.$inject = ['$rootScope','$scope','$state','$http','$window','$filter',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController','Report'];
    function ReportActiveController($rootScope,$scope, $state,$http,$window,$filter,
                                    $timeout, AlertService,
                                    $translate, ErrorHandle,
                                    TableController,ComboBoxController,Report) {
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
        var currentUser = $rootScope.currentUser;
        $scope.searchInfo = {
            areaIds: [],
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

        var areaIds = []
        var areaIdList = "";
        // if(!currentUser.areaIds.includes(1)) {
        //     currentUser.areas.forEach(function (area) {
        //         areaCodes.push(area.areaCode);
        //     })
        //     if(areaCodes.length > 0) {
        //         areaCodeList = areaCodes.join(',');
        //     }
        // }

        let params = areaIdList.length > 0 ? "areaId=in=("+areaIdList+")":"";
        $scope.deviceReportInfo = {
            "totalDeviceImport": 0,
            "deviceImports":[]
        }

        var tableConfig = {
            tableId: "masterDevices",               //table Id
            model: "masterDevices",                 //model
            defaultSort:"serialNumber",          //sap xep mac dinh theo cot nao
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
            debugger
            Report.exportReportActive($scope.searchInfo).then(function (res){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                debugger
                $scope.downloadUrl = "api/download?filePath=" + res.url;
                timeout(function (){
                    angular.element("#exportReport").trigger("click");
                });
            }).catch(function (error){
                debugger
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                AlertService.error($translate.instant(error.data.errorKey));
            });
        }

        $scope.exportExcel = function (){
            $scope.searchInfo.exportType = "excel";
            $scope.blockUI();
            debugger
            Report.exportReportActive($scope.searchInfo).then(function (res){
                debugger
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                $scope.downloadUrl = "api/download?filePath=" + res.url;
                timeout(function (){
                    angular.element("#exportReport").trigger("click");
                });
            }).catch(function (error){
                debugger
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                AlertService.error($translate.instant(error.data.errorKey));
            });
        }


        $("#activeStartDatePicker").kendoDatePicker({
            format:"dd/MM/yyyy",
            change: function (){
                var value = this.value();
                if (value != null){
                    $scope.searchInfo.activeStartDate = value.getTime();
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
                    $scope.searchInfo.activeEndDate = value.getTime();
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
                $scope.deviceReportInfo.deviceImports = data;
                $scope.deviceReportInfo.totalDeviceImport = data.length;
                if ($scope.blockModal != null) {$scope.blockModal.hide();}
                $scope.isSearching = false;
            }).catch(function (data) {
                ErrorHandle.handleOneError(data);
                if ($scope.blockModal != null){$scope.blockModal.hide();}
            });
        }

        // Default là ngày hiện tại
        var activeEndDatePicker = $("#activeEndDatePicker").data("kendoDatePicker");
        activeEndDatePicker.value($scope.todayTime);
        activeEndDatePicker.trigger("change");

        // Default là ngày cách ngày kích hoạt thiết bị đến 1 tháng
        today.setMonth(today.getMonth()-1)
        today.toLocaleString();
        $scope.todayTime = genDateTime(today.getTime());
        var activeStartDatePicker = $("#activeStartDatePicker").data("kendoDatePicker");
        activeStartDatePicker.value($scope.todayTime);
        activeStartDatePicker.trigger("change");
    }
})();