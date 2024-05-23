package JAVA_Files;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.auth.UserProfile;
import JAVA_Files.product.ShowNewProducts;

import java.util.Scanner;

// 로그인 후 메인페이지
public class MainPage {
    public static int loggedInUserId = -1;
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        while (true) { // 사용자가 1 또는 2룰 입력할 때까지 메뉴 선택을 반복
            ShopUI.printShopBanner(); // 쇼핑몰 배너 출력
            ShopUI.printPromoText2(); // 쇼핑몰 프로모션 문구2 출력
            System.out.println("+————————메뉴————————+");
            System.out.println("| 1. 마이페이지\t\t|");
            System.out.println("| 2. 신상품 보기\t\t|");
            System.out.println("+———————————————————+");
            System.out.print("원하시는 메뉴 번호를 입력해주세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    UserProfile.showUserProfile();
                    break;
                case 2:
                    ShowNewProducts.showNewProducts();
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
