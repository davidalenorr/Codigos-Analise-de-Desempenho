import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Algoritmo Bubble Sort
    public static void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // Troca os elementos
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    swapped = true;
                }
            }
            // Se não houve trocas nesta passagem, a lista já está ordenada
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java Main <nome_do_arquivo>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = "arq-saida.txt";
        List<Integer> numbers = new ArrayList<>();

        // Ler os números do arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Medição de memória inicial (antes da ordenação)
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Sugere ao garbage collector para rodar
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        
        // Ordenar usando Bubble Sort e medir o tempo
        long startTime = System.nanoTime();
        bubbleSort(numbers);
        long endTime = System.nanoTime();
        
        // Medição de memória final (depois da ordenação)
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = (memoryAfter - memoryBefore) / 1024; // Convertendo para KB
        if (memoryUsed < 0) { 
             MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
             memoryUsed = memoryBean.getHeapMemoryUsage().getUsed() / 1024;
        }


        long durationInNano = (endTime - startTime);
        double durationInMs = durationInNano / 1_000_000.0;

        // Escrever os números ordenados no arquivo de saída
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Integer number : numbers) {
                writer.write(number.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de saída: " + e.getMessage());
            return;
        }

        // Imprimir as informações no console
        System.out.println("Informações Gerais");
        System.out.println("Linguagem: Java");
        System.out.println("Versão: " + System.getProperty("java.version"));
        
        System.out.println("\nInformações do PC");
        System.out.println("Sistema Operacional: " + System.getProperty("os.name"));
        System.out.println("Processadores (cores): " + runtime.availableProcessors());
        
        // Convertendo de bytes para GB para melhor leitura
        java.lang.management.OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        long totalMemoryBytes = 0;
        try {
            java.lang.reflect.Method m = osBean.getClass().getMethod("getTotalMemorySize");
            m.setAccessible(true);
            Object value = m.invoke(osBean);
            if (value instanceof Long) {
                totalMemoryBytes = (Long) value;
            }
        } catch (Exception e) {
            System.err.println("Não foi possível obter a memória total: " + e.getMessage());
        }
        System.out.printf("Memória RAM Total: %.2f GB\n", totalMemoryBytes / (1024.0 * 1024.0 * 1024.0));
        
        System.out.println("\nResultados da Ordenação");
        System.out.printf("Tempo de execução: %.3f ms\n", durationInMs);
        System.out.printf("RAM utilizada (aproximado): %d kb\n", memoryUsed);
    }
}