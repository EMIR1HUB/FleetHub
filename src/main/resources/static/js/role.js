
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)

        // /roles/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (role, status) {
            $('#idEdit').val(role.id);
            $('#descriptionEdit').val(role.description);
            $('#detailsEdit').val(role.details);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');
        $.get(href, function (role, status) {
            $('#idDetails').val(role.id);
            $('#descriptionDetails').val(role.description);
            $('#detailsDetails').val(role.details);
            $('#createdByDetails').val(role.createdBy);
            $('#createdDateDetails').val(role.createdDate.substr(0,19).replace("T", " "));
            $('#lastModifiedByDetails').val(role.lastModifiedBy);
            $('#lastModifiedDateDetails').val(role.lastModifiedDate.substr(0, 19).replace("T", " "))
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var roleName = $(this).data('name');   // Получаем name из data-атрибута

        $('#roleNameSpan').text(roleName);  // Устанавливаем текст по id="roleNameSpan"
        // Выбирает элемент с id "confirmDeleteButton" и устанавливает новое значение атрибута "href". Полученному в предыдущей строке.
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });
});