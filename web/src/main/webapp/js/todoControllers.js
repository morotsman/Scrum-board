define(['angular','_'], function() {
    'use strict';

    var todoControllers =  angular.module('myApp.todoControllers', ['myApp.services']);
            // Sample controller where service is being used

    todoControllers.controller('MenuController', ['$scope', function($scope) {

        $scope.menuData = {};

        $scope.menuData.teams = [
            "Team 1",
            "Team 2",
            "Team 3"
          ];

        $scope.adminTeam = function(){
            alert("Admin team");
        };

        $scope.adminUser = function(){
            alert("Admin user");
        };

        $scope.showTeamOverview = function(teamIndex){
            alert($scope.menuData.teams[teamIndex])
        }

        $scope.showBoard = function(teamIndex){
            alert($scope.menuData.teams[teamIndex])
        }

    }]);

    todoControllers.controller('MainController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('PersonalController', ['$scope', function($scope) {


    }]);




    return todoControllers;





});