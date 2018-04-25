/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 *
 * @author Bruno
 */
public class FXMLController {

    @FXML
    private CheckBox chkCMixto;
    @FXML
    private TextField txtSemillaC;
    @FXML
    private Button btnProbar;
    @FXML
    private TextField txtCantNrosC;
    @FXML
    private TextField txtCantIntervalosC;
    @FXML
    private Button btnReiniciarC;
    @FXML
    private TextArea txtAResultados;
    @FXML
    private BarChart<?, ?> chrtFrecuenciaC;
    @FXML
    private NumberAxis yFrecBc;
    @FXML
    private CategoryAxis xIntervalo;
    @FXML
    private LineChart<?, ?> lnchrEsperadaC;
    @FXML
    private NumberAxis yFrecLn;
    @FXML
    private ListView<?> lsvSerie;
    @FXML
    private Label lbl_sumatoria_chi_cuadrado;
    @FXML
    private Label lbl_acepta_prueba;
    @FXML
    private TableView<?> tbl_Tabla;


    @FXML
    private void handleChkCMixto(ActionEvent event) {
    }

    @FXML
    private void handleButtonProbar(ActionEvent event) {
    }

    @FXML
    private void handleButtonReiniciarC(ActionEvent event) {
    }
    
}
