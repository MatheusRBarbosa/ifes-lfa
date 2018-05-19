# Trabalho de LFA

- Autor: Matheus Barbosa

## Arquivos
- **arquivos/**: Pasta com arquivos mealy e moore
- **lfa/**: Pasta com código fonte
- **prog**: Programa para rodar na linha de comando (linux)
- **TrabLFA.jar**: Build do código
### Conteúdo do arquivo 'prog'
 ```
 #!/bin/bash
 java -jar TrabLFA.jar $@
 ```
 
 ### Executando programa
 Caso precise de permissão
 ```
 chmod +x prog
 ```
 Rodando
 ```
 ./prog -i <arquivo de entrada> -o <arquivo de saida>
 ```

## Estrutura do código (lfa/)
 - Classe **main**: Pega os caminhos dos arquivos de entradas e saídas.<br>
 - Classe **rFile**: Transforma os dados do arquivo em List<ArrayList<String>>, ou transforma List<ArrayList<String>> em um arquivo.<br>
 - Classe **Automato**: Identifica qual tipo de automato que é e converte para o outro.<br>
