package JAVA_Files.order;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 주문 목록 조회 로직을 구현한 클래스
public class OrderList {
    // 유저 아이디룰 받아 주문 목록을 조회하는 메서드
    public static void orderList(int userId) {
        Scanner scanner = new Scanner(System.in);
        ShopUI.printOrderListBanner();

        // Orders에 ShoesProduct를 Join
        String sql = "SELECT order_id, ordered_at, shoes_name, size_number, delivery_status, order_price" +
                " FROM Orders" +
                " JOIN ShoesProduct ON Orders.shoes_option_id = ShoesProduct.shoes_option_id" +
                " WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            boolean orderExists = false;
            while (rs.next()) {
                orderExists = true;
                int orderId = rs.getInt("order_id");
                String shoesName = rs.getString("shoes_name");
                int sizeNumber = rs.getInt("size_number");
                String orderedAt = rs.getTimestamp("ordered_at").toString();
                String deliveryStatus = rs.getString("delivery_status");
                int orderPrice = rs.getInt("order_price");

                System.out.println("[주문번호 : " + orderId + "]");
                System.out.println(" 주문일자 : " + orderedAt);
                System.out.println(" 배송상태 : " + deliveryStatus);
                System.out.println("-------------------------------");
                System.out.println(" 제품명 : " + shoesName);
                System.out.println(" 사이즈 : " + sizeNumber + " (1개)");
                System.out.println(" 결제금액 : " + orderPrice + "원");
                System.out.println("+—————————————————————————————+");
            }
            if (orderExists == true)
                showOrderListMenu(userId);
            else {
                System.out.println("주문 내역이 없습니다.");
                System.out.println("이전 페이지로 돌아가려면 아무 키나 누르세요.");
                scanner.nextLine();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 주문의 상세 내역을 확인할지 묻는 메서드
    private static void showOrderListMenu(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("주문의 상세 내역을 확인하려면 주문번호를 입력하세요.(0 입력시 나가기)");
        System.out.print("주문번호 -> ");
        int orderId = scanner.nextInt(); // 주문번호를 입력받아 주문 상세 조회를 구현한 메서드 전달한다.
        scanner.nextLine();
        if (orderId == 0)
            return;
        OrderDetail.orderDetail(userId, orderId);
    }
}
