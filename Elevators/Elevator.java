import java.util.ArrayList;

public class Elevator {
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    private int floor;
    private int id;
    private Direction direction;

    Elevator(int id, int floor) {
        setId(id);
        setFloor(floor);
        setDirection(Direction.STAND);
    }
    public int getId() {
        return id;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPassengers(Passenger pass) {
        this.passengers.add(pass);
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void movement() {
        if(getDirection() == Direction.STAND) {
            setFloor(getFloor());
        } else if (getDirection() == Direction.UP) {
            int new_floor = getFloor() + 1;
            setFloor(new_floor);
        } else if (getDirection() == Direction.DOWN) {
            int new_floor = getFloor() - 1;
            setFloor(new_floor);
        }
        ArrayList<Passenger> buf_pass = new ArrayList<Passenger>();
        getPassengers().removeIf(passenger -> (passenger.getFinishFloor() == getFloor()));
    }
}
