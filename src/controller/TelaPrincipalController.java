package controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Compromisso;
import model.ArquivoCompromissos;

public class TelaPrincipalController {

    @FXML
    private Button btnNovo;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Label lblTitulo;

    private List<Compromisso> compromissos;

    @FXML
    public void initialize() {
        compromissos = ArquivoCompromissos.carregar();
    }

    @FXML
    void btnCriarNovo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastrarCompromisso.fxml"));
            Parent root = loader.load();

            CadastrarCompromissoController controller = loader.getController();
            controller.setCompromissos(compromissos);

            Stage stage = new Stage();
            stage.setTitle("Novo Compromisso");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir nova view: " + e.getMessage());
        }
    }

    @FXML
    private void btnMostrarLista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListaCompromissos.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Lista de Compromissos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}