import java.util.*;

class FriendNode {
    int friendId;
    FriendNode next;

    public FriendNode(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class UserNode {
    int userId;
    String name;
    int age;
    FriendNode friendList; // Head of the friends linked list
    UserNode next;

    public UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendList = null;
        this.next = null;
    }

    // Add friend to the user's friend list
    public void addFriend(int friendId) {
        FriendNode newFriend = new FriendNode(friendId);
        newFriend.next = friendList;
        friendList = newFriend;
    }

    // Remove friend from the user's friend list
    public void removeFriend(int friendId) {
        if (friendList == null) return;

        if (friendList.friendId == friendId) {
            friendList = friendList.next;
            return;
        }

        FriendNode current = friendList, prev = null;
        while (current != null && current.friendId != friendId) {
            prev = current;
            current = current.next;
        }
        if (current != null) prev.next = current.next;
    }

    // Display all friends of the user
    public void displayFriends() {
        System.out.print("Friends of " + name + " (ID: " + userId + "): ");
        FriendNode temp = friendList;
        if (temp == null) {
            System.out.println("No friends added yet.");
            return;
        }
        while (temp != null) {
            System.out.print(temp.friendId + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Count number of friends
    public int countFriends() {
        int count = 0;
        FriendNode temp = friendList;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class SocialMedia {
    UserNode head; // Head of the user list

    // Add a new user
    public void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        newUser.next = head;
        head = newUser;
    }

    // Search for a user by User ID
    public UserNode searchUserById(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Search for a user by Name
    public UserNode searchUserByName(String name) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }

    // Add a friend connection between two users
    public void addFriendship(int userId1, int userId2) {
        UserNode user1 = searchUserById(userId1);
        UserNode user2 = searchUserById(userId2);
        if (user1 != null && user2 != null) {
            user1.addFriend(userId2);
            user2.addFriend(userId1);
            System.out.println("Friendship added between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Remove a friend connection between two users
    public void removeFriendship(int userId1, int userId2) {
        UserNode user1 = searchUserById(userId1);
        UserNode user2 = searchUserById(userId2);
        if (user1 != null && user2 != null) {
            user1.removeFriend(userId2);
            user2.removeFriend(userId1);
            System.out.println("Friendship removed between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Find mutual friends between two users
    public void findMutualFriends(int userId1, int userId2) {
        UserNode user1 = searchUserById(userId1);
        UserNode user2 = searchUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        HashSet<Integer> user1Friends = new HashSet<>();
        FriendNode temp1 = user1.friendList;
        while (temp1 != null) {
            user1Friends.add(temp1.friendId);
            temp1 = temp1.next;
        }

        System.out.print("Mutual Friends: ");
        FriendNode temp2 = user2.friendList;
        boolean found = false;
        while (temp2 != null) {
            if (user1Friends.contains(temp2.friendId)) {
                System.out.print(temp2.friendId + " ");
                found = true;
            }
            temp2 = temp2.next;
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    // Display all friends of a specific user
    public void displayUserFriends(int userId) {
        UserNode user = searchUserById(userId);
        if (user != null) user.displayFriends();
        else System.out.println("User not found.");
    }

    // Count number of friends for each user
    public void displayFriendCounts() {
        UserNode temp = head;
        while (temp != null) {
            System.out.println(temp.name + " (ID: " + temp.userId + ") has " + temp.countFriends() + " friends.");
            temp = temp.next;
        }
    }
}

public class SocialMediaApp{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialMedia sm = new SocialMedia();

        // Adding some users
        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 22);
        sm.addUser(3, "Charlie", 28);
        sm.addUser(4, "David", 23);

        // Creating friend connections
        sm.addFriendship(1, 2);
        sm.addFriendship(1, 3);
        sm.addFriendship(2, 3);
        sm.addFriendship(2, 4);

        // Displaying all users' friends
        sm.displayUserFriends(1);
        sm.displayUserFriends(2);
        sm.displayUserFriends(3);
        sm.displayUserFriends(4);

        // Searching for a user
        UserNode user = sm.searchUserByName("Charlie");
        if (user != null) System.out.println("User found: " + user.name + " (ID: " + user.userId + ")");

        // Finding mutual friends
        sm.findMutualFriends(1, 2);

        // Removing a friendship
        sm.removeFriendship(1, 3);
        sm.displayUserFriends(1);

        // Displaying the number of friends each user has
        sm.displayFriendCounts();

        scanner.close();
    }
}
