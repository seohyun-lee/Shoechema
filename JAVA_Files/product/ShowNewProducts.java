package JAVA_Files.product;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// 신상품 보기 로직 실행 클래스
public class ShowNewProducts {

    // 가장 최근에 등록된 상품 5개의 shoes_id, shoes_name, price, sizes를 모두 제시한 후, 메뉴 제시로 넘어가는 클래스
    public static void showNewProducts() {
        Scanner scanner = new Scanner(System.in);
        ShopUI.printNewProductsBanner();

        // SQL문 작성
        String sql = "SELECT s.shoes_id, s.shoes_name, s.price, s.release_date, " +
                "GROUP_CONCAT(DISTINCT sz.size_number ORDER BY sz.size_number ASC) AS sizes " +
                "FROM (SELECT * FROM Shoes ORDER BY release_date DESC LIMIT 5) AS s " +
                "JOIN ShoesOptions so ON s.shoes_id = so.shoes_id " +
                "JOIN Sizes sz ON so.size_id = sz.size_id " +
                "GROUP BY s.shoes_id, s.release_date " +
                "ORDER BY s.release_date DESC";

        // PreparedStatement 지정
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // SQL 문 실행 후 ResultSet 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // 데이터 행 읽고 객체 생성
                Shoes shoes = new Shoes();
                shoes.setShoesId(rs.getInt("shoes_id"));
                shoes.setShoesName(rs.getString("shoes_name"));
                shoes.setPrice(rs.getInt("price"));

                // 콘솔에 출력
                System.out.println(" [" + shoes.getShoesId() + "]");
                System.out.println(" 제품명 : " + shoes.getShoesName());
                System.out.println(" 가격 : " + shoes.getPrice() + "원");
                System.out.println(" 사이즈 종류 : " + rs.getString("sizes"));
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print("상품의 상세 정보를 보시겠습니까? [Y/N] -> ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("Y")) { // Y 또는 y 입력 시 상품 다시 검색
            ShowDetail.showDetail();
        }
        else {
            ProductMenu.showProductMenu();
        }
    }
}
