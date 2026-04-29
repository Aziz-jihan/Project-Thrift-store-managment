package inventory;

import exceptions.InvalidCouponException;

public class Tops extends ClothingItem {
    public Tops(String serial, String name, String brand, String size, String condition, double price) {
        super("Tops", serial, name, brand, size, condition, price);
    }

    @Override
    public double calculateDiscount(String coupon, double originalPrice) throws InvalidCouponException {
        if (coupon.equalsIgnoreCase("new10")) {
            return originalPrice * 0.10;
        }
        throw new InvalidCouponException("Coupon " + coupon + " is invalid.");
    }

    public double calculateDiscount(double percentage, double originalPrice) {
        return originalPrice * (percentage / 100);
    }
}
