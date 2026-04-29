import inventory.ClothingItem;
import java.util.ArrayList;

public class Invoice {
    public static void generateInvoice(Customer customer, ArrayList<ClothingItem> purchases, String couponUsed, double finalTotal) {
        System.out.println("\n--------------------Thrieft Nakiiiiii--------------------");
        System.out.println("INVOICE");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < purchases.size(); i++) {
            ClothingItem item = purchases.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getBrand() + " - " + item.getPrice() + " Tk");
        }
        System.out.println("---------------------------------------------------------");
        if (couponUsed != null && !couponUsed.isEmpty()) {
            System.out.println("Coupon Applied: " + couponUsed);
        }
        System.out.println("Final Total: " + finalTotal + " Tk");
        System.out.println("---------------------------------------------------------");
        System.out.println("Thanks for shopping with us!");
        System.out.println("---------------------------------------------------------\n");
    }
}
