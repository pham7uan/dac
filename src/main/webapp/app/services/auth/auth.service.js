(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('Auth', Auth);

    Auth.$inject = ['$rootScope', '$state', '$window','$sessionStorage', '$q','$http', 'Principal', 'AuthServerProvider', 'User'];

    function Auth ($rootScope, $state,$window, $sessionStorage, $q,$http, Principal, AuthServerProvider, User) {
        var service = {
            authorize: authorize,
            getPreviousState: getPreviousState,
            login: login,
            logout: logout,
            loginWithToken: loginWithToken,
            storePreviousState: storePreviousState,
            resetPassword:resetPassword,
            activeNewUser:activeNewUser,
            getUser: getUser,
            resetPreviousState:resetPreviousState
        };

        return service;

        function getUser() {
            return $http.get('/api/users/current').then(function(response) {
                return response.data;
            });
        }

        function authorize (force) {
            var authReturn = Principal.identity(force).then(authThen);

            return authReturn;

            function authThen () {
                var isAuthenticated = Principal.isAuthenticated();
                console.log(isAuthenticated);
                // an authenticated user can't access to login and register pages
                if (isAuthenticated && ($rootScope.toState.name === 'login')) {
                    $state.go('dashboard');
                }
                if(isAuthenticated && $rootScope.toState.name === 'register') {
                    $state.go('dashboard');
                }
                // recover and clear previousState after external login redirect (e.g. oauth2)
                if (isAuthenticated && !$rootScope.fromState.name && getPreviousState()) {
                    var previousState = getPreviousState();
                    resetPreviousState();
                    $state.go(previousState.name, previousState.params);
                }

                var authorities = $rootScope.toState.data.authorities;

                if (authorities != undefined && authorities.length > 0 && !Principal.hasAnyAuthority(authorities)) {
                    if (isAuthenticated) {
                        // user is signed in but not authorized for desired state
                        $state.go('error.403');
                    }
                    else {
                        // user is not authenticated. stow the state they wanted before you
                        // send them to the login service, so you can return them when you're done
                        storePreviousState($rootScope.toState.name, $rootScope.toStateParams);
                        // now, send them to the signin state so they can log in
                        var mobile = $window.localStorage.getItem("mcMobile");
                        if(mobile == 0){
                            $state.go('login',{reload:true});
                        } else {
                            $state.go('login-m',{reload:true});
                        }
                    }
                }
            }
        }


        function login (credentials, callback) {
            var cb = callback || angular.noop;
            var deferred = $q.defer();

            AuthServerProvider.login(credentials)
                .then(loginThen)
                .catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

            function loginThen (data) {
                Principal.identity(true).then(function(account) {

                    deferred.resolve(data);
                });
                return cb();
            }

            return deferred.promise;
        }

        function loginWithToken(jwt, rememberMe) {
            return AuthServerProvider.loginWithToken(jwt, rememberMe);
        }

        function logout () {
            AuthServerProvider.logout();
            Principal.authenticate(null);
        }


        function getPreviousState() {
            var previousState = $sessionStorage.previousState;
            return previousState;
        }

        function resetPreviousState() {
            delete $sessionStorage.previousState;
        }

        function storePreviousState(previousStateName, previousStateParams) {
            var previousState = { "name": previousStateName, "params": previousStateParams };
            $sessionStorage.previousState = previousState;
        }

        function resetPassword(resetPasswordVM) {
            return $http.post('/api/users/forgot-password/finish',resetPasswordVM).then(function(response) {
                return response.data;
            });
        }

        function activeNewUser(resetPasswordVM) {
            return $http.post('/api/users/active-new-user',resetPasswordVM).then(function(response) {
                return response.data;
            });
        }
    }
})();
