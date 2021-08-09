(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Pricing', Pricing);

    Pricing.$inject = ['$http'];

    function Pricing ($http) {
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
            return $http.get('/api/pricing/search?query=&size=1000').then(function(response) {
                return response.data;
            });
        }

        function create(role) {
            return $http.post('/api/pricing', role).then(function(response) {
                return response.data;
            });
        }

        function getPage(params) {
            return $http.get('/api/pricing/search?' + params).then(function (response) {
                return response;
            });
        }

        function getOne(id) {
            return $http.get('/api/pricing/'+id).then(function(response) {
                return response.data;
            });
        }

        function update(role) {
            return $http.put('/api/pricing/' + role.id, role).then(function(response) {
                return response.data;
            });
        }

        function deleteOne(id) {
            return $http.delete('/api/pricing/' + id).then(function(response) {
                return response.data;
            });
        }

        function deleteMany(ids) {
            return $http.post('/api/pricing/batch-delete', ids).then(function(response) {
                return response.data;
            });
        }

        function getFullInformation(id) {
            return $http.get('/api/pricing/get-full-information/'+id).then(function(response) {
                return response.data;
            });
        }
    }
})();
