# Navigazione Robot

*Autore: Marco Scanu*

## Obiettivo
Scrivere un programma che realizzi un algoritmo di navigazione utilizzando il simulatore ***GridWorld***. Il simulatore genera griglie bidimensionali con una disposizione casuale degli ostacoli. Il robot viene posizionato in una data cella di partenza e viene definita una cella obiettivo *target*. Scopo del programma è quello di individuare un percorso, se esiste, tra la cella di partenza e la cella *target*. <br>
Il programma deve considerare tre parametri per il costruttore di ***GridWorld***:
- La dimensione della griglia da generare (numero intero maggiore di 0);
- La densità degli ostacoli (un numero in virgola mobile tra 0 e 1 estremi esclusi);
- Il seme per la generazione casuale (un numero intero).

In particolare, il seme permette la rigenerazione di mondi con la stessa disposizione degli ostacoli a parità di densità. Il programma deve restituire il percorso individuato tra la cella di partenza e la cella *target*.

## Specifiche architetturali e funzionali
1) Il programma può essere composto da più di un file .java ma deve ***obbligatoriamente*** contenere una classe Main in cui è definito il metodo main. <br>
2) Il programma ***non deve*** essere organizzato in package. <br>
3) Il programma ***deve*** interagire con la classe GridWorld fornita a corredo dell’homework per la simulazione del robot. Non saranno accettate soluzioni realizzate utilizzando altri strumenti di simulazione. <br>

4) Il programma ***deve*** essere invocato tramite la linea di comando:

	java Main <dimensione_griglia> <densità_ostacoli> <seme>

dove: <br>
<dimensione_griglia> è un int strettamente maggiore di 0 che indica la dimensione della griglia (righe e colonne); <br>
<densità_ostacoli> è un double nell’intervallo (0 , 1) che indica la densità degli ostacoli (percentuale di celle “bloccate” per il robot); <br>
"seed" è un long che viene utilizzato come seme per la generazione casuale degli ostaocoli in modo da garantire la ripetibilità degli esperimenti.

5) Il programma deve fornire in uscita il percorso individuato o l’indicazione che non esiste un percorso.

### Formato di input
Il programma non prende ***nessun tipo di input*** se non quello specificato sopra per la linea di comando e
nelle modalità indicate.

### Formato di output
L’output del programma è una ***singola riga*** in console che contiene le seguenti informazioni: <br>
1) Nel caso in cui esista un percorso dalla cella iniziale al target, la riga ha la sintassi:

	Percorso: <cella_1> <cella_2> <cella_3> ...

dove:
- <cella_i> è una cella del percorso dalla posizione iniziale alla posizione obiettivo;
- il formato per <cella_i> è (riga, colonna);
- le celle sono visualizzate nella stessa sequenza a del percorso.

2) Nel caso in cui non esista un percorso dalla cella iniziale al target, la riga ha la sintassi:

	Nessun percorso!

Il programma non emette ***nessun altro tipo di output*** se non quello specificato.
