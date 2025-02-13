<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <link rel="stylesheet" href="css/style-registrazione.css">
    <link rel="icon" href="img/logo_circle.png" type="image/png">
</head>
<body>
<section class="header-container">
    <jsp:include page="fragment/header.jsp" />
</section>

<div class="container">
    <h2>REGISTRAZIONE</h2>
    <form id="registration-form" action="Register" method="post">
        <label for="nome">Nome</label>
        <input type="text" id="nome" name="nome" placeholder="Inserisci Nome" required>

        <label for="cognome">Cognome</label>
        <input type="text" id="cognome" name="cognome" placeholder="Inserisci Cognome" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="Inserisci Email" required>

        <label for="numero">Numero cellulare</label>
        <input type="tel" name="numero" id="numero" placeholder="Inserisci numero cellulare" required>
        
        <label for="date">Data di nascita</label>
        <input type="date" name="date" id="date" placeholder="Inserisci la data di nascita" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Inserisci Password" required>

        <label for="confirm-password">Conferma Password</label>
        <input type="password" id="confirm-password" name="confirm-password" placeholder="Conferma Password" required>
        <p id="error-message" style="color: red;"></p>
        <br>
        <input type="submit" value="Registrati">
        
        <p class="login">Hai gi&aacute; un account?<a href="login.jsp">Accedi</a></p>
    </form>
</div>
<section class="footer-container">
    <jsp:include page="fragment/footer.jsp" />
</section>

<script>
document.getElementById('registration-form').addEventListener('submit', function(event) {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirm-password').value;
    var passwordRegex = /^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?:{}|<>]).+$/;
    var errorMessage = document.getElementById('error-message');

    errorMessage.textContent = '';

    if (password !== confirmPassword) {
        errorMessage.textContent = 'Le password non corrispondono. Per favore, riprova.';
        event.preventDefault();
        return;
    }

    if (!passwordRegex.test(password)) {
        errorMessage.textContent = 'La password deve contenere almeno una lettera maiuscola e un carattere speciale.';
        event.preventDefault();
        return;
    }
});
</script>
</body>
</html>
