package com.acelerazg.controller;

import com.acelerazg.Task;
import com.acelerazg.View.TaskView;
import com.acelerazg.enums.CategoryModel;
import com.acelerazg.enums.StatusModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskController {

    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner scNum = new Scanner(System.in);
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void addTask() {
        System.out.println("\n\u001B[32mVamos adicionar uma tarefa. Complete os dados abaixo:");
        sc.nextLine();

        System.out.print("Nome da tarefa: ");
        String name = sc.nextLine();

        System.out.print("Descrição:  ");
        String description = sc.nextLine();

        System.out.print("Data Limite:  ");
        Date finalDate = parseDateInput(sc.nextLine());

        int priority = 0;
        try {
            do {
                System.out.print("Prioridade (1 à 5):  ");
                priority = scNum.nextInt();
            } while (priority < 1 || priority > 5);
        } catch (InputMismatchException e) {
            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
        }
        sc.nextLine();

        int option;
        CategoryModel category = null;
        try {
            do {
                System.out.print("Categoria:  " +
                        "\n1 - Casa;" +
                        "\n2 - Estudos;" +
                        "\n3 - Trabalho;" +
                        "\n4 - Lazer");
                System.out.print("\nSelecione a opção desejada: ");
                option = scNum.nextInt();
                switch (option) {
                    case 1:
                        category = CategoryModel.CASA;
                        break;
                    case 2:
                        category = CategoryModel.ESTUDOS;
                        break;
                    case 3:
                        category = CategoryModel.TRABALHO;
                        break;
                    case 4:
                        category = CategoryModel.LAZER;
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                }
            } while (option != 1 && option != 2 && option != 3 && option != 4);
        } catch (InputMismatchException e) {
            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
        }

        int optionSt;
        StatusModel status = null;
        try {
            do {
                System.out.print("Status:  " +
                        "\n1 - ToDo;" +
                        "\n2 - Doing;" +
                        "\n3 - Done;");
                System.out.print("\nSelecione a opção desejada: ");
                optionSt = scNum.nextInt();
                switch (optionSt) {
                    case 1:
                        status = StatusModel.TODO;
                        break;
                    case 2:
                        status = StatusModel.DOING;
                        break;
                    case 3:
                        status = StatusModel.DONE;
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                }
            } while (optionSt != 1 && optionSt != 2 && optionSt != 3);
        } catch (InputMismatchException e) {
            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
        }

        if (name != null && description != null && finalDate != null && priority != 0 && category != null && status != null) {
            Task newTask = new Task(name, description, finalDate, priority, category, status);
            taskList.add(newTask);
            System.out.println("\n\n\u001B[33mTarefa foi adicionada com sucesso!!!!\n");
        } else {
            System.out.println("Tarefa não salva. Verifique os campos.");
        }

    }

    public static void updateTask() {
        taskList.forEach(task -> System.out.println("Nome da Tarefa: " + task.getName()));
        System.out.print("\n\n\u001B[32mAtualizar qual tarefa: ");
        String updateTask = sc.nextLine();

        Task oldTask = TaskView.findTaskByName(updateTask);
        if(oldTask == null) {
            System.out.println("Tarefa inexistente!");
            return;
        }

       int option;
       do {
           System.out.println("Digite a opção que deseja atualizar: " +
                   "\n1 - Nome;" +
                   "\n2 - Descrição;" +
                   "\n3 - Data Limite;" +
                   "\n4 - Prioridade;" +
                   "\n5 - Categoria;" +
                   "\n6 - Status;" +
                   "\n0 - Sair.");
                option = scNum.nextInt();
                switch (option) {
                    case 1:
                        System.out.print("\nDigite o novo nome da tarefa: ");
                        sc.nextLine();
                        try {
                            oldTask.setName(sc.nextLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
                        }
                        break;
                    case 2:
                        System.out.print("\nDigite a nova descrição da tarefa: ");
                        try {
                            oldTask.setDescription(sc.nextLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
                        }
                        break;
                    case 3:
                        System.out.print("\nDigite a nova data limite da tarefa: ");
                        try {
                            oldTask.setFinalDate(parseDateInput(sc.nextLine()));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
                        }
                        break;
                    case 4:
                        System.out.print("\nDigite a nova prioridade da tarefa: ");
                        try {
                            oldTask.setPriority(scNum.nextInt());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
                        }
                        break;
                    case 5:
                        System.out.print("\nDigite a nova categoria da tarefa: " +
                                CategoryModel.CASA  + " | " + CategoryModel.ESTUDOS
                                + " | " + CategoryModel.TRABALHO + " | "  + CategoryModel.LAZER
                        + ": ");
                        String newCategory = sc.nextLine();
                        try {
                            oldTask.setCategory(CategoryModel.valueOf(newCategory.toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
                        }
                        break;
                    case 6:
                        System.out.print("\nDigite o novo status da tarefa: " + StatusModel.TODO + " | "
                        + StatusModel.DOING + " | " + StatusModel.DONE + ": ");
                        String newStatus = sc.nextLine();
                        try {
                            oldTask.setStatus(StatusModel.valueOf(newStatus.toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada não Válida. Certifique-se de escrever umas das opções corretamente!");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida! Digite novamente a opção desejada.");
                }
       }while (option != 0);
        System.out.println("\n\n\u001B[33mTarefa atualizada!");
    }

    public static Date parseDateInput(String inputDate) {
        try {
            return DATE_FORMAT.parse(inputDate);
        } catch (ParseException e) {
            System.out.println("Formato Inválido. Formato esperado: dd/MM/yyyy");
            return null;
        }
    }
}
