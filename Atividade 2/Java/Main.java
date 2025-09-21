package Java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

// Representa cada elemento (nó) da lista encadeada
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Representa a lista encadeada
class LinkedList {
    private Node head;

    // Construtor que inicializa a lista a partir de um array de valores
    public LinkedList(int[] initialValues) {
        this.head = null;
        if (initialValues != null) {
            for (int value : initialValues) {
                this.append(value);
            }
        }
    }

    // Adiciona um elemento no final da lista
    public void append(int data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        Node lastNode = this.head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = newNode;
    }

    // Função A: Adiciona 'data' após o nó com o valor 'positionValue'
    public void add(int data, int positionValue) {
        Node newNode = new Node(data);
        Node current = this.head;
        while (current != null) {
            if (current.data == positionValue) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
    }

    // Função R: Remove o primeiro nó que contém 'data'
    public void remove(int data) {
        Node dummy = new Node(0); // Nó sentinela
        dummy.next = this.head;
        
        Node prev = dummy;
        Node current = this.head;
        boolean found = false;

        while (current != null) {
            if (current.data == data) {
                prev.next = current.next;
                found = true;
                break;
            }
            prev = current;
            current = current.next;
        }
        
        this.head = dummy.next; // Atualiza o head

        if (!found) {
            System.out.println("Erro " + data);
        }
    }

    // Função P: Retorna a representação da lista em String
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        Node current = this.head;
        while (current != null) {
            sj.add(String.valueOf(current.data));
            current = current.next;
        }
        return sj.toString();
    }
}

// Classe principal para executar o programa
public class Main {

    public static void processFile(String filename) {
        LinkedList linkedList = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // 1. Lê a primeira linha para os valores iniciais
            String line = br.readLine();
            if (line != null) {
                String[] valuesStr = line.trim().split("\\s+");
                int[] initialValues = new int[valuesStr.length];
                for (int i = 0; i < valuesStr.length; i++) {
                    initialValues[i] = Integer.parseInt(valuesStr[i]);
                }
                linkedList = new LinkedList(initialValues);
            } else {
                return; // Arquivo vazio
            }

            // 2. Pula a segunda linha
            br.readLine();

            // 3. Processa as operações
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split("\\s+");
                String command = parts[0];

                switch (command) {
                    case "A":
                        linkedList.add(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        break;
                    case "R":
                        linkedList.remove(Integer.parseInt(parts[1]));
                        break;
                    case "P":
                        System.out.println(linkedList);
                        break;
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico no arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java Main <nome_do_arquivo>");
            return;
        }
        String filename = args[0];

        // --- Início do Timer ---
        long startTime = System.nanoTime();

        processFile(filename);

        // --- Fim do Timer ---
        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;

        System.out.println("----------------------------------------");
        System.out.printf("Tempo de execução (Java): %.4f segundos\n", durationInSeconds);
    }
}