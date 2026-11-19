package siga;

public interface SituacaoEmprestimo {

    void emprestar(Emprestimo emprestimo);

    void restituir(Emprestimo emprestimo);

    void anular(Emprestimo emprestimo);

    String getNome();
}