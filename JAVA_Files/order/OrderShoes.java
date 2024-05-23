package JAVA_Files.order;

import JAVA_Files.MainPage;
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
             PreparedStatement pstmtDelivery = conn.prepareStatement("SELECT address, phone_number FROM Users WHERE user_id = ?");
             PreparedStatement pstmtProduct = conn.prepareStatement("SELECT name, size_number, price FROM ShoesOptions, Shoes, Sizes" +
                     " WHERE Shoes.shoes_id = ShoesOptions.shoes_id AND Sizes.size_id = ShoesOptions.size_id AND shoes_option_id = ?");
        ) {
            int userId = MainPage.loggedInUserId; //현재 로그인된 유저 아이디 가져오기
            pstmtDelivery.setInt(1, userId);
            ResultSet rsDelivery = pstmtDelivery.executeQuery(); //유저아이디로 유저 정보 가져오기

            pstmtProduct.setInt(1, shoesOptionId);
            ResultSet rsProduct = pstmtProduct.executeQuery();

            if (rsDelivery.next() && rsProduct.next()) { //상품, 유저 하나
                String shoesName = rsProduct.getString("name");
                int sizeNumber = rsProduct.getInt("size_number");
                int price = rsProduct.getInt("price");

                System.out.println("——————주문하기——————");
                System.out.println("신발 제품명: " + shoesName);
                System.out.println("신발 사이즈: " + sizeNumber);
                System.out.println("신발 수량 1개");
                System.out.println("———————————————————");
                System.out.println("결제 금액 " + price + "원");
                System.out.println("——————배송정보——————");

                // userId 유저의 주소, 전화번호 받아오기
                String address = rsDelivery.getString("address");
                String phoneNumber = rsDelivery.getString("phone_number");
                if (address == null) {
                    System.out.println("비송지가 입력되지 않았습니다.\n" +
                            "배송지를 입력해 주세요: ");
                    address = scanner.nextLine();
                    System.out.println("———————————————————");
                }
                if (phoneNumber == null) {
                    System.out.println("전화번호가 입력되지 않았습니다.\n" +
                            "전화번호를 입력해 주세요: ");
                    phoneNumber = scanner.nextLine();
                    System.out.println("——————배송정보——————");
                }
                System.out.println("배송지: " + address);
                System.out.println("전화번호: " + phoneNumber);
                System.out.println("——————결제수단——————");
                System.out.println("1. 신용카드로 결제");
                System.out.println("2. 실시간 계좌이체로 결제");
                System.out.println("———————————————————");
                System.out.println("결제하시려면 1 또는 2를 입력하세요. (나가기: 0)");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        OrderPurchase.orderPurchase(shoesOptionId, address, price, "Credit Card");
                        break;
                    case 2:
                        OrderPurchase.orderPurchase(shoesOptionId, address, price, "Bank Transfer");
                        break;
                    case 0:
                        System.out.println("상세 조회 회면으로 되돌아갑니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }
            } else {
                System.out.println("존재하지 않는 유저입니다. 다시 시도해주세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
