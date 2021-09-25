package lottery;

import java.util.Random;

public class Lottery {

    //Private attribute for the ticket
    private String ticket;

    //Empty Lottery Constructor
    public Lottery() {
        ticket = "";
    }

    //Constructor that takes reference to a random object
    public Lottery(Random randRef) {

        //Generate random numbers for the tickets
        ticket = "";
        for (int i = 0; i < 6; i++)
            ticket = ticket.concat(String.valueOf(randRef.nextInt(10)));
    }

    //Getter for the ticket
    public String GetTicket() {
        return ticket;
    }


    //Generate a string of random digits for the ticket
    public static String GenerateRandomWinner(Random randRef) {
        String digits = "";
        for (int i = 0; i < 6; i++) {
            digits = digits.concat(String.valueOf(randRef.nextInt(10)));
        }
        return digits;
    }

    static int partition(Lottery[] arr, int low, int high) {
        Lottery pivot = arr[high];

        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (Integer.parseInt(arr[j].GetTicket()) < Integer.parseInt(pivot.GetTicket())) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }


    static void swap(Lottery[] arr, int i, int j) {
        Lottery temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void quickSort(Lottery[] arr, int low, int high) {
        if (low < high) {
            int index = partition(arr, low, high);

            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    //Sort the array
    public static void Sort(Lottery[] tickets) {
        quickSort(tickets, 0, tickets.length - 1);
    }

    //Generate index of winner
    public static int GenerateSelectWinner(int maxIndex, Random randRef) {
        return randRef.nextInt(maxIndex);
    }

    public static Boolean Solution1(Lottery[] array, String sol, int maxIndex) {
        for (int i = 0; i < maxIndex; i++)
            if (array[i].GetTicket().equals(sol))
                return true;
        return false;
    }

    //@todo Check Code Here
    //Tried to make recursive binary search but always resulted in stack overflow
//    public static Boolean Solution2(Lottery[] array, int low, int high, String sol){
//
//        if(high >= low){
//            int mid = (low + (high -1)) / 2;
//
//            if((array[mid].GetTicket()).equals(sol))
//                return true;
//
//            if(Integer.parseInt(array[mid].GetTicket()) > Integer.parseInt(sol))
//                return Solution2(array, low, mid - 1, sol);
//
//            if(Integer.parseInt(array[mid].GetTicket()) < Integer.parseInt(sol))
//                return Solution2(array, mid + 1, high, sol);
//        }
//        return false;
//    }

    public static Boolean Solution2(Lottery[] array, int low, int high, String sol){
        int left = low;
        int right = high;
        int mid = (left + right)/2;

        while(left <= right){
            if(Integer.parseInt(array[mid].GetTicket()) > Integer.parseInt(sol))
                right = mid - 1;
            if(Integer.parseInt(array[mid].GetTicket()) < Integer.parseInt(sol))
                left = mid + 1;
            if((array[mid].GetTicket()).equals(sol))
                return true;
            mid = (left + right)/2;
        }

        return false;
    }
}
