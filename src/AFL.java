import java.util.Random;
import java.util.Scanner;

public class AFL {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        boolean running = true;

        while (running) {
            System.out.println("\n===============================");
            System.out.println("Selamat Datanggg Di Pembantaian");
            System.out.println("===============================");
            System.out.print("Masukkan jumlah data(n): ");
            int n = input.nextInt();

            int[] originalData = new int[n];
            for (int i = 0; i < n; i++) {
                originalData[i] = rand.nextInt(1000) + 1; 
            }

            System.out.println("\nData Acak Berhasil Dibuat.");
            if (n <= 20) printArray(originalData);

            System.out.println("\nPilih Metode Sortir:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Merge Sort");
            System.out.println("3. Quick Sort");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda (1-4): ");
            int pilihan = input.nextInt();

            int[] dataToSort = originalData.clone();
            long startTime, endTime;

            switch (pilihan) {
                case 1:
                    System.out.println("\nMenjalankan Bubble Sort...");
                    startTime = System.nanoTime();
                    bubbleSort(dataToSort);
                    endTime = System.nanoTime();
                    tampilkanHasil(dataToSort, endTime - startTime);
                    break;

                case 2:
                    System.out.println("\nMenjalankan Merge Sort...");
                    startTime = System.nanoTime();
                    mergeSort(dataToSort, 0, dataToSort.length - 1);
                    endTime = System.nanoTime();
                    tampilkanHasil(dataToSort, endTime - startTime);
                    break;

                case 3:
                    System.out.println("\nMenjalankan Quick Sort...");
                    startTime = System.nanoTime();
                    quickSort(dataToSort, 0, dataToSort.length - 1);
                    endTime = System.nanoTime();
                    tampilkanHasil(dataToSort, endTime - startTime);
                    break;

                case 4:
                    running = false;
                    System.out.println("Program selesai");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

            if (pilihan != 4) {
                System.out.print("\nIngin mencoba lagi? (y/n): ");
                char ulang = input.next().toLowerCase().charAt(0);
                if (ulang != 'y') running = false;
            }
        }
        input.close();
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) { arr[k] = L[i]; i++; }
            else { arr[k] = R[j]; j++; }
            k++;
        }
        while (i < n1) { arr[k] = L[i]; i++; k++; }
        while (j < n2) { arr[k] = R[j]; j++; k++; }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void tampilkanHasil(int[] arr, long time) {
        System.out.println("Hasil akhir:");
    }

    public static void printArray(int[] arr) {
        if (arr.length > 30) {
            for (int i = 0; i < 30; i++) System.out.print(arr[i] + " ");
            System.out.println("... (terpotong, n terlalu besar)");
        } else {
            for (int val : arr) System.out.print(val + " ");
            System.out.println();
        }
    }
}