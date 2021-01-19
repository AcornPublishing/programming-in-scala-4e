import java.util.LinkedList;
import java.util.List;

/**
 *  An inventory class written in Java.
 */
public class Inventory 
{
    private final List<InventoryItem> items =
	new LinkedList<InventoryItem>();
    
    public void addItem(InventoryItem item) 
    {
	items.add(item);
    }
    

    public List<InventoryItem> getItems() 
    {
	return items;
    }
}
