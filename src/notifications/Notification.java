package notifications;

import channels.Channel;

public abstract class Notification {
    protected String id;
    protected String message;
    protected Channel channel;

    public Notification(String id, String message, Channel channel) {
        this.id = id;
        this.message = message;
        this.channel = channel;
    }

    public void setImplementation(Channel channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public abstract String execute();
}