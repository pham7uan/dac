angular
    .module('erpApp')
    .controller('errorCtrl', [
        '$timeout',
        '$scope',
        '$window',
        '$state',
        'Auth',
        '$rootScope',
        function ($timeout,$scope,$window,$state,Auth,$rootScope) {
            $scope.logout = function() {
                var mobile = $window.localStorage.getItem("mcMobile");
                Auth.logout();
                if(mobile == 0){
                    $state.go('login',{reload:true});
                } else {
                    $state.go('login-m',{reload:true});
                }
            }
        }
    ])
;