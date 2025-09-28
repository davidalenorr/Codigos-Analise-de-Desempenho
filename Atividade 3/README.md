# Análise de Algoritmos de Ordenação com BubbleSort e QuickSort

Este projeto contém implementações do algoritmo BubbleSort e Quicksort em Java e Python para analisar e comparar seu desempenho em termos de tempo de execução e consumo de memória RAM.

## Pré-requisitos

### Java
- JDK (Java Development Kit) instalado (versão 8 ou superior).

### Python
- Python 3 instalado.
- Biblioteca `psutil`:
  ```bash
  pip install psutil
  ```
## Como Executar
  
  ### 1. Java

1.  Navegue até o diretório da implementação Java:
    ```bash
    cd \Java Quick\
    ```
    ou
    
    ```bash
    cd \Java Bubble\
    ```
3.  Compile o arquivo `.java`:
    ```bash
    javac Main.java
    ```
4.  Execute o programa, passando o caminho para o arquivo de entrada como argumento:
    ```bash
    java Main ../arq.txt
    ```
### 2. Python

1.  Navegue até o diretório da implementação Python:
    ```bash
    cd \Python\
    ```
    ou
    
    ```bash
    cd \Java Bubble\
    ```
3.  Execute o script, passando o caminho para o arquivo de entrada como argumento:
    ```bash
    python main.py ../arq.txt
    ```

Após a execução de cada programa, um arquivo `arq-saida.txt` será criado ou sobrescrito no mesmo diretório do programa com os números ordenados, e as métricas de desempenho serão impressas no terminal.
