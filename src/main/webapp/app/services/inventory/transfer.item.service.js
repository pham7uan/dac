(function () {
    'use strict';
    angular
        .module('erpApp')
        .factory('TransferItem', TransferItem);

    TransferItem.$inject = ['$http'];

    function TransferItem($http, $scope) {
        var service = {
            create: create,
            getOne: getOne,
            getPage: getPage,
            update: update,
            deleteOne: deleteOne,
            deleteMany: deleteMany,
            activate:activate,
            deactivate:deactivate,
            getItems:getItems,
            getSellProduct:getSellProduct
        };

        return service;
        function getSellProduct(reportInput) {
            return $http.post('/api/transfer-items/sell', reportInput).then(function(response) {
                return response.data;
            });
        }

        function create(unitType) {
            return $http.post('/api/transfer-items', unitType).then(function(response) {
                return response.data;
            });
        }

        function getItems(transferId,type,contentIds,active) {
            var params = "query=transferId==" + transferId +";type==" + type +";contentId=in=" + contentIds;
            if(angular.isDefined(active)){
                params +=";active==" + active;
            }
            params +="&page=0&size=10000&sort=id,asc"
            return $http.get('/api/transfer-items/search?' + params).then(function (response) {
                return response.data;
            });
        }

        function getPage(params) {
            return $http.get('/api/transfer-items/search?' + params).then(function (response) {
                return response;
            });
        }

        function update(unitType) {
            return $http.put('/api/transfer-items/' + unitType.id, unitType).then(function(response) {
                return response.data;
            });
        }

        function deleteOne(id) {
            return $http.delete('/api/transfer-items/' + id).then(function(response) {
                return response.data;
            });
        }

        function deleteMany(ids) {
            return $http.post('/api/transfer-items/batch-delete', ids).then(function(response) {
                return response.data;
            });
        }

        function activate(id) {
            return $http.get('/api/transfer-items/activate?id=' + id).then(function(response) {
                return response.data;
            });
        }

        function deactivate(id) {
            return $http.get('/api/transfer-items/deactivate?id=' +id).then(function(response) {
                return response.data;
            });
        }

        function getOne(id){
            return $http.get('/api/transfer-items/' + id).then(function(response) {
                return response.data;
            });
        }
    }
})();
