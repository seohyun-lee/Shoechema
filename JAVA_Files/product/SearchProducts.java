package JAVA_Files.product;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 검색 페이지
public class SearchProducts {

    public static void searchProducts() {
        Scanner scanner = new Scanner(System.in);
        ShopUI.printSearchBanner(); // 검색 배너 출력

        //SQL문 작성
        String sql = "SELECT * FROM Shoes WHERE shoes_name LIKE ?";

        //PreparedStatement 지정
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ){
            System.out.print("신발 검색: ");
            String searchQuery = scanner.nextLine();

            // PreparedStatement의 파라미터를 설정
            pstmt.setString(1, "%" + searchQuery + "%");

            // SQL 문 실행 후 ResultSet 통해 데이터 읽기
            try (ResultSet rs = pstmt.executeQuery()) {
                ShopUI.printSearchResultBanner(); // 검색 결과 배너 출력
                while (rs.next()) { // 결과 출력
                    // 데이터 행 읽고 객체 생성
                    Shoes shoes = new Shoes();
                    shoes.setShoesId(rs.getInt("shoes_id"));
                    shoes.setShoesName(rs.getString("shoes_name"));
                    shoes.setPrice(rs.getInt("price"));

                    // 결과 출력
                    System.out.println(" [" + shoes.getShoesId() + "]");
                    System.out.println(" 제품명 : " + shoes.getShoesName());
                    System.out.println(" 가격 : " + shoes.getPrice() + "원");
                    System.out.println("--------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print("다시 검색하시겠습니까? [Y/N] -> ");
        String answer = scanner.next();
        scanner.nextLine();

        if (answer.equalsIgnoreCase("Y")) { // Y 또는 y 입력 시 상품 다시 검색
            searchProducts();
        }
        else {
            ProductMenu.showProductMenu();
        }
    }
}
