define(['angular','_'], function() {
    'use strict';

    var todoControllers =  angular.module('myApp.todoControllers', ['myApp.services']);
            // Sample controller where service is being used

    todoControllers.controller('MenuController', ['$scope','$location', function($scope,$location) {

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

    todoControllers.controller('MainController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('PersonalController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('AdminTeamController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('AdminUserController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('TeamOverviewController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('BoardController', ['$scope', function($scope) {


    }]);

    return todoControllers;





});