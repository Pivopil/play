'use strict';

movieApp.controller('MovieController', ['$scope', 'movieDetails', '$stateParams', function ($scope, movieDetails, $stateParams) {
    $scope.movieDetails = movieDetails;
    $scope.id = $stateParams.id;
    console.log(movieDetails);
}]);