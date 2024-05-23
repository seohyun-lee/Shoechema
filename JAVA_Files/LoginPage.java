package JAVA_Files;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.auth.SignUp;
import JAVA_Files.auth.Login;

import java.util.Scanner;

// 로그인 전 메인페이지
public class LoginPage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // 사용자가 1 또는 2를 입력할 때까지 메뉴 선택을 반복
            ShopUI.printShopBanner(); // 쇼핑몰 배너 출력
            ShopUI.printPromoText1(); // 쇼핑몰 프로모션 문구1 출력
            System.out.println("+————————메뉴————————+");
            System.out.println("| 1. 회원가입\t\t\t|");
            System.out.println("| 2. 로그인\t\t\t|");
            System.out.println("+———————————————————+");
            System.out.print("원하시는 메뉴 번호를 입력해주세요: ");

            int choice = scanner.nextInt(); // 사용자로부터 번호를 입력받음
            scanner.nextLine();

            switch (choice) {
                case 1: // 사용자가 1을 입력한 경우, 회원가입 화면으로 이동
                    SignUp.signUp();
                    break;
                case 2: // 사용자가 2를 입력한 경우, 로그인 화면으로 이동
                    Login.login();
                    break;
                default: // 1 또는 2가 아닌 다른 입력이 들어온 경우, 재입력을 요청
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }
}

