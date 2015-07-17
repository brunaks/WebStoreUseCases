package kata.webstore;

public class AddProductToCartUseCase
{
    private Repository repository;
    private boolean successful;
    private boolean notEnoughUnitsInStock;
    private String customerId;
    private String productId;
    private int units;

    public AddProductToCartUseCase(Repository repository)
    {

        this.repository = repository;
    }

    public void withCustomerId(String customerId)
    {

        this.customerId = customerId;
    }

    public void withProductId(String productId)
    {

        this.productId = productId;
    }

    public void withUnits(int units)
    {

        this.units = units;
    }

    public void doIt()
    {

        Product p = repository.getProductById(productId);

        successful = p.hasUnits(units);
        notEnoughUnitsInStock = !successful;

        if (successful)
        {
            Customer customer = new Customer();
            customer.getCart().add(p, units);
            repository.saveCustomer(customer);
            p.reduceUnits(units);
            repository.saveProduct(p);
        }
    }

    public boolean wasSuccessful()
    {
        return successful;
    }

    public boolean notEnoughUnitsInStock()
    {
        return notEnoughUnitsInStock;
    }
}
