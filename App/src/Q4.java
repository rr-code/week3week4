import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class Q4{

    // 🔹 MERGE SORT (Ascending returnRate, Stable)
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) { // stable
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 🔹 QUICK SORT (Descending returnRate + volatility ASC)
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        // 🔸 Median-of-3 pivot selection
        int mid = (low + high) / 2;
        Asset pivot = medianOfThree(arr[low], arr[mid], arr[high]);

        int i = low - 1;

        for (int j = low; j <= high; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        return i;
    }

    // 🔹 Comparison logic
    private static int compare(Asset a, Asset b) {
        // Desc returnRate
        if (a.returnRate != b.returnRate) {
            return Double.compare(b.returnRate, a.returnRate);
        }
        // Asc volatility
        return Double.compare(a.volatility, b.volatility);
    }

    // 🔹 Median of 3 pivot
    private static Asset medianOfThree(Asset a, Asset b, Asset c) {
        if (compare(a, b) < 0) {
            if (compare(b, c) < 0) return b;
            else if (compare(a, c) < 0) return c;
            else return a;
        } else {
            if (compare(a, c) < 0) return a;
            else if (compare(b, c) < 0) return c;
            else return b;
        }
    }

    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 🔹 MAIN METHOD
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        // Merge Sort (Ascending)
        Asset[] mergeArr = assets.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (Asc): " + Arrays.toString(mergeArr));

        // Quick Sort (Descending + volatility ASC)
        Asset[] quickArr = assets.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort (Desc): " + Arrays.toString(quickArr));
    }
}