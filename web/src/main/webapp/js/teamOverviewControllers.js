define(['angular','_'], function() {
    'use strict';

    var teamOverviewControllers =  angular.module('myApp.teamOverviewControllers', ['myApp.daos']);
            // Sample controller where service is being used




    teamOverviewControllers.controller('TeamOverviewController', ['$scope','todoService', function($scope, todoService) {

        $scope.teamOverviewData = {};

        $scope.teamOverviewData.team = todoService.getTeamToOverview();

        alert($scope.teamOverviewData.team.teamName);

        var teamChanged = function(){
            $scope.teamOverviewData.team = todoService.getTeamToOverview();
            alert($scope.teamOverviewData.team.teamName);
        };



        $scope.$on('TeamOverviewSelected', teamChanged);


    }]);



    return teamOverviewControllers;





});