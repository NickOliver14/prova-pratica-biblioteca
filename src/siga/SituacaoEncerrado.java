package siga;

public class SituacaoEncerrado implements SituacaoEmprestimo {

    @Override
    public void emprestar(Emprestimo emprestimo) {
        throw new IllegalStateException(
                "Nao e possivel emprestar em " + getNome() + ".");
    }

    @Override
    public void restituir(Emprestimo emprestimo) {
        throw new IllegalStateException(
                "Nao e possivel restituir em " + getNome() + ".");
    }

    @Override
    public void anular(Emprestimo emprestimo) {
        throw new IllegalStateException(
                "Nao e possivel anular em " + getNome() + ".");
    }

    @Override
    public String getNome() {
        return "ENCERRADO";
    }
}