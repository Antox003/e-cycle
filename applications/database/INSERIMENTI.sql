-- Inserisci l'utente
INSERT INTO UserAccount (Nome, Cognome, Data_nascita, Email, Password, Telefono, ruolo) 
VALUES ('Antonio', 'Margio', '09-07-2003', 'antonio@gmail.com', '482c811da5d5b4bc6d497ffa98491e38', '1234567890', 'admin');
INSERT INTO UserAccount (Nome, Cognome, Data_nascita, Email, Password, Telefono, ruolo) 
VALUES ('Ciro', 'Esposito', '01-01-1999', 'ciro@gmail.com', '482c811da5d5b4bc6d497ffa98491e38', '1234567890', 'registeredUser');


INSERT INTO SPEDIZIONE (Stato, Provincia, Citta, Cap, Indirizzo, Civico, ID_ACCOUNT) 
VALUES ('Italia', 'AV', 'Avellino', '83100', 'Via Giacomo', '37', 1);
INSERT INTO SPEDIZIONE (Stato, Provincia, Citta, Cap, Indirizzo, Civico, ID_ACCOUNT) 
VALUES ('Italia', 'NA', 'Napoli', '83100', 'Via GinoPio', '93', 2);


INSERT INTO PAGAMENTO (Tipo_carta, Numero_carta, Data_scadenza, ID_ACCOUNT) 
VALUES ('MasterCard', '1234567891234567', '0000-00-00', 1);
INSERT INTO PAGAMENTO (Tipo_carta, Numero_carta, Data_scadenza, ID_ACCOUNT) 
VALUES ('Visa', '123456765234567', '0000-00-00', 2);


INSERT INTO ORDINI (Numero_Prodotti, Data_acquisto, ID_ACCOUNT, Nome_prodotto, ID_INDIRIZZO, Numero_carta) 
VALUES (1, '12-02-2025', 1, 'Xiaomi 14', 1, '1234567891234567');

INSERT INTO PRODOTTO (Nome, Descrizione, Casa, Prezzo, Display, Fotocamera, Archiviazione, Autenticazione, Chip, SIM, Bluetooth, Connettori, Rete, Batteria, DimPes, SO, Acqua)
VALUES (
    'iPhone 12', 
    'Acquista iPhone 12 rigenerato e risparmia senza compromettere la qualità. Con display Super Retina XDR, chip A14 Bionic e doppia fotocamera da 12 MP, offre prestazioni eccellenti a un prezzo accessibile. Ogni unità è accuratamente testata e garantita per funzionare come nuova. Scegli sostenibilità e valore con iPhone 12 rigenerato!', 
    'Apple', 
	279.99, 
    'Display OLED Super Retina da 6.1 con HDR e True Tone', 
    'Doppia fotocamera da 12 MP con stabilizzazione ottica dell\'immagine, modalità ritratto e illuminazione, video 4K a 1080p', 
    '128gb', 
    'Proteggi la tua privacy con il riconoscimento facciale', 
    'Chip A14 Bionic, estremamente potente e intelligente', 
    'SIM & eSIM', 
    '5.0', 
    'Connettore Lightning', 
    '5G / LTE, Wi-Fi', 
    'Li-ion 2815 mAh, ricarica senza fili, compatibile con caricabatterie Qi',
    '146.7 x 71.5 x 7.4 mm / 164 g', 
    'Goditi iOS 18, il miglior sistema operativo mai sviluppato da Apple', 
    'A causa del processo di ricondizionamento non possiamo garantire che la certificazione IP68 rimanga valida.'
);

INSERT INTO PRODOTTO (Nome, Descrizione, Casa, Prezzo, Display, Fotocamera, Archiviazione, Autenticazione, Chip, SIM, Bluetooth, Connettori, Rete, Batteria, DimPes, SO, Acqua)
VALUES (
    'Xiaomi 14', 
    'Acquista Xiaomi 14 rigenerato e risparmia senza compromettere la qualità. Con display AMOLED da 6.5 pollici ad alta risoluzione e colori vividi, processore Snapdragon 888+ e fotocamera avanzata da 108 MP, offre prestazioni eccellenti a un prezzo accessibile. Ogni unità è accuratamente testata e garantita per funzionare come nuova. Scegli sostenibilità e valore con Xiaomi 14 rigenerato!', 
    'Xiaomi', 
	299.99, 
    'Display AMOLED da 6.5 pollici con risoluzione HD+ e supporto HDR', 
    'Fotocamera principale da 108 MP con stabilizzazione ottica dell\'immagine, zoom ottico 5x e registrazione video 8K a 30fps', 
    '256 GB', 
    'Proteggi la tua privacy con il riconoscimento facciale e il sensore di impronte digitali integrato nello schermo', 
    'Snapdragon 888+, estremamente potente e efficiente', 
    'Dual SIM (nano-SIM e eSIM)', 
    'Bluetooth 5.2', 
    'USB Type-C', 
    '5G / LTE, Wi-Fi 6', 
    'Batteria al litio da 4500 mAh, ricarica rapida a 65W e ricarica wireless', 
    '157.2 x 74.6 x 8.1 mm / 185 g', 
    'Basato su MIUI 15, con funzionalità avanzate e aggiornamenti regolari', 
    'A causa del processo di ricondizionamento non possiamo garantire la certificazione IP68 originale.'
);
INSERT INTO PRODOTTO (Nome, Descrizione, Casa, Prezzo, Display, Fotocamera, Archiviazione, Autenticazione, Chip, SIM, Bluetooth, Connettori, Rete, Batteria, DimPes, SO, Acqua)
VALUES (
    'Samsung S23 Ultra', 
    'Acquista Samsung Galaxy S23 Ultra rigenerato e risparmia senza compromettere la qualità. Con display Dynamic AMOLED 2X da 6.8 pollici con risoluzione Quad HD+ e colori vibranti, processore Snapdragon 8 Gen 2 e fotocamera avanzata con sistema quad-camera da 200 MP, offre prestazioni eccellenti a un prezzo accessibile. Ogni unità è accuratamente testata e garantita per funzionare come nuova. Scegli sostenibilità e valore con Samsung Galaxy S23 Ultra rigenerato!', 
    'Samsung', 
	329.99, 
    'Display Dynamic AMOLED 2X da 6.8 pollici con risoluzione Quad HD+ e supporto HDR10+', 
    'Sistema quad-camera principale da 200 MP con stabilizzazione ottica dell\'immagine, zoom ottico 10x e registrazione video 8K a 60fps', 
    '512 GB', 
    'Proteggi la tua privacy con il riconoscimento facciale, il lettore di impronte digitali ultrasonico e la tecnologia di scansione dell\'iride', 
    'Snapdragon 8 Gen 2, estremamente potente e efficiente', 
    'Dual SIM (nano-SIM e eSIM)', 
    'Bluetooth 5.3', 
    'USB Type-C', 
    '5G / LTE, Wi-Fi 6', 
    'Batteria al litio da 5000 mAh, ricarica rapida a 100W e ricarica wireless', 
    '165.1 x 76.3 x 8.5 mm / 215 g', 
    'Basato su One UI 7, con funzionalità avanzate e aggiornamenti regolari', 
    'A causa del processo di ricondizionamento non possiamo garantire la certificazione IP68 originale.'
);
INSERT INTO PRODOTTO (Nome, Descrizione, Casa, Prezzo, Display, Fotocamera, Archiviazione, Autenticazione, Chip, SIM, Bluetooth, Connettori, Rete, Batteria, DimPes, SO, Acqua)
VALUES (
    'MacBook Air M1', 
    'Acquista MacBook Air M1 rigenerato e risparmia senza compromettere la qualità. Con display Retina da 13.3 pollici con tecnologia True Tone e colori vividi, chip Apple M1 e storage SSD fino a 512 GB, offre prestazioni eccellenti a un prezzo accessibile. Ogni unità è accuratamente testata e garantita per funzionare come nuova. Scegli sostenibilità e valore con MacBook Air M1 rigenerato!', 
    'Apple', 
	999.99, 
    'Display Retina da 13.3 pollici con retroilluminazione a LED, risoluzione 2560x1600, True Tone', 
    'Web cam HD 1080p 30fps', 
    'SSD fino a 512 GB', 
    'Proteggi la tua privacy con il Touch ID integrato nel tastiera', 
    'Chip Apple M1, estremamente potente e efficiente', 
    'Non applicabile', 
    'Bluetooth 5.0', 
    'Due porte Thunderbolt 4 / USB 4, jack per cuffie da 3.5 mm', 
    'Wi-Fi 6, Bluetooth 5.0', 
    'Batteria integrata da 49.9 wattora, fino a 18 ore di riproduzione video', 
    '304.1 x 212.4 x 16.1 mm / 1.29 kg', 
    'macOS Monterey, con funzionalità avanzate e aggiornamenti regolari', 
    'Non applicabile'
);
INSERT INTO PRODOTTO (Nome, Descrizione, Casa, Prezzo, Display, Fotocamera, Archiviazione, Autenticazione, Chip, SIM, Bluetooth, Connettori, Rete, Batteria, DimPes, SO, Acqua)
VALUES (
    'Surface Pro 9', 
    'Acquista Microsoft Surface Pro 9 rigenerato e risparmia senza compromettere la qualità. Con display PixelSense da 13.5 pollici con risoluzione 4K e colori vibranti, processore Intel Core i7 di undicesima generazione e fotocamera avanzata con sistema dual-camera da 16 MP, offre prestazioni eccellenti a un prezzo accessibile. Ogni unità è accuratamente testata e garantita per funzionare come nuova. Scegli sostenibilità e valore con Microsoft Surface Pro 9 rigenerato!', 
    'Microsoft', 
	699.99, 
    'Display PixelSense da 13.5 pollici con risoluzione 4K e supporto HDR', 
    'Sistema dual-camera principale da 16 MP con autofocus avanzato e registrazione video 4K a 30fps', 
    '1 TB SSD', 
    'Proteggi la tua privacy con il riconoscimento facciale Windows Hello e il lettore di impronte digitali integrato nel tastiera', 
    'Intel Core i7 di undicesima generazione, potente e efficiente', 
    'Non applicabile', 
    'Bluetooth 5.0', 
    'Porta USB-C, Surface Connect, jack per cuffie da 3.5 mm', 
    'Wi-Fi 6, LTE opzionale', 
    'Batteria al litio da 60 Wh, fino a 11 ore di autonomia', 
    '292 x 201 x 8.5 mm / 790 g', 
    'Windows 11 Pro, con funzionalità avanzate e aggiornamenti regolari', 
    'Non applicabile'
);

INSERT INTO PRODOTTO (Nome, Descrizione, Casa, Prezzo, Display, Fotocamera, Archiviazione, Autenticazione, Chip, SIM, Bluetooth, Connettori, Rete, Batteria, DimPes, SO, Acqua)
VALUES (
    'Google Pixel 8', 
    'Acquista Google Pixel 8 rigenerato e risparmia senza compromettere la qualità. Con display OLED da 6.3 pollici con risoluzione Full HD+ e colori vividi, processore Snapdragon 8 Gen 2 e fotocamera avanzata con sistema dual-camera da 50 MP, offre prestazioni eccellenti a un prezzo accessibile. Ogni unità è accuratamente testata e garantita per funzionare come nuova. Scegli sostenibilità e valore con Google Pixel 8 rigenerato!', 
    'Google', 
	499.99, 
    'Display OLED da 6.3 pollici con risoluzione Full HD+ e supporto HDR', 
    'Sistema dual-camera principale da 50 MP con stabilizzazione ottica dell\'immagine, zoom ottico 5x e registrazione video 4K a 60fps', 
    '256 GB', 
    'Proteggi la tua privacy con il riconoscimento facciale e il sensore di impronte digitali integrato nel retro del dispositivo', 
    'Snapdragon 8 Gen 2, estremamente potente e efficiente', 
    'SIM & eSIM', 
    'Bluetooth 5.2', 
    'USB Type-C', 
    '5G / LTE, Wi-Fi 6', 
    'Batteria al litio da 4500 mAh, ricarica rapida a 30W e ricarica wireless', 
    '156.4 x 73.1 x 8.9 mm / 185 g', 
    'Android 15, con funzionalità avanzate e aggiornamenti regolari', 
    'IP68, resistente a polvere e acqua fino a 1.5 metri per 30 minuti'
);