package Aula2;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CadastroDeAlunos_1 {

	private enum TipoDeLog {
		ERRO, INICIAR, EXIBIR, INSERIR, EXCLUIR, ENCERRAR
	}
       
        

	private Scanner entrada;
	private final String enderecoArquivoDeDados = "D:\\2020deucerto\\COMPUTAÇÃO\\DELL\\AULA2\\dados.ser";
	private final String enderecoArquivoDeLog = "D:\\2020deucerto\\COMPUTAÇÃO\\DELL\\AULA2\\log.txt";
	private File arquivo;
	private HashMap<String, Aluno> alunos;

	public static void main(String[] args) {
		CadastroDeAlunos_1 cadastro = new CadastroDeAlunos_1();
		
		cadastro.salvarNoLog(TipoDeLog.INICIAR, "Iniciando a aplicação.");
		
		cadastro.iniciar();
	}

	public CadastroDeAlunos_1() {
		arquivo = new File(enderecoArquivoDeDados);
		alunos = new HashMap<String, Aluno>();
		entrada = new Scanner(System.in);
                
	}

	private void iniciar() {
		int opcao = -1;
		try {

			lerArquivo();

			do {
				exibeMenu();
				opcao = opcaoUsuario();
				switch (opcao) {
				case 1:
					exibirAlunos();
					break;
				case 2:
					novoAluno();
					break;
				case 3:
					removerAluno();
					break;
				case 4:
					break;

				default:
					System.out.println("Opção inexistente.");
					opcao = -1;
				}

			} while (opcao != 4);

			finalizar();

		} catch (IOException exception) {
			System.err.println("Falha durante a manipulação do arquivo.");
			System.err.println("Erro: " + exception.getMessage());
                        this.salvarNoLog(TipoDeLog.ERRO, "Erro na aplicação."+exception.getMessage());
		} catch (ClassNotFoundException exception) {
			System.err.println("Falha durante conversão do registro em Aluno.");
                         this.salvarNoLog(TipoDeLog.ERRO, "Erro na aplicação."+exception.getMessage());
		}

		System.out.println("Encerrando...");
		System.exit(0);
	}

	private void finalizar() throws IOException {
		System.out.println("Salvando dados no arquivo...");
                this.salvarNoLog(TipoDeLog.ENCERRAR, "Encerrando a aplicação.");
		gravarArquivo();
	}

	private void exibeMenu() {
		System.out.println(":: Menu ::\n");
		System.out.println(" 1. Ver Alunos\n 2. Adicionar Aluno\n 3. Remover Aluno\n 4. Sair");
		System.out.print(">> ");
	}

	private int opcaoUsuario() throws IOException {
		return entrada.nextInt();
	}

	private void exibirAlunos() {
		System.out.println("# Lista de Alunos\n");
		for (Aluno aluno : alunos.values()) {
			System.out.println("Matricula: " + aluno.getMatricula());
			System.out.println("Nome: " + aluno.getNome());
			if (aluno.getEndereco() != null && !aluno.getEndereco().isEmpty()) {
				System.out.println("Endereço: " + aluno.getEndereco());
			}
			if (aluno.getTelefone() != null && !aluno.getTelefone().isEmpty()) {
				System.out.println("Telefone: " + aluno.getTelefone());
			}
			System.out.println();
		}
		System.out.println("-----------------------");
                this.salvarNoLog(TipoDeLog.EXIBIR, "Exibindo lista de Alunos.");
		
	}

	private void novoAluno() {
		System.out.println("Informe matr�cula, nome, endereço e telefone do aluno, separados por virgula.");
		System.out.print("* Apenas matr�cula e nome são obrigatórios. \n>>");
		entrada.nextLine();

		String dados = entrada.nextLine();
		String[] dadosAluno = dados.split(",");

		Aluno aluno = new Aluno(dadosAluno[0], dadosAluno[1]);
		if (dadosAluno.length >= 3) {
			aluno.setEndereco(dadosAluno[2]);
		}
		if (dadosAluno.length >= 4) {
			aluno.setTelefone(dadosAluno[3]);
		}

		alunos.put(aluno.getMatricula(), aluno);
                this.salvarNoLog(TipoDeLog.INSERIR, "Incerindo novo Aluno.");
	}

	private void removerAluno() {
		System.out.print("Informe matrícula do aluno que deseja remover.\n>>");
		entrada.nextLine();

		String matricula = entrada.nextLine();

		if (alunos.containsKey(matricula)) {
			Aluno aluno = alunos.remove(matricula);
			System.out.println("Aluno " + aluno.getNome() + " removido...");
		} else {
			System.out.println("Matrícula não encontrada...");
		}
                 this.salvarNoLog(TipoDeLog.EXCLUIR, "Excluindo aluno.");
	}

	private void lerArquivo() throws IOException, ClassNotFoundException {

		if (arquivo.length() <= 0)
			return;

		System.out.println("Carregando dados do arquivo...");
		ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivo));
		Aluno temp = null;

		try {

			while ((temp = (Aluno) leitor.readObject()) != null) {
				alunos.put(temp.getMatricula(), temp);
			}

		} catch (EOFException e) {
			System.out.println("Arquivo de registros vazio.");
                        
		}
		leitor.close();
		leitor = null;
                
	}

	private void gravarArquivo() throws IOException {
		if (alunos != null) {
			ObjectOutputStream gravador = new ObjectOutputStream(new FileOutputStream(arquivo, false));
			for (Aluno a : alunos.values()) {
				gravador.writeObject(a);
			}
			gravador.flush();
			gravador.close();
			gravador = null;
		}
	}

	private void salvarNoLog(TipoDeLog tipoDeLog, String log) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime agora = LocalDateTime.now();
		
		String mensagemDeLog = "[" + agora.format(format) + "] [" + tipoDeLog + "] " + log + "\n";
		
		System.out.println(mensagemDeLog);
		
		// Implementar aqui as funcionalidades para salvar o Log em arquivo
                try{
            File arquivo = new File("D:\\2020deucerto\\COMPUTAÇÃO\\DELL\\AULA2\\log.txt");

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo, true));
            

            bufferedWriter.append(System.getProperty("line.separator") + mensagemDeLog);

            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException exception) {
            System.out.println("# Ocorreu um erro durante a gravação do arquivo.");
            exception.printStackTrace();
        }
                
	}

}
