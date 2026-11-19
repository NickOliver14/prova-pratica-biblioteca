package siga;

import java.util.ArrayList;

import java.util.List;

public class AcervoAdapter implements Acervo {

    private final AcervoLegadoWS legado;

    public AcervoAdapter(AcervoLegadoWS legado) {
        this.legado = legado;
    }

    @Override
    public List<Exemplar> consultarExemplares() {
        List<Exemplar> exemplares = new ArrayList<>();
        for (String[] linha : legado.consultarExemplares()) {
            Exemplar item = new Exemplar(linha[0], linha[1]);
            item.setDisponivel("D".equalsIgnoreCase(linha[2]));
            exemplares.add(item);
        }
        return exemplares;
    }
}