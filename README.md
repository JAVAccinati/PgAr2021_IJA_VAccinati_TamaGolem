# PgAr2021_IJA_VAccinati_TamaGolem

## Spiegazione generazione grafo

### 1
Per generare il grafico siamo partiti dalla considerazione per cui una matrice in cui tutti gli elementi sono uguali
a zero è teoricamente in equilibrio.
A questo punto avevamo allora bisogno di trovare una operazione che modificasse qualcuno dei valori, ma si comportasse
da invariante, ovvero conservasse la proprieta' di equilibrio.

Prima di illustrare il processo utilizzato e' necessario che capiate come tutti i "percorsi" possibili all'interno della 
matrice sono rappresentati nel triangolo superiore.

<div style="text-align: center"><img src="corrispondenza_percorsi_triangolo_superiore.jpg" height="300"></div>

Il percorso in blu nell'immagine corrisponde a quello mezzo rosso e mezzo blu, dove gli elementi che giacciono sul tratto 
rosso sono moltiplicati per -1.

A questo punto possiamo affermare come per ciascun punto passino esattemente due percorsi.

<div style="text-align: center"><img src="due_percorsi_possibili.jpg" height="300"></div>

Fatta questa premessa introduciamo la proprieta' che ci ha permesso di risolvere il problema.
Prendiamo 3 punti a caso nel triangolo superiore detti 1,2 e 3 in modo tale che per questi punti passino solo 3 percorsi:
A e B per 1, B e C per 2 e C e A per 3.
Abbiamo cosi' creato un percorso chiuso, un triangolo i cui vertici sono gli elementi selezionati.
Come si puo' vedere in figura, modificando con criterio solo questi tre valori, i tre percorsi rimangono bilanciati.

<div style="text-align: center"><img src="funzionamento_triangolo.jpg" height="300"></div>

Abbiamo cosi' trovato un modo per modificare liberamente e a piacimento i valori della matrice senza rovinarne l'equilibrio.  
Sfruttando questa proprieta' possiamo generare il grafo.

###
Per inizializzare in pratica la matrice selezioniamo allora un elemento a caso nel triangolo superiore
e da questo generiamo un triangolo di cui modificheremo i vertici.

La generazione dei triangoli avviene in maniera diversa in relazione a dove si trova nella matrice
l'elemento estratto e i vari processi verrano spiegati in seguito.

<div style="text-align: center"><img src="divisione_zone_interne_esterne_angolo.jpg" height="300"></div>

N.B. Il quadrato rosso, ovv
###



### 

<div style="text-align: center"><img src="generazione_triangolo_caso_interno.jpg" height="300"></div>

### 6

<div style="text-align: center"><img src="generazione_triangolo_caso_esterno.jpg" height="300"></div>

## Not flexing just sayin'

<img src="25x25.png" height="200">

<img src="vinter_ci_ama.png" height="200">
