// Programmers: Ben Diskin, David Rukashaza-Hancock, T. Jake Holmes, Jaspreet Khatkar
// CS 145: Face-To-Face
// Date: 3/2/2023
// Assignment: Binary Search Tree
// Reference: Chapter 17
// Purpose: Use recursion to add/remove employees from a fictional database
// Extra Credit: Iterative Employee Search Function IterativeSearch() line 180-208


import java.util.*;

public class BinarySearchTree {

   static Scanner input = new Scanner(System.in);
   private static Employee root; // root node
   private static String company = "Arctic Gun Metal Energy Drink LLC";
    
   public static void main(String[] args) {
      //Sample Employees
      root = addEmployee(root, 50, "Jake", "H", "Mixologist");
      root = addEmployee(root, 40, "Jaspreet", "K", "Drink Tester");
      root = addEmployee(root, 60, "David", "R-H", "Brand Manager");
      root = addEmployee(root, 55, "Ben", "D", "Graphic Design Specialist");
   
      System.out.println("This Program Will Allow You To View And Edit A Directory Of Employees At " + company + "\n");
   
      menu();
   
   } // end of main

   public static void menu() {  
      int menu = 0;
      
      while(menu < 1 || menu > 4){
      
         System.out.println("MAIN MENU:");
         System.out.println("1. View " + company + " Employee Directory");
         System.out.println("2. Add An Employee To The " + company + " Directory");
         System.out.println("3. Update/Remove Specific Employee Information From The " + company + " Directory");
         System.out.println("4. Quit Program");
         System.out.print("\nPlease Select A Valid Menu Option Between 1 And 4:\t");
         
         try{
         
            menu = input.nextInt();
            System.out.println();
         }
         
         catch(InputMismatchException e){ String issue = input.nextLine(); }
      }
   
      if(menu == 1){
         int choice = 0;
         while(choice > 3 || choice < 1){
            
            System.out.print("How Would You Like To View The Display?:\n1. In-Order\n2. Pre-Order\n3. Post-Order\t");
            
            try{
               choice = input.nextInt();
            }
            
            catch(InputMismatchException e){ String issue = input.nextLine(); }
         
         }
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
      else if(menu == 2){
         
         int employeeID = -1;
         System.out.println("ADD EMPLOYEE TO THE DIRECTORY:");
         while(employeeID < 0){
         
            System.out.print("\nProvide Employee ID:\t");
            
            try{
               employeeID = input.nextInt();
               boolean taken = checkID(employeeID, root, false);
               while(taken == true) {
                  System.out.print("It Looks Like That ID Is Taken. Please Choose Another.\t");
                  employeeID = input.nextInt();
                  taken = checkID(employeeID, root, false);
               }
            }
            
            catch(InputMismatchException e){ String issue = input.nextLine(); }
         }
         System.out.print("Provide First Name:\t");
         String firstName = input.next();
          
         System.out.print("Provide Last Name:\t");
         String lastName = input.next();
          
         System.out.print("Provide Position:\t");
         String position = input.nextLine(); // extra line to change from next to nextLine
         position = input.nextLine();  
      
         root = addEmployee(root, employeeID, firstName, lastName, position);
         
         System.out.println("\n" + firstName + " " + lastName + " Has Been Successfully Added To The Directory\n");
         
         menu();           
      }
      else if(menu == 3){
         employeeSearch();
      }
      else if(menu == 4){
         System.out.println("\nThis Program Has Ended.");
         System.exit(0);
      }
   }
    
    // Method To Search For AN Employee
   public static void employeeSearch(){
      int choice;
   
      Employee temp = iterativeSearch();
      if(temp == null) {
         System.out.print("\nIt Doesn't Look Like That Employee ID Matches Any Existing Employees. Would You Like To:\n1. Return To The Menu\n2. Try Again \t");
         choice = input.nextInt();
         if(choice == 1) { menu(); }
         else { employeeSearch(); }
      } // end of if
      else {
         choice = -1;
      
         System.out.println("\n" + temp.employeeInfo() + "\n");
         
         while(choice < 0){
            try{
               System.out.print("Would You Like To:\n1. Remove Employee From The Directory\n2. Edit Employee Information\n3. Return To The Main Menu\t");
               choice = input.nextInt();
            }
            
            catch(InputMismatchException e){ String issue = input.nextLine(); }
         }
         
         System.out.println();
         if(choice == 1){
         
            choice = 0;
         
            while(choice < 1 || choice > 2){
            
               try{
                  System.out.print("Are You Sure You Want To Remove " + temp.firstName() + " " + temp.lastName() + " From The Directory?:\n1. YES\n2. NO\t");
                  choice = input.nextInt();
               }
               
               catch(InputMismatchException e){ String issue = input.nextLine(); }
            }

            if(choice == 1) {
               root = remove(root, temp.employeeID());
               System.out.println("Employee Has Been Successfully Removed!");
               menu();
            } else {
               System.out.println("\nOK, " + temp.firstName() + " " + temp.lastName() + " Was Not Removed.\n");
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
      int employeeID = -1;

      System.out.println("UPDATE/REMOVE EMPLOYEE INFORMATION:");
      
      while (employeeID < 0){
      
         try{
            System.out.print("\nPlease Provide The ID Of The Employee You're Searching For:\t");
            employeeID = input.nextInt();
         }
         
         catch(InputMismatchException e){ String issue = input.nextLine(); }
      }
      Employee temp = root;
    
      while(temp != null) {
        // pass right subtree as new tree
         if (employeeID > temp.employeeID()) {
            temp = temp.right(); 
         }
         // pass left subtree as new tree
         else if (employeeID < temp.employeeID()) {
            temp = temp.left();
         }
         else {
            return temp; // if the ID is found
         }
      }
      return null;
   } // end of IS
   
    // Method to edit Employee
   public static void editEmployee(Employee temp) {
      String edit;
      int choice = 0;
      int finished = 2;

      while(finished == 2){
      
         while(choice < 1 || choice > 4){
         
            try{
               System.out.print("What Information Would You Like To Update:\n1. First Name\n2. Last Name\n3. Position\n4. Employee ID\t");
               choice = input.nextInt();
            }
            catch(InputMismatchException e){
               String issue = input.nextLine();
            }
         }
         if(choice == 1) {
            System.out.print("Please Provide A New First Name:\t");
            edit = input.next();
            temp.setFirstName(edit);
         }
         else if(choice == 2) {
            System.out.print("Please Provide A New Last Name:\t");
            edit = input.next();
            temp.setLastName(edit);
         }
         else if(choice == 3){
            System.out.print("Please Provide A New Position:\t");
            edit = input.nextLine();
            edit = input.nextLine();
            temp.setPosition(edit);
         }
         
         else{
         
            System.out.print("Please Provide A New Employee ID:\t");
            int newID = 0;

            newID = input.nextInt();
            boolean taken = checkID(newID, root, false);
            
            while(taken == true) {
               System.out.print("It Looks Like That ID Is Taken. Please Choose Another.\t");
               newID = input.nextInt();
               taken = checkID(newID, root, false);
            }
            temp.setEmployeeID(newID);
         }
        
         System.out.println("\nEmployee Information Has Been Successfully Updated!");
         System.out.println("\n" + temp.employeeInfo());
         
         finished = 0;
         
         while(finished < 0 || finished > 2){
         
            try{
               System.out.print("\n Have You Completed Your Updates For This Employee?:\n1. Yes\n2. NO\t");
               finished = input.nextInt();
            }
            catch(InputMismatchException e){ String issue = input.nextLine(); }
         }
      }
      System.out.println();
      menu();
   } // end of edit

    // Method for In Order view - working
   public static void displayIO(Employee root) {
      if (root != null) {
         displayIO(root.left());
         System.out.println(root.employeeInfo());
         displayIO(root.right());
      }
   } // end IO
    
     // Method for Pre Order view - working
   public static void displayPrO(Employee root) {
      if (root != null) {
         System.out.println(root.employeeInfo());
         displayPrO(root.left());
         displayPrO(root.right());
      }
   } // end of PrO
    
     // Method for Post Order view - working
   public static void displayPO(Employee root) {
      if (root != null) {
         displayPO(root.left());
         displayPO(root.right());
         System.out.println(root.employeeInfo());
      }
   } // end of directory
    
     // Method to add employee to directory
   public static Employee addEmployee(Employee root, int employeeID, String firstName, String lastName, String occupation) {
      if (root == null) {
         root = new Employee(employeeID, firstName, lastName, occupation);
         
      } else if (employeeID < root.employeeID()) {
         root.setLeft(addEmployee(root.left(), employeeID, firstName, lastName, occupation));
      } else if (employeeID > root.employeeID()) {
         root.setRight(addEmployee(root.right(), employeeID, firstName, lastName, occupation));
      }
      return root;
   }//end add 

   //Method to remove an employee from the directory
   public static Employee remove(Employee node, int employeeID) {
      if(node == null) { 
         return null; }
    
      if(employeeID < node.employeeID()) {
         node.setLeft(remove(node.left(), employeeID));
      } else if(employeeID > node.employeeID()) {
         node.setRight(remove(node.right(), employeeID));
      } else {
         if(node.left() == null && node.right() != null) { // right open
            return node.right(); 
         } else if(node.right() == null && node.left() != null) { // left open
            return node.left();   
         } else if (node.right() == null && node.left() == null) { 
            return null ; } // neither      
         else {
            Employee nodeRight = node.right();
            while(nodeRight.left() != null) { nodeRight = nodeRight.left(); } // both
            node.setFirstName(nodeRight.firstName());
            node.setLastName(nodeRight.lastName());
            node.setPosition(nodeRight.position());
            node.setEmployeeID(nodeRight.employeeID());
            node.setRight(remove(node.right(), nodeRight.employeeID()));
         }
      } // end of else
      return node;
   }

   // Method to check if employee ID already exists
   public static boolean checkID(int employeeID, Employee root, boolean taken) {
      if (root != null) {
         taken = checkID(employeeID, root.left(), taken);
         if (root.employeeID() == employeeID){
            taken = true;
         }
         taken = checkID(employeeID, root.right(), taken);
      }
      return taken;  
   } // end of checkID

} // end of lab6 class

class Employee {

   private int employeeID;
   private String firstName;
   private String lastName;
   private String position;
   private Employee left;
   private Employee right;

   // -------------------- CONSTRUCTOR METHOD
   public Employee(int employeeID, String firstName, String lastName, String position) {
      this.employeeID = employeeID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
      this.left = null;
      this.right = null;
   }

   // -------------------- ACCESSOR METHODS
   public Employee left() {
      return this.left;
   }

   public Employee right() {
      return this.right;
   }

    public int employeeID() {
      return this.employeeID;
    }
    
    public String firstName() {
      return this.firstName;
    }

    public String lastName() {
      return this.lastName;
    }

    public String position() {
      return this.position;
    }



    public String employeeInfo() {
        return "EMPLOYEE ID: " + this.employeeID + " - " + this.firstName + " " + this.lastName +  ", " + this.position;
    }

    // -------------------- MUTATOR METHODS

    public void setEmployeeID(int employeeID) {
      this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public void setPosition(String position) {
      this.position = position;
    }

    public void setLeft(Employee left) {
      this.left = left;
    }

    public void setRight(Employee right) {
      this.right = right;
    }

} // end node class employee
