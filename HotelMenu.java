import java.util.Scanner;

abstract class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract String getItemType();

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getItemType() + ": " + name + " - " + price + " Rs";
    }
}

class RegularMenuItem extends MenuItem {
    public RegularMenuItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getItemType() {
        return "Regular Item";
    }
}

class SpecialMenuItem extends MenuItem {
    public SpecialMenuItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String getItemType() {
        return "Special Item";
    }
}

class Menu {
    private MenuItem[] items;
    private int[] quantities;

    public Menu() {
        items = new MenuItem[10];
        quantities = new int[10];
        items[0] = new RegularMenuItem("Burger", 99);
        items[1] = new RegularMenuItem("Pizza", 129);
        items[2] = new RegularMenuItem("Pasta", 89);
        items[3] = new RegularMenuItem("Salad", 69);
        items[4] = new RegularMenuItem("Chicken Wings", 199);
        items[5] = new RegularMenuItem("Chicken Leg piece", 229);
        items[6] = new RegularMenuItem("Paneer 65", 150);
        items[7] = new RegularMenuItem("Chilly Paneer", 129);
        items[8] = new SpecialMenuItem("Machurian", 99);
        items[9] = new SpecialMenuItem("Noodles", 80);
    }

    public MenuItem getItem(int choice) {
        if (choice >= 1 && choice <= items.length) {
            return items[choice - 1];
        }
        return null;
    }

    public void setQuantity(int choice, int quantity) {
        if (choice >= 1 && choice <= items.length) {
            quantities[choice - 1] = quantity;
        }
    }

    public int getQuantity(int choice) {
        if (choice >= 1 && choice <= items.length) {
            return quantities[choice - 1];
        }
        return 0;
    }

    public int getMenuSize() {
        return items.length;
    }
}

class Cart {
    private MenuItem item;
    private int quantity;

    public Cart(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return item.getPrice() * quantity;
    }

    public String getCartItemInfo() {
        return item.getName() + " x" + quantity;
    }
}

public class HotelMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        int choice;
        double totalCost = 0.0;

        System.out.println("---------------------------------------------");
        System.out.println("|          *                 *              |");
        System.out.println("|             Welcome to our                |");
        System.out.println("|                 Hotel                     |");
        System.out.println("|          *                 *              |");
        System.out.println("---------------------------------------------");

        System.out.println("Menu:");
        for (int i = 0; i < menu.getMenuSize(); i++) {
            System.out.println((i + 1) + ". " + menu.getItem(i + 1));
        }

        System.out.println((menu.getMenuSize() + 1) + ". Checkout");
        System.out.println("\nMax order per person is 10.\n");

        Cart[] cart = new Cart[10];
        int itemCount = 0;

        for (int i = 0; i < 10; i++) {
            System.out.print("Enter your choice (1-" + (menu.getMenuSize() + 1) + "): ");
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= menu.getMenuSize()) {
                MenuItem selectedMenuItem = menu.getItem(choice);
                if (selectedMenuItem != null) {
                    System.out.print("Enter quantity for " + selectedMenuItem.getName() + ": ");
                    int quantity = scanner.nextInt();
                    menu.setQuantity(choice, quantity);
                    Cart cartItem = new Cart(selectedMenuItem, quantity);
                    cart[itemCount] = cartItem;
                    itemCount++;
                    System.out.println(selectedMenuItem.getName() + " x" + quantity + " added to your order.");
                }
            } else if (choice == menu.getMenuSize() + 1) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }

        System.out.println("\nYour order summary:");

        for (int i = 0; i < itemCount; i++) {
            System.out.println(cart[i].getCartItemInfo() + " - Rs " + cart[i].getTotalCost());
            totalCost += cart[i].getTotalCost();
        }

        System.out.println("Your total cost is: " + totalCost + " Rs.");
        System.out.println("Thank you for dining with us!");
        scanner.close();
    }
}
