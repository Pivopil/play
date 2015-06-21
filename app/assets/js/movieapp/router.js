'use strict';

movieApp.config([ '$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('main', {
            url: '/',
            templateUrl: '/assets/angular/main-template.html',
            controller: 'MainController',
            ncyBreadcrumb: {
                label: 'Search page'
            }
        })
        .state('lists', {
            url: '/list',
            templateUrl: '/assets/angular/lists-template.html',
            controller: 'ListsController',
            resolve: {
                favouriteLists: ['MovieService', function (MovieService) {
                    return MovieService.getFavouriteLists();
                }]
            },
            ncyBreadcrumb: {
                parent: 'main',
                label: 'Favourite lists'
            }
        })
        .state('movies', {
            url: '/list/:id',
            templateUrl: '/assets/angular/favourite-list-template.html',
            controller: 'FavouriteListController',
            resolve: {
                movies: ['$stateParams', 'MovieService', function ($stateParams, MovieService) {
                    return MovieService.getFavouriteMoviesByListId($stateParams.id);
                }]
            },
            ncyBreadcrumb: {
                parent: 'lists',
                label: 'Movies in list {{id}}'
            }
        })
        .state('movie', {
            url: '/list/:id/movie/:themoviedbId',
            templateUrl: '/assets/angular/movie-template.html',
            controller: 'MovieController',
            resolve: {
                movieDetails: ['$stateParams', 'MovieService', function ($stateParams, MovieService) {
                    return MovieService.getMovieDetails($stateParams.themoviedbId);
                }]
            },
            ncyBreadcrumb: {
                parent: 'movies({id: id})',
                label: '{{movieDetails.title}}'
            }
        });
}]);