package JAVA_Files.auth;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//회원가입
public class SignUp {
    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        String email, password, phoneNumber, address;
        ShopUI.printDividingLine();

        //이메일 입력받기(중복 이메일일 경우 다시 입력)
        while (true) {
            System.out.print("이메일을 입력해 주세요: ");
            email = scanner.nextLine();

            if (!isEmailExists(email)) {
                break;
            } else {
                System.out.println("이미 존재하는 이메일입니다. 다시 입력해주세요.");
            }
        }

        //비밀번호 입력받기
        System.out.print("비밀번호를 입력해 주세요: ");
        password = scanner.nextLine();

        //전화번호 입력받기
        System.out.print("전화번호를 입력해 주세요: ");
        phoneNumber = scanner.nextLine();

        //주소 입력받기
        System.out.print("주소를 입력해 주세요: ");
        address = scanner.nextLine();

        //입력받은 정보로 유저 등록
        if (registerUser(email, password, phoneNumber, address)) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
    }

    //존재하는 이메일인지 확인하는 메소드
    private static boolean isEmailExists(String email) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT 1 FROM Users WHERE email = ?")) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery(); //입력받은 이메일로 유저 검색

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //유저 등록 메소드
    private static boolean registerUser(String email, String password, String phoneNumber, String address) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users (email, password, address, phone_number) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, address);
            pstmt.setString(4, phoneNumber);

            int rowsAffected = pstmt.executeUpdate(); //입력받은 정보로 유저 insert
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

