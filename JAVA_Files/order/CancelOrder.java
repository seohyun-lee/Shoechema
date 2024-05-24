package JAVA_Files.order;

import JAVA_Files.MainPage;

import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CancelOrder {

    public static void cancelOrder(int orderId) {
        int userId = MainPage.loggedInUserId;

        String checkSql = "SELECT shoes_option_id, delivery_status FROM Orders WHERE order_id = ? AND user_id = ?";
        String updateSql = "UPDATE Orders SET delivery_status = 'Canceled' WHERE order_id = ?";
        String restoreSql = "UPDATE ShoesOptions SET quantity = quantity + 1 WHERE shoes_option_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql);
             PreparedStatement restoreStmt = conn.prepareStatement(restoreSql);
        ) {
            checkStmt.setInt(1, orderId);
            checkStmt.setInt(2, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String deliveryStatus = rs.getString("delivery_status");
                int shoesOptionId = rs.getInt("shoes_option_id");

                if ("Processing".equals(deliveryStatus)) {
                    updateStmt.setInt(1, orderId);
                    int affectedRows = updateStmt.executeUpdate();

                    if (affectedRows > 0) {
                        restoreStmt.setInt(1, shoesOptionId);
                        restoreStmt.executeUpdate();
                        System.out.println("주문이 취소되었습니다. 재고가 복원되었습니다.");
                    } else {
                        System.out.println("주문 취소에 실패했습니다.");
                    }
                } else if ("Canceled".equals(deliveryStatus)) {
                    System.out.println("이미 취소된 주문입니다.");
                } else {
                    System.out.println("출고 또는 배송이 완료되어 주문 취소가 불가능합니다.");
                }
            } else {
                System.out.println("유효하지 않은 주문이거나 주문을 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}