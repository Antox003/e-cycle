DROP DATABASE IF EXISTS ecycle;
CREATE DATABASE ecycle;
USE ecycle;

-- Eliminare e ricreare la tabella USERACCOUNT
DROP TABLE IF EXISTS UserAccount;
CREATE TABLE IF NOT EXISTS UserAccount (
    ID_ACCOUNT int NOT NULL AUTO_INCREMENT,
    Nome varchar(20) NOT NULL, 
    Cognome varchar(20) NOT NULL,
    Data_nascita varchar(20) NOT NULL,
    Email varchar(30) NOT NULL,
    password varchar(50) NOT NULL, 
    Telefono varchar(10) NOT NULL, 
    ruolo varchar(16) NOT NULL,
PRIMARY KEY (ID_ACCOUNT)
);

-- Eliminare e ricreare la tabella SPEDIZIONE
DROP TABLE IF EXISTS SPEDIZIONE;
CREATE TABLE IF NOT EXISTS SPEDIZIONE (
    ID_INDIRIZZO int NOT NULL AUTO_INCREMENT,
    Stato varchar(20) NOT NULL,
    Provincia varchar(15) NOT NULL,
    Citta varchar(40) NOT NULL,
    Cap varchar(5) NOT NULL,
    Indirizzo varchar(50) NOT NULL,
    Civico varchar(5) NOT NULL, 
    ID_ACCOUNT int NOT NULL,
PRIMARY KEY (ID_INDIRIZZO),
FOREIGN KEY (ID_ACCOUNT) REFERENCES UserAccount(ID_ACCOUNT) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Eliminare e ricreare la tabella PAGAMENTO
DROP TABLE IF EXISTS PAGAMENTO;
CREATE TABLE IF NOT EXISTS PAGAMENTO (
    Tipo_carta varchar(40) NOT NULL,
    Numero_carta varchar(16) NOT NULL, 
    Data_scadenza varchar(20) NOT NULL,
    ID_ACCOUNT int NOT NULL,
PRIMARY KEY (Numero_carta),
FOREIGN KEY (ID_ACCOUNT) REFERENCES UserAccount(ID_ACCOUNT) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Eliminare e ricreare la tabella PRODOTTO
DROP TABLE IF EXISTS PRODOTTO;
CREATE TABLE IF NOT EXISTS PRODOTTO (
    ID_PRODOTTO int NOT NULL AUTO_INCREMENT,
    Nome varchar(50) NOT NULL,
    Descrizione varchar(500) NOT NULL,
    Casa varchar(10) NOT NULL,
    Prezzo DOUBLE NOT NULL,
    Display varchar(300) NOT NULL,
    Fotocamera varchar(300) NOT NULL,
    Archiviazione varchar(300) NOT NULL,
    Autenticazione varchar(300) NOT NULL,
	Chip varchar(300) NOT NULL,
    SIM varchar(300) NOT NULL,
    Bluetooth varchar(300) NOT NULL,
    Connettori varchar(300) NOT NULL,
    Rete varchar(300) NOT NULL,
    Batteria varchar(300) NOT NULL,
    DimPes varchar(300) NOT NULL,
    SO varchar(300) NOT NULL,
    Acqua varchar(300) NOT NULL,
PRIMARY KEY (ID_PRODOTTO)
);

DROP TABLE IF EXISTS ORDINI;
CREATE TABLE IF NOT EXISTS ORDINI (
    ID_ORDINE int NOT NULL AUTO_INCREMENT,
    Numero_Prodotti int NOT NULL,
    Data_acquisto varchar(15) NOT NULL,
    ID_ACCOUNT int NOT NULL,
    Nome_prodotto varchar(50) NOT NULL,
    ID_INDIRIZZO int,
    Numero_carta varchar(16),
    PRIMARY KEY (ID_ORDINE),
    FOREIGN KEY (ID_ACCOUNT) REFERENCES UserAccount(ID_ACCOUNT) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (ID_INDIRIZZO) REFERENCES SPEDIZIONE(ID_INDIRIZZO) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Numero_carta) REFERENCES PAGAMENTO(Numero_carta) ON UPDATE CASCADE ON DELETE CASCADE
);