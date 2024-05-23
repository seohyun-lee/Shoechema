package JAVA_Files.order;

import JAVA_Files.MainPage;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetail {
    public static void orderDetail(int orderId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT name, size_number, " +
                     "ordered_at, delivery_address, delivery_status, order_price, payment_type FROM Orders, ShoesOptions, Shoes, Sizes " +
                     "WHERE Shoes.shoes_id = ShoesOptions.shoes_id AND Sizes.size_id = ShoesOptions.size_id " +
                     "AND Orders.shoes_option_id = ShoesOptions.shoes_option_id AND user_id = ? AND order_id = ?");
        ) {
            int userId = MainPage.loggedInUserId; //현재 로그인된 유저 아이디 가져오기
            pstmt.setInt(1, userId);
            pstmt.setInt(2, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String shoesName = rs.getString("name");
                int sizeNumber = rs.getInt("size_number");
                int price = rs.getInt("price");
                String orderedAt = rs.getTimestamp("ordered_at").toString();
                String deliveryAddress = rs.getString("delivery_address");
                String deliverStatus = rs.getString("delivery_status");
                String paymentType = rs.getString("");

                System.out.println("——————주문내역——————");
                System.out.println("주문번호: " + orderId);
                System.out.println("주문일자: " + orderedAt);
                System.out.println("배송 상태: " + deliverStatus);
                System.out.println("———————————————————");
                System.out.println("신발 제품명: " + shoesName);
                System.out.println("신발 사이즈: " + sizeNumber);
                System.out.println("신발 수량 1개");
                System.out.println("——————배송정보——————");
                System.out.println("배송지 " + deliveryAddress + "원");
                System.out.println("——————결제수단——————");
                System.out.println("결제 수단: " + paymentType);
                System.out.println("결제 금액 " + price + "원");
            }  else {
                System.out.println("존재하지 않는 상품. 다시 시도해주세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
