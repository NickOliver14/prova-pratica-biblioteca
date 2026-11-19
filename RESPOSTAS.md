# Respostas — Sistema de Emprestimo da Biblioteca

Nome completo: Nicolas Alves de Oliveira  RA: 3111392523005

> Preencha **todos** os campos. Campo vazio é campo não avaliado.
> Não apague os títulos nem os rótulos em negrito: a correção procura por eles.

---

# Etapa 1 — Diagnóstico

## Problema 1 — a conversão do acervo central

**Em que consiste o problema, e o que acontece quando o sistema cresce:**
A lógica para ler e converter os dados que vêm do sistema legado (uma matriz "String[][]") em objetos "Exemplar" está duplicada em "CatalogoExemplares" e "RelatorioColecao" e por estar repetida, essa regra acabou ficando diferente nos dois lugares: o relatório usa "equalsIgnoreCase("D")" para aceitar letras minúsculas, mas o catálogo continua usando "equals("D")". 
Na prática, isso gera inconsistência — o exemplar "TOM-2003", que vem com "d", aparece como disponível no relatório e indisponível no catálogo e conforme o sistema cresce, qualquer nova classe que precisar desses dados vai acabar copiando essa conversão de novo, multiplicando erros, divergências e o trabalho de manutenção.

**Princípio SOLID violado:**
Viola o Princípio da Responsabilidade Única (SRP), pois "CatalogoExemplares" e "RelatorioColecao" acumulam a própria regra de negócio com a tarefa de interpretar os dados crus do sistema externo e  também viola o Princípio da Inversão de Dependência (DIP), já que essas classes dependem diretamente da classe concreta "AcervoLegadoWS", em vez de dependerem de uma interface (abstração).


**Classes afetadas (pelo nome):**
**Que outra dependência indevida essas classes têm em relação ao
acervo central, e por que ela é um problema:**
Além de duplicarem a conversão, essas classes dependem diretamente da classe concreta "AcervoLegadoWS" e do formato exato que ela retorna e sabendo que é uma matriz de texto onde a posição 0 é o código, 1 é a descrição e 2 é a situação.
Isso cria um acoplamento forte com um serviço externo que o sistema não controla, se o legado mudar a ordem dessas colunas, o tipo de retorno ou migrar para outro formato (como REST/JSON), todas as classes que consomem esses dados vão quebrar e precisarão ser alteradas. Esse acoplamento também dificulta escrever testes unitários isolados e impede trocar a fonte de dados no futuro sem mexer nas regras de negócio.


## Problema 2 — o ciclo de vida de Emprestimo

**Em que consiste o problema, e o que acontece quando o sistema cresce:**
O ciclo de vida de "Emprestimo" é controlado usando textos soltos ("RESERVADO", "EMPRESTADO", "ENCERRADO") e vários "if/else" espalhados pelos métodos "emprestar()", "restituir()" e "anular()". Com as regras de transição distribuídas desse jeito, fica difícil enxergar o fluxo completo em um só lugar e além disso, usar "String" para representar estados abre margem para erros de digitação que só são descobertos quando o sistema já está rodando e conforme o sistema cresce e surgem novos estados (como "ATRASADO", "RENOVADO" ou "EXTRAVIADO") ou novas operações (como "renovar()" e "cobrarMulta()"), será preciso alterar todos esses métodos para encaixar novos "if/else", deixando o código cada vez mais complexo e fácil de quebrar o que já funcionava.

**Princípio SOLID violado:**
Viola o Princípio Aberto/Fechado (OCP), porque a classe "Emprestimo" precisa ter seus métodos modificados toda vez que um novo estado ou regra de transição é adicionado, em vez de permitir que o comportamento seja estendido sem mexer no código existente e também viola o Princípio da Responsabilidade Única (SRP), já que "Emprestimo" guarda seus próprios dados e, ao mesmo tempo, concentra todas as regras e validações de cada estado.

**Classes afetadas (pelo nome):**
"Emprestimo".

---

# Etapa 4 — Justificativa

## Padrão aplicado ao Problema 1

**O problema que ele resolve (descreva antes de nomear o padrão):**
Ele resolve a incompatibilidade entre o que o sistema precisa (uma lista de objetos "Exemplar") e o que o serviço legado entrega (uma matriz "String[][]") e por isso ele faz a ponte entre os dois lados, traduzindo a interface e os dados crus do legado para uma interface padronizada que o restante do sistema espera e assim, a lógica de conversão fica centralizada em um único lugar e as classes consumidoras deixam de depender diretamente do serviço legado.

**Nome do padrão:**
"Adapter".

**Alternativa descartada e por que ela não resolveria por completo:**
No caso alterar diretamente a classe "AcervoLegadoWS" por três motivos: o primeiro, porque trata-se de um serviço legado de outra equipe cujo código não podemos modificar; o segundo pois mudar o formato de retorno quebraria outros sistemas antigos que também dependem dessa matriz "String[][]"; e terceiro, porque mesmo que fosse possível alterar o legado para retornar "Exemplar", as classes de negócio continuariam acopladas diretamente a uma classe concreta externa em vez de dependerem de uma interface, mantendo a violação do Princípio da Inversão de Dependência (DIP).


## Padrão aplicado ao Problema 2

**O problema que ele resolve (descreva antes de nomear o padrão):**
Ele resolve o excesso de "if/else" espalhados que controlam o comportamento de um objeto dependendo do estado em que ele se encontra e ele separa cada estado e suas regras de transição em classes próprias, fazendo com que a classe principal apenas delegue as ações para o estado atual.

**Nome do padrão:**
"State"(estado).


**Alternativa descartada e por que ela não resolveria por completo:**
Seria Trocar a "Strin"g" por um "enum" simples (como "enum Situacao { RESERVADO, EMPRESTADO, ENCERRADO" }). Embora o "enum" evite erros de digitação, ele não elimina os "if/else" ou "switch" espalhados pelos métodos "emprestar()", "restituir()" e "anular()". As regras continuariam espalhadas dentro de "Emprestimo" e, sempre que um novo estado fosse criado, ainda seria preciso alterar todos esses métodos, mantendo a violação do Princípio Aberto/Fechado (OCP). Além disso, essa abordagem não cumpriria o requisito de deixar os métodos de transição com apenas uma linha e sem "if", nem deixaria o ciclo de vida organizado em classes separadas por fase.