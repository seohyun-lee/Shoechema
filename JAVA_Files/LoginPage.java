package JAVA_Files;

import JAVA_Files.auth.SignUp;
import JAVA_Files.auth.Login;

import java.util.Scanner;

//로그인 전 메인페이지
public class LoginPage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("————————메뉴———————");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("———————————————————");
            System.out.print("원하시는 메뉴 번호를 입력해주세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    SignUp.signUp();
                    break;
                case 2:
                    Login.login();
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}

