package ip.src.main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate time;

    public Deadline(String description, String time) throws SnowmanException {
        super(description);
        try {
            this.time = LocalDate.parse(time); // expects yyyy-MM-dd
        } catch (Exception e) {
            throw new SnowmanException("Error: Please use the date format yyyy-MM-dd");
        }
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + time;
    }
}
