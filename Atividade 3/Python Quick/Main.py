import sys
import time
import platform
import psutil

# Aumenta o limite de recursão para o Quicksort em listas grandes
sys.setrecursionlimit(100000) 

def quicksort(arr):
    """
    Função principal que inicia a ordenação Quicksort.
    É uma abordagem mais "pythônica" que chama uma função auxiliar.
    """
    _quicksort_recursive(arr, 0, len(arr) - 1)

def _quicksort_recursive(arr, low, high):
    """Função recursiva auxiliar para o Quicksort."""
    if low < high:
        # Encontra o pivô tal que os elementos menores estão à esquerda
        # e os maiores à direita.
        pi = _partition(arr, low, high)

        # Ordena recursivamente as duas partições
        _quicksort_recursive(arr, low, pi - 1)
        _quicksort_recursive(arr, pi + 1, high)

def _partition(arr, low, high):
    """
    Função para particionar a lista. O último elemento é escolhido como pivô.
    """
    # Índice do menor elemento
    i = low - 1
    pivot = arr[high]

    for j in range(low, high):
        # Se o elemento atual for menor ou igual ao pivô
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i] # Troca

    arr[i + 1], arr[high] = arr[high], arr[i + 1] # Coloca o pivô no lugar certo
    return i + 1

def main():
    if len(sys.argv) < 2:
        print("Uso: python main.py <nome_do_arquivo>")
        sys.exit(1)

    input_file = sys.argv[1]
    output_file = "arq-saida.txt"

    # Ler números do arquivo
    try:
        with open(input_file, 'r') as f:
            numbers = [int(line.strip()) for line in f.readlines()]
    except FileNotFoundError:
        print(f"Erro: Arquivo '{input_file}' não encontrado.")
        sys.exit(1)
    except ValueError:
        print(f"Erro: O arquivo '{input_file}' contém linhas que não são números inteiros.")
        sys.exit(1)

    process = psutil.Process()
    
    # Memória antes da ordenação
    mem_before = process.memory_info().rss

    # Ordenar com Quicksort e medir o tempo
    start_time = time.perf_counter()
    quicksort(numbers)
    end_time = time.perf_counter()

    # Memória depois da ordenação
    mem_after = process.memory_info().rss
    
    # Calcula a diferença em KB
    mem_used_kb = (mem_after - mem_before) / 1024
    if mem_used_kb < 0:
        mem_used_kb = process.memory_info().rss / 1024

    duration_ms = (end_time - start_time) * 1000

    # Escrever o resultado no arquivo de saída
    with open(output_file, 'w') as f:
        for num in numbers:
            f.write(str(num) + '\n')

    # Imprimir as informações
    print("Informações Gerais")
    print(f"Linguagem: Python")
    print(f"Versão: {sys.version}")
    
    print("\nInformações do PC --")
    print(f"Sistema: {platform.system()} {platform.release()}")
    print(f"Processador: {platform.processor()}")
    total_mem_gb = psutil.virtual_memory().total / (1024**3)
    print(f"Memória RAM Total: {total_mem_gb:.2f} GB")
    
    print("\nResultados da Ordenação")
    print(f"Tempo de execução: {duration_ms:.3f} ms")
    print(f"RAM utilizada (pelo processo): {mem_used_kb:.2f} kb")

if __name__ == "__main__":
    main()