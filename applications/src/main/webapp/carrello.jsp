<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.CartBean, model.ProductBean, java.text.DecimalFormat, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style-carrello.css">
    <link rel="icon" href="img/logo_circle.png" type="image/png">
    <title>E-Cycle | Carrello</title>
</head>
<body>

<section class="header-container">
    <jsp:include page="fragment/header.jsp"/>
</section>

<div class="container-carrello">
    <div class="titoli">
        <h3 class="titolo">Carrello <img src="img/img-carrello.svg" class="img-h3"></h3>
        <h5 class="cancella-articoli"><a href="CartControl?action=svuotare">Svuota Carrello</a> <img src="img/img-cestino.png" class="img-h5"></h5>
    </div>
    <hr> 
	<br>
	<br>
    <div class="articoli-carrello">
        <%
            Collection<ProductBean> carrello = null;
            double somma = 0;
            DecimalFormat df = new DecimalFormat("0.00");

            if (session.getAttribute("carrello") != null) {
                CartBean cart = (CartBean) session.getAttribute("carrello");
                carrello = cart.getCarrello();
            }

            if (carrello != null && !carrello.isEmpty()) {
                for (ProductBean bean : carrello) {
                    Double price = bean.getPrezzo();
                    String prezzo = df.format(price);

                    Double tot = bean.getPrezzo() * bean.getQuantity();
                    String prezzoTot = df.format(tot);
                    somma += tot;
        %>
        <div class="articolo-carrello">
            <div class="box-immagine">
                <img src="img/<%=bean.getNome()%>.png" class="immagine-prodotto">
            </div>
            <div class="info-prodotto">
                <h1 class="titolo-prodotto"><%=bean.getNome()%></h1>
                <br>
                <h3 class="dettagli"><%=prezzo%> &euro;</h3>
            </div>
            <div class="quantita">
                <div class="button"><a href="CartControl?action=diminuisci&codice=<%=bean.getCode()%>"> - </a></div>
                <input type="number" value="<%=bean.getQuantity()%>" min="1" max="99" readonly>
                <div class="button"><a href="CartControl?action=aumenta&codice=<%=bean.getCode()%>"> + </a></div>
            </div>
            <div class="prezzo">
                <div class="ammontare"><%=prezzoTot%> &euro;</div>
                <div class="rimuovi"><a href="CartControl?action=rimuovere&codice=<%=bean.getCode()%>">Rimuovi</a></div>
            </div>
            <br>
        </div>
        <br>
        <%
                }
            }
        %>
    </div>
    <br>
    <%
        String totale = df.format(somma);
        if (carrello != null && !carrello.isEmpty()) {
    %>
    <br>
    <hr>
    <br>
    <div class="checkout">
        <div class="totale">
            <div>
                <div class="subtotale">Totale</div>
            </div>
            <div class="costo-totale"><%=totale%> &euro;</div>
        </div>
        
        <button class="pulsante"> <a href="CartControl?action=paga"> Paga ora </a></button>
        <br>
    </div>
    <% } else { %>
    <div class="total-price" style="margin-top: 20px; margin-bottom: 500px">
        <table>
            <tr>
                <td>Carrello vuoto! Inserisci un prodotto per continuare</td>
            </tr>
        </table>
    </div>
    <% } %>
</div>

<section class="footer-container">
    <jsp:include page="fragment/footer.jsp"/>
</section>

</body>
</html>
