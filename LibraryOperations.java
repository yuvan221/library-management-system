import java.sql.*;

public class LibraryOperations {

    public void addBook(String title, String author, int quantity) {
        String query = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, quantity);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public void viewAllBooks() {
        String query = "SELECT * FROM books";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            System.out.println("\n--- Library Catalog ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Title: %s | Author: %s | Qty: %d\n",
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity"));
            }
            System.out.println("-----------------------");
            
        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
    }

    public void searchBook(String keyword) {
        String query = "SELECT * FROM books WHERE title LIKE ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("Found -> ID: %d | Title: %s | Author: %s | Qty: %d\n",
                        rs.getInt("book_id"), rs.getString("title"), rs.getString("author"), rs.getInt("quantity"));
            }
            if (!found) {
                System.out.println("No books found matching: " + keyword);
            }
        } catch (SQLException e) {
            System.out.println("Error searching book: " + e.getMessage());
        }
    }

    public void updateBookQuantity(int bookId, int newQuantity) {
        String query = "UPDATE books SET quantity = ? WHERE book_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, bookId);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book quantity updated successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, bookId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    public void issueBook(int bookId) {
        String checkQuery = "SELECT quantity FROM books WHERE book_id = ?";
        String updateQuery = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int qty = rs.getInt("quantity");
                if (qty > 0) {
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is currently out of stock.");
                }
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    public void returnBook(int bookId) {
        String query = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, bookId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Book ID not found. Return failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}