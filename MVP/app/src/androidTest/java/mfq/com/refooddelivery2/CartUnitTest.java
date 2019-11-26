package mfq.com.refooddelivery2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Product;
import mfq.com.refooddelivery2.product_accessories.Price;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CartUnitTest {

    private static Cart cart = Cart.getInstance();

    @Before
    public void beforeTest(){
        cart.getProducts().clear();
    }

    @Test
    public void addItemToCart(){
        cart.addProduct(new Product("Ozi", new Price(250.0), 5));
        assertEquals(1, cart.getProducts().size());

    }

    @Test
    public void addExistingItemToCart(){
        cart.addProduct(new Product("Ozi", new Price(250.0), 5));
        cart.addProduct(new Product("Ozi", new Price(250.0), 3));
        assertEquals(1, cart.getProducts().size());
        assertEquals(8, cart.getTotalQuantity());
    }

    @Test
    public void addNonExistingItemToCart(){
        cart.addProduct(new Product("Ozi", new Price(250.0), 5));
        cart.addProduct(new Product("Fari", new Price(250.0), 3));
        assertEquals(2, cart.getProducts().size());
        assertEquals(8, cart.getTotalQuantity());
    }

    @Test
    public void checkOutPrice(){
        cart.addProduct(new Product("Ozi", new Price(250.0), 5));
        cart.addProduct(new Product("Fari", new Price(250.0), 3));
        assertEquals(0, Double.compare(cart.getTotalSum(), 2000.0));
    }

    @Test
    public void deleteItemFromCart(){
        Product ozi = new Product("Ozi", new Price(250.0), 5);
        cart.addProduct(ozi);
        cart.addProduct(new Product("Fari", new Price(250.0), 3));
        cart.deleteProduct(ozi);
        assertEquals(1, cart.getProducts().size());
        assertEquals(3, cart.getTotalQuantity());
        assertEquals(0, Double.compare(cart.getTotalSum(), 750.0));
    }

    


}