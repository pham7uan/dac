(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Area', Area);

    Area.$inject = ['$http'];

    function Area ($http) {
        var service = {
            getPage: getPage,
        };

        return service;

        function getPage(params) {
            return $http.get('/api/area/search?' + params).then(function (response) {
                return response;
            });
        }
    }
})();
