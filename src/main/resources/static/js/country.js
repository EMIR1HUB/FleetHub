$('document').ready(function () {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();

        // /countries/getById/?id=1
        var href = $(this).attr('href');

        $.get(href, function (country, status) {
            $('#idEdit').val(country.id);
            $('#descriptionEdit').val(country.description);
            $('#capitalEdit').val(country.capital);
            $('#codeEdit').val(country.code);
            $('#continentEdit').val(country.continent);
            $('#nationalityEdit').val(country.nationality);
        });

        $('#editModal').modal();
    });

    $('table #detailsButton').on('click', function (event) {
        event.preventDefault();

        // /countries/getById/?id=1
        var href = $(this).attr('href');

        $.get(href, function (country, status) {
            $('#idDetails').val(country.id);
            $('#descriptionDetails').val(country.description);
            $('#capitalDetails').val(country.capital);
            $('#codeDetails').val(country.code);
            $('#continentDetails').val(country.continent);
            $('#nationalityDetails').val(country.nationality);
            $('#lastModifiedByDetails').val(location.lastModifiedBy);
            $('#lastModifiedDateDetails').val(location.lastModifiedDate.substr(0, 19).replace("T", " "));
        });
        $('#detailsModal').modal();
    });


    $('table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var countryDescription = $(this).data('description');   // Получаем description из data-атрибута

        $('#countryDescriptionSpan').text(countryDescription);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});