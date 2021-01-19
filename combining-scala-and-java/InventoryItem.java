/**
 * Part of the Inventory model, which is all written in Java.
 */
public class InventoryItem 
{
    private final String name;
    private final int count;
    private final int price;

    public InventoryItem(String name, int count, int price) 
    {
	this.name = name;
	this.count = count;
	this.price = price;
    }

    public int getCount() 
    {
	return count;
    }
    
    public String getName() 
    {
	return name;
    }
    
    public int getPriceInCents() 
    {
	return price;
    }

    @Override
    public String toString() 
    {
	return name + "($" + (price/100) + "." + (price%100) + ")";
    }

    public boolean equals(Object that) 
    {
	if (that instanceof InventoryItem) {
	    InventoryItem thatItem = (InventoryItem) that;
	    
	    return this.getName().equals(thatItem.getName())
		&& this.getPriceInCents() == thatItem.getPriceInCents();
	}
	
	return false;
    }
}

