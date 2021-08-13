(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Device', Device);

    Device.$inject = ['$http'];

    function Device ($http) {
        var service = {
            getAll: getAll,
            create: create,
            getPage: getPage,
            getOne: getOne,
            update: update,
            deleteOne: deleteOne,
            deleteMany: deleteMany,
            getFullInformation: getFullInformation
        };

        return service;

        function getAll() {
            return $http.get('/api/device/search?query=&size=1000').then(function(response) {
                return response.data;
            });
        }

        function create(role) {
            return $http.post('/api/device', role).then(function(response) {
                return response.data;
            });
        }

        function getPage(params) {
            return $http.get('/api/device/search?' + params).then(function (response) {
                return response;
            });
        }

        function getOne(id) {
            return $http.get('/api/device/'+id).then(function(response) {
                return response.data;
            });
        }

        function update(role) {
            return $http.put('/api/device/' + role.id, role).then(function(response) {
                return response.data;
            });
        }

        function deleteOne(id) {
            return $http.delete('/api/device/' + id).then(function(response) {
                return response.data;
            });
        }

        function deleteMany(ids) {
            return $http.post('/api/device/batch-delete', ids).then(function(response) {
                return response.data;
            });
        }

        function getFullInformation(id) {
            return $http.get('/api/device/get-full-information/'+id).then(function(response) {
                return response.data;
            });
        }
    }
})();
