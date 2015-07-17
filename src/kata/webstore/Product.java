package kata.webstore;

public class Product
{
    private int units;

    public void addUnits(int units)
    {

        this.units = units;
    }

    public boolean hasUnits(int units)
    {
        return this.units >= units;
    }

    public int getUnits()
    {
        return units;
    }

    public void reduceUnits(int units)
    {
        this.units -= units;
    }
}
