package JAVA_Files.order;

import JAVA_Files.MainPage;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderPurchase {

    public static void orderPurchase(int shoesOptionId, String deliveryAddress, int orderPrice, String paymentType) {
        int userId = MainPage.loggedInUserId; //현재 로그인된 유저 아이디 가져오기

        String sql = "INSERT INTO Orders values(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, shoesOptionId);
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(4,deliveryAddress);
            pstmt.setString(5, "Processing");
            pstmt.setInt(6, orderPrice);
            pstmt.setString(7, paymentType);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                decreaseShoesOptionQuantity(shoesOptionId);
                System.out.println("주문이 완료되었습니다.");
            } else {
                System.out.println("상품의 주문이 불가능합니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void decreaseShoesOptionQuantity(int shoesOptionId) throws SQLException {
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
                    System.out.println("재고가 업데이트되었습니다.");
                } else {
                    System.out.println("재고가 부족합니다.");
                }
            } else {
                System.out.println("해당 상품 옵션을 찾을 수 없습니다.");
            }
        }
    }
}
