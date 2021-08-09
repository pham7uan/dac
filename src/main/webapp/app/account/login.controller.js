(function() {
    'use strict';

    angular
        .module('erpApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope', '$state', '$timeout','$window', 'Auth','$scope','$http','AlertService','User','ErrorHandle'];

    function LoginController ($rootScope, $state, $timeout,$window, Auth, $scope, $http,AlertService,User,ErrorHandle) {
        var vm = this;
        vm.authenticationError = false;
        vm.credentials = {};
        vm.login = login;
        vm.password = null;
        vm.rememberMe = true;
        vm.email = null;
        vm.account = null

        $timeout(function (){angular.element('#email').focus();});

        $scope.logining = false;
        function login (event) {
            $scope.logining = true;
            event.preventDefault();
            Auth.login({
                account:vm.account,
                password: vm.password,
                rememberMe: vm.rememberMe
            }).then(function () {
                vm.authenticationError = false;
                User.current().then(function (user) {
                    $rootScope.currentUser = user;
                    if(user.organizations && user.organizations.length > 0){
                        var organization = $rootScope.currentUser.organizations[0];
                        if(organization){
                            $rootScope.organization = organization;
                            $rootScope.organizationId = organization.id;
                            $window.localStorage.setItem("organizationId", organization.id);
                            $window.localStorage.setItem("organizationName", organization.name);
                        } else {
                            $window.localStorage.setItem("organizationId", null);
                            $window.localStorage.setItem("organizationName", null);
                        }
                    } else {
                        $window.localStorage.setItem("organizationId", null);
                        $window.localStorage.setItem("organizationName", null);
                    }


                    if(user.domain){
                        var url =window.location.href;
                        if(!url.includes(user.domain)){
                            $scope.redirectLink = user.domain;
                            if (Auth.getPreviousState()) {
                                var previousState = Auth.getPreviousState();
                                Auth.resetPreviousState();
                                $scope.redirectLink += "/#/" + previousState.name;
                                if(previousState.params){
                                    $scope.redirectLink +="?";
                                    var index = 0;
                                    for(var param in previousState.params){
                                        $scope.redirectLink +=param +"=" +  previousState.params[param];
                                        if(index < previousState.params.length -1){
                                            $scope.redirectLink +="&";
                                        }
                                    }
                                }
                            }
                            window.location = $scope.redirectLink
                        } else {
                            if (Auth.getPreviousState()) {
                                var previousState = Auth.getPreviousState();
                                Auth.resetPreviousState();
                                $state.go(previousState.name, previousState.params);
                            } else {
                                $state.go('dashboard', {from: 'login'});
                            }
                        }
                    } else {
                        if (Auth.getPreviousState()) {
                            var previousState = Auth.getPreviousState();
                            Auth.resetPreviousState();
                            $state.go(previousState.name, previousState.params);
                        } else {
                            $state.go('dashboard', {from: 'login'});
                        }
                    }
                })
                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is successful, go to stored previousState and clear previousState
            }).catch(function (data) {
                $scope.logining = false;
                vm.authenticationError = true;
                AlertService.error('Thông tin tài khoản hoặc mật khẩu không chính xác');
            });
        }
        $scope.emailForgot = null;
        $scope.sendMail = false;
        $scope.failed = false;
        $scope.submitEmail = function () {
            return $http.post('/api/users/forgot-password/init?email=' + $scope.emailForgot).then(function (response) {
                AlertService.success("Hệ thống đã gửi mail cho bạn");
                $scope.checkEmail = false;
                $scope.sendMail = true;
            }).catch(function (data) {
                AlertService.error("Gửi mail thất bại");
                $scope.checkEmail = true;
                $scope.failed = true;
            })
        }

        $scope.emailForgotChange = function(){
            console.log("change")
            $scope.checkEmail = false;
            $scope.failed = false;
        }

    }
})();
