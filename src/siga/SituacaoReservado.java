package siga;

public class SituacaoReservado implements SituacaoEmprestimo {

    @Override
    public void emprestar(Emprestimo emprestimo) {
        emprestimo.setSituacao(new SituacaoEmprestado());
    }

    @Override
    public void restituir(Emprestimo emprestimo) {
        throw new IllegalStateException(
                "Nao e possivel restituir em " + getNome() + ".");
    }

    @Override
    public void anular(Emprestimo emprestimo) {
        emprestimo.setSituacao(new SituacaoEncerrado());
    }

    @Override
    public String getNome() {
        return "RESERVADO";
    }
}