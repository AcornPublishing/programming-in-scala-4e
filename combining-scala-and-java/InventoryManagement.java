/**
 *  A sample application that is mostly Java but that
 *  includes a Scala file.
 */
public class InventoryManagement 
{
    public static void main(String[] args) 
    {
	Inventory inventory = makeSampleInventory();
	
	// Call out to Scala to analyze the inventory
	InventoryItem mostExpensive =
	    InventoryAnalysis.findMostExpensive(inventory);
	
	System.out.println("Most expensive item = " + mostExpensive);
    }


    /**
     *  Make up a sample inventory for testing.
     */
    private static Inventory makeSampleInventory() 
    {
	Inventory inventory = new Inventory();
	
	inventory.addItem(new InventoryItem("widget", 100, 599));
	inventory.addItem(new InventoryItem("sprocket", 200, 499));
	inventory.addItem(new InventoryItem("dreariness", 300, 99));

	return inventory;
    }
}
