package JAVA_Files.order;

import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 결제 진행 로직 구현 클래스
public class OrderPurchase {

    public static void orderPurchase(int userId, int shoesOptionId, String deliveryAddress, int orderPrice, String paymentType) {
        String sql = "INSERT INTO Orders (user_id, shoes_option_id, delivery_address, delivery_status, order_price, payment_type) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // 트랜잭션 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, shoesOptionId);
                pstmt.setString(3, deliveryAddress);
                pstmt.setString(4, "Processing");
                pstmt.setInt(5, orderPrice);
                pstmt.setString(6, paymentType);

                boolean isAvailable = decreaseShoesOptionQuantity(conn, shoesOptionId);
                if (isAvailable == true) {
                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("주문이 완료되었습니다.");
                        conn.commit(); // 모든 작업이 성공했으므로 커밋
                    } else {
                        System.out.println("상품의 주문이 불가능합니다.");
                        conn.rollback(); // 주문 삽입 실패 시 롤백
                    }
                } else {
                    System.out.println("재고가 부족하여 주문이 불가능합니다.");
                    conn.rollback(); // 재고 감소 실패 시 롤백
                }
            } catch (SQLException e) {
                conn.rollback();  // 예외 발생 시 롤백
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean decreaseShoesOptionQuantity(Connection conn, int shoesOptionId) throws SQLException {
        String checkSql = "SELECT quantity FROM ShoesOptions WHERE shoes_option_id = ?";
        String updateSql = "UPDATE ShoesOptions SET quantity = quantity - 1 WHERE shoes_option_id = ?";
        try (PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
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
