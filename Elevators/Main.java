import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.print("Number of elevators: ");
        int elevatorsNum = Integer.parseInt(in.nextLine());
        System.out.print("Number of place in elevator: ");
        int places = Integer.parseInt(in.nextLine());
        System.out.print("Number of floors: ");
        int floors = Integer.parseInt(in.nextLine());
        System.out.print("Number of max people on the floor: ");
        int maxPeopleOnFloor = Integer.parseInt(in.nextLine());

        if (floors > 0 && places > 0 && maxPeopleOnFloor > 0 && elevatorsNum > 0) {
            ElevatorOperator operator = new ElevatorOperator(floors, places, elevatorsNum);
            Requests request = new Requests(maxPeopleOnFloor, floors, operator);
            Thread reqT = new Thread(request);
            Thread elevT = new Thread(operator);
            reqT.start();
            elevT.start();
        }
        else {
            System.out.print("Enter correct data");
        }
    }
}
