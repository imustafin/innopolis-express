package mfq.com.refooddelivery2;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import mfq.com.refooddelivery2.helper.ApiManager;
import mfq.com.refooddelivery2.helper.GsonHelper;
import mfq.com.refooddelivery2.models.Cart;
import mfq.com.refooddelivery2.models.Product;

@RunWith(Parameterized.class)
public class ParametrizedCartTest {
    Cart cart;
    List<Product> products;

    public ParametrizedCartTest(Cart cart, List<Product> products) {
        this.cart = cart;
        this.products = products;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws IOException {
        List<Product> products = getProducts();
        Product p = new Product(products.get(0));
        p.setQuantity(3);
        List<Product> similarProducts = new ArrayList<>(Arrays.asList(p, products.get(0), products.get(1), products.get(0)));

        return Arrays.asList(new Object[][]{
            {
                Cart.getInstance(),
                similarProducts
            }
        });
    }

    public static List<Product> getProducts() throws IOException {
        byte[] is = Files.readAllBytes(Paths.get("src/main/assets/json_data"));
        String json = new String(is, "UTF-8");
        return GsonHelper.parseGsonArray(json, Product[].class);
    }

    @Before
    public void clearCart(){
        cart.getProducts().clear();
    }

    @Test
    public void productQuantityTest(){
        int expectedQuantity = 0;
        int actualQuantity = 0;

        for (Product product:
            products) {
            expectedQuantity += product.getQuantity();
            cart.addProduct(product);
        }

        for (Product product : cart.getProducts()) {
            actualQuantity += product.getQuantity();
        }

        Assert.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    public void cartAddItemTest() {
        cart.addProduct(products.get(0));

        Assert.assertEquals(1, cart.getProducts().size());
    }

    @Test
    public void cartSizeTest() {
        Set<String> uniqueNames = products.stream().map(Product::getName).collect(Collectors.toSet());

        products.forEach(x -> cart.addProduct(x));

        Assert.assertEquals(uniqueNames.size(), cart.getProducts().size());
    }
}
