# Assignment 3: Bridge Pattern
**Name:** Sultanov Abilkaiyr

**Group:** SE-2529

**Topic:** B (Notifications)

**GitHub URL:** https://github.com/flwrhx11-collab/Assignment3-Bridge-Pattern.git

**Base Commit Hash:** e8938accf94a28dd491483ba7d8f0d0a4988efb5

**Submitted Commit Hash:** 9d055fa81f4b88ddd3a775285e10ef1b94457cfc

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
T1 PASS | Reminder + EmailChannel | result=Email envelope: Reminder: Doctor appointment
T2 PASS | Reminder + SmsChannel | result=SMS single-line: Reminder: Buy milk
T3 PASS | UrgentAlert + EmailChannel | result=Email envelope: [URGENT] Server down
T4 PASS | UrgentAlert + SmsChannel | result=SMS single-line: [URGENT] Payment failed
T5 PASS sameObject=true | stateUnchanged=true
before=Email envelope: Reminder: Meeting at 5 | after=SMS single-line: Reminder: Meeting at 5
T6 PASS | Reminder + PushChannel | result=Push envelope: Reminder: Update app
T7 PASS | UrgentAlert + PushChannel | result=Push envelope: [URGENT] Security breach
SUMMARY: 7/7 PASS