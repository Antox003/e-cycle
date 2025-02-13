<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.List, model.OrdiniBean, model.OrdiniDAODataSource, model.ProductBean, java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style-ordini.css">
    <meta charset="UTF-8">
    <title>E-Cycle | Ordini</title>
    <link rel="icon" href="img/logo_circle.png" type="image/png">
</head>
<body>
     <section class="header-container">
        <%@include file="fragment/header.jsp" %>
    </section>
    <div class="container-ordini">
        <div class="titoli">
         <br>
            <h3 class="titolo">I miei Ordini <img src="img/img-ordini.svg" class="img-h3"></h3>
            <%-- Visualizza il numero totale di ordini effettuati --%>
            <%
                OrdiniDAODataSource ordiniDAO = new OrdiniDAODataSource();
                List<OrdiniBean> ordiniList = null;
                try {
                    ordiniList = ordiniDAO.doRetrieveAll("DESC"); // "DESC" o "ASC" a seconda dell'ordine desiderato
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gestisci l'errore, ad esempio, mostrando un messaggio all'utente
                }
                int idAccountFromSession = (int) session.getAttribute("ID_ACCOUNT");
            %>
            <a class="n_ordini"> Ordini effettuati</a>
        </div>

        <% if (ordiniList != null && !ordiniList.isEmpty()) { %>
            <% for (OrdiniBean ordine : ordiniList) { %>
            <%if (ordine.getAccount() == idAccountFromSession){%>
                <div class="articoli-ordine">
                    <div class="riepilogo">
                        <h1 class="titolo-art"><img src="img/img-info.svg" class="img-h1"> Dettagli ordine</h1><br> 
                        <div class="dettagli">
                            <img class="immagine-prodotto" src="img/<%=ordine.getProdotto()%>.png"/>
                            <b><%=ordine.getProdotto()%></b>       
                        </div> 
                        <table class="tabella-utente">
                            <tr>
                                <th>Stato</th>
                                <td>Ordine effettuato</td>
                            </tr>
                            <tr>
                                <th>Prodotti</th>
                                <td><%= ordine.getNumeroprodotti() %></td>
                            </tr>
                            <tr>
                                <th>Data Acquisto</th>
                                <td><%= ordine.getDataacquisto() %></td>
                            </tr>
                           
                        </table>
                    </div>

                </div>
                <br>
                <br>
            <% }} %>
        <% } else { %>
            <p>Nessun ordine disponibile.</p>
        <% } %>
    </div>
    
    <section class="footer-container">
        <%@ include file="fragment/footer.jsp" %>
    </section>
</body>
</html>
