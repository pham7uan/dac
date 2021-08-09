(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Product', Product);

    Product.$inject = ['$http'];

    function Product ($http) {
        var service = {
            getAll: getAll,
            create: create,
            getPage: getPage,
            getOne: getOne,
            update: update,
            deleteOne: deleteOne,
            deleteMany: deleteMany,
            uploadAvatar:uploadAvatar
        };

        return service;

        function getAll() {
            return $http.get('/api/products/search?query=&size=10000').then(function(response) {
                return response.data;
            });
        }

        function create(role) {
            return $http.post('/api/products', role).then(function(response) {
                return response.data;
            });
        }

        function getPage(params) {
            return $http.get('/api/products/search?' + params).then(function (response) {
                return response;
            });
        }

        function getPageFullInformation(params) {
            return $http.get('/api/products/search-full-information?' + params).then(function (response) {
                return response;
            });
        }

        function getOne(id) {
            return $http.get('/api/products/'+id).then(function(response) {
                return response.data;
            });
        }

        function update(role) {
            return $http.put('/api/products/' + role.id, role).then(function(response) {
                return response.data;
            });
        }

        function deleteOne(id) {
            return $http.delete('/api/products/' + id).then(function(response) {
                return response.data;
            });
        }

        function deleteMany(ids) {
            return $http.post('/api/products/batch-delete', ids).then(function(response) {
                return response.data;
            });
        }

        function uploadAvatar(file, model) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('model', model);
            return $http.post('/api/upload/', fd,{
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function(response) {
                return response;
            });
        }
    }
})();
