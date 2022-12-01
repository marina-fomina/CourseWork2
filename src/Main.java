import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

        // Planner
        Planner planner = new Planner();
        Map<Integer, Task> tasks = new HashMap<>();
        planner.addTask(cleanTeeth);
        planner.addTask(cookBreakfast);
        planner.addTask(buyTicket);
        planner.addTask(buyGifts);
        planner.addTask(doTraining);
        planner.addTask(payBills);
        planner.addTask(learnGerman);
        planner.addTask(checkEmail);
        planner.addTask(conferenceCall);
        planner.addTask(handInReport);
        System.out.println(planner);
        planner.getTasksPerDay(LocalDate.of(2022, 12, 1));
    }

    private static void printMenu() {

    }
}