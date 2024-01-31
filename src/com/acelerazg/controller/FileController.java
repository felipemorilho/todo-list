package com.acelerazg.controller;

import com.acelerazg.Task;
import com.acelerazg.enums.CategoryModel;
import com.acelerazg.enums.StatusModel;

import java.io.*;
import java.util.Date;

import static com.acelerazg.controller.TaskController.*;

public class FileController {

    private static final String FILE_PATH = "src/resources/data/task.txt";
    public static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task: taskList) {
                String dateFormatted = DATE_FORMAT.format(task.getFinalDate());
                writer.println(taskToString(task, dateFormatted));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo");
        }
    }

    public static void loadFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = stringToTask(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Nenhuma tarefa encontrada. Gerando novo arquivo");
        }
    }

    private static Task stringToTask(String line) {
        String[] parts = line.split("\\|");
        String name = parts[0];
        String description = parts[1];
        Date finalDate = parseDateInput(parts[2]);
        int priority = Integer.parseInt(parts[3]);
        CategoryModel category = CategoryModel.valueOf(parts[4]);
        StatusModel status = StatusModel.valueOf(parts[5]);

        return new Task(name, description, finalDate, priority, category, status);
    }

    private static String taskToString(Task task, String dateFormatted) {
        return task.getName() + "|" +
                task.getDescription() + "|" +
                dateFormatted + "|" +
                task.getPriority() + "|" +
                task.getCategory() + "|" +
                task.getStatus();
    }
}
