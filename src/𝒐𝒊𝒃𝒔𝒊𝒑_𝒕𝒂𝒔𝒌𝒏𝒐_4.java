import java.util.Scanner;

public class 𝒐𝒊𝒃𝒔𝒊𝒑_𝒕𝒂𝒔𝒌𝒏𝒐_4 {
    private static User currentUser;
    private static ExamTimer timer;

    public static void main(String[] args) {
        𝒐𝒊𝒃𝒔𝒊𝒑_𝒕𝒂𝒔𝒌𝒏𝒐_4 examSystem = new 𝒐𝒊𝒃𝒔𝒊𝒑_𝒕𝒂𝒔𝒌𝒏𝒐_4();
        examSystem.start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("***********Welcome to the Online Examination System***************");
        while (true) {
            System.out.println("\n1. Login");
            System.out.println("\n2. Update Profile");
            System.out.println("\n3. Take Exam");
            System.out.println("\n4. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    login(sc);
                    break;
                case 2:
                    if (isLoggedIn()) {
                        updateProfile(sc);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 3:
                    if (isLoggedIn()) {
                        takeExam(sc);
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 4:
                    logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void login(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if ("user".equals(username) && "pass".equals(password)) {
            currentUser = new User(username, password);
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private void updateProfile(Scanner sc) {
        System.out.println("Updating profile for " + currentUser.getUsername());
        System.out.println("1. Update username");
        System.out.println("2. Update password");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new username: ");
                String newUsername = sc.nextLine();
                currentUser.setUsername(newUsername);
                System.out.println("Username updated successfully.");
                break;
            case 2:
                System.out.print("Enter new password: ");
                String newPassword = sc.nextLine();
                currentUser.setPassword(newPassword);
                System.out.println("Password updated successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void takeExam(Scanner sc) {
        System.out.println("\nInstructions:");
        System.out.println("1. The exam consists of 3 multiple-choice questions.");
        System.out.println("2. You have 1 minute to complete the exam.");
        System.out.println("3. To pass you have to give correct answers to all questions.");
        System.out.println("4. The exam will auto-submit when time is up.");

        int examDurationInMinutes = 1;
        timer = new ExamTimer(examDurationInMinutes);
        timer.start();

        String[] questions = {
                "Question 1: Who invented Java Programming?",
                "Question 2: Which component is used to compile, debug and execute the java programs?",
                "Question 3: What is the extension of java code files?"
        };
        String[][] options = {
                {"1) Guido van Rossum", "2) James Gosling", "3) Dennis Ritchie", "4) Bjarne Stroustrup"},
                {"1) JRE", "2) JIT", "3) JDK", "4) JVM"},
                {"1) .js", "2) .txt", "3) .class", "4) .java"}
        };
        int[] correctAnswers = {2, 3, 4};
        int[] userAnswers = new int[questions.length];

        for (int i = 0; i < questions.length; i++) {
            if (timer.isTimeUp()) {
                break;
            }
            System.out.println("\n" + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }
            System.out.print("Your answer (1-4): ");
            userAnswers[i] = sc.nextInt();
            sc.nextLine();
        }

        if (timer.isTimeUp()) {
            System.out.println("Time's up! Auto-submitting the exam...");
        } else {
            System.out.println("Exam completed. Submitting the exam...");
        }
        timer.stopTimer();
        showResult(userAnswers, correctAnswers);
    }

    private void showResult(int[] userAnswers, int[] correctAnswers) {
        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("\nExam Submitted Successfully!");
        System.out.println("Your Score: " + score + "/" + correctAnswers.length);
        if (score == correctAnswers.length) {
            System.out.println("Result: Pass");
        } else {
            System.out.println("Result: Fail");
        }
    }

    private void logout() {
        if (isLoggedIn()) {
            currentUser = null;
            System.out.println("Logged out successfully.");
            System.out.println("Thanks for taking the exam.");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    private boolean isLoggedIn() {
        return currentUser != null;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class ExamTimer extends Thread {
    private int remainingTime;
    private boolean timeUp = false;
    private boolean running = true;

    public ExamTimer(int durationInMinutes) {
        this.remainingTime = durationInMinutes * 60;
    }

    @Override
    public void run() {
        try {
            while (remainingTime > 0 && running) {
                System.out.print("\rTime remaining: " + remainingTime / 60 + " minutes and " + remainingTime % 60 + " seconds.");
                Thread.sleep(1000);
                remainingTime--;
            }
            if (remainingTime == 0) {
                timeUp = true;
                System.out.print("\rTime's up! Auto-submitting the exam...");
            }
        } catch (InterruptedException e) {
            System.out.println("Timer interrupted");
        }
    }

    public boolean isTimeUp() {
        return timeUp;
    }

    public void stopTimer() {
        running = false;
    }
}
