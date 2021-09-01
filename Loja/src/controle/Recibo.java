package controle;

public class Recibo {

    private final Vendavel vendavel;

    private final int quantidade;

    private final Usuario comprador;

    private final float valorTotal;

    public Recibo(Vendavel vendavel, int quantidade, Usuario comprador, float valorTotal) {
        this.vendavel = vendavel;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return String.format("Recibo no valor de R$%.2f referente à compra " +
                "de %d unidades de %s pelo comprador %s",
                valorTotal, quantidade, vendavel.getDescricao(), comprador.getNome());
    }
}
