import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class 𝒐𝒊𝒃𝒔𝒊𝒑_𝒕𝒂𝒔𝒌𝒏𝒐_1 {
    private static ArrayList<ReservationUser> users = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        users.add(new ReservationUser("admin", "admin123", "admin"));
        users.add(new ReservationUser("user", "user123", "user"));

        System.out.println("Welcome to the Online Reservation System!");

        boolean loggedIn = false;
        ReservationUser currentUser = null;

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            currentUser = login(username, password);
            if (currentUser != null) {
                loggedIn = true;
                System.out.println("Login successful! Welcome, " + currentUser.getRole() + "!\n");
            } else {
                System.out.println("Invalid username or password. Please try again.\n");
            }
        }

        while (true) {
            if (currentUser.getRole().equals("admin")) {
                adminMenu(currentUser);
            } else {
                userMenu(currentUser);
            }
        }
    }

    private static ReservationUser login(String username, String password) {
        for (ReservationUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void adminMenu(ReservationUser user) {
        System.out.println("Admin Menu:");
        System.out.println("1. View All Reservations");
        System.out.println("2. Make a Reservation");
        System.out.println("3. Cancel a Reservation");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                viewAllReservations();
                break;
            case 2:
                makeReservation(user);
                break;
            case 3:
                cancelReservation(user);
                break;
            case 4:
                System.out.println("Thank you for using the Online Reservation System.");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.\n");
        }
    }

    private static void userMenu(ReservationUser user) {
        System.out.println("User Menu:");
        System.out.println("1. Make a Reservation");
        System.out.println("2. Cancel a Reservation");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                makeReservation(user);
                break;
            case 2:
                cancelReservation(user);
                break;
            case 3:
                System.out.println("Thank you for using the Online Reservation System.");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.\n");
        }
    }

    private static void viewAllReservations() {
        System.out.println("All Reservations:");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.\n");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
            System.out.println();
        }
    }

    private static void makeReservation(ReservationUser user) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = sc.nextLine();
        System.out.print("Enter class type: ");
        String classType = sc.nextLine();
        System.out.print("Enter date of journey (DD/MM/YYYY): ");
        String dateOfJourney = sc.nextLine();
        System.out.print("Enter origin: ");
        String origin = sc.nextLine();
        System.out.print("Enter destination: ");
        String destination = sc.nextLine();

        Reservation reservation = new Reservation(name, trainNumber, classType, dateOfJourney, origin, destination);
        reservations.add(reservation);
        System.out.println("Reservation successful! Your PNR number is: " + reservation.getPnr() + "\n");
    }

    private static void cancelReservation(ReservationUser user) {
        System.out.print("Enter your PNR number: ");
        String pnr = sc.nextLine();

        Reservation reservationToCancel = null;
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel != null) {
            reservations.remove(reservationToCancel);
            System.out.println("Reservation with PNR " + pnr + " has been successfully canceled.\n");
        } else {
            System.out.println("Reservation with PNR " + pnr + " not found.\n");
        }
    }
}

class ReservationUser {
    private String username;
    private String password;
    private String role;

    public ReservationUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private String dateOfJourney;
    private String origin;
    private String destination;
    private String pnr;

    public Reservation(String name, String trainNumber, String classType, String dateOfJourney, String origin, String destination) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.origin = origin;
        this.destination = destination;
        this.pnr = generatePNR();
    }

    private String generatePNR() {
        Random random = new Random();
        int pnrNumber = 100000 + random.nextInt(900000);
        return String.valueOf(pnrNumber);
    }

    public String getPnr() {
        return pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr +
                ", Name: " + name +
                ", Train Number: " + trainNumber +
                ", Class: " + classType +
                ", Date of Journey: " + dateOfJourney +
                ", From: " + origin +
                ", To: " + destination;
    }
}
