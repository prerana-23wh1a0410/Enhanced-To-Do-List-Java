package todolist;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {

    String username;
    String password;
    String email;

    ArrayList<Task> tasks;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tasks = new ArrayList<>();
    }

    // Add Task
    public void addTask(String description, Priority priority,
                        LocalDateTime deadline, Recurrence recurrence) {

        tasks.add(new Task(description, priority, deadline, recurrence));
    }

    // View Tasks
    public void viewTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {

            System.out.println("Your tasks:");

            for (int i = 0; i < tasks.size(); i++) {

                Task task = tasks.get(i);

                if (task.isOverdue()) {
                    System.out.println(
                        (i + 1) + ". [OVERDUE] " + task
                    );
                } else {
                    System.out.println(
                        (i + 1) + ". " + task
                    );
                }
            }
        }
    }

    // View Overdue Tasks
    public void viewOverdueTasks() {

        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {

            Task task = tasks.get(i);

            if (task.isOverdue()) {

                System.out.println(
                    (i + 1) + ". [OVERDUE] " + task
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No overdue tasks.");
        }
    }

    // Remove Task
    public void removeTask(int index) {

        if (index >= 0 && index < tasks.size()) {

            tasks.remove(index);

            System.out.println("Task removed!");

        } else {

            System.out.println("Invalid task number.");
        }
    }

    // Mark Completed
    public void markTaskCompleted(int index) {

        if (index >= 0 && index < tasks.size()) {

            tasks.get(index).markCompleted();

            System.out.println("Task marked as completed!");

        } else {

            System.out.println("Invalid task number.");
        }
    }

    // Edit Task
    public void editTask(int index, String newDescription) {

        if (index >= 0 && index < tasks.size()) {

            tasks.get(index).description = newDescription;

            System.out.println("Task updated!");

        } else {

            System.out.println("Invalid task number.");
        }
    }

    // Sort by Priority
    public void sortTasksByPriority() {

        tasks.sort((a, b) ->
            b.priority.compareTo(a.priority)
        );

        System.out.println("Tasks sorted by priority.");
    }

    // Show Progress
    public void showProgress() {

        int completed = 0;

        for (Task task : tasks) {

            if (task.isCompleted) {
                completed++;
            }
        }

        System.out.println(
            "Completed Tasks: "
            + completed
            + "/"
            + tasks.size()
        );
    }
}