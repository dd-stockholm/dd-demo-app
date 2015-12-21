'use strict';

var ddDemoApp = angular.module('dd-demo-app', [
    'ngRoute'
    ]);
  
  ddDemoApp.config(['$routeProvider', function($routeProvider){
    $routeProvider.
    when('/parties', {
      templateUrl: 'partials/parties.html',
      controller: 'PartiesController'
    }).
    when('/questions', {
      templateUrl: 'partials/questions.html',
      controller: 'QuestionController'
    }).
    otherwise({
      redirectTo: 'index.html'
    });
  }]);