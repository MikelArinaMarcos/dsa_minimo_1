import edu.upc.dsa.Exceptions.juegoNoExisteException;
import edu.upc.dsa.Exceptions.usuarioNoExisteException;
import edu.upc.dsa.Juego;
import edu.upc.dsa.GestorManagerImpl;
import edu.upc.dsa.GestorManager;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;

public class GestorManagerImplTest {

    final static Logger logger = Logger.getLogger(String.valueOf(GestorManagerImpl.class));
    GestorManager gestorManager;

    @Before
    public void setUp() throws usuarioNoExisteException {
        logger.info("--------Generando datos--------");
        gestorManager = new GestorManagerImpl();
        gestorManager.addJuego("j0001", 10);
        gestorManager.addJuego("j0002", 20);
        gestorManager.addJuego("j0003", 30);

        gestorManager.addUser("mikel@correo.com", "mikel", "arina");
        gestorManager.addUser("arina@correo.com", "arina", "mikel");
        gestorManager.addUser("mikelarina@correo.com", "mikelarina", "arinamikel");
        gestorManager.addUser("mikelarinamarcos@correo.com", "mikelarinamarcos", "marcosarinamikel");
    }

    @After
    public void tearDown(){
        logger.info("--------Final Tests--------");
    }

    @Test
    public void testSortList(){
        logger.info("--------Sort list--------");
        GestorManager.sortList();
        Assert.assertEquals(3, this.gestorManager.sizeJuegos());
    }

    @Test
    public void testLUsuarios() throws usuarioNoExisteException {
        logger.info("--------Comprobando lista usuarios--------");
        Assert.assertEquals(3, this.gestorManager.sizeUsers());
        Assert.assertEquals("mikel", gestorManager.getUser("mikel@correo.com").getName());
    }

    @Test
    public void testComanda() throws juegoNoExisteException, usuarioNoExisteException {
        logger.info("--------Comprobando Juego--------");
        LinkedList<Juego> juegos = new LinkedList<>();
        juegos.add(gestorManager.getJuego("j0001"));
        juegos.add(gestorManager.getJuego("j0002"));
        juegos.add(gestorManager.getJuego("j0003"));
        gestorManager.getUser("mikel@correo.com");
        gestorManager.addPartida(juegos, gestorManager.getUser("mikel@correo.com"));
        Assert.assertEquals(1, gestorManager.sizePartida());

        juegos.clear();
        juegos.add(gestorManager.getJuego("j0001"));
        juegos.add(gestorManager.getJuego("j0002"));
        juegos.add(gestorManager.getJuego("j0003"));
        juegos.add(gestorManager.getJuego("j0004"));
        Assert.assertEquals(2, gestorManager.sizePartida());

        gestorManager.finalizarPartida();
        Assert.assertEquals(1, gestorManager.sizePartida());

        logger.info("--------Partidas completadas--------");
        gestorManager.partidaCompletadaUser(gestorManager.getUser("mikel@correo.com"));
    }
}
