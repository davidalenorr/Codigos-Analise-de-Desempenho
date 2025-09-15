import sys

# representa cada elemento da lista encadeada
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


# representa a lista encadeada
class LinkedList:

    # começa a lista de acordo com os dados do txt, bem dizer construtor
    def __init__(self, initial_values=None):
        self.head = None  # primeiro elemento da lista
        if initial_values:
            for value in initial_values:  # anda pelos valores iniciais e adiciona
                self.append(value)

    #  método iterador para percorrer a lista
    def __iter__(self):
        current = self.head
        while current:  # enquanto houver um elemento atual
            yield current  # retorna o elemnto atual
            current = current.next

    # função P do txt
    def __str__(self):
        return " ".join(str(node.data) for node in self)  # junta os dados em uma string

    # adiciona no final da lista cada elemento, se a lista tiver vazia, adiciona o primeiro elemento, caso não vai para o final da lista e adiciona o elemento
    def append(self, data):
        new_node = Node(data)
        if not self.head:  # se a lista estiver vazia
            self.head = new_node
            return
        last_node = self.head  # começa do primeiro elemento
        while last_node.next:
            last_node = last_node.next
        last_node.next = new_node

    # função A do txt
    def add(self, data, position):
        new_node = Node(data)

        # se posição é 1, adiciona no início
        if position <= 1:
            new_node.next = self.head
            self.head = new_node
            return

        # vai até a posição anterior à que eu quero e adiciona o elemento
        prev = self.head
        current_pos = 1
        while current_pos < position - 1 and prev.next:
            prev = prev.next
            current_pos += 1

        new_node.next = prev.next
        prev.next = new_node

    # função R do txt
    def remove(self, data):

        # usa um elemento fantasma para simplificar a lógica de remoção
        dummy = Node(0)  # elemento fantasma
        dummy.next = self.head
        prev, current = dummy, self.head

        #correção #denini
        found = False

        while current:
            if current.data == data:
                # remove o elemento ajustando o ponteiro do nó anterior
                prev.next = current.next
                found = True
                break # utilizando para quando o elemento é encontrado

            # move para o proximo elemento
            prev = current
            current = current.next

        self.head = dummy.next  # garante que o head esteja correto

        # usado como operador logico caso não encontre ele imprime o dado errado
        if not found:
            print(f'Erro {data}')


# função para processar o arquivo
def process_file(filename):
    with open(filename, 'r') as file:
        lines = file.read().strip().split('\n')

    # primeira linha: valores iniciais da lista
    initial_values = map(int, lines[0].split())

    # cria e inicializa a lista
    linked_list = LinkedList(initial_values)

    # processa cada operação em um único loop
    for line in lines[2:]:
        parts = line.split()
        command = parts[0]

        if command == 'A':  # adicionar
            linked_list.add(int(parts[1]), int(parts[2]))
        elif command == 'R':  # remover
            linked_list.remove(int(parts[1]))
        elif command == 'P':  # imprimir
            print(linked_list)


# execução

#if __name__ == "__main__":
    # processa o arquivo
 #   process_file("arq.txt") #args ou parametros da main
# my_script.py

def main():
    if len(sys.argv) > 1:
        file_to_process = sys.argv[1]
        process_file(file_to_process)  #
    else:
        print("Erro")


if __name__ == "__main__":
    main()
