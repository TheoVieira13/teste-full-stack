package br.com.teste_full_stack.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_gerenciador_tarefas")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_task")
	private Long id;
	
	@NotBlank(message = "A descrição não pode ser vazia. ")
	@Size(min = 3, max = 255, message = "A descrição deve ter entre 3 e 255 caracteres")
	@Column(name = "descriacao_task")
	private String descricao;
	
	@Column(name = "completo")
	private boolean completo;
	
	public Task(Long id,
			@NotBlank(message = "A descrição não pode ser vazia. ") @Size(min = 3, max = 255, message = "A descrição deve ter entre 3 e 255 caracteres") String descricao,
			boolean completo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.completo = completo;
	}

	public Task() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}

}
