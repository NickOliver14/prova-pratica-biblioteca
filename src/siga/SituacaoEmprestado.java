package siga;

public class SituacaoEmprestado implements SituacaoEmprestimo {

    @Override
    public void emprestar(Emprestimo emprestimo) {
        throw new IllegalStateException(
                "Nao e possivel emprestar em " + getNome() + ".");
    }

    @Override
    public void restituir(Emprestimo emprestimo) {
        emprestimo.setSituacao(new SituacaoEncerrado());
    }

    @Override
    public void anular(Emprestimo emprestimo) {
        throw new IllegalStateException(
                "Nao e possivel anular em " + getNome() + ".");
    }

    @Override
    public String getNome() {
        return "EMPRESTADO";
    }
}