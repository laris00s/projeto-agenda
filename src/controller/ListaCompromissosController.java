package controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent; 
import javafx.scene.Scene; 
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage; 
import model.Compromisso;
import model.ArquivoCompromissos;

public class ListaCompromissosController {

    @FXML
    private ListView<HBox> listViewCompromissos;

    private List<Compromisso> compromissos;

    @FXML
    public void initialize() {
        compromissos = ArquivoCompromissos.carregar();
        atualizarListaCompromissos();
    }

    public void atualizarListaCompromissos() {
        listViewCompromissos.getItems().clear();

        for (Compromisso compromisso : compromissos) {

            HBox hbox = new HBox(10);

            Label compromissoLabel = new Label(compromisso.getTitulo());
            compromissoLabel.setStyle("-fx-font-size: 14px;");

            Button btnDetalhes = new Button("Mais Detalhes");
            btnDetalhes.setOnAction(e -> mostrarDetalhes(compromisso));

            Button btnEditar = new Button("Editar");
            btnEditar.setOnAction(e -> editarCompromisso(compromisso));

            hbox.getChildren().addAll(compromissoLabel, btnDetalhes, btnEditar);

            listViewCompromissos.getItems().add(hbox);
        }

        ArquivoCompromissos.salvar(compromissos);
    }

    private void mostrarDetalhes(Compromisso compromisso) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalhes do Compromisso");
        alert.setHeaderText(compromisso.getTitulo());
        alert.setContentText(
                "Data: " + compromisso.getData() + "\n\n" +
                "Descrição:\n" + compromisso.getDescricao()
        );
        alert.showAndWait();
    }

    private void editarCompromisso(Compromisso compromisso) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditarCompromisso.fxml"));
            Parent root = loader.load();

            EditarCompromissoController controller = loader.getController();
            controller.setCompromisso(compromisso, this);

            Stage stage = new Stage();
            stage.setTitle("Editar Compromisso");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    public void adicionarCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
        ArquivoCompromissos.salvar(compromissos);
        atualizarListaCompromissos();
    }

    @FXML
    private void btnFechar() {
        ArquivoCompromissos.salvar(compromissos);
        Stage stage = (Stage) listViewCompromissos.getScene().getWindow();
        stage.close();
    }
}