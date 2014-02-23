define(['angular','_'], function() {
    'use strict';

    var teamOverviewControllers =  angular.module('myApp.teamOverviewControllers', ['myApp.daos']);
            // Sample controller where service is being used




    teamOverviewControllers.controller('TeamOverviewController', ['$scope','todoService','$modal', function($scope, todoService, $modal) {

        $scope.teamOverviewData = {};

        $scope.teamOverviewData.team = todoService.getTeamToOverview();

        alert($scope.teamOverviewData.team.teamName);

        var teamChanged = function(){
            $scope.teamOverviewData.team = todoService.getTeamToOverview();
            alert($scope.teamOverviewData.team.teamName);
        };



        $scope.$on('TeamOverviewSelected', teamChanged);

        $scope.addSprint = function(){
            var modalInstance = $modal.open({
                templateUrl: 'partials/addSprint.html',
                controller: 'AddSprintDialogController'
            });

            modalInstance.result.then(function(presentation) {
                $scope.loadPresentation(presentation.id);
            }, function() {
                //Do nothing if not saving
            });
        };

        $scope.addStory = function(){

        };


    }]);

    teamOverviewControllers.controller('AddSprintDialogController', ['$scope', function($scope) {


    }]);



    return teamOverviewControllers;





});