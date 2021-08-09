(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Node', Node);

    Node.$inject = ['$http'];

    function Node ($http) {
        var service = {
            create: create,
            getPage: getPage,
            getOne: getOne,
            update: update,
            deleteOne: deleteOne,
            deleteMany: deleteMany
        };

        return service;

        function create(privilege) {
            return $http.post('/api/nodes', privilege).then(function(response) {
                return response.data;
            });
        }

        function getPage(params) {
            return $http.get('/api/nodes/search?' + params).then(function (response) {
                return response;
            });
        }

        function getOne(id) {
            return $http.get('/api/nodes/'+id).then(function(response) {
                return response.data;
            });
        }

        function update(privilege) {
            return $http.put('/api/nodes/' + privilege.id, privilege).then(function(response) {
                return response.data;
            });
        }

        function deleteOne(id) {
            return $http.delete('/api/nodes/' + id).then(function(response) {
                return response.data;
            });
        }

        function deleteMany(ids) {
            return $http.post('/api/nodes/batch-delete', ids).then(function(response) {
                return response.data;
            });
        }
    }
})();
