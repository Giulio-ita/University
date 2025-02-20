#include <stdio.h>
#include <stdlib.h>

/* funzioni dichiarate */
int numero_righe(int numero_matrice);

int numero_colonne(int numero_matrice);

double **crea_matrice(int righe,
		      int colonne);

double **somma_matrici(double **matrice1,
		       double **matrice2,
		       int righe1,
		       int righe2,
		       int colonne1,
		       int colonne2);


double **prodotto_matrici(double **matrice1,
		          double **matrice2,
		          int righe1,
		          int righe2,
		          int colonne1,
		          int colonne2);

void stampa_matrice(double **matrice,
		    int righe,
		    int colonne,
		    char idenifica_matrice);


int main()
{
    double **matrice1,
	       **matrice2;

	double **matrice_somma,
	       **matrice_prodotto;

	int    righe1,
	       colonne1,
	       righe2,
	       colonne2;

	righe1 = numero_righe(1);//?????????????????????????????????????????????????????????????????????????????????????????????????????
	colonne1 = numero_colonne(1);
	matrice1 = crea_matrice(righe1,
			        colonne1);

	righe2 = numero_righe(2);
	colonne2 = numero_colonne(2);
	matrice2 = crea_matrice(righe2,
			        colonne2);

	stampa_matrice(matrice1,
		       righe1,
		       colonne1,
		       '1');

	stampa_matrice(matrice2,
		       righe2,
		       colonne2,
		       '2');



	if(colonne1 != colonne2 || righe1 != righe2)
	{
		printf("\n");
		printf("Non è possibile effettuare la somma \n");
		printf("\n");
	}
	else
	{
		matrice_somma = somma_matrici(matrice1,
		                              matrice2,
		                              righe1,
		                              righe2,
		                              colonne1,
		                              colonne2);

		stampa_matrice(matrice_somma,
		               righe1,
		               colonne1,
		               's');

		free(matrice_somma);
		matrice_somma = NULL;
	}

	if(colonne1 != righe2)
	{
		printf("\n");
		printf("Non è possibile effettuare il prodotto \n");
		printf("\n");
	}
	else
	{

		matrice_prodotto = prodotto_matrici(matrice1,
		                                    matrice2,
		                                    righe1,
		                                    righe2,
		                                    colonne1,
		                                    colonne2);

		stampa_matrice(matrice_prodotto,
		               righe2,
		               colonne1,
		               'p');

		free(matrice_prodotto);
		matrice_prodotto = NULL;
	}


	free(matrice1);
	matrice1 = NULL;


	free(matrice2);
	matrice2 = NULL;



	return 0;
}

int numero_righe(int numero_matrice)
{
	int numero;

	int esito_lettura;

	do
	{
		printf("Inserire il numero di righe della matrice %d \n",
		       numero_matrice);
		esito_lettura = scanf("%d",
			    	      &numero);

		if(esito_lettura != 1 || numero <= 0)
		{
			printf("Errore, Inserire un input valido \n");
		}
		while(getchar() !='\n');

	}while(esito_lettura !=1 || numero <=0);

	return(numero);
}


int numero_colonne(int numero_matrice)
{
	int numero;

	int esito_lettura;

	do
	{
		printf("Inserire il numero di colonne della matrice %d \n",
		       numero_matrice);

		esito_lettura = scanf("%d",
			    	      &numero);

		if(esito_lettura != 1 || numero <= 0)
		{
			printf("Errore, Inserire un input valido \n");
		}
		while(getchar() !='\n');

	}while(esito_lettura !=1 || numero <=0);

	return(numero);
}

double **crea_matrice(int righe,
                      int colonne)
{
	int i,
	    j;

	int esito_lettura;

	double **matrice;

	matrice = (double**)calloc(righe,
			           sizeof(double*));

	for(i = 0;
	   (i < righe);
	   i++)
	{
		matrice[i] = (double*)calloc(colonne,
				             sizeof(double));
	}

	for(i=0;
	   (i < righe);
	   i++)
	{
			for(j=0;
	                   (j < righe);
	                   j++)
                 	{
				do
				{
					printf("Inserisci il valore nella riga %d colonne %d : \n",
					       i+1,
					       j+1);

					esito_lettura = scanf("%lf",
							      &matrice[i][j]);

					if(esito_lettura !=1 || righe <= 0)
					{
						printf("Errore : Inserire un input valido");
					}
					while (getchar() !='\n');

				}while(esito_lettura !=1);
                 	}
	}

	return (matrice);
}

double **somma_matrici(double **matrice1,
		       double **matrice2,
		       int righe1,
		       int righe2,
		       int colonne1,
		       int colonne2)
{
	int i,
	    j;

	double **matrice_somma;

	matrice_somma = (double**)calloc(righe1,
					 sizeof(double*));

	for(i = 0;
	   (i < righe1);
	   i++)
	{
		matrice_somma[i] = (double*)calloc(colonne1,
				                    sizeof(double*));

		for(j=0;
		   (j<colonne1);
		   j++)
		{
			matrice_somma[i][j] += matrice1[i][j] + matrice2[i][j];
		}

	}

	return(matrice_somma);

}

double **prodotto_matrici(double **matrice1,
		          double **matrice2,
		          int righe1,
		          int righe2,
		          int colonne1,
		          int colonne2)
{
	int i,
	    j,
	    c;

	double **matrice_prodotto;

	matrice_prodotto = (double**)calloc(righe1,
					    sizeof(double*)* righe1);

	for(i = 0;
	   (i < righe1);
	   i++)
	{
		matrice_prodotto[i] = (double*)calloc(colonne2,
				                       sizeof(double*) * colonne2);
	}

	for(i=0;
           (i<righe2);
            i++)
	{
        		for(j=0;
                           (j<colonne1);
                            j++)
                 	{
        		 	matrice_prodotto[i][j] = 0;

				for(c=0;
                                   (c<colonne1);
                                    c++)
                         	{

        		 		matrice_prodotto[i][j] += (matrice1[i][c] * matrice2[c][j]);
                         	}
                 	}

	}

	return(matrice_prodotto);
}



void stampa_matrice(double **matrice,
		    int righe,
		    int colonne,
		    char identifica_matrice)
{
	int i,
	    j;

	switch(identifica_matrice)
	{
		case '1':
		  printf("Matrice 1 \n");
		  break;

		case '2':
		  printf("Matrice 2 \n");
		  break;

		 case 't':
		  printf("Matrice somma \n");
		  break;

		 case 'p':
		  printf("Matrice prodotto \n");
		  break;
	}

	for (i = 0;
	    (i< righe);
	    i++)
	{

		for (j = 0;
	  	    (j< righe);
	  	     j++)
		{
			printf("|%lf|",
			       matrice[i][j]);
		}

		printf("\n");
	}

}


