(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Device', Device);

    Device.$inject = ['$http','$window'];

    function Device ($http,$window) {
        var service = {
            getPage: getPage,
        };

        return service;

        function getPage(params) {
            return $http.get('/api/users/search?' + params).then(function (response) {
                return response;
            });
        }
    }
})();
