import sys
import time
import platform
import psutil

def bubble_sort(arr):
    """Implementação do algoritmo Bubble Sort."""
    n = len(arr)
    for i in range(n):
        swapped = False
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                swapped = True
        if not swapped:
            break

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

    # Ordenar com Bubble Sort e medir o tempo
    start_time = time.perf_counter()
    bubble_sort(numbers)
    end_time = time.perf_counter()

    # Memória depois da ordenação
    mem_after = process.memory_info().rss
    
    # Calcula a diferença em KB
    mem_used_kb = (mem_after - mem_before) / 1024
    if mem_used_kb < 0: # Caso o SO tenha otimizado a memória
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
    
    print("\nInformações do PC")
    print(f"Sistema: {platform.system()} {platform.release()}")
    print(f"Processador: {platform.processor()}")
    # Convertendo de bytes para GB
    total_mem_gb = psutil.virtual_memory().total / (1024**3)
    print(f"Memória RAM Total: {total_mem_gb:.2f} GB")
    
    print("\nResultados da Ordenação")
    print(f"Tempo de execução: {duration_ms:.3f} ms")
    print(f"RAM utilizada (pelo processo): {mem_used_kb:.2f} kb")

if __name__ == "__main__":
    main()