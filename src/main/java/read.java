import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class read {
    public static void main (String [] args) throws FileNotFoundException {
        File file = new File("C:\\Quiz\\pogoda\\src\\main\\java\\Parser.java");

        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] numberString = line.split(" ");
        int [] numbers = new int[3];
        int counter = 0;

        for (String number : numberString) {
            numbers[counter++] = Integer.parseInt(number);
        }
        System.out.println(Arrays.toString(numbers));
        scanner.close();
    }

    }
