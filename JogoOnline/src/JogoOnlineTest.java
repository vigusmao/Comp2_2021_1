import org.junit.Test;

import static org.junit.Assert.fail;

public class JogoOnlineTest {

    @Test
    public void testarLoginComUsuarioDesconhecido() {
        JogoOnline jogo = new JogoOnline();
        try {
            jogo.fazerLogin("UsuarioQualquerDesconhecido", "1234");
            fail("Uma UsuarioInexistenteException deve ser lançada se o username for desconhecido");

        } catch (UsuarioInexistenteException e) {
            // tudo bem, aconteceu o que eu esperava!!!
        }
    }

//    // equivalentemente...
//
//    @Test(expected = UsuarioInexistenteException.class)
//    public void testarLoginComUsuarioDesconhecido() {
//        JogoOnline jogo = new JogoOnline();
//        jogo.fazerLogin("UsuarioQualquerDesconhecido", "1234");
//    }
}
