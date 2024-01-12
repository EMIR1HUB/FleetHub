
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /clients/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (client, status) {
            $('#txtIdEdit').val(client.id);
            $('#txtNameEdit').val(client.name);
            $('#txtDetailsEdit').val(client.details);
            $('#txtWebsiteEdit').val(client.website);
            $('#txtAddressEdit').val(client.address);
            $('#ddlRegionEdit').val(client.regionid);
            $('#ddlCountryEdit').val(client.countryid);
            $('#txtCityEdit').val(client.city);
            $('#txtPhoneEdit').val(client.phone);
            $('#txtEmailEdit').val(client.email);
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
        var clientName = $(this).data('name');      // Получаем name из data-атрибута

        $('#clientNameSpan').text(clientName);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});