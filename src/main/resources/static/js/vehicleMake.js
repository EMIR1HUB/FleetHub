
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /vehicles_makes/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (vehicleMake, status) {
            $('#idEdit').val(vehicleMake.id);
            $('#descriptionEdit').val(vehicleMake.description);
            $('#detailsEdit').val(vehicleMake.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (vehicleMake, status) {
            $('#idDetails').val(vehicleMake.id);
            $('#descriptionDetails').val(vehicleMake.description);
            $('#detailsDetails').val(vehicleMake.details);
            $('#lastModifiedByDetails').val(vehicleMake.lastModifiedBy);
            $('#lastModifiedDateDetails').val(vehicleMake.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var vehiclesMakeName = $(this).data('description');   // Получаем description из data-атрибута


        $('#vehiclesMakeNameSpan').text(vehiclesMakeName);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});