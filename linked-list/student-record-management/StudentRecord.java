import java.util.*;
class Student{
    int rollNumber;
    String name;
    int age;
    double grade;
    Student next;
    public Student(int rollNumber, String name, int age, double grade,Student next) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next=null;
    }

}
class StudentRecordManagement{
    Student head;

    void addAtBeginning(int roll, String name, int age, String grade) {
        Student newStudent=new Student(roll, name, age, age, head);
        newStudent.next=head;
        head=newStudent;
    }

    void addAtEnd(int roll, String name, int age, String grade) {
        Student newStudent=new Student(roll, name, age, age, head);
        if(head==null){
            head=newStudent;
            return;
        }
        Student temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=newStudent;
    }

    void addAtPosition(int roll, String name, int age, String grade, int position) {
        Student newStudent=new Student(roll, name, age, age, head);
        if(position==1){
            newStudent.next=head;
            head=newStudent;
            return;
        }
        Student temp=head;
        for(int i=1;i<position-1 && temp!=null;i++){
            temp=temp.next;
        }
        if(temp==null || temp.next==null){
            System.out.println("Position out of range");
            return;
        }
        newStudent.next=temp.next;
        temp.next=newStudent;
    }

    void deleteByRollNumber(int roll) {
        Student newStudent=new Student(roll, null, roll, roll, head);
        if(head==null){
            System.out.println("List is empty. No student to delete.");
            return;
        }
        if(head.rollNumber==roll){
            head=head.next;
            return;
        }
        Student temp=head;
        while(temp.next!=null && temp.next.rollNumber!=roll){
            temp=temp.next;
        }
        if(temp.next==null){
            System.out.println("Student not found in the list.");
            return;
        }
        temp.next=temp.next.next;
        System.out.println("Student with roll number " + roll + " deleted successfully.");
        

        
    }

    void searchByRollNumber(int roll) {
        Student temp=head;
        boolean found=false;
        while(temp!=null){
            if(temp.rollNumber==roll){
                found=true;
                System.out.println("Student Found:");
                System.out.println("Roll Number: " + temp.rollNumber);
                System.out.println("Name: " + temp.name);
                System.out.println("Age: " + temp.age);
                System.out.println("Grade: " + temp.grade);
                break;
            }
            temp=temp.next;
        }
        if(!found){
            System.out.println("Student not found in the list.");
        }
    }

    void updateGrade(int roll, String grade) {
        Student temp=head;
        boolean found=false;
        while(temp!=null){
            if(temp.rollNumber==roll){
                found=true;
                temp.grade=Double.parseDouble(grade);
                System.out.println("Grade updated successfully for student with roll number " + roll);
                break;
            }
            temp=temp.next;
        }
        if(!found){
            System.out.println("Student not found in the list.");
        }
    }

    void displayAll() {
        Student temp=head;
        while(temp!=null){
            System.out.println("Student Details:");
            System.out.println("Roll Number: " + temp.rollNumber);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Grade: " + temp.grade);
            System.out.println("------------------------");
            temp=temp.next;
        }
        if(temp==null){
           return;
        }
    }

    

    
}


public class StudentRecord {
    public static void main(String[] args) {
        StudentRecordManagement srm = new StudentRecordManagement();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Specific Position");
            System.out.println("4. Delete Student by Roll Number");
            System.out.println("5. Search Student by Roll Number");
            System.out.println("6. Update Grade by Roll Number");
            System.out.println("7. Display All Records");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    srm.addAtBeginning(roll, name, age, grade);
                    break;
                case 2:
                    System.out.print("Enter Roll Number: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    grade = sc.nextLine();
                    srm.addAtEnd(roll, name, age, grade);
                    break;
                case 3:
                    System.out.print("Enter Roll Number: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    grade = sc.nextLine();
                    System.out.print("Enter Position: ");
                    int position = sc.nextInt();
                    srm.addAtPosition(roll, name, age, grade, position);
                    break;
                case 4:
                    System.out.print("Enter Roll Number to delete: ");
                    roll = sc.nextInt();
                    srm.deleteByRollNumber(roll);
                    break;
                case 5:
                    System.out.print("Enter Roll Number to search: ");
                    roll = sc.nextInt();
                    srm.searchByRollNumber(roll);
                    break;
                case 6:
                    System.out.print("Enter Roll Number to update grade: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Grade: ");
                    grade = sc.nextLine();
                    srm.updateGrade(roll, grade);
                    break;
                case 7:
                    srm.displayAll();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}