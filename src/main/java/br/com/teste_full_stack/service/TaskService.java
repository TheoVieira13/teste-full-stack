package br.com.teste_full_stack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.teste_full_stack.model.Task;
import br.com.teste_full_stack.repository.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	public List<Task> getPendingTask() {
		return taskRepository.findByCompletedFalse();
	}
	
	public List<Task> getCompletedTasks() {
		return taskRepository.findByCompletedTrue();
	}
	
	public Optional<Task> getTasksById(Long id) {
		return taskRepository.findById(id);
	}
	
	public Task createTask(Task task) {
		task.setCompleto(false);
		return taskRepository.save(task);
	}
	
	public Task updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setDescricao(taskDetails.getDescricao());
            task.setCompleto(taskDetails.isCompleto());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID " + id));
    }
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
	
	public Task toggleTaskCompletion(Long id) {
		return taskRepository.findById(id).map(task -> {
			task.setCompleto(!task.isCompleto());
			return taskRepository.save(task);
		}).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID " + id));
	}
}
