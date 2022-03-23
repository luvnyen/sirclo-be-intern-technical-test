import java.util.Set;

abstract class Ship {
    private String name;
    private int maxPassengers;
    private int weight;
    private int maxWeight;
    private char dockLocation;

    public Ship(String name, int maxPassengers, int weight, int maxWeight, char dockLocation) {
        this.name = name;
        this.maxPassengers = maxPassengers;
        this.weight = weight;
        this.maxWeight = maxWeight;
        this.dockLocation = dockLocation;
    }

    public String getName() {
        return name;
    }

    public int getmaxPassengers() {
        return maxPassengers;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public char getDockLocation() {
        return dockLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setmaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setDockLocation(char dockLocation) {
        this.dockLocation = dockLocation;
    }

    public void printInfo() {
        System.out.println("\nSHIP INFORMATION");
        System.out.println("Ship name: " + name);
        System.out.println("Ship max passengers: " + maxPassengers);
        System.out.println("Ship weight: " + weight + " kg");
        System.out.println("Ship max weight: " + maxWeight + " kg");
        System.out.println("Ship dock location: " + dockLocation);
    }
}

class MotorBoat extends Ship {
    private int maxSpeed;
    private int maxDistance;

    public MotorBoat(String name, int maxPassengers, int weight, int maxWeight, char dockLocation, int maxSpeed, int maxDistance) {
        super(name, maxPassengers, weight, maxWeight, dockLocation);
        this.maxSpeed = maxSpeed;
        this.maxDistance = maxDistance;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Ship max speed: " + maxSpeed);
        System.out.println("Ship max distance: " + maxDistance);
    }
}

class SailBoat extends Ship {
    private String sailArea;

    public SailBoat(String name, int maxPassengers, int weight, int maxWeight, char dockLocation, String sailArea) {
        super(name, maxPassengers, weight, maxWeight, dockLocation);
        this.sailArea = sailArea;
    }

    public String getSailArea() {
        return sailArea;
    }

    public void setSailArea(String sailArea) {
        this.sailArea = sailArea;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Ship sail area: " + sailArea);
    }
}

class CruiseShip extends Ship {
    private int crewMembersCount;

    public CruiseShip(String name, int maxPassengers, int weight, int maxWeight, char dockLocation, int crewMembersCount) {
        super(name, maxPassengers, weight, maxWeight, dockLocation);
        this.crewMembersCount = crewMembersCount;
    }

    public int getCrewMembersCount() {
        return crewMembersCount;
    }

    public void setCrewMembersCount(int crewMembersCount) {
        this.crewMembersCount = crewMembersCount;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Ship crew members count: " + crewMembersCount);
    }
}

class ShippingYard {
    private Ship[] ships;
    private int motorBoatShipsCount = 0;
    private int sailBoatShipsCount = 0;
    private int cruiseShipShipsCount = 0;

    public ShippingYard() {
        this.ships = new Ship[0];
    }

    public int getAllShipsCount() {
        return ships.length;
    }

    public int getShipsCountByType(String type) {
        if (type.equals("Motor Boat")) {
            return motorBoatShipsCount;
        } else if (type.equals("Sail Boat")) {
            return sailBoatShipsCount;
        } else if (type.equals("Cruise Ship")) {
            return cruiseShipShipsCount;
        } else {
            return 0;
        }
    }

    public int getShipsCountByDockLocation(char dockLocation) {
        int count = 0;
        for (Ship ship : ships) {
            if (ship.getDockLocation() == dockLocation) {
                count++;
            }
        }
        return count;
    }

    public void printAllDockLocations() {
        Set<Character> dockLocations = new java.util.HashSet<Character>();
        for (Ship ship : ships) {
            dockLocations.add(ship.getDockLocation());
        }
        System.out.println("All dock locations that have ships:");
        for (Character dockLocation : dockLocations) {
            System.out.println("- Dock location " + dockLocation);
        }
    }

    public void addNewShip(Ship ship) {
        Ship[] newShips = new Ship[ships.length + 1];
        for (int i = 0; i < ships.length; i++) {
            newShips[i] = ships[i];
        }
        newShips[ships.length] = ship;
        ships = newShips;

        if (ship instanceof MotorBoat) {
            motorBoatShipsCount++;
        } else if (ship instanceof SailBoat) {
            sailBoatShipsCount++;
        } else if (ship instanceof CruiseShip) {
            cruiseShipShipsCount++;
        }

        System.out.println("Successfully added new ship with name " + ship.getName() + "!");
    }

    public void removeShipByName(String name) {
        int index = -1;
        for (int i = 0; i < ships.length; i++) {
            if (ships[i].getName().equals(name)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("There are no ships with the name " + name + " in the yard!");
            return;
        }

        Ship[] newShips = new Ship[ships.length - 1];
        for (int i = 0; i < index; i++) {
            newShips[i] = ships[i];
        }
        for (int i = index; i < ships.length - 1; i++) {
            newShips[i] = ships[i + 1];
        }
        ships = newShips;

        if (ships[index] instanceof MotorBoat) {
            motorBoatShipsCount--;
        } else if (ships[index] instanceof SailBoat) {
            sailBoatShipsCount--;
        } else if (ships[index] instanceof CruiseShip) {
            cruiseShipShipsCount--;
        }

        System.out.println("Successfully removed ship with name " + name + " from the yard!");
    }

    public void removeShipsByDockLocation(char dockLocation) {
        if (getShipsCountByDockLocation(dockLocation) == 0) {
            System.out.println("There are no ships in dock location " + dockLocation + "!");
        } else {
            Ship[] newShips = new Ship[ships.length - 1];
            int index = 0;
            for (int i = 0; i < ships.length; i++) {
                if (ships[i].getDockLocation() != dockLocation) {
                    newShips[index] = ships[i];
                    index++;
                } else {
                    if (ships[i] instanceof MotorBoat) {
                        motorBoatShipsCount--;
                    } else if (ships[i] instanceof SailBoat) {
                        sailBoatShipsCount--;
                    } else if (ships[i] instanceof CruiseShip) {
                        cruiseShipShipsCount--;
                    }
                }
            }
            ships = newShips;

            System.out.println("Successfully removed ships from dock location " + dockLocation + "!");
        }
    }

    public void printAllShipsInfo() {
        if (ships.length == 0) {
            System.out.println("There are no ships in the yard!");
        } else {
            for (int i = 0; i < ships.length; i++) {
                ships[i].printInfo();
            }
        }
    }

    public void printShipsInfoByDockLocation(char dockLocation) {
        if (ships.length == 0) {
            System.out.println("There are no ships in the yard!");
        } else {
            System.out.println("\nAll ships info by dock location " + dockLocation + ":");
            boolean isThereShipWithDockLocation = false;
            for (int i = 0; i < ships.length; i++) {
                if (ships[i].getDockLocation() == dockLocation) {
                    isThereShipWithDockLocation = true;
                    ships[i].printInfo();
                }
            }
            if (!isThereShipWithDockLocation) {
                System.out.println("There are no ships with the dock location " + dockLocation + "!");
            }
        }
    }

    public void printShipsByType(String type) {
        System.out.println("\nAll " + type + " info:");
        if (type.equals("Motor Boat")) {
            if (motorBoatShipsCount == 0) {
                System.out.println("There are no motor boats in the yard!");
            } else {
                for (int i = 0; i < ships.length; i++) {
                    if (ships[i] instanceof MotorBoat) {
                        ships[i].printInfo();
                    }
                }
            }
        } else if (type.equals("Sail Boat")) {
            if (sailBoatShipsCount == 0) {
                System.out.println("There are no sail boats in the yard!");
            } else {
                for (int i = 0; i < ships.length; i++) {
                    if (ships[i] instanceof SailBoat) {
                        ships[i].printInfo();
                    }
                }
            }
        } else if (type.equals("Cruise Ship")) {
            if (cruiseShipShipsCount == 0) {
                System.out.println("There are no cruise ships in the yard!");
            } else {
                for (int i = 0; i < ships.length; i++) {
                    if (ships[i] instanceof CruiseShip) {
                        ships[i].printInfo();
                    }
                }
            }
        } else {
            System.out.println("There are no ships with the type " + type + "!");
        }
    }

    public void printShipsInfoByName(String name) {
        if (ships.length == 0) {
            System.out.println("There are no ships in the yard!");
        } else {
            System.out.println("\nShips info by name '" + name + "':");

            boolean isThereShipsWithSameName = false;
            for (int i = 0; i < ships.length; i++) {
                if (ships[i].getName().equals(name)) {
                    isThereShipsWithSameName = true;
                    ships[i].printInfo();
                }
            }
            if (!isThereShipsWithSameName) {
                System.out.println("There are no ships with the name " + name + " in the yard!");
            }
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        // create all test cases
        ShippingYard shippingYard = new ShippingYard();
        
        shippingYard.addNewShip(new MotorBoat("Motor Boat 1", 10, 100, 200, 'A', 100, 100));
        shippingYard.addNewShip(new MotorBoat("Motor Boat 2", 10, 100, 200, 'B', 100, 100));
        shippingYard.addNewShip(new SailBoat("Sail Boat 1", 10, 100, 200, 'A', "Sail Area 1"));
        shippingYard.addNewShip(new SailBoat("Sail Boat 2", 10, 100, 200, 'B', "Sail Area 2"));
        shippingYard.addNewShip(new CruiseShip("Cruise Ship 1", 10, 100, 200, 'C', 10));
        shippingYard.addNewShip(new CruiseShip("Cruise Ship 2", 10, 100, 200, 'D', 10));

        // print all ships info
        System.out.println("\n==============================\n");
        System.out.println("All ships info:");
        shippingYard.printAllShipsInfo();

        // get all ships count
        System.out.println("\n==============================\n");
        System.out.println("All ships count: " + shippingYard.getAllShipsCount());

        // get ships count by type
        System.out.println("\n==============================\n");
        System.out.println("Motor boats count: " + shippingYard.getShipsCountByType("Motor Boat"));
        System.out.println("Sail boats count: " + shippingYard.getShipsCountByType("Sail Boat"));
        System.out.println("Cruise ships count: " + shippingYard.getShipsCountByType("Cruise Ship"));

        // print all dock locations that have ships
        System.out.println("\n==============================\n");
        shippingYard.printAllDockLocations();

        // print ships count by dock location
        System.out.println("\n==============================\n");
        System.out.println("Ships count by dock location 'A': " + shippingYard.getShipsCountByDockLocation('A'));
        System.out.println("Ships count by dock location 'E': " + shippingYard.getShipsCountByDockLocation('E'));

        // print all ships info by dock location
        System.out.println("\n==============================\n");
        shippingYard.printShipsInfoByDockLocation('A');
        shippingYard.printShipsInfoByDockLocation('E');

        // print all ships info by type
        System.out.println("\n==============================\n");
        shippingYard.printShipsByType("Motor Boat");
        shippingYard.printShipsByType("Sail Boat");
        shippingYard.printShipsByType("Cruise Ship");
        shippingYard.printShipsByType("Military Ship");

        // print all ships info by name
        System.out.println("\n==============================\n");
        shippingYard.printShipsInfoByName("Motor Boat 1");
        shippingYard.printShipsInfoByName("Sail Boat 3");

        // remove ship by name
        System.out.println("\n==============================\n");
        shippingYard.removeShipByName("Motor Boat 1");
        shippingYard.printShipsInfoByName("Motor Boat 1");
        shippingYard.removeShipByName("Motor Boat 3");

        // remove ships by dock location
        System.out.println("\n==============================\n");
        shippingYard.removeShipsByDockLocation('A');
        shippingYard.printShipsInfoByDockLocation('A');
        shippingYard.removeShipsByDockLocation('G');
        shippingYard.printAllDockLocations();
    }
}