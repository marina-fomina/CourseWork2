public enum TaskType {
    PRIVATE ("личная"),
    WORK ("рабочая");
    private final String taskTypeName;

    TaskType(String taskTypeName) {
        if (taskTypeName == null || taskTypeName.isBlank()) {
            throw new NotEnoughData("Данные не заполнены или заполнены некорректно!");
        } else {
            this.taskTypeName = taskTypeName;
        }
    }

    public String toString() {
        return "Тип задачи — " + getTaskTypeName() + ".";
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }
}
