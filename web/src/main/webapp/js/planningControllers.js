define(['angular','_'], function() {
    'use strict';

    var planningControllers =  angular.module('myApp.planningControllers', ['myApp.daos']);
            // Sample controller where service is being used




    planningControllers.controller('PlanningController', ['$scope','todoService','$modal', 'sprintDao', function($scope, todoService, $modal, sprintDao) {

        $scope.planningData = {};

        $scope.planningData.team = todoService.getTeamToShowBoard();


        var teamChanged = function(){
            $scope.planningData.team = todoService.getTeamToShowBoard();
            alert($scope.planningData.team.teamName);
        };

        $scope.$on('PlanningSelected', teamChanged);

        var failure = function(){
            alert("Failure");
        }

        var sprintsLoaded = function(data){
            $scope.planningData.sprints = data.sprints;
            _.map($scope.planningData.sprints, function(sprint){
                sprint.stories = [];
                sprint.stories.push({name:"hepp"});
                return sprint;
            });
            $scope.planningData.sprints.push({name:"Backlog", stories:[]});
        }

        var loadSprints = function(){
            sprintDao.getSprints($scope.planningData.team, sprintsLoaded, failure);
        };

        $scope.addSprint = function(){
            var modalInstance = $modal.open({
                templateUrl: 'partials/addSprint.html',
                controller: 'AddSprintDialogController'
            });

            modalInstance.result.then(function(presentation) {
                loadSprints();
            }, function() {
                //cancel
            });
        };

          $scope.planningData.sortableOptions = {
            placeholder: "stories-placeholder",
            connectWith: ".stories-container",
            items: "> .sortable",
          };

        $scope.addStory = function(index){
            alert("add story for: " + $scope.planningData.sprints[index].name);
        };

        loadSprints();


    }]);

    planningControllers.controller('AddSprintDialogController', ['$scope', '$modalInstance','sprintDao','todoService', function($scope, $modalInstance, sprintDao, todoService) {

            $scope.createSprintData = {};

            $scope.ok = function() {
                var result;
                sprintDao.createSprint(todoService.getTeamToShowBoard(), $scope.createSprintData.sprintName, function(data){
                    $modalInstance.close(data);
                }, function(data){
                    if(data.status === 409){
                        $scope.createSprintData.error = "A sprint called " + $scope.createSprintData.sprintName + " already exists";
                    }else{
                        $scope.createSprintData.error = "Failed to create sprint due to " + data.status;
                    }
                });

            };

            $scope.cancel = function() {
                $modalInstance.dismiss('cancel');
            };

    }]);



    return planningControllers;





});