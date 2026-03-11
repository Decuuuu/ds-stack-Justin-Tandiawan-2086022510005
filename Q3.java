import java.util.Stack;

public class Q3 {
    // Strategi: Membangun angka menggunakan doubling (d+) dan increment (1+)
    public static String generateInstructions(int target) {
        if (target == 1) return "1";
        if (target % 2 == 0) {
            return generateInstructions(target / 2) + "d+";
        } else {
            return generateInstructions(target - 1) + "1+";
        }
    }

    public static void main(String[] args) {
        // Contoh untuk target angka tunggal dari test case
        int target = 5; 
        System.out.println(generateInstructions(target));
    }
}