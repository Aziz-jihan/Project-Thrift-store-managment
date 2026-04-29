package inventory;

import exceptions.InvalidCouponException;

public interface Discountable {
    double calculateDiscount(String coupon, double originalPrice) throws InvalidCouponException;
}
