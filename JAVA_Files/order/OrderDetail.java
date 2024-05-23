package JAVA_Files.order;

import JAVA_Files.MainPage;
import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetail {
    public static void orderDetail(int userId, int orderId) {
        String sql = "SELECT name, size_number, ordered_at, delivery_address, delivery_status, order_price, payment_type" +
                " FROM Orders, ShoesOptions, Shoes, Sizes" +
                " WHERE Shoes.shoes_id = ShoesOptions.shoes_id" +
                " AND Sizes.size_id = ShoesOptions.size_id" +
                " AND Orders.shoes_option_id = ShoesOptions.shoes_option_id" +
                " AND user_id = ?" +
                " AND order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
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
                String paymentType = rs.getString("payment_type");

                // 주문 내역 출력
                ShopUI.printOrderDetailBanner();
                System.out.println(" 주문번호: " + orderId);
                System.out.println(" 주문일자: " + orderedAt);
                System.out.println(" 배송상태: " + deliverStatus);
                System.out.println("-------------------------------");
                System.out.println(" 제품명: " + shoesName);
                System.out.println(" 사이즈: " + sizeNumber);
                System.out.println(" 수량: 1개");
                System.out.println("+———————————배송정보————————————+");
                System.out.println(" 배송지: " + deliveryAddress);
                System.out.println("+———————————결제정보————————————+");
                System.out.println(" 결제수단: " + paymentType);
                System.out.println(" 결제금액: " + price + "원");
            }  else {
                System.out.println("존재하지 않는 상품입니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}