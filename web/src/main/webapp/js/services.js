define(['angular'], function() {
    'use strict';

    var myModule = angular.module('myApp.services', []);
    myModule.value('version', '0.1');


    myModule.factory('todoService', function() {

    });

    myModule.factory('userDao', ['$resource', '$http','$rootScope', function($resource, $http, $rootScope) {

            return {
                  getUser: function(theUserName, callback, errorCallback) {
                    var that = this;
                    var User = $resource('services/v1/user/:userName', {},{get:{method: "GET"}});
                    User.get({userName: theUserName}).$promise.then(callback, errorCallback);
                  },
                  createUser : function(newUserName, callback, errorCallback){
                        var User = $resource('services/v1/user/'+newUserName,{}, {save:{method: "PUT"}});
                        User.save({userName: newUserName}).$promise.then(callback, errorCallback);
                  }

            };
        }]);

});