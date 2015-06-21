'use strict';

movieApp.controller('FavouriteListController', ['$scope', 'movies', '$stateParams', function ($scope, movies, $stateParams) {
    $scope.movies = movies;
    $scope.id = $stateParams.id;

    $scope.removeMovie = function (movie) {
        var index = $scope.movies.indexOf(movie);
        movie.remove().then(function () {
            $scope.movies.splice(index, 1);
        });
    }
}]);