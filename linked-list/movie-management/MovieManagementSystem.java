public class MovieManagementSystem {
    private Node head;
    private Node tail;
    private int size;
    
    // Node class for the doubly linked list
    private class Node {
        private String title;
        private String director;
        private int year;
        private double rating;
        private Node next;
        private Node prev;
        
        public Node(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
            this.next = null;
            this.prev = null;
        }
    }
    
    // Constructor
    public MovieManagementSystem() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    // Add a movie at the beginning of the list
    public void addMovieAtBeginning(String title, String director, int year, double rating) {
        Node newNode = new Node(title, director, year, rating);
        
        if (head == null) {
            // List is empty
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        System.out.println("Movie '" + title + "' added at the beginning.");
    }
    
    // Add a movie at the end of the list
    public void addMovieAtEnd(String title, String director, int year, double rating) {
        Node newNode = new Node(title, director, year, rating);
        
        if (head == null) {
            // List is empty
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        System.out.println("Movie '" + title + "' added at the end.");
    }
    
    // Add a movie at a specific position
    public void addMovieAtPosition(String title, String director, int year, double rating, int position) {
        // Check if position is valid
        if (position < 0 || position > size) {
            System.out.println("Invalid position. Valid range is 0 to " + size);
            return;
        }
        
        // Add at beginning if position is 0
        if (position == 0) {
            addMovieAtBeginning(title, director, year, rating);
            return;
        }
        
        // Add at end if position is equal to size
        if (position == size) {
            addMovieAtEnd(title, director, year, rating);
            return;
        }
        
        // Add at specific position
        Node newNode = new Node(title, director, year, rating);
        Node current = head;
        
        // Traverse to the position
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
        
        size++;
        System.out.println("Movie '" + title + "' added at position " + position + ".");
    }
    
    // Remove a movie by title
    public void removeMovieByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty. No movies to remove.");
            return;
        }
        
        // If the movie to remove is the head
        if (head.title.equals(title)) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            System.out.println("Movie '" + title + "' removed successfully.");
            return;
        }
        
        // If the movie to remove is the tail
        if (tail.title.equals(title)) {
            tail = tail.prev;
            tail.next = null;
            size--;
            System.out.println("Movie '" + title + "' removed successfully.");
            return;
        }
        
        // Search for the movie in the middle
        Node current = head;
        while (current != null && !current.title.equals(title)) {
            current = current.next;
        }
        
        // If movie found
        if (current != null) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
            System.out.println("Movie '" + title + "' removed successfully.");
        } else {
            System.out.println("Movie '" + title + "' not found.");
        }
    }
    
    // Search for movies by director
    public void searchMoviesByDirector(String director) {
        if (head == null) {
            System.out.println("List is empty. No movies to search.");
            return;
        }
        
        Node current = head;
        boolean found = false;
        
        System.out.println("\nMovies directed by '" + director + "':");
        while (current != null) {
            if (current.director.equals(director)) {
                found = true;
                System.out.println("Title: " + current.title + ", Year: " + current.year + ", Rating: " + current.rating);
            }
            current = current.next;
        }
        
        if (!found) {
            System.out.println("No movies found with director '" + director + "'.");
        }
    }
    
    // Search for movies by rating
    public void searchMoviesByRating(double rating) {
        if (head == null) {
            System.out.println("List is empty. No movies to search.");
            return;
        }
        
        Node current = head;
        boolean found = false;
        
        System.out.println("\nMovies with rating " + rating + ":");
        while (current != null) {
            if (current.rating == rating) {
                found = true;
                System.out.println("Title: " + current.title + ", Director: " + current.director + ", Year: " + current.year);
            }
            current = current.next;
        }
        
        if (!found) {
            System.out.println("No movies found with rating " + rating + ".");
        }
    }
    
    // Display all movies in forward order
    public void displayMoviesForward() {
        if (head == null) {
            System.out.println("List is empty. No movies to display.");
            return;
        }
        
        Node current = head;
        System.out.println("\nMovies (Forward Order):");
        System.out.println("------------------------");
        
        while (current != null) {
            System.out.println("Title: " + current.title + 
                             ", Director: " + current.director + 
                             ", Year: " + current.year + 
                             ", Rating: " + current.rating);
            current = current.next;
        }
    }
    
    // Display all movies in reverse order
    public void displayMoviesReverse() {
        if (tail == null) {
            System.out.println("List is empty. No movies to display.");
            return;
        }
        
        Node current = tail;
        System.out.println("\nMovies (Reverse Order):");
        System.out.println("------------------------");
        
        while (current != null) {
            System.out.println("Title: " + current.title + 
                             ", Director: " + current.director + 
                             ", Year: " + current.year + 
                             ", Rating: " + current.rating);
            current = current.prev;
        }
    }
    
    // Update movie rating by title
    public void updateMovieRating(String title, double newRating) {
        if (head == null) {
            System.out.println("List is empty. No movies to update.");
            return;
        }
        
        Node current = head;
        boolean found = false;
        
        while (current != null) {
            if (current.title.equals(title)) {
                found = true;
                current.rating = newRating;
                System.out.println("Rating for movie '" + title + "' updated to " + newRating);
                break;
            }
            current = current.next;
        }
        
        if (!found) {
            System.out.println("Movie '" + title + "' not found.");
        }
    }
    
    // Get size of the list
    public int getSize() {
        return size;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        MovieManagementSystem movieSystem = new MovieManagementSystem();
        
        // Add movies
        movieSystem.addMovieAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        movieSystem.addMovieAtEnd("The Godfather", "Francis Ford Coppola", 1972, 9.2);
        movieSystem.addMovieAtBeginning("Pulp Fiction", "Quentin Tarantino", 1994, 8.9);
        movieSystem.addMovieAtPosition("The Dark Knight", "Christopher Nolan", 2008, 9.0, 2);
        
        // Display movies
        movieSystem.displayMoviesForward();
        movieSystem.displayMoviesReverse();
        
        // Search movies
        movieSystem.searchMoviesByDirector("Christopher Nolan");
        movieSystem.searchMoviesByRating(8.9);
        
        // Update rating
        movieSystem.updateMovieRating("Inception", 9.5);
        
        // Display updated list
        movieSystem.displayMoviesForward();
        
        // Remove movie
        movieSystem.removeMovieByTitle("Pulp Fiction");
        
        // Display final list
        movieSystem.displayMoviesForward();
    }
}