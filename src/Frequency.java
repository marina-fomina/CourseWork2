public enum Frequency {
    SINGLE ("однократная"),
    EVERYDAY ("ежедневная"),
    WEEKLY ("еженедельная"),
    MONTHLY ("ежемесячная"),
    YEARLY ("ежегодная");
    private final String frequencyType;

    Frequency(String frequencyType) {
        if (frequencyType == null || frequencyType.isBlank()) {
            throw new NotEnoughData("Данные не заполнены или заполнены некорректно!");
        } else {
            this.frequencyType = frequencyType;
        }
    }

//    public boolean appearsInDate(LocalDate taskDate, LocalDate receivedDate) {
//        switch (this) {
//            case SINGLE:
//        }
//    }

    public String toString() {
        return "Повторяемость задачи — " + getFrequencyType();
    }

    public String getFrequencyType() {
        return frequencyType;
    }
}
