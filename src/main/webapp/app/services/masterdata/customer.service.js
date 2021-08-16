(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Customer', Customer);

    Customer.$inject = ['$http','$window'];

    function Customer ($http,$window) {
        var service = {
            getPage: getPage,
        };

        return service;

        function getPage(params) {
            return $http.get('/api/customer/search?' + params).then(function (response) {
                return response;
            });
        }
    }
})();
