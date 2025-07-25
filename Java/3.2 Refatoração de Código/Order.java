import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Priceable> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(Priceable item) {
        items.add(item);
    }

    public List<Priceable> getItems() {
        return items;
    }
}

public interface Priceable {
    double getPrice();
}

public class TotalCalculator {
    public double calculateTotal(List<Priceable> items) {
        double total = 0;
        for (Priceable item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

public class Item implements Priceable {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}