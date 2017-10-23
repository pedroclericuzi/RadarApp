import java.util.ArrayList;
import java.util.Scanner;

public class index {
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args){
		ArrayList<Customer> listCustomer = new ArrayList<>();
		
		System.out.println("Escolha uma operação: ");
		System.out.println("1. Adicionar pessoa;");
		System.out.println("2. Remover pessoa");
		int op = in.nextInt();
		if(op == 1){
			listCustomer = adicionandoPessoa(listCustomer);
			for (int i = 0; i < listCustomer.size(); i++) {
				String nome = listCustomer.get(i).getNome();
				int idade = listCustomer.get(i).getIdade();
				System.out.println(nome +", "+idade+" anos");
			}
		} else {
			removendoPessoa(listCustomer);
		}
		//teste
	}
	
	public static ArrayList<Customer> adicionandoPessoa(ArrayList<Customer> listCustomer){
		System.out.println("Por favor, insira seu nome");
		String nome = in.next();
		System.out.println("Agora, insira sua idade");
		int idade = in.nextInt();
		listCustomer.add(new Customer(nome, idade));
		return listCustomer;
	}
	
	public static void removendoPessoa(ArrayList<Customer> listCustomer) {
		
	}
	
}
