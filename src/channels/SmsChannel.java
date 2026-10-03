package channels;

public class SmsChannel implements Channel {
    @Override
    public String deliver(String message) {
        return "SMS single-line: " + message;
    }
}