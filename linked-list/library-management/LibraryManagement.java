class LibraryManagement {
    class Node {
        String title, author, genre;
        int bookID;
        boolean isAvailable;
        Node next, prev;

        Node(String title, String author, String genre, int bookID, boolean isAvailable) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.bookID = bookID;
            this.isAvailable = isAvailable;
            this.next = this.prev = null;
        }
    }

    private Node head, tail;
    private int bookCount;

    public void addBookAtBeginning(String title, String author, String genre, int bookID, boolean isAvailable) {
        Node newNode = new Node(title, author, genre, bookID, isAvailable);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        bookCount++;
    }

    public void addBookAtEnd(String title, String author, String genre, int bookID, boolean isAvailable) {
        Node newNode = new Node(title, author, genre, bookID, isAvailable);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        bookCount++;
    }

    public void addBookAtPosition(String title, String author, String genre, int bookID, boolean isAvailable, int position) {
        if (position <= 0) {
            addBookAtBeginning(title, author, genre, bookID, isAvailable);
            return;
        }
        Node newNode = new Node(title, author, genre, bookID, isAvailable);
        Node temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            addBookAtEnd(title, author, genre, bookID, isAvailable);
            return;
        }
        newNode.next = temp.next;
        if (temp.next != null) {
            temp.next.prev = newNode;
        } else {
            tail = newNode;
        }
        newNode.prev = temp;
        temp.next = newNode;
        bookCount++;
    }

    public void removeBookByID(int bookID) {
        Node temp = head;
        while (temp != null && temp.bookID != bookID) {
            temp = temp.next;
        }
        if (temp == null) return;
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        } else {
            head = temp.next;
        }
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else {
            tail = temp.prev;
        }
        bookCount--;
    }

    public Node searchBookByTitle(String title) {
        Node temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) return temp;
            temp = temp.next;
        }
        return null;
    }

    public Node searchBookByAuthor(String author) {
        Node temp = head;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void updateAvailability(int bookID, boolean isAvailable) {
        Node temp = head;
        while (temp != null) {
            if (temp.bookID == bookID) {
                temp.isAvailable = isAvailable;
                return;
            }
            temp = temp.next;
        }
    }

    public void displayBooksForward() {
        Node temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", ID: " + temp.bookID + ", Available: " + temp.isAvailable);
            temp = temp.next;
        }
    }

    public void displayBooksReverse() {
        Node temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", ID: " + temp.bookID + ", Available: " + temp.isAvailable);
            temp = temp.prev;
        }
    }

    public int countBooks() {
        return bookCount;
    }

    public static void main(String[] args) {
        LibraryManagement library = new LibraryManagement();
        library.addBookAtEnd("The Hobbit", "J.R.R. Tolkien", "Fantasy", 101, true);
        library.addBookAtBeginning("1984", "George Orwell", "Dystopian", 102, true);
        library.addBookAtPosition("To Kill a Mockingbird", "Harper Lee", "Classic", 103, false, 1);
        library.displayBooksForward();
        System.out.println("Total Books: " + library.countBooks());
        library.displayBooksReverse();
    }
}
