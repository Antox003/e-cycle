<%@ page language="java"  import="java.util.List" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Cycle | Login</title>
    <link rel="stylesheet" href="css/style-login.css">
    <link rel="icon" href="img/logo_circle.png" type="image/png">
</head>
<body>
	
	<section class="header-container>">
     	<%@include file="fragment/header.jsp" %>
    </section>
    

    <section class="login-form">
        <div class="container">
            <form action="Login" method="post">
            <h2>LOGIN</h2>
                <label for="email">Email</label>
                <br>
                <input type="email" name="email" id="email" placeholder=" Inserisci il tuo username">
                <br>
                <% 
                    List<String> errors = (List<String>) request.getAttribute("errors");
                    if (errors != null){
                        for (String error: errors){ 
                            if (error.contains("email")) { %>
                                <span style="color: red;"><%= error %></span><br>
                            <% }
                        }
                    }
                %>
                <br>
                <label for="password">Password</label>
                <br>
                <input type="password" name="password"  id="password" placeholder=" Inserisci la tua password">
                <br>
                <% 
                    if (errors != null){
                        for (String error: errors){ 
                            if (error.contains("password")) { %>
                                <span style="color: red;"><%= error %></span><br>
                            <% }
                        }
                    }
                %>
                <br>
                <div class="button-container">
                    <input type="submit" id="button" value="Accedi">
                    <p>Non hai un account? <a href="registrazione.jsp">Registrati</a></p>
                </div>
                <br>
                <% 
                    if (errors != null){
                        for (String error: errors){ 
                            if (!error.contains("email") && !error.contains("password")) { %>
                                <span style="color: red;"><%= error %></span><br>
                            <% }
                        }
                    }
                %>
            </form>
        </div>
    </section>
    <br>
    <section class="footer-container">
     	<%@include file="fragment/footer.jsp" %>
    </section>
    

</body>
</html>