package JAVA_Files.auth;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 회원가입
public class SignUp {
    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        String email, password, phoneNumber, address;
        ShopUI.printSignUpBanner();

        // 이메일 입력받기 (중복 이메일일 경우 다시 입력, 공백 불가)
        while (true) {
            System.out.print("(필수) 이메일을 입력해 주세요: ");
            email = scanner.nextLine();

            if (email.isBlank()) {
                System.out.println("이메일은 비워둘 수 없습니다.");
            } else if (isEmailExists(email)) {
                System.out.println("이미 존재하는 이메일입니다. 다시 입력해주세요.");
            } else if (email.length() > 64) {
                System.out.println("글자수가 너무 많습니다. 64자 이내로 입력해 주세요.");
            } else {
                break;
            }
        }

        //비밀번호 입력받기
        while (true) {
            System.out.print("(필수) 비밀번호를 입력해 주세요: ");
            password = scanner.nextLine();

            if (password.isBlank()) {
                System.out.println("비밀번호는 비워둘 수 없습니다.");
            } else if (password.length() > 64) {
                System.out.println("글자수가 너무 많습니다. 64자 이내로 입력해 주세요.");
            } else {
                break;
            }
        }

        //전화번호 입력받기
        while (true) {
            System.out.print("(필수) 전화번호를 입력해 주세요: ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.isBlank()) {
                System.out.println("전화번호는 비워둘 수 없습니다.");
            } else if (phoneNumber.length() > 13) {
                System.out.println("글자수가 너무 많습니다. 13자 이내로 입력해 주세요.");
            } else {
                break;
            }
        }

        //주소 입력받기
        System.out.print("(선택) 주소를 입력해 주세요: ");
        address = scanner.nextLine();
        if (address.isBlank()) {
            address = null;
        } else if (address.length() > 255) {
            address = address.substring(0, 255); // 255byte만 저장
        }

        //입력받은 정보로 유저 등록
        if (registerUser(email, password, phoneNumber, address)) {
            System.out.println("회원가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
    }

    // 존재하는 이메일인지 확인하는 메서드
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

    //유저 등록 메서드
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

