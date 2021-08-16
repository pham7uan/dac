(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Device', Device);

    Device.$inject = ['$http','$window'];

    function Device ($http,$window) {
        var service = {
            getPage: getPage,
            getCustomer:getCustomer
        };

        return service;

        function getCustomer(params,input) {
            return $http.post('/api/devices/customers?' + params,input).then(function (response) {
                return response;
            });
        }

        function getPage(params) {
            return $http.get('/api/devices/search?' + params).then(function (response) {
                return response;
            });
        }
    }
})();
