import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {

    private static final Planner PLANNER = new Planner();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public static void main(String[] args) {

        Task cleanTeeth = new Task("Почистить зубы", "пойти в ванную и почистить зубы", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 1, 8, 0), Frequency.EVERYDAY);
        Task cookBreakfast = new Task("Приготовить завтрак", "прийти на кухню, достать продукты " +
                "из холодильника и приготовить завтрак", TaskType.PRIVATE, LocalDateTime.of(2022, 12, 1, 8, 15),
                Frequency.EVERYDAY);
        Task buyTicket = new Task("Купить билет в Москву", "выбрать и купить билет на поезд в Москву на конец декабря",
                TaskType.PRIVATE, LocalDateTime.of(2022, 12, 4, 11, 0), Frequency.SINGLE);
        Task buyGifts = new Task("Купить подарки на Новый год", "придумать и купить родным и друзьям подарки на Новый год",
                TaskType.PRIVATE, LocalDateTime.of(2022, 12, 15, 12, 0), Frequency.YEARLY);
        Task doTraining = new Task("Тренировка", "сходить на тренировку в спортзале", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 6, 18, 0), Frequency.WEEKLY);
        Task payBills = new Task("Оплатить услуги ЖКХ", "заплатить за газ и электроэнергию", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 1, 15, 0), Frequency.MONTHLY);
        Task learnGerman = new Task("Занятие по немецкому", "занятие по немецкому с репетитором в Zoom", TaskType.PRIVATE,
                LocalDateTime.of(2022, 12, 2, 19, 0), Frequency.WEEKLY);
        Task checkEmail = new Task("Проверить почту", "разобрать письма на рабочей почте", TaskType.WORK,
                LocalDateTime.of(2022, 12, 1, 9, 15), Frequency.EVERYDAY);
        Task conferenceCall = new Task("Созвон по работе", "созвониться с коллегами и обсудить план работы на следующий год",
                TaskType.WORK, LocalDateTime.of(2022, 12, 7, 10, 30), Frequency.SINGLE);
        Task handInReport = new Task("Отчёт", "сделать и сдать отчёт за месяц", TaskType.WORK,
                LocalDateTime.of(2022, 12, 28, 14, 0), Frequency.MONTHLY);

        PLANNER.addTask(cleanTeeth);
        PLANNER.addTask(cookBreakfast);
        PLANNER.addTask(buyTicket);
        PLANNER.addTask(buyGifts);
        PLANNER.addTask(doTraining);
        PLANNER.addTask(payBills);
        PLANNER.addTask(learnGerman);
        PLANNER.addTask(checkEmail);
        PLANNER.addTask(conferenceCall);
        PLANNER.addTask(handInReport);


        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            getTasksPerDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

    }


    private static void printMenu() {
        System.out.println("" +
                "1. Добавить задачу \n" +
                "2. Удалить задачу \n" +
                "3. Получить задачи на указанный день \n" +
                "0. Выход"
        );
    }

    // Метод для добавления задачи
    private static void inputTask(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Введите название задачи: ");
        String heading = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        System.out.println("Введите тип задачи: \n" +
                "личная или рабочая");
        System.out.println("Вы ввели: ");
        TaskType taskTypeName = checkTaskType(scanner.next());
        System.out.println("Введите тип повторяемости задачи: \n" +
                "однократная \n" + "ежедневная \n" + "еженедельная \n" + "ежемесячная \n" + "ежегодная");
        System.out.println("Вы ввели: ");
        Frequency frequencyType = checkFrequency(scanner.next());
        scanner.nextLine();
        System.out.println("Введите дату выполнения задачи в формате дд.мм.гггг: ");
        String taskDate = scanner.nextLine();
        System.out.println("Введите время выполнения задачи в формате чч:мм: ");
        String taskTime = scanner.nextLine();
        LocalDateTime taskDateTime = createDateAndTime(taskDate, taskTime);
        Task task = new Task(heading, taskDescription, taskTypeName, taskDateTime, frequencyType);
        PLANNER.addTask(task);
        System.out.println("Вы создали задачу: ");
        System.out.println(task.toString());
        System.out.println("Для возврата в меню нажмите Enter");
        scanner.nextLine();
        scanner.nextLine();
    }

    // Метод для удаления задачи
    private static void removeTask(Scanner scanner) {
        System.out.println("Все задачи: ");
        for (Task task : PLANNER.getAllTasks()) {
            System.out.println(task.getId() + " " + task.toString());
        }
        while (true) {
            try {
                scanner.nextLine();
                System.out.println("Введите id задачи, которую хотите удалить: ");
                int receivedId = scanner.nextInt();
                PLANNER.removeTask(receivedId);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Введен неверный id задачи!");
            } catch (TaskNotFoundException e) {
                System.out.println("Нет задачи с таким id.");
            }
        }
    }

    // Метод для получения задачи на указанный день
    private static void getTasksPerDay(Scanner scanner) {
        if (PLANNER.getAllTasks().isEmpty()) {
            System.out.println("В ежедневнике пока нет задач.");
        }
        LocalDate localDate = checkDate(scanner);
        Collection<Task> tasksForDate = PLANNER.getTasksForDate(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMATTER) + ":");
        for (Task task : tasksForDate) {
            System.out.println(task.getHeading() + ". " + "Время — " + task.getTaskDateTime().toLocalTime() + ". Описание задачи — " +
                    task.getTaskDescription() + ". " + task.getTaskType() + " " + task.getFrequency() + ".");
            }
    }

    // Метод создания даты и времени из введенных пользователем данных
    private static LocalDateTime createDateAndTime(String taskDate, String taskTime) {
        LocalDateTime taskDateAndTime = LocalDateTime.of(LocalDate.parse (taskDate, DATE_FORMATTER),
                LocalTime.parse (taskTime, TIME_FORMATTER));
        return taskDateAndTime;
    }

    // Метод проверки типа введенной задачи
    private static TaskType checkTaskType(String taskTypeName) {
        if (taskTypeName.equals("личная")) {
            return TaskType.PRIVATE;
        }
        if (taskTypeName.equals("рабочая")) {
            return TaskType.WORK;
        }
        throw new IllegalArgumentException("Тип задачи введён неверно!");
    }

    // Метод проверки повторяемости введенной задачи
    private static Frequency checkFrequency(String frequencyType) {
        if (frequencyType.equals("однократная")) {
            return Frequency.SINGLE;
        }
        if (frequencyType.equals("ежедневная")) {
            return Frequency.EVERYDAY;
        }
        if (frequencyType.equals("еженедельная")) {
            return Frequency.WEEKLY;
        }
        if (frequencyType.equals("ежемесячная")) {
            return Frequency.MONTHLY;
        }
        if (frequencyType.equals("ежегодная")) {
            return Frequency.YEARLY;
        }
        throw new IllegalArgumentException("Тип повторяемости задачи введён неверно!");
    }

    // Метод проверки правильности введения даты
    private static LocalDate checkDate(Scanner scanner) {
        while (true) {
            try {
                scanner.nextLine();
                System.out.println("Введите дату в формате дд.мм.гггг: ");
                String receivedDate = scanner.nextLine();
                return LocalDate.parse(receivedDate, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Вы ввели дату в неверном формате!" );
            }
        }
    }
}