import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final Path folderPath = Paths.get("src","data");
    private static final Path filePath = Paths.get("src","data", "haru.txt");

    public static void verifyTaskFile() throws IOException {
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
            Files.createFile(filePath);
        } else if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public static ArrayList<Task> loadTaskList() throws HaruException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath.toString());
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            try {
                String task = sc.nextLine();
                String[] arguments = task.split("\\|");
                boolean isDone = arguments[1].equals("1");
                String description = arguments[2];

                switch (task.charAt(0)) {
                case 'T':
                    tasks.add(new Todo(isDone, description));
                    break;

                case 'D':
                    DateTimeFormatter deadlineFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime by = LocalDateTime.parse(arguments[3], deadlineFormatter);
                    tasks.add(new Deadline(isDone, description, by));
                    break;

                case 'E':
                    DateTimeFormatter eventFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime from = LocalDateTime.parse(arguments[3], eventFormatter);
                    LocalDateTime to = LocalDateTime.parse(arguments[4], eventFormatter);
                    tasks.add(new Event(isDone, description, from, to));
                    break;
                }
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                throw new HaruException.CorruptedFileException();
            }
        }
        return tasks;
    }

    public static void updateTaskList(ArrayList<Task> tasks) throws IOException {
        FileWriter f = new FileWriter(filePath.toString());
        for (Task task : tasks) {
            f.write(task.getTaskInfoForFile() + System.lineSeparator());
        }
        f.close();
    }
}
