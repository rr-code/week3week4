import java.util.*;

public class Q5 {

    // 🔹 LINEAR SEARCH (First Occurrence)
    public static int linearSearchFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First -> Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First -> Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 LINEAR SEARCH (Last Occurrence)
    public static int linearSearchLast(String[] arr, String target) {
        int comparisons = 0;
        int lastIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                lastIndex = i;
            }
        }

        System.out.println("Linear Last -> Comparisons: " + comparisons);
        return lastIndex;
    }

    // 🔹 BINARY SEARCH (Find any occurrence)
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Search -> Comparisons: " + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search -> Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 COUNT OCCURRENCES (using binary search expansion)
    public static int countOccurrences(String[] arr, String target) {
        int index = binarySearch(arr, target);
        if (index == -1) return 0;

        int count = 1;

        // left side
        int i = index - 1;
        while (i >= 0 && arr[i].equals(target)) {
            count++;
            i--;
        }

        // right side
        i = index + 1;
        while (i < arr.length && arr[i].equals(target)) {
            count++;
            i++;
        }

        return count;
    }

    // 🔹 MAIN METHOD
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Linear Search
        int first = linearSearchFirst(logs, "accB");
        int last = linearSearchLast(logs, "accB");

        System.out.println("Linear First Index: " + first);
        System.out.println("Linear Last Index: " + last);

        // Sort for Binary Search
        Arrays.sort(logs);
        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        // Binary Search
        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary Index: " + index);
        System.out.println("Count: " + count);
    }
}