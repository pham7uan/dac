(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('authExpiredInterceptor', authExpiredInterceptor);

    authExpiredInterceptor.$inject = ['$rootScope', '$q', '$injector', '$localStorage', '$sessionStorage'];

    function authExpiredInterceptor($rootScope, $q, $injector, $localStorage, $sessionStorage) {
        var service = {
            responseError: responseError
        };

        return service;

        function responseError(response) {
            if (response.status === 401) {
                delete $localStorage.authenticationToken;
                delete $sessionStorage.authenticationToken;
                var Principal = $injector.get('Principal');
                if (Principal.isAuthenticated()) {
                    var Auth = $injector.get('Auth');
                    Auth.authorize(true);
                }
            }
            if(response.data.message == 'Invalid JWT signature.'){
                delete $localStorage.authenticationToken;
                delete $sessionStorage.authenticationToken;
                location.reload();
            }
            return $q.reject(response);
        }
    }
})();
