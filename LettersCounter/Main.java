import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Enter name of the input file: ");
        Scanner scanner = new Scanner(System.in);
        String inputName = scanner.nextLine();
        Counter LettersCounter = new Counter();
        Map<Character, Integer> count = LettersCounter.CounterRead(inputName);

        if(count != null) {
            System.out.println("Enter name of the output file: ");
            String outputName = scanner.nextLine();
            scanner.close();
            LettersCounter.CounterWrite(outputName, count);
        } else {
            System.out.println("Please, enter correct name of the file!!!");
        }
    }
}