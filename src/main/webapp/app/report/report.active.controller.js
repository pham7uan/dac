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

        $scope.searchInfo = {
            areaCodes: [],
            activeStartDate: null,
            activeEndDate: null
        }

        $scope.ComboBox = {};
        var areaCbb = {
            id: 'area',
            url: '/api/areas',
            originParams: "type==0",
            valueField: 'areaCode',
            labelField: 'name',
            searchField: 'name',
            table: null,
            column: null,
            maxItems: null,
            ngModel: [],
            options: areaCodes,
            placeholder: "Nhập khu vực",
            orderBy: 'name,asc'
        };
        ComboBoxController.init($scope, areaCbb);
        $scope.deviceReportInfo = {
            "totalDeviceImport": 0,
            "deviceImports":[]
        }

        $scope.exportPdf = function (){
            $scope.blockUI();
            Report.exportReportActive($scope.deviceReportInfo).then(function (res){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                $scope.downloadUrl = "api/download?filePath=" + res.url;
                timeout(function (){
                    angular.element("#exportReport").trigger("click");
                });
            }).catch(function (error){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                AlertService.error($translate.instant(error.data.errorKey));
            });
        }

        $scope.exportExcel = function (){
            $scope.blockUI();
            Report.exportReportActive($scope.deviceReportInfo).then(function (res){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                $scope.downloadUrl = "api/download?filePath=" + res.url;
                timeout(function (){
                    angular.element("#exportReport").trigger("click");
                });
            }).catch(function (error){
                if ($scope.blockModal != null){$scope.blockModal.hide();}
                AlertService.error($translate.instant(error.data.errorKey));
            });
        }

    }
})();