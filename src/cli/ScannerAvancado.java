package cli;

import modelo.Equipe;
import modelo.Uf;
import negocio.NegocioEquipe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Formatter;
import java.util.Scanner;

public class ScannerAvancado {
    private Scanner scan;

    public ScannerAvancado() {
        scan = new Scanner(System.in);
    }

    public int pedirInt(String textoPedido) {
        try {
            System.out.println(textoPedido);
            String valorlido = scan.nextLine();
            int valorInicial = Integer.parseInt(valorlido);
            return valorInicial;
        } catch (NumberFormatException nfe) {
            System.out.println("O valor informado não é um número. Por favor insira um número.\n");
            return pedirInt(textoPedido);
        }
    }

    public String pedirString(String textoPedido) {

        System.out.println(textoPedido);
        String valorlido = scan.nextLine();
        return valorlido;

    }

    public Uf pedirUf() {
        Uf uf = null;

        String escolhido = pedirString("Escolha o Estado:" + "\nAC: Acre | AL: Alagoas | AM: Amazonas | AP: Amapá | BA: Bahia" +
                "\nCE: Ceará | DF: Distrito Federal | ES: Espírito Santo | GO: Goiás | MA: Maranhão" +
                "\nMG: Minas Gerais | MS: Mato Grosso do Sul | MT: Mato Grosso | PA: Pará | PB: Paraíba" +
                "\nPE: Pernambuco | PI: Piauí | PR: Paraná | RJ: Rio de Janeiro | RN: Rio Grande do Norte" +
                "\nRO: Rondônia | RR: Roraima | RS: Rio Grande do Sul | SC: Santa Catarina | SE: Sergipe" +
                "\nSP: São Paulo | TO: Tocantins" +
                "\n\nDIGITE A SIGLA DO ESTADO!\n");

        while (uf == null) {
            if (escolhido.equalsIgnoreCase("AC")) {
                uf = Uf.AC;
            } else if (escolhido.equalsIgnoreCase("AL")) {
                uf = Uf.AL;
            } else if (escolhido.equalsIgnoreCase("AM")) {
                uf = Uf.AM;
            } else if (escolhido.equalsIgnoreCase("AP")) {
                uf = Uf.AP;
            } else if (escolhido.equalsIgnoreCase("BA")) {
                uf = Uf.BA;
            } else if (escolhido.equalsIgnoreCase("CE")) {
                uf = Uf.CE;
            } else if (escolhido.equalsIgnoreCase("DF")) {
                uf = Uf.DF;
            } else if (escolhido.equalsIgnoreCase("ES")) {
                uf = Uf.ES;
            } else if (escolhido.equalsIgnoreCase("GO")) {
                uf = Uf.GO;
            } else if (escolhido.equalsIgnoreCase("MA")) {
                uf = Uf.MA;
            } else if (escolhido.equalsIgnoreCase("MG")) {
                uf = Uf.MG;
            } else if (escolhido.equalsIgnoreCase("MS")) {
                uf = Uf.MS;
            } else if (escolhido.equalsIgnoreCase("MT")) {
                uf = Uf.MT;
            } else if (escolhido.equalsIgnoreCase("PA")) {
                uf = Uf.PA;
            } else if (escolhido.equalsIgnoreCase("PB")) {
                uf = Uf.PB;
            } else if (escolhido.equalsIgnoreCase("PE")) {
                uf = Uf.PE;
            } else if (escolhido.equalsIgnoreCase("PI")) {
                uf = Uf.PI;
            } else if (escolhido.equalsIgnoreCase("PR")) {
                uf = Uf.PR;
            } else if (escolhido.equalsIgnoreCase("RJ")) {
                uf = Uf.RJ;
            } else if (escolhido.equalsIgnoreCase("RN")) {
                uf = Uf.RN;
            } else if (escolhido.equalsIgnoreCase("RO")) {
                uf = Uf.RO;
            } else if (escolhido.equalsIgnoreCase("RR")) {
                uf = Uf.RR;
            } else if (escolhido.equalsIgnoreCase("RS")) {
                uf = Uf.RS;
            } else if (escolhido.equalsIgnoreCase("SC")) {
                uf = Uf.SC;
            } else if (escolhido.equalsIgnoreCase("SE")) {
                uf = Uf.SE;
            } else if (escolhido.equalsIgnoreCase("SP")) {
                uf = Uf.SP;
            } else if (escolhido.equalsIgnoreCase("TO")) {
                uf = Uf.TO;
            } else {
                System.out.println("Estado inválido. Tente novamente.");
                escolhido = pedirString("Escolha o Estado:" + "\nAC: Acre | AL: Alagoas | AM: Amazonas | AP: Amapá | BA: Bahia" +
                        "\nCE: Ceará | DF: Distrito Federal | ES: Espírito Santo | GO: Goiás | MA: Maranhão" +
                        "\nMG: Minas Gerais | MS: Mato Grosso do Sul | MT: Mato Grosso | PA: Pará | PB: Paraíba" +
                        "\nPE: Pernambuco | PI: Piauí | PR: Paraná | RJ: Rio de Janeiro | RN: Rio Grande do Norte" +
                        "\nRO: Rondônia | RR: Roraima | RS: Rio Grande do Sul | SC: Santa Catarina | SE: Sergipe" +
                        "\nSP: São Paulo | TO: Tocantins" +
                        "\nDIGITE A SIGLA DO ESTADO!");
            }
        }
        return uf;
    }

    public Equipe pedirEquipe(NegocioEquipe negocioEquipe, String s) {
        Equipe e = null;
        while (e == null) {
            String nomeEquipe = pedirString(s);
            e = negocioEquipe.procurarPorNome(nomeEquipe);
        }
        return e;
    }

    public LocalDateTime pedirData(String textoPedido){
        try {
            System.out.println(textoPedido);
            String valorlido = scan.nextLine() + " 00:00:00";
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //LocalDateTime valorInicial = LocalDateTime.parse(valorlido,formatter);
            LocalDateTime valorInicial = LocalDateTime.from(f.parse(valorlido));
            return valorInicial;
        } catch (Exception e) {
            System.out.println("Erro ao fazer ao passar da data. Certifique-se de usar o formato correto.\n");
            System.out.println(e.getMessage());
            return pedirData(textoPedido);
        }
    }


}