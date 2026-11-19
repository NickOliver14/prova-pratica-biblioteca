package siga;

public class Emprestimo {

    private final Leitor solicitante;
    private final Exemplar item;
    private SituacaoEmprestimo situacao;

    public Emprestimo(Leitor solicitante, Exemplar item) {
        this.solicitante = solicitante;
        this.item = item;
        this.situacao = new SituacaoReservado();
    }

    public String getSituacao() {
        return situacao.getNome();
    }

    void setSituacao(SituacaoEmprestimo situacao) {
        this.situacao = situacao;
    }

    public void emprestar() {
        situacao.emprestar(this);
    }

    public void restituir() {
        situacao.restituir(this);
    }

    public void anular() {
        situacao.anular(this);
    }

    public Leitor getSolicitante() {
        return solicitante;
    }

    public Exemplar getExemplar() {
        return item;
    }
}