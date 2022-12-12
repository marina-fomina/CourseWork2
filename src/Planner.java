import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Planner {

    private final Map<Integer, Task> tasks = new HashMap<>();

    public Planner() {
    }


    public void addTask(Task task) {
        if (task != null) {
            this.tasks.put(task.getId(), task);
        } else {
            throw new NotEnoughData("Недостаточно данных о задаче!");
        }
    }

    public Collection<Task> getTasksForDate(LocalDate localDate) {
        TreeSet<Task> tasksForDate = new TreeSet<>();
        for (Task task : tasks.values()) {
            if (task.getTaskDateTime().toLocalDate().equals(localDate)) {
                tasksForDate.add(task);
            }
        }
        return tasksForDate;
    }

    public void removeTask(int id) throws TaskNotFoundException {
        if (this.tasks.containsKey(id)) {
            this.tasks.remove(id);
        } else {
            throw new TaskNotFoundException();
        }

    }

    public Collection<Task> getAllTasks() {
        return this.tasks.values();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список задач: ").append('\n');
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            stringBuilder.append(entry.getValue()).append('\n');
        }
        return stringBuilder.toString();
    }
}
