<?php

// Representa cada elemento (nó) da lista encadeada
class Node {
    public $data;
    public $next;

    public function __construct($data) {
        $this->data = $data;
        $this->next = null;
    }
}

// Representa a lista encadeada
class LinkedList {
    private $head;

    // Construtor que inicializa a lista a partir de um array de valores
    public function __construct(array $initialValues = []) {
        $this->head = null;
        foreach ($initialValues as $value) {
            $this->append($value);
        }
    }

    // Adiciona um elemento no final da lista
    public function append($data) {
        $newNode = new Node($data);
        if ($this->head === null) {
            $this->head = $newNode;
            return;
        }
        $lastNode = $this->head;
        while ($lastNode->next !== null) {
            $lastNode = $lastNode->next;
        }
        $lastNode->next = $newNode;
    }

    // Função A: Adiciona 'data' após o nó com o valor 'positionValue'
    public function add($data, $positionValue) {
        $newNode = new Node($data);
        $current = $this->head;
        while ($current !== null) {
            if ($current->data == $positionValue) {
                $newNode->next = $current->next;
                $current->next = $newNode;
                return;
            }
            $current = $current->next;
        }
    }

    // Função R: Remove o primeiro nó que contém 'data'
    public function remove($data) {
        $dummy = new Node(0); // Nó sentinela
        $dummy->next = $this->head;
        
        $prev = $dummy;
        $current = $this->head;
        $found = false;

        while ($current !== null) {
            if ($current->data == $data) {
                $prev->next = $current->next;
                $found = true;
                break;
            }
            $prev = $current;
            $current = $current->next;
        }

        $this->head = $dummy->next; // Atualiza o head

        if (!$found) {
            echo "Erro " . $data . PHP_EOL;
        }
    }

    // Função P: Retorna a representação da lista em String
    public function __toString() {
        $result = [];
        $current = $this->head;
        while ($current !== null) {
            $result[] = $current->data;
            $current = $current->next;
        }
        return implode(' ', $result);
    }
}

// Função para processar o arquivo
function processFile($filename) {
    $lines = file($filename, FILE_IGNORE_NEW_LINES);
    if ($lines === false) {
        echo "Erro ao ler o arquivo '{$filename}'.\n";
        return;
    }

    // 1. Primeira linha: valores iniciais
    $initialValues = array_map('intval', preg_split('/\s+/', trim($lines[0])));
    $linkedList = new LinkedList($initialValues);

    // 2. Processa as operações (a partir da terceira linha, índice 2)
    for ($i = 2; $i < count($lines); $i++) {
        if (trim($lines[$i]) === '') continue; // Ignora linhas vazias
        $parts = preg_split('/\s+/', trim($lines[$i]));
        $command = $parts[0];

        switch ($command) {
            case 'A':
                $linkedList->add((int)$parts[1], (int)$parts[2]);
                break;
            case 'R':
                $linkedList->remove((int)$parts[1]);
                break;
            case 'P':
                echo $linkedList . PHP_EOL;
                break;
        }
    }
}

// Ponto de entrada do script
function main($argv) {
    if (count($argv) < 2) {
        echo "Uso: php main.php <nome_do_arquivo>\n";
        return;
    }
    $filename = $argv[1];

    // --- Início do Timer ---
    $startTime = microtime(true);

    processFile($filename);

    // --- Fim do Timer ---
    $endTime = microtime(true);
    $durationInSeconds = $endTime - $startTime;

    echo "----------------------------------------\n";
    printf("Tempo de execução (PHP): %.4f segundos\n", $durationInSeconds);
}

main($argv);

?>