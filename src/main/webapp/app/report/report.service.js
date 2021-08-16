(function() {
    'use strict';
    angular
        .module('erpApp')
        .factory('Report', Report);

    Report.$inject = ['$http','$window'];

    function Report ($http) {
        var service = {
            exportReportActive: exportReportActive,
            getReportDevice: getReportDevice
        };

        return service;
        function exportReportActive(result){
            return $http.post('/api/report/export-active', result).then(function (response) {
                return response.data;
            });
        }

        function getReportDevice(searchInfo){
            return $http.post('/api/report/active-device', searchInfo).then(function (response){
               return response.data;
            });
        }
    }
})();