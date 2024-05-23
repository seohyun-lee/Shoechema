package JAVA_Files.UI;

public class ShopUI {
    public static void printBanner() {
        System.out.println("+==========================================================+");
        System.out.println("|  ____  _                     _                           |");
        System.out.println("| / ___|| |__   ___   ___  ___| |__   ___ _ __ ___   __ _  |");
        System.out.println("| \\___ \\| '_ \\ / _ \\ / _ \\/ __| '_ \\ / _ \\ '_ ` _ \\ / _` | |");
        System.out.println("|  ___) | | | | (_) |  __/ (__| | | |  __/ | | | | | (_| | |");
        System.out.println("| |____/|_| |_|\\___/ \\___|\\___|_| |_|\\___|_| |_| |_|\\__,_| |");
        System.out.println("+==========================================================+");
        ShopUI.printWelcome();
    }

    public static void printWelcome() {
        System.out.println("  ~~~ 당신을 위한 신발 쇼핑몰 Shoechema에 오신 것을 환영합니다 ~~~  ");
    }

    public static void printDividingLine() {
        System.out.println("—————————————————————————————————————————————————————————————");
    }
}
