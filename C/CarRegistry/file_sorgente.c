/* inclusione delle librerie */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

/* definizione delle costanti simboliche */

#define MAX_VEICOLO 7           	/* numero massimo di caratteri del campo veicolo */
#define MAX_PROPRIETARIO 7          /* numero massimo di caratteri del campo proprietario */
#define MAX_MODELLO 7               /* numero massimo di caratteri del campo modello */
#define MAX_IMM 20              	/* numero massimo di caratteri del campo anno d'immatricolazione */


/* dichiarazione struttura dinamica */
struct nodo{
char veicolo[MAX_VEICOLO];
char proprietario[MAX_PROPRIETARIO];
char modello [MAX_MODELLO];
int immatricolazione;
struct nodo *next;
};
typedef struct nodo nodo;

/* dichiarazioni delle funzioni */

nodo *cerca_in_lista(nodo *testa, char valore[], int *contatore);

nodo *leggiLista(void);

int inserisci_in_lista(nodo **testa, nodo inserimento, int *contatore);

int rimuovi_da_lista(nodo **testa, char valore1[], int *contatore);

void stampaLista(nodo *p);

nodo max(nodo *testa, int *contatore);

nodo min(nodo *testa, int *contatore);

char menu(void);

void genera_Veicoli_Random();


/* definizione della funzione main */

int main() {

    nodo *esito_ricerca,        /* output: variabile indicante il successo o meno della ricerca */
         *testa,                /* output: variabile indicante la testa della lista */
         inserimento,           /* output: variabile indicante il successo o meno dell'inserimento */
         massimo,               /* output: variabile indicante il massimo lessicografico*/
         minimo;                /* output: variabile indicante il minimo lessicografico */

    char valore[MAX_VEICOLO];       /* lavoro: variabile d'appoggio */

    char esito_menu;               /* input: valore indicante la scelta del menù espressa dall'utente */

    int esito_inserimento;         /* variabile indicante il risultato del successo o meno dell'algoritmo di inserimento */

    int esito_rimozione;           /* variabile indicante il risultato del successo o meno dell'algoritmo di rimozione */

    int contaInserimento = 0;      /* variabile contatore di passi per l'algoritmo di inserimento */

    int contaRimozione = 0;        /* variabile contatore di passi per l'algoritmo di rimozione */

    int contaCerca = 0;            /* variabile contatore di passi per l'algoritmo di ricerca */

    int contaMax = 0;              /* variabile contatore di passi per l'algoritmo di ricerca del massimo */

    int contaMin = 0;              /* variabile contatore di passi per l'algoritmo di ricerca del minimo */

    genera_Veicoli_Random();

    testa = leggiLista();

    fflush(stdin);      /* permette la pulizia del buffer di tastiera */

    esito_menu = menu();

    switch (esito_menu) {
      case '1':
    printf("Che veicolo vuoi cercare?\n");
    scanf("%s", valore);

    esito_ricerca = cerca_in_lista(testa, valore, &contaCerca);

        if (esito_ricerca != NULL)
        {
        printf("elemento trovato\n\n%s\t%s\t%s\t%d\n", esito_ricerca->veicolo,
                                                       esito_ricerca->proprietario,
                                                       esito_ricerca->modello,
                                                       esito_ricerca->immatricolazione);
        }
        else
        printf("elemento non trovato\n\n");
    break;
      case '2':
    printf("Inserire il campo nome del veicolo (massimo 6 caratteri)\n");
    scanf("%s", inserimento.veicolo);
    printf("Inserire il campo proprietario (massimo 6 caratteri)\n");
    scanf("%s", inserimento.proprietario);
    printf("Inserire il campo modello (massimo 6 caratteri)\n");
    scanf("%s", inserimento.modello);
    printf("Inserire l'anno di immatricolazione\n");
    scanf("%d", &inserimento.immatricolazione);

    esito_inserimento = inserisci_in_lista(&testa, inserimento, &contaInserimento);
        if (esito_inserimento != 0)
            printf("elemento inserito\n\n");
        else
            printf("elemento non inserito\n\n");
    break;
      case'3':
        printf("che veicolo vuoi rimuovere?");
        scanf("%s", valore);

    esito_rimozione = rimuovi_da_lista(&testa, valore, &contaRimozione);

        if (esito_rimozione != 0)
            printf("elemento rimosso\n\n");
        else
            printf("elemento non rimosso\n\n");
    break;
    case '4':
    massimo = max(testa, &contaMax);
    printf("il massimo e' %s\n\n" , massimo.veicolo);
    break;
    case '5':
    minimo = min(testa, &contaMin);
    printf("il minimo e' %s\n" , minimo.veicolo);
    break;
    default:
        break;
    }

    printf("Il numero di passi dell'algoritmo di inserimento e' %d\n", contaInserimento);
    printf("Il numero di passi dell'algoritmo di rimozione e' %d\n", contaRimozione);
    printf("Il numero di passi dell'algoritmo di ricerca e' %d\n", contaCerca);
    printf("Il numero di passi dell'algoritmo del massimo in ordine lessicografico e' %d\n", contaMax);
    printf("Il numero di passi dell'algoritmo del minimo in ordine lessicografico e' %d\n", contaMin);

	return (0);
}

/* stampare in output il menù di scelta */
char menu(void) {

    char c;             /* input: numero indicante la scelta dell'utente */
    int esito_lettura;  /* lavoro: variabile d'appoggio */

    do
    {
        printf("\nInserire un nunero: \n");
        printf("'1' *****Ricerca un veicolo*****\n");
        printf("'2' *****Inserimento di un veicolo e relativi attributi*****\n");
        printf("'3' *****Rimozione di un veicolo*****\n");
        printf("'4' *****Valore piu' grande in ordine lessicografico*****\n");
        printf("'5' *****Valore piu' piccolo in ordine lessicografico*****\n");

          esito_lettura = scanf("%c",
                                &c);

          if(esito_lettura !=1  ||  (c != '1' && c!= '2' && c != '3' && c!= '4' && c!= '5'))
          {
              printf("Inserire un input valido (solo un numero da 1 a 5)\n");
          }
          while (getchar() !='\n');

    } while (esito_lettura != 1 || (c != '1' && c!= '2' && c != '3' && c!= '4' && c!= '5'));

return c;
}


/* acquisire file e memorizzarlo in una struttura dinamica */
nodo *leggiLista(void) {

    nodo *p,            /* lavoro: variabile appoggio per creare nodi lista */
         *testa=NULL,   /* ouput: testa della lista */
         *ultimo=NULL;  /* ultimo nodo della lista */

    char veicolo[MAX_VEICOLO];              /* lavoro: variabile d'appoggio */
    char proprietario[MAX_PROPRIETARIO];    /* lavoro: variabile d'appoggio */
    char modello [MAX_MODELLO];             /* lavoro: variabile d'appoggio */
    int anno;                                  /* lavoro: variabile d'appoggio */

    FILE *in;       /* variabile di tipo puntatore al file */

    in = fopen("dati.txt", "r");

    if ((in = fopen ("dati.txt", "r")) == NULL)
    {
        printf("Non e' presente alcun elemento nel file fornito dall'utente");
    }
    else
        {

        while (fscanf(in, "%8s %8s %8s %d", veicolo,
                                            proprietario,
                                            modello,
                                            &anno)==4)
        {
        p=(nodo *)malloc(sizeof(nodo));
        strcpy(p->veicolo, veicolo);
        strcpy(p->proprietario, proprietario);
        strcpy(p->modello, modello);
        p->immatricolazione = anno;
        p->next = NULL;
            if(ultimo != NULL)
                {
		ultimo->next = p;
		}
                ultimo =p;
            if(testa ==NULL)
                testa = p;
        }
    }
    fclose(in);

    return(testa);
}

/* ricerca e restituisce i dati relativi ai veicoli */
nodo *cerca_in_lista(nodo *testa,
                     char valore[],
                     int *contaCerca)
{
nodo *p;        /* output: variabile indicante il nodo cercato */

    for(p = testa, (*contaCerca)++;
      ((p != NULL) && (strcmp(p->veicolo,valore) != 0));
       p=p->next, (*contaCerca)+=2)
       {
       }

return(p);
}

/* funzione che implementa l'algoritmo di inserimento di un nodo veicolo all'interno della lista */

int inserisci_in_lista(nodo **testa,
                       nodo inserimento,
                       int *contaInserimento) {

int inserito;   /* output: variabile che indica il successo o meno dell'inserimento*/
nodo *corr,     /* lavoro: variabile d'appoggio per lo scorrimento dei puntatori*/
     *prec,     /* lavoro: variabile d'appoggio per lo scorrimento dei puntatori*/
     *nuovo;    /* variabile indicante il nuovo nodo inserito dall'utente*/

for(corr = prec = *testa, (*contaInserimento)++;
    (corr != NULL) && (corr->veicolo > inserimento.veicolo);
    prec = corr, corr = corr->next, (*contaInserimento)+=2)
	{

    if((corr != NULL) &&(corr->veicolo == inserimento.veicolo))
    {
            inserito = 0;
            (*contaInserimento)++;
    }
      else{
            inserito = 1;
            nuovo = (nodo *)malloc(sizeof(nodo));
            strcpy(nuovo->veicolo, inserimento.veicolo);
            strcpy(nuovo->proprietario, inserimento.proprietario);
            strcpy(nuovo->modello, inserimento.modello);
            nuovo->immatricolazione = inserimento.immatricolazione;
            nuovo->next = corr;
            (*contaInserimento)+=7;
                if(corr == *testa)
                {
                   (*contaInserimento)++;
                    *testa = nuovo;
                }
                else
                {
                    prec->next = nuovo;
                   (*contaInserimento)++;
                }

      }
    (*contaInserimento)++;
}
return(inserito);
}

/* funzione che implementa l'algoritmo di rimozione di un nodo veicolo all'interno della lista */

int rimuovi_da_lista(nodo **testa,
                    char valore1[],
                    int *contaRimozione)
{
int rimosso;     /* output: variabile indicante il successo o meno della rimozione */
    nodo *corr,  /* lavoro: variabile d'appoggio per lo scorrimento dei puntatori */
         *prec;  /* lavoro: variabile d'appoggio per lo scorrimento dei puntatori */

        for(corr = prec = *testa, (*contaRimozione)++;
            ((corr != NULL) && (strcmp(corr->veicolo,valore1)) != 0);
            prec = corr, corr = corr->next, (*contaRimozione)+=2);
        if ((corr == NULL) || (strcmp(corr->veicolo,valore1)) != 0)
        {
            rimosso = 0;
            (*contaRimozione)++;
        }
        else
        {
            rimosso = 1;
            (*contaRimozione)++;
                if(corr == *testa)
                {
                  *testa = corr->next;
                  (*contaRimozione)+=2;
                }
                else
                {
                prec->next = corr->next;
                free(corr);
                (*contaRimozione)+=3;
                }
        }
    return(rimosso);
}

/* funzione che implementa l'algoritmo di ricerca del massimo in ordine lessicografico */

nodo max(nodo *testa,
         		int *contaMax)
{
    nodo *p,       /* lavoro: variabile d'appoggio */
          max,     /* output: massimo definitivo */
          temp;    /* lavoro: variabile d'appoggio per il confronto tra il massimo temporaneo ed elemento corrente */

    strcpy(temp.veicolo,"zzzzzz"); /* inizializzazione della variabile che indica il massimo temporaneo */

for(p = testa, (*contaMax)++;
    (p != NULL);
    p=p->next, (*contaMax+=2))
    {
        (*contaMax)++;

        if(strcmp(p->veicolo, temp.veicolo) < 0)
        {
        strcpy(max.veicolo,p->veicolo);
        (*contaMax) += 2;
        }
    }

return(max);
}

/* funzione che implementa l'algoritmo di ricerca del minimo in ordine lessicografico */

nodo min(nodo *testa,
         		int *contaMin)
{
    nodo *p,           /* output: minimo definitvo */
          min,         /* lavoro: variabile che esprime il minimo temporaneo */
          temp;        /* lavoro: variabile d'appoggio per il confronto tra minimo tetmporaneo ed elemento corrente */

    strcpy(temp.veicolo,"AAAAAA");  /* inizializzazione della variabile che indica il minimo provvisorio */

for(p = testa, (*contaMin)++;
    (p != NULL);
    p=p->next, (*contaMin)+=2)
    {
        (*contaMin)++;

        if(strcmp(p->veicolo, temp.veicolo) > 0)
        {
            strcpy(min.veicolo,p->veicolo);

            (*contaMin) += 2;
        }
    }

return(min);
}

/* funzione che genera un numero di nodi veicolo pseudocasuali */

void genera_Veicoli_Random()
{
    FILE *in;       /* input: variabile puntatore al file */
    int m = 0,      /* lavoro: variabile contatre per scorrere il numero dei veicoli randomici */
        n,          /* lavoro: variabile indicante il massimo dei veicoli randomici da generare */
        x,          /* lavoro: varibile contatore per generare campo veicolo pseudocasuale */
        y,          /* lavoro: varibile contatore per generare campo proprieario pseudocasuale */
        z,          /* lavoro: varibile contatore per generare campo modello pseudocasuale */
        a,          /* lavoro: varibile contatore per generare campo anno d'immatricolazione pseudocasuale */
        b[4];       /* output: variabile campo veicolo */
    char i[6],      /* output: variabile campo proprietario */
         j[6],      /* output: variabile campo modello */
         k[6];      /* output: variabile campo anno d'immatricolazione */

    in = fopen("dati.txt", "w");

    printf("Inserire il numero di veicoli da generare in modo pseudocasuale...\n");
    printf("*****i veicoli generati confluiranno all'interno del file di input che verra' acquisito dal programma*****\n");
    scanf("%d", &n);

    srand(time(0));


    while (m < n)
    {
    for(x=0; x<6; x++)
    {
      i[x] = (rand()%26+65);
      fprintf(in, "%c", i[x]);
    }
    fprintf(in, "\t");

        for(y=0; y<6; y++)
        {
          j[y] = (rand()%26+65);
          fprintf(in, "%c", j[y]);
        }
        fprintf(in, "\t");

            for(z=0; z<6; z++)
            {
              k[z] = (rand()%26+65);
              fprintf(in, "%c", k[z]);
            }
            fprintf(in, "\t");

                for(a=0; a<4; a++)
                    {
                    b[a] = rand()%10;
                    fprintf(in, "%d", b[a]);
                    }
                    fprintf(in, "\n");
     m++;}
    fclose(in);
}



