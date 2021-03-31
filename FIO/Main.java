import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Please, enter name, surname, patronymic date of birth " +
                           "(example: Osmanov Islam Ramilevich 14.12.2001): ");
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        scanner.close();
        Initials man = new Initials();
        man.PrintChangedInfo(info);
    }
}
