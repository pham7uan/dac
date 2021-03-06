(function () {
    'use strict';
    angular
        .module('erpApp')
        .factory('ComboBoxController', ComboBoxController);

    ComboBoxController.$inject = ['$rootScope', 'Principal','$translate','apiData','$http'];

    function ComboBoxController($rootScope, Principal, $translate,apiData,$http, $scope) {
        var service = {
            init: init,
            isEmpty: isEmpty,
            clone:clone,
            checkIsValidForm: checkIsValidForm,
            removeUnicode: removeUnicode,
            setLong: setLong,
            getProductByLocationIds: getProductByLocationIds,
            getParamSetLong: getParamSetLong,
            isEmptyObject: isEmptyObject,
            isValidDecimal: isValidDecimal,
            getDefaultOrganization: getDefaultOrganization,
            getTransferCode: getTransferCode
        };

        return service;

        function isEmpty(x){
            if(!angular.isDefined(x) || x == null || x === ''){
                return true;
            }
            return false;
        }

        function isEmptyObject(obj){
            if(obj === "" || (Object.entries(obj).length === 0 && obj.constructor === Object)) return true;
            return false;
        }

        function isObject(obj) {
            var type = typeof obj;
            return type === 'function' || type === 'object' && !!obj;
        }

        function clone(src) {
            return JSON.parse(JSON.stringify(src));
        }

        function setLong(key, val) {
            var c1 = key + '=="*[' + val +']*",'; // [x]
            var c2 = key + '=="*[' + val +',*",'; // [x,1,2]
            var c3 = key + '=="*,' + val +',*",'; // [1,x,2]
            var c4 = key + '=="*,' + val +']*"'; //  [1,2,x]
            var str = '(' + c1 + c2 + c3 + c4 +')';

            return str;
        }

        function getProductByLocationIds(key, val){
            var c1 = key + '=="*[' + val +']*",'; // [x]
            var c2 = key + '=="*[' + val +',*",'; // [x,1,2]
            var c3 = key + '=="*,' + val +',*",'; // [1,x,2]
            var c4 = key + '=="*,' + val +']*",'; //  [1,2,x]
            var c5 = key + '==null'; //  null

            var str = '(' + c1 + c2 + c3 + c4 + c5 + ')';

            return str;
        }

        function getParamSetLong(key, val){
            var c1 = key + '=="*[' + val +']*",'; // [x]
            var c2 = key + '=="*[' + val +',*",'; // [x,1,2]
            var c3 = key + '=="*,' + val +',*",'; // [1,x,2]
            var c4 = key + '=="*,' + val +']*"'; //  [1,2,x]
            var str = '(' + c1 + c2 + c3 + c4 + ')';

            return str;
        }

        function init(scope, config) {
            $scope = scope;

            $scope.ComboBox[config.id] = {};
            if(!angular.isDefined(config.resetScroll)){
                $scope.ComboBox[config.id].resetScroll = false;
            } else {
                $scope.ComboBox[config.id].resetScroll = config.resetScroll;
            }
            if(!angular.isDefined(config.page)){
                $scope.ComboBox[config.id].page = 0;
            } else {
                $scope.ComboBox[config.id].page = config.page;
            }
            if(!angular.isDefined(config.totalCount)){
                $scope.ComboBox[config.id].totalCount = null;
            } else {
                $scope.ComboBox[config.id].totalCount = config.totalCount;
            }
            if(!angular.isDefined(config.specialUrl)){
                config.specialUrl = null;
            }

            if(!angular.isDefined(config.perPage)){
                $scope.ComboBox[config.id].perPage = 10;
            } else {
                $scope.ComboBox[config.id].perPage = config.perPage;
            }
            // console.log(config.url);
            // if(!angular.isDefined(config.specialUrl)){
            //     config.url += "/search?query=";
            // } else {
            //     config.url = config.specialUrl;
            // }
            // console.log(config.url);

            $scope.ComboBox[config.id].ngModel = config.ngModel;
            $scope.ComboBox[config.id].options = config.options;
            $scope.ComboBox[config.id].table = config.table;
            $scope.ComboBox[config.id].column = config.column;
            $scope.ComboBox[config.id].scope = $scope;
            $scope.ComboBox[config.id].config = {
                placeholder:config.placeholder,
                plugins: {
                    'infinite_scroll': {},
                    'remove_button': {
                        label     : ''
                    }
                }, //enable load more
                maxItems: config.maxItems,
                valueField: config.valueField,
                labelField: config.labelField,
                searchField:config.searchField,
                create: false,
                render: {
                    option: function (data, escape) {
                        if(!data[config.labelField]) return '<div class="hide"></div>';
                        return '<div class="option">' + '<span class="title" title="'+escape(data[config.labelField]) + '">' + escape(data[config.labelField]) + '</span>' + '</div>';
                    },
                    item: function (data, escape) {
                        return '<div class="item" title="'+escape(data[config.labelField]) +'">' + escape(data[config.labelField]) + '</div>';
                    }
                },
                //use load event if use load more
                load: function (query, callback) {
                    query = JSON.parse(query)
                    if($scope.ComboBox[config.id].resetScroll){
                        query.page = 0;
                        callback($scope.ComboBox[config.id].resetScroll);
                        $scope.ComboBox[config.id].resetScroll = false;
                    }
                    $scope.ComboBox[config.id].page = query.page || 0;
                    if (!$scope.ComboBox[config.id].totalCount || $scope.ComboBox[config.id].totalCount > ( ($scope.ComboBox[config.id].page - 1) * $scope.ComboBox[config.id].perPage)) {
                        if(!config.url || config.url === "") return;
                        var api = apiData.genApi(config.url, config.searchField, query.search, $scope.ComboBox[config.id].perPage, null, config.originParams, query.page, config.specialUrl, config.orderBy);
                        $http.get(api).then(function (response) {
                            $scope.ComboBox[config.id].totalCount = parseInt(response.headers()["x-total-count"], 10);
                            callback(response.data);
                        });
                    } else {
                        callback();
                    }
                }
            };

        }

        function checkIsValidForm($form){
            // check ri??ng t???ng input
            var checkValid = true;
            $form.find("input.md-input").each(function () {
                if(!$(this).parsley().isValid()){
                    checkValid = false;
                    return false;
                }
            });
            return checkValid;
        }

        function removeUnicode(str) {
            str = str.replace(/??|??|???|???|??|??|???|???|???|???|???|??|???|???|???|???|???/g, "a");
            str = str.replace(/??|??|???|???|???|??|???|???|???|???|???/g, "e");
            str = str.replace(/??|??|???|???|??/g, "i");
            str = str.replace(/??|??|???|???|??|??|???|???|???|???|???|??|???|???|???|???|???/g, "o");
            str = str.replace(/??|??|???|???|??|??|???|???|???|???|???/g, "u");
            str = str.replace(/???|??|???|???|???/g, "y");
            str = str.replace(/??/g, "d");
            str = str.replace(/??|??|???|???|??|??|???|???|???|???|???|??|???|???|???|???|???/g, "A");
            str = str.replace(/??|??|???|???|???|??|???|???|???|???|???/g, "E");
            str = str.replace(/??|??|???|???|??/g, "I");
            str = str.replace(/??|??|???|???|??|??|???|???|???|???|???|??|???|???|???|???|???/g, "O");
            str = str.replace(/??|??|???|???|??|??|???|???|???|???|???/g, "U");
            str = str.replace(/???|??|???|???|???/g, "Y");
            str = str.replace(/??/g, "D");
            return str;
        }

        function isValidDecimal(val) {
            var tmp = val.split(".");
            if(tmp.length <= 1) return true;

            // check xem c?? bao nhi??u s??? th???p ph??n ?????ng sau
            return tmp[1].toString().length <= 3;
        }

        function getDefaultOrganization (){
            var defaultVal = {id: 1, name: 'T???t c???'};
            var isAdmin = false;

            // check is admin => return t???t c???
            Principal.hasAuthority("ROLE_SYSTEM_ADMIN").then(function (data) {
                isAdmin = data;
            });
            if(isAdmin) return defaultVal;

            var currentUser = $rootScope.currentUser;
            var organizations = currentUser.organizations;
            if(!organizations || organizations.length < 1) return defaultVal;

            // 1 dn th?? tr??? v??? dn ????
            if(organizations.length < 2) return organizations[0];

            /*
            * x??t xem c?? bao nhi??u DN ?????c l???p
            * n???u ch??? c?? 1 cha => hi???n th??? cha ????, ng?????c l???i hi???n th??? t???t c???*/
            var countParentId = 0;
            var parent = defaultVal;
            var listParentIds = [];
            for(var i = 0; i < organizations.length; i++){
                if(!organizations[i].parentId){
                    countParentId += 1;
                    parent = organizations[i];
                    // l???y list cha ri??ng
                    listParentIds.push(organizations[i].id);
                } else{
                    // c?? thu???c cha ???? kh??ng => kh??ng th?? ??? nhi???u doanh nghi???p
                    // l???y cha cao nh???t
                    var relateName = organizations[i].relateName;
                    var relateNameSplit = relateName.split('/');
                    if(!listParentIds.includes(parseInt(relateNameSplit[0]))){
                        countParentId += 1;
                    }
                }

                // c?? nhi???u cha => l???y t???t c???
                if(countParentId === 2){
                    parent = defaultVal;
                    break;
                }
            }

            return parent;
        }

        function getTransferCode(transfer) {
            var code = transfer.code ? transfer.code : transfer.transferCode;
            // if(!code) return ""
            // if(transfer.type == 1 && code){
            //     code = "C??-" + code;
            // } else if(transfer.type == 2 && code){
            //     if(transfer.internal){
            //         code = "NVQ-" + code;
            //     } else {
            //         code = "VL-" + code;
            //     }
            //
            // } else if(transfer.type == 3 && code){
            //     code = "TG-" + code;
            // }  else if(transfer.type == 313 && code){
            //     code = "TGT-" + code;
            // } else if((transfer.type >= 18 && transfer.type <=25) || (transfer.type == 33 || transfer.type == 34)){
            //     code = "Chi-" + code;
            // } else if((transfer.type >= 26 && transfer.type <=32) || transfer.type == 35){
            //     code = "Thu-" + code;
            // } else if(transfer.type > 1000){
            //     code = "TC-" + code;
            // }

            return code;
        }
    }
})();