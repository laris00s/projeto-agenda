package controller;

import java.util.List;
import model.ArquivoCompromissos;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Compromisso;

public class CadastrarCompromissoController {

    @FXML
    private Button btnAdicionarCompromisso;

    @FXML
    private DatePicker dateData;

    @FXML
    private Label lblData;

    @FXML
    private Label lblDescricao;

    @FXML
    private Label lblNovoCompromisso;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtTitulo;
    
    
    private List<Compromisso> compromissos;

    public void setCompromissos(List<Compromisso> compromissos) {
        this.compromissos = compromissos; 
    }

    public void adicionarCompromisso() {
        String titulo = txtTitulo.getText();
        String descricao = txtDescricao.getText();
        String data = (dateData.getValue() != null) ? dateData.getValue().toString() : null;

        
        if (titulo.isEmpty() || descricao.isEmpty() || data == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Preencha todos os campos corretamente.");
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return; 
        }

        Compromisso compromisso = new Compromisso(titulo, data, descricao);
        System.out.println("Compromisso salvo: " + compromisso);

        compromissos.add(compromisso); 
        ArquivoCompromissos.salvar(compromissos); 

        txtTitulo.clear();
        txtDescricao.clear();
        dateData.setValue(null);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Compromisso Adicionado");
        alert.setHeaderText(null);
        alert.setContentText("O compromisso foi adicionado com sucesso!");
        alert.showAndWait();

        ArquivoCompromissos.salvar(compromissos);

        Stage stage = (Stage) btnAdicionarCompromisso.getScene().getWindow();
        stage.close();  
    }
        
    }


