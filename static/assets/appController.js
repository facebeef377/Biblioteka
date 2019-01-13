var app = angular.module('app', ['ngRoute', 'ngStorage', 'filters']);

app.controller('appController', function ($rootScope, $scope, $http, $timeout, $filter, $location, $localStorage) {

  $rootScope.baseUrl = "http://localhost:8080";

  $scope.initVars_admin = function () {
    console.log("Library Init!");
    $scope.identify();
    $scope.getBooks();
    $scope.getAuthors();
    $scope.getLeases();
  }

  //////////////////////////////////////////////
  //
  //      USER
  //
  //////////////////////////////////////////////

  $scope.user = {}; //Dane użytkownika
  $scope.login = {}; //Tymczasowe dane do logowania
  $scope.register = {}; //Tymczasowe dane do rejestracji

  //Wysyła token, aby sprawdzić dane uzytkownika
  $scope.identify = function () {
    if ($localStorage.token != "" && $localStorage.token != null) {
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/user/identify",
        headers: headers
      }

      $http(req).then(function (result) {
        console.log(result.data);
        $scope.user = result.data;
      });
    }
  }

  //Dodanje nowego uzytkownika
  $scope.register_user = function () {
    $http.post($rootScope.baseUrl + "/user/register", $scope.register).then(function (result) {
      if (result.data.Status == "OK") {
        $rootScope.showSuccessAlert("Zarejestrowano!");
        $location.path("/login");
      } else if (result.data.Status == "FAILED_LOGIN") {
        $rootScope.showErrorAlert("Nazwa uzytkownika jest zajęta!");
      } else {
        $rootScope.showErrorAlert("Błąd");
      }
    });
  }

  //Funkcja odpowiadająca za logowanie
  $scope.login_user = function () {
    $http
      .post($rootScope.baseUrl + "/login", $scope.login)
      .then(
        function (result) {
          //Zwraca token, który ląduje do pamięci przeglądarki
          $localStorage.token = result.data.token;
          $rootScope.showSuccessAlert("Zalogowano!");
          $location.path("/books");
          $scope.identify();
        },
        function () {
          $rootScope.showErrorAlert("Blad logowania!");
        });
  }

  //Wylogowanie / Usuwa wszystkie dane o użytkowniku z przeglądrki
  $scope.logout = function () {
    $scope.user = {};
    $localStorage.token = "";
    document.location = $rootScope.baseUrl + "/index.html";
  }

  //////////////////////////////////////////////
  //
  //      BOOKS
  //
  //////////////////////////////////////////////
  $scope.books = []; //Lista książek
  $scope.book = {}; //Nowy obiekt - książka

  //Funkcja pobiera wszystkie dostępne książki
  $scope.getBooks = function () {
    $scope.clearMyFilter();

    var req = {
      method: 'POST',
      url: $scope.baseUrl + "/book/all"
    }

    $http(req).then(function (result) {
      console.log(result.data);
      $scope.books = result.data;
    });
  }

  $scope.addBook = function () {
    if ($localStorage.token != "" && $localStorage.token != null) {
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/book/add",
        headers: headers,
        data: $scope.book
      }

      $http(req).then(function (result) {
        console.log(result.data);
        if (result.data.Status == "OK") {
          $rootScope.showSuccessAlert("Zapisano");
          $scope.book = {};
          $location.path("/books");
          $scope.getBooks();
        } else {
          $rootScope.showErrorAlert("Błąd");
        }
      });
    }
  }

  $scope.deleteBook = function (book) {
    if ($localStorage.token != "" && $localStorage.token != null) {
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/book/delete",
        headers: headers,
        data: {
          id: book.id
        }
      }

      $http(req).then(function (result) {
        console.log(result.data);
        if (result.data.Status == "OK") {
          $rootScope.showSuccessAlert("Usunięto");
          $scope.getBooks();
        } else {
          $rootScope.showErrorAlert("Błąd");
        }
      });
    }
  }

  $scope.gotoEdit_book = function (book) {
    $location.path("/add_book");
    $scope.book = book;
  }

  $scope.clearBook = function () {
    $scope.book = {};
  }


  //////////////////////////////////////////////
  //
  //      AUTHORS
  //
  //////////////////////////////////////////////
  $scope.authors = []; //Lista autorów
  $scope.author = {}; //Nowy obiekt - autor

  //Funkcja pobiera wszystkich autorów
  $scope.getAuthors = function () {
    if ($localStorage.token != "" && $localStorage.token != null) {
      $scope.clearMyFilter();
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/author/all",
        headers: headers
      }

      $http(req).then(function (result) {
        console.log(result.data);
        $scope.authors = result.data;
      });
    }
  }

  $scope.addAuthor = function () {
    if ($localStorage.token != "" && $localStorage.token != null) {
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/author/add",
        headers: headers,
        data: $scope.author
      }

      $http(req).then(function (result) {
        console.log(result.data);
        if (result.data.Status == "OK") {
          $rootScope.showSuccessAlert("Zapisano");
          $scope.author = {};
          $location.path("/authors");
          $scope.getAuthors();
        } else {
          $rootScope.showErrorAlert("Błąd");
        }
      });
    }
  }

  $scope.deleteAuthor = function (author) {
    if ($localStorage.token != "" && $localStorage.token != null) {
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/author/delete",
        headers: headers,
        data: {
          id: author.id
        }
      }

      $http(req).then(function (result) {
        console.log(result.data);
        if (result.data.Status == "OK") {
          $rootScope.showSuccessAlert("Usunięto");
          $scope.getAuthors();
        } else {
          $rootScope.showErrorAlert("Błąd");
        }
      });
    }
  }

  $scope.gotoEdit_author = function (author) {
    $location.path("/add_author");
    $scope.author = author;
    delete $scope.author.books;
  }

  $scope.clearAuthor = function () {
    $scope.author = {};
  }

  //////////////////////////////////////////////
  //
  //      Leases
  //
  //////////////////////////////////////////////
  $scope.leases = []; //Lista wypożyczeń
  $scope.lease = {}; //Nowy obiekt - wypożyczenie

  //Funkcja pobiera wszystkich wypożyczeń
  $scope.getLeases = function () {
    if ($localStorage.token != "" && $localStorage.token != null) {
      $scope.clearMyFilter();
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/lease/all",
        headers: headers
      }

      $http(req).then(function (result) {
        console.log(result.data);
        $scope.leases = result.data;
      });
    }
  }

  $scope.addLease = function (book) {
    if ($localStorage.token != "" && $localStorage.token != null) {

      $scope.lease = {
        reader: {
          id: $scope.user.id
        },
        book: {
          id: book.id
        }
      }

      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/lease/add",
        headers: headers,
        data: $scope.lease
      }

      $http(req).then(function (result) {
        console.log(result.data);
        if (result.data.Status == "OK") {
          $rootScope.showSuccessAlert("Wypożyczono");
          $scope.lease = {};
          $location.path("/leases");
          $scope.getBooks();
        } else {
          $rootScope.showErrorAlert("Błąd");
        }
      });
    }
  }

  $scope.deleteLease = function (lease) {
    if ($localStorage.token != "" && $localStorage.token != null) {
      var headers = {}
      headers['Authorization'] = 'Bearer ' + $localStorage.token;

      var req = {
        method: 'POST',
        url: $scope.baseUrl + "/lease/return",
        headers: headers,
        data: {
          id: lease.id
        }
      }

      $http(req).then(function (result) {
        console.log(result.data);
        if (result.data.Status == "OK") {
          $rootScope.showSuccessAlert("Usunięto");
          $scope.getLeases();
        } else {
          $rootScope.showErrorAlert("Błąd");
        }
      });
    }
  }

  $scope.clearLease = function () {
    $scope.lease = {};
  }

  //////////////////////////////////////////////
  //
  //      Filter
  //
  //////////////////////////////////////////////

  $scope.propertyName = 'id';
  $scope.reverse = true;
  $scope.search = {};

  $scope.sortBy = function (propertyName) {
    $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
    $scope.propertyName = propertyName;
  };

  $scope.filterMyData = function (input, searchParam) {

    if (searchParam == '')
      return true;

    if (input == null || input == undefined) {
      input = "";
    }

    return input.toLowerCase().indexOf(searchParam.toLowerCase()) >= 0;
  }

  $scope.clearMyFilter = function () {
    $scope.propertyName = 'id';
    $scope.reverse = true;
    $scope.search = {};
  }

});

angular.module('filters', []).filter('milisecondFilter', [function () {
  return function (number) {
    if (!angular.isUndefined(number)) {
      return Math.ceil(number / (1000 * 60 * 60 * 24));
    }
  };
}]);
