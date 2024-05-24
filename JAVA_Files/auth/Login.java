package JAVA_Files.auth;

import JAVA_Files.MainPage;
import JAVA_Files.UI.ShopUI;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 로그인
public class Login {
    // 이메일과 비밀번호를 입력받고, 로그인 진행 메서드를 호출하는 메서드
    public static void login() {
        Scanner scanner = new Scanner(System.in);
        String email, password;

        ShopUI.printLoginBanner();
        // 사용자로부터 이메일 입력받기
        System.out.print("이메일을 입력해 주세요: ");
        email = scanner.nextLine();

        // 사용자로부터 비밀번호 입력받기
        System.out.print("비밀번호를 입력해 주세요: ");
        password = scanner.nextLine();

        // 입력받은 정보로 로그인
        System.out.println("로그인 중... ");
        authenticateUser(email, password);
    }

    // 입력받은 이메일과 비밀번호로 로그인하는 메소드
    private static void authenticateUser(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT user_id, password FROM Users WHERE email = ?"))
        {
            // 입력받은 이메일로 사용자를 찾아 user_id, password를 가져오는 쿼리를 실행
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            // 입력받은 이메일로 사용자가 존재하는 경우
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // 입력된 비밀번호와 저장된 비밀번호를 비교
                if (storedPassword.equals(password)) {
                    // 비밀번호가 일치하면 user_id를 전역변수 loggedInUserId에 저장
                    MainPage.loggedInUserId = rs.getInt("user_id");
                    MainPage.main(); //메인페이지로 이동
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 로그인해 주세요.");
                }
            } else {
                System.out.println("해당 이메일로 가입된 사용자가 없습니다. 다시 로그인해 주세요.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
