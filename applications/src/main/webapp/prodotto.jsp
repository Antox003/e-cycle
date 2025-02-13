<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="model.ProductBean,java.util.List "%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Prodotto</title>
    <link rel="stylesheet" href="css/style-prodotto.css">
    <link rel="icon" href="img/logo_circle.png" type="image/png">
</head>
<body>

<section class="header-container">
    <jsp:include page="fragment/header.jsp" />
</section>

<section id="riquadro">
    <div class="container-max">
        <div class="container-prodotto">
		<% ProductBean product = (ProductBean) request.getAttribute("product"); %>
            <% if (product != null) { %>
                <div class="container-img">
                    <div class="big">
                        <img src="img/<%=product.getNome()%>.png" alt="">
                    </div>
                </div>
                
                 <div class="descrizione">
                    <h5><%= product.getCasa() %></h5>
                    <h1><%= product.getNome() %></h1>
                    <p>
                        <%= product.getDescrizione() %>
                    </p>            
                    <h3><%= product.getPrezzo() %>&#8364</h3>

                    <div class="container-button">
                        <button class="buy">
								<a href="PagaOra?action=paga&productId=<%= product.getCode() %>&quantity=1&productName=<%= product.getNome() %>">
    Paga ora
</a>

                        </button>
    					 <%if (session.getAttribute("registeredUser") == null) { %>
                       
                        <% } else {%>
                        <button class="cart">
                           <a href="CartControl?action=aggiungi&codice=<%= product.getCode() %>">
                                <img src="img/cart-add.svg" alt="">Aggiungi al carrello
                            </a>
                        </button>
                        <%} %>
                    </div>
                </div>
            </div>

            <section class="info-spedizione">
                <div class="info">
                    <img src="img/truck.svg" alt="">
                    <br>
                    <br>
                    <h4>Spedizione rapida</h4>
                    <br>
                    <p>Riceverai il tuo dispositivo entro 1-3 giorni lavorativi</p>
                </div>
                <br>
                <div class="info">
                    <img src="img/safe-lock.svg" alt="">
                    <br>
                    <br>
                    <h4>Pagamenti sicuri</h4>
                    <br>
                    <p>Utilizziamo solamente i pi&uacute; affidabili servizi di pagamento</p>
                </div>
                <br>
                <div class="info">
                    <img src="img/tick.svg" alt="">
                    <br>
                    <br>
                    <h4>Garanzia e reso</h4>
                    <br>
                    <p>12 mesi di Garanzia Swappie e reso gratuito entro 14 giorni</p>
                </div>
                
            </section>
            <br>
            <br>
            <hr>
            <br>
            <br>
            <section class="table">

                <div class="gray">
                    <div class="specifiche">
                        <h4>Display</h4>
                    </div>
                    <div class="specifiche">
                        <p><%= product.getDisplay() %></p>
                    </div>
                </div>
                <div class="white" >
                    <div class="specifiche">
                        <h4>Fotocamera</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getFotocamera() %></p>
                    </div>
                </div>
                <div class="gray">
                    <div class="specifiche">
                        <h4>Spazio archiviazione</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getArchiviazione() %></p>
                    </div>
                </div>
                <div class="white">
                    <div class="specifiche">
                        <h4>Autenticazione sicura</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getAutenticazione() %></p>
                    </div>   
                </div>
                <div class="gray" >
                    <div class="specifiche">
                        <h4>Chip</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getChip() %></p>
                    </div>
                </div>
                <div class="white">
                    <div class="specifiche">
                        <h4>Scheda SIM</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getSIM() %></p>
                    </div>
                </div>
                <div class="gray" >
                    <div class="specifiche">
                        <h4>Bluetooth</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getBluetooth() %></p>
                    </div>
                </div>
                <div class="white">
                    <div class="specifiche">
                        <h4>Connettori</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getConnettori() %></p>
                    </div>
                </div>
                <div class="gray" >
                    <div class="specifiche">
                        <h4>Rete</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getRete() %>i</p>
                    </div>
                </div>
                <div class="white">
                    <div class="specifiche">
                        <h4>Batteria</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getBatteria() %></p>
                    </div> 
                </div>
                <div class="gray" >
                    <div class="specifiche">
                        <h4>Dimensioni e peso</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getDimPes() %></p>
                    </div>
                </div>
                <div class="white">
                    <div class="specifiche">
                        <h4>Sistema operativo</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getSO() %></p>
                    </div>
                </div>
                <div class="grays" >
                    <div class="specifiche">
                        <h4>Resistenza all'acqua</h4>
                    </div>
                    <div class="specifiche">
                        <p class="paragrafo"><%= product.getAcqua() %></p>
                    </div>
                
                
                
            <% } else { %>
                <p>Prodotto non trovato.</p>
            <% } %>
        </div>
    </div>
</section>

<section class="footer-container">
    <jsp:include page="fragment/footer.jsp" />
</section>

</body>
</html>
