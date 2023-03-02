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

    Employee root;
    private LinkedList employeeDirectory = new LinkedList(); // empty list
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        String menu="";

        // sample employee
        Employee one = new Employee(1, "Jake", "Holmes", "Junior Software Developer");
        System.out.println(one.EmployeeInfo());

      while(!menu.equals("s")&&!menu.equals("a")&&!menu.equals("d")&&!menu.equals("q")){
         System.out.println("This program will show you a list of employees in a company");
         System.out.println("please selct and option: ");
         System.out.println("See all employees in search tree(s)");
         System.out.println("Add employee to search tree(a)");
         System.out.println("Delete employee from search tree (d)");
         System.out.println("quit program(q)");
        if(!menu.equals("s") || !menu.equals("a") || !menu.equals("d") || !menu.equals("q")) {
            System.out.print("Please select a valid menu option:\t");
            menu = input.next();
        }
      }

      if(menu.equals("s")){
         directory(root);
      }
      else if(menu.equals("a")){
         System.out.print("add");
      }
      else if(menu.equals("d")){
         System.out.print("delete");
      }
      else if(menu.equals("q")){
      System.out.println("thank you for your time.");
         System.exit(0);
      }

    } // end of main
    
    public static void directory(Employee root) {
        if (root == null) {
          return;
        } 
        inOrder(root.left);
        System.out.printf("%s ", node.data);
        inOrder(root.right);
    } // end of directory
    
} // end of lab6 class

class Employee {

    private int employeeID;
    private String firstName;
    private String lastName;
    private String occupation;
    private Employee left;
    private Employee right;

    public Employee(int employeeID, String firstName, String lastName, String occupation) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.left = null;
        this.right = null;
    }

    public String EmployeeInfo() {
        return "Employee Name: " + this.firstName + " " + this.lastName + "\nEmployee ID: " + this.employeeID + "\nPosition: " + this.occupation;
    }

} // end node class employee
