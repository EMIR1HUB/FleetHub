
$('document').ready(function(){
    var password = document.getElementById("password")
    var confirmPassword = document.getElementById("confirmPassword");

    function validatePassword(){
        if(password.value != confirmPassword.value) {
            confirmPassword.setCustomValidity("Пароли не совпадают");
        } else {
            confirmPassword.setCustomValidity('');
        }
    }
    password.onchange = validatePassword;
    confirmPassword.onkeyup = validatePassword;
});

// Функция для переключения видимости конкретных полей ввода
function togglePasswordInputs() {
    // Получаем специфические поля ввода по ID
    var passwordInput = document.getElementById('password');
    var confirmPasswordInput = document.getElementById('confirmPassword');

    // Проверяем состояние чекбокса
    if (!document.getElementById('toggleInputVisibility').checked) {
        // Если чекбокс отмечен, удаляем readonly и disabled
        passwordInput.setAttribute("readonly", "");
        passwordInput.setAttribute("disabled", "");

        confirmPasswordInput.setAttribute("readonly", "");
        confirmPasswordInput.setAttribute("disabled", "");
    } else {
        // В противном случае добавляем readonly и disabled обратно
        passwordInput.removeAttribute("readonly");
        passwordInput.removeAttribute("disabled");

        confirmPasswordInput.removeAttribute("readonly");
        confirmPasswordInput.removeAttribute("disabled");
    }
}