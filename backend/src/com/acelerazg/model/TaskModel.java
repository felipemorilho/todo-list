package com.acelerazg.model;

import com.acelerazg.enums.CategoryModel;
import com.acelerazg.enums.StatusModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskModel {
    private String name;
    private String description;
    private Date finalDate;
    private int priority;
    private CategoryModel category;
    private StatusModel status;

    public TaskModel(String name, String description, Date finalDate, int priority, CategoryModel category, StatusModel status) {
        this.name = name;
        this.description = description;
        this.finalDate = finalDate;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return "Tarefa " +
                "Nome = " + name +
                ", \nDescrição = " + description +
                ", \nData Limite = " + dateFormat.format(finalDate) +
                ", \nPrioridade = " + priority +
                ", \nCategoria = " + category +
                ", \nStatus = " + status + "\n";
    }
}