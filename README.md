

Hapais - Trabalho de Compiladores - [IC-UFAL](www.ic.ufal.br) - \[2017.2\]
=======

Especificação Linguagem
------

A especificação da linguagem, juntamente com os tokens para análise léxica está definida no documento especificação, na raiz deste repositório.

Analisador Léxico
------

O analisador léxico foi feito baseado em um autômato, onde analisa-se caractere por caractere até encontrar um token válido (ou inválido, o que gerará um erro, mas a execução não é interrompida).

Analisador Sintático
------

Para análise sintática, foi utilizado o algoritmo de análise preditiva tabular. A gramática livre de contexto, LL(1) e a tabela gerada está disponível no documento também anexo ao repositório.

Analisador Semântico e geração de código
------

Uma futura implementação será feita, para que a linguagem comece a gerar código.

As saídas dos analisadores foram definidas pelo professor da disciplina, e podem ser consultadas no documento anexo ao repositório referente a definição do trabalho.
