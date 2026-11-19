package siga;

import java.util.List;

/**
 * Relatorio da colecao completa de exemplares do acervo.
 * Nao conhece mais a classe concreta AcervoLegadoWS nem a matriz de strings legada.
 */
public class RelatorioColecao {

    private final Acervo acervo;

    public RelatorioColecao(Acervo acervo) {
        this.acervo = acervo;
    }

    public List<Exemplar> listarTodos() {
        return acervo.consultarExemplares();
    }

    public void imprimir() {
        System.out.println("   Situacao segundo o relatorio:");
        for (Exemplar item : listarTodos()) {
            System.out.println("     " + item + " - "
                    + (item.isDisponivel() ? "disponivel" : "baixado"));
        }
    }
}