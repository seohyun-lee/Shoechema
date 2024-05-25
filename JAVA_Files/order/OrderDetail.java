package JAVA_Files.order;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 개별 주문의 상세 내역 조회 로직을 구현한 클래스
public class OrderDetail {
    public static void orderDetail(int userId, int inputOrderId) {
        // Orders에 ShoesProduct를 Join
        String sql =  "SELECT ordered_at, shoes_name, size_number, delivery_address, delivery_status, order_price, payment_type" +
                " FROM Orders" +
                " JOIN ShoesProduct ON Orders.shoes_option_id = ShoesProduct.shoes_option_id" +
                " WHERE user_id = ? AND order_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, inputOrderId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String shoesName = rs.getString("shoes_name");
                int sizeNumber = rs.getInt("size_number");
                int order_price = rs.getInt("order_price");
                String orderedAt = rs.getTimestamp("ordered_at").toString();
                String deliveryAddress = rs.getString("delivery_address");
                String deliveryStatus = rs.getString("delivery_status");
                String paymentType = rs.getString("payment_type");

                // 주문 내역 출력
                ShopUI.printOrderDetailBanner();
                System.out.println("[주문번호 : " + inputOrderId + "]");
                System.out.println(" 주문일자 : " + orderedAt);
                System.out.println(" 배송상태 : " + deliveryStatus);
                System.out.println("+———————————제품정보————————————+");
                System.out.println(" 제품명 : " + shoesName);
                System.out.println(" 사이즈 : " + sizeNumber);
                System.out.println(" 수량 : 1개");
                System.out.println("+———————————배송정보————————————+");
                System.out.println(" 배송지 : " + deliveryAddress);
                System.out.println("+———————————결제정보————————————+");
                System.out.println(" 결제수단 : " + paymentType);
                System.out.println(" 결제금액 : " + order_price + "원");
                System.out.println("+—————————————————————————————+");

                showOrderDetailMenu(inputOrderId, deliveryStatus);
            } else {
                System.out.println("존재하지 않는 주문입니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showOrderDetailMenu(int orderId, String deliveryStatus) {
        Scanner scanner = new Scanner(System.in);
        if (deliveryStatus.equals("Processing")) {
            System.out.println("주문을 취소(삭제)하려면 1을, 돌아가려면 0을 눌러주세요.");
            System.out.print("입력 -> ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0)
                return;
            OrderCancel.orderCancel(orderId);
        } else {
            System.out.println("돌아가려면 아무 키나 눌러주세요.");
            System.out.print("입력 -> ");
            scanner.nextLine();
        }
    }
}
