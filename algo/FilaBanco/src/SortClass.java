import java.util.ArrayList;

public class SortClass {
	
	public ArrayList<Customer> colocandoFila(String nome, int idade, ArrayList<Customer> listCustomer){
		listCustomer.add(new Customer(nome, idade));
		ArrayList<Customer> listaSubstituta = ordenando(listCustomer);
		return listaSubstituta;
	}
	
	public ArrayList<Customer> ordenando(ArrayList<Customer> listCustomer){
		ArrayList<Customer> listaNova = new ArrayList<Customer>();
		
		int atual = 0;
		int pos = 0;
		while (listCustomer.size()>0) {
			atual = listCustomer.get(pos).getIdade(); //Pega a primeira posição pra se tornar a posição atual
			for (int i = 0; i < listCustomer.size(); i++) {
				if(i>pos) { //Verifica se não está sendo comparado a posição atual com o contador do for
					if(listCustomer.get(i).getIdade()>atual) {
						atual = listCustomer.get(i).getIdade(); //Nova idade atual
						pos = i; //nova posição atual
					}
				} 
			}
			
			listaNova.add(listCustomer.get(pos)); //Adicionando na listaNova o objeto com maior valor em IDADE 
			listCustomer.remove(pos); //Remove o maior elemento da lista
			
			//Zera todos os valores
			atual = 0;
			pos = 0;
		}
		return listaNova;
	}
	
}
