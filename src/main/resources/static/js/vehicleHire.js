
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /vehickeMovements/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicleHire, status) {
            $('#idEdit').val(vehicleHire.id);
            $('#ddlVehicleEdit').val(vehicleHire.vehicleid);
            $('#ddlClientEdit').val(vehicleHire.clientid);
            $('#ddlLocationEdit').val(vehicleHire.locationid);

            $('#dateOutEdit').val(vehicleHire.dateOut.substr(0, 10));
            $('#dateInEdit').val(vehicleHire.dateIn.substr(0, 10));
            $('#timeOutEdit').val(vehicleHire.timeOut);
            $('#timeInEdit').val(vehicleHire.timeIn);
            $('#priceEdit').val(vehicleHire.price);
            $('#remarksEdit').val(vehicleHire.remarks);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicleHire, status) {
            $('#idDetails').val(vehicleHire.id);
            $('#ddlVehicleDetails').val(vehicleHire.vehicleid);
            $('#ddlClientDetails').val(vehicleHire.clientid);
            $('#ddlLocationDetails').val(vehicleHire.locationid);

            $('#dateOutDetails').val(vehicleHire.dateOut.substr(0, 10));
            $('#dateInDetails').val(vehicleHire.dateIn.substr(0, 10));
            $('#timeOutDetails').val(vehicleHire.timeOut);
            $('#timeInDetails').val(vehicleHire.timeIn);
            $('#priceDetails').val(vehicleHire.price);
            $('#remarksDetails').val(vehicleHire.remarks);
            // $('#lastModifiedByDetails').val(vehicleMaintenance.lastModifiedBy);
            // $('#lastModifiedDateDetails').val(vehicleMaintenance.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehicleHireVehicle = $(this).data('name');      // Получаем name из data-атрибута

        $('#vehicleHireVehicleSpan').text(vehicleHireVehicle);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});