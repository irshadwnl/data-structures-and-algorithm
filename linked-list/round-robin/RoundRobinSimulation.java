import java.util.Scanner;

class Process {
    int processId;
    int burstTime;
    int remainingTime;
    int waitingTime;
    int turnAroundTime;
    Process next;

    public Process(int processId, int burstTime) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
        this.next = null;
    }
}

class RoundRobinScheduler {
    Process head = null;
    Process tail = null;

    // Add process at the end
    public void addProcess(int processId, int burstTime) {
        Process newProcess = new Process(processId, burstTime);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            tail.next = head; // Circular linking
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }

    // Display all processes in queue
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Process temp = head;
        System.out.println("Process Queue:");
        do {
            System.out.println("Process ID: " + temp.processId + ", Remaining Burst Time: " + temp.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    // Execute Round-Robin Scheduling
    public void executeRoundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int totalProcesses = 0;
        int totalWaitingTime = 0, totalTurnAroundTime = 0;
        Process current = head;
        int time = 0; // Total time elapsed

        // Count total processes
        do {
            totalProcesses++;
            current = current.next;
        } while (current != head);

        System.out.println("\nExecuting Round Robin with Time Quantum: " + timeQuantum);

        while (head != null) {
            current = head;
            do {
                if (current.remainingTime > 0) {
                    int executedTime = Math.min(timeQuantum, current.remainingTime);
                    time += executedTime;
                    current.remainingTime -= executedTime;

                    if (current.remainingTime == 0) { // Process completed
                        current.turnAroundTime = time;
                        current.waitingTime = current.turnAroundTime - current.burstTime;
                        System.out.println("Process " + current.processId + " completed at time " + time);
                        removeProcess(current.processId);
                    }
                }
                displayProcesses();
                current = current.next;
            } while (head != null && current != head);
        }

        // Calculate Average Waiting & Turnaround Time
        System.out.println("\nFinal Statistics:");
        System.out.println("Process ID\tWaiting Time\tTurnaround Time");
        for (Process temp = head; temp != null; temp = temp.next) {
            totalWaitingTime += temp.waitingTime;
            totalTurnAroundTime += temp.turnAroundTime;
            System.out.println(temp.processId + "\t\t" + temp.waitingTime + "\t\t" + temp.turnAroundTime);
        }

        System.out.println("Average Waiting Time: " + (totalWaitingTime / (double) totalProcesses));
        System.out.println("Average Turnaround Time: " + (totalTurnAroundTime / (double) totalProcesses));
    }

    // Remove process after execution
    public void removeProcess(int processId) {
        if (head == null) return;

        Process current = head, prev = null;

        // If head is to be removed
        if (head.processId == processId) {
            if (head == tail) { // Only one process in queue
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            return;
        }

        // Search for the process
        do {
            prev = current;
            current = current.next;
            if (current.processId == processId) {
                prev.next = current.next;
                if (current == tail) {
                    tail = prev;
                }
                return;
            }
        } while (current != head);
    }
}

public class RoundRobinSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        // Taking user input for processes
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        for (int i = 1; i <= numProcesses; i++) {
            System.out.print("Enter burst time for Process " + i + ": ");
            int burstTime = scanner.nextInt();
            scheduler.addProcess(i, burstTime);
        }

        System.out.print("Enter Time Quantum: ");
        int timeQuantum = scanner.nextInt();

        // Displaying initial process queue
        scheduler.displayProcesses();

        // Running Round Robin scheduling
        scheduler.executeRoundRobin(timeQuantum);
        
        scanner.close();
    }
}
