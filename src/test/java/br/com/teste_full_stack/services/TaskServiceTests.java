package br.com.teste_full_stack.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.teste_full_stack.model.Task;
import br.com.teste_full_stack.repository.TaskRepository;
import br.com.teste_full_stack.service.TaskService;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitarios para TaskService")
public class TaskServiceTests {

	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskService taskService;
	
	private Task task1;
	private Task task2;
	
	@BeforeEach
	void setup() {
		task1 = new Task(1L, "Estudar Spring Boot", false);
		task2 = new Task(2L, "Fazer compras", true);
	}
	
	@Test
	@DisplayName("Deve retornar todas as tarefas")
	void shouldGetAllTasks() {
		when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));
		
		List<Task> tasks = taskService.getAllTasks();
		
		assertNotNull(tasks);
		assertEquals(2, tasks.size());
		assertEquals("Estudar Spring Boot", tasks.get(0).getDescricao());
		assertEquals("Fazer compras", tasks.get(1).getDescricao());
		
		verify(taskRepository, times(1)).findAll();
	}
	
	@Test
	@DisplayName("Deve retornar tarefas pendentes")
	void sholdGetPendingTasks() {
		when(taskRepository.findByCompletedFalse()).thenReturn(Arrays.asList(task1));
		
		List<Task> pendingTasks = taskService.getPendingTask();
		
		assertNotNull(pendingTasks);
		assertEquals(1, pendingTasks.size());
		assertEquals("Estudar Spring Boot", pendingTasks.get(0).getDescricao());
		assertFalse(pendingTasks.get(0).isCompleto());
		
		verify(taskRepository, times(1)).findByCompletedFalse();
	}
	
	@Test
	@DisplayName("Deve criar uma nova tarefa e marca-la como pendente")
	void sholdCreateTask() {
		Task newTask = new Task(null, "Ler um livro", false);
		
		when(taskRepository.save(any(Task.class))).thenReturn(new Task(3L, "Ler um livro", false));

		Task createdTask = taskService.createTask(newTask);
		
		assertNotNull(createdTask.getId());
		assertEquals("Ler um livro", createdTask.getDescricao());
		assertEquals(false, createdTask.isCompleto());
		
		verify(taskRepository, times(1)).save(any(Task.class));
	}
	
	@Test
    @DisplayName("Deve alternar o status de conclusão de uma tarefa")
    void shouldToggleTaskCompletion() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        when(taskRepository.save(any(Task.class))).thenReturn(new Task(1L, "Estudar Spring Boot", true));

        Task toggledTask = taskService.toggleTaskCompletion(1L);

        assertTrue(toggledTask.isCompleto());
        
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }
	
	@Test
    @DisplayName("Deve lançar exceção ao tentar alternar status de tarefa inexistente")
    void shouldThrowExceptionWhenToggleTaskNotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            taskService.toggleTaskCompletion(99L);
        });

        assertTrue(thrown.getMessage().contains("Tarefa não encontrada com ID 99"));
        verify(taskRepository, times(1)).findById(99L);
        verify(taskRepository, never()).save(any(Task.class));
    }
	
	@Test
    @DisplayName("Deve deletar uma tarefa")
    void shouldDeleteTask() {
        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }
}
