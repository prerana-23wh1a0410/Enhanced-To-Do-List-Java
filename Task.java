package todolist;

import java.time.LocalDateTime;

enum Priority {
    LOW, MEDIUM, HIGH
}

enum Recurrence {
    NONE, DAILY, WEEKLY, MONTHLY
}

public class Task {

    String description;
    boolean isCompleted;
    Priority priority;
    LocalDateTime deadline;
    Recurrence recurrence;

    public Task(String description, Priority priority,
                LocalDateTime deadline, Recurrence recurrence) {

        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.recurrence = recurrence;
        this.isCompleted = false;
    }

    // Mark task as completed
    public void markCompleted() {
        isCompleted = true;
        updateRecurrence();
    }

    // Check if task is due within 24 hours
    public boolean isDueSoon() {

        return !isCompleted
                && deadline != null
                && deadline.isAfter(LocalDateTime.now())
                && deadline.isBefore(
                    LocalDateTime.now().plusDays(1)
                );
    }

    // Check if task is overdue
    public boolean isOverdue() {

        return !isCompleted
                && deadline != null
                && deadline.isBefore(LocalDateTime.now());
    }

    // Update deadline for recurring tasks
    private void updateRecurrence() {

        if (recurrence == Recurrence.DAILY) {

            deadline = deadline.plusDays(1);

        } else if (recurrence == Recurrence.WEEKLY) {

            deadline = deadline.plusWeeks(1);

        } else if (recurrence == Recurrence.MONTHLY) {

            deadline = deadline.plusMonths(1);
        }
    }

    @Override
    public String toString() {

        String dueSoonNotification =
                isDueSoon() ? " | Due Soon!" : "";

        return (isCompleted ? "[Completed] " : "[Pending] ")
                + "[" + priority + "] "
                + description
                + " (Due: " + deadline + ")"
                + dueSoonNotification;
    }
}