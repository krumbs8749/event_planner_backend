package com.app.service;

import com.app.model.Task;
import com.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createOrUpdateTask(Task task) {
        // Validate necessary information
        if (task.getName() == null || task.getDescription() == null || task.getEvent() == null) {
            throw new IllegalArgumentException("Missing essential data for the task.");
        }

        // Check if a task with the same name and event already exists
        Optional<Task> existingTask = taskRepository.findByNameAndEvent(task.getName(), task.getEvent());
        if (existingTask.isPresent()) {
            // If such a task exists, update its properties
            Task updatedTask = existingTask.get();
            updatedTask.setName(task.getName());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setCost(task.getCost() != null ? task.getCost() : 0.0);
            // Optionally, update other fields as necessary

            return taskRepository.save(updatedTask);
        }

        // If no such task exists, create a new task
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
