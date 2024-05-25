package JAVA_Files.order;

import JAVA_Files.MainPage;

import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 사용자로부터 입력받은 번호의 주문을 취소하는 로직을 구현한 클래스
public class OrderCancel {
    public static void orderCancel(int orderId) {
        // 현재 로그인된 유저 아이디 가져오기
        int userId = MainPage.loggedInUserId;
        // Orders 테이블에서 주어진 order_id와 user_id로 shoes_option_id와 delivery_status를 가져오는 SQL 쿼리
        String checkSql = "SELECT shoes_option_id, delivery_status FROM Orders WHERE order_id = ? AND user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkPstmt = conn.prepareStatement(checkSql)){

            checkPstmt.setInt(1, orderId); // checkSql의 1번째 파라미터 설정 (orderId)
            checkPstmt.setInt(2, userId); // checkSql의 2번째 파라미터 설정 (userId)
            ResultSet rs = checkPstmt.executeQuery(); // checkSql 쿼리 실행 및 결과 가져오기

            if (rs.next()) { // order_id와 user_id로 주문이 존재하는 경우
                String deliveryStatus = rs.getString("delivery_status"); // delivery_status를 변수 deliveryStatus에 저장
                int shoesOptionId = rs.getInt("shoes_option_id"); // shoes_option_id를 변수 shoesOptionId에 저장

                if ("Processing".equals(deliveryStatus)) { // 배송 상태가 'Processing'인 경우에만 주문 취소 가능
                    orderCancelTransaction(orderId, shoesOptionId);
                } else { // 'Delivered' 또는 'Shipped' 상태인 경우
                    System.out.println("출고 또는 배송이 완료되어 주문 취소가 불가능합니다.");
                }
            } else { // order_id와 user_id로 주문이 존재하지 않는 경우
                System.out.println("유효하지 않은 주문이거나 주문을 찾을 수 없습니다.");
            }
        } catch (SQLException e) { // SQL 실행 중 SQLException 발생 시 stack trace를 출력
            e.printStackTrace();
        }
    }

    // 주문 취소와 재고 개수 1 증가를 구현하는 메서드
    private static void orderCancelTransaction(int orderId, int shoesOptionId) {
        // Orders 테이블에서 주어진 order_id를 갖는 주문을 취소(삭제)하는 SQL 쿼리
        String deleteSql = "DELETE FROM Orders WHERE order_id = ?";
        // ShoesOptions 테이블에서 주문이 취소된 제품의 수량을 복구하는 SQL 쿼리
        String restoreSql = "UPDATE ShoesOptions SET quantity = quantity + 1 WHERE shoes_option_id = ?";

        try (Connection conn = DatabaseConnection.getConnection()
        ) {
            // 트랜잭션 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            try (PreparedStatement deletePstmt = conn.prepareStatement(deleteSql);
                 PreparedStatement restorePstmt = conn.prepareStatement(restoreSql)) {

                deletePstmt.setInt(1, orderId); // deleteSql의 1번째 파라미터 설정 (orderId)
                int affectedRows = deletePstmt.executeUpdate(); // deleteSql 쿼리 실행 및 결과 가져오기

                if (affectedRows > 0) { // 주문 취소가 성공한 경우, ShoesOption의 quantity를 1 증가
                    restorePstmt.setInt(1, shoesOptionId); // restoreSql의 1번째 파라미터 설정 (shoesOptionId)
                    restorePstmt.executeUpdate(); // restoreSql의 쿼리 실행 및 결과 가져오기
                    System.out.println("주문이 취소되었습니다.");
                    conn.commit(); // 모든 작업이 성공했으므로 커밋
                } else {
                    System.out.println("주문 취소에 실패했습니다.");
                    conn.rollback(); // 주문 취소 실패 시 롤백
                }
            } catch (SQLException e) {
                conn.rollback(); // 예외 발생 시 롤백
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}