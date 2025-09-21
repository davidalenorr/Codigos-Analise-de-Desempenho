import sys
import time

# Representa cada elemento (nó) da lista encadeada
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

# Representa a lista encadeada
class LinkedList:
    # Construtor: inicializa a lista a partir de uma sequência de valores
    def __init__(self, initial_values=None):
        self.head = None  # Primeiro elemento da lista
        if initial_values:
            for value in initial_values:
                self.append(value)

    # Método iterador para percorrer a lista
    def __iter__(self):
        current = self.head
        while current:
            yield current
            current = current.next

    # Função P: Retorna a representação da lista em formato de string
    def __str__(self):
        return " ".join(str(node.data) for node in self)

    # Adiciona um novo elemento no final da lista
    def append(self, data):
        new_node = Node(data)
        if not self.head:  # Se a lista estiver vazia
            self.head = new_node
            return
        last_node = self.head
        while last_node.next:
            last_node = last_node.next
        last_node.next = new_node

    # Função A: Adiciona 'data' após o nó com o valor 'position'
    def add(self, data, position):
        new_node = Node(data)
        current = self.head
        while current:
            if current.data == position:
                new_node.next = current.next
                current.next = new_node
                return
            current = current.next

    # Função R: Remove a primeira ocorrência do nó com o valor 'data'
    def remove(self, data):
        dummy = Node(0) # Nó sentinela para simplificar a remoção
        dummy.next = self.head
        prev = dummy
        current = self.head
        found = False

        while current:
            if current.data == data:
                prev.next = current.next # Remove o nó
                found = True
                break # Para o loop após encontrar

            prev = current
            current = current.next

        self.head = dummy.next # Garante que o head esteja correto

        if not found:
            print(f'Erro {data}')

# Função para processar o arquivo
def process_file(filename):
    try:
        with open(filename, 'r') as file:
            lines = file.read().strip().split('\n')

        # Primeira linha: valores iniciais da lista
        initial_values = map(int, lines[0].split())
        linked_list = LinkedList(initial_values)

        # Processa as operações a partir da terceira linha
        for line in lines[2:]:
            parts = line.split()
            if not parts: continue # Ignora linhas vazias
            command = parts[0]

            if command == 'A':
                linked_list.add(int(parts[1]), int(parts[2]))
            elif command == 'R':
                linked_list.remove(int(parts[1]))
            elif command == 'P':
                print(linked_list)
    except FileNotFoundError:
        print(f"Erro: O arquivo '{filename}' não foi encontrado.")
    except Exception as e:
        print(f"Ocorreu um erro: {e}")

def main():
    if len(sys.argv) < 2:
        print("Uso: python main.py <nome_do_arquivo>")
        return
        
    file_to_process = sys.argv[1]

    # --- Início do Timer ---
    start_time = time.time()
    
    process_file(file_to_process)
    
    # --- Fim do Timer ---
    end_time = time.time()
    duration = end_time - start_time
    
    print("----------------------------------------")
    print(f"Tempo de execução (Python): {duration:.4f} segundos")

if __name__ == "__main__":
    main()