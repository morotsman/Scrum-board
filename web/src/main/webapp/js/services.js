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
                  saveUser : function(user, callback, errorCallback){
                        var User = $resource('services/v1/user/' + user.userName,{}, {save:{method: "PUT"}});
                        User.save({userName: user.userName},_.pick(user, 'userName', 'password','firstName', 'lastName', 'phoneNumber', 'email')).$promise.then(callback, errorCallback);
                  },
                  getUsers : function(callback, errorCallback){
                    var Users = $resource('services/v1/user', {},{get:{method: "GET"}});
                    Users.get({}).$promise.then(callback, errorCallback);
                  },
                  deleteUser: function(user,callback, errorCallback) {
                    var User = $resource('services/v1/user/:userName');
                    User.delete({userName: user.userName}, {}).$promise.then(callback,errorCallback);
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
                  },
                  deleteTeam: function(team,callback, errorCallback) {
                    var Team = $resource('services/v1/team/:teamName');
                    Team.delete({teamName: team.teamName}, {}).$promise.then(callback,errorCallback);
                  }

            };
        }]);

    myModule.factory('membershipDao', ['$resource', function($resource) {

            var getMembershipLink = function(objectWithLinks, linkToFind){
                return _.chain(objectWithLinks.links)
                                .filter(function(link){
                                    return link.rel === linkToFind;
                                 })
                                .map(function(link){
                                    return link.href;
                                })
                                .value()[0];
            }


            return {
                  createMembership : function(team, theUserName, callback, errorCallback){
                        var membershipLink = getMembershipLink(team, "membership");
                        var Membership = $resource(membershipLink + '/' + theUserName,{}, {save:{method: "PUT"}});
                        Membership.save({userName: theUserName}).$promise.then(callback, errorCallback);
                  },
                  getMemberships : function(team, callback, errorCallback){
                     var membershipLink = getMembershipLink(team, "membership");
                     var Memberships = $resource(membershipLink, {},{get:{method: "GET"}});
                     Memberships.get({teamName: ""}).$promise.then(callback, errorCallback);
                  },
                  deleteMembership : function(team, theUserName, callback, errorCallback){
                    var membershipLink = getMembershipLink(team, "membership");
                    var Membership = $resource(membershipLink + '/' + theUserName);
                    Membership.delete({userName: theUserName}, {}).$promise.then(callback,errorCallback);
                  }

            };
        }]);


});