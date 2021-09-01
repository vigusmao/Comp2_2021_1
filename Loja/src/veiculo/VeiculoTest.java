package veiculo;

import controle.Pessoa;
import exception.PesoMaximoExcedidoException;
import exception.VolumeMaximoExcedidoException;
import org.junit.Test;
import produto.Livro;

import static org.junit.Assert.*;

public class VeiculoTest {

    @Test
    public void testarTransporte() throws PesoMaximoExcedidoException, VolumeMaximoExcedidoException {
        Caminhao caminhao = new Caminhao();

        Livro livro = new Livro(12345, "Meu Livro", "Julio Verne", "Editora");
        Pessoa pessoa = new Pessoa(87436, "Fulana");

        caminhao.transportar(livro, "Rua Tal, 12344");
        caminhao.transportar(pessoa, "Rua Tal, 12344");

    }


}