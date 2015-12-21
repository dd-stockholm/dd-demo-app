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
    otherwise({
      redirectTo: 'index.html'
    });
  }]);