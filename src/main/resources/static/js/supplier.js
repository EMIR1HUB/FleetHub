
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /clients/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (supplier, status) {
            $('#txtIdEdit').val(supplier.id);
            $('#txtNameEdit').val(supplier.name);
            $('#txtDetailsEdit').val(supplier.details);
            $('#txtWebsiteEdit').val(supplier.website);
            $('#txtAddressEdit').val(supplier.address);
            $('#ddlRegionEdit').val(supplier.regionid);
            $('#ddlCountryEdit').val(supplier.countryid);
            $('#txtCityEdit').val(supplier.city);
            $('#txtPhoneEdit').val(supplier.phone);
            $('#txtEmailEdit').val(supplier.email);
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
        var supplierName = $(this).data('name');      // Получаем name из data-атрибута

        $('#supplierNameSpan').text(supplierName);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});