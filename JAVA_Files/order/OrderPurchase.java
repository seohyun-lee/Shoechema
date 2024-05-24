package JAVA_Files.order;

import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderPurchase {

    public static void orderPurchase(int userId, int shoesOptionId, String deliveryAddress, int orderPrice, String paymentType) {
        String sql = "INSERT INTO Orders (user_id, shoes_option_id, delivery_address, delivery_status, order_price, payment_type) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, shoesOptionId);
            pstmt.setString(3,deliveryAddress);
            pstmt.setString(4, "Processing");
            pstmt.setInt(5, orderPrice);
            pstmt.setString(6, paymentType);

            boolean isAvailable = decreaseShoesOptionQuantity(shoesOptionId);
            if (isAvailable == true) {
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("주문이 완료되었습니다.");
                } else {
                    System.out.println("상품의 주문이 불가능합니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean decreaseShoesOptionQuantity(int shoesOptionId) throws SQLException {
        String checkSql = "SELECT quantity FROM ShoesOptions WHERE shoes_option_id = ?";
        String updateSql = "UPDATE ShoesOptions SET quantity = quantity - 1 WHERE shoes_option_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
             PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
        ) {
            checkPstmt.setInt(1, shoesOptionId);
            ResultSet rs = checkPstmt.executeQuery();
            if (rs.next()) {
                int currentQuantity = rs.getInt("quantity");
                if (currentQuantity > 0) {
                    updatePstmt.setInt(1, shoesOptionId);
                    updatePstmt.executeUpdate();
                    return true;
                } else {
                    System.out.println("재고가 없습니다.");
                }
            } else {
                System.out.println("해당 상품 옵션을 찾을 수 없습니다.");
            }
            return false;
        }
    }
}
