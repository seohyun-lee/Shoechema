package JAVA_Files.order;

import JAVA_Files.MainPage;
import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OrderShoes {

    public static void order(int shoesOptionId) {
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement deliveryPstmt = conn.prepareStatement("SELECT phone_number FROM Users WHERE user_id = ?");
             PreparedStatement productPstmt = conn.prepareStatement(
                     "SELECT shoes_name, size_number, price FROM ShoesProduct WHERE shoes_option_id = ?");
        ) {
            int userId = MainPage.loggedInUserId; // 현재 로그인된 유저 아이디 가져오기
            deliveryPstmt.setInt(1, userId);
            ResultSet deliveryRs = deliveryPstmt.executeQuery(); // 유저 아이디로 유저 정보 가져오기

            productPstmt.setInt(1, shoesOptionId);
            ResultSet productRs = productPstmt.executeQuery();

            if (deliveryRs.next() && productRs.next()) { //상품, 유저 유효함
                String shoesName = productRs.getString("shoes_name");
                int sizeNumber = productRs.getInt("size_number");
                int price = productRs.getInt("price");
                String phoneNumber = deliveryRs.getString("phone_number");

                ShopUI.printDoOrderBanner();
                System.out.println(" 제품명 : " + shoesName);
                System.out.println(" 사이즈 : " + sizeNumber);
                System.out.println(" 수량 : 1개");
                System.out.println("-------------------------------");
                System.out.println(" 결제금액 : " + price + "원");
                System.out.println("-------------------------------");

                System.out.print("배송지를 입력해주세요: ");
                String address = scanner.nextLine();

                System.out.println("+———————————배송 정보————————————+");
                System.out.println(" 배송지 : " + address);
                System.out.println(" 전화번호 : " + phoneNumber);
                System.out.println("+———————————결제수단————————————+");
                System.out.println(" 1. 신용카드로 결제");
                System.out.println(" 2. 실시간 계좌이체로 결제");
                System.out.println("+——————————————————————————————+");
                System.out.println("결제하시려면 1 또는 2를 입력하세요. (나가기: 0)");
                System.out.print("입력 -> ");

                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        OrderPurchase.orderPurchase(userId, shoesOptionId, address, price, "Credit Card");
                        break;
                    case 2:
                        OrderPurchase.orderPurchase(userId, shoesOptionId, address, price, "Bank Transfer");
                        break;
                    case 0:
                        System.out.println("이전 화면으로 되돌아갑니다.");
                        break;
                    default:
                        System.out.println("입력 오류로 결제에 실패했습니다.");
                }
            } else {
                System.out.println("상품 또는 사용자가 유효하지 않습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
