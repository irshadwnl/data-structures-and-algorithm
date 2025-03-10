class Task {
    int taskId;
    String taskName;
    String priority;
    String dueDate;
    Task next; // Circular reference

    public Task(int taskId, String taskName, String priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    Task head = null;
    Task tail = null;
    Task currentTask = null; // To track the current task for traversal

    // Add task at the beginning
    public void addTaskAtBeginning(int id, String name, String priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            tail = newTask;
            newTask.next = head; // Circular link
        } else {
            newTask.next = head;
            tail.next = newTask;
            head = newTask;
        }
        if (currentTask == null) currentTask = head; // Initialize current task
    }

    // Add task at the end
    public void addTaskAtEnd(int id, String name, String priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            tail = newTask;
            newTask.next = head; // Circular link
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
        if (currentTask == null) currentTask = head; // Initialize current task
    }

    // Add task at a specific position (1-based index)
    public void addTaskAtPosition(int id, String name, String priority, String dueDate, int position) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (position == 1) {
            addTaskAtBeginning(id, name, priority, dueDate);
            return;
        }

        Task temp = head;
        int count = 1;
        while (count < position - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }
    }

    // Remove a task by Task ID
    public void removeTask(int id) {
        if (head == null) {
            System.out.println("No tasks available to remove.");
            return;
        }

        Task temp = head, prev = null;

        // If head is the task to remove
        if (head.taskId == id) {
            if (head == tail) { // Only one element
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            System.out.println("Task " + id + " removed.");
            return;
        }

        // Searching for the task
        do {
            prev = temp;
            temp = temp.next;
            if (temp.taskId == id) {
                prev.next = temp.next;
                if (temp == tail) {
                    tail = prev;
                }
                System.out.println("Task " + id + " removed.");
                return;
            }
        } while (temp != head);

        System.out.println("Task ID " + id + " not found.");
    }

    // View the current task and move to the next one
    public void viewCurrentTask() {
        if (currentTask == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task: ID=" + currentTask.taskId + ", Name=" + currentTask.taskName +
                ", Priority=" + currentTask.priority + ", Due Date=" + currentTask.dueDate);
        currentTask = currentTask.next; // Move to the next task (circular)
    }

    // Display all tasks in the list
    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;
        System.out.println("Task List:");
        do {
            System.out.println("ID=" + temp.taskId + ", Name=" + temp.taskName +
                    ", Priority=" + temp.priority + ", Due Date=" + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    // Search for a task by Priority
    public void searchTaskByPriority(String priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority.equalsIgnoreCase(priority)) {
                System.out.println("Found: ID=" + temp.taskId + ", Name=" + temp.taskName +
                        ", Priority=" + temp.priority + ", Due Date=" + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }
}

public class CircularTaskScheduler {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Adding tasks
        scheduler.addTaskAtBeginning(1, "Task A", "High", "2023-03-10");
        scheduler.addTaskAtEnd(2, "Task B", "Medium", "2023-03-15");
        scheduler.addTaskAtEnd(3, "Task C", "Low", "2023-03-20");
        scheduler.addTaskAtPosition(4, "Task D", "High", "2023-03-12", 2);

        // Display all tasks
        scheduler.displayTasks();

        // View current task and move to next
        scheduler.viewCurrentTask();
        scheduler.viewCurrentTask();

        // Search by priority
        scheduler.searchTaskByPriority("High");

        // Remove a task
        scheduler.removeTask(2);
        scheduler.displayTasks();
    }
}
