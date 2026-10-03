# Assignment 3: Bridge Pattern
**Name:** Sultanov Abilkaiyr

**Group:** SE-2529

**Topic:** B (Notifications)

**GitHub URL:** https://github.com/flwrhx11-collab/Assignment3-Bridge-Pattern.git

**Base Commit Hash:** e8938accf94a28dd491483ba7d8f0d0a4988efb5

## Role Map
| Role | Class | Source Path |
|---|---|---|
| Abstraction | Notification | src/notifications/Notification.java |
| A1 | Reminder | src/notifications/Reminder.java |
| A2 | UrgentAlert | src/notifications/UrgentAlert.java |
| Implementor | Channel | src/channels/Channel.java |
| I1 | EmailChannel | src/channels/EmailChannel.java |
| I2 | SmsChannel | src/channels/SmsChannel.java |
| I3 | PushChannel | src/channels/PushChannel.java |
| Client | Main | src/Main.java |

**Notes:**
* Bridge field: `channel` interface reference in `Notification.java`
* Bridge operation: `execute()` in `Notification.java` and subclasses
* Runtime switch: `setImplementation(...)` method in `Notification.java`, demonstrated in T5 check

## Build and Run Commands
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main

## Expected Outcomes
(Скопируй сюда весь текст из твоего файла demo-output.txt)