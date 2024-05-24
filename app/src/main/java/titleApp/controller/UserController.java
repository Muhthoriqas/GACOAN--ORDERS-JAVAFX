package titleApp.controller;

import titleApp.config.DatabaseConnector;
import titleApp.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController {
  public void addUser(User user) {
    String sql = "INSERT INTO users(username, password) VALUES(?,?)";

    try (Connection conn = DatabaseConnector.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, user.getUsername());
      pstmt.setString(2, user.getPassword());
      pstmt.executeUpdate();
      System.out.println("User added successfully");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
