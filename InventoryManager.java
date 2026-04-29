import exceptions.InvalidPriceException;
import exceptions.InvalidSerialException;
import inventory.ClothingItem;
import inventory.Tops;
import inventory.Bottoms;
import inventory.Outerwear;
import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<ClothingItem> items;

    public InventoryManager() {
        this.items = new ArrayList<>();
    }

    public void loadInventory(ArrayList<ClothingItem> loadedItems) {
        this.items = loadedItems;
    }

    public ArrayList<ClothingItem> getItems() {
        return items;
    }

    public void addItem(String type, String serial, String name, String brand, String size, String condition, double price) throws InvalidPriceException {
        if (price < 200) {
            throw new InvalidPriceException("Price must be at least 200 Tk.");
        }
        
        if (type.equalsIgnoreCase("Tops")) {
            items.add(new Tops(serial, name, brand, size, condition, price));
        } else if (type.equalsIgnoreCase("Bottoms")) {
            items.add(new Bottoms(serial, name, brand, size, condition, price));
        } else if (type.equalsIgnoreCase("Outerwear")) {
            items.add(new Outerwear(serial, name, brand, size, condition, price));
        } else {
            System.out.println("Invalid clothing type.");
        }
    }

    public void removeItem(String serial) throws InvalidSerialException {
        boolean found = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getSerial().equalsIgnoreCase(serial)) {
                items.remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidSerialException("Serial number " + serial + " not found.");
        }
    }
    
    public ClothingItem getItemBySerial(String serial) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getSerial().equalsIgnoreCase(serial)) {
                return items.get(i);
            }
        }
        return null;
    }
    
    public void displayInventory() {
        System.out.println("List of Products:");
        for (int i = 0; i < items.size(); i++) {
            ClothingItem item = items.get(i);
            System.out.println("Serial: " + item.getSerial() + " | Type: " + item.getType() + " | Name: " + item.getName() + " | Brand: " + item.getBrand() + " | Price: " + item.getPrice() + " Tk");
        }
    }
}
