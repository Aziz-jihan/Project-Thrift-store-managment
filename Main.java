import exceptions.InvalidCouponException;
import exceptions.InvalidPriceException;
import exceptions.InvalidSerialException;
import inventory.ClothingItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static InventoryManager inventoryManager = new InventoryManager();
    private static CustomerManager customerManager = new CustomerManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load data initially
        inventoryManager.loadInventory(FileManager.loadInventory());
        customerManager.loadCustomers(FileManager.loadCustomers());

        boolean running = true;
        while (running) {
            print_ThriftNaki();
            System.out.println("Welcome to ThriftNaki!");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("Type 'exit' to quit.");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                running = false;
            } else if (choice.equals("1")) {
                customerPanel();
            } else if (choice.equals("2")) {
                adminPanel();
            } else {
                System.out.println("Invalid option.");
            }
        }
        
        System.out.println("Goodbye!");
    }

    private static void adminPanel() {
        boolean inAdmin = true;
        while (inAdmin) {
            print_ThriftNaki();
            System.out.println("Admin Panel");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Trigger Discount");
            System.out.println("Type 'exit' to go back.");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                inAdmin = false;
            } else if (choice.equals("1")) {
                addProduct();
            } else if (choice.equals("2")) {
                removeProduct();
            } else if (choice.equals("3")) {
                triggerDiscount();
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static void addProduct() {
        print_ThriftNaki();
        System.out.println("Adding New Product (type 'exit' to cancel)");
        
        System.out.print("Clothing Type (Tops/Bottoms/Outerwear): ");
        String type = scanner.nextLine();
        if (type.equalsIgnoreCase("exit")) return;

        System.out.print("Serial Number (e.g. T001, 1, 2, 3): ");
        String serial = scanner.nextLine();
        if (serial.equalsIgnoreCase("exit")) return;

        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (name.equalsIgnoreCase("exit")) return;

        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        if (brand.equalsIgnoreCase("exit")) return;

        System.out.print("Size: ");
        String size = scanner.nextLine();
        if (size.equalsIgnoreCase("exit")) return;

        System.out.print("Condition: ");
        String condition = scanner.nextLine();
        if (condition.equalsIgnoreCase("exit")) return;

        System.out.print("Price: ");
        String priceStr = scanner.nextLine();
        if (priceStr.equalsIgnoreCase("exit")) return;

        try {
            double price = Double.parseDouble(priceStr);
            inventoryManager.addItem(type, serial, name, brand, size, condition, price);
            FileManager.saveInventory(inventoryManager.getItems());
            System.out.println("Product added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid price.");
        } catch (InvalidPriceException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void removeProduct() {
        print_ThriftNaki();
        inventoryManager.displayInventory();
        System.out.print("Enter serial number of item to remove (or 'exit' to cancel): ");
        String serial = scanner.nextLine();
        if (serial.equalsIgnoreCase("exit")) return;

        try {
            inventoryManager.removeItem(serial);
            FileManager.saveInventory(inventoryManager.getItems());
            System.out.println("Product removed successfully!");
        } catch (InvalidSerialException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void triggerDiscount() {
        print_ThriftNaki();
        System.out.println("Trigger Holiday/Clearance Discount");
        System.out.print("Enter discount percentage (or 'exit' to cancel): ");
        String percStr = scanner.nextLine();
        if (percStr.equalsIgnoreCase("exit")) return;

        try {
            double percentage = Double.parseDouble(percStr);
            if (percentage <= 0 || percentage > 50) {
                System.out.println("Invalid percentage. Please enter a value between 0 and 50.");
                return;
            }

            for (ClothingItem item : inventoryManager.getItems()) {
                double discountAmount = item.calculateDiscount(percentage, item.getPrice());
                item.setPrice(item.getPrice() - discountAmount);
            }
            FileManager.saveInventory(inventoryManager.getItems());
            System.out.println("A discount of " + percentage + "% has been applied to all inventory items.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private static void customerPanel() {
        boolean inCustomer = true;
        while (inCustomer) {
            print_ThriftNaki();
            System.out.println("Customer Panel");
            System.out.println("1. New Customer");
            System.out.println("2. Existing Customer");
            System.out.println("Type 'exit' to go back.");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                inCustomer = false;
            } else if (choice.equals("1")) {
                Customer c = signUp();
                if (c != null) {
                    shoppingPanel(c);
                }
            } else if (choice.equals("2")) {
                Customer c = login();
                if (c != null) {
                    shoppingPanel(c);
                }
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static Customer signUp() {
        print_ThriftNaki();
        System.out.println("Sign Up");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (name.equalsIgnoreCase("exit")) return null;

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (email.equalsIgnoreCase("exit")) return null;

        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (password.equalsIgnoreCase("exit")) return null;

        Customer newCustomer = new Customer(name, email, password, "");
        customerManager.addCustomer(newCustomer);
        FileManager.saveCustomers(customerManager.getCustomers());
        System.out.println("Account created successfully!");
        return newCustomer;
    }

    private static Customer login() {
        print_ThriftNaki();
        System.out.println("Login");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (email.equalsIgnoreCase("exit")) return null;

        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (password.equalsIgnoreCase("exit")) return null;

        Customer c = customerManager.authenticateCustomer(email, password);
        if (c != null) {
            System.out.println(c.getName()+" welcome back!");
            return c;
        } else {
            System.out.println("Invalid email or password.");
            return null;
        }
    }

    private static void shoppingPanel(Customer customer) {
        ArrayList<ClothingItem> cart = new ArrayList<>();
        boolean shopping = true;
        
        while (shopping) {
            print_ThriftNaki();
            inventoryManager.displayInventory();
            System.out.println("\nEnter item serial to add to cart.");
            System.out.println("Enter 'y' to generate invoice.");
            System.out.println("Type 'exit' to go back and cancel purchase.");
            System.out.print("Option: ");
            String option = scanner.nextLine();

            if (option.equalsIgnoreCase("exit")) {
                shopping = false;
            } else if (option.equalsIgnoreCase("y")) {
                if (cart.isEmpty()) {
                    System.out.println("Cart is empty!");
                    continue;
                }
                checkoutPhase(customer, cart);
                shopping = false; // Checkout ends shopping session
            } else {
                ClothingItem item = inventoryManager.getItemBySerial(option);
                if (item != null) {
                    cart.add(item);
                    System.out.println(item.getName() + " added to cart.");
                } else {
                    System.out.println("Invalid serial number.");
                }
            }
        }
    }

    private static void checkoutPhase(Customer customer, ArrayList<ClothingItem> cart) {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total += cart.get(i).getPrice();
        }

        System.out.println("\nTotal Price: " + total + " Tk");
        System.out.print("Do you have a coupon code? (Press Enter to skip): ");
        String coupon = scanner.nextLine();

        double finalTotal = total;
        String appliedCoupon = "";

        if (!coupon.trim().isEmpty()) {
            if (customer.getTransactionDetails().contains("N")) {
                System.out.println("You have already used the coupon.");
            } else {
                try {
                    // To show method overriding usage, we use the first item to calculate discount
                    // A better way is applying discount proportionally, but simple is fine:
                    ClothingItem itemForDiscount = cart.get(0);
                    double discount = itemForDiscount.calculateDiscount(coupon, total);
                    finalTotal = total - discount;
                    appliedCoupon = coupon;
                    
                    // Add N to transaction history
                    String currentTrans = customer.getTransactionDetails();
                    customer.setTransactionDetails(currentTrans + "N");
                    System.out.println("Coupon applied successfully!");
                } catch (InvalidCouponException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }

        System.out.print("Enter 'y' to finalize purchase: ");
        String finalize = scanner.nextLine();

        if (finalize.equalsIgnoreCase("y")) {
            // Finalize purchase
            Invoice.generateInvoice(customer, cart, appliedCoupon, finalTotal);

            // Add purchase history to transaction details
            String currentTrans = customer.getTransactionDetails();
            String date = new Date().toString();
            for (int i = 0; i < cart.size(); i++) {
                currentTrans += cart.get(i).getType() + "+" + date + ",";
                // Remove from inventory
                try {
                    inventoryManager.removeItem(cart.get(i).getSerial());
                } catch (InvalidSerialException e) {
                    // Ignore, item is already verified
                }
            }
            customer.setTransactionDetails(currentTrans);

            // Save updates
            FileManager.saveInventory(inventoryManager.getItems());
            FileManager.saveCustomers(customerManager.getCustomers());
            System.out.println("Purchase complete! Items removed from inventory.");
        } else {
            System.out.println("Purchase cancelled.");
        }
    }

    private static void print_ThriftNaki() {
        System.out.println("\n--------------------Thrift Nakiiiiii--------------------");
    }
}
