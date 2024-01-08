
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /vehicle_status/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicleStatus, status) {
            $('#idEdit').val(vehicleStatus.id);
            $('#descriptionEdit').val(vehicleStatus.description);
            $('#detailsEdit').val(vehicleStatus.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicleStatus, status) {
            $('#idDetails').val(vehicleStatus.id);
            $('#descriptionDetails').val(vehicleStatus.description);
            $('#detailsDetails').val(vehicleStatus.details);
            $('#lastModifiedByDetails').val(vehicleStatus.lastModifiedBy);
            $('#lastModifiedDateDetails').val(vehicleStatus.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehiclesStatusName = $(this).data('description');   // Получаем description из data-атрибута


        $('#vehiclesStatusNameSpan').text(vehiclesStatusName);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});