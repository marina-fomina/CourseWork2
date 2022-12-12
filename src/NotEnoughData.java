public class NotEnoughData extends RuntimeException {
    public NotEnoughData() {
    }

    public NotEnoughData(String message) {
        super(message);
    }
}
