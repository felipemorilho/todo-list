package com.acelerazg;

import com.acelerazg.View.TaskView;
import com.acelerazg.controller.TaskController;

import java.util.Scanner;

import static com.acelerazg.controller.FileController.loadFile;
import static com.acelerazg.controller.FileController.saveToFile;

public class Main {

    public static void main(String[] args) {
        loadFile();
        Scanner sc = new Scanner(System.in);
        while (true) {
            menu();
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    TaskController.addTask();
                    break;
                case 2:
                    TaskController.updateTask();
                    break;
                case 3:
                    TaskView.listTaskByPriority();
                    break;
                case 4:
                    TaskView.listTaskByStatus();
                    break;
                case 5:
                    TaskView.listTaskByDate();
                    break;
                case 6:
                    TaskView.listTaskByCategory();
                    break;
                case 7:
                    TaskView.showStats();
                    break;
                case 0:

                    System.out.println("\n---- Finalizando o app. Até a próxima!!! ----------");
                    System.exit(0);
                default:
                    System.out.println("\nOpção inválida. Por favor, selecione um opção válida!!!!");
            }
            saveToFile();
        }
    }

    public static void menu() {
        System.out.println("\u001B[34m******** BEM VINDO!!! ********" +
                "\n\nEsse é o TODO-List, o app que te ajuda a organizar suas tarefas." +
                "\n ");
        System.out.println("1 - Adicionar nova tarefa;");
        System.out.println("2 - Atualizar Tarefa;");
        System.out.println("3 - Filtrar tarefas por prioridade;");
        System.out.println("4 - Filtrar tarefas por status;");
        System.out.println("5 - Filtrar tarefas por data;");
        System.out.println("6 - Filtrar tarefas por categoria;");
        System.out.println("7 - Mostrar estatísticas dos status das tarefas;");
        System.out.println("0 - Sair.");
        System.out.print("\nSelecione a opção desejada: ");
    }
}