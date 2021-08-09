(function() {
    'use strict';

    angular
        .module('erpApp')
        .factory('User', User);

    User.$inject = ['$http','$window'];

    function User ($http,$window) {
        var service = {
            current : current,
            getAll: getAll,
            create: create,
            getPage: getPage,
            getOwner:getOwner,
            getUserById: getUserById,
            update: update,
            deleteOne: deleteOne,
            deleteMany: deleteMany,
            activate:activate,
            deactivate:deactivate,
            uploadAvatar:uploadAvatar,
            deleteFileUpload: deleteFileUpload,
            deleteAvatar:deleteAvatar,
            changePassword:changePassword,
            getOne: getOne,
            checkTraceAdmin:checkTraceAdmin,
            register: register,
            getEmployees:getEmployees,
            getAllEmployees:getAllEmployees,
            setView:setView,
            extend:extend,
            stop:stop,
            seen:seen,
            salaryCalculator:salaryCalculator,
            salaryReport:salaryReport,
            sendMail:sendMail
        };

        return service;
        function sendMail(input) {
            return $http.post('/api/users/send-mail',input).then(function(response) {
                return response.data;
            });
        }

        function salaryCalculator(input) {
            return $http.post('/api/salary/calculator',input).then(function(response) {
                return response.data;
            });
        }
        function salaryReport(input) {
            return $http.post('/api/salary/report',input).then(function(response) {
                return response.data;
            });
        }
        function extend(id,quantity,type) {
            return $http.get('/api/users/extend?id=' + id +"&quantity="+quantity +"&type="+type).then(function(response) {
                return response.data;
            });
        }

        function stop(id) {
            return $http.get('/api/users/stop?id=' + id).then(function(response) {
                return response.data;
            });
        }

        function seen(id,v) {
            return $http.get('/api/users/seen?id=' + id +"&v="+v).then(function(response) {
                return response.data;
            });
        }

        function setView(id,view) {
            return $http.get('/api/users/set-view?id=' + id +"&view="+view).then(function(response) {
                return response.data;
            });
        }

        function current() {
            return $http.get('/api/users/current').then(function(response) {
                return response.data;
            });
        }

        function getAll() {
            return $http.get('/api/users').then(function(response) {
                return response.data;
            });
        }

        function register(user) {
            return $http.post('/api/users/register',user).then(function(response) {
                return response.data;
            });
        }

        function create(user) {
            return $http.post('/api/users',user).then(function(response) {
                return response.data;
            });
        }

        function getEmployees(params) {
            var organizationId = $window.localStorage.getItem("organizationId");
            return $http.get('/api/users/search-employees?orgId=' + organizationId +"&" + params).then(function (response) {
                return response;
            });
        }

        function getAllEmployees() {
            var organizationId = $window.localStorage.getItem("organizationId");
            return $http.get('/api/users/search-employees?orgId=' + organizationId +"&query=ctv==0&page=0&size=10000").then(function (response) {
                return response;
            });
        }

        function getPage(params) {
            return $http.get('/api/users/search?' + params).then(function (response) {
                return response;
            });
        }

        function getOwner(params) {
            return $http.get('/api/users/search-owners?' + params).then(function (response) {
                return response;
            });
        }

        function getUserById(id) {
            return $http.get('/api/users/' + id).then(function(response) {
                return response.data;
            });
        }

        function update(user) {
            return $http.put('/api/users/' + user.id, user).then(function(response) {
                return response.data;
            });
        }

        function deleteOne(id) {
            return $http.delete('/api/users/' + id).then(function(response) {
                return response.data;
            });
        }

        function deleteMany(ids) {
            return $http.post('/api/users/batch-delete', ids).then(function(response) {
                return response.data;
            });
        }

        function activate(id) {
            return $http.get('/api/users/activate?id=' + id).then(function(response) {
                return response.data;
            });
        }

        function deactivate(id) {
            return $http.get('/api/users/deactivate?id=' + id).then(function(response) {
                return response.data;
            });
        }

        function uploadAvatar(file, model) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('model', model);
            return $http.post('/api/upload/', fd,{
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function(response) {
                return response;
            });
        }

        function deleteAvatar(userId) {
            return $http.post('/api/users/'+ userId +'/deleteAvatar').then(function(response) {
                return response.data;
            });
        }

        function deleteFileUpload(fileName) {
            return $http.delete('/api/deleteFileUploaded?fileName=' + fileName).then(function(response) {
                return response.data;
            });
        }

        function changePassword(user) {
            return $http.post('/api/users/change-password',user).then(function(response) {
                return response.data;
            });
        }

        function getOne(id){
            return $http.get('/api/users/' + id).then(function(response) {
                return response.data;
            });
        }

        function checkTraceAdmin(organizationId){
            return $http.get('/api/users/check-trace-admin?organizationId=' + organizationId).then(function(response) {
                return response.data;
            });
        }
    }
})();
