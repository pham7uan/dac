(function(){
    'use strict';
    angular.module('erpApp')
        .controller('DeviceController',DeviceController);

    DeviceController.$inject = ['$rootScope','$scope','$state','$http','$window',
        '$timeout', 'AlertService',
        '$translate','ErrorHandle',
        'TableController','ComboBoxController', "$http",
        'Device'];
    function DeviceController($rootScope,$scope, $state,$http,$window,
                               $timeout, AlertService,
                               $translate, ErrorHandle,
                               TableController,ComboBoxController,
                                Device) {
        $scope.ComboBox = {};

        $scope.input ={
            areaIds: [],
            serialId: "",
            customerCode: "",
            serialNumber: "",
            productName: "",
            listDs: "",
            state: "",
            activeDate:""
        }

        var serialCbx = {
            id: 'serial',
            url: '/api/device',
            originParams: 'id!=0',
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            table: null,
            column: null,
            maxItems: null,
            ngModel: [],
            options: [],
            placeholder: null,
            orderBy: 'id,asc'
        };
        ComboBoxController.init($scope, serialCbx);

        var columns = {
            'id':'Number',
            'areaName':'Text',
            "customerCode":'Text',
            "serial": "Text",
            'productName':'Text',
            'pricingCode':'Text',
            'state':'Text',
            'activeDate':'DateTime'
        };

        // khai bao cau hinh cho bang
        var customParams = "type==0";
        var tableConfig = {
            tableId: "devices",               //table Id
            model: "devices",                 //model
            defaultSort:"created",          //sap xep mac dinh theo cot nao
            sortType: "desc",                //kieu sap xep
            loadFunction: Device.getPage,     //api load du lieu
            columns: columns,               //bao gom cac cot nao
            handleAfterReload: null,        //xu ly sau khi load du lieu
            handleAfterReloadParams: null,  //tham so cho xu ly sau khi load
            deleteCallback: null,           //delete callback
            customParams: "",               //dieu kien loc ban dau
            pager_id: "table_device_pager",   //phan trang
            page_id: "device_selectize_page", //phan trang
            page_number_id: "device_selectize_pageNum",   //phan trang
            page_size_option: ["5", "10", "25", "50"]   //lua chon size cua 1 page
        }

        TableController.initTable($scope, tableConfig);     //khoi tao table
        TableController.sortDefault(tableConfig.tableId);   //set gia tri sap xep mac dinh
        TableController.reloadPage(tableConfig.tableId);    //load du lieu cho table

        Device.getPage().then(function (response) {
            console.log(response)
        })
    }
})();