(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Device', Device);

    Device.$inject = ['$http','$window'];

    function Device ($http,$window) {
        var service = {
            getPage: getPage,
            exportDevice: exportDevice
        };

        return service;



        function getPage(params) {
            return $http.get('/api/devices/search?' + params).then(function (response) {
                return response;
            });
        }

        function exportDevice(param) {
            return $http.get('/api/devices/export?param=' + param).then(function (response) {
                return response.data.fileName;
            });
        }
    }
})();
