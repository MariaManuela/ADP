
#include <math.h>
#include "stdafx.h"
#include "stdlib.h"
#include <iostream>
#include "mpi.h"
#define MASTER 0
#define SIZE 8


using namespace std;
int main(int argc, char **argv)
{
	int rank;
	int x; 
	int vector[SIZE] = { 4,5,6,7,22,11,34,21 };
	int numProc;
	
    MPI_Init(&argc, &argv);
	
	MPI_Comm_size(MPI_COMM_WORLD, &numProc);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);

	if (rank == MASTER) {

		x = SIZE / numProc;
		int start = rank * x;
		int stop = (rank + 1) * x;
		int suma_partiala = 0;


		for (int i = start; i < stop; i++) {

			suma_partiala += vector[i];

		}

		printf("Partial sum is: %d, at rank: %d\n", suma_partiala, rank);
		
		MPI_Send(&suma_partiala, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
		MPI_Send(&stop, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
		
	}
	else 
	{
		int start;
		int stop = SIZE;
		int suma_partiala = 0;
		int suma_totala = 0;

		MPI_Recv(&suma_totala, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		MPI_Recv(&start, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		for (int i = start; i < stop; i++) {

			suma_partiala += vector[i];

		}
		printf("Partial sum is: %d, at rank: %d\n", suma_partiala, rank);
			
		suma_totala = suma_totala + suma_partiala;
		
		printf("Total sum is: %d\n", suma_totala);

	}

	
	
	MPI_Finalize();
	return 0;
}



