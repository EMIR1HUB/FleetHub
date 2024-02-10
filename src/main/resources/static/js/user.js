$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault();
        // /users/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('#idEdit').val(user.id);
            $('#usernameEdit').val(user.username);
            $('#firstnameEdit').val(user.firstname);
            $('#lastnameEdit').val(user.lastname);
            $('#emailEdit').val(user.email);
            // $('#passwordEdit').val(user.password);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (user, status) {
            $('#idDetails').val(user.id);
            $('#usernameDetails').val(user.username);
            $('#firstnameDetails').val(user.firstname);
            $('#lastnameDetails').val(user.lastname);
            $('#emailDetails').val(user.email);
            // $('#passwordDetails').val(user.password);
            // $('#createdByDetails').val(employee.createdBy);
            // $('#createdDateDetails').val(employee.createdDate.substr(0,19).replace("T", " "));
            // $('#lastModifiedByDetails').val(employee.lastModifiedBy);
            // $('#lastModifiedDateDetails').val(employee.lastModifiedDate.substr(0, 19).replace("T", " "));
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var userFullName = $(this).data('name');      // Получаем name из data-атрибута

        $('#userFullNameSpan').text(userFullName);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });

    // $('.table #photoButton').on('click',function(event) {
    //     event.preventDefault();
    //     var href = $(this).attr('href');
    //     $('#employeePhoto').attr('src', href);
    //     $('#photoModal').modal();
    // });
});