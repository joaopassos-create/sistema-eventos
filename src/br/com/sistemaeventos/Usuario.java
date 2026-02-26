package br.com.sistemaeventos;
import java.util.ArrayList;
public class Usuario {
	
	private String nome;
	private String email;
	private int idade;
	private ArrayList<Evento> eventosConfirmados = new ArrayList<>();
	
	public Usuario(String nome, String email, int idade) {
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}
		public String getNome() {
			return nome;
		}
		public void confirmarEvento(Evento evento) {
			eventosConfirmados.add(evento);
			}
		public void cancelarEvento(Evento evento) {
			eventosConfirmados.remove(evento);
		}
		public ArrayList<Evento> getEventosConfirmados() {
			return eventosConfirmados;
		}
}

		