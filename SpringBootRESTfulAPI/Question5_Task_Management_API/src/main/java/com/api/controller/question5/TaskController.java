package com.api.controller.question5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.question5.Task;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 5L;

    public TaskController() {
        // Initialize sample data
        tasks.add(new Task(1L, "Complete Spring Boot Course", "Finish all modules and projects", false, "HIGH",
                "2026-02-28"));
        tasks.add(new Task(2L, "Write API Documentation", "Document all endpoints with examples", false, "MEDIUM",
                "2026-03-15"));
        tasks.add(new Task(3L, "Unit Testing", "Write unit tests for all controllers", false, "HIGH", "2026-03-10"));
        tasks.add(new Task(4L, "Code Review", "Review and refactor existing code", true, "MEDIUM", "2026-02-05"));
    }

    // GET all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    // GET task by ID
    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        Optional<Task> task = tasks.stream().filter(t -> t.getTaskId().equals(taskId)).findFirst();
        return task.orElse(null);
    }

    // POST - Create new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setTaskId(nextId++);
        tasks.add(task);
        return task;
    }

    // PUT - Update task by ID
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Optional<Task> existingTask = tasks.stream().filter(t -> t.getTaskId().equals(taskId)).findFirst();
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            task.setPriority(updatedTask.getPriority());
            task.setDueDate(updatedTask.getDueDate());
            return task;
        }
        return null;
    }

    // DELETE task by ID
    @DeleteMapping("/{taskId}")
    public boolean deleteTask(@PathVariable Long taskId) {
        return tasks.removeIf(t -> t.getTaskId().equals(taskId));
    }

    // GET tasks by completion status (Query Parameter)
    @GetMapping("/status")
    public List<Task> getTasksByCompletionStatus(@RequestParam boolean completed) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    // GET tasks by priority
    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable String priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    // PATCH - Mark task as completed
    @PatchMapping("/{taskId}/complete")
    public Task markTaskAsCompleted(@PathVariable Long taskId) {
        Optional<Task> existingTask = tasks.stream().filter(t -> t.getTaskId().equals(taskId)).findFirst();
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setCompleted(true);
            return task;
        }
        return null;
    }
}
