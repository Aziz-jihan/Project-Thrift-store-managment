package inventory;

import exceptions.InvalidCouponException;

public class Outerwear extends ClothingItem {
    public Outerwear(String serial, String name, String brand, String size, String condition, double price) {
        super("Outerwear", serial, name, brand, size, condition, price);
    }

    // Method overriding for interface Discountable
    @Override
    public double calculateDiscount(String coupon, double originalPrice) throws InvalidCouponException {
        if (coupon.equalsIgnoreCase("new10")) {
            return originalPrice * 0.10;
        }
        throw new InvalidCouponException("Coupon " + coupon + " is invalid.");
    }

    // Custom method overloading
    public double calculateDiscount(double percentage, double originalPrice) {
        return originalPrice * (percentage / 100);
    }
}
