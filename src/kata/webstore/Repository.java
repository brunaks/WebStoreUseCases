package kata.webstore;

public interface Repository
{
    Product getProductById(String id);
    Customer getCustomerById(String id);
    void saveProduct(Product product);
    void saveCustomer(Customer customer);
}
