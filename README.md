# HackHub

Piattaforma web per la gestione di hackathon.

Il progetto mira a creare un sistema che permetta l’organizzazione e la gestione completa di hackathon, eventi a cui partecipano team di utenti registrati. La piattaforma consente agli organizzatori di creare nuovi hackathon, definendone le informazioni principali come nome, regolamento, date, luogo, premio, dimensione massima dei team e membri dello staff assegnati.

Gli utenti possono registrarsi alla piattaforma, creare o unirsi a un team e iscrivere il proprio team agli hackathon disponibili. Durante lo svolgimento dell’evento, i membri del team possono inviare una sottomissione e aggiornarla entro la scadenza prevista.

La piattaforma supporta inoltre il lavoro dello staff: i mentori possono assistere i team tramite richieste di supporto e proporre call attraverso un servizio Calendar esterno, mentre i giudici possono valutare le sottomissioni assegnando un giudizio scritto e un punteggio numerico. Al termine della valutazione, l’organizzatore proclama il team vincitore e il premio viene erogato tramite un sistema di pagamento esterno.

Anche i visitatori non autenticati possono accedere alla piattaforma per consultare le informazioni pubbliche sugli hackathon.

# Vincoli e Dettagli Tecnici

Il progetto deve essere sviluppato in Java e successivamente portato su Spring Boot.

Lo strato di presentazione può essere sviluppato con strumenti a scelta dello studente ed eventualmente può limitarsi alla linea di comando e/o API REST.

Si devono utilizzare almeno due design pattern diversi dal Singleton.