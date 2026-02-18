package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoCompromissos {

    private static final String ARQUIVO = "compromissos.txt";

    public static void salvar(List<Compromisso> lista) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Compromisso c : lista) {
                writer.println(c.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Compromisso> carregar() {
        List<Compromisso> lista = new ArrayList<>();

        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                lista.add(Compromisso.fromFileString(linha));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}