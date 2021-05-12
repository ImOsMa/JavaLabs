import java.util.ArrayList;

public class ElevatorOperator implements Runnable {
    private int floors;
    private int elevNum;
    private int places;
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private ArrayList<Elevator> elevators = new ArrayList<>();
    ArrayList<Passenger> upStartPass = new ArrayList<>();
    ArrayList<Passenger> downStartPass = new ArrayList<>();
    ArrayList<Passenger> upAimPass = new ArrayList<>();
    ArrayList<Passenger> downAimPass = new ArrayList<>();
    ArrayList<Passenger> mainPass = new ArrayList<>();

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public void setElevators(Elevator elev) {
        this.elevators.add(elev);
    }

    public void setPassengers(Passenger pass) {
        this.passengers.add(pass);
    }

    public void setElevNum(int elevNum) {
        this.elevNum = elevNum;
    }

    public int getFloors() {
        return this.floors;
    }

    public int getElevNum() {
        return this.elevNum;
    }

    public int getPlaces() {
        return this.places;
    }

    public ArrayList<Passenger> getPassengers() {
        return this.passengers;
    }

    public ArrayList<Elevator> getElevators() {
        return this.elevators;
    }

    public ElevatorOperator(int floors, int places, int elevNum) {
        setFloors(floors);
        setElevNum(elevNum);
        setPlaces(places);
        for (int i = 0; i < elevNum; i++) {
            Elevator elevator = new Elevator(i, 0);
            setElevators(elevator);
        }
    }

    private void elevSetDirectionWithCurFloor(Elevator elev, int curFloor) {
        if (curFloor == getFloors() - 1) {
            elev.setDirection(Direction.DOWN);
        }
        if (curFloor == 0) {
            elev.setDirection(Direction.UP);
        }
    }

    private void elevSetDirectionWittPassDirection(Elevator elev, int curFloor, int elevWork) {
        elevSetDirectionWithCurFloor(elev, curFloor);
        if (elev.getDirection() == Direction.STAND) {
            if (upAimPass.size() > downAimPass.size()
                    && upStartPass.size() > elevWork * getPlaces()) {
                elev.setDirection(Direction.UP);
            }
            else if (upAimPass.size() <= downAimPass.size()
                    && downStartPass.size() > elevWork * getPlaces()) {
                elev.setDirection(Direction.DOWN);
            }
        }
    }

    private void passengerDistribution(Passenger pass, Elevator elev) {
        if (pass.getCurrentFloor() > elev.getFloor())
            upStartPass.add(pass);
        else if (pass.getCurrentFloor() < elev.getFloor())
            downStartPass.add(pass);
        if (pass.getCurrentFloor() == elev.getFloor() &&  pass.getCurrentFloor() < pass.getFinishFloor()) {
            upAimPass.add(pass);
        }
        else if (pass.getCurrentFloor() == elev.getFloor() &&  pass.getCurrentFloor() > pass.getFinishFloor()) {
            downAimPass.add(pass);
        }
    }

    private void printIterationInfo(Elevator elev) {
        String dir = "";
        if (elev.getDirection() == Direction.UP) {
            dir = "UP";
        } else if (elev.getDirection() == Direction.DOWN) {
            dir = "DOWN";
        } else {
            dir = "STAND";
        }
        System.out.println("Elevator NUM: " + elev.getId() + "; FLOOR: " + elev.getFloor() +
                "; Direction: " + dir + "; Passenger number: " + elev.getPassengers().size());
    }
    private void elevSetDirectionWithPass(Elevator elev, int curFloor, int elevWork) {

        if(elev.getPassengers().size() == 0 || curFloor == getFloors() - 1) {
            if(elev.getDirection() == Direction.UP && (upStartPass.size() < elevWork * getPlaces()
                    || downStartPass.size() < elevWork * getPlaces())) {
                elev.setDirection(Direction.STAND);
            }
            else if (downStartPass.size() > elevWork * getPlaces()
                    || curFloor == getFloors() - 1) {
                elev.setDirection(Direction.DOWN);
            }
        } else if (curFloor == 0) {
            if (upStartPass.size() > elevWork * getPlaces())
                elev.setDirection(Direction.UP);
            else {
                elev.setDirection(Direction.STAND);
            }
        } else {
            elev.setDirection(Direction.UP);
        }
    }

    @Override
    public void run() {
        int iter = 0;
        while(true) {
            iter++;
            int elevWork = 0;
            System.out.println("----Iteration №" + iter + "----");
            for (Elevator elev: getElevators()) {
                elev.movement();
                int currentFloor = elev.getFloor();
                if(getPlaces() > elev.getPassengers().size()) {
                    // update lists
                    upStartPass = new ArrayList<>();
                    downStartPass = new ArrayList<>();
                    upAimPass = new ArrayList<>();
                    downAimPass = new ArrayList<>();
                    for (Passenger pass : getPassengers()) {
                        passengerDistribution(pass, elev);
                    }
                    elevSetDirectionWithPass(elev, currentFloor, elevWork);
                    elevSetDirectionWittPassDirection(elev, currentFloor, elevWork);
                    mainPass = new ArrayList<>();
                    if (elev.getDirection() == Direction.DOWN) {
                        mainPass = upAimPass;
                    }
                    else if (elev.getDirection() == Direction.UP) {
                        mainPass = downAimPass;
                    }
                    while (mainPass.size() != 0 && elev.getPassengers().size() < getPlaces()) {
                        elev.setPassengers(mainPass.get(0));
                        getPassengers().remove(mainPass.get(0));
                        mainPass.remove(0);
                    }
                }
                else {
                    elevSetDirectionWithCurFloor(elev, currentFloor);
                }
                elevWork++;
                printIterationInfo(elev);
            }
            try {
                Thread.sleep(1800);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
