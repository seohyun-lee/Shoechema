package JAVA_Files.product;

import JAVA_Files.MainPage;
import JAVA_Files.auth.UserProfile;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchProducts {
    static Scanner scanner = new Scanner(System.in);
    static SearchProducts searchProducts = new SearchProducts();
    public static void searchProducts() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------------------");
        System.out.println("[검색 페이지]");
        System.out.println("------------------------------");

        //SQL문 작성
        String sql = "" +
                "SELECT * " +
                "FROM Shoes " +
                "WHERE name LIKE ?";

        //PreparedStatement 지정
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            while(true) {
                int index = 1;

                System.out.print("신발 검색 : ");
                String searchQuery = scanner.nextLine();

                // PreparedStatement의 파라미터를 설정
                pstmt.setString(index, "%" + searchQuery + "%");
                index++;

                //SQL 문 실행 후 ResultSet 통해 데이터 읽기
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("------------------------------");
                    System.out.println("[검색 결과]");
                    while (rs.next()) { //결과 출력
                        //데이터 행 읽고 객체 생성
                        Shoes shoes = new Shoes();
                        shoes.setShoesId(rs.getInt("shoes_id"));
                        shoes.setName(rs.getString("name"));
                        shoes.setPrice(rs.getInt("price"));

                        //콘솔에 출력
                        System.out.println("[" + shoes.getShoesId() + "]");
                        System.out.println("제품명 : " + shoes.getName());
                        System.out.println("가격 : " + shoes.getPrice() + "원");
                    }
                }
                System.out.println("------------------------------");
                System.out.print("다시 검색하시겠습니까? [Y/N] : ");
                String answer = scanner.next();
                scanner.nextLine();

                if (answer.equals("Y")) continue;
                else if (answer.equals("N")) {
                    searchProducts.showMenu();
                    break;
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    void showMenu() {
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
