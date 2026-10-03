import channels.Channel;
import channels.EmailChannel;
import channels.SmsChannel;
import notifications.Notification;
import notifications.Reminder;
import notifications.UrgentAlert;

public class Main {
    static int passed = 0;
    static int total = 5;

    public static void main(String[] args) {
        Channel email = new EmailChannel();
        Channel sms = new SmsChannel();

        Notification t1App = new Reminder("ID-1", "Doctor appointment", email);
        check("T1", t1App, "Reminder", "EmailChannel", "Email envelope: Reminder: Doctor appointment");

        Notification t2App = new Reminder("ID-2", "Buy milk", sms);
        check("T2", t2App, "Reminder", "SmsChannel", "SMS single-line: Reminder: Buy milk");

        Notification t3App = new UrgentAlert("ID-3", "Server down", email);
        check("T3", t3App, "UrgentAlert", "EmailChannel", "Email envelope: [URGENT] Server down");

        Notification t4App = new UrgentAlert("ID-4", "Payment failed", sms);
        check("T4", t4App, "UrgentAlert", "SmsChannel", "SMS single-line: [URGENT] Payment failed");

        Notification t5App = new Reminder("ID-5", "Meeting at 5", email);
        String before = t5App.execute();

        Notification originalRef = t5App;
        String originalId = t5App.getId();
        String originalMessage = t5App.getMessage();

        t5App.setImplementation(sms);
        String after = t5App.execute();

        boolean sameObject = (t5App == originalRef);
        boolean stateUnchanged = t5App.getId().equals(originalId) && t5App.getMessage().equals(originalMessage);
        boolean correctBefore = before.equals("Email envelope: Reminder: Meeting at 5");
        boolean correctAfter = after.equals("SMS single-line: Reminder: Meeting at 5");

        if (sameObject && stateUnchanged && correctBefore && correctAfter) {
            System.out.println("T5 PASS sameObject=true | stateUnchanged=true");
            System.out.println("before=" + before + " | after=" + after);
            passed++;
        } else {
            System.out.println("T5 FAIL");
        }

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS (Base Version)");
    }

    private static void check(String id, Notification app, String aName, String iName, String expected) {
        String actual = app.execute();
        if (actual.equals(expected)) {
            System.out.println(id + " PASS | " + aName + " + " + iName + " | result=" + actual);
            passed++;
        } else {
            System.out.println(id + " FAIL | " + aName + " + " + iName + " | expected=" + expected + " | actual=" + actual);
        }
    }
}