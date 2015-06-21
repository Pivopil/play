'use strict';

movieApp.controller('MainController', ['$scope', 'MovieService', 'cfg', '$modal',
    function ($scope, MovieService, cfg, $modal) {

        $scope.displayed = [];
        $scope.searchText = '';

        $scope.search = function () {
            $scope.tableState.pagination.start = 0;
            $scope.callServer($scope.tableState);
        };

        $scope.callServer = function callServer(tableState) {
            $scope.tableState = tableState;
            $scope.isLoading = true;

            var start = tableState.pagination.start || 0;

            var page = start / 20 + 1;
            var query = $scope.searchText !== "" ? $scope.searchText : "titanic";

            MovieService.getMovies(query, page).then(function (result) {
                $scope.displayed = result.results;
                tableState.pagination.numberOfPages = result.total_pages;
                $scope.isLoading = false;
            });
        };

        $scope.save = function (themoviedbId, name) {
            var modalInstance = $modal.open({
                templateUrl: '/assets/angular/add-movie-dialog.html',
                controller: 'AddMovieController',
                resolve: {
                    favouriteLists: ['MovieService', function (MovieService) {
                        return MovieService.getFavouriteLists();
                    }]
                }
            });

            modalInstance.result.then(function (favouriteList) {
                favouriteList.customPOST({themoviedbId: themoviedbId, favouriteId: favouriteList.id, name: name}, cfg.movieUrl);
            })
        };

    }]);