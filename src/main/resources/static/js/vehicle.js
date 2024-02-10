
$('document').ready(function () {

    $("#ddlVehicleMakeAdd").change(function(){
        var selectedVehicleMakeId = $(this).val();

        // Отправка запроса на получение регионов для выбранной страны
        $.get("/vehicles/parameters/make/" + selectedVehicleMakeId, function(vehicleModels){
            // Обновление раскрывающегося списка регионов
            var vehicleModelDropdown = $("#ddlVehicleModelAdd");
            vehicleModelDropdown.empty();

            // Заполнение раскрывающегося списка регионов новыми значениями
            $.each(vehicleModels, function(index, vehicleModel){
                vehicleModelDropdown.append($("<option>").text(vehicleModel.description).val(vehicleModel.id));
            });
        });
    });

    $("#ddlVehicleMakeEdit").change(function(){
        var selectedVehicleMakeId = $(this).val();

        $.get("/vehicles/parameters/make/" + selectedVehicleMakeId, function(vehicleModels){
            // Обновление раскрывающегося списка регионов
            var vehicleModelDropdown = $("#ddlVehicleModelEdit");
            vehicleModelDropdown.empty();

            $.each(vehicleModels, function(index, vehicleModel){
                vehicleModelDropdown.append($("<option>").text(vehicleModel.description).val(vehicleModel.id));
            });
        });
    });

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /vehicles/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicle, status) {
            $('#idEdit').val(vehicle.id);
            $('#ddlVehicleTypeEdit').val(vehicle.vehicletypeid);
            $('#txtVehicleNameEdit').val(vehicle.name);
            $('#txtVehicleNumberEdit').val(vehicle.vehicleNumber);
            $('#txtRegistrationDateEdit').val(vehicle.registrationDate.substr(0, 10));
            $('#txtDescriptionEdit').val(vehicle.description);
            $('#txtAcquisitionDateEdit').val(vehicle.acquisitionDate.substr(0, 10));
            $('#ddlVehicleMakeEdit').val(vehicle.vehiclemakeid);
            $('#ddlVehicleModelEdit').val(vehicle.vehiclemodelid);
            $('#ddlCurrentLocationEdit').val(vehicle.locationid);
            $('#txtPowerEdit').val(vehicle.power);
            $('#txtFuelCapacityEdit').val(vehicle.fuelCapacity);
            $('#txtNetWeightEdit').val(vehicle.netWeight);
            $('#txtRemarksEdit').val(vehicle.remarks);
            $('#ddlEmployeeEdit').val(vehicle.employeeid);
            $('#ddlCurrentStatusEdit').val(vehicle.vehiclestatusid);
            $('#txtImageEdit').val(vehicle.photo);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicle, status) {
            $('#idDetails').val(vehicle.id);
            $('#ddlVehicleTypeDetails').val(vehicle.vehicletypeid);
            $('#txtVehicleNameDetails').val(vehicle.name);
            $('#txtVehicleNumberDetails').val(vehicle.vehicleNumber);
            $('#txtRegistrationDateDetails').val(vehicle.registrationDate.substr(0, 10));
            $('#txtDescriptionDetails').val(vehicle.description);
            $('#txtAcquisitionDateDetails').val(vehicle.acquisitionDate.substr(0, 10));
            $('#ddlVehicleMakeDetails').val(vehicle.vehiclemakeid);
            $('#ddlVehicleModelDetails').val(vehicle.vehiclemodelid);
            $('#ddlCurrentLocationDetails').val(vehicle.locationid);
            $('#txtPowerDetails').val(vehicle.power);
            $('#txtFuelCapacityDetails').val(vehicle.fuelCapacity);
            $('#txtNetWeightDetails').val(vehicle.netWeight);
            $('#txtRemarksDetails').val(vehicle.remarks);
            $('#ddlEmployeeDetails').val(vehicle.employeeid);
            $('#ddlCurrentStatusDetails').val(vehicle.vehiclestatusid);
            $('#createdByDetails').val(vehicle.createdBy);
            $('#createdDateDetails').val(vehicle.createdDate.substr(0,19).replace("T", " "));
            $('#lastModifiedByDetails').val(vehicle.lastModifiedBy);
            $('#lastModifiedDateDetails').val(vehicle.lastModifiedDate.substr(0, 19).replace("T", " "));
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehicleName = $(this).data('name');      // Получаем name из data-атрибута

        $('#vehicleNameSpan').text(vehicleName);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });

    $('.table #photoButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $('#employeePhoto').attr('src', href);
        $('#photoModal').modal();
    });
});