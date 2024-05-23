package JAVA_Files.product;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShowNewProducts {

    public static void showNewProducts() {
        ShopUI.printNewProductsBanner();

        //SQL문 작성
        String sql = "SELECT name, price, shoes_id FROM shoes ORDER BY release_date DESC LIMIT 5";

        //PreparedStatement 지정
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            //SQL 문 실행 후 ResultSet 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductMenu.showSearchMenu();
    }
}
