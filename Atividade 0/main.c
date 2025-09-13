#include <stdio.h>

void limpar_buffer_entrada() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

// Função que verifica se um número é primo.
// Retorna 1 se for primo, 0 caso contrário.
int eh_primo(int num) {
    // Números menores ou iguais a 1 não são primos.
    if (num <= 1) {
        return 0;
    }
    // O número 2 é o único primo par.
    if (num == 2) {
        return 1;
    }
    // Qualquer outro número par não é primo.
    if (num % 2 == 0) {
        return 0;
    }
    // Começa a testar divisores a partir do 3, se um número tem um divisor d
    // se d for maior que a raiz de num , então k tem que ser menor.
    // vai pulando e 2 em 2, pois números pares já foram eliminados.
    // se o resto for zero, então num não é primo.
    for (int i = 3; i * i <= num; i += 2) {
        if (num % i == 0) {
            return 0; // Encontrou um divisor, então não é primo.
        }
    }
    
    // 5. Se nenhum divisor foi encontrado, o número é primo.
    return 1;
}

int main() {
    int n;

    // Loop para garantir que a entrada seja válida.
    while (1) {
        printf("Digite um número inteiro N > 0: ");
        if (scanf("%d", &n) == 1 && n > 0) {
            limpar_buffer_entrada();
            break;
        } else {
            printf("Erro\n\n");
            limpar_buffer_entrada();
        }
    }

    printf("\nBuscando primos até %d n", n);

    int quantidade = 0;

    // Exibe os resultados
    printf("\n---------- RESULTADO ----------\n");
    printf("O valor de N: %d\n", n);
    printf("\nOs números primos encontrados são:\n[");

    // Itera por cada número de 2 até N
    int primeiro_primo_impresso = 0;
    for (int i = 2; i <= n; i++) {
        if (eh_primo(i)) {
            if (primeiro_primo_impresso) {
                printf(", ");
            }
            printf("%d", i);
            quantidade++;
            primeiro_primo_impresso = 1;
        }
    }
    printf("]\n");

    printf("\nQuantidade de números primos encontrados: %d\n", quantidade);
    printf("-----------------------------\n\n");

    return 0;
}