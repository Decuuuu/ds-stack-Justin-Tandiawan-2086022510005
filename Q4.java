import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

public class Q4 {
    public static void solve(int[] input) {
        Stack<Integer> mainStack = new Stack<>();
        for (int n : input) mainStack.push(n);

        int takeCount = 1;
        while (takeCount <= mainStack.size()) {
            ArrayList<Integer> temp = new ArrayList<>();
            // Ambil sesuai jumlah takeCount
            for (int i = 0; i < takeCount && !mainStack.isEmpty(); i++) {
                temp.add(mainStack.pop());
            }
            // Urutkan (angka kecil masuk duluan/di bawah)
            Collections.sort(temp, Collections.reverseOrder());
            for (int n : temp) mainStack.push(n);
            
            takeCount++;
        }

        // Tampilkan hasil akhir
        while (!mainStack.isEmpty()) {
            System.out.print(mainStack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        int[] data = {42, 9, 17, 63, 28, 5, 74};
        solve(data); // Expected: Sorted output
    }
}