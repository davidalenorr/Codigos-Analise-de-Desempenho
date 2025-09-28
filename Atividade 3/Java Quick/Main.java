import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            // pi é o índice da partição, arr.get(pi) está agora no lugar certo
            int pi = partition(arr, low, high);

            // Ordena recursivamente os elementos antes e depois da partição
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high); // Pivô é o último elemento
        int i = (low - 1); // Índice do menor elemento

        for (int j = low; j < high; j++) {
            // Se o elemento atual for menor ou igual ao pivô
            if (arr.get(j) <= pivot) {
                i++;
                // Troca arr[i] e arr[j]
                Collections.swap(arr, i, j);
            }
        }

        // Troca arr[i+1] e arr[high] (o pivô)
        Collections.swap(arr, i + 1, high);

        return i + 1;
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
        
        // Medição de memória inicial
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Ordenar usando QuickSort e medir o tempo
        long startTime = System.nanoTime();
        quickSort(numbers, 0, numbers.size() - 1);
        long endTime = System.nanoTime();

        // Medição de memória final
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

        // Imprimir as informações
        System.out.println("Informações Gerais");
        System.out.println("Linguagem: Java");
        System.out.println("Versão: " + System.getProperty("java.version"));

        System.out.println("\nInformações do PC");
        System.out.println("Sistema Operacional: " + System.getProperty("os.name"));
        System.out.println("Processadores (cores): " + runtime.availableProcessors());
        
        com.sun.management.OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
            com.sun.management.OperatingSystemMXBean.class);
        long totalMemoryBytes = osBean.getTotalMemorySize();
        System.out.printf("Memória RAM Total: %.2f GB\n", totalMemoryBytes / (1024.0 * 1024.0 * 1024.0));

        System.out.println("\nResultados da Ordenação");
        System.out.printf("Tempo de execução: %.3f ms\n", durationInMs);
        System.out.printf("RAM utilizada (aproximado): %d kb\n", memoryUsed);
    }
}