<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.UserBean,java.sql.SQLException,java.util.Enumeration ,java.util.List,model.ProductBean,model.ProductDAODataSource, model.OrdiniDAODataSource, model.OrdiniBean"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style-admin.css">
    <link rel="icon" href="img/logo_circle.png" type="image/png">
    <meta charset="UTF-8">
    <title>E-Cycle | Admin</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
</head>
<body>

<section class="header-container">
        <%@include file="fragment/header.jsp" %>
    </section>

    <div class="container-admin">
        <h3 class="titolo">Gestione Sito Web <img src="img/img-gestione.svg" class="img-h3"></h3>
        <div class="contenuto">
            <div class="container-nav">
                <nav class="nav">
                    <div class="nav-distanza-div">
                        <div id="nav-dashboard" class="nav-div contrassegno" onclick="showSection('dashboard', 'nav-dashboard')">
                            <img src="img/img-dashboard.svg" class="nav-img" alt="dashboard">
                            <h3>Dashboard</h3>
                        </div>
                        <div id="nav-catalogo" class="nav-div" onclick="showSection('catalogo', 'nav-catalogo')">
                            <img src="img/img-catalogo.svg" class="nav-img" alt="catalogo">
                            <h3>Catalogo</h3>
                        </div>
                        <div id="nav-ordini" class="nav-div" onclick="showSection('ordini', 'nav-ordini')">
                            <img src="img/img-ordini.svg" class="nav-img" alt="ordini">
                            <h3>Ordini</h3>
                        </div>
                        <div id="nav-utenti" class="nav-div" onclick="showSection('utenti', 'nav-utenti')">
                            <img src="img/img-utente.svg" class="nav-img" alt="utenti">
                            <h3>Utenti Registrati</h3>
                        </div>
                    </div>
                </nav>
            </div>

            <div class="main">
                <div id="dashboard" class="container-sezione">
                    <!-- Contenuto della sezione Dashboard -->
                    <p class="p_titolo"><b class="titolo_p"><em>Benvenuto</em></b><br>Qui puoi visualizzare una panoramica del sito.
                        <br>Esplora la barra di <b>navigazione laterale</b> per pi� funzioni!
                    </p>
                    <p class="p"> 
                        <b class="b"><img src="img/img-freccia.svg" class="img-p">Funzioni rapide </b>
                    </p>
                    <div class="container-riquadri">
                        <div class="riquadro" onclick="togglePopup('popup1')">
                            <div class="dettagli">
                                <h2 class="dett-3">Aggiungi articolo</h2>
                            </div>
                            <img src="img/img-aggiunta.svg" alt="articoli">
                        </div>

                        <div id="popup1" class="sfondo-popup"> 
                            <div class="container-popup"> 
                                <h2 class="h2-popup">Aggiungi un articolo al Catalogo</h2><br> 
                                <form class="container-form"> 
                                    <div class="selezione">
                                        <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup1')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
                                    </div>
                                    <br>
                                    <label class="form-label" for="nome">Nome (max 50 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci il nome" maxlength="50" id="nome" name="nome" required> 
                                    <label class="form-label" for="descrizione">Descrizione (max 500 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci la descrizione" maxlength="500" id="descrizione" name="descrizione" required> 
                                    <label class="form-label" for="immagine">Immagine:</label>
                                    <input class="form-input" type="file" id="immagine" name="immagine" accept="image/*" required>
                                    <label class="form-label" for="prezzo">Prezzo:</label> 
                                    <input class="form-input" type="number" placeholder="Inserisci il prezzo" id="prezzo" name="prezzo" min="0.01" max="100000" step="0.01" required>
                                    <hr><h4>Specifiche Prodotto (max 300 caratteri per campo)</h4><br>
                                    <label class="form-label" for="casa">Casa di produzione:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci casa di produzione" maxlength="300" id="casa" name="casa" required> 
                                    <label class="form-label" for="display">Display:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci display" maxlength="300" id="display" name="display" required> 
                                    <label class="form-label" for="fotocamera">Fotocamera:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci fotocamera" maxlength="300" id="fotocamera" name="fotocamera" required> 
                                    <label class="form-label" for="archiviazione">Archiviazione:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci archiviazione" maxlength="300" id="archiviazione" name="archiviazione" required> 
                                    <label class="form-label" for="chip">Chip:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci chip" id="chip" maxlength="300" name="chip" required> 
                                    <label class="form-label" for="sim">SIM:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci sim" id="sim" maxlength="300" name="sim" required> 
                                    <label class="form-label" for="bluetooth">Bluetooth:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci bluetooth" maxlength="300" id="bluetooth" name="bluetooth" required> 
                                    <label class="form-label" for="connettori">Connettori:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci connettori" maxlength="300" id="connettori" name="connettori" required> 
                                    <label class="form-label" for="rete">Rete:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci rete" id="rete" maxlength="300" name="rete" required> 
                                    <label class="form-label" for="batteria">Batteria:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci batteria" id="batteria" maxlength="300" name="batteria" required> 
                                    <label class="form-label" for="DimPes">Dimensione e Peso:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci dimensioni e peso" maxlength="300" id="DimPes" name="DimPes" required> 
                                    <label class="form-label" for="so">Sistema Operativo:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci sistema operativo" maxlength="300" id="so" name="so" required> 
                                    <label class="form-label" for="acqua">Resistenza all'acqua:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci resistenza all'acqua" maxlength="300" id="acqua" name="acqua" required> 
                                    <div class="selezione">
                                        <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup1')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
                                        <button class="button-submit" type="submit">Aggiungi</button> 
                                    </div>
                                </form> 
                            </div> 
                        </div>

                        <div class="riquadro" onclick="togglePopup('popup2')">
                            <div class="dettagli">
                                <h2 class="dett-3">Aggiungi admin</h2>
                            </div>
                            <img src="img/img-aggiunta_utente.svg" alt="ordini">
                        </div>
                        <div id="popup2" class="sfondo-popup"> 
                            <div class="container-popup"> 
                                <h2 class="h2-popup">Aggiungi un Admin al sito</h2><br> 
                                <form class="container-form"> 
                                    <label class="form-label" for="nome">Nome (max 20 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci il nome" maxlength="20" id="nome" name="nome" required> 
                                    <label class="form-label" for="cognome">Cognome (max 20 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci il cognome" maxlength="20" id="cognome" name="cognome" required> 
                                    <label class="form-label" for="data_nascita">Data di Nascita:</label> 
                                    <input class="form-input" type="date" placeholder="Inserisci data di nascita" id="data_nascita" name="data_nascita" required>
                                    <label class="form-label" for="telefono">Telefono (max 10 caratteri):</label> 
                                    <input class="form-input" type="tel" placeholder="0123456789" maxlength="10" id="telefono" name="telefono" required>  
                                    <label class="form-label" for="email">Email (max 30 caratteri):</label> 
                                    <input class="form-input" type="email" placeholder="Inserisci l'email" maxlength="30" id="email" name="email" required>
                                    <label class="form-label" for="password">Password (max 50 caratteri):</label> 
                                    <input class="form-input" type="password" placeholder="Inserisci la password" maxlength="50" id="password" name="password" required>      
                                    <div class="selezione">
                                        <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup2')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
                                        <button class="button-submit" type="submit">Aggiungi</button> 
                                    </div>
                                </form>
                            </div> 
                        </div>

                    </div>
                    
                    <p class="p">
                        <b class="b"><img src="img/img-freccia.svg" class="img-p">Statistiche </b>
                    </p>
                    <div class="container-riquadri">
                        <div class="riquadro">
                            <div class="dettagli" onclick="showSection('catalogo', 'nav-catalogo')">
                                <h2 class="dett-1" id="div_art">#</h2>
                                <h2 class="dett-2">Articoli in Vendita</h2>
                            </div>
                            <img src="img/img-telefono.svg" alt="articoli">
                        </div>
                        <div class="riquadro" onclick="showSection('ordini', 'nav-ordini')">
                            <div class="dettagli">
                                <h2 class="dett-1" id="div_ord">#</h2>
                                <h2 class="dett-2">Ordini Totali</h2>
                            </div>
                            <img src="img/img-camion.svg" alt="ordini">
                        </div>
                        <div class="riquadro" onclick="showSection('utenti', 'nav-utenti')">
                            <div class="dettagli">
                                <h2 class="dett-1" id="div_ute">#</h2>
                                <h2 class="dett-2">Utenti Registrati</h2>
                            </div>
                            <img src="img/img-utente.svg" alt="utenti">
                        </div>
                    </div>
                    <p class="p ordini-recenti"><br>
                        <b class="b"><img src="img/img-freccia.svg" class="img-p">Ordini Recenti </b>
                        <button class="button-dash" onclick="showSection('ordini', 'nav-ordini')">Visualizza tutti<img src="img/img-freccia.svg" class="img-button"></button>
                    </p>
                    <table class="tabella-utente">
                        <thead>
                            <tr><th>ID Ordine</th><th>Pagato</th><th>Ordinato da</th><th>Data Ordine</th></tr>
                        </thead>
                        <tbody>
                            <tr><td>ID1</td><td>10 &#8364</td><td>email1@gmail.com</td><td>2022-01-01</td></tr>
                            <tr><td>ID2</td><td>50 &#8364</td><td>email2@gmail.com</td><td>2023-02-15</td></tr>
                            <tr><td>ID3</td><td>10.99 &#8364</td><td>email3@gmail.com</td><td>2024-03-10</td></tr>
                        </tbody>
                    </table>

                </div>


                <div id="catalogo" class="container-sezione" style="display: none;">
                    <!-- Contenuto della sezione Catalogo -->
                    <p class="p"><b class="b"><img src="img/img-freccia.svg" class="img-p">Catalogo</b><br> Qui puoi visualizzare il catalogo del sito.<br>Puoi <b>aggiungere</b>, <b>eliminare</b> o <b>modificare</b> articoli o effettuare una <b>ricerca</b> per <b>parametri</b>.<br></p>
                    <div class="container-riquadri">
                        <div class="riquadro riquadro2">
                            <div class="dettagli" onclick="togglePopup('popup3')">
                                <h2 class="dett-3">Aggiungi articolo</h2>
                            </div>
                            <img src="img/img-aggiunta.svg" alt="articoli">
                        </div>
                        <div id="popup3" class="sfondo-popup"> 
                            <div class="container-popup"> 
                                <h2 class="h2-popup">Aggiungi un articolo al Catalogo</h2><br> 
                                <form class="container-form" action="NuovoProdotto" method="post"> 
                                    <div class="selezione">
                                        <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup3')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
                                    </div>
                                    <br>
                                     <input type="hidden" name="ID_PRODOTTO" value="">
                                    <label class="form-label" for="nome">Nome (max 50 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci il nome" maxlength="50" id="nome" name="nome" required> 
                                    <label class="form-label" for="descrizione">Descrizione (max 500 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci la descrizione" maxlength="500" id="descrizione" name="descrizione" required> 
                                   	<label class="form-label" for="immagine">Immagine:</label>
                                    <input class="form-input" type="file" id="immagine" name="immagine" accept="image/*" required>
                                    <label class="form-label" for="prezzo">Prezzo:</label> 
                                    <input class="form-input" type="number" placeholder="Inserisci il prezzo" id="prezzo" name="prezzo" min="0.01" max="10000" step="0.01" required>
                                    <hr><h4>Specifiche Prodotto (max 300 caratteri per campo)</h4><br>
                                    <label class="form-label" for="casa">Casa di produzione:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci casa di produzione" maxlength="300" id="casa" name="casa" required> 
                                    <label class="form-label" for="display">Display:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci display" maxlength="300" id="display" name="display" required> 
                                    <label class="form-label" for="fotocamera">Fotocamera:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci fotocamera" maxlength="300" id="fotocamera" name="fotocamera" required> 
                                    <label class="form-label" for="archiviazione">Archiviazione:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci archiviazione" maxlength="300" id="archiviazione" name="archiviazione" required> 
                                    <label class="form-label" for="archiviazione">Autenticazione:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci autenticazione" maxlength="300" id="autenticazione" name="autenticazione" required> 
                                    <label class="form-label" for="chip">Chip:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci chip" id="chip" maxlength="300" name="chip" required> 
                                    <label class="form-label" for="sim">SIM:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci sim" id="sim" maxlength="300" name="sim" required> 
                                    <label class="form-label" for="bluetooth">Bluetooth:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci bluetooth" maxlength="300" id="bluetooth" name="bluetooth" required> 
                                    <label class="form-label" for="connettori">Connettori:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci connettori" maxlength="300" id="connettori" name="connettori" required> 
                                    <label class="form-label" for="rete">Rete:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci rete" id="rete" maxlength="300" name="rete" required> 
                                    <label class="form-label" for="batteria">Batteria:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci batteria" id="batteria" maxlength="300" name="batteria" required> 
                                    <label class="form-label" for="DimPes">Dimensione e Peso:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci dimensioni e peso" maxlength="300" id="DimPes" name="DimPes" required> 
                                    <label class="form-label" for="so">Sistema Operativo:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci sistema operativo" maxlength="300" id="so" name="so" required> 
                                    <label class="form-label" for="acqua">Resistenza all'acqua:</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci resistenza all'acqua" maxlength="300" id="acqua" name="acqua" required> 
                                    <div class="selezione">
                                        <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup3')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
                                        <button class="button-submit" type="submit">Aggiungi</button> 
                                    </div>
                                </form>  
                            </div> 
                        </div>
                    </div>
                    <div class="container-ricerca">
                        <input type="text" id="searchCatalogo" onkeyup="searchTable('searchCatalogo', 'tableCatalogo', 'resultCatalogo')" placeholder="Cerca nel catalogo...">
                    </div>
                    <div id="resultCatalogo" class="risultati-ricerca"></div>
                     <table id="tableCatalogo" class="tabella-utente">
    <thead>
        <tr><th>ID Prodotto</th><th>Nome</th><th>Elimina</th><th>Modifica</th><th>Specifiche e Pagina Prodotto</th></tr>
    </thead>
    <tbody>
        <% 
        ProductDAODataSource productDAO = new ProductDAODataSource();
        List<ProductBean> productList = null;

        try {
            productList = productDAO.doRetrieveAll(null); // Recupera tutti i prodotti
        } catch (SQLException e) {
            e.printStackTrace(); // Stampa l'errore nella console (consigliato per il debug)
            out.println("<tr><td colspan='5'>Errore durante il recupero dei prodotti.</td></tr>"); // Messaggio di errore visibile nella tabella
        }

        if (productList != null) {
            for (ProductBean product : productList) {
        %>
        <tr>
            <td><%= product.getCode() %></td>
            <td><%= product.getNome() %></td>
            <td>
                
                <form action="EliminaProdotto" method="post" style="display:inline;">
                       <input type="hidden" name="idProdotto" value="<%= product.getCode() %>">
                         <button type="submit" class="eliminaBtn"> <img src="img/img-elimina.svg" class="img-tab"></button>
                   </form>
            </td>
            <td>
                <a href="AdminProductList?action=edit&id=<%= product.getCode() %>">Modifica</a>
                <img src="img/img-matita.svg" class="img-tab" onclick="togglePopup('popup6')">
            </td>
            <td>
                <a href="ProductDetail?ID_PRODOTTO=<%= product.getCode() %>">
                    <img src="img/img-dett_prodotto.svg" class="img-tab">
                </a>
            </td>
        </tr>
       <% 
       
            } 
        }
        %>
    </tbody>
</table>
                
                    <div id="popup6" class="sfondo-popup"> 
                        <div class="container-popup"> 
                            <h2 class="h2-popup">Modifica un articolo del Catalogo</h2><br> 
                           
                        
                           
                           
	<form class="container-form" id="productForm" method="post" action="ModificaProdotto"> 
        <div class="selezione">
            <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup6')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
        </div>
        <br>
  <% 
                ProductBean prodotto = (ProductBean) request.getAttribute("prodotto");
                if (prodotto == null) {
                    out.println("<p>Errore: Prodotto non trovato.</p>");
                } else {
            %>
        <input type="text" id="ID_PRODOTTO" name="ID_PRODOTTO" value="<%=prodotto.getCode()%>">

        <label class="form-label" for="nome">Nome (max 50 caratteri):</label> 
        <input class="form-input" type="text" placeholder="Inserisci il nome" maxlength="50" id="nome" name="nome"> 
        <label class="form-label" for="descrizione">Descrizione (max 500 caratteri):</label> 
        <input class="form-input" type="text" placeholder="Inserisci la descrizione" maxlength="500" id="descrizione" name="descrizione"> 
        <label class="form-label" for="prezzo">Prezzo:</label> 
        <input class="form-input" type="text" placeholder="Inserisci il prezzo" id="prezzo" name="prezzo">
        <hr><h4>Specifiche Prodotto (max 300 caratteri per campo)</h4><br>
        <label class="form-label" for="casa">Casa di produzione:</label> 
        <input class="form-input" type="text" placeholder="Inserisci casa di produzione" maxlength="300" id="casa" name="casa"> 
        <label class="form-label" for="display">Display:</label> 
        <input class="form-input" type="text" placeholder="Inserisci display" maxlength="300" id="display" name="display"> 
        <label class="form-label" for="fotocamera">Fotocamera:</label> 
        <input class="form-input" type="text" placeholder="Inserisci fotocamera" maxlength="300" id="fotocamera" name="fotocamera"> 
        <label class="form-label" for="archiviazione">Archiviazione:</label> 
        <input class="form-input" type="text" placeholder="Inserisci archiviazione" maxlength="300" id="archiviazione" name="archiviazione">
         <label class="form-label" for="archiviazione">Autenticazione:</label> 
        <input class="form-input" type="text" placeholder="Inserisci autenticazione" maxlength="300" id="autenticazione" name="autenticazione">  
        <label class="form-label" for="chip">Chip:</label> 
        <input class="form-input" type="text" placeholder="Inserisci chip" id="chip" maxlength="300" name="chip"> 
        <label class="form-label" for="sim">SIM:</label> 
        <input class="form-input" type="text" placeholder="Inserisci sim" id="sim" maxlength="300" name="sim"> 
        <label class="form-label" for="bluetooth">Bluetooth:</label> 
        <input class="form-input" type="text" placeholder="Inserisci bluetooth" maxlength="300" id="bluetooth" name="bluetooth"> 
        <label class="form-label" for="connettori">Connettori:</label> 
        <input class="form-input" type="text" placeholder="Inserisci connettori" maxlength="300" id="connettori" name="connettori"> 
        <label class="form-label" for="rete">Rete:</label> 
        <input class="form-input" type="text" placeholder="Inserisci rete" id="rete" maxlength="300" name="rete"> 
        <label class="form-label" for="batteria">Batteria:</label> 
        <input class="form-input" type="text" placeholder="Inserisci batteria" id="batteria" maxlength="300" name="batteria"> 
        <label class="form-label" for="DimPes">Dimensione e Peso:</label> 
        <input class="form-input" type="text" placeholder="Inserisci dimensioni e peso" maxlength="300" id="DimPes" name="DimPes"> 
        <label class="form-label" for="so">Sistema Operativo:</label> 
        <input class="form-input" type="text" placeholder="Inserisci sistema operativo" maxlength="300" id="so" name="so"> 
        <label class="form-label" for="acqua">Resistenza all'acqua:</label> 
        <input class="form-input" type="text" placeholder="Inserisci resistenza all'acqua" maxlength="300" id="acqua" name="acqua"> 
        <div class="selezione">
            <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup6')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
            <button class="button-submit" type="submit">Modifica</button> 
        </div>              
    </form>

<%} %>

						</div> 
                    </div>
                </div>
                
                
                <div id="ordini" class="container-sezione" style="display: none;">
                    <!-- Contenuto della sezione Ordini -->
                    <p class="p">
                        <b class="b"><img src="img/img-freccia.svg" class="img-p">Ordini</b><br> 
                        Qui puoi visualizzare tutti gli ordini effettuati dagli utenti.<br>
                        Puoi <b>eliminare</b> un ordine o<br> 
                        effettuare una <b>ricerca</b> per <b>parametri</b> o per <b>data</b> <em>(da data x a data y)</em>.<br>
                        Vai alla sezione <a class="link" onclick="showSection('utenti', 'nav-utenti')"><b><u>Utenti Registrati</u></b></a> per visualizzare l'indirizzo e spedire l'ordine al cliente.
                    </p>
                    <div class="container-ricerca">
                        <input type="text" id="searchOrdini" onkeyup="searchTable('searchOrdini', 'tableOrdini', 'resultOrdini')" placeholder="Cerca tra gli ordini...">
                        <input type="date" id="startDate" onchange="searchTable('searchOrdini', 'tableOrdini', 'resultOrdini', 'startDate', 'endDate')" placeholder="Data inizio">
                        <input type="date" id="endDate" onchange="searchTable('searchOrdini', 'tableOrdini', 'resultOrdini', 'startDate', 'endDate')" placeholder="Data fine">
                    </div>
                    <div id="resultOrdini" class="risultati-ricerca"></div>
                    <table id="tableOrdini" class="tabella-utente">
               
                        <thead>
                            <tr><th>ID Ordine</th><th>Nome prodotto</th><th>Pagato</th><th>Ordinato da</th><th>ID Indirizzo</th><th>Data Ordine</th><th>Elimina</th></tr>
                        </thead>
                        <tbody>
                             <%
                OrdiniDAODataSource ordiniDAO = new OrdiniDAODataSource();
                List<OrdiniBean> ordiniList = null;
                try {
                    ordiniList = ordiniDAO.doRetrieveAll("DESC"); // "DESC" o "ASC" a seconda dell'ordine desiderato
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gestisci l'errore, ad esempio, mostrando un messaggio all'utente
                }
            %>
              <% if (ordiniList != null && !ordiniList.isEmpty()) { %>
            <% for (OrdiniBean ordine : ordiniList) { %>
                            <tr><td><%=ordine.getCode()%></td><td><%=ordine.getProdotto()%></td><td>10 &#8364</td><td><%=ordine.getIndirizzo()%></td><td>1</td><td><%=ordine.getDataacquisto()%></td><td><img src="img/img-elimina.svg" class="img-tab"></td></tr>
                            <%}
              }
            %>
                        </tbody>
                    </table>
                </div>
                

                <div id="utenti" class="container-sezione" style="display: none;">
                    <!-- Contenuto della sezione Utenti -->
                    <p class="p">
                        <b class="b"><img src="img/img-freccia.svg" class="img-p">Utenti</b><br> 
                        Qui puoi visualizzare tutti gli utenti registrati al sito.<br>
                        Puoi <b>modificare</b> o <b>eliminare</b> utenti, <b>aggiungere</b> un <b>amministratore</b>,<br>  
                        <b>ricercare</b> un <b>indirizzo</b> di un profilo o effettuare una <b>ricerca</b> per <b>parametri</b>/per <b>ruolo</b>.
                    </p>                    
                    <div class="container-riquadri">
                        <div class="riquadro" onclick="togglePopup('popup4')">
                            <div class="dettagli">
                                <h2 class="dett-3">Aggiungi Admin</h2>
                            </div>
                            <img src="img/img-aggiunta_utente.svg" alt="ordini">
                        </div>
                        <div class="riquadro" onclick="togglePopup('popup5')">
                            <div class="dettagli">
                                <h2 class="dett-3">Indirizzi Utenti</h2>
                            </div>
                            <img src="img/img-casa.svg" alt="ordini">
                        </div>

                        <div id="popup4" class="sfondo-popup"> 
                            <div class="container-popup"> 
                                <h2 class="h2-popup">Aggiungi un Admin al sito</h2><br> 
                                <form class="container-form"> 
                                    <label class="form-label" for="nome">Nome (max 20 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci il nome" maxlength="20" id="nome" name="nome" required> 
                                    <label class="form-label" for="cognome">Cognome (max 20 caratteri):</label> 
                                    <input class="form-input" type="text" placeholder="Inserisci il cognome" maxlength="20" id="cognome" name="cognome" required> 
                                    <label class="form-label" for="data_nascita">Data di Nascita:</label> 
                                    <input class="form-input" type="date" placeholder="Inserisci data di nascita" id="data_nascita" name="data_nascita" required>
                                    <label class="form-label" for="telefono">Telefono (max 10 caratteri):</label> 
                                    <input class="form-input" type="tel" placeholder="0123456789" maxlength="10" id="telefono" name="telefono" required>  
                                    <label class="form-label" for="email">Email (max 30 caratteri):</label> 
                                    <input class="form-input" type="email" placeholder="Inserisci l'email" maxlength="30" id="email" name="email" required>
                                    <label class="form-label" for="password">Password (max 50 caratteri):</label> 
                                    <input class="form-input" type="password" placeholder="Inserisci la password" maxlength="50" id="password" name="password" required>      
                                    <div class="selezione">
                                        <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup4')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
                                        <button class="button-submit" type="submit">Aggiungi</button> 
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div id="popup5" class="sfondo-popup"> 
                            <div class="container-popup container-popup_ind"> 
                                <h2 class="h2-popup">Indirizzi degli Utenti</h2><br>
                                <p class="p">
                                    Qui puoi visualizzare tutti gli <b>indirizzi</b> degli <b>utenti</b> per effettuare una <b>spedizione</b> presso il loro <b>domicilio</b>.<br>
                                    Puoi effettuare una <b>ricerca</b> per <b>parametri</b>.
                                </p>
                                <div class="container-ricerca">
                                    <input type="text" id="searchIndirizzo" onkeyup="searchTable('searchIndirizzo', 'tableIndirizzo', 'resultIndirizzo')" placeholder="Cerca tra gli Indirizzi...">
                                </div>
                                <div id="resultIndirizzo" class="risultati-ricerca"></div>
                                <table id="tableIndirizzo" class="tabella-utente">
                                    <thead>
                                        <tr><th>ID Indirizzo</th><th>Email</th><th>Stato</th><th>Citta' e Provincia</th><th>CAP</th><th>Indirizzo completo</th></tr>
                                    </thead>
                                    <tbody>
                                        <tr><td>ID1</td><td>email1@gmail.com</td><td>Italia</td><td>Pratola Serra, AV</td><td>83039</td><td>Piazza Dei Petruzziello, 6</td></tr>
                                        <tr><td>ID2</td><td>email2@gmail.com</td><td>Italia</td><td>Mercogliano, AV</td><td>83013</td><td>Via 1234</td></tr>
                                        <tr><td>ID3</td><td>email3@gmail.com</td><td>Italia</td><td>Capriglia Irpina, AV</td><td>83010</td><td>Via 12345</td></tr>
                                    </tbody>
                                </table>                                                          
                                <div class="selezione">
                                    <button class="button-chiudi-popup" onclick="togglePopup('popup5')"><img src="img/img-freccia_indietro.svg" class="img-button">Indietro</button> 
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="container-ricerca">
                        <input type="text" id="searchUtenti" onkeyup="searchTable('searchUtenti', 'tableUtenti', 'resultUtenti')" placeholder="Cerca tra gli utenti...">
                        <select name="ruolo" class="ruolo_utenti" id="ruolo" onchange="searchTable('searchUtenti', 'tableUtenti', 'resultUtenti', 'ruolo')">
                            <option value="">Tutti</option>
                            <option value="Utente">Utente</option>
                            <option value="Amministratore">Amministratore</option>  
                        </select>                      
                    </div>
                    <div id="resultUtenti" class="risultati-ricerca"></div>
                    <table id="tableUtenti" class="tabella-utente">
                        <thead>
                            <tr><th>Nome</th><th>Cognome</th><th>Email</th><th>Telefono</th><th>Ruolo</th><th>Modifica</th><th>Elimina</th></tr>
                        </thead>
                        <tbody>
                            <tr><td>Gennaro</td><td>Sinicario</td><td>email1@gmail.com</td><td>123456789</td><td>Utente</td><td><img src="img/img-matita.svg" class="img-tab" onclick="togglePopup('popup7')"></td><td><img src="img/img-elimina.svg" class="img-tab"></td></tr>
                            <tr><td>Antonio</td><td>Margio</td><td>email2@gmail.com</td><td>123456789</td><td>Amministratore</td><td><img src="img/img-matita.svg" class="img-tab" onclick="togglePopup('popup7')"></td><td><img src="img/img-elimina.svg" class="img-tab"></td></tr>
                            <tr><td>Francesco</td><td>Gragnaniello</td><td>email3@gmail.com</td><td>123456789</td><td>Utente</td><td><img src="img/img-matita.svg" class="img-tab" onclick="togglePopup('popup7')"></td><td><img src="img/img-elimina.svg" class="img-tab"></td></tr>
                        </tbody>
                    </table>
                    <div id="popup7" class="sfondo-popup"> 
                        <div class="container-popup"> 
                            <h2 class="h2-popup">Modifica un Utente del sito</h2><br> 
                            <form class="container-form"> 
                                <label class="form-label" for="nome">Nome (max 20 caratteri):</label> 
                                <input class="form-input" type="text" placeholder="Inserisci il nome" maxlength="20" id="nome" name="nome"> 
                                <label class="form-label" for="cognome">Cognome (max 20 caratteri):</label> 
                                <input class="form-input" type="text" placeholder="Inserisci il cognome" maxlength="20" id="cognome" name="cognome"> 
                                <label class="form-label" for="data_nascita">Data di Nascita:</label> 
                                <input class="form-input" type="date" placeholder="Inserisci data di nascita" id="data_nascita" name="data_nascita">
                                <label class="form-label" for="telefono">Telefono (max 10 caratteri):</label> 
                                <input class="form-input" type="tel" placeholder="0123456789" maxlength="10" id="telefono" name="telefono">  
                                <label class="form-label" for="email">Email (max 30 caratteri):</label> 
                                <input class="form-input" type="email" placeholder="Inserisci l'email" maxlength="30" id="email" name="email">
                                <label class="form-label" for="password">Password (max 50 caratteri):</label> 
                                <input class="form-input" type="password" placeholder="Inserisci la password" maxlength="50" id="password" name="password">      
                                <label class="form-label" for="ruolo">Ruolo</label> 
                                <select class="form-input" name="ruolo">
                                    <option></option>
                                    <option value="Utente">Utente</option>
                                    <option value="Amministratore">Amministratore</option>  
                                </select> 
                                <div class="selezione">
                                    <button class="button-chiudi-popup" type="reset" onclick="togglePopup('popup7')"><img src="img/img-freccia_indietro.svg" class="img-button">Annulla</button> 
                                    <button class="button-submit" type="submit">Modifica</button> 
                                </div>
                            </form>
                        </div>
                </div>
                

            </div>
        </div>
    </div>
    <script>
        //CONTRASSEGNO_NAV
        function showSection(sectionId, navId){
            const sections = document.querySelectorAll('.container-sezione');
            sections.forEach(section => {
                section.style.display = 'none';
            });
            document.getElementById(sectionId).style.display = 'flex';
            const navOptions = document.querySelectorAll('.nav-div');
            navOptions.forEach(option => {
                option.classList.remove('contrassegno');
            });
            document.getElementById(navId).classList.add('contrassegno');
        }

        // RICERCA_TABELLA
        function searchTable(inputId, tableId, resultId, startDateId = null, endDateId = null){
            var input = document.getElementById(inputId);
            var filter = input.value.toLowerCase();
            var table = document.getElementById(tableId);
            var tr = table.getElementsByTagName('tr');
            var resultDiv = document.getElementById(resultId);
            var startDate = startDateId ? document.getElementById(startDateId).value : null;
            var endDate = endDateId ? document.getElementById(endDateId).value : null;
            var roleSelect = document.getElementById('ruolo');
            var roleFilter = roleSelect ? roleSelect.value.toLowerCase() : '';
            if(filter || roleFilter || startDate || endDate){
                resultDiv.innerHTML = '<b>Risultati della Ricerca:</b>';
            }else{
                resultDiv.innerHTML = '';
            }
            for(var i = 1; i < tr.length; i++){
                var td = tr[i].getElementsByTagName('td');
                var match = false;
                var roleMatch = true;
                var dateMatch = true;

                // Filtro per testo
                for(var j = 0; j < td.length; j++){
                    if(td[j] && td[j].innerHTML.toLowerCase().indexOf(filter) > -1){
                        match = true;
                    }
                }

                // Filtro per data
                if(startDate || endDate) {
                    var dateTd = tr[i].getElementsByTagName('td')[5];
                    if(dateTd) {
                        var itemDate = new Date(dateTd.innerHTML);
                        var start = startDate ? new Date(startDate) : null;
                        var end = endDate ? new Date(endDate) : null;
                        if((start && itemDate < start) || (end && itemDate > end)){
                            dateMatch = false;
                        }
                    }
                }

                // Filtro per ruolo
                if(roleFilter){
                    var roleTd = td[4];
                    if(roleTd){
                        if(roleTd.innerHTML.toLowerCase() !== roleFilter){
                            roleMatch = false;
                        }
                    }
                }

                // Condizione finale per mostrare/nascondere la riga
                if(match && dateMatch && roleMatch){
                    tr[i].style.display = '';
                }else{
                    tr[i].style.display = 'none';
                }
            }
        }

        //POPUP
        function togglePopup(popupID){
            const overlay = document.getElementById(popupID);
            if (overlay){
                overlay.classList.toggle('show');
            }
        }
        
        //CONTEGGIO_RIGHE
        let table1 = document.getElementById('tableCatalogo').getElementsByTagName('tbody')[0];
        let table2 = document.getElementById('tableOrdini').getElementsByTagName('tbody')[0];
        let table3 = document.getElementById('tableUtenti').getElementsByTagName('tbody')[0];
        let rowCount1 = table1.getElementsByTagName('tr').length;
        let rowCount2 = table2.getElementsByTagName('tr').length;
        let rowCount3 = table3.getElementsByTagName('tr').length;
        let risultatoH2_1 = document.getElementById('div_art');
        let risultatoH2_2 = document.getElementById('div_ord');
        let risultatoH2_3 = document.getElementById('div_ute');
        risultatoH2_1.textContent = rowCount1;
        risultatoH2_2.textContent = rowCount2;
        risultatoH2_3.textContent = rowCount3;
    </script>
</body>
</html>