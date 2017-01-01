var app = angular.module('campApp', ["ngRoute", 'ngSanitize', 'angular-bind-html-compile']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : "camp.html",
            controller : "campCtrl"
        })
        .when('/quarters', {
            templateUrl : "quarters.html",
            controller : "quartersCtrl"
        })
        .when('/recruitment_post', {
            templateUrl : "recruitment_post.html",
            controller : "recruitmentPostCtrl"
        })
        .when('/inspect_recruits', {
            templateUrl : "inspect_recruits.html",
            controller : "inspectRecruitsCtrl"
        })
        .when('/notice_board', {
            templateUrl : "notice_board.html"
        })
        .otherwise({
            template : "<h1>Page not found</h1><p>The page you were trying to access does not exist</p>"
        });
});

app.factory('campService', function() {
    var passedData = {};
    function set(data) {
        passedData = data;
    }
    function get() {
        return passedData;
    }

    return {
        set: set,
        get: get
    }
});
