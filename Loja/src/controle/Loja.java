package controle;

import exception.*;
import veiculo.Caminhao;

import java.util.HashMap;
import java.util.HashSet;

public class Loja {

    private Pessoa dono;

    private HashMap<Long, Vendavel> vendavelByCodigo;

    private HashMap<Vendavel, Integer> quantidadeByVendavel;

    private HashSet<Usuario> usuariosBanidos;

    private Caminhao caminhao;

    public Loja() {
        this.vendavelByCodigo = new HashMap<>();
        this.quantidadeByVendavel = new HashMap<>();
        this.usuariosBanidos = new HashSet<>();
    }

    /**
     * Queremos acrescentar quantidades de um vendavel ao estoque da loja.
     *
     * Se o vendavel já existir no estoque, acrescentamos a quantidade informada
     * à quantidade já existente daquele vendavel no estoque da loja;
     * caso contrário, incluiremos o vendavel no estoque pela primeira vez,
     * com a quantidade informada.
     *
     * @param vendavel O vendavel desejado
     * @param quantidade A quantidade do vendavel a ser acrescentada ao estoque
     */
    public void incluirVendavelNoEstoque(Vendavel vendavel, int quantidade) {
        this.vendavelByCodigo.put(vendavel.getCodigo(), vendavel);
        this.quantidadeByVendavel.put(vendavel,
                getQuantidadeEmEstoque(vendavel) + quantidade);
    }

    public Vendavel encontrarVendavel(long codigo) {
        return this.vendavelByCodigo.get(codigo);
    }

    /**
     * Retorna a quantidade de itens em estoque para o vendavel informado.
     *
     * @param vendavel O vendavel que se deseja consultar
     * @return a quantidade em estoque;
     *         ou 0, se estiver em falta ou se a loja não o vender
     *
     * @throws EstoqueInsuficienteException se não houver em estoque a quantidade TOTAL desejada
     *
     */
    public int getQuantidadeEmEstoque(Vendavel vendavel) {
        return this.quantidadeByVendavel.getOrDefault(vendavel, 0);
    }

    public void banirUsuario(Usuario usuario) {
        this.usuariosBanidos.add(usuario);
    }

    /**
     * Vende um vendavel.
     *
     * @param vendavel o vendavel desejado
     * @param quantidade a quantidade desejada
     * @param comprador o comprador
     * @return
     * @throws EstoqueInsuficienteException se não houver o vendavel em estoque
     * @throws EnderecoInvalidoException se o comprador não tiver endereço cadastrado
     * @throws PagamentoException se houver qualquer problema na hora de pagar
     */
    public Recibo efetuarVenda(Vendavel vendavel, int quantidade, Usuario comprador)
            throws EstoqueInsuficienteException, EnderecoInvalidoException, PagamentoException {

        int quantidadeEmEstoque = getQuantidadeEmEstoque(vendavel);

        if (quantidadeEmEstoque < quantidade) {
            throw new EstoqueInsuficienteException(quantidadeEmEstoque);
        }

        if (comprador.getEndereco() == null) {
            throw new EnderecoInvalidoException();
        }

        float valorTotal = vendavel.getPrecoSugerido() * quantidade;
        try {
            if (valorTotal > 0) {
                receberPagamento(comprador, valorTotal);
            }
        } catch (UsuarioBanidoException|CartaoNaoAutorizadoException e) {
            throw new PagamentoException();
        }

        // atualiza o estoque
        this.quantidadeByVendavel.put(vendavel, quantidadeEmEstoque - quantidade);

        if (vendavel instanceof Transportavel) {
            try {
                entregar(this.caminhao, (Transportavel) vendavel, quantidade, comprador.getEndereco());
                Pessoa fulana = null;
                fulana.setNome("Fulana");

            } catch (PesoMaximoExcedidoException|VolumeMaximoExcedidoException e) {

                // blablalblab




            } finally {

                /* o código que estiver aqui será executado
                   NO MATTER WHAT!!!!!
                   Aconteça o que acontecer!!!!!

                */
            }
        }

        // prepara o recibo
        Recibo recibo = new Recibo(vendavel, quantidade, comprador,
                valorTotal);

        return recibo;
    }

    private void entregar(Transportador transportador, Transportavel transportavel,
                          int quantidade, String endereco)
            throws PesoMaximoExcedidoException, VolumeMaximoExcedidoException {

        transportador.transportar(transportavel, endereco);

    }

    private void receberPagamento(Usuario comprador, float valor)
            throws CartaoNaoAutorizadoException, UsuarioBanidoException {

        if (this.usuariosBanidos.contains(comprador)) {
            throw new UsuarioBanidoException();
        }

        if (!autorizarCartao(comprador.getNumeroCartaoDeCredito())) {
            throw new CartaoNaoAutorizadoException();
        }

        if (valor <= 0) {
            throw new IllegalArgumentException("Cobrança de valor não-positivo");
            // runtime exception!
        }

        // ToDo IMPLEMENT ME!!!
    }

    private boolean autorizarCartao(long numeroDoCartao) {
        // ToDo IMPLEMENT ME!!!

        return true;
    }
}
