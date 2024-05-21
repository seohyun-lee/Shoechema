package JAVA_Files.auth;

import JAVA_Files.MainPage;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//로그인
public class Login {
    public static void login() {
        Scanner scanner = new Scanner(System.in);
        String email, password;

        //이메일 입력받기
        System.out.print("이메일을 입력해 주세요:");
        email = scanner.nextLine();

        //비밀번호 입력받기
        System.out.print("비밀번호를 입력해 주세요:");
        password = scanner.nextLine();

        //입력받은 정보로 로그인
        authenticateUser(email, password);
    }

    //입력받은 이메일과 비밀번호로 로그인하는 메소드
    private static void authenticateUser(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT user_id, password FROM Users WHERE email = ?"))
        {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery(); //입력받은 이메일로 user_id, password 찾기

            //입력받은 이메일이 존재하는 경우
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                //입력된 비밀번호와 저장된 비밀번호 비교
                if (storedPassword.equals(password)) {
                    //비밀번호가 일치하면 유저의 ID를 전역변수에 저장
                    MainPage.loggedInUserId = rs.getInt("user_id");
                    System.out.println("환영합니다.");
                    MainPage.main(); //메인페이지로 이동
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 로그인해 주세요.");
                }
            } else {
                System.out.println("존재하지 않는 이메일입니다. 다시 로그인해 주세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
