define(['angular','_'], function() {
    'use strict';

    var planningControllers =  angular.module('myApp.planningControllers', ['myApp.daos']);
            // Sample controller where service is being used




    planningControllers.controller('PlanningController', ['$scope','todoService','$modal', function($scope, todoService, $modal) {

        $scope.planningData = {};

        $scope.planningData.team = todoService.getTeamToShowBoard();


        var teamChanged = function(){
            $scope.planningData.team = todoService.getTeamToShowBoard();
            alert($scope.planningData.team.teamName);
        };



        $scope.$on('PlanningSelected', teamChanged);

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

    planningControllers.controller('AddSprintDialogController', ['$scope', function($scope) {


    }]);



    return planningControllers;





});