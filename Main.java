import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryOperations libOps = new LibraryOperations();
        boolean running = true;

        System.out.println("--");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("--");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Update Book Quantity");
            System.out.println("5. Delete Book");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    libOps.addBook(title, author, qty);
                    break;
                case 2:
                    libOps.viewAllBooks();
                    break;
                case 3:
                    System.out.print("Enter Title Keyword: ");
                    String keyword = scanner.nextLine();
                    libOps.searchBook(keyword);
                    break;
                case 4:
                    System.out.print("Enter Book ID to Update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter New Quantity: ");
                    int newQty = scanner.nextInt();
                    libOps.updateBookQuantity(updateId, newQty);
                    break;
                case 5:
                    System.out.print("Enter Book ID to Delete: ");
                    int delId = scanner.nextInt();
                    libOps.deleteBook(delId);
                    break;
                case 6:
                    System.out.print("Enter Book ID to Issue: ");
                    int issueId = scanner.nextInt();
                    libOps.issueBook(issueId);
                    break;
                case 7:
                    System.out.print("Enter Book ID to Return: ");
                    int returnId = scanner.nextInt();
                    libOps.returnBook(returnId);
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-8.");
            }
        }
        scanner.close();
    }
}