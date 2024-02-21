//Adicionar Tarefa
var taskSaved = [];

document.getElementById("addTask").onclick = function () {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("task-list").style.visibility = "initial";
    document.getElementById("confirmUpdate").style.visibility = "hidden";
    document.getElementById("saveTask").style.visibility = "initial";

}

document.getElementById("saveTask").addEventListener("click", function (event) {

    event.preventDefault();

    document.getElementById("option").style.visibility = "initial";
    document.getElementById("task-list").style.visibility = "hidden";
    document.getElementById("saveTask").style.visibility = "hidden";

    var taskName = document.getElementById("taskName").value;

    var taskDescription = document.getElementById("taskDescription").value;

    var taskFinalDate = document.getElementById("taskFinalDate").value;

    var taskPriority = document.querySelector('input[name="priority"]:checked');

    var selectedPriority = taskPriority ? taskPriority.value : "";

    var category = document.querySelector('input[name="category"]:checked');

    var selectedCategory = category ? category.value : "";

    var status = document.querySelector('input[name="task-status"]:checked');

    var selectedStatus = status ? status.value : "";

    var newTask = {
        name: taskName,
        description: taskDescription,
        finalDate: taskFinalDate,
        priority: selectedPriority,
        category: selectedCategory,
        status: selectedStatus
    };

    taskSaved.push(newTask);

    eraseFields();

});

function eraseFields() {

    document.getElementById("taskName").value = "";
    document.getElementById("taskDescription").value = "";
    document.getElementById("taskFinalDate").value = "";
    document.querySelector('input[name="priority"]:checked').checked = false;
    document.querySelector('input[name="category"]:checked').checked = false;
    document.querySelector('input[name="task-status"]:checked').checked = false;

}

function fillForm() {

    var selectToUpdate = document.getElementById("selectTask");
    var index = selectToUpdate.selectedIndex;

    if(index !== 0) {
        var selectedTask = taskSaved[index - 1];

        document.getElementById("taskName").value = selectedTask.name;
        document.getElementById("taskDescription").value = selectedTask.description;
        document.getElementById("taskFinalDate").value = selectedTask.finalDate;
       
        var selectedPriority = document.querySelector('input[value="' + selectedTask.priority + '"]');

        if (selectedPriority) {

            selectedPriority.checked = true;

        }

        var selectedCategory = document.querySelector('input[value="' + selectedTask.category + '"]');

        if (selectedCategory) {

            selectedCategory.checked = true;

        }

        var selectedStatus = document.querySelector('input[value="' + selectedTask.status + '"]');

        if (selectedStatus) {

            selectedStatus.checked = true;

        }
    } else {

        eraseFields();

    }
}

function updateTaskList() {

    var select = document.getElementById("selectTask");

    select.innerHTML = '';
    select.innerHTML = '<select id="selectTask" onchange="fillForm()"><option value="0" selected>Escolha uma tarefa:</option></select>'

    for (var i = 0; i < taskSaved.length; i++) {

        select.innerHTML += '<option value="' + (i + 1) + '">' + taskSaved[i].name + '</option>';

    }
}

document.getElementById("updateTask").onclick = function () {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("update-task").style.visibility = "initial";
    document.getElementById("task-list").style.visibility = "initial";
    document.getElementById("saveTask").style.visibility = "hidden";
    document.getElementById("confirmUpdate").style.visibility = "initial";
    document.getElementById("confirmUpdate").style.margin = "-14.125em";

    updateTaskList();
    fillForm();

}

function updateTask () {

    var selectToUpdate = document.getElementById("selectTask");
    var index = selectToUpdate.selectedIndex;

    if(index !== 0) {

        var selectedTask = taskSaved[index - 1];

        selectedTask.name = document.getElementById("taskName").value;
        selectedTask.description = document.getElementById("taskDescription").value;
        selectedTask.finalDate = document.getElementById("taskFinalDate").value;
        selectedTask.priority = document.querySelector('input[name="priority"]:checked').value;
        selectedTask.category = document.querySelector('input[name="category"]:checked').value;
        selectedTask.status = document.querySelector('input[name="task-status"]:checked').value;
    
    } else {

        alert("Selecione uma Tarefa!");

    }

}

document.getElementById("confirmUpdate").onclick = function () {

    updateTask();
    updateTaskList();
    eraseFields();

    document.getElementById("option").style.visibility = "initial";
    document.getElementById("update-task").style.visibility = "hidden";
    document.getElementById("task-list").style.visibility = "hidden";
    document.getElementById("confirmUpdate").style.visibility = "hidden";

}


document.getElementById("deleteTask").onclick = function () {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("update-task").style.visibility = "initial";
    document.getElementById("delete-task").style.visibility = "initial";

    updateTaskList();
    eraseFields();

};

function deleteTask () {
    
    var selectToDelete = document.getElementById("selectTask");
    var index = selectToDelete.selectedIndex; 

    if (index !== 0) {
        
        taskSaved.splice(index - 1, 1);

        alert("Tarefa Removida com sucesso!");
        updateTaskList();
        eraseFields();
    } else {
        alert("Selecione uma tarefa para remover!");
    }
}

document.getElementById("confirmDelete").onclick = function () {
    
    document.getElementById("option").style.visibility = "initial";
    document.getElementById("update-task").style.visibility = "hidden";
    document.getElementById("delete-task").style.visibility = "hidden";
    
    deleteTask();

}

function showTasksByPriority() {

    var tasksByPriority = {};

    taskSaved.forEach(function(task) {
        
        var priority = task.priority;

        if(!tasksByPriority[priority]) {

            tasksByPriority[priority] = [];

        }

        tasksByPriority[priority].push(task);

    });

    document.getElementById("show-task").innerHTML = '';

    for (var priority in tasksByPriority) {

        if(tasksByPriority.hasOwnProperty(priority)) {

            var tasks = tasksByPriority[priority];

            var priorityTitle = document.createElement("h2");
            priorityTitle.textContent = "Prioridade: " + priority;
            
            document.getElementById("show-task").appendChild(priorityTitle);

            var ul = document.createElement("ul");

            tasks.forEach(function(task) {

                var li = document.createElement("li");
                li.textContent = task.name;
                ul.appendChild(li);

            });

            document.getElementById("show-task").appendChild(ul);

        }
    }
}

document.getElementById("listTaskByPriority").onclick = function() {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("show-task").style.visibility = "initial";
    document.getElementById("return").style.visibility = "initial";

    showTasksByPriority();

}

function showTasksByStatus() {

    var tasksByStatus = {};

    taskSaved.forEach(function(task) {
        
        var status = task.status;

        if(!tasksByStatus[status]) {

            tasksByStatus[status] = [];

        }

        tasksByStatus[status].push(task);

    });

    document.getElementById("show-task").innerHTML = '';

    for (var status in tasksByStatus) {

        if(tasksByStatus.hasOwnProperty(status)) {

            var tasks = tasksByStatus[status];

            var statusTitle = document.createElement("h2");
            statusTitle.textContent = "Status: " + status;
            
            document.getElementById("show-task").appendChild(statusTitle);

            var ul = document.createElement("ul");

            tasks.forEach(function(task) {

                var li = document.createElement("li");
                li.textContent = task.name;
                ul.appendChild(li);

            });

            document.getElementById("show-task").appendChild(ul);

        }
    }
}

document.getElementById("listTaskByStatus").onclick = function() {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("show-task").style.visibility = "initial";
    document.getElementById("return").style.visibility = "initial";

    showTasksByStatus();

}

function showTasksByDate() {

    taskSaved.sort(function(a, b) {

        var dateA = new Date(a.finalDate);
        var dateB = new Date(b.finalDate);

        return dateA - dateB
    });

    document.getElementById("show-task").innerHTML = '';

    var ul = document.createElement("ul");

    taskSaved.forEach(function(task) {

        var li = document.createElement("li");
        li.textContent = task.name + " - Data: " + formatDate(task.finalDate);
        ul.appendChild(li);

    });

    document.getElementById("show-task").appendChild(ul);
}

document.getElementById("listTaskByDate").onclick = function() {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("show-task").style.visibility = "initial";
    document.getElementById("return").style.visibility = "initial";

    showTasksByDate();

}

function showTasksByCategory() {

    var tasksByCategory = {};

    taskSaved.forEach(function(task) {
        
        var category = task.category;

        if(!tasksByCategory[category]) {

            tasksByCategory[category] = [];

        }

        tasksByCategory[category].push(task);

    });

    document.getElementById("show-task").innerHTML = '';

    for (var category in tasksByCategory) {

        if(tasksByCategory.hasOwnProperty(category)) {

            var tasks = tasksByCategory[category];

            var categoryTitle = document.createElement("h2");
            categoryTitle.textContent = "Categoria: " + category;
            
            document.getElementById("show-task").appendChild(categoryTitle);

            var ul = document.createElement("ul");

            tasks.forEach(function(task) {

                var li = document.createElement("li");
                li.textContent = task.name;
                ul.appendChild(li);

            });

            document.getElementById("show-task").appendChild(ul);

        }
    }
}

document.getElementById("listTaskByCategory").onclick = function() {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("show-task").style.visibility = "initial";
    document.getElementById("return").style.visibility = "initial";

    showTasksByCategory();

}

function showTasksStatistics() {

    var taskStatistics = {};

    taskSaved.forEach(function(task) {
        
        var statistics = task.status;

        if(!taskStatistics[statistics]) {

            taskStatistics[statistics] = 1;

        } else {
            taskStatistics[statistics]++;
        }
    });

    document.getElementById("show-task").innerHTML = '';

     for (var statistics in taskStatistics) {

        if(taskStatistics.hasOwnProperty(statistics)) {

            var showStatistic = document.createElement("h2")
            showStatistic.textContent = statistics + ": " + taskStatistics[statistics];

            document.getElementById("show-task").appendChild(showStatistic);

        }
    }
}

document.getElementById("viewStatistic").onclick = function() {

    document.getElementById("option").style.visibility = "hidden";
    document.getElementById("show-task").style.visibility = "initial";
    document.getElementById("return").style.visibility = "initial";

    showTasksStatistics();

}

document.getElementById("return").onclick = function() {

    document.getElementById("option").style.visibility = "initial";
    document.getElementById("show-task").style.visibility = "hidden";
    document.getElementById("return").style.visibility = "hidden";

}

function formatDate(date) {

    var formatDate = {day: '2-digit', month: '2-digit', year: 'numeric'};
    return new Date(date).toLocaleDateString('pt-br', formatDate);
}

var currentYear = new Date;
currentYear = currentYear.getFullYear();
document.getElementById("current_year").innerHTML = currentYear;



