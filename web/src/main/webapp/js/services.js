define(['angular','_'], function() {
    'use strict';

    var myModule = angular.module('myApp.services', []);


    myModule.factory('todoService', function() {
        var teamToOverview;


         return {
                     getTeamToOverview: function() {
                         return teamToOverview;
                     },
                     setTeamToOverview: function(team) {
                         teamToOverview = team;
                     }
                 };
    });


});