package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {

	private List<Produto> produtos = new ArrayList<>();

	public void listarProdutos() {
		if (produtos.size() == 0) {
			System.out.println("Nenhum produto cadastrado no sistema \n");
		} else {
			System.out.println("Lista de produtos: \n");
			for (Produto p : produtos) {
				System.out.println(p);

			}
		}
	}

	public void cadastrar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome do novo produto: ");
		String nome = sc.nextLine();
		System.out.println("Digite o Nome do Vendedor: ");
		String NomeVendedor = sc.nextLine();
		System.out.println("Digite o CPF do vendedor: ");
		String cpf = sc.nextLine();
		System.out.println("Digite o preço do Produto: ");
		float preco = sc.nextFloat();
		Vendedor v = new Vendedor(NomeVendedor, cpf);
		boolean valid = true;
		int codigo = 0;
		while (valid) {
			System.out.println("Digite o código do Produto: ");
			codigo = sc.nextInt();
			Produto p = buscarProduto(codigo);

			if (p == null) {
				valid = false;
			} else {
				System.out.println("Código já existente!\n");
				valid = true;
			}
		}

		Produto p = new Produto(nome, codigo, 0);
		p.setPreco(preco);
		p.setVendedor(v);
		produtos.add(p);

		System.out.println("Deseja adicionar estoque ao produto? (S/N): ");
		sc.nextLine();
		String resposta = sc.nextLine();
		if (resposta.equalsIgnoreCase("S")) {
			System.out.println("Digite a quantidade a ser adicionada no estoque: ");
			int quantidade = sc.nextInt();
			p.adicionarEstoque(quantidade);

			float valorAcrescentado = quantidade * p.getPreco(); // Calcular o valor em reais que está sendo
																	// acrescentado no estoque
			System.out.println("\n" + nome + " Cadastrado com sucesso \nCódigo: " + codigo + "\nEstoque: " + quantidade
					+ "\nValor em reais acrescentado ao estoque: R$ \n" + valorAcrescentado + "\nPreço: " + preco
					+ "\nVendedor: " + v.getNome() + "\nCPF do vendedor: " + cpf + "\n");
		} else {
			System.out.println("\n" + nome + " Cadastrado com sucesso \nCódigo: " + codigo + "\nPreço: " + preco
					+ "\nVendedor: " + v.getNome() + "\nCPF do vendedor: " + cpf + "\n");
		}
	}

	public void adicionarEstoque() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o código do produto: ");
		int codigo = sc.nextInt();
		Produto p = buscarProduto(codigo);

		if (p != null) {
			System.out.println("Digite a quantidade a ser adicionada no estoque: ");
			int quantidade = sc.nextInt();
			p.adicionarEstoque(quantidade);

			// Calcular o valor em reais que está sendo acrescentado
			float valorAcrescentado = quantidade * p.getPreco();
			System.out.println(p.getNome() + " Estoque atualizado: " + p.getEstoque());
			System.out.println("Valor em reais acrescentado ao estoque: R$" + valorAcrescentado + "\n");
		} else {
			System.out.println("Nenhum produto cadastrado no sistema com o código informado. \n");
		}
	}

	public void remover() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o código do produto que deseja excluir: ");
		int codigo = sc.nextInt();
		Produto p = buscarProduto(codigo);

		if (p != null) {

			System.out.println(p);

			if (p.getEstoque() > 0) {
				System.out.println("Quer realmente excluir este produto? (S/N): ");
				sc.nextLine();
				String resposta = sc.nextLine();
				if (resposta.equalsIgnoreCase("S")) {
					produtos.remove(p);
					System.out.println(
							"Produto: " + p.getNome() + " de código: " + p.getCodigo() + "\nRemovido com sucesso!\n");
				} else {
					System.out.println("Remoção cancelada. \n");
				}
			} else {
				produtos.remove(p);
				System.out.println("Produto de código: " + p.getCodigo() + "\nRemovido com sucesso!\n");
			}
		} else {
			System.out.println("Produto não encontrado! \n");
		}
	}

	public void vender() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o código do produto que deseja vender: ");
		int codigo = sc.nextInt();
		Produto p = buscarProduto(codigo);

		if (p != null) {
			System.out.println("Digite a quantidade a ser vendida: ");
			int quantidade = sc.nextInt();

			if (p.vender(quantidade)) {
				// Calcular o valor em reais que está sendo decrescido
				float valorDecrescido = quantidade * p.getPreco();
				System.out.println(
						"Venda realizada com sucesso. " + p.getNome() + " Estoque atualizado: " + p.getEstoque());
				System.out.println("Valor em reais decrescido do estoque: R$" + valorDecrescido + "\n");
			}
		} else {
			System.out.println("Produto não encontrado! \n");
		}
	}

	private Produto buscarProduto(int codigo) {
		for (Produto p : produtos) {
			if (p.getCodigo() == codigo) {
				return p;
			}
		}
		return null;
	}

	private List<Produto> listaPorVendedor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o CPF do vendeddor: ");
		String cpf = sc.nextLine();
		List<Produto> listaFiltrada = new ArrayList<>();
		// ListaFiltrada é uma nova lista que vai filtrar apenas os produtos vendidos
		// por tal Vendedor.
		for (Produto produto : produtos) {

			if (produto.getVendedor().getCpf().equals(cpf)) {
				listaFiltrada.add(produto);
			}

		}
		return listaFiltrada;
	}

	public void PrintarLista() {
		List<Produto> listaProduto = listaPorVendedor();
		if (listaProduto.size() == 0) {
			System.out.println("Não existem produtos cadastros para este Vendedor!");

		} else {
			for (Produto produto : listaProduto) {
				System.out.println(produto);
			}
		}
	}

	public void qntProdutos() {
		List<Produto> listaProduto = listaPorVendedor();
		System.out.println("Existem: " + listaProduto.size() + " produto(s) cadastrado(s) ");
	}

	public void somaValorProduto() {
		List<Produto> listaProduto = listaPorVendedor();
		if (listaProduto.size() == 0) {
			System.out.println("Não existem produtos cadastros para este Vendedor!");

		} else {
			float precoTotal = 0;
			for (Produto produto : listaProduto) {
				precoTotal = precoTotal + produto.getPreco();
			}
			String nomeVendedor = listaProduto.get(0).getVendedor().getNome();
			System.out.println(
					"A soma dos produtos do vendedor " + nomeVendedor + " tem um preço total de: " + precoTotal);
		}

	}
}