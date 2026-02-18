package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.ArquivoCompromissos;
import model.Compromisso;

public class EditarCompromissoController {

    private Compromisso compromisso; 
    private ListaCompromissosController listaController; 

    @FXML
    private Label lblNovoCompromisso;

    @FXML
    private Label lblDescricao;

    @FXML
    private Label lblTitulo;

    @FXML
    private DatePicker dateData;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtTitulo;

    @FXML
    private Label lblData;

    @FXML
    private Button btnSalvarAlteracao;

    @FXML
    void salvarAlteracao(ActionEvent event) {
        if (compromisso != null) {

            compromisso.setTitulo(txtTitulo.getText());
            compromisso.setDescricao(txtDescricao.getText());

            if (dateData.getValue() != null) {
                compromisso.setData(dateData.getValue().toString());
            }

            listaController.atualizarListaCompromissos();
            ArquivoCompromissos.salvar(listaController.getCompromissos());

            ((Button) event.getSource()).getScene().getWindow().hide();
        }
    }

    public void setCompromisso(Compromisso compromisso, ListaCompromissosController listaController) {
        this.compromisso = compromisso;
        this.listaController = listaController;

        txtTitulo.setText(compromisso.getTitulo());
        txtDescricao.setText(compromisso.getDescricao());

        if (compromisso.getData() != null && !compromisso.getData().isEmpty()) {
            try {
                dateData.setValue(java.time.LocalDate.parse(compromisso.getData()));
            } catch (Exception e) {
                System.out.println("Data inválida ignorada: " + compromisso.getData());
            }
        }
    }
}