package todolist;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

public class EnhancedTodoList {

    static HashMap<String, User> users = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== TODO LIST =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerUser();
                    break;

                case 2:
                    loginUser();

                    if (currentUser != null) {
                        manageTasks();
                    }

                    break;

                case 3:
                    forgotPassword();
                    break;

                case 4:
                    System.out.println("Exiting application.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        scanner.close();
    }

    // ================= REGISTER =================

    static void registerUser() {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {

            System.out.println("Username already taken.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User newUser = new User(username, password, email);

        users.put(username, newUser);

        System.out.println("User registered successfully!");
    }

    // ================= LOGIN =================

    static void loginUser() {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user != null && user.password.equals(password)) {

            System.out.println("Login successful!");

            currentUser = user;

        } else {

            System.out.println("Invalid username or password.");
        }
    }

    // ================= FORGOT PASSWORD =================

    static void forgotPassword() {

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        User user = users.get(username);

        if (user == null) {

            System.out.println("Username not found.");
            return;
        }

        System.out.print("Enter your registered email: ");
        String email = scanner.nextLine();

        if (user.email.equals(email)) {

            System.out.print("Enter a new password: ");
            String newPassword = scanner.nextLine();

            user.password = newPassword;

            System.out.println(
                "Password has been reset successfully!"
            );

        } else {

            System.out.println("Incorrect email.");
        }
    }

    // ================= TASK MENU =================

    static void manageTasks() {

        int choice;

        do {

            System.out.println("\n===== TASK MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. View Overdue Tasks");
            System.out.println("4. Remove Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Edit Task");
            System.out.println("7. Sort Tasks by Priority");
            System.out.println("8. View Progress");
            System.out.println("9. Logout");

            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // ================= ADD TASK =================

                case 1:

                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();

                    System.out.print(
                        "Enter task priority (LOW, MEDIUM, HIGH): "
                    );

                    Priority priority = Enum.valueOf(
                        Priority.class,
                        scanner.nextLine().toUpperCase()
                    );

                    System.out.print(
                        "Enter task deadline (YYYY-MM-DDTHH:MM): "
                    );

                    LocalDateTime deadline =
                        LocalDateTime.parse(scanner.nextLine());

                    System.out.print(
                        "Enter recurrence (NONE, DAILY, WEEKLY, MONTHLY): "
                    );

                    Recurrence recurrence = Enum.valueOf(
                        Recurrence.class,
                        scanner.nextLine().toUpperCase()
                    );

                    currentUser.addTask(
                        description,
                        priority,
                        deadline,
                        recurrence
                    );

                    System.out.println("Task added!");

                    break;

                // ================= VIEW TASKS =================

                case 2:

                    currentUser.viewTasks();

                    break;

                // ================= VIEW OVERDUE =================

                case 3:

                    currentUser.viewOverdueTasks();

                    break;

                // ================= REMOVE TASK =================

                case 4:

                    System.out.print(
                        "Enter task number to remove: "
                    );

                    int removeIndex = scanner.nextInt() - 1;

                    currentUser.removeTask(removeIndex);

                    break;

                // ================= COMPLETE TASK =================

                case 5:

                    System.out.print(
                        "Enter task number to mark as completed: "
                    );

                    int completeIndex =
                        scanner.nextInt() - 1;

                    currentUser.markTaskCompleted(
                        completeIndex
                    );

                    break;

                // ================= EDIT TASK =================

                case 6:

                    System.out.print(
                        "Enter task number to edit: "
                    );

                    int editIndex =
                        scanner.nextInt() - 1;

                    scanner.nextLine();

                    System.out.print(
                        "Enter new description: "
                    );

                    String newDescription =
                        scanner.nextLine();

                    currentUser.editTask(
                        editIndex,
                        newDescription
                    );

                    break;

                // ================= SORT =================

                case 7:

                    currentUser.sortTasksByPriority();

                    break;

                // ================= PROGRESS =================

                case 8:

                    currentUser.showProgress();

                    break;

                // ================= LOGOUT =================

                case 9:

                    System.out.println("Logging out...");

                    currentUser = null;

                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
    }
}