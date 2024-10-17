import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car extends Vehicle {
    private int numberofDoors;
    private String transmissionType;

    public Car(String brand, String model, int year, int numberofDoors, String transmissionType) {
        super(brand, model, year);
        this.numberofDoors = numberofDoors;
        this.transmissionType = transmissionType;
    }
}
class Fleet {
    private List<Garage> garages;

    public Fleet() {
        garages = new ArrayList<>();
    }

    public void addGarage(Garage garage) {
        garages.add(garage);
        System.out.println("Гараж добавлен.");
    }

    public void removeGarage(Garage garage) {
        garages.remove(garage);
        System.out.println("Гараж удален.");
    }

    public List<Garage> getGarages() {
        return garages;
    }
}

class Garage {
    private List<Vehicle> vehicles;

    public Garage() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Транспортное средство добавлено.");
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        System.out.println("Транспортное средство удалено.");
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}

class Motorcycle extends Vehicle {
    private String bodyType;
    private boolean hasSidecar;

    public Motorcycle(String brand, String model, int year, String bodyType, boolean hasSidecar) {
        super(brand, model, year);
        this.bodyType = bodyType;
        this.hasSidecar = hasSidecar;
    }
}

class Vehicle {
    private String brand;
    private String model;
    private int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ")";
    }

    public void startEngine() {
        System.out.println("Двигатель " + brand + " " + model + " запущен.");
    }

    public void stopEngine() {
        System.out.println("Двигатель " + brand + " " + model + " остановлен.");
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fleet fleet = new Fleet();

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить гараж");
            System.out.println("2 - Удалить гараж");
            System.out.println("3 - Добавить транспортное средство в гараж");
            System.out.println("4 - Удалить транспортное средство из гаража");
            System.out.println("5 - Показать все гаражи и транспортные средства");
            System.out.println("0 - Выйти");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Завершение программы...");
                break;
            }

            switch (choice) {
                case 1:
                    Garage garage = new Garage();
                    fleet.addGarage(garage);
                    System.out.println("Новый гараж добавлен.");
                    break;

                case 2:
                    System.out.println("Введите номер гаража для удаления:");
                    int garageIndex = scanner.nextInt();
                    if (garageIndex >= 0 && garageIndex < fleet.getGarages().size()) {
                        fleet.removeGarage(fleet.getGarages().get(garageIndex));
                    } else {
                        System.out.println("Неверный номер гаража.");
                    }
                    break;

                case 3:
                    System.out.println("Введите номер гаража для добавления ТС:");
                    garageIndex = scanner.nextInt();
                    if (garageIndex >= 0 && garageIndex < fleet.getGarages().size()) {
                        Garage selectedGarage = fleet.getGarages().get(garageIndex);

                        System.out.println("Выберите тип ТС: 1 - Автомобиль, 2 - Мотоцикл");
                        int vehicleType = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Введите марку:");
                        String brand = scanner.nextLine();
                        System.out.println("Введите модель:");
                        String model = scanner.nextLine();
                        System.out.println("Введите год выпуска:");
                        int year = scanner.nextInt();

                        if (vehicleType == 1) {
                            System.out.println("Введите количество дверей:");
                            int doors = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Введите тип трансмиссии:");
                            String transmission = scanner.nextLine();
                            Car car = new Car(brand, model, year, doors, transmission);
                            selectedGarage.addVehicle(car);
                        } else if (vehicleType == 2) {
                            System.out.println("Введите тип кузова:");
                            String bodyType = scanner.nextLine();
                            System.out.println("Есть ли боковой прицеп?:");
                            boolean hasSidecar = scanner.nextBoolean();
                            Motorcycle motorcycle = new Motorcycle(brand, model, year, bodyType, hasSidecar);
                            selectedGarage.addVehicle(motorcycle);
                        }
                    } else {
                        System.out.println("Неверный номер гаража.");
                    }
                    break;

                case 4:
                    System.out.println("Введите номер гаража для удаления ТС:");
                    garageIndex = scanner.nextInt();
                    if (garageIndex >= 0 && garageIndex < fleet.getGarages().size()) {
                        Garage selectedGarage = fleet.getGarages().get(garageIndex);
                        System.out.println("Введите номер ТС для удаления:");
                        int vehicleIndex = scanner.nextInt();
                        if (vehicleIndex >= 0 && vehicleIndex < selectedGarage.getVehicles().size()) {
                            selectedGarage.removeVehicle(selectedGarage.getVehicles().get(vehicleIndex));
                        } else {
                            System.out.println("Неверный номер ТС.");
                        }
                    } else {
                        System.out.println("Неверный номер гаража.");
                    }
                    break;

                case 5:
                    System.out.println("Список гаражей и транспортных средств:");
                    int garageCount = 0;
                    for (Garage g : fleet.getGarages()) {
                        System.out.println("Гараж " + garageCount + ":");
                        int vehicleCount = 0;
                        for (Vehicle v : g.getVehicles()) {
                            System.out.println("  ТС " + vehicleCount + ": " + v);
                            vehicleCount++;
                        }
                        garageCount++;
                    }
                    break;

                default:
                    System.out.println("Неверный выбор.");
            }
        }

        scanner.close();
    }
}


