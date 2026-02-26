package br.com.sistemaeventos;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Evento> listaEventos = carregarEventos();
        Usuario usuario = null;
        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE EVENTOS =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Cadastrar evento");
            System.out.println("3 - Listar eventos");
            System.out.println("4 - Confirmar participação");
            System.out.println("5 - Cancelar participação");
            System.out.println("6 - Ver eventos confirmados");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(scanner.nextLine());

                    usuario = new Usuario(nome, email, idade);
                    System.out.println("Usuário cadastrado!");
                    break;

                case 2:
                    System.out.print("Nome do evento: ");
                    String nomeEvento = scanner.nextLine();

                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();

                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();

                    System.out.print("Ano: ");
                    int ano = Integer.parseInt(scanner.nextLine());

                    System.out.print("Mês: ");
                    int mes = Integer.parseInt(scanner.nextLine());

                    System.out.print("Dia: ");
                    int dia = Integer.parseInt(scanner.nextLine());

                    System.out.print("Hora: ");
                    int hora = Integer.parseInt(scanner.nextLine());

                    System.out.print("Minuto: ");
                    int minuto = Integer.parseInt(scanner.nextLine());

                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    LocalDateTime horario = LocalDateTime.of(ano, mes, dia, hora, minuto);

                    Evento evento = new Evento(nomeEvento, endereco, categoria, horario, descricao);
                    listaEventos.add(evento);
                    salvarEventos(listaEventos);

                    System.out.println("Evento cadastrado!");
                    break;

                
                
                case 3:

                    listaEventos.sort((e1, e2) -> e1.getHorario().compareTo(e2.getHorario()));

                    if (listaEventos.isEmpty()) {
                        System.out.println("Nenhum evento cadastrado.");
                    } else {
                        for (int i = 0; i < listaEventos.size(); i++) {
                            System.out.println("[" + i + "] " + listaEventos.get(i));
                        }
                    }
                    break;
               
                case 4:
                    if (usuario == null) {
                        System.out.println("Cadastre um usuário primeiro.");
                        break;
                    }

                    System.out.print("Digite o número do evento: ");
                    int indiceConfirmar = Integer.parseInt(scanner.nextLine());

                    if (indiceConfirmar >= 0 && indiceConfirmar < listaEventos.size()) {

                        Evento eventoSelecionado = listaEventos.get(indiceConfirmar);

                        if (eventoSelecionado.getHorario().isBefore(LocalDateTime.now())) {
                            System.out.println("Não é possível confirmar participação em evento que já ocorreu.");
                        } else {
                            usuario.confirmarEvento(eventoSelecionado);
                            System.out.println("Participação confirmada!");
                        }

                    } else {
                        System.out.println("Evento inválido.");
                    }

                    break;
                  

                    System.out.print("Digite o número do evento: ");
                    int indiceCancelar = Integer.parseInt(scanner.nextLine());

                    usuario.cancelarEvento(listaEventos.get(indiceCancelar));
                    System.out.println("Participação cancelada!");
                    break;

                case 6:
                    if (usuario == null) {
                        System.out.println("Cadastre um usuário primeiro.");
                        break;
                    }

                    if (usuario.getEventosConfirmados().isEmpty()) {
                        System.out.println("Nenhum evento confirmado.");
                    } else {
                        for (Evento e : usuario.getEventosConfirmados()) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 7:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 7);

        scanner.close();
    }
    private static void salvarEventos(ArrayList<Evento> lista) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("events.data"));
            oos.writeObject(lista);
            oos.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar.");
        } 
    }
    @SuppressWarnings("unchecked")
    private static ArrayList<Evento> carregarEventos() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("events.data"));
            ArrayList<Evento> lista = (ArrayList<Evento>) ois.readObject();
            ois.close();
            return lista;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}