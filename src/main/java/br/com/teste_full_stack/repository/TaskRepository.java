package br.com.teste_full_stack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste_full_stack.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByCompletedFalse();
	List<Task> findByCompletedTrue();
}
