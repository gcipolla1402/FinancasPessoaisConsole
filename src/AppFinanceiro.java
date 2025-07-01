import java.util.*;

public class AppFinanceiro {
    static List<Transacao> transacoes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU FINANCEIRO ---");
            System.out.println("1. Adicionar Receita");
            System.out.println("2. Adicionar Despesa");
            System.out.println("3. Ver Saldo");
            System.out.println("4. Listar Transações");
            System.out.println("5. Ver Gráfico de Despesas (Console)");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt(); sc.nextLine();

            switch (opcao) {
                case 1 -> adicionarTransacao("Receita", sc);
                case 2 -> adicionarTransacao("Despesa", sc);
                case 3 -> mostrarSaldo();
                case 4 -> listarTransacoes();
                case 5 -> gerarGraficoTextoDespesas();
                case 6 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    static void adicionarTransacao(String tipo, Scanner sc) {
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Valor: ");
        double valor = sc.nextDouble(); sc.nextLine();
        String categoria = "-";
        if (tipo.equals("Despesa")) {
            System.out.print("Categoria: ");
            categoria = sc.nextLine();
        }
        transacoes.add(new Transacao(tipo, descricao, valor, categoria));
        System.out.println(tipo + " adicionada com sucesso!");
    }

    static void mostrarSaldo() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            saldo += t.getTipo().equals("Receita") ? t.getValor() : -t.getValor();
        }
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    static void listarTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
            return;
        }
        for (Transacao t : transacoes) {
            System.out.println(t);
        }
    }

    static void gerarGraficoTextoDespesas() {
        Map<String, Double> categorias = new HashMap<>();

        for (Transacao t : transacoes) {
            if (t.getTipo().equals("Despesa")) {
                categorias.put(
                        t.getCategoria(),
                        categorias.getOrDefault(t.getCategoria(), 0.0) + t.getValor()
                );
            }
        }

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma despesa registrada.");
            return;
        }

        double maior = Collections.max(categorias.values());

        System.out.println("\n--- Gráfico de Despesas ---");
        for (Map.Entry<String, Double> entry : categorias.entrySet()) {
            String cat = entry.getKey();
            double valor = entry.getValue();
            int barra = (int) ((valor / maior) * 20); // escala de 20
            String grafico = "█".repeat(barra);
            System.out.printf("%-15s | %-20s R$ %.2f%n", cat, grafico, valor);
        }
    }
}