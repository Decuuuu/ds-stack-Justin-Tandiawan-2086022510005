public class AP_AFL {
    private int n;

    public AP_AFL(int n) {
        this.n = n;
    }

    public void cetakPola1() {
        System.out.println("Pola 1:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) System.out.print(" ");
            for (int j = i; j < n; j++) System.out.print("*");
            System.out.println();
        }
    }

    public void cetakPola2() {
        System.out.println("\nPola 2:");
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j < 3; j++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
        for (int i = 2; i >= 1; i--) {
            for (int j = 3; j > i; j--) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
    }

    public void cetakPola3() {
        System.out.println("\nPola 3:");
        for (int i = 1; i <= 2; i++) {
            for (int j = i; j < 3; j++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
        System.out.println("  *");
        System.out.println("******");
        System.out.println("  ***");
    }

    public static void main(String[] args) {
        AP_AFL printer = new AP_AFL(5);
        printer.cetakPola1();
        printer.cetakPola2();
        printer.cetakPola3();
    }
}

class AP_AFL_Optimized {
    public void cetakPola1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) System.out.print(" ");
            for (int j = i; j < n; j++) System.out.print("*");
            System.out.println();
        }
    }
}