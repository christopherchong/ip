package haru.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import haru.HaruException;
import haru.task.Deadline;
import haru.task.Event;
import haru.task.Task;
import haru.task.TaskList;
import haru.task.Todo;
import haru.util.DateTimeUtil;

/**
 * Performs file operations on the task file.
 *
 * <p>
 * The {@code Storage} class is responsible for file operations such as verifying the
 * existence of the task file, loading from the file, and saving to the file.
 * </p>
 */
public class Storage {
    /** Path of the task list file. */
    private final Path filePath;

    /**
     * Creates a new {@code Storage} with the specified file path.
     *
     * @param filePath The file path.
     */
    public Storage(Path filePath) {
        assert filePath != null : "Storage file path should not be null";
        this.filePath = filePath;
    }

    /**
     * Checks if the task file exists. If not, it is created.
     *
     * @throws IOException If an I/O error occurs while accessing storage.
     */
    public void verifyTaskFile() throws IOException {
        Path folderPath = filePath.getParent();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Loads tasks from the task file into a {@code TaskList} object.
     *
     * @return a {@code TaskList} object with tasks from the task file.
     * @throws HaruException If the task file is corrupted.
     * @throws IOException If an I/O error occurs while accessing storage.
     */
    public ArrayList<Task> loadTaskList() throws HaruException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath.toString());
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            try {
                String task = sc.nextLine();
                String[] arguments = task.split("\\|");
                char taskType = task.charAt(0);
                boolean isDone = arguments[1].equals("1");
                String description = arguments[2];

                switch (taskType) {
                case 'T':
                    tasks.add(new Todo(isDone, description));
                    break;
                case 'D':
                    LocalDateTime by = DateTimeUtil.parseStorage(arguments[3]);
                    tasks.add(new Deadline(isDone, description, by));
                    break;
                case 'E':
                    LocalDateTime from = DateTimeUtil.parseStorage(arguments[3]);
                    LocalDateTime to = DateTimeUtil.parseStorage(arguments[4]);
                    tasks.add(new Event(isDone, description, from, to));
                    break;
                default:
                    throw new HaruException.CorruptedFileException();
                }
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                throw new HaruException.CorruptedFileException();
            }
        }
        return tasks;
    }

    /**
     * Updates the task file using the current {@link TaskList}.
     *
     * @param tasks The {@link TaskList} used to update the task file.
     * @throws IOException If an I/O error occurs while accessing storage.
     */
    public void updateTaskList(TaskList tasks) throws IOException {
        FileWriter f = new FileWriter(filePath.toString());
        for (int i = 0; i < tasks.size(); i++) {
            f.write(tasks.get(i).getTaskInfoForFile() + System.lineSeparator());
        }
        f.close();
    }
}
