
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /employees_types/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (employeeType, status) {
            $('#idEdit').val(employeeType.id);
            $('#descriptionEdit').val(employeeType.description);
            $('#detailsEdit').val(employeeType.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (employeeType, status) {
            $('#idDetails').val(employeeType.id);
            $('#descriptionDetails').val(employeeType.description);
            $('#detailsDetails').val(employeeType.details);
            $('#lastModifiedByDetails').val(employeeType.lastModifiedBy);
            $('#lastModifiedDateDetails').val(employeeType.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var employeeTypeName = $(this).data('description');   // Получаем description из data-атрибута


        $('#employeeTypeSpan').text(employeeTypeName);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});