(function() {
    'use strict';
    angular
        .module('erpApp')
        .factory('Report', Report);

    Report.$inject = ['$http','$window'];

    function Report ($http,$window) {
        var service = {
            exportReportActive: exportReportActive
        };

        return service;
        function exportReportActive(result){
            return $http.post('/api/report-active/export', result).then(function (response) {
                return response.data;
            });
        }
    }
})();
