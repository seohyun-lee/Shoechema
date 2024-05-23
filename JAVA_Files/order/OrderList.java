package JAVA_Files.order;

import JAVA_Files.MainPage;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderList {

    public static void orderList(int userId) { //현재 로그인된 유저 아이디를 받는다
        String sql = ""; // View
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String shoesName = rs.getString("name");
                int sizeNumber = rs.getInt("size_number");
                int price = rs.getInt("price");
                String orderedAt = rs.getTimestamp("ordered_at").toString();
                String deliveryAddress = rs.getString("delivery_address");
                String deliverStatus = rs.getString("delivery_status");
                String paymentType = rs.getString("payment_type");
                // Todo: 출력
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
