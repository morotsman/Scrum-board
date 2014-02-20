define(['angular','_'], function() {
    'use strict';

    var menuControllers =  angular.module('myApp.menuControllers', ['myApp.daos']);
            // Sample controller where service is being used

    menuControllers.controller('MenuController', ['$scope','$location', 'teamDao', function($scope,$location, teamDao) {

        $scope.menuData = {};


        $scope.adminTeam = function(){
            $location.url('/AdminTeamView');
        };

        $scope.adminUser = function(){
            $location.url('/AdminUserView');
        };

        $scope.showTeamOverview = function(teamIndex){
            $location.url('/TeamOverviewView');
        };

        $scope.showBoard = function(teamIndex){
            $location.url('/BoardView');
        }

        var loadTeams = function(){
            teamDao.getTeams(teamsLoaded, failure);
        };

        var teamsLoaded = function(data){
            $scope.menuData.teams = data.teams;
        }

        var failure = function(failure){
            alert("Failure");
        };

        $scope.$on('TeamCreated', function() {
            loadTeams();
        });


        loadTeams();


    }]);

    menuControllers.controller('MainController', ['$scope', function($scope) {


    }]);



    return menuControllers;





});