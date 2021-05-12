public class Passenger {
    private int id;
    private int startFloor;
    private int finishFloor;
    private int currentFloor;

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public void setFinishFloor(int finishFloor) {
        this.finishFloor = finishFloor;
    }

    Passenger(int startFloor, int finishFloor, int id) {
        setStartFloor(startFloor);
        setCurrentFloor(startFloor);
        setFinishFloor(finishFloor);
        setId(id);
    }
    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getFinishFloor() {
        return finishFloor;
    }

    public int getStartFloor() {
        return startFloor;
    }
}
