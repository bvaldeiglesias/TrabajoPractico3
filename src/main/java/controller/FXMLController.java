package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pruebasAleatoridad.ChiCuadrado;
import generadoresPseudoAleatorios.Congruencial;

/**
 *
 * @author Bruno
 */
public class FXMLController implements Initializable{

    @FXML
    private Button btnProbar;
    @FXML
    private BarChart chrtFrecuenciaC;
    @FXML
    private NumberAxis yFrecBc;
    @FXML
    private CategoryAxis xIntervalo;
    @FXML
    private LineChart lnchrEsperadaC;
    @FXML
    private NumberAxis yFrecLn;
    @FXML
    private RadioButton rdbUniforme;
    @FXML
    private ToggleGroup grpDistribucion;
    @FXML
    private RadioButton rdbPoisson;
    @FXML
    private RadioButton rdbExponencial;
    @FXML
    private RadioButton rdbNormal;
    @FXML
    private Label lblAoMedia;
    @FXML
    private Label lblBoDesviacion;
    @FXML
    private TextField txtAoMedia;
    @FXML
    private TextField txtBoDesviacion;
    @FXML
    private TextField txtLambda;
    @FXML
    private ListView txfSerieGenerada;
    @FXML
    private TextField txtCantNros;
    @FXML
    private TextField txtCantIntervalos;
    @FXML
    private Button btnReiniciar;
    @FXML
    private Label lblSumatoriaChiCuadrado;
    @FXML
    private Label lblAceptaPrueba;
    @FXML
    private TableView tblTabla;

    private double[] serie;
    private ChiCuadrado pruebaChi;
    private NumberAxis yFrecLn_B;
    private Congruencial rndGenerator;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblTabla.setEditable(true);

        TableColumn colIntervalo = new TableColumn("Intervalo");
        TableColumn colFrec_obt = new TableColumn("Frecuencia Obtenida");
        TableColumn colFrec_esp = new TableColumn("Frecuencia Esperada");
        TableColumn col3 = new TableColumn("(fo-fe)^2");
        TableColumn col4 = new TableColumn("(col 3)/fe");

        colIntervalo.setCellValueFactory(new PropertyValueFactory<>("intervalo"));
        colFrec_obt.setCellValueFactory(new PropertyValueFactory<>("frecuencia_obtenida"));
        colFrec_esp.setCellValueFactory(new PropertyValueFactory<>("frecuencia_esperada"));
        col3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        col4.setCellValueFactory(new PropertyValueFactory<>("col4"));

        tblTabla.getColumns().addAll(colIntervalo, colFrec_obt, colFrec_esp, col3, col4);

        tblTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    @FXML
    private void handleButtonProbar(ActionEvent event) {
    }
    
    @FXML
    private void handleButtonReiniciar(ActionEvent event) {
    }


    @FXML
    private void handleRdbUniforme(ActionEvent event) {
        lblAoMedia.setText("A");
        lblBoDesviacion.setText("B");
        txtAoMedia.setDisable(false);
        txtBoDesviacion.setDisable(false);
        txtLambda.setDisable(true);
    }

    @FXML
    private void handleRdbPoisson(ActionEvent event) {
        txtAoMedia.setDisable(true);
        txtBoDesviacion.setDisable(true);
        txtLambda.setDisable(false);
    }

    @FXML
    private void handleRdbExponencial(ActionEvent event) {
        txtAoMedia.setDisable(true);
        txtBoDesviacion.setDisable(true);
        txtLambda.setDisable(false);
    }

    @FXML
    private void handleRdbNormal(ActionEvent event) {
        lblAoMedia.setText("Media");
        lblBoDesviacion.setText("Desviacion");
        txtAoMedia.setDisable(false);
        txtBoDesviacion.setDisable(false);
        txtLambda.setDisable(true);
    }
  
}
