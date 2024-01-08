
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /invoice_status/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (invoiceStatus, status) {
            $('#idEdit').val(invoiceStatus.id);
            $('#descriptionEdit').val(invoiceStatus.description);
            $('#detailsEdit').val(invoiceStatus.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (invoiceStatus, status) {
            $('#idDetails').val(invoiceStatus.id);
            $('#descriptionDetails').val(invoiceStatus.description);
            $('#detailsDetails').val(invoiceStatus.details);
            $('#lastModifiedByDetails').val(invoiceStatus.lastModifiedBy);
            $('#lastModifiedDateDetails').val(invoiceStatus.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var invoicesStatusName = $(this).data('description');   // Получаем description из data-атрибута


        $('#invoicesStatusNameSpan').text(invoicesStatusName);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});