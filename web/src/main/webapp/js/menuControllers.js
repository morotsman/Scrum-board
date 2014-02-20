define(['angular','_'], function() {
    'use strict';

    var menuControllers =  angular.module('myApp.menuControllers', ['myApp.daos']);
            // Sample controller where service is being used

    menuControllers.controller('MenuController', ['$scope','$location', function($scope,$location) {

        $scope.menuData = {};


        $scope.menuData.teams = [
            "Team 1",
            "Team 2",
            "Team 3"
          ];

        $scope.adminTeam = function(){
            $location.url('/AdminTeamView');
        };

        $scope.adminUser = function(){
            $location.url('/AdminUserView');
        };

        $scope.showTeamOverview = function(teamIndex){
            $location.url('/TeamOverviewView');
        }

        $scope.showBoard = function(teamIndex){
            $location.url('/BoardView');
        }

    }]);

    menuControllers.controller('MainController', ['$scope', function($scope) {


    }]);



    return menuControllers;





});