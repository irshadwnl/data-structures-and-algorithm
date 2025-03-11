public class TextEditorUndoRedo {
    class Node {
        String textState;
        Node next, prev;

        Node(String textState) {
            this.textState = textState;
            this.next = this.prev = null;
        }
    }

    private Node head, tail, current;
    private int historySize;
    private int maxHistory;

    public TextEditorUndoRedo(int maxHistory) {
        this.head = this.tail = this.current = null;
        this.historySize = 0;
        this.maxHistory = maxHistory;
    }

    public void addTextState(String text) {
        Node newNode = new Node(text);
        if (current != null) {
            current.next = newNode;
            newNode.prev = current;
        }
        current = newNode;
        if (head == null) {
            head = tail = newNode;
        } else {
            tail = newNode;
        }
        historySize++;
        if (historySize > maxHistory) {
            head = head.next;
            head.prev = null;
            historySize--;
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo actions available.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo actions available.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.textState);
        } else {
            System.out.println("No text available.");
        }
    }

    public static void main(String[] args) {
        TextEditorUndoRedo editor = new TextEditorUndoRedo(10);
        editor.addTextState("Hello");
        editor.addTextState("Hello World");
        editor.addTextState("Hello World!");
        editor.displayCurrentState();
        editor.undo();
        editor.displayCurrentState();
        editor.redo();
        editor.displayCurrentState();
    }
}
