<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style-header.css">

</head>
<body>
	<header>
        <div class="cont-bar">
            <a href="#home"><img class="logo" src="img/img-logo.png" alt="Logo"></a>
            <input class="side-menu" type="checkbox" id="side-menu"/>
            <label class="hamb" for="side-menu">
                <span class="hamb-line"></span>
            </label>
            <nav>
                <ul class="menu">
                    <li><a href="home"><img src="img/img-casa.svg" class="img-nav" alt="Home Icon"><span class="nav-testo">Home</span></a></li>
                    <li><a href="carrello.jsp"><img src="img/img-carrello.svg" class="img-nav" alt="Carrello Icon"><span class="nav-testo">Carrello</span></a></li>
                     <%
                     UserBean user = (UserBean) session.getAttribute("registeredUser");
                     if (user != null && "admin".equals(user.getRole())) { %>
                    <li><a href="admin.jsp"><img src="img/img-gestione.svg" class="img-nav" alt="Gestione Icon"><span class="nav-testo">Gestisci</span></a></li>
                    <%} %>                    
                    <%if (session.getAttribute("registeredUser") == null) { %>
                    	<button class="btn"><a href="login.jsp">Login</a></button>
                    <%}else{ %>
                    <li class="menu-profilo">
                        <a><img src="img/img-utente.svg" class="img-nav" alt="Profilo Icon"><span class="nav-testo">Profilo<img src="img/img-frecciagiu.svg" class="freccia-giu"></span></a>
                        <ul class="sottomenu">
                            <li><a href="profilo"><img src="img/img-settaggi.svg" class="img-nav" alt="Settaggi Icon">Profilo</a></li>
                            <li><a href="ordini.jsp"><img src="img/img-ordini.svg" class="img-nav" alt="Ordini Icon">Ordini</a></li>
                            <li><a class="logout" href="Logout"><img src="img/img-logout.svg" class="img-nav" alt="Logout Icon">Esci</a></li>
                        </ul>
                        <%} %>

                    </li>
                </ul>
            </nav>
        </div>
    </header>
</body>
</html>