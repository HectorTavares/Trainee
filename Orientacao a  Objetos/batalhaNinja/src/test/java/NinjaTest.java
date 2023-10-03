import entities.Batalha;
import entities.Jutsu;
import entities.Ninja;
import org.junit.Assert;
import org.junit.Test;


public class NinjaTest {


    @Test
    public void deveRetornarNinjaComJutsuMaisForteSeOsDoisGastamOMesmoChakra() {
        //Arrange
        Jutsu jutsuNinjaUm = new Jutsu(5, 10);
        Ninja ninjaUm = new Ninja("Naruto", jutsuNinjaUm);

        Jutsu jutsuNinjaDois = new Jutsu(5, 8);
        Ninja ninjaDois = new Ninja("Gaara", jutsuNinjaDois);

        Batalha batalha = new Batalha();

        //Act
        Ninja ninjaVencedor = batalha.lutar(ninjaUm, ninjaDois);

        //Assert
        Assert.assertSame(ninjaUm, ninjaVencedor);
    }

    @Test
    public void deveAtualizarOChakraDoOponenteDeAcordoComODanoDoJutsoQuandoAtacar() {
        //Arrange
        Jutsu jutsuNinjaAtacante = new Jutsu(5, 10);
        Ninja ninjaAtacante = new Ninja("Naruto", jutsuNinjaAtacante);

        Jutsu jutsuNinjaOponente = new Jutsu(5, 8);
        Ninja ninjaOponente = new Ninja("Gaara", jutsuNinjaOponente);

        int nivelChakraEsperado = 90;
        //Act
        ninjaAtacante.atacar(ninjaOponente);

        //Assert
        Assert.assertEquals(nivelChakraEsperado, ninjaOponente.getChakra());
    }

    @Test
    public void deveRetornarPrimeiroNinjaComoVencedorQuandoONomeForItachi() {
        //Arrange
        Jutsu jutsuNinjaUm = new Jutsu(5, 1);
        Ninja ninjaUm = new Ninja("Itachi", jutsuNinjaUm);

        Jutsu jutsuNinjaDois = new Jutsu(1, 10);
        Ninja ninjaDois = new Ninja("Gaara", jutsuNinjaDois);

        Batalha batalha = new Batalha();

        //Act
        Ninja ninjaVencedor = batalha.lutar(ninjaUm, ninjaDois);

        //Assert
        Assert.assertSame(ninjaUm, ninjaVencedor);
    }

    @Test
    public void deveRetornarSegundoNinjaComoVencedorQuandoONomeForItachi() {
        //Arrange
        Jutsu jutsuNinjaUm = new Jutsu(1, 10);
        Ninja ninjaUm = new Ninja("Sasuke", jutsuNinjaUm);

        Jutsu jutsuNinjaDois = new Jutsu(5, 2);
        Ninja ninjaDois = new Ninja("Itachi", jutsuNinjaDois);

        Batalha batalha = new Batalha();

        //Act
        Ninja ninjaVencedor = batalha.lutar(ninjaUm, ninjaDois);

        //Assert
        Assert.assertSame(ninjaDois, ninjaVencedor);
    }

    @Test
    public void deveRetornarPrimeiroNinjaComoVencedorQuandoEmpatar() {
        //Arrange
        Jutsu rasengan = new Jutsu(5, 10);
        Ninja ninjaUm = new Ninja("Naruto", rasengan);

        Jutsu chidori = new Jutsu(5, 10);
        Ninja ninjaDois = new Ninja("Sasuke", chidori);

        Batalha batalha = new Batalha();

        //Act
        Ninja ninjaVencedor = batalha.lutar(ninjaUm, ninjaDois);

        //Assert
        Assert.assertSame(ninjaUm, ninjaVencedor);
    }

    @Test
    public void deveRetornarSegundoNinjaComoVencedor(){
        //Arrange
        Jutsu jutsuNinjaUm = new Jutsu(5, 1);
        Ninja ninjaUm = new Ninja("Sasuke", jutsuNinjaUm);

        Jutsu jutsuNinjaDois = new Jutsu(1, 10);
        Ninja ninjaDois = new Ninja("Naruto", jutsuNinjaDois);

        Batalha batalha = new Batalha();

        //Act
        Ninja ninjaVencedor = batalha.lutar(ninjaUm, ninjaDois);

        //Assert
        Assert.assertSame(ninjaDois, ninjaVencedor);
    }

}
