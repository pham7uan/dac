(function () {
    'use strict';
    angular.module('erpApp')
        .controller('RoleHomeController', RoleHomeController);

    RoleHomeController.$inject = ['$rootScope', '$scope', '$state','$window', 'Role', 'Privilege', 'AlertService', '$translate', 'variables', '$timeout', 'TableController', 'ErrorHandle'];

    function RoleHomeController($rootScope, $scope, $state,$window, Role, Privilege, AlertService, $translate, variables, $timeout, TableController, ErrorHandle) {
        var vm = this;
        $scope.ComboBox = {};
        $scope.organizationId = $window.localStorage.getItem("organizationId");
        // khai bao cac column va kieu du lieu
        var columns = {
            'id':'Number',
            'displayName':'Text',
            'description':'Text',
            'type':'Number'
        }

        $scope.typeFields = [
            {value: 0, title: 'Tùy Chọn'},
            {value: 1, title: 'Mặc Định'}
        ];

        // khai bao cau hinh cho bang
        var tableConfig = {
            tableId: "roles",               //table Id
            model: "roles",                 //model
            defaultSort:"created",          //sap xep mac dinh theo cot nao
            sortType: "desc",                //kieu sap xep
            loadFunction: Role.getPage,     //api load du lieu
            columns: columns,               //bao gom cac cot nao
            handleAfterReload: null,        //xu ly sau khi load du lieu
            handleAfterReloadParams: null,  //tham so cho xu ly sau khi load
            deleteCallback: null,           //delete callback
            customParams: "",               //dieu kien loc ban dau
            pager_id: "table_role_pager",   //phan trang
            page_id: "role_selectize_page", //phan trang
            page_number_id: "role_selectize_pageNum",   //phan trang
            page_size_option: ["5", "10", "25", "50"]   //lua chon size cua 1 page
        }

        TableController.initTable($scope, tableConfig);     //khoi tao table
        TableController.sortDefault(tableConfig.tableId);   //set gia tri sap xep mac dinh
        TableController.reloadPage(tableConfig.tableId);    //load du lieu cho table

        // ham xoa mac dinh
        $scope.defaultDelete = function () {
            TableController.defaultDelete(tableConfig.tableId,Role.deleteMany);
        }

    }

})();