package JAVA_Files.UI;

// 문자열 출력을 통해 쇼핑몰 애플리케이션에서 페이지 및 기능들을 명확히 표시하고자 작성한 클래스
public class ShopUI {
    // GuestPage, MainPage 배너
    public static void printShopBanner() { // 쇼핑몰 배너
        System.out.println();
        System.out.println("+==========================================================+");
        System.out.println("|  한 걸음 한 걸음, 스타일리시하게.                             |");
        System.out.println("|  ____  _                     _                           |");
        System.out.println("| / ___|| |__   ___   ___  ___| |__   ___ _ __ ___   __ _  |");
        System.out.println("| \\___ \\| '_ \\ / _ \\ / _ \\/ __| '_ \\ / _ \\ '_ ` _ \\ / _` | |");
        System.out.println("|  ___) | | | | (_) |  __/ (__| | | |  __/ | | | | | (_| | |");
        System.out.println("| |____/|_| |_|\\___/ \\___|\\___|_| |_|\\___|_| |_| |_|\\__,_| |");
        System.out.println("+==========================================================+");
    }

    // auth 배너들
    public static void printLoginBanner() { // 로그인 배너
        System.out.println("+==========================================================+");
        System.out.println("|                      ~~~ 로그인 ~~~                       |");
        System.out.println("+==========================================================+");
    }
    public static void printSignUpBanner() { // 회원가입 배너
        System.out.println("+==========================================================+");
        System.out.println("|                     ~~~ 회원가입 ~~~                      |");
        System.out.println("+==========================================================+");
    }
    public static void printUserProfileBanner() { // 회원 정보 배너
        System.out.println("+==========================================================+");
        System.out.println("|                     ~~~ 회원 정보 ~~~                      |");
        System.out.println("+==========================================================+");
    }
    public static void printChangeAddressBanner() { // 주소 변경 배너
        System.out.println("+―――――――――――――――――――+");
        System.out.println("|           주소 변경            |");
        System.out.println("+―――――――――――――――――――+");
    }

    // product 배너들
    public static void printSearchBanner() { // 신발 검색 배너
        System.out.println("+==========================================================+");
        System.out.println("|                     ~~~ 신발 검색 ~~~                      |");
        System.out.println("+==========================================================+");
    }
    public static void printSearchResultBanner() { // 신발 검색 결과 배너
        System.out.println("+―――――――――――――――――――+");
        System.out.println("|         신발 검색 결과          |");
        System.out.println("+―――――――――――――――――――+");
    }
    public static void printDetailResultBanner() { // 신발 상세 정보 배너
        System.out.println("+==========================================================+");
        System.out.println("|                   ~~~ 신발 상세 정보 ~~~                    |");
        System.out.println("+==========================================================+");
    }
    public static void printProductMenuBanner() { // 메뉴 배너
        System.out.println("+―――――――――――――――――――+");
        System.out.println("|             메뉴              |");
        System.out.println("+―――――――――――――――――――+");
    }
    public static void printNewProductsBanner() { // 신상품 목록 배너
        System.out.println("+==========================================================+");
        System.out.println("|                    ~~~ 신상품 목록  ~~~                    |");
        System.out.println("+==========================================================+");
    }

    // order 배너들
    public static void printDoOrderBanner() { // 주문하기 배너
        System.out.println("+―――――――――――――――――――+");
        System.out.println("|            주문하기            |");
        System.out.println("+―――――――――――――――――――+");
    }
    public static void printOrderListBanner() { // 주문 목록 배너
        System.out.println("+==========================================================+");
        System.out.println("|                    ~~~ 주문 목록  ~~~                     |");
        System.out.println("+==========================================================+");
    }
    public static void printOrderDetailBanner() { // 주문 상세 배너
        System.out.println("+―――――――――――――――――――+");
        System.out.println("|           주문 상세            |");
        System.out.println("+―――――――――――――――――――+");
    }
    
    // GuestPage의 프로모션 텍스트
    public static void printPromoText1() {
        System.out.println("      ~~~ Shoechema의 신발과 특별한 여정을 함께하세요 ~~~      ");
    }
    // MainPage의 프로모션 텍스트
    public static void printPromoText2() {
        System.out.println("   ~~~ 어디서든 빛나는 당신을 위한 최고의 선택, Shoechema ~~~    ");
    }

}
