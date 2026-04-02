import java.util.*;
import java.time.LocalTime;

class Transaction {
    String id;
    double fee;
    LocalTime timestamp;

    public Transaction(String id, double fee, LocalTime timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class week3week4 {

    // 🔹 Bubble Sort (by fee)
    public static void bubbleSortByFee(ArrayList<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (by fee + timestamp)
    public static void insertionSortByFeeAndTime(ArrayList<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && (
                    list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.isAfter(key.timestamp))
            )) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    // 🔹 High-fee detection (>50)
    public static void findHighFeeTransactions(ArrayList<Transaction> list) {
        System.out.println("High-fee outliers (>50):");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    // 🔹 Main method
    public static void main(String[] args) {

        ArrayList<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, LocalTime.of(10, 0)));
        transactions.add(new Transaction("id2", 25.0, LocalTime.of(9, 30)));
        transactions.add(new Transaction("id3", 5.0, LocalTime.of(10, 15)));

        // Bubble Sort (≤100)
        ArrayList<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSortByFee(bubbleList);
        System.out.println("Bubble Sorted: " + bubbleList);

        // Insertion Sort (100–1000)
        ArrayList<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSortByFeeAndTime(insertionList);
        System.out.println("Insertion Sorted: " + insertionList);

        // Outliers
        findHighFeeTransactions(transactions);
    }
}
