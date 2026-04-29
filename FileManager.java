import inventory.ClothingItem;
import inventory.Tops;
import inventory.Bottoms;
import inventory.Outerwear;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String INVENTORY_FILE = "inventory.txt";
    private static final String CUSTOMER_FILE = "customer.txt";

    public static ArrayList<ClothingItem> loadInventory() {
        ArrayList<ClothingItem> items = new ArrayList<>();
        try {
            File file = new File(INVENTORY_FILE);
            if (!file.exists()) return items;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    String type = parts[0];
                    String serial = parts[1];
                    String name = parts[2];
                    String brand = parts[3];
                    String size = parts[4];
                    String condition = parts[5];
                    double price = Double.parseDouble(parts[6]);

                    if (type.equalsIgnoreCase("Tops")) {
                        items.add(new Tops(serial, name, brand, size, condition, price));
                    } else if (type.equalsIgnoreCase("Bottoms")) {
                        items.add(new Bottoms(serial, name, brand, size, condition, price));
                    } else if (type.equalsIgnoreCase("Outerwear")) {
                        items.add(new Outerwear(serial, name, brand, size, condition, price));
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
        return items;
    }

    public static void saveInventory(ArrayList<ClothingItem> items) {
        try {
            FileWriter writer = new FileWriter(INVENTORY_FILE);
            for (int i = 0; i < items.size(); i++) {
                writer.write(items.get(i).toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public static ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            File file = new File(CUSTOMER_FILE);
            if (!file.exists()) return customers;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String name = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    String trans = "";
                    if (parts.length > 3) {
                        trans = parts[3];
                    }
                    customers.add(new Customer(name, email, password, trans));
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
        return customers;
    }

    public static void saveCustomers(ArrayList<Customer> customers) {
        try {
            FileWriter writer = new FileWriter(CUSTOMER_FILE);
            for (int i = 0; i < customers.size(); i++) {
                writer.write(customers.get(i).toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }
}
