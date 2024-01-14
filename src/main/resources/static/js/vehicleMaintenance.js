
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /vehicleMaintenance/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicleMaintenance, status) {
            $('#idEdit').val(vehicleMaintenance.id);
            $('#priceEdit').val(vehicleMaintenance.price);
            $('#remarksEdit').val(vehicleMaintenance.remarks);
            $('#ddlSupplierEdit').val(vehicleMaintenance.supplierid);
            $('#ddlVehicleEdit').val(vehicleMaintenance.vehicleid);
            $('#startDateEdit').val(vehicleMaintenance.startDate.substr(0, 10));
            $('#endDateEdit').val(vehicleMaintenance.endDate.substr(0, 10));
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicleMaintenance, status) {
            $('#idDetails').val(vehicleMaintenance.id);
            $('#priceDetails').val(vehicleMaintenance.price);
            $('#remarksDetails').val(vehicleMaintenance.remarks);
            $('#ddlSupplierDetails').val(vehicleMaintenance.supplierid);
            $('#ddlVehicleDetails').val(vehicleMaintenance.vehicleid);
            $('#startDateDetails').val(vehicleMaintenance.startDate.substr(0, 10));
            $('#endDateDetails').val(vehicleMaintenance.endDate.substr(0, 10));
            $('#lastModifiedByDetails').val(vehicleMaintenance.lastModifiedBy);
            $('#lastModifiedDateDetails').val(vehicleMaintenance.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehicleMaintenanceVehicle = $(this).data('name');      // Получаем name из data-атрибута

        $('#vehicleMaintenanceVehicleSpan').text(vehicleMaintenanceVehicle);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});