'use strict';

movieApp.factory('MovieService', ['Restangular', 'cfg', function (Restangular, cfg) {

    Restangular.setBaseUrl(cfg.baseUrl);

    return {
        getMovies: function (query, page) {
            var url = cfg.themoviedbUrl.replace('__query__', query).replace('__page__', page).replace('__apiKey__', cfg.apiKey);
            return Restangular.oneUrl('themoviedbUrl', url).get();
        },
        getMovieDetails: function (themoviedbId) {
            var url = cfg.themoviedbDetailsUrl.replace('__themoviedbId__', themoviedbId).replace('__apiKey__', cfg.apiKey);
            return Restangular.oneUrl('themoviedbDetailsUrl', url).get();
        },
        getFavouriteLists: function () {
            return Restangular.all(cfg.favouriteListUrl).getList();
        },
        getFavouriteMoviesByListId: function (listId) {
            return Restangular.one(cfg.favouriteListUrl, listId).getList(cfg.movieUrl);
        }
    }
}]);