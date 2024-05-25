package JAVA_Files;

import JAVA_Files.UI.ShopUI;
import JAVA_Files.auth.UserProfile;
import JAVA_Files.product.SearchProducts;
import JAVA_Files.product.ShowNewProducts;

import java.util.Scanner;

// 로그인 후 메인페이지
public class MainPage {
    public static int loggedInUserId = -1; // 로그인된 사용자의 user_id가 담기는 전역변수

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        while (true) { // 사용자가 1 또는 2룰 입력할 때까지 메뉴 선택을 반복
            ShopUI.printShopBanner(); // 쇼핑몰 배너 출력
            ShopUI.printPromoText2(); // 쇼핑몰 프로모션 문구2 출력

            System.out.println("+————————————메뉴————————————+");
            System.out.println("| 1. 신상품을 보기\t\t\t|");
            System.out.println("| 2. 상품을 검색하기\t\t\t|");
            System.out.println("| 3. 마이 페이지\t\t\t\t|");
            System.out.println("+———————————————————————————+");
            System.out.print("원하시는 메뉴 번호를 입력해주세요: ");

            int choice = scanner.nextInt(); // 사용자로부터 번호를 입력받음
            scanner.nextLine();

            switch (choice) {
                case 1: // 사용자가 1을 입력한 경우, 신상품 보기
                    ShowNewProducts.showNewProducts();
                    break;
                case 2: // 사용자가 2을 입력한 경우, 상품을 검색하기
                    SearchProducts.searchProducts();
                    break;
                case 3: // 사용자가 3을 입력한 경우, 마이 페이지
                    UserProfile.showUserProfile();
                    break;
                default: // 1~3이 아닌 다른 입력이 들어온 경우, 재입력을 요청
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }
}
