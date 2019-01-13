app.run(function ($rootScope) {

  $rootScope.showSuccessAlert = function (text) {
    swal({
      type: 'success',
      title: text,
      showConfirmButton: false,
      timer: 1500
    })
  }

  $rootScope.showErrorAlert = function (text) {
    swal({
      type: 'error',
      title: text,
      showConfirmButton: false,
      timer: 1500
    })
  }

  $rootScope.showInfoAlert = function (text) {
    swal({
      type: 'info',
      title: text,
      showConfirmButton: false,
      timer: 1500
    })
  }

  $rootScope.showLoading = function (text) {
    swal({
      title: 'Czekaj!',
      text: text,
      type: 'info',
      showConfirmButton: false,
    })
  }

  $rootScope.closeLoading = function () {
    swal.close();
  }

});