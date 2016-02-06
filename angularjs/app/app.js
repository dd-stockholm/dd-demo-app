'use strict';

var ddDemoApp = angular.module('dd-demo-app', [
    'ngRoute',
    'ui.bootstrap'
    ]);
  
  ddDemoApp.config(['$routeProvider', function($routeProvider){
    $routeProvider.
    when('/delegation', {
      templateUrl: 'partials/parties.html',
      controller: 'PartiesController'
    }).
    when('/vote', {
      templateUrl: 'partials/questions.html',
      controller: 'QuestionsController'
    }).
    when('/about', {
      templateUrl: 'partials/about.html',
      controller: 'AboutController'
    }).
    otherwise({
      redirectTo: 'index.html'
    });
  }]);