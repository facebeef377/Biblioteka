app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix('');
  $routeProvider
    .when('/add_author', {
      templateUrl: 'pages/add_author.html'
    })
    .when('/add_book', {
      templateUrl: 'pages/add_book.html'
    })
    .when('/add_taken', {
      templateUrl: 'pages/add_item.html'
    })
    .when('/authors', {
      templateUrl: 'pages/authors.html'
    })
    .when('/books', {
      templateUrl: 'pages/books.html'
    })
    .when('/taken', {
      templateUrl: 'pages/taken.html'
    })
    .when('/login', {
      templateUrl: 'pages/login.html'
    })
    .when('/register', {
      templateUrl: 'pages/register.html'
    })
    .otherwise({
      redirectTo: '/books'
    });
});