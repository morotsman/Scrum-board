define(['angular'], function() {
    'use strict';

    var myModule = angular.module('myApp.services', []);
    myModule.value('version', '0.1');


    myModule.factory('todoService', function() {

    });

    myModule.factory('userDao', ['$resource', '$http','$rootScope', function($resource, $http, $rootScope) {

            return {
                  getUser: function(callback, errorCallback) {
                    var that = this;
                    var theUser = $resource('services/v1/user/:userName', {},{get:{method: "GET",isArray: false,cache: false}});
                    theUser.get({userName: $rootScope.loggedUser}).$promise.then(callback, errorCallback);
                  },
                  toUser: function(jsonData) {
                    return jsonData;
                  },
                  createUser : function(newUserName, callback, errorCallback){
                        var User = $resource('services/v1/user/'+newUserName,{}, {save:{method: "PUT", isArray: false, cache: false}});
                        User.save({userName: newUserName}).$promise.then(callback, errorCallback);
                  }

            };
        }]);

});