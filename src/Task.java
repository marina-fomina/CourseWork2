import java.time.LocalDateTime;

public class Task implements NextTimeDate {
    private final String heading;
    private final String taskDescription;
    private final TaskType taskType;
    private final LocalDateTime localDateTime;
    private final Frequency frequency;
//    private static int counter = 0;
//    private final Integer id;

    public Task(String heading, String taskDescription, TaskType taskType, LocalDateTime localDateTime, Frequency frequency) {
//        this.id = counter++;
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
        this.taskType = taskType;
        this.localDateTime = localDateTime;
        this.frequency = frequency;
    }

    @Override
    public void getNextTimeDate() {

    }

    public String toString() {
        return getHeading() + ". Описание задачи — " + getTaskDescription() + ". " + getTaskType() + " Дата и время — " +
                getLocalDateTime() + ". " + getFrequency() + ".";
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Frequency getFrequency() {
        return frequency;
    }
}
