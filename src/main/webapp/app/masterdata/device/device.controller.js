(function(){
    'use strict';
    angular.module('erpApp')
        .controller('DeviceController',DeviceController);

    DeviceController.$inject = ['$rootScope','$scope','$state','$stateParams',
        '$timeout','Device', 'AlertService','ErrorHandle','TableController',
        'ComboBoxController', 'Area', 'Customer', 'Pricing', 'Product'];
    function DeviceController($rootScope,$scope, $state,$stateParams,$timeout,
                              Device, AlertService, ErrorHandle,TableController,
                              ComboBoxController, Area, Customer, Pricing, Product) {
        $scope.ComboBox = {};

        $scope.input ={
            areaIds: [],
            serial: "",
            customerCode: "",
            pricingCode: "",
            productName: "",
            listDs: "",
            state: "",
            activeDate:""
        }
        var columns = {
            'id':'Number',
            'areaId':'MultiNumber',
            "customerCode":'Text',
            "serial": "Text",
            'productName':'Text',
            'pricingCode':'Text',
            'state':'Text',
            'activeDate':'DateTime'
        };

        var customParams = ""; // lay ENDUSER
        var tableConfig = {
            tableId: "devices",               //table Id
            model: "devices",                 //model
            defaultSort:"created",          //sap xep mac dinh theo cot nao
            sortType: "desc",                //kieu sap xep
            loadFunction: Device.getPage,     //api load du lieu
            paramBody:$scope.input,
            columns: columns,               //bao gom cac cot nao
            handleAfterReload: null,        //xu ly sau khi load du lieu
            handleAfterReloadParams: null,  //tham so cho xu ly sau khi load
            deleteCallback: null,           //delete callback
            loading:false,
            customParams: customParams,               //dieu kien loc ban dau
            pager_id: "table_device_pager",   //phan trang
            page_id: "device_selectize_page", //phan trang
            page_number_id: "device_selectize_pageNum",   //phan trang
            page_size_option: ["5", "10", "25", "50"]   //lua chon size cua 1 page
        };

        TableController.initTable($scope, tableConfig);     //khoi tao table
        TableController.sortDefault(tableConfig.tableId);   //set gia tri sap xep mac dinh
        TableController.reloadPage(tableConfig.tableId);    //load du lieu cho table

        $scope.areaes = [];
        $scope.areaConfig = {
            placeholder:"chọn khu vực",
            plugins: {
                'remove_button': {
                    label     : ''
                }
            }, //enable load more
            maxItems: null,
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            create: false
        };
        Area.getPage("query=&page=0&size=1000&sort=id,desc").then(function (response) {
            $scope.areaes = response.data
        })

        $scope.customers = [];
        $scope.customerConfig = {
            placeholder:"chọn mã khách hàng",
            plugins: {
                'remove_button': {
                    label     : ''
                }
            }, //enable load more
            maxItems: 1,
            valueField: 'code',
            labelField: 'code',
            searchField: 'code',
            create: false
        };
        Customer.getPage("query=&page=0&size=1000&sort=id,desc").then(function (response) {
            $scope.customers = response.data
        })

        $scope.pricings = [];
        $scope.pricingConfig = {
            placeholder:"chọn gói cước",
            plugins: {
                'remove_button': {
                    label     : ''
                }
            }, //enable load more
            maxItems: 1,
            valueField: 'code',
            labelField: 'cycle',
            searchField: 'cycle',
            create: false
        };
        Pricing.getPage("query=&page=0&size=1000&sort=id,desc").then(function (response) {
            $scope.pricings = response.data
        })

        var serialCbx = {
            id: 'serial',
            url: '/api/devices',
            originParams: '',
            valueField: 'serial',
            labelField: 'serial',
            searchField: 'serial',
            table: null,
            column: null,
            maxItems: null,
            ngModel: [],
            options: [],
            placeholder: "Chọn serial number",
            orderBy: 'id,asc'
        };
        ComboBoxController.init($scope, serialCbx);

        $scope.products = [];
        $scope.productConfig = {
            placeholder:"chọn Tên sản phẩm",
            plugins: {
                'remove_button': {
                    label     : ''
                }
            }, //enable load more
            maxItems: 1,
            valueField: 'internalReference',
            labelField: 'internalReference',
            searchField: 'internalReference',
            create: false
        };
        Product.getPage("query=&page=0&size=1000&sort=id,desc").then(function (response) {
            $scope.products = response.data
        })

        $scope.lists = [
            {
                id: 1,
                name: "Tất cả"
            },
            {
                id: 2,
                name: "Có ngày kích hoạt"
            }
        ];
        $scope.listConfig = {
            placeholder:"chọn danh sách",
            plugins: {
                'remove_button': {
                    label     : ''
                }
            }, //enable load more
            maxItems: 1,
            valueField: 'id',
            labelField: 'name',
            searchField: 'name',
            create: false
        };

        $scope.states = [
            {
                id: 1,
                state: "Xuất xưởng"
            },
            {
                id: 2,
                state: "Đang hoạt động"
            },
            {
                id: 3,
                state: "Không hoạt động"
            },
        ];
        $scope.stateConfig = {
            placeholder:"chọn Trạng thái thiết bị ",
            plugins: {
                'remove_button': {
                    label     : ''
                }
            }, //enable load more
            maxItems: 1,
            valueField: 'id',
            labelField: 'state',
            searchField: 'state',
            create: false
        };

        $("#activeDatePicker").kendoDatePicker({
            format: "dd/MM/yyyy",
            change: function() {
                var value = this.value();
                if(value !=null){
                    $scope.input.activeDate = value.getTime()
                } else {
                    $scope.input.activeDate = null;
                }
            }
        });

        // khai bao cau hinh cho bang
        $scope.search = function(){
            console.log('a',tableConfig.tableId)
            TableController.reloadPage(tableConfig.tableId);    //load du lieu cho table
        }

        $scope.mappingColumn = function(table,column, model){
            $scope.TABLES[table].filter[column] = model;
        }
    }
})();
