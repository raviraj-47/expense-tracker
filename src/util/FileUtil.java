package util;

import java.io.File;

public class FileUtil {

    public static void ensureLogFileExists(String filePath) {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    System.err.println("Warning: Failed to create directories for " + parent.getAbsolutePath());
                }
            }

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.err.println("Warning: Failed to create file " + file.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            System.err.println("Error while ensuring log file exists: " + e.getMessage());
        }
    }
}
