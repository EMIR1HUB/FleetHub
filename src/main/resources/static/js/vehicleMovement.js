
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /vehickeMovements/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicleMovement, status) {
            $('#idEdit').val(vehicleMovement.id);
            $('#ddlVehicleEdit').val(vehicleMovement.vehicleid);
            $('#ddlLocation1Edit').val(vehicleMovement.locationid1);
            $('#ddlLocation2Edit').val(vehicleMovement.locationid2);
            $('#date1Edit').val(vehicleMovement.date1.substr(0, 10));
            $('#date2Edit').val(vehicleMovement.date2.substr(0, 10));
            $('#remarksEdit').val(vehicleMovement.remarks);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicleMovement, status) {
            $('#idDetails').val(vehicleMovement.id);
            $('#ddlVehicleDetails').val(vehicleMovement.vehicleid);
            $('#ddlLocation1Details').val(vehicleMovement.locationid1);
            $('#ddlLocation2Details').val(vehicleMovement.locationid2);
            $('#date1Details').val(vehicleMovement.date1.substr(0, 10));
            $('#date2Details').val(vehicleMovement.date2.substr(0, 10));
            $('#remarksDetails').val(vehicleMovement.remarks);
            // $('#lastModifiedByDetails').val(vehicleMaintenance.lastModifiedBy);
            // $('#lastModifiedDateDetails').val(vehicleMaintenance.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehicleMovementVehicle = $(this).data('name');      // Получаем name из data-атрибута

        $('#vehicleMovementVehicleSpan').text(vehicleMovementVehicle);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});