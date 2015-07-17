package kata.webstore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddProductToCartTest
{

    private FakeRepository repository;
    private AddProductToCartUseCase uc;

    @Before
    public void setUp() throws Exception
    {
        repository = new FakeRepository();
        uc = new AddProductToCartUseCase(repository);
        repository.returnedCustomer = new Customer();
        repository.returnedProduct = new Product();

        uc.withCustomerId("customer1");
        uc.withProductId("product1");
    }

    @Test
    public void successfulWithMoreThanEnoughInStock()
    {
        repository.returnedProduct.addUnits(7);
        uc.withUnits(5);

        uc.doIt();

        assertSuccessful();

        assertNotNull(repository.savedCustomer);
        Cart cart = repository.savedCustomer.getCart();
        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getUnits());

        assertNotNull(repository.savedProduct);
        assertEquals(2, repository.savedProduct.getUnits());
    }

    private void assertSuccessful()
    {
        assertTrue(uc.wasSuccessful());
        assertFalse(uc.notEnoughUnitsInStock());
    }

    @Test
    public void successfulWithJustEnoughUnitsInStock()
    {
        repository.returnedProduct.addUnits(6);
        uc.withUnits(6);

        uc.doIt();

        assertSuccessful();
        assertNotNull(repository.savedCustomer);
        Cart cart = repository.savedCustomer.getCart();
        assertEquals(1, cart.getItems().size());
        assertEquals(6, cart.getItems().get(0).getUnits());
        assertEquals(repository.savedProduct, cart.getItems().get(0).getProduct());

        assertNotNull(repository.savedProduct);
        assertEquals(0, repository.savedProduct.getUnits());
    }

    @Test
    public void notEnoughAvailableUnits()
    {
        repository.returnedProduct.addUnits(3);
        uc.withUnits(5);

        uc.doIt();

        assertFalse(uc.wasSuccessful());
        assertTrue(uc.notEnoughUnitsInStock());
        assertNull(repository.savedCustomer);
        assertNull(repository.savedProduct);
    }



    private static class FakeRepository implements Repository
    {
        Customer returnedCustomer;
        Customer savedCustomer;
        Product returnedProduct;
        Product savedProduct;

        @Override
        public Product getProductById(String id)
        {
            assertEquals("product1", id);
            return returnedProduct;
        }

        @Override
        public Customer getCustomerById(String id)
        {
            assertEquals("customer1", id);
            return returnedCustomer;
        }

        @Override
        public void saveProduct(Product product)
        {
            this.savedProduct = product;
        }

        @Override
        public void saveCustomer(Customer customer)
        {
            this.savedCustomer = customer;
        }
    }
}
