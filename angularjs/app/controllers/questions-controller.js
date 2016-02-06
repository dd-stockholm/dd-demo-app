'use strict';

angular.module('dd-demo-app')
    .controller('QuestionsController', ['$scope', 'QuestionsService', function($scope, QuestionsService) {
        QuestionsService.active().then(function(questionItems) {
            console.log("ctrl", questionItems);
            $scope.questionItems = questionItems;
        }, function(err) {
            alert(err);
        });
    }]);