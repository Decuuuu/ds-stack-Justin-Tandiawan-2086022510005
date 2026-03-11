public class PolaBintang {
    public static void main(String[] args) {
        int n = 5; 
        
        System.out.println("Pola 1:");
        for (int i = 0; i < n; i++) {
            
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            
            for (int j = i; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

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

        System.out.println("\nPola 3:");
        for (int i = 1; i <= 2; i++) {
            for (int j = i; j < 3; j++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
        System.out.println("  *"); 
        System.out.println("*****"); 
        System.out.println(" ***");   
    }
}