define(['angular','_'], function() {
    'use strict';

    var myModule = angular.module('myApp.services', []);
    myModule.value('version', '0.1');


    myModule.factory('todoService', function() {

    });

    myModule.factory('userDao', ['$resource', function($resource) {

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


    myModule.factory('teamDao', ['$resource', function($resource) {

            return {
                  getTeam: function(theTeamName, callback, errorCallback) {
                    var that = this;
                    var Team = $resource('services/v1/team/:teamName', {},{get:{method: "GET"}});
                    Team.get({teamName: theTeamName}).$promise.then(callback, errorCallback);
                  },
                  saveTeam : function(team, callback, errorCallback){
                        var Team = $resource('services/v1/team/:teamName',{}, {save:{method: "PUT"}});
                        Team.save({teamName: team.teamName},_.pick(team, 'teamName', 'description')).$promise.then(callback, errorCallback);
                  },
                  getTeams : function(callback, errorCallback){
                       var Teams = $resource('services/v1/team', {},{get:{method: "GET"}});
                       Teams.get({teamName:""}).$promise.then(callback, errorCallback);
                  }

            };
        }]);

});