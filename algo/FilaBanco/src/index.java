import java.util.ArrayList;
import java.util.Scanner;

public class index {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args){
		ArrayList<Customer> listCustomer = new ArrayList<>();
		boolean rodando = true;

		while(rodando){
			System.out.println("Escolha uma operacao: ");
			System.out.println("1. Adicionar pessoa;");
			System.out.println("2. Remover pessoa");
			System.out.println("3. Sair");
			int op = in.nextInt();
			
			switch (op) {
				case 1:
					listCustomer = adicionandoPessoa(listCustomer);
					break;
				case 2:
					removendoPessoa(listCustomer);
					break;
				case 3:
					rodando = false;
					System.out.println("================= FIM ==================");
					break;
			}
		}
	}
	
	public static ArrayList<Customer> adicionandoPessoa(ArrayList<Customer> listCustomer){
		System.out.println("Por favor, insira seu nome");
		String nome = in.next();
		System.out.println("Agora, insira sua idade");
		int idade = in.nextInt();
		SortClass sortClass = new SortClass();
		ArrayList<Customer> listaNova = sortClass.colocandoFila(nome, idade, listCustomer); //Insere e ordena
		System.out.println("Fila atualizada:");
		for (int i = 0; i < listaNova.size(); i++) {
			String nomeLista = listaNova.get(i).getNome();
			int idadeLista = listaNova.get(i).getIdade();
			System.out.println(" - " + nomeLista +", "+idadeLista+" anos");
		}
		System.out.println("==========================");
		return listaNova;
	}
	
	public static void removendoPessoa(ArrayList<Customer> listCustomer) {
		
		if(listCustomer.size()>0) { //Se a lista não tiver ninguém, ele nem entra
			listCustomer.remove(0); //Remove o mais velho
			System.out.println("Fila atualizada:");
			for (int i = 0; i < listCustomer.size(); i++) {
				String nomeLista = listCustomer.get(i).getNome();
				int idadeLista = listCustomer.get(i).getIdade();
				System.out.println(" - " + nomeLista +", "+idadeLista+" anos");
			}
		} else {
			System.out.println("NÃO EXISTE NINGUÉM CADASTRADO");
		}
	}	
}
