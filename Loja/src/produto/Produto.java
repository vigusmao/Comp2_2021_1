package produto;

import controle.Dimensoes;
import controle.Transportavel;
import controle.Vendavel;

import java.awt.*;

public abstract class Produto implements Vendavel, Transportavel {

    // o identificador único do produto
    private final long codigo;

    private String descricao;

    private Image imagem;

    private float precoSugerido;

    private int pesoEmGramas;

    private Dimensoes dimensoes;

    public Produto(long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoSugerido = getPrecoDefault();
    }

    public long getCodigo() {
        return codigo;
    }

    @Override
    public int getPesoEmGramas() {
        return pesoEmGramas;
    }

    public void setPesoEmGramas(int pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }

    @Override
    public Dimensoes getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(Dimensoes dimensoes) {
        this.dimensoes = dimensoes;
    }

    public String getDescricao() {
        return this.descricao;
    }

    protected abstract float getPrecoDefault();

    public float getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(float precoSugerido) {
        this.precoSugerido = precoSugerido;
    }
}
