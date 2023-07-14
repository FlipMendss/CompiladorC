#include <stdio.h>

int main(){

	int Ini, Fim, Conta, Soma;

	Ini=0;

	Fim=0;

	Soma=0;
	scanf("%d %d ", &Ini, &Fim);

	for(Conta=Ini;Conta<=Fim;Conta++){

	Soma=Soma+Conta;
	}
	printf("%d ", Soma);

}

