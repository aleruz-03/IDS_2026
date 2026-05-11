# Appunti HackHub

# Casi d'Uso

## Membro dello Staff

Attori coinvolti:

- Organizzatore
- Mentore
- Giudice

Casi d'uso comuni:

- Consultare l'elenco degli hackathon
- Accedere alle sottomissioni dei team, solo per gli hackathon a cui il membro dello staff è assegnato

---

## Organizzatore

L'Organizzatore può:

- Creare un hackathon specificando:
  - nome
  - regolamento
  - scadenza iscrizioni
  - data di inizio
  - data di fine
  - luogo
  - premio in denaro
  - dimensione massima del team
  - un Giudice
  - uno o più Mentori
- Aggiungere più Mentori anche successivamente alla creazione dell'hackathon
- Proclamare un solo team vincitore
- Gestire le segnalazioni ricevute dai Mentori

---

## Mentore

Il Mentore può:

- Visualizzare le richieste di supporto inviate dai team
- Gestire una richiesta di supporto
- Proporre una call tramite un servizio Calendar esterno
- Segnalare un team all'Organizzatore in caso di violazione del regolamento

Attore secondario:

- Calendar esterno, utilizzato per la gestione delle call

Nota:

- La proposta di una call può essere inserita come sequenza alternativa nel caso d'uso "Gestire richiesta di supporto".

---

## Giudice

Il Giudice può:

- Visualizzare le sottomissioni dei team
- Rilasciare una valutazione per ciascuna sottomissione, composta da:
  - breve giudizio scritto
  - punteggio numerico compreso tra 0 e 10

---

## Membro del Team

Il Membro del Team può:

- Consultare tutti gli hackathon
- Iscrivere il proprio team a un hackathon
- Inviare una sottomissione entro la scadenza prevista
- Aggiornare una sottomissione entro la scadenza prevista
- Creare una richiesta di supporto

---

## Utente Registrato

L'Utente registrato può:

- Creare un nuovo team
- Invitare altri utenti a unirsi al team
- Accettare un invito a unirsi a un team

Vincolo:

- In qualunque momento un utente può appartenere a un solo team.

---

## Visitatore

Il Visitatore è un utente non autenticato.

Il Visitatore può:

- Consultare le informazioni pubbliche presenti sul sito
- Registrarsi alla piattaforma
- Effettuare l'accesso

---

## Servizi Esterni

### Calendar

Servizio esterno utilizzato per la pianificazione e gestione delle call tra Mentore e Team.

### Sistema di Pagamento

Servizio esterno utilizzato per l'erogazione del premio in denaro al team vincitore.

---

# Design Pattern Possibili:

- ## Factory Method

Utilizzabile per la creazione dei diversi tipi di membri dello staff:

- Organizzatore
- Mentore
- Giudice

- ## Builder

Utilizzabile per la creazione di un hackathon, dato che richiede molti parametri:

- nome
- regolamento
- scadenze
- date
- luogo
- premio
- dimensione massima del team
- staff assegnato

- ## State

Utilizzabile per gestire lo stato dell'hackathon.

Stati possibili:

- In iscrizione
- In corso
- In valutazione
- Concluso

- ## Adapter

Utilizzabile per integrare servizi esterni con interfacce diverse, ad esempio:

- servizi Calendar
- sistemi di pagamento

- ## Proxy

Utilizzabile per il controllo degli accessi, ad esempio per verificare che un membro dello staff possa accedere solo alle sottomissioni degli hackathon a cui è assegnato.

---

# Entità

Le principali entità del sistema sono:

- Utente
- Hackathon
- Team
- InvitoTeam
- Sottomissione
- Valutazione
- RichiestaSupporto
- PropostaCall
- Segnalazione
- PagamentoPremio