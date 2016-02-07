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
    when('/delegation/m', {
      templateUrl: 'partials/m.html',
      controller: 'PartiesController'
    }).
    when('/delegation/kd', {
      templateUrl: 'partials/kd.html',
      controller: 'PartiesController'
    }).
    when('/delegation/v', {
      templateUrl: 'partials/v.html',
      controller: 'PartiesController'
    }).
    when('/delegation/sd', {
      templateUrl: 'partials/sd.html',
      controller: 'PartiesController'
    }).
    when('/delegation/s', {
      templateUrl: 'partials/s.html',
      controller: 'PartiesController'
    }).
    when('/delegation/mp', {
      templateUrl: 'partials/mp.html',
      controller: 'PartiesController'
    }).
    when('/delegation/l', {
      templateUrl: 'partials/l.html',
      controller: 'PartiesController'
    }).
    when('/delegation/c', {
      templateUrl: 'partials/c.html',
      controller: 'PartiesController'
    }).
    otherwise({
      redirectTo: 'index.html'
    });
  }]);