define(['angular','_'], function() {
    'use strict';

    var todoControllers =  angular.module('myApp.todoControllers', ['myApp.services']);
            // Sample controller where service is being used

    todoControllers.controller('MenuController', ['$scope', function($scope) {

        $scope.menuData = {};

        $scope.menuData.template = 'partials/Personal.html';

        $scope.menuData.teams = [
            "Team 1",
            "Team 2",
            "Team 3"
          ];

        $scope.adminTeam = function(){
            $scope.menuData.template = 'partials/AdminTeam.html';
        };

        $scope.adminUser = function(){
            $scope.menuData.template = 'partials/AdminUser.html';
        };

        $scope.showTeamOverview = function(teamIndex){
            $scope.menuData.template = 'partials/TeamOverview.html';
        }

        $scope.showBoard = function(teamIndex){
            $scope.menuData.template = 'partials/Board.html';
        }

    }]);

    todoControllers.controller('MainController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('PersonalController', ['$scope', function($scope) {


    }]);




    return todoControllers;





});