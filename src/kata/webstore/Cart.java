package kata.webstore;

import java.util.ArrayList;
import java.util.List;

public class Cart
{

    private final ArrayList<CartItem> items;

    public Cart() {items = new ArrayList<>();}

    public List<CartItem> getItems()
    {
        return items;
    }

    public void add(Product product, int units)
    {
        items.add(new CartItem(product, units));

    }
}
