package org.example.logging;

import java.io.File;

public class CreateLogFile {

    private CreateLogFile() {
    }

    private static class CreateFile {
        private static final File logFile = new File("file.log");
    }

    public static File getInstance() {
        return CreateFile.logFile;
    }

}
