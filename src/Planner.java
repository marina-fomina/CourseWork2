import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Planner {

    private static int counter = 0;
    private final int id;
    private final Map<Integer, Task> tasks = new HashMap<>();

    public Planner() {
        this.id = counter++;
    }

    public void addTask(Task task) {
        if (task != null) {
            int id = counter++;
            this.tasks.put(id, task);
        } else {
            throw new NotEnoughData("Недостаточно данных о задаче!");
        }
    }

    public void getTasksPerDay(LocalDate neededDay) {
        System.out.println("Список задач на день: " + neededDay);
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            if (entry.getValue().getLocalDateTime().getDayOfYear() == neededDay.getDayOfYear()) {
                System.out.println(entry.getValue());
            }
        }
    }

    public void removeTask(String heading) {
        Iterator<Map.Entry<Integer, Task>> taskIterator = tasks.entrySet().iterator();
        while (taskIterator.hasNext()) {
            if (taskIterator.next().getValue().getHeading().equals(heading)) {
                taskIterator.remove();
                break;
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список задач: ").append('\n');
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            stringBuilder.append(entry.getValue()).append('\n');
        }
        return stringBuilder.toString();
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planner planner = (Planner) o;
        return Objects.equals(id, planner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
