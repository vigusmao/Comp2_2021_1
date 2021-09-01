package exception;

public class EstoqueInsuficienteException extends Exception {  // checked

    private int quantidadeEmEstoque;

    public EstoqueInsuficienteException(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
}
