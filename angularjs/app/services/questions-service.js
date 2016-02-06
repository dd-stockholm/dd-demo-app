'use strict';

angular.module('dd-demo-app')
    .factory('QuestionsService', ['$http', '$q', function($http, $q) {

        var deferred = $q.defer();

        var success = function(response) {
            console.log('success', response);
            deferred.resolve(response.data);
            return deferred.promise;
        }

        var failure = function(err) {
            console.log('failure', err);
            deferred.reject(err.data);
            return deferred.promise;
        }

        var service = {
            active: function() {
                return $http.get('/api/question/active').then(success, failure);
            }
        };

        return service;
    }]);