// Programmers: Ben Diskin, David Rukashaza-Hancock, T. Jake Holmes, Jaspreet Khatkar
// CS 145: Face-To-Face
// Date: 3/2/2023
// Assignment: Binary Search Tree
// Reference: Chapter 17
// Purpose: Use recursion to add/remove employees from a fictional database
// Extra Credit:





import java.util.*;
import java.io.*;

public class BinarySearchTree {

    private static Employee root;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
      //Sample Employees
      root = addEmployee(root, 50, "Jake", "H", "Junior Software Developer");
      root = addEmployee(root, 40, "Jaspreet", "K", "Cyber Security Specialist");
      root = addEmployee(root, 60, "David", "R-H", "Product Manager");
      root = addEmployee(root, 55, " Ben", "D", "Graphic Design Specialist");

      System.out.println("This Program Will Allow You To View And Edit The Directory Of Employees At Bronze Hill LLC\n");

      menu();

    } // end of main

    public static void menu() {
      String menu="";
      System.out.println("Please Select An Option:");
      while(!menu.equals("s")&&!menu.equals("a")&&!menu.equals("d")&&!menu.equals("q")){
         System.out.println("See Bronze Hill Employee Directory(s)");
         System.out.println("Add An Employee To The Bronze Hill Directory(a)");
         System.out.println("Remove An Employee To The Bronze Hill Directory(d)");
         System.out.print("Quit Program(q)\t");
         menu = input.next().toLowerCase();
        if(!menu.equals("s") && !menu.equals("a") && !menu.equals("d") && !menu.equals("q")) {
            System.out.println("\nPlease Select A Valid Menu Option:");
        }
      }

      if(menu.equals("s")){
        System.out.println("\nBRONZE HILL EMPLOYEE DIRECTORY:");
        directory(root);
        System.out.println();
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
        String occupation = input.nextLine(); 

         root = addEmployee(root, employeeID, firstName, lastName, occupation);           
          

      }
      else if(menu.equals("d")){
         System.out.print("delete");
      }
      else if(menu.equals("q")){
      System.out.println("\nThis Program Has Ended.");
         System.exit(0);
      }
    }

    public static void directory(Employee root) {
        if (root != null) {
        directory(root.left);
        System.out.println("\n" + root.employeeInfo());
        directory(root.right);
        }
    } // end of directory
    
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
        return "Employee Name: " + this.firstName + " " + this.lastName + "\nEmployee ID: " + this.employeeID + "\nPosition: " + this.occupation;
    }

} // end node class employee
