<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/style-check.css">
    <link rel="icon" href="img/logo_circle.png" type="image/png">
</head>
<body>

    <section class="header-container">
        <%@include file="fragment/header.jsp" %>
   </section>

    <section id="check">
        <div class="container">
            <img src="img/tick.png" alt="TICK">
            <br>
            <br>
            <h1 class="title">Ordine effettuato con successo</h1>
            <br>
            <button>
                <a href="home">Torna alla home</a>
            </button>
        </div>
        </div>
    </section>

    <section class="footer-container">
        <%@include file="fragment/footer.jsp" %>
   </section>

</body>
</html>