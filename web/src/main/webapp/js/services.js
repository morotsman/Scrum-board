define(['angular','_'], function() {
    'use strict';

    var myModule = angular.module('myApp.services', []);


    myModule.factory('todoService', function() {
         var teamToOverview;
         var teamToShowBoard;


         return {
                     getTeamToOverview: function() {
                         return teamToOverview;
                     },
                     setTeamToOverview: function(team) {
                         teamToOverview = team;
                     },getTeamToShowBoard: function() {
                        return teamToShowBoard;
                     },
                     setTeamToShowBoard: function(team) {
                        teamToShowBoard = team;
                     }
                 };
    });

    myModule.factory('linkService', function() {

         return {
            findLink : function(objectWithLinks, linkToFind){
                return _.chain(objectWithLinks.links)
                        .filter(function(link){
                            return link.rel === linkToFind;
                        })
                        .map(function(link){
                            return link.href;
                        })
                        .value()[0];
            }
            ,findLinks : function(objectWithLinks, linkToFind){
                return _.chain(objectWithLinks.links)
                        .filter(function(link){
                            return link.rel === linkToFind;
                        })
                        .map(function(link){
                            return link.href;
                        })
                        .value()[0];
            }
         };
    });


});