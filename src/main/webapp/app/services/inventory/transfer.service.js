(function () {
    'use strict';
    angular
        .module('erpApp')
        .factory('Transfer', Transfer);

    Transfer.$inject = ['$http'];

    function Transfer($http) {
        var service = {
            create: create,
            getOne: getOne,
            getPage: getPage,
            update: update,
            deleteOne: deleteOne,
            activate:activate,
            deactivate:deactivate
        };

        return service;

        function create(unitType) {
            return $http.post('/api/transfers', unitType).then(function(response) {
                return response.data;
            });
        }

        function getPage(params) {
            return $http.get('/api/transfers/search?' + params).then(function (response) {
                return response;
            });
        }

        function update(unitType) {
            return $http.put('/api/transfers/' + unitType.id, unitType).then(function(response) {
                return response.data;
            });
        }

        function deleteOne(id) {
            return $http.delete('/api/transfers/' + id).then(function(response) {
                return response.data;
            });
        }

        function activate(id) {
            return $http.get('/api/transfers/activate?id=' + id).then(function(response) {
                return response.data;
            });
        }

        function deactivate(id) {
            return $http.get('/api/transfers/deactivate?id=' +id).then(function(response) {
                return response.data;
            });
        }

        function getOne(id){
            return $http.get('/api/transfers/' + id).then(function(response) {
                return response.data;
            });
        }
    }
})();
