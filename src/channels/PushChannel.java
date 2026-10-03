package channels;

public class PushChannel implements Channel {
    @Override
    public String deliver(String message) {
        return "Push envelope: " + message;
    }
}