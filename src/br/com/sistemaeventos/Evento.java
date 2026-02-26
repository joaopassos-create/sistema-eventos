package br.com.sistemaeventos;
import java.time.LocalDateTime;
import java.io.Serializable;
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
	private String endereco;
	private String categoria;
	private LocalDateTime horario;
	private String descricao;
	
	public Evento(String nome, String endereco, String categoria,
     LocalDateTime horario, String descricao)  {
		this.nome = nome;
		this.endereco = endereco;
		this.categoria = categoria;
		this.horario = horario;
		this.descricao = descricao;
		}

	public String getNome () {
		return nome;
	}
	
	public LocalDateTime getHorario() {
		return horario;
	}
	
	@Override
	public String toString() {

	    String status;

	    if (horario.isBefore(LocalDateTime.now())) {
	        status = " (Evento já ocorreu)";
	    } else if (horario.isAfter(LocalDateTime.now())) {
	        status = " (Evento futuro)";
	    } else {
	        status = " (Acontecendo agora!)";
	    }

	    return "Nome: " + nome +
	           " | Endereço: " + endereco +
	           " | Categoria: " + categoria +
	           " | Horário: " + horario +
	           " | Descrição: " + descricao +
	           status;
	}
}
		
	