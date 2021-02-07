package org.example.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Logging {

    private static File logFile = CreateLogFile.getInstance();

    private Logging() {

    }

    public static void writeFile(String message) {

        try (FileWriter fileWriter = new FileWriter(logFile, true)) {
            fileWriter.write(LocalDate.now() + " "
                    + LocalTime.now().getHour() + ":"
                    + LocalTime.now().getMinute() + ":"
                    + LocalTime.now().getSecond() + " "
                    + message + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
