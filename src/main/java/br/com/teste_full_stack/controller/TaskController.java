package br.com.teste_full_stack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.teste_full_stack.model.Task;
import br.com.teste_full_stack.service.TaskService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class TaskController {

	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping
	public String listAllTasks(Model model) {
		model.addAttribute("tasks", taskService.getAllTasks());
		model.addAttribute("newTask", new Task());
		return "index";
	}
	
	@GetMapping("/pendentes")
	public String listPendingTasks(Model model) {
		model.addAttribute("tasks", taskService.getPendingTask());
		model.addAttribute("newTask", new Task());
		return "index";
	}
	
	@GetMapping("/completos")
	public String listCompletedTasks(Model model) {
		model.addAttribute("tasks", taskService.getCompletedTasks());
		model.addAttribute("newTask", new Task());
		return "index";
	}
	
	@PostMapping("/tasks")
	public String addTask(@Valid @ModelAttribute Task task, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("tasks", taskService.getAllTasks());
			model.addAttribute("newTask", task);
			return "index";
		}
		taskService.createTask(task);
		return "redirect:/";
	}
	
	@PostMapping("/tasks/{id}/toggle")
	public String toggleTaskCompletion(@PathVariable Long id) {
		taskService.toggleTaskCompletion(id);
		return "redirect:/";
	}
	
	@PostMapping("/tasks/{id}/edit")
	public String editTask(@PathVariable Long id, @ModelAttribute Task task) {
		taskService.updateTask(id, task);
		return "redirect:/";
	}
	
	@PostMapping("tasks/{id}/delete")
	public String deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return "redirect:/";
	}
}
