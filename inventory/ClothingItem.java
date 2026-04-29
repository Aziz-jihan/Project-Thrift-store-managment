package inventory;

public abstract class ClothingItem implements Discountable {
    private String type;
    private String serial;
    private String name;
    private String brand;
    private String size;
    private String condition; //fair, good, excellent
    private double price;

    public ClothingItem(String type, String serial, String name, String brand, String size, String condition, double price) {
        this.type = type;
        this.serial = serial;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.condition = condition;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double calculateDiscount(double percentage, double originalPrice);

    @Override
    public String toString() {
        return type + "|" + serial + "|" + name + "|" + brand + "|" + size + "|" + condition + "|" + price + "|";
    }
}
