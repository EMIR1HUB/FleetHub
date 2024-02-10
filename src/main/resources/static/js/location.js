
$('document').ready(function () {

    $("#ddlCountryAdd").change(function(){
        var selectedCountryId = $(this).val();

        // Отправка запроса на получение регионов для выбранной страны
        $.get("/countries/parameters/country/" + selectedCountryId, function(regions){
            // Обновление раскрывающегося списка регионов
            var regionDropdown = $("#ddlRegionAdd");
            regionDropdown.empty();

            // Заполнение раскрывающегося списка регионов новыми значениями
            $.each(regions, function(index, region){
                regionDropdown.append($("<option>").text(region.name).val(region.id));
            });
        });
    });

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /locations/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (location, status) {
            $('#idEdit').val(location.id);
            $('#descriptionEdit').val(location.description);
            $('#cityEdit').val(location.city);
            $('#addressEdit').val(location.address);
            $('#ddlCountryEdit').val(location.countryid);
            $('#ddlRegionEdit').val(location.regionid);
            $('#detailsEdit').val(location.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (location, status) {
            $('#idDetails').val(location.id);
            $('#descriptionDetails').val(location.description);
            $('#cityDetails').val(location.city);
            $('#addressDetails').val(location.address);
            $('#ddlCountryDetails').val(location.countryid);
            $('#ddlStateDetails').val(location.regionid);
            $('#detailsDetails').val(location.details);
            $('#lastModifiedByDetails').val(location.lastModifiedBy);
            $('#lastModifiedDateDetails').val(location.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();     // Предотвращает поведение события (переход по ссылки)

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var locationAddress = $(this).data('address');   // Получаем description из data-атрибута


        $('#locationAddressSpan').text(locationAddress);  // Устанавливаем текст по id="countryDescriptionSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});