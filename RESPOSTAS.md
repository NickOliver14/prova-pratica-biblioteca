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


**Princípio SOLID violado:**


**Classes afetadas (pelo nome):**


---

# Etapa 4 — Justificativa

## Padrão aplicado ao Problema 1

**O problema que ele resolve (descreva antes de nomear o padrão):**


**Nome do padrão:**


**Alternativa descartada e por que ela não resolveria por completo:**


## Padrão aplicado ao Problema 2

**O problema que ele resolve (descreva antes de nomear o padrão):**


**Nome do padrão:**


**Alternativa descartada e por que ela não resolveria por completo:**
