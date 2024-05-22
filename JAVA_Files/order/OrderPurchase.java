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
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Orders values(?, ?, ?, ?, ?, ?, ?)");
        ) {
            int userId = MainPage.loggedInUserId; //현재 로그인된 유저 아이디 가져오기
            pstmt.setInt(1, userId);
            pstmt.setInt(2, shoesOptionId);
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(4,deliveryAddress);
            pstmt.setString(5, "Processing");
            pstmt.setInt(6, orderPrice);
            pstmt.setString(7, paymentType);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("주문이 완료되었습니다.");
            } else {
                System.out.println("존재하지 않는 상품. 다시 시도해주세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
