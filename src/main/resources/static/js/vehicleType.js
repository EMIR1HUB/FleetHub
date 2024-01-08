
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /vehicle_types/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicleTypes, status) {
            $('#idEdit').val(vehicleTypes.id);
            $('#descriptionEdit').val(vehicleTypes.description);
            $('#detailsEdit').val(vehicleTypes.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicleTypes, status) {
            $('#idDetails').val(vehicleTypes.id);
            $('#descriptionDetails').val(vehicleTypes.description);
            $('#detailsDetails').val(vehicleTypes.details);
            $('#lastModifiedByDetails').val(vehicleTypes.lastModifiedBy);
            $('#lastModifiedDateDetails').val(vehicleTypes.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehiclesTypeName = $(this).data('description');   // Получаем description из data-атрибута


        $('#vehiclesTypeNameSpan').text(vehiclesTypeName);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});