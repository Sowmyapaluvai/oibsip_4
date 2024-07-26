import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class onlineexam {
    private static Map<String, String> users = new HashMap<>();
    private static String presentUser = null;
    private static String userPassword = null;
    private static long sessionEndTime = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (presentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> register(scanner);
                    case 2 -> login(scanner);
                    case 3 -> {
                        System.out.println("Exiting.....");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("1. Update Profile");
                System.out.println("2. Change Password");
                System.out.println("3. Answer MCQ");
                System.out.println("4. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> updateProfile(scanner);
                    case 2 -> changePassword(scanner);
                    case 3 -> answerMCQ(scanner);
                    case 4 -> logout();
                    default -> System.out.println("Invalid choice.");
                }
            }
        }
    }

  

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            presentUser = username;
            userPassword = password;
            sessionEndTime = System.currentTimeMillis() + 60000; 
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    private static void register(Scanner scanner) {
        System.out.print("Enter a username to register: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
        } else {
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();
            users.put(username, password);
            System.out.println("Registration successful!");
        }
    }
  

    private static void changePassword(Scanner scanner) {
        if (valid()) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            users.put(presentUser, newPassword);
            userPassword = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            isExpired();
        }
    }
    private static void updateProfile(Scanner scanner) {
        if (valid()) {
            System.out.print("Enter new profile info: ");
            String newInfo = scanner.nextLine();
            
            System.out.println("Profile updated with info: " + newInfo);
        } else {
            isExpired();
        }
    }

    private static void answerMCQ(Scanner scanner) {
        int count=0;
        System.out.println("YOu have 5 minutes to complete the exam");
        System.out.println("All the best!");
        if (valid()) {
            System.out.println("Question: What is the capital of France?");
            System.out.println("1. Berlin");
            System.out.println("2. Madrid");
            System.out.println("3. Paris");
            System.out.println("4. Rome");
            int answer = scanner.nextInt();
            scanner.nextLine(); 

            if (answer == 3) {
              count++;
            } 
            System.out.println("Question: Full form of SQL?");
            System.out.println("1. Syntax Query Language");
            System.out.println("2. Structured Query Language");
            System.out.println("3. System Quote Language");
            System.out.println("4. System Query Language");
            int a1= scanner.nextInt();
            scanner.nextLine(); 

            if (a1 == 2) {
               count++;
            } 
            System.out.println("Question: Which package contains the Random class?");
            System.out.println("1. java.util package");
            System.out.println("2. java.lang package");
            System.out.println("3. java.awt package");
            System.out.println("4. java.io package");
            int a2 = scanner.nextInt();
            scanner.nextLine(); 

            if (a2 == 1) {
               count++;
            } 
            System.out.println("Question: which of the following is a reserved keyword in java?");
            System.out.println("1. object");
            System.out.println("2. strictfp");
            System.out.println("3. main");
            System.out.println("4. system");
            int a3 = scanner.nextInt();
            scanner.nextLine();

            if (a3 == 2) {
                count++;
            }
            System.out.println("Question: Which of the following is a mutable class in java?");
            System.out.println("1. java.lang.String");
            System.out.println("2. java.lang.Byte");
            System.out.println("3. java.lang.Short");
            System.out.println("4. java.lang.StringBuilder");
            int a4= scanner.nextInt();
            scanner.nextLine(); 

            if (a4 == 4) {
               count++;
            } 

        } else {
            isExpired();
        }
        System.out.println("Would you like to submit?");
        System.out.println("1.Yes");
        System.out.println("2.No");
        int a5= scanner.nextInt();
            scanner.nextLine(); 
            if(a5==1){
                System.out.println("your score is "+count+" out of 5");
            }
            else{
                System.out.println("Recheck the answers");
            }
         }

    private static void logout() {
        presentUser = null;
        userPassword = null;
        sessionEndTime = 0;
        System.out.println("Logged out successfully.");
    }

    private static boolean valid() {
        return System.currentTimeMillis() < sessionEndTime;
    }

    private static void isExpired() {
        System.out.println("Session expired.. :(  Logging out...");
        logout();
    }
}
