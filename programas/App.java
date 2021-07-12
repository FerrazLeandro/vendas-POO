package programas;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import classes.Produto;
import classes.Venda;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {
        int opcao = 0;
        Scanner in = new Scanner(System.in);
        String nomeProduto = null;
        List<Produto> produtos = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        do {
            System.out.println("\n:::: MENU ::::");
            System.out.println("1 – Incluir produto");
            System.out.println("2 – Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 – Registrar venda");
            System.out.println("5 – Vendas por período – detalhado");
            System.out.println("0 - Sair");
            System.out.printf("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada
            System.out.printf("\n");

            if (opcao == 1) {                  
                Produto prod = new Produto();

                System.out.println("\n:::: CADASTRO DE PRODUTOS ::::");     
                System.out.print("Código: ");
                prod.setCodigo(in.nextInt());
                in.nextLine(); // Tira o ENTER que ficou na entrada
                System.out.print("Nome: ");
                prod.setNome(in.nextLine());
                System.out.print("Valor: ");
                prod.setValor(in.nextDouble());
                System.out.print("Quantidade: ");
                prod.setQuantidade(in.nextInt());
                in.nextLine(); // Tira o ENTER que ficou na entrada
                produtos.add(prod); 

            } else if (opcao == 2) {
                if (produtos.size() == 0) {
                 System.out.println("\nNão há produtos cadastrados.");
                } else {
                    boolean achou  = false;
                    Produto prodEncontrado = null;
                    System.out.print("Informe nome do produto: ");
                    nomeProduto = in.nextLine();

                    for (int i = 0; !achou && i < produtos.size();  i++){
                        achou = (produtos.get(i).getNome().equals(nomeProduto));
                        prodEncontrado = produtos.get(i);
                    }

                    if (achou == true) {
                        System.out.println("\n:::: Busca de produtos ::::");
                        System.out.println(prodEncontrado.toString());
                        } else {
                            System.out.println("\nProduto não encontrado!");
                        }
                }

            } else if (opcao == 3) {                       
                if (produtos.size() == 0) {
                System.out.println("\nNão há produtos cadastrados.");
                } else {
                    System.out.print("\n:::: Listagem de produtos ::::\n");
                    produtos.sort(Comparator.comparing(Produto::getCodigo));
                    produtos.forEach(System.out::println);
                    double media = produtos.stream().mapToDouble(prod -> prod.getValor()).average().getAsDouble();
                    double max = produtos.stream().mapToDouble(prod -> prod.getValor()).max().getAsDouble();
                    double min = produtos.stream().mapToDouble(prod -> prod.getValor()).min().getAsDouble();
                    System.out.printf("\nValor médio R$: %.2f - Valor máximo R$: %.2f - Valor mínimo R$: %.2f\n: ", media, max, min);         
                }
            } else if (opcao == 4) {
                Venda vend = new Venda ();  
                System.out.print("Informe o nome do produto: ");
                nomeProduto = in.nextLine();
                boolean achou  = false;
                Produto prodEncontrado = null;

                for (int i = 0; !achou && i < produtos.size();  i++){
                    achou = (produtos.get(i).getNome().equals(nomeProduto));
                    prodEncontrado = produtos.get(i);
                    }

                if (achou == true) {
                    System.out.printf("\nProduto: %s \n", prodEncontrado.getNome());
                    vend.setNomeVenda(prodEncontrado.getNome());
                    System.out.print("Data 'DD/MM/AAAA': ");
                    Date dataVenda = formato.parse(in.nextLine());
                    vend.setDataVenda(dataVenda);
                    System.out.print("Quantidade: ");
                    vend.setQuantidadeVenda(in.nextInt());
                    vend.setValorUnitario(prodEncontrado.getValor());
                    vend.setValorTotal(vend.getValorUnitario() * vend.getQuantidadeVenda());

                    if (prodEncontrado.getQuantidade() < vend.getQuantidadeVenda()){
                        System.out.printf("\nNão há quantidade disponivel em estoque! \nQuantidade em estoque: %s\n", prodEncontrado.getQuantidade());
                    } else {
                        vendas.add(vend);
                        prodEncontrado.setQuantidade(prodEncontrado.getQuantidade() - vend.getQuantidadeVenda());  
                        System.out.println("\n Operação realizada com sucesso!\n");  
                    }
                } else {
                    System.out.println("\nProduto não encontrado!\n ");
                } 
                    
            } else if (opcao == 5) {
                    if (vendas.size() == 0) {
                        System.out.println("\nNão há vendas realizadas.");
                        } else {
                        System.out.print("Data inicial 'DD/MM/AAAA': ");
                        Date dataInicial = formato.parse(in.nextLine());
                        System.out.print("Data final 'DD/MM/AAAA': ");
                        Date dataFinal = formato.parse(in.nextLine());
                        System.out.printf("\n  :::: Listagem de vendas ::::\n:::: %s a %s :::: \n", formato.format(dataInicial), formato.format(dataFinal));
                        vendas.sort(Comparator.comparing(Venda::getDataVenda));
                        double media = 0;
                        int quantidadeVendaPeriodo = 0;
                        for (int i = 0; i < vendas.size(); i++) {
                            if (vendas.get(i).getDataVenda().after(dataInicial) && vendas.get(i).getDataVenda().before(dataFinal)){
                                System.out.printf(vendas.get(i).toString());
                                quantidadeVendaPeriodo = quantidadeVendaPeriodo + 1;
                                media = (media + vendas.get(i).getValorTotal()) / quantidadeVendaPeriodo;
                            }
                        }                   
                       // vendas.forEach(System.out::println); 
                        //double media = vendas.stream().mapToDouble(vend -> vend.getValorTotal()).average().getAsDouble();        
                        System.out.printf("\nValor médio das vendas no período R$: %.2f\n", media);    
                    }
            
            } else if (opcao != 0) {
            System.out.println("\nOpção inválida!");
        }} while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.print("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}
