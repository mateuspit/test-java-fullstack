import java.util.ArrayList;
import java.util.List;

public class OrderLegacy {
    private List<Item> items;

    public OrderLegacy() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);        
    }
    
    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

// Suponho que exista essa classe abaixo

// public class Item {
//     private String name;
//     private double price;

//     public Item(String name, double price) {
//         this.name = name;
//         this.price = price;
//     }

//     public double getPrice() {
//         return price;
//     }
// }