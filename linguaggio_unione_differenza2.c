
/****************************/
/*inclusione delle librerie */
/****************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/* definizone dei tipi */

struct elementi
{
    char            *stringa;   /* sequenza di simboli */
    struct elementi *succ;      /* puntatore alla componente sucessiva */
};

/********************************/
/* dichiarazione delle funzioni */
/********************************/

struct elementi* acquisisci_elementi();

bool duplicati_linguaggio(struct elementi *A,
                          char            *sequenza_simboli);

bool validazione_simboli(char *sequenza_simboli);

void inserimento(struct elementi **A,
                 char            *sequenza_simboli);

void unione(struct elementi *A,
            struct elementi *B);

bool duplicati_tra_linguaggi(struct elementi *A,
                             struct elementi *B);

void differenza(struct elementi *A,
                struct elementi *B);

bool intersezione(struct elementi *A,
                  struct elementi *B);

/******************************/
/* definizione delle funzioni */
/******************************/

/* definizione della funzione main */

int main(void) {

	struct elementi *A = NULL;	/* input: prima componente del primo linguaggio */
	struct elementi *B = NULL;	/* input: prima componente del secondo linguaggio */

	/* acquisire il primo linguaggio */
	printf("1 linguaggio.\n");
	A = acquisisci_elementi();

	/* acquisire il secondo linguaggio */
	printf("2 linguaggio.\n");
	B = acquisisci_elementi();
	printf("\n");

	/* calcolare e comunicare l'unione dei due linguaggi */
	printf("L'unione dei due linguaggi e' ");
	unione(A,
           B);
	printf("\n");

	printf("La differenza tra il primo linguaggio e il secondo e' ");
	/* controllare se tutte le sequenze del primo linguaggio sono presenti anche nel secondo */
   	if (intersezione(A,
                     B))
	{
		printf("l'insieme vuoto");
	}
	else
	/* calcolare e comunicare la differenza tra il primo linguaggio e il secondo */
	{
		differenza(A,
                   B);
	}

	/* liberazione della memoria allocata per il primo linguaggio */
	free(A);

	/* liberazione della memoria allocata per il secondo linguaggio */
	free(B);

	return(0);
}

/* definizione della funzione per acquisire sequenze di simboli all'interno del linguaggio */
struct elementi* acquisisci_elementi()
{
	struct elementi *A = NULL;                  /* input: prima componente del linguaggio */

	int              n,                         /* input: numero di sequenze di simboli */
                     lunghezza_stringa,         /* input: lunghezza della singola sequenza di simboli */
                     i = 0,
                     temp,                     /* lavoro: indice di scorrimento della sequenza */
                     esito_lettura;             /* lavoro: validazione dei valori inseriti */

	char             valore_stringa[5],            //SE IMPOSTO UN VALORE MX ALL'ARRAY VA BENE SENNO............
                     *valore_stringa_allocata = NULL;   /* input: sequenza di simboli da allocare dinamicamente */

	/* leggere e validare il numero di elementi del linguaggio */
	do
	{
		printf("Inserire il numero di sequenze di lunghezza finita di simboli:\n");
		esito_lettura = scanf("%d",
                              &n);
		if ((esito_lettura != 1) ||(n < 0))
			printf("Errore : Inserire un input valido\n");
		while (getchar() != '\n');
	}
	while ((esito_lettura != 1) || (n < 0));

	if (n != 0)
	{
	    /* iterazione dell'operazione di validazione */
		do
		{
			/* leggere e validare il valore della singola sequenza di simboli */
			do
			{
				printf("Inserire il valore della singola sequenza di simboli su un alfabeto {a,e,i,o,u}:\n");
				/* allocazione della memoria dinamica */
				esito_lettura = scanf("%s",
                                      valore_stringa);
                                      printf("ciao%s", valore_stringa);
				lunghezza_stringa = strlen(valore_stringa);
				printf("ciao%d", lunghezza_stringa);
				valore_stringa_allocata = calloc(lunghezza_stringa,
                                                 sizeof(valore_stringa));
				valore_stringa_allocata = valore_stringa;
				if ((esito_lettura != 1))
 					printf("Errore : Inserire un input valido\n");
                while (getchar() != '\n');
            }
            while (esito_lettura != 1);

            if (duplicati_linguaggio(A,
                                     valore_stringa_allocata))
                if (validazione_simboli(valore_stringa_allocata))
                {
                    inserimento(&A,
                                valore_stringa_allocata);
                    i++;
                }
                else
                    printf("simboli non validi\n");
            else
                printf("sequenza di simboli gia' presente all'interno del linguaggio\n");
        }
		while (i < n);
	}
	return(A);
}

/* definizione della funzione per controllare sequenze di simboli che compaiono più volte in un linguaggio */
bool duplicati_linguaggio(struct elementi *A,                   /* input: prima componente del linguaggio*/
                          char            *sequenza_simboli)    /* input: sequenza di simboli */
{
	bool duplicato = true;	/* output: valore di controllo della presenza di sequenze di simboli già presenti nel linguaggio */

	/* scorrere la lista e verificare se la sequenza di simboli è già presente nel linguaggio */
	while (A != NULL && duplicato)
	{
		if(!strcmp(A->stringa,
                   sequenza_simboli))
			duplicato = false;
		A = A->succ;
	}
	return duplicato;
}

/* definizione della funzione di validazione dell'input */
bool validazione_simboli(char *sequenza_simboli)    /* input: sequenza di simboli */
{
	int i,                                      /* lavoro: indice di scorrimento dei singoli simboli */
		lunghezza = strlen(sequenza_simboli),   /* output: lunghezza della singola sequenza di simboli */
		controllo_lunghezza = 0;                /* output: contatore da confrontare con la variabile lunghezza */

	/* leggere e validare la sequenza di simboli inserita */
	for (i=0;
           sequenza_simboli[i] != '\0';
         i++)
        {
		if ((sequenza_simboli[i] == 'a') ||
            (sequenza_simboli[i] == 'e') ||
            (sequenza_simboli[i] == 'i') ||
            (sequenza_simboli[i] == 'o') ||
            (sequenza_simboli[i] == 'u'))
            {
                controllo_lunghezza++;
            }
        }
    return(lunghezza == controllo_lunghezza);
}

/* definizione della funzione di inserimento di sequenze di simboli all'interno del linguaggio */
void inserimento(struct elementi **A,               /* input: prima componente del linguaggio */
                 char            *sequenza_simboli)	/* input: sequenza di simboli */
{
	struct elementi *prec,		/* lavoro: variabile per la gestione della catena di puntatori */
                    *nuovo,		/* lavoro: variabile che crea nuovo elemento del linguaggio */
                    *corrente;	/* lavoro: variabile per la gestione della catena di puntatori */

	/* allocazione dinamica della memoria */
	nuovo = (struct elementi *)malloc(sizeof(struct elementi));
	if (nuovo != NULL)
	{
		nuovo->stringa = (char *)malloc((strlen(sequenza_simboli) + 1) * sizeof(char));
		strcpy(nuovo->stringa,
               sequenza_simboli);
		prec = NULL;
		corrente = *A;
		while (corrente != NULL)
		{
			prec = corrente;
			corrente = corrente->succ;
		}
		if (prec == NULL)
		{
			nuovo->succ = *A;
			*A = nuovo;
		}
		else
		{
			prec->succ = nuovo;
			nuovo->succ = corrente;
		}
	}
}

/* definizione della funzione per svolgere l'unione dei due linguaggi */
void unione(struct elementi *A,     /* input: prima componente del primo linguaggio */
            struct elementi *B)     /* input: prima componente del secondo linguaggio */
{
	/* controllare se ambedue i linguaggi non presentano alcuna sequenza di simboli */
	if (A == NULL && B == NULL)
		printf("l'insieme vuoto\n");
	else
	{
		/* scorrere tutte le sequenze di simboli del secondo linguaggio e confrontare se vi è uguaglianza con le sequenze del primo */
		while (B != NULL)
		{
			if (duplicati_tra_linguaggi(A,
                                        B))
				printf("{%s}",
                       B->stringa);
			B = B->succ;
		}
		while (A != NULL)
		{
			printf("{%s}",
                   A->stringa);
			A = A->succ;
		}
	}
}

/* definizione della funzione per determinare l'uguaglianza o meno tra due sequenze di simboli dei due linguaggi */
bool duplicati_tra_linguaggi(struct elementi *A,    /* input: prima componente del primo linguaggio */
                             struct elementi *B)    /* input: prima componente del secondo linguaggio */
{
	bool duplicato = true;	/* lavoro: valore di verità se due sequenze appartenenti a linguaggi diversi sono uguali */

	/* scorrere elementi del linguaggio e verificare se due sequenze appartenenti a linguaggi diversi sono uguali */
	while (A != NULL && duplicato)
	{
		if (!strcmp(A->stringa,
                    B->stringa))
			duplicato = false;
		A = A->succ;
	}
	return duplicato;
}

/* definizione della funzione per svolgere la differenza tra il primo linguaggio e il secondo */
void differenza(struct elementi *A,     /* input: prima componente del primo linguaggio */
                struct elementi *B)     /* input: prima componente del secondo linguaggio */
{
	/* controllare se il primo linguaggio non presenta sequenze di simboli */
	if (A == NULL)
		printf("l'insieme vuoto");
	/* invocazione ricorsiva della funzione differenza */
	else
	{
		if (duplicati_tra_linguaggi(B,
                                    A))
			printf("{%s}",
                   A->stringa);
		if (A->succ != NULL)
			differenza(A->succ,
                       B);
	}
}

/* definizione della funzione per controllare se tutte le sequenze di simboli del primo linguaggio sono presenti anche nel secondo */
bool intersezione(struct elementi *A,	/* input: primo linguaggio */
                  struct elementi *B)	/* input: secondo linguaggio */
{
	int n = 0,	/* lavoro: contatore del numero di sequenze del linguaggio */
		c = 0;	/* lavoro: contatore di sequenze uguali tra i due linguaggi */

	/* scorrere ogni singola sequenza di simboli del primo linguaggio e confrontare se vi è uguaglianza con sequenze del secondo linguaggio */
	while (A != NULL)
	{
		n++;
		if (!duplicati_tra_linguaggi(B,
                                     A))
			c++;
		A = A->succ;
	}
	return(n==c);
}
