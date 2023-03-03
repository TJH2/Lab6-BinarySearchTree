// Programmers: Ben Diskin, David Rukashaza-Hancock, T. Jake Holmes, Jaspreet Khatkar
// CS 145: Face-To-Face
// Date: 3/2/2023
// Assignment: Binary Search Tree
// Reference: Chapter 17
// Purpose: Use recursion to add/remove employees from a fictional database
// Extra Credit:





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

      System.out.println("This Program Will Allow You To View And Edit The Directory Of Employees At " + company + "\n");

      menu();

    } // end of main

    public static void menu() {
      String menu="";
      System.out.println("Please Select An Option:");
      while(!menu.equals("s")&&!menu.equals("a")&&!menu.equals("d")&&!menu.equals("q")){
         System.out.println(company + " Employee Directory(s)");
         System.out.println("Add An Employee To The " + company + " Directory(a)");
         System.out.println("Remove An Employee From The " + company + " Directory(d)");
         System.out.print("Quit Program(q)\t");
         menu = input.next().toLowerCase();
        if(!menu.equals("s") && !menu.equals("a") && !menu.equals("d") && !menu.equals("q")) {
            System.out.println("\nPlease Select A Valid Menu Option:");
        }
      }

      if(menu.equals("s")){
        System.out.println("\n" + company.toUpperCase() + " EMPLOYEE DIRECTORY:");
        directoryIO(root);
        System.out.println();
        //directoryPrO(root);
        //System.out.println();
        //directoryPO(root);
        //System.out.println();
        menu();
      }
      else if(menu.equals("a")){
       System.out.print("Provide employee ID \t");
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
      else if(menu.equals("d")){
         System.out.print("delete");
      }
      else if(menu.equals("q")){
      System.out.println("\nThis Program Has Ended.");
         System.exit(0);
      }
    }

    // Method for In Order view - working
    public static void directoryIO(Employee root) {
        if (root != null) {
        directoryIO(root.left);
        System.out.println(root.employeeInfo());
        directoryIO(root.right);
        }
    } // end IO
    
     // Method for Pre Order view - working
     public static void directoryPrO(Employee root) {
        if (root != null) {
        System.out.println(root.employeeInfo());
        directoryPrO(root.left);
        directoryPrO(root.right);
        }
    } // end of PrO
    
     // Method for Post Order view - working
     public static void directoryPO(Employee root) {
        if (root != null) {
        directoryPO(root.left);
        directoryPO(root.right);
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
    protected String occupation;
    protected Employee left;
    protected Employee right;

    public Employee(int employeeID, String firstName, String lastName, String occupation) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.left = null;
        this.right = null;
    }

    public String employeeInfo() {
        return "EMPLOYEE ID: " + this.employeeID + " - " + this.firstName + " " + this.lastName +  ", " + this.occupation;
    }

} // end node class employee
