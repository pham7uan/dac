(function () {
    'use strict';

    angular
        .module('erpApp')
        .factory('ErrorHandle', ErrorHandle);

    ErrorHandle.$inject = ['AlertService', '$translate','$state','$window','Auth'];

    function ErrorHandle(AlertService,$translate,$state,$window,Auth) {
        var service = {
            handleError: handleError,
            handleErrors:handleErrors
        };

        return service;

        function handleError(data) {
            var response = data.data;
            if(response != null) {
                if(response.status == 403){
                    AlertService.error("Phiên làm việc đã kết thúc");
                    var mobile = $window.localStorage.getItem("mcMobile");
                    Auth.logout();
                    if(mobile == 0){
                        $state.go('login',{reload:true});
                    } else {
                        $state.go('login-m',{reload:true});
                    }
                } else {
                    var entity = response.entityName;
                    var errorKey = response.errorKey;
                    var title = response.title;
                    if(title == null)
                        title = "Lấy dữ liệu không thành công, có thể do phiên làm việc đã kết thúc";
                    if(entity != null && errorKey != null) {
                        AlertService.error("error." + entity + "." + errorKey);
                    } else {
                        AlertService.error(title);
                    }
                }

            }

        }

        function handleErrors(data) {
            var msgs = []
            for (var i=0; i< data.length; i++){
                var response = data[i]
                if(response != null) {
                    var entity = response.entityName;
                    var errorKey = response.errorKey;
                    var title = response.title;
                    if(title == null){
                        title = "error.common.referenceError";
                        if (msgs.indexOf(title) == -1){
                            msgs.push(title)
                        }
                    }
                    if(entity != null && errorKey != null) {
                        var msg = "error." + entity + "." + errorKey
                        if (msgs.indexOf(msg) == -1){
                            msgs.push(msg)
                        }
                    }
                }
            }
            var msg_show =''
            for (var i=0;i<msgs.length; i++){
                var msg_t = $translate.instant(msgs[i])
                msg_show +=msg_t +'\n'
            }
            AlertService.error(msg_show);
        }
    }
})();
