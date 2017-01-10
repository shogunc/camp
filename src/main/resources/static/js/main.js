var app = angular.module('campApp', ["ngRoute", 'ngSanitize', 'angular-bind-html-compile']);

app.config(['$routeProvider', '$controllerProvider',
    function($routeProvider, $controllerProvider) {
        // Function used to register new controllers as new partials load.
        app.registerCtrl = $controllerProvider.register;
        // Function used to asynchronously preload scripts before routing.
        app.deployScripts = function(scripts) {
            return function($q,$rootScope){
                var deferred = $q.defer();
                $script(scripts, function() {
                    $rootScope.$apply(function() {
                        deferred.resolve();
                    });
                });
                return deferred.promise;
            }
        };

        $routeProvider
            .when('/', {
                templateUrl : "camp.html",
                resolve: {scripts: app.deployScripts(['js/camp.js'])}
            })
            .when('/quarters', {
                templateUrl : "quarters.html",
                resolve: {scripts: app.deployScripts(['js/quarters.js'])}
            })
            .when('/recruitment_post', {
                templateUrl : "recruitment_post.html",
                resolve: {scripts: app.deployScripts(['js/recruitment_post.js'])}
            })
            .when('/inspect_recruits', {
                templateUrl : "inspect_recruits.html"
            })
            .when('/notice_board', {
                templateUrl : "notice_board.html"
            })
            .otherwise({
                template : "<h1>Page not found</h1><p>The page you were trying to access does not exist</p>"
            });
        
    }
]);

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