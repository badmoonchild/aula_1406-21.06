package ifpr.pgua.eic.tads;

import java.util.ArrayList;
import java.util.Scanner;

import ifpr.pgua.eic.tads.modelos.Banco;
import ifpr.pgua.eic.tads.modelos.ContaCorrente;
import ifpr.pgua.eic.tads.modelos.Pessoa;

public class App {

    public static String menuGeral() {
        String str = "";

        str += "1 - Conta\n";
        str += "2 - Pessoa\n";
        str += "3 - Banco\n";
        str += "0 - Voltar\n";

        return str;

    }

    public static String menuBanco(){
        String str = "";

        str += "1 - Listar as pessoas\n";
        str += "2 - Listar as contas\n";
        str += "0 - Voltar\n";

        return str;
    }

    public static String menuConta() {
        String str = "";

        str += "1 - Criar conta\n";
        str += "2 - Depositar\n";
        str += "3 - Sacar\n";
        str += "4 - Ver dados\n";
        str += "0 - Sair\n";

        return str;
    }

    public static String menuPessoa() {
        String str = "";
        str += "1 - Cadastrar\n";
        str += "2 - Mostrar\n";
        str += "0 - Voltar\n";

        return str;

    }

    //MADEEEE 
    public static boolean comparadocumento (String cpf, ArrayList<String> guardacpf) {

        boolean erro = false; 

        for (int i = 0; i < guardacpf.size(); i++){

            if(cpf.equals(guardacpf.get(i))){ // comparando as duas strings

                erro = true; 

            }else{

                erro = false; 
            }
        }

        return erro;  
    }

    public static boolean comparaconta (int numeroDaConta, ArrayList<Integer> guardaconta) {

        boolean erro = false; 

        for (int i = 0; i < guardaconta.size(); i++){

            if(numeroDaConta == (guardaconta.get(i))){ // 

                erro = true; 

            }else{

                erro = false; 
            }
        }

        return erro;  
    }

    public static boolean comparaAgencia (int agencia, ArrayList<Integer> guardaAgencia) {

        boolean erro = false; 

        for (int i = 0; i < guardaAgencia.size(); i++){

            if(agencia == (guardaAgencia.get(i))){ // 

                erro = true; 

            }else{

                erro = false; 
            }
        }

        return erro;  
    }

    public static void main(String[] args) throws Exception {
        ContaCorrente conta = null;
        Pessoa pessoa = null;
        Banco banco = new Banco("Bamerindus", "009-0099", "001001/00-9");

        Scanner scan = new Scanner(System.in);
        int opcao;

        int numeroDaConta;
        int agencia;
        String documento;
        String senha;
        boolean ativa;
        double saldo, valor;

        String nome, cpf;
        int idade;
        double salario;
        ArrayList <String> guardacpf = new ArrayList<>(); // 
        ArrayList <Integer> guardaconta = new ArrayList<>(); // 
        ArrayList <Integer> guardaAgencia = new ArrayList<>(); // 

        do {
            System.out.println(menuGeral());
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 2) { //Menu da pessoa
                System.out.println(menuPessoa());
                opcao = scan.nextInt();
                scan.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("Digite o nome:");
                        nome = scan.nextLine();
                        System.out.println("Digite o cpf:");
                        cpf = scan.nextLine();
                        // c
                        //comparadocumento(cpf, guardacpf);
                        while(comparadocumento(cpf, guardacpf)){
                            System.out.println("Nao foi possivel efetuar, CPF ja existente no banco de cadastro");
                            cpf = scan.next();
                        }

                        guardacpf.add(cpf);


                        System.out.println("Digite a idade:");
                        idade = scan.nextInt();
                        System.out.println("Digite o salario:");
                        salario = scan.nextDouble();



                        pessoa = new Pessoa(nome, cpf, idade, salario);
                        banco.cadastrarPessoa(pessoa);

                        System.out.println("Cadastrada!");
                        break;
                    case 2:
                        System.out.println("Detalhes da pessoa");
                        if (pessoa != null) {
                            System.out.println(pessoa);
                        } else {
                            System.out.println("Pessoa não criada!");
                        }
                        break;
                }
            } else if(opcao == 1){

                System.out.println(menuConta());
                opcao = scan.nextInt();
                scan.nextLine();

                switch (opcao) {
                    case 1:
                        if (pessoa != null) {

                            System.out.println("Digite o número da conta:");
                            numeroDaConta = scan.nextInt();
                            // c
                            //comparaconta(cpf, guardacpf);
                            while(comparaconta(numeroDaConta, guardaconta)){
                            System.out.println("Nao foi possivel efetuar, conta ja existente no banco de cadastro");
                            numeroDaConta = scan.nextInt();
                            } 
                    
                            guardaconta.add(numeroDaConta); 
                            //

                            scan.nextLine();
                            System.out.println("Digite a agência:");
                            agencia = scan.nextInt();
                            //
                            while(comparaAgencia(agencia, guardaAgencia)){
                                System.out.println("Nao foi possivel efetuar, numero de Agencia ja existente no banco de cadastro");
                                agencia = scan.nextInt();
                            }
    
                            guardaAgencia.add(agencia);
                            
                            scan.nextLine();
                            System.out.println("Digite a senha:");
                            senha = scan.nextLine();
                            System.out.println("Está ativa (1-sim;0-não)");
                            opcao = scan.nextInt();
                            ativa = opcao == 1;
                            System.out.println("Quer informa o saldo? (1-sim;0-não)");
                            opcao = scan.nextInt();
                            if (opcao == 1) {
                                System.out.println("Digite o saldo:");
                                saldo = scan.nextDouble();
                                conta = new ContaCorrente(numeroDaConta, agencia, pessoa, senha, ativa, saldo);

                            } else {
                                conta = new ContaCorrente(numeroDaConta, agencia, pessoa, senha, ativa);

                            }
                            pessoa.setContaCorrente(conta);
                            banco.cadastarConta(conta);
                            System.out.println("Conta criada!!");
                        }else{
                            System.out.println("Necessário uma pessoa!");
                        }
                        break;
                    case 2:
                        System.out.println("Depositar!");
                        if (conta != null) {
                            System.out.println("Digite um valor:");
                            valor = scan.nextDouble();
                            if (conta.depositar(valor)) {
                                System.out.println("Realizado!");
                            } else {
                                System.out.println("Não Realizado! Valor insuficiente!");
                            }

                        } else {
                            System.out.println("Não permitido! Crie uma conta!");
                        }
                        break;

                    case 3:
                        System.out.println("Sacar!");
                        if (conta != null) {
                            System.out.println("Digite um valor:");
                            valor = scan.nextDouble();
                            if (conta.sacar(valor)) {
                                System.out.println("Realizado!");
                            } else {
                                System.out.println("Não realizado! Valor inválido ou insuficiente!");
                            }

                        } else {
                            System.out.println("Não permitido! Crie uma conta!");
                        }
                        break;
                    case 4:
                        System.out.println("Extrato!");
                        if (conta != null) {
                            System.out.println(conta);
                        } else {
                            System.out.println("Não existe conta criada!");
                        }

                        break;
                }
            }else if(opcao == 3){
                System.out.println(menuBanco());
                opcao = scan.nextInt();
                switch(opcao){
                    case 1:
                        System.out.println("Listar pessoas!");
                        ArrayList<Pessoa> lista = banco.getPessoas();
                        for(int i=0;i<lista.size();i++){
                            System.out.println(lista.get(i));
                        }
                    break;
                    case 2:
                        System.out.println("Listar contas!");
                        ArrayList<ContaCorrente> contas = banco.getContaCorrentes();
                        for(int i=0;i<contas.size() ;i++){
                            System.out.println(contas.get(i));
                        }
                    break;
                }   
            }

        } while (opcao != 0);

    }
}
