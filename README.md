

Trabalho de Compiladores - [IC-UFAL](www.ic.ufal.br) - \[2017.2: 1BIM\]
=======

Especificação Mínima da Linguagem (entrega: 05/03)
------

### Regras gerais

* _A linguagem deve poder ser analisada em um passo único._

    * Isso significa que o ANALISADOR LÉXICO atuará como uma subrotina, chamada pelo ANALISADOR SINTÁTICO 
    quando este este necessitar de mais um TOKEN.

* _Como regra geral, seguir a forma de análise dos trabalhos de CLP._ 
    * Ver capítulo 5 (o 6 também, talvez?) do livro Conceitos de Linguagens de Programação, de R Sebesta.
    * Por exemplo, capítulo 5 apresenta **nomes, vinculações e escopo**. É pra especificar a linguagem
      seguindo isso.

* [x] _Especificar a **estrutura** geral de um programa, indicando onde podem ser declaradas funções, variáveis,
  instruções._
    * Ver item anterior.

* [ ] Subir DOC no OVERLEAF pra cá. 

### Regras específicas

* [x] _A especificação de tipos deve ser estática, com no mínimo o seguinte conjunto de tipos de dados:_
    * inteiro;
    * ponto flutuante;
    * caractere;
    * booleano;
    * cadeia de caracteres;
    * arranjos unidimensionais.
    * [ ] mais algum? Verificar isso depois de especificar os anteriores.

* [x] **Para o item acima, especificar quais as operações de cada tipo.**

* [x] _Especificar as constantes literais de cada tipo._

* [ ] _Conjunto **mínimo** de operadores:_
    * [x] aritméticos: para tipos numéricos
        * aditivos, multiplicativos;
        * unário negativo;
    * [x] relacionais:
        * para tipos numéricos, caracteres e cadeias de caracteres: todos;
        * para tipos booleanos: igualdade e desigualdade;
    * [x] lógicos: para o tipo booleano
        * negação, conjunção e disjunção;
    * [x] concatenação: geram cadeias de caracteres
        * [x] tipos caracteres e cadeias de caracteres;
        * [x] tipos numéricos e booleanos: se concatenados a um caractere ou cadeia decaracteres, devem ser
          convertidos para cadeia de caracteres.

* [x] **Para o item acima, especificar ordem de precedência e associatividade.**

* [x] _Instruções_:
    * estrutura condicional de uma e duas vias;
    * estrutura iterativa com controle lógico;
    * estrutura iterativa controlada por contador com passo igual a um caso omitido;
    * entrada: deve permitir entrada de mais de uma variável em uma única instrução;
    * saída:
        * deve permitir mais de uma variável/constante literal em uma única instrução;
        * deve permitir minimamente formatação opcional de tamanho do campo e, para ponto flutuante, número de
          casas decimais (_default_: 2 casas); quando presente, o formato deve preceder o elemento a ser
          impresso.

* [ ] **Para o item acima, especificar natureza e formas de controle.**
    * Isso signica o que mesmo?

* [x] _Atribuição pode ser instrução ou operador (especificar)_

* [x] _Funções_

### Exemplos a serem criados
* [x] _Incluir os seguintes programas exemplos, que deverão ser testados usando o analisador léxico_:
    * alô mundo;
    * listar os elementos da série de Fibonacci até um valor limite, separados por vírgula, implementada em
      uma função usando iteração com controle lógico; o limite deve ser lido no programa principal;
    * _shell sort_, implementado em uma função usando iteração controlada por contador, em um arranjo cujos
      valores devem ser lidos e listados no programa principal; também no programa principal listar os valores
      ordenados.

Especificação dos Tokens (entrega: 05/03)
------

* [ ] _Especificar a linguagem de programação em que os analisadores léxico e sintático serão implementados._

* [ ] _Especificar a enumeração com as categorias dos **tokens** a ser **obrigatoriamente** usada nos 
  analisadores léxico e sintático, usando a sintaxe da linguagem escolhida para a implementação dos 
  analisadores; nomes simbólicos de até 10 caracteres._

* [ ] _Especificar em dois grupos **distintos**:_
    * tabela com nomes das expressões e as expressão regulares auxiliares, que não representam terminais
      da linguagem;
    * tabela com as categorias simbólicas dos _tokens_ e as expressões regulares dos lexemas correspondentes,
      que representam os terminais da linguagem. 

* A especificação das expressões regulares devem seguir a simplificação do padrão EBNF ISO/IEC 14977:
  1996(E) como usada em sala de aula (nomes sem espaços e sem vírgulas entre elementos das produções); as
  expressões regulares auxiliares funcionam como terminais das expressões associadas aos _tokens_, devendo
  estar entre apóstrofos (') ou aspas (").



Analisador Léxico (entrega: 14/03)
------
