$('document').ready(function () {
    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /regions/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (region, status) {
            $('#idEdit').val(region.id);
            $('#ddlCountryEdit').val(region.countryid);
            $('#capitalEdit').val(region.capital);
            $('#codeEdit').val(region.code);
            $('#nameEdit').val(region.name);
            $('#detailsEdit').val(region.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (region, status) {
            $('#idDetails').val(region.id);
            $('#ddlCountryDetails').val(region.countryid);
            $('#nameDetails').val(region.name);
            $('#capitalDetails').val(region.capital);
            $('#codeDetails').val(region.code);
            $('#detailsDetails').val(region.details);
            $('#lastModifiedByDetails').val(region.lastModifiedBy);
            $('#lastModifiedDateDetails').val(region.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var regionName = $(this).data('name');   // Получаем description из data-атрибута


        $('#regionNameSpan').text(regionName);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});