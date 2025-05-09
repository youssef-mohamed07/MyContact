@echo off
if not exist target\classes mkdir target\classes
javac -d target/classes src/main/java/com/mycompany/mycontacts/*.java
java -cp "target/classes;." com.mycompany.mycontacts.GUI 