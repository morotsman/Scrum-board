define(['angular'], function() {
    'use strict';

    var loginControllers =  angular.module('myApp.loginControllers', ['myApp.services']);
            // Sample controller where service is being used

    loginControllers.controller('LoginController', ['$scope','$rootScope','$location','userDao', function($scope,$rootScope,$location,userDao) {
            
            $scope.login = function(){

                var success = function(user){
                   $rootScope.loggedUser = $scope.loginData.user;
                   $location.url('/TodoView');
                };

                var failure = function(data){
                    if(data.status == 404){
                        $scope.loginData.loginError = $scope.loginData.user + " is an unknown user.";
                    } else{
                        $scope.loginData.loginError = "Received error code " + data.status + " from the server."
                    }
                };

                userDao.getUser($scope.loginData.user, success, failure);
            };

            $scope.createUser = function(){
                 $location.path( "/createUser" );
            }
        }]);

        loginControllers.controller('CreateUserController', ['$scope','userDao','$location', function($scope, userDao,$location) {

             $scope.createUser = function(){

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