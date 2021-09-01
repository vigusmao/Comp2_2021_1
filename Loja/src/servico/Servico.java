package servico;

import controle.Pessoa;
import controle.Vendavel;

public class Servico implements Vendavel {

    private long codigo;

    private Pessoa profissionalResponsavel;

    private int duracaoEstimadaEmHoras;

    private String descricao;

    private float precoSugerido;

    public Servico(long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public long getCodigo() {
        return codigo;
    }

    public int getDuracaoEstimadaEmHoras() {
        return duracaoEstimadaEmHoras;
    }

    public void setDuracaoEstimadaEmHoras(int duracaoEstimadaEmHoras) {
        this.duracaoEstimadaEmHoras = duracaoEstimadaEmHoras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(float precoSugerido) {
        this.precoSugerido = precoSugerido;
    }
}
