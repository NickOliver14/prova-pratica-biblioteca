package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * Catalogo responsavel por fornecer a lista de exemplares disponiveis para emprestimo.
 * Nao conhece mais a classe concreta AcervoLegadoWS nem a matriz de strings legada.
 */
public class CatalogoExemplares {

    private final Acervo acervo;

    public CatalogoExemplares(Acervo acervo) {
        this.acervo = acervo;
    }

    public List<Exemplar> listarDisponiveis() {
        List<Exemplar> disponiveis = new ArrayList<>();
        for (Exemplar item : acervo.consultarExemplares()) {
            if (item.isDisponivel()) {
                disponiveis.add(item);
            }
        }
        return disponiveis;
    }
}