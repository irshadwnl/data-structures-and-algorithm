public class InventoryManagement {
    class Node {
        String itemName;
        int itemID, quantity;
        double price;
        Node next;

        Node(String itemName, int itemID, int quantity, double price) {
            this.itemName = itemName;
            this.itemID = itemID;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }
    }

    private Node head;

    public void addItemAtBeginning(String name, int id, int qty, double price) {
        Node newNode = new Node(name, id, qty, price);
        newNode.next = head;
        head = newNode;
    }

    public void addItemAtEnd(String name, int id, int qty, double price) {
        Node newNode = new Node(name, id, qty, price);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addItemAtPosition(String name, int id, int qty, double price, int position) {
        if (position <= 0) {
            addItemAtBeginning(name, id, qty, price);
            return;
        }
        Node newNode = new Node(name, id, qty, price);
        Node temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            addItemAtEnd(name, id, qty, price);
            return;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void removeItemByID(int id) {
        if (head == null) return;
        if (head.itemID == id) {
            head = head.next;
            return;
        }
        Node temp = head, prev = null;
        while (temp != null && temp.itemID != id) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) return;
        prev.next = temp.next;
    }

    public void updateQuantity(int id, int newQuantity) {
        Node temp = head;
        while (temp != null) {
            if (temp.itemID == id) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    public Node searchItemByID(int id) {
        Node temp = head;
        while (temp != null) {
            if (temp.itemID == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    public Node searchItemByName(String name) {
        Node temp = head;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }

    public double calculateTotalInventoryValue() {
        double total = 0;
        Node temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        return total;
    }

    public void sortInventory(String criteria, boolean ascending) {
        if (head == null || head.next == null) {
            return; 
        }
        
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            Node prev = null;
            Node next = head.next;
            
            while (next != null) {
                boolean condition = false;
                
                if (criteria.equalsIgnoreCase("name")) {
                    condition = ascending ? 
                        current.itemName.compareToIgnoreCase(next.itemName) > 0 : 
                        current.itemName.compareToIgnoreCase(next.itemName) < 0;
                } else if (criteria.equalsIgnoreCase("price")) {
                    condition = ascending ? current.price > next.price : current.price < next.price;
                }
                
                if (condition) {
                    if (prev == null) {
                        head = next;
                    } else {
                        prev.next = next;
                    }
                    
                    current.next = next.next;
                    next.next = current;
                    
                    swapped = true;
                    
                    prev = next;
                    next = current.next;
                } else {
                    prev = current;
                    current = next;
                    next = next.next;
                }
            }
        } while (swapped);
    }

    public void displayInventory() {
        Node temp = head;
        while (temp != null) {
            System.out.println("Item: " + temp.itemName + ", ID: " + temp.itemID + ", Quantity: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();
        inventory.addItemAtEnd("Laptop", 101, 5, 50000);
        inventory.addItemAtBeginning("Mouse", 102, 10, 500);
        inventory.addItemAtPosition("Keyboard", 103, 7, 1500, 1);
        inventory.displayInventory();
        System.out.println("Total Inventory Value: " + inventory.calculateTotalInventoryValue());
        inventory.sortInventory("price", true);
        System.out.println("Sorted Inventory by Price:");
        inventory.displayInventory();
    }
}
