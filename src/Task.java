import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements NextTimeDate, Comparable<Task> {
    private final String heading;
    private final String taskDescription;
    private final TaskType taskType;
    private final LocalDateTime taskDateTime;
    private final Frequency frequency;
    private static int counter = 0;
    private final Integer id;

    private static final DateTimeFormatter DATE_AND_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Task(String heading, String taskDescription, TaskType taskType, LocalDateTime taskDateTime, Frequency frequency) {
        this.id = counter++;
        if (heading == null || heading.isBlank()) {
            throw new NotEnoughData("Данные не заполнены или заполнены некорректно!");
        } else {
            this.heading = heading;
        }
        if (taskDescription == null || taskDescription.isBlank()) {
            throw new NotEnoughData("Данные не заполнены или заполнены некорректно!");
        } else {
            this.taskDescription = taskDescription;
        }
        if (taskType == null) {
            throw new NotEnoughData("Тип задачи не выбран!");
        } else {
            this.taskType = taskType;
        }
        if (frequency == null) {
            throw new NotEnoughData("Тип повторяемости задачи не выбран!");
        } else {
            this.frequency = frequency;
        }
        if (taskDateTime == null) {
            throw new NotEnoughData("Вы не ввели дату и время выполнения задачи!");
        } else {
            this.taskDateTime = taskDateTime;
        }
    }
    // Метод получения следующей даты и времени выполнения задачи
    @Override
    public String getNextTimeAndDate() {
        LocalDateTime nextTimeAndDate = null;
        if (frequency.getFrequencyType().equals("однократная")) {
            System.out.println("Задача не повторяется");
        }
        if (frequency.getFrequencyType().equals("ежедневная")) {
            nextTimeAndDate = LocalDateTime.of(getTaskDateTime().toLocalDate().plusDays(1), getTaskDateTime().toLocalTime());
        }
        if (frequency.getFrequencyType().equals("еженедельная")) {
            nextTimeAndDate = LocalDateTime.of(getTaskDateTime().toLocalDate().plusWeeks(1), getTaskDateTime().toLocalTime());
        }
        if (frequency.getFrequencyType().equals("ежемесячная")) {
            nextTimeAndDate = LocalDateTime.of(getTaskDateTime().toLocalDate().plusMonths(1), getTaskDateTime().toLocalTime());
        }
        if (frequency.getFrequencyType().equals("ежегодная")) {
            nextTimeAndDate = LocalDateTime.of(getTaskDateTime().toLocalDate().plusYears(1), getTaskDateTime().toLocalTime());
        }
        assert nextTimeAndDate != null;
        return nextTimeAndDate.format(DATE_AND_TIME_FORMATTER);
    }



    public String toString() {
        return getHeading() + ". Описание задачи — " + getTaskDescription() + ". " + getTaskType() + " Дата и время — " +
                getTaskDateTime().format(DATE_AND_TIME_FORMATTER) + ". " + getFrequency() + ".";
    }

    public String getHeading() {
        return heading;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask == null) {
            return 1;
        }
        return this.getTaskDateTime().toLocalTime().compareTo(otherTask.getTaskDateTime().toLocalTime());
    }
}
