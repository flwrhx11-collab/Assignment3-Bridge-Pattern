import channels.Channel;
import channels.EmailChannel;
import channels.SmsChannel;
import channels.PushChannel;
import notifications.Notification;
import notifications.Reminder;
import notifications.UrgentAlert;

public class Main {
    static int passed = 0;
    static int total = 7;

    public static void main(String[] args) {
        Channel email = new EmailChannel();
        Channel sms = new SmsChannel();
        Channel push = new PushChannel();

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

        Notification t6App = new Reminder("ID-6", "Update app", push);
        check("T6", t6App, "Reminder", "PushChannel", "Push envelope: Reminder: Update app");

        Notification t7App = new UrgentAlert("ID-7", "Security breach", push);
        check("T7", t7App, "UrgentAlert", "PushChannel", "Push envelope: [URGENT] Security breach");

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
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