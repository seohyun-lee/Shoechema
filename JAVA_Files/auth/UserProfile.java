package JAVA_Files.auth;

import JAVA_Files.MainPage;
import JAVA_Files.UI.ShopUI;
import JAVA_Files.order.OrderList;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserProfile {
    //유저 정보 조회 메소드
    public static void showUserProfile() {
        Scanner scanner = new Scanner(System.in);
        ShopUI.printUserProfileBanner();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT email, phone_number, address FROM Users WHERE user_id = ?"))
        {
            int userId = MainPage.loggedInUserId; //현재 로그인된 유저 아이디 가져오기
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();//유저아이디로 유저 정보 가져오기

            //해당 uerId의 유져가 존재하는 경우
            if (rs.next()) {
                //userId 유저의 이메일, 전화번호, 주소 받아오기
                String userEmail = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");

                System.out.println("+————————내 정보————————");
                System.out.println("이메일: " + userEmail);
                System.out.println("전화번호: " + phoneNumber);
                System.out.println("주소: " + address);
                System.out.println("—————————메뉴—————————");
                System.out.println("1. 주소 변경");
                System.out.println("2. 내 주문 보기");
                System.out.println("3. 마이페이지 나가기");
                System.out.println("—————————————————————");
                System.out.print("원하시는 메뉴 번호를 입력해주세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        updateAddress(userId, scanner);
                        break;
                    case 2:
                        OrderList.orderList(userId);
                        break;
                    case 3:
                        System.out.println("마이페이지를 나갑니다.");
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

    //주소 변경 메소드
    private static void updateAddress(int userId, Scanner scanner) {
        System.out.println("주소 변경");
        System.out.print("새로운 주소를 입력해주세요: ");
        String newAddress = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE Users SET address = ? WHERE user_id = ?"))
        {
            pstmt.setString(1, newAddress);
            pstmt.setInt(2, userId);

            pstmt.executeUpdate();
            System.out.println("주소 변경이 완료되었습니다.");

            showUserProfile(); //마이페이지로 이동

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
