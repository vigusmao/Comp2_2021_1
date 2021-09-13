package controle;

public class Recibo<V extends Vendavel> implements Vendavel, Comparable<Recibo> {

    private final V vendavel;

    private final int quantidade;

    private final Usuario comprador;

    private final float valorTotal;

    public Recibo(V vendavel, int quantidade, Usuario comprador, float valorTotal) {
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

    @Override
    public long getCodigo() {
        return 0;
    }

    @Override
    public float getPrecoSugerido() {
        return (float) (10 + 0.01 * this.valorTotal);
    }

    @Override
    public String getDescricao() {
        return null;
    }

    @Override
    public int compareTo(Recibo o) {
        return Float.compare(this.valorTotal, o.valorTotal);
    }
}
