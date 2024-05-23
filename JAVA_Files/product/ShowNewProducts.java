package JAVA_Files.product;

import JAVA_Files.MainPage;
import JAVA_Files.UI.ShopUI;
import JAVA_Files.auth.UserProfile;
import JAVA_Files.util.DatabaseConnection;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowNewProducts {

    public static void showNewProducts() {
        Scanner scanner = new Scanner(System.in);
        ShopUI.printDividingLine();

        //SQL문 작성
        String sql = "" +
                "SELECT name, price, shoes_id " +
                "FROM shoes " +
                "ORDER BY release_date DESC " +
                "LIMIT 5";

        //PreparedStatement 지정
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            //SQL 문 실행 후 ResultSet 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
//            int i = 1;
            while(rs.next()) {
                //데이터 행 읽고 객체 생성
                Shoes shoes = new Shoes();
                shoes.setShoesId(rs.getInt("shoes_id"));
                shoes.setName(rs.getString("name"));
                shoes.setPrice(rs.getInt("price"));

                //콘솔에 출력
                System.out.println("[" + shoes.getShoesId() + "]");
                System.out.println("제품명 : " + shoes.getName());
                System.out.println("가격 : " + shoes.getPrice() + "원");
//                i++; //상품 번호 증가
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------");
        System.out.println("[메뉴]");
        System.out.println("1. 상세보기");
        System.out.println("2. 마이페이지");
        System.out.println("3. 내 주문 보기");
        System.out.println("4. 메인 페이지로 이동");
        System.out.println("5. 상품 검색 페이지");
        System.out.println("------------------------------");

        System.out.print("메뉴 번호 입력 -> ");
        int menu = scanner.nextInt();

        switch (menu) {
            case 1:
                ShowDetail.showDetail();
                break;
            case 2:
                UserProfile.showUserProfile();
                break;
            case 3:
                UserProfile.showOrders(MainPage.loggedInUserId);
                break;
            case 4:
                MainPage.main();
                break;
            case 5:
                SearchProducts.searchProducts();
                break;
            case 0:
                ShowNewProducts.showNewProducts();
                break;
            default:
                System.out.println("번호를 잘못 입력하셨습니다.");
        }
    }
}
