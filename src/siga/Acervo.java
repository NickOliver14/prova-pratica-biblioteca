package siga;

import java.util.List;


public interface Acervo {

    /**
     * Consulta todos os exemplares disponiveis ou cadastrados no acervo.
     * @return lista de exemplares convertidos para o modelo de dominio.
     */
    List<Exemplar> consultarExemplares();
}