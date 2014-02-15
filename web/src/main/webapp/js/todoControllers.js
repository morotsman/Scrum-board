define(['angular','_'], function() {
    'use strict';

    var todoControllers =  angular.module('myApp.todoControllers', ['myApp.services']);
            // Sample controller where service is being used


    todoControllers.controller('PersonalController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('AdminTeamController', ['$scope','teamDao', function($scope, teamDao) {

        $scope.teamAdminData = {};



        var teamsLoaded = function(data){
            $scope.teamAdminData.teams = data.teams;
        }

        var loadTeams = function(){
            teamDao.getTeams(teamsLoaded, failure);
        }

        var deselectAllTeams = function(){
            $scope.teamAdminData.selectedTeam = undefined;
            $scope.teamAdminData.teams = _.map($scope.teamAdminData.teams,function(team){ team.selected = false; return team;});
        };

        $scope.selectTeam = function(index){
           deselectAllTeams();
           $scope.teamAdminData.selectedTeam =  $scope.teamAdminData.teams[index];
           $scope.teamAdminData.selectedTeam.selected = true;
           $scope.teamAdminData.createTeam = undefined;
        };

        $scope.newTeam = function(){
            deselectAllTeams();
            $scope.teamAdminData.selectedTeam = undefined;
            $scope.teamAdminData.createTeam = {};
        }

        var success = function(){
            loadTeams();
        };

        var failure = function(failure){
            alert("Failure");
        };

        $scope.createTeam = function(){
            teamDao.saveTeam($scope.teamAdminData.createTeam, success, failure);
            $scope.teamAdminData.createTeam = undefined;
        }

        $scope.saveTeam = function(){
            teamDao.saveTeam($scope.teamAdminData.selectedTeam, success, failure);
            deselectAllTeams();
        }

        loadTeams();


    }]);

    todoControllers.controller('AdminUserController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('TeamOverviewController', ['$scope', function($scope) {


    }]);

    todoControllers.controller('BoardController', ['$scope', function($scope) {


    }]);

    return todoControllers;





});