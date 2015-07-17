package kata.webstore;

public class CartItem
{
    private int units;
    private Product product;

    public CartItem(Product product, int units)
    {
        this.product = product;
        this.units = units;
    }

    public int getUnits()
    {
        return units;
    }

    public Product getProduct()
    {
        return product;
    }
}
