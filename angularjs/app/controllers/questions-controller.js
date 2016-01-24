'use strict';

angular.module('dd-demo-app')
    .controller('QuestionController', ['$scope', function($scope) {
      $scope.questions = questions;
    }]);