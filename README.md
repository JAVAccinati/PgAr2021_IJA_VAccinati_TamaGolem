# PgAr2021_IJA_VAccinati_TamaGolem

## Spiegazione generazione grafo

### 1
Per generare il grafico siamo partiti dalla considerazione per cui una matrice in cui tutti gli elementi sono uguali
a zero è teoricamente in equilibrio.
A questo punto avevamo allora bisogno di trovare una operazione che modificasse qualcuno dei valori, ma si comportasse
da invariante, ovvero conservasse la proprieta' di equilibrio.

Prima di illustrare il processo utilizzato e' necessario che capiate come tutti i "percorsi" possibili all'interno della 
matrice sono rappresentati nel triangolo superiore.

<img src="corrispondenza_percorsi_triangolo_superiore.jpg" height="300">

Il percorso in blu nell'immagine corrisponde a quello mezzo rosso e mezzo blu, dove gli elementi che giacciono sul tratto 
rosso sono moltiplicati per -1.

A questo punto possiamo affermare come per ciascun punto passino esattemente due percorsi.

<img src="due_percorsi_possibili.jpg" height="300">

Fatta questa premessa introduciamo la proprieta' che ci ha permesso di risolvere il problema.
Prendiamo 3 punti a caso nel triangolo superiore detti 1,2 e 3 in modo tale che per questi punti passino solo 3 percorsi:
A e B per 1, B e C per 2 e C e A per 3.
Abbiamo cosi' creato un percorso chiuso, un triangolo i cui vertici sono gli elementi selezionati.
Come si puo' vedere in figura, modificando con criterio solo questi tre valori, i tre percorsi rimangono bilanciati.

<img src="funzionamento_triangolo.jpg" height="300">

Abbiamo cosi' trovato un modo per modificare liberamente e a piacimento i valori della matrice senza rovinarne l'equilibrio.  
Sfruttando questa propriet
###

<img src="divisione_zone_interne_esterne_angolo.jpg" height="300">

###



### 

<img src="generazione_triangolo_caso_interno.jpg" height="300" style="text-align:center">

### 6

<img src="generazione_triangolo_caso_esterno.jpg" height="300">

## Not flexing just sayin'

<img src="25x25.png" height="200">

<img src="vinter_ci_ama.png" height="200">
