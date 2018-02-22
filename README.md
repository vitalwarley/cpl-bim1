

Trabalho de Compiladores [IC-UFAL] [2017.2: 1BIM]
=======

Especificação Mínima da Linguagem (entrega: 05/03)
------

* [ ] _A linguagem deve poder ser analisada em um passo único_: ``... mais detalhes?``

* _Como regra geral, seguir a forma de análise dos trabalhos de CLP_ 

* _Especificar a **estrutura** geral de um programa, indicando onde podem ser declaradas funções, variáveis,
  instruções_

* _A especificação de tipos deve ser estática, com no mínimo o seguinte conjunto de tipos de dados:_
    * inteiro;
    * ponto flutuante;
    * caractere;
    * booleano;
    * cadeia de caracteres;
    * arranjos unidimensionais.

* **Para o item acima, especificar quais as operações de cada tipo.**

* _Especificar as constantes literais de cada tipo_

* _Conjunto **mínimo** de operadores:_
    * aritméticos: para tipos numéricos
        * aditivos, multiplicativos;
        * unário negativo;
    * relacionais:
        * para tipos numéricos, caracteres e cadeias de caracteres: todos;
        * para tipos booleanos: igualdade e desigualdade;
    * lógicos: para o tipo booleano
        * negação, conjunção e disjunção;
    * concatenação: geram cadeias de caracteres
        * tipos caracteres e cadeias de caracteres;
        * tipos numéricos e booleanos: se concatenados a um caractere ou cadeia decaracteres, devem ser
          convertidos para cadeia de caracteres.

* **Para o item acima, especificar ordem de precedência e associatividade.**

* _Instruções_:
    * estrutura condicional de uma e duas vias;
    * estrutura iterativa com controle lógico;
    * estrutura iterativa controlada por contador com passo igual a um caso omitido;
    * entrada: deve permitir entrada de mais de uma variável em uma única instrução;
    * saída:
        * deve permitir mais de uma variável/constante literal em uma única instrução;
        * deve permitir minimamente formatação opcional de tamanho do campo e, para ponto flutuante, número de
          casas decimais (_default_: 2 casas); quando presente, o formato deve preceder o elemento a ser
          impresso.

* **Para o item acima, especificar natureza e formas de controle.**

* _Atribuição pode ser instrução ou operador (especificar)_

* _Funções_

* _Incluir os seguintes programas exemplos, que deverão ser testados usando o analisador léxico_:
    * alô mundo;
    * listar os elementos da série de Fibonacci até um valor limite, separados por vírgula, implementada em
      uma função usando iteração com controle lógico; o limite deve ser lido no programa principal;
    * _shell sort_, implementado em uma função usando iteração controlada por contador, em um arranjo cujos
      valores devem ser lidos e listados no programa principal; também no programa principal listar os valores
      ordenados.

Especificação dos Tokens (entrega: 05/03)
------

Analisador Léxico (entrega: 14/03)
------
