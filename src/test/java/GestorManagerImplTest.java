import edu.upc.eetac.dsa.Exceptions.juegoNoExisteException;
import edu.upc.eetac.dsa.Exceptions.usuarioNoExisteException;
import edu.upc.eetac.dsa.Juego;
import edu.upc.eetac.dsa.GestorManagerImpl;
import edu.upc.eetac.dsa.GestorManager;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedList;
import java.util.logging.Logger;

public class GestorManagerImplTest {

    final static Logger logger = Logger.getLogger(String.valueOf(GestorManagerImpl.class));
    GestorManager gestorManager;

    @Before
    public void setUp() throws usuarioNoExisteException {
        logger.info("--------Generando datos--------");
        gestorManager = new GestorManagerImpl();
        gestorManager.nuevoJuego("j0001", "juego 1",10);
        gestorManager.nuevoJuego("j0002", "juego 2",20);
        gestorManager.nuevoJuego("j0003", "juego 3",30);

        gestorManager.addUsuario("mikel");
        gestorManager.addUsuario("clara");
        gestorManager.addUsuario("marie");
        gestorManager.addUsuario("cristina");
    }

    @After
    public void tearDown(){
        logger.info("--------Final Tests--------");
    }

    @Test
    public void testLUsuarios() throws usuarioNoExisteException {
        logger.info("--------Comprobando lista usuarios--------");
        Assert.assertEquals(3, this.gestorManager.tamanoUsuarios());
        Assert.assertEquals("mikel", gestorManager.getUsuario("mikel").getIdUsuario());
    }

    @Test
    public void testComanda() throws juegoNoExisteException, usuarioNoExisteException {
        logger.info("--------Comprobando Juego--------");
        LinkedList<Juego> juegos = new LinkedList<>();
        juegos.add(gestorManager.getJuego("j0001"));
        juegos.add(gestorManager.getJuego("j0002"));
        juegos.add(gestorManager.getJuego("j0003"));
        gestorManager.getUsuario("mikel");
        Assert.assertEquals(1, gestorManager.tamanoJuego());

        juegos.clear();
        juegos.add(gestorManager.getJuego("j0001"));
        juegos.add(gestorManager.getJuego("j0002"));
        juegos.add(gestorManager.getJuego("j0003"));
        juegos.add(gestorManager.getJuego("j0004"));
        Assert.assertEquals(2, gestorManager.tamanoJuego());
    }
}
