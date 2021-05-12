import java.util.Random;

public class Requests implements Runnable{
    private int FloorPersons;
    private int floors;
    private ElevatorOperator operator;

    public int getFloorPersons() {
        return FloorPersons;
    }

    public int getFloors() {
        return floors;
    }

    public ElevatorOperator getOperator() {
        return operator;
    }

    public void setFloorPersons(int persons) {
        this.FloorPersons = persons;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setOperator(ElevatorOperator operator) {
        this.operator = operator;
    }

    Requests(int FloorPersons, int floors, ElevatorOperator operator) {
        setFloorPersons(FloorPersons);
        setFloors(floors);
        setOperator(operator);
    }


    @Override
    public void run() {
        int iter = 0;
        while(true) {
            Random random = new Random();
            int start = random.nextInt(floors + 1);
            int amount = random.nextInt(FloorPersons + 1);
            for (int i = 0; i < amount; i++) {
                int finish = random.nextInt(floors + 1);
                while (start == finish) {
                    finish = random.nextInt(floors + 1);
                }
                Passenger passenger = new Passenger(start, finish, iter);
                getOperator().setPassengers(passenger);
                iter++;
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
