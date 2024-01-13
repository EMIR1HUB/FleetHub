
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /clients/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (contact, status) {
            $('#txtIdEdit').val(contact.id);
            $('#txtFirstnameEdit').val(contact.firstname);
            $('#txtLastnameEdit').val(contact.lastname);
            $('#txtEmailEdit').val(contact.email);
            $('#txtPhoneEdit').val(contact.phone);
            $('#txtRemarksEdit').val(contact.remarks);
        });
        $('#editModal').modal();
    });

    // $('.table #detailsButton').on('click', function (event) {
    //     event.preventDefault();
    //     var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
    //     $.get(href, function (client, status) {
    //         $('#idDetails').val(location.id);
    //         $('#descriptionDetails').val(location.description);
    //         $('#cityDetails').val(location.city);
    //         $('#addressDetails').val(location.address);
    //         $('#ddlCountryDetails').val(location.countryid);
    //         $('#ddlStateDetails').val(location.regionid);
    //         $('#detailsDetails').val(location.details);
    //         $('#lastModifiedByDetails').val(location.lastModifiedBy);
    //         $('#lastModifiedDateDetails').val(location.lastModifiedDate.substr(0, 19).replace("T", " "))
    //     });
    //     $('#detailsModal').modal(); // запуск модального окна
    // });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var contactFirstname = $(this).data('name');      // Получаем name из data-атрибута

        $('#contactFirstnameSpan').text(contactFirstname);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});