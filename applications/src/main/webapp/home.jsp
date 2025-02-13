<%@ page language="java" import="java.util.List, model.ProductBean" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Cycle | Home</title>
    <link rel="stylesheet" href="css/style-home.css">
    <link rel="icon" href="img/logo_circle.png" type="image/png">
</head>
<body>

    <section class="header-container">
        <%@include file="fragment/header.jsp" %>
    </section>

    <section id="prova">
    
    </section>

    <div id="riquadro">
        <br>
        <section id="barra_ricerca">
            <div class="container_strumenti">
                <input type="search" placeholder=" Cerca nel catalogo" id="search" oninput="searchProducts()">
                <button class="search-button" onclick="searchProducts()">
                    <img src="img/search.svg" alt="search">
                </button>
            </div>
        </section>

        <section id="tendenza">
            <h2>I nostri prodotti ></h2>

            <div class="container_card" id="product-list">
                <% List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList"); %>
                <% if (productList != null) { %>
                    <% for (ProductBean product : productList) { %>
                        <div class="card">
                            <img class="immagine-prodotto" src="img/<%= product.getNome() %>.png" alt="Immagine prodotto">
                            <h3 class="product-name"><%= product.getNome() %></h3>
                            <p class="product-description"><%= product.getDescrizione() %></p>
                            <br>
                            <div class="bottone-prodotto">
                                <button class="visualizza-prodotto"><a href="ProductDetail?ID_PRODOTTO=<%= product.getCode() %>">Vedi prodotto</a></button>
                                <h5 class="product-price"><%= product.getPrezzo() %> &#8364;</h5>
                            </div>
                        </div>
                    <% } %>
                <% } else { %>
                    <p>Nessun prodotto disponibile</p>
                <% } %>
            </div>
        </section>
    </div>
    
     <section class="footer-container">
     	<%@include file="fragment/footer.jsp" %>
    </section>
    
    <script>
        function searchProducts() {
            var input = document.getElementById('search').value.toLowerCase();
            var products = document.getElementsByClassName('card');
            
            if (input === "") {
                for (var i = 0; i < products.length; i++) {
                    products[i].style.display = "block";
                }
                return;
            }

            for (var i = 0; i < products.length; i++) {
                var productName = products[i].getElementsByClassName('product-name')[0].innerText.toLowerCase();
                var productDescription = products[i].getElementsByClassName('product-description')[0].innerText.toLowerCase();
                
                if (productName.includes(input) || productDescription.includes(input)) {
                    products[i].style.display = "block";
                } else {
                    products[i].style.display = "none";
                }
            }
        }
    </script>
</body>
</html>
