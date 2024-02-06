
$('document').ready(function () {

    $('.table #editButton').on('click', function (event) {
        event.preventDefault(); // Предотвращает поведение события (переход по ссылки)
        // /employees/getById/?id=1
        var href = $(this).attr('href');
        $.get(href, function (employee, status) {
            $('#txtIdEdit').val(employee.id);
            $('#txtPassportEdit').val(employee.passport);
            $('#txtUsernameEdit').val(employee.username);
            $('#txtInitialsEdit').val(employee.initials);
            $('#txtFirstnameEdit').val(employee.firstname);
            $('#txtLastnameEdit').val(employee.lastname);
            $('#txtOthernameEdit').val(employee.othername);
            $('#ddlGenderEdit').val(employee.gender);
            $('#ddlNationalityEdit').val(employee.countryid);
            $('#txtAddressEdit').val(employee.address);
            $('#txtDateOfBirthEdit').val(employee.dateOfBirth.substr(0, 10));
            $('#txtHireDateEdit').val(employee.hireDate.substr(0, 10));
            $('#ddlRegionEdit').val(employee.regionid);
            $('#txtCityEdit').val(employee.city);
            $('#txtPhoneEdit').val(employee.phone);
            $('#ddlMaritalStatusEdit').val(employee.maritalStatus);
            $('#txtEmailEdit').val(employee.email);
            $('#ddlJobTitleEdit').val(employee.jobtitleid);
            $('#ddlEmployeeTypeEdit').val(employee.employeetypeid);
            $('#fupPhotoEdit').val(employee.photo);
        });
        $('#editModal').modal();
    });

    $('.table #detailsButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        $.get(href, function (employee, status) {
            $('#txtUsernameDetails').val(employee.username);
            $('#txtIdDetails').val(employee.id);
            $('#txtInitialsDetails').val(employee.initials);
            $('#txtFirstnameDetails').val(employee.firstname);
            $('#txtLastnameDetails').val(employee.lastname);
            $('#txtOthernameDetails').val(employee.othername);
            $('#ddlGenderDetails').val(employee.gender);
            $('#ddlNationalityDetails').val(employee.countryid);
            $('#txtAddressDetails').val(employee.address);
            $('#txtDateOfBirthDetails').val(employee.dateOfBirth.substr(0, 10));
            $('#txtHireDateDetails').val(employee.hireDate.substr(0, 10));
            $('#ddlRegionDetails').val(employee.regionid);
            $('#txtCityDetails').val(employee.city);
            $('#txtPhoneDetails').val(employee.phone);
            $('#ddlMaritalStatusDetails').val(employee.maritalStatus);
            $('#txtEmailDetails').val(employee.email);
            $('#ddlJobTitleDetails').val(employee.jobtitleid);
            $('#ddlEmployeeTypeDetails').val(employee.employeetypeid);
            $('#createdByDetails').val(employee.createdBy);
            $('#createdDateDetails').val(employee.createdDate.substr(0,19).replace("T", " "));
            $('#lastModifiedByDetails').val(employee.lastModifiedBy);
            $('#lastModifiedDateDetails').val(employee.lastModifiedDate.substr(0, 19).replace("T", " "));
        });
        $('#detailsModal').modal(); // запуск модального окна
    });

    $('.table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');      // Получаем значение "href" (URL), текущего элемента, на котором произошло событие
        var employeeFullName = $(this).data('name');      // Получаем name из data-атрибута

        $('#employeeFullNameSpan').text(employeeFullName);
        $('#confirmDeleteButton').attr('href', href);
        $('#deleteModal').modal();  // запуск модального окна
    });

    $('.table #photoButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#employeePhoto').attr('src', href);
        $('#photoModal').modal();
    });
});