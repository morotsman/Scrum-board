define(['angular'], function() {
    'use strict';

    var loginControllers =  angular.module('myApp.loginControllers', ['myApp.services']);
            // Sample controller where service is being used

    loginControllers.controller('LoginController', ['$scope','$rootScope','$location','userDao', function($scope,$rootScope,$location,userDao) {


        var loginSuccess = function(user){
            $rootScope.loggedUser = $scope.loginData.user;
            $location.url('/MainView');
        };

        var loginFailure = function(data){
            if(data.status == 404){
                $scope.loginData.loginError = $scope.loginData.user + " is an unknown user.";
            } else{
                $scope.loginData.loginError = "Received error code " + data.status + " from the server."
            }
        };

        $scope.login = function(){
            userDao.getUser($scope.loginData.user, loginSuccess, loginFailure);
        };

        $scope.createUser = function(){
            $location.path( "/createUser" );
        }

    }]);

    loginControllers.controller('CreateUserController', ['$scope','userDao','$location', function($scope, userDao,$location) {

        var success = function(user){
            $location.url('/userCreated');
        }

        var failure = function(error){
            if(error.status == 409){
                $scope.createUserData.error = $scope.createUserData.user + " already exists.";
            }else{
                $scope.createUserData.error = "Received error code " + data.status + " from the server."
            }
        }

        $scope.createUser = function(){
            userDao.createUser($scope.createUserData.user, success, failure);
        };

        $scope.goToLogin = function(){
            $location.url( "/login" );
        }
    }]);

    loginControllers.controller('UserCreatedController', ['$scope','$location', function($scope, $location) {

        $scope.goToLogin = function(){
            $location.url( "/login" );
        }


    }]);




    return loginControllers;

});