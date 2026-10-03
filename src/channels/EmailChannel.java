package channels;

public class EmailChannel implements Channel {
    @Override
    public String deliver(String message) {
        return "Email envelope: " + message;
    }
}