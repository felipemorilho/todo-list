package com.acelerazg.View;

import com.acelerazg.model.TaskModel;
import com.acelerazg.enums.StatusModel;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import static com.acelerazg.controller.TaskController.taskList;

public class TaskView {


    public static TaskModel findTaskByName(String taskName) {
        return taskList.stream()
                .filter(task -> task.getName().equalsIgnoreCase(taskName))
                .findFirst()
                .orElse(null);
    }

    public static void listTaskByCategory() {
        System.out.println("\nListando as Tarefas por categoria: ");
        taskList.sort(Comparator.comparing(TaskModel::getCategory));
        taskList.forEach(System.out::println);
    }

    public static void listTaskByPriority() {
        System.out.println("\nListando tarefas por ordem de prioridade: ");
        taskList.sort(Comparator.comparingInt(TaskModel::getPriority).reversed());
        taskList.forEach(System.out::println);
    }

    public static void listTaskByStatus() {
        System.out.println("\nListando tarefas por status: ");
        taskList.sort(Comparator.comparing(TaskModel::getStatus));
        taskList.forEach(System.out::println);
    }

    public static void listTaskByDate() {
        System.out.println("\nListando Tarefas por data: ");
        Date currentDate = new Date();
        taskList.stream()
                .filter(task -> task.getFinalDate().after(currentDate) || task.getFinalDate().equals(currentDate))
                .sorted(Comparator.comparing(TaskModel::getFinalDate))
                .forEach(task -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println("Tarefa: " + task.getName() + " | Data Limite: " + dateFormat.format(task.getFinalDate()));
                });
    }
    public static void showStats() {
        System.out.println("\nEstatísticas das tarefas: ");
        long todoStats = taskList.stream().filter(task -> task.getStatus() == StatusModel.TODO).count();
        long doingStats = taskList.stream().filter(task -> task.getStatus() == StatusModel.DOING).count();
        long doneStats = taskList.stream().filter(task -> task.getStatus() == StatusModel.DONE).count();

        System.out.println("Tarefas [ToDo]: " + todoStats);
        System.out.println("Tarefas [Doing]: " + doingStats);
        System.out.println("Tarefas [Done]: " + doneStats);
    }
}
