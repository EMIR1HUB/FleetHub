
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /vehicle_models/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicleModels, status) {
            $('#idEdit').val(vehicleModels.id);
            $('#ddlVehicleMakesEdit').val(vehicleModels.vehiclemakeid);
            $('#descriptionEdit').val(vehicleModels.description);
            $('#detailsEdit').val(vehicleModels.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicleModels, status) {
            $('#idDetails').val(vehicleModels.id);
            $('#ddlVehicleMakesDetails').val(vehicleModels.vehiclemakeid);
            $('#descriptionDetails').val(vehicleModels.description);
            $('#detailsDetails').val(vehicleModels.details);
            $('#lastModifiedByDetails').val(vehicleModels.lastModifiedBy);
            $('#lastModifiedDateDetails').val(vehicleModels.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehiclesModelName = $(this).data('description');   // Получаем description из data-атрибута


        $('#vehiclesModelNameSpan').text(vehiclesModelName);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});