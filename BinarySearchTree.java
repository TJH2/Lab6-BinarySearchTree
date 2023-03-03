// Programmers: Ben Diskin, David Rukashaza-Hancock, T. Jake Holmes, Jaspreet Khatkar
// CS 145: Face-To-Face
// Date: 3/2/2023
// Assignment: Binary Search Tree
// Reference: Chapter 17
// Purpose: Use recursion to add/remove employees from a fictional database
// Extra Credit: Iterative Employee Search Function IterativeSearch()





import java.util.*;

public class BinarySearchTree {

   static Scanner input = new Scanner(System.in);
   private static Employee root; // root node
   private static String company = "Arctic Gun Steel Energy Drink LLC";
    
    
    public static void main(String[] args) {
      //Sample Employees
      root = addEmployee(root, 50, "Jake", "H", "Mixologist");
      root = addEmployee(root, 40, "Jaspreet", "K", "Drink Tester");
      root = addEmployee(root, 60, "David", "R-H", "Brand Manager");
      root = addEmployee(root, 55, " Ben", "D", "Graphic Design Specialist");

      System.out.println("This Program Will Allow You To View And Edit A Directory Of Employees At " + company + "\n");

      menu();

    } // end of main

    public static void menu() {
      String menu="";
      System.out.println("Please Select An Option:");
      while(!menu.equals("s")&&!menu.equals("a")&&!menu.equals("l")&&!menu.equals("q")){
         System.out.println("View " + company + " Employee Directory(s)");
         System.out.println("Add An Employee To The " + company + " Directory(a)");
         System.out.println("Edit Specific Employee Information From The " + company + " Directory(l)");
         System.out.print("Quit Program(q)\t");
         menu = input.next().toLowerCase();
        if(!menu.equals("s") && !menu.equals("a") && !menu.equals("l") && !menu.equals("q")) {
            System.out.println("\nPlease Select A Valid Menu Option:");
        }
      }

      if(menu.equals("s")){
        int choice = 0;
        System.out.print("Would You Like To View An In-Order(1), Pre-Order(2) or Post-Order(3) Display?");
        choice = input.nextInt();
        System.out.println("\n" + company.toUpperCase() + " EMPLOYEE DIRECTORY:");
        if(choice == 1) {
        displayIO(root);
        System.out.println();
        } else if(choice == 2) {
        displayPrO(root);
        System.out.println();
        } else {
        displayPO(root);
        System.out.println();
        }
        menu();
      }
      else if(menu.equals("a")){
       System.out.print("\nProvide employee ID \t");
         int employeeID = input.nextInt();
     
       System.out.print("Provide First Name:\t");
         String firstName = input.next();
          
       System.out.print("Provide Last Name:\t");
        String lastName = input.next();
          
       System.out.print("Provide occupation:\t");
        String occupation = input.nextLine(); // extra line to change from next to nextLine
        occupation = input.nextLine();  

         root = addEmployee(root, employeeID, firstName, lastName, occupation);
         
         System.out.println("\n" + firstName + " " + lastName + " Has Been Successfully Added To The Directory\n");
         
         menu();           
      }
      else if(menu.equals("l")){
         employeeSearch();
      }
      else if(menu.equals("q")){
      System.out.println("\nThis Program Has Ended.");
         System.exit(0);
      }
    }
    
    // Method To Search For AN Employee
    public static void employeeSearch(){
      int choice;
   
         Employee temp = iterativeSearch();
         if(temp == null) {
            System.out.print("\nIt Doesn't Look Like That Employee ID Matches Any Existing Employees. Would You Like To Return To The Menu(1) Or Try Again(2)?");
            choice = input.nextInt();
            if(choice == 1) { menu(); }
            else { employeeSearch(); }
         } // end of if
         else {
            System.out.println("\n" + temp.employeeInfo() + "\n");
            System.out.print("Would You Like To Remove Employee From The Directory(1), Edit Employee Information(2), Or Return To The Main Menu(3)?\t");
            choice = input.nextInt();
            System.out.println();
            if(choice == 1){
               System.out.print("Are You Sure You Want To Remove " + temp.firstName + " " + temp.lastName + " From The Directory? 1 = No, 2 = Yes");
               choice = input.nextInt();
               if(choice == 1) {
                     System.out.println("\nOK, " + temp.firstName + " " + temp.lastName + " Was Not Removed.\n");
                     menu(); 
               } else {
                        //remove(temp.employeeID);
                        System.out.println("Employee Has Been Successfully Removed!");
                        menu();
                }
            }
            else if(choice == 2) {
               editEmployee(temp);
            }
            else { menu(); }
         } // end of else
    }
    
    //Iterative Method Using While Loop To Validate Employee Existance
    public static Employee iterativeSearch() {
      System.out.print("\nPlease Provide The ID Of The Employee You're Searching For\t");
         int employeeID = input.nextInt();
    
      while(root != null) {
        // pass right subtree as new tree
        if (employeeID > root.employeeID)
            root = root.right;
        // pass left subtree as new tree
        else if (employeeID < root.employeeID)
            root = root.left;
        else
            return root; // if the ID is found
      }
      return null;
    } // end of IS
   
    
    // Method to edit Employee
    public static void editEmployee(Employee temp) {
    String edit;
     int finished = 1;
     while(finished == 1){
        System.out.print("What Information Would You Like To Update, First Name(1), Last Name(2) Or Position(3)?\t");
        int choice = input.nextInt();
        if(choice == 1) {
         System.out.print("Please Provide A New First Name:\t");
         edit = input.next();
         temp.firstName = edit;
        }
        else if(choice == 2) {
         System.out.print("Please Provide A New Last Name:\t");
         edit = input.next();
         temp.lastName = edit;
        }
        else {
         System.out.print("Please Provide A New Position:\t");
         edit = input.nextLine();
         temp.position = edit;
        }
        
        System.out.println("\nEmployee Information Has Been Successfully Updated!");
        System.out.println("\n" + temp.employeeInfo());
        System.out.print("\n Have You Completed Your Updates For This Employee? 1 = No, 2 = Yes");
        finished = input.nextInt();
     }
      System.out.println();
      menu();
    } // end of edit

    // Method for In Order view - working
    public static void displayIO(Employee root) {
        if (root != null) {
        displayIO(root.left);
        System.out.println(root.employeeInfo());
        displayIO(root.right);
        }
    } // end IO
    
     // Method for Pre Order view - working
     public static void displayPrO(Employee root) {
        if (root != null) {
        System.out.println(root.employeeInfo());
        displayPrO(root.left);
        displayPrO(root.right);
        }
    } // end of PrO
    
     // Method for Post Order view - working
     public static void displayPO(Employee root) {
        if (root != null) {
        displayPO(root.left);
        displayPO(root.right);
        System.out.println(root.employeeInfo());
        }
    } // end of directory
    
     // Method to add employee to directory
     public static Employee addEmployee(Employee root, int employeeID, String firstName, String lastName, String occupation) {

      if (root == null) {
         root = new Employee(employeeID, firstName, lastName, occupation);
         
       } else if (employeeID < root.employeeID) {
         root.left = addEmployee(root.left, employeeID, firstName, lastName, occupation);
       } else if (employeeID > root.employeeID) {
         root.right = addEmployee(root.right, employeeID, firstName, lastName, occupation);
      }
      return root;
    }//end add 
    
} // end of lab6 class

class Employee {

    protected int employeeID;
    protected String firstName;
    protected String lastName;
    protected String position;
    protected Employee left;
    protected Employee right;

    public Employee(int employeeID, String firstName, String lastName, String position) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.left = null;
        this.right = null;
    }

    public String employeeInfo() {
        return "EMPLOYEE ID: " + this.employeeID + " - " + this.firstName + " " + this.lastName +  ", " + this.position;
    }

} // end node class employee
