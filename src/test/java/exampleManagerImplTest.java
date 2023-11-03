import edu.upc.dsa.Exceptions.exampleNoExisteException;
import edu.upc.dsa.Exceptions.usuarioNoExisteException;
import edu.upc.dsa.Producto;
import edu.upc.dsa.exampleManagerImpl;
import edu.upc.dsa.exampleManager;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Logger;

public class exampleManagerImplTest {

    final static Logger logger = Logger.getLogger(String.valueOf(exampleManagerImpl.class));
    exampleManager exampleManager;

    @Before
    public void setUp() throws usuarioNoExisteException {
        logger.info("--------Generando datos--------");
        exampleManager = new exampleManagerImpl();
        exampleManager.addProducto("Bocadillo", 3);
        exampleManager.addProducto("Cocacola", 1);
        exampleManager.addProducto("Croisant", 2);

        exampleManager.addUser("SuperPG@correo.com", "SuperPG", "TuMadre");
        exampleManager.addUser("SuperPG@correo.com", "SuperPG", "TuMadre");
        exampleManager.addUser("Jlarrinzal@correo.com", "Zipi", "1234");
        exampleManager.addUser("christianL@correo.com", "Zape", "1234");
    }

    @After
    public void tearDown(){
        logger.info("--------Final Tests--------");
    }

    @Test
    public void testSortList(){
        logger.info("--------Sort list--------");
        exampleManager.sortList();
        Assert.assertEquals(3, this.exampleManager.sizeProductos());
    }

    @Test
    public void testLUsuarios() throws usuarioNoExisteException {
        logger.info("--------Comprobando lista usuarios--------");
        Assert.assertEquals(3, this.exampleManager.sizeUsers());
        Assert.assertEquals("SuperPG", exampleManager.getUser("SuperPG@correo.com").getNombre());
    }

    @Test
    public void testComanda() throws exampleNoExisteException, usuarioNoExisteException {
        logger.info("--------Comprobando Comanda--------");
        LinkedList<Producto> productos = new LinkedList<>();
        productos.add(exampleManager.getProducto("Cocacola"));
        productos.add(exampleManager.getProducto("Croisant"));
        productos.add(exampleManager.getProducto("Bocadillo"));
        exampleManager.getUser("christianL@correo.com");
        exampleManager.addComanda(productos, exampleManager.getUser("christianL@correo.com"));
        Assert.assertEquals(1, exampleManager.sizeComanda());

        productos.clear();
        productos.add(exampleManager.getProducto("Cocacola"));
        productos.add(exampleManager.getProducto("Cocacola"));
        productos.add(exampleManager.getProducto("Cocacola"));
        productos.add(exampleManager.getProducto("Bocadillo"));
        exampleManager.addComanda(productos, exampleManager.getUser("christianL@correo.com"));
        Assert.assertEquals(2, exampleManager.sizeComanda());

        exampleManager.servirComanda();
        Assert.assertEquals(1, exampleManager.sizeComanda());

        logger.info("--------Best Sellers--------");
        exampleManager.sortBestSellers();

        logger.info("--------Comandas completadas--------");
        exampleManager.comandaCompletadaUser(exampleManager.getUser("christianL@correo.com"));
    }
}
