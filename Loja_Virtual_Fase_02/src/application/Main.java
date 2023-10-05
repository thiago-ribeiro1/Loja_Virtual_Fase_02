package application;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Estoque estoque = new Estoque();

		int opcao = 0;
		while (opcao != 9) {
			System.out.println("============= MENU =============");
			System.out.println();
			System.out.println("1) Listar todos os produtos");
			System.out.println("2) Listar produtos que um determinado vendedor cadastrou");
			System.out.println("3) Contar quantos produtos foram cadastrados por um determinado vendedor");
			System.out.println("4) Somar os valores dos produtos que foram cadastrados por um determinado vendedor");
			System.out.println("5) Cadastrar um novo produto");
			System.out.println("6) Adicionar estoque de um produto");
			System.out.println("7) Remover um produto do comércio");
			System.out.println("8) Vender algum produto existente");
			System.out.println("9) Sair do programa");
			System.out.println();
			System.out.println();
			System.out.println("============= MENU =============");
			System.out.println();
			System.out.println("Digite a sua opção: ");
			opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				estoque.listarProdutos();
				break;

			case 2:
				estoque.PrintarLista();
				break;

			case 3:
				estoque.qntProdutos();
				break;

			case 4:
				estoque.somaValorProduto();
				break;

			case 5:
					estoque.cadastrar();
					break;
			case 6:
					estoque.adicionarEstoque();
					break;

			case 7:
					estoque.remover();
					break;
			case 8:
					estoque.vender();
					break;

			case 9:
				System.out.println();
				System.out.println("Sair\n");
				break;
			default:
				System.out.println();
				System.out.println("Opção inválida, tente novamente \n");
				break;
			}
		}
		sc.close();
	}
}