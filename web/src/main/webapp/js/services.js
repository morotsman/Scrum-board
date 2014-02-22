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


});