package JAVA_Files.product;

import JAVA_Files.order.OrderShoes;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//상품 상세보기 페이지
public class ShowDetail {
    private static Scanner scanner = new Scanner(System.in);

    public static void showDetail() {
        System.out.print("상품 번호 입력 -> ");
        int shoesId = scanner.nextInt();
        scanner.nextLine();
        //SQL문 작성
        String sql = "SELECT s.shoes_name, s.price, s.release_date, so.quantity, so.shoes_option_id, sz.size_number " +
                "FROM Shoes s " +
                "JOIN ShoesOptions so ON s.shoes_id = so.shoes_id " +
                "JOIN Sizes sz ON so.size_id = sz.size_id " +
                "WHERE s.shoes_id = ?";
        //PreparedStatement 지정
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            // 매개변수 설정
            pstmt.setInt(1, shoesId);
            //SQL 문 실행 후 ResultSet 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
            boolean shoesExists = false;
            while (rs.next()) {
                shoesExists = true;
                //데이터 행 읽고 객체 생성
                Shoes shoes = new Shoes();
                shoes.setShoesName(rs.getString("shoes_name")); //신발 이름
                shoes.setPrice(rs.getInt("price")); //신발 가격
                shoes.setReleaseDate(rs.getDate("release_date")); //신발 출시일
                shoes.setQuantity(rs.getInt("quantity")); //재고
                shoes.setSizeNum(rs.getInt("size_number")); //사이즈
                shoes.setShoesOptId(rs.getInt("shoes_option_id")); //shoes_option_id -> 제품 주문하기로 이어지는 매개변수

                //콘솔에 출력
                System.out.println("+—————————————————————————————+");
                System.out.println(" 상품명 : " + shoes.getShoesName());
                System.out.println(" 가격 : " + shoes.getPrice() + "원");
                System.out.println(" 사이즈 : " + shoes.getSizeNum());
                System.out.println(" 재고 : " + shoes.getQuantity() + "개");
                System.out.println(" 출시일 : " + shoes.getReleaseDate());
                System.out.println("------------------------------");
                System.out.println(" 상품 넘버 : " + shoes.getShoesOptId());
                System.out.println("+—————————————————————————————+");
            }
            if (shoesExists == true)
                ShowDetail.askOrder(); // 주문할지 묻는 메뉴
            else
                System.out.println("해당 넘버로 상품을 찾지 못했습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void askOrder() {
        while (true) {
            System.out.print("주문하기 [Y/N] -> ");
            String result = scanner.nextLine();

            if (result.equalsIgnoreCase("Y")) {
                System.out.print("주문하고자 하는 상품의 넘버를 입력해주세요: ");
                int shoesOptionId = scanner.nextInt();
                OrderShoes.order(shoesOptionId);
                break;
            } else if (result.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("메뉴를 잘못 입력하셨습니다.");
            }
        }
        ProductMenu.showProductMenu();
    }
}
