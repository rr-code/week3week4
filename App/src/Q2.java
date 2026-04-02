import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

public class Q2 {

    // 🔹 Bubble Sort (Ascending riskScore)
    public static void bubbleSortAscending(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }

        System.out.println("Bubble Sort Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (Descending riskScore + accountBalance)
    public static void insertionSortDescending(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && (
                    arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance)
            )) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // 🔹 Top 10 Highest Risk Clients
    public static void printTopClients(Client[] arr, int topN) {
        System.out.println("Top " + topN + " High-Risk Clients:");

        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.println(arr[i].name + "(" + arr[i].riskScore + ")");
        }
    }

    // 🔹 Main Method
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Bubble Sort (Ascending)
        Client[] bubbleArr = clients.clone();
        bubbleSortAscending(bubbleArr);
        System.out.println("Bubble Sorted (Asc): " + Arrays.toString(bubbleArr));

        // Insertion Sort (Descending)
        Client[] insertionArr = clients.clone();
        insertionSortDescending(insertionArr);
        System.out.println("Insertion Sorted (Desc): " + Arrays.toString(insertionArr));

        // Top Clients
        printTopClients(insertionArr, 10);
    }
}