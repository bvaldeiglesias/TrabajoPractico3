package controller;

import distribuciones.Exponencial;
import distribuciones.Normal;
import distribuciones.Poisson;
import distribuciones.Uniforme;
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
import generadoresPseudoAleatorios.GeneradorPoisson;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Toggle;
import generadoresPseudoAleatorios.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

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
    private double[] serieFe;
    
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
        txtCantNros.setDisable(true);
        txtCantIntervalos.setDisable(true);
        txtAoMedia.setDisable(true);
        txtBoDesviacion.setDisable(true);
        txtLambda.setDisable(true);
        btnReiniciar.setDisable(false);
        btnProbar.setDisable(true);
        
        int intervalos = Integer.parseInt(txtCantIntervalos.getText());
        for (int i = 3; i < 11; i++) {
            if (Integer.parseInt(txtCantIntervalos.getText()) > (10*i + 1)) {
                intervalos = 10*i + 1;
            }
        }
        boolean poisson = false;
        if (rdbUniforme.isSelected()) {
            int cantNros = Integer.parseInt(txtCantNros.getText());
            int a = Integer.parseInt(txtAoMedia.getText());
            int b = Integer.parseInt(txtBoDesviacion.getText());
            
            serie = new double[cantNros];
            GeneradorUniforme gnr = new GeneradorUniforme(a, b);
            
            for (int i = 0; i < cantNros; i++) {
                serie[i] = gnr.rnd();
                txfSerieGenerada.getItems().add(serie[i]);
            }
            
//            double sum = 0;
//            for (double d : serie) {
//                sum+=d;
//            }
            
//            double mediaAritmetica = sum/serie.length;
//            
//            Uniforme calculador = new Uniforme(mediaAritmetica, serie);
//            calculador.quickSort();
            serieFe = new double[intervalos];
            for (int i = 0; i < intervalos; i++) {
                //serieFe[i]=calculador.fe_densidad();
                serieFe[i]=cantNros/intervalos;
            }
            
   
            
        }
        if (rdbNormal.isSelected()) {
            int cantNros = Integer.parseInt(txtCantNros.getText());
            double media = Double.parseDouble(txtAoMedia.getText());
            double desviacion = Double.parseDouble(txtBoDesviacion.getText());

            serie = new double[cantNros];
            GeneradorBoxMuller gnr = new GeneradorBoxMuller(desviacion, media);

            for (int i = 0; i < cantNros; i++) {
                gnr.generarBoxMuller();
                if (i % 2 == 0) {
                    serie[i] = gnr.getN1();
                } else {
                    serie[i] = gnr.getN2();
                }
                txfSerieGenerada.getItems().add(serie[i]);
            }
            
            Normal calculador = new Normal(serie, intervalos, desviacion, media);
            calculador.marca_clase(intervalos);
            calculador.fmc();
            double[] aux = calculador.frec_esperada_x_intervalo();
            serieFe = new double[intervalos];
            for (int i = 0; i < intervalos; i++) {
                serieFe[i]=aux[i];
            }
        }
        if (rdbPoisson.isSelected()) {
            int cantNros = Integer.parseInt(txtCantNros.getText());
            int lambda = Integer.parseInt(txtLambda.getText());
            poisson = true;
            
            serie = new double[cantNros];
            GeneradorPoisson gnr = new GeneradorPoisson(lambda);
            
            for (int i = 0; i < cantNros; i++) {
                serie[i] = gnr.rnd();
                txfSerieGenerada.getItems().add(serie[i]);
            }
            
            Poisson calculador = new Poisson(serie, lambda, intervalos);
            double[] aux = calculador.frecuencia_esperada_intervalo();
            serieFe = new double[intervalos];
            for (int i = 0; i < intervalos; i++) {
                serieFe[i]=aux[i];
            }
            
        }
        if (rdbExponencial.isSelected()) {
            int cantNros = Integer.parseInt(txtCantNros.getText());
            int lambda = Integer.parseInt(txtLambda.getText());
            
            serie = new double[cantNros];
            GeneradorExponencial gnr = new GeneradorExponencial(lambda);
            
            for (int i = 0; i < cantNros; i++) {
                serie[i] = gnr.rnd();
                txfSerieGenerada.getItems().add(serie[i]);
            }
            
            Exponencial calculador = new Exponencial(lambda, serie, intervalos);
            calculador.prob_ocurrencia_teclado();
            double[] aux = calculador.frec_esperada_x_intervalo();
            serieFe = new double[intervalos];
            for (int i = 0; i < intervalos; i++) {
                serieFe[i]=aux[i];
            }
        }
        
        pruebaChi = new ChiCuadrado(serie, intervalos , 4);
        for (int i = 0; i < intervalos; i++) {
            pruebaChi.setFe(serieFe[i], i);
        }
        if (pruebaChi.hipotesis(poisson))
        {
            lblAceptaPrueba.setText("Se acepta hipotesis. " + pruebaChi.getR() + " < " + pruebaChi.getValueTablaChi() + " (Valor de tabla)");
        } else
        {
            lblAceptaPrueba.setText("Se rechaza hipotesis. " + pruebaChi.getR() + " > " + pruebaChi.getValueTablaChi() + " (Valor de tabla)");
        }
        
        
        
        
        
        //Parametros para definir escala de eje Y
        double unit = pruebaChi.getFrecuenciaEspMayor(1) / (double) 10;
        if (unit < 1)
        {
            unit = 1;
        }
        double upperLimit = pruebaChi.getFrecuenciaEspMayor(1) + unit + 5;

        // base bar chart
        chrtFrecuenciaC.setLegendVisible(false);
        chrtFrecuenciaC.setAnimated(false);
        chrtFrecuenciaC.setCategoryGap(10);

        // overlay line chart
        lnchrEsperadaC.setLegendVisible(false);
        lnchrEsperadaC.setAnimated(false);
        lnchrEsperadaC.setCreateSymbols(true);
        lnchrEsperadaC.setAlternativeRowFillVisible(false);
        lnchrEsperadaC.setAlternativeColumnFillVisible(false);
        lnchrEsperadaC.setHorizontalGridLinesVisible(false);
        lnchrEsperadaC.setVerticalGridLinesVisible(false);
        lnchrEsperadaC.getXAxis().setVisible(false);
        lnchrEsperadaC.getYAxis().setVisible(false);
        //Carga stylesheet a linechart
        lnchrEsperadaC.getStylesheets().addAll(getClass().getResource("/styles/chart.css").toExternalForm());

        //Sincronizar rango de eje Y de ambas graficas 
        yFrecBc.setAutoRanging(false);
        yFrecBc.setLowerBound(0);
        yFrecBc.setUpperBound(upperLimit);
        yFrecBc.setTickUnit(unit);

        yFrecLn.setAutoRanging(false);
        yFrecLn.setLowerBound(0);
        yFrecLn.setUpperBound(upperLimit);
        yFrecLn.setTickUnit(unit);

        //Cargar datos de distribucion de frecuencia en graficos
        XYChart.Series set1 = new XYChart.Series<>();
        for (int i = 0; i < pruebaChi.getK(); i++)
        {
            set1.getData().add(new XYChart.Data(pruebaChi.getIntervalo(i+1, poisson), pruebaChi.getFrecuenciaObs(i)));
        }
        chrtFrecuenciaC.getData().addAll(set1);

        XYChart.Series set2 = new XYChart.Series<>();
        for (int i = 0; i < pruebaChi.getK(); i++)
        {
            set2.getData().add(new XYChart.Data(pruebaChi.getIntervalo(i+1, poisson), pruebaChi.getFrecuenciaEsp(i)));
        }
        lnchrEsperadaC.getData().addAll(set2);
        
        //Relleno de la tabla
        final ObservableList<Row> data = FXCollections.observableArrayList();
        for (int i = 0; i < pruebaChi.getK(); i++) {
            String Frecuencia_observada = String.valueOf(pruebaChi.getFrecuenciaObs(i));
            String Frecuencia_esperada = String.valueOf(pruebaChi.getFrecuenciaEsp(i));
            String valueCol3 = pruebaChi.getPosTabla(3, i);
            String valueCol4 = pruebaChi.getPosTabla(4, i);
            data.add(new Row(pruebaChi.getIntervalo(i+1, poisson), Frecuencia_observada, Frecuencia_esperada, valueCol3, valueCol4));

        }
        tblTabla.getItems().addAll(data);
        lblSumatoriaChiCuadrado.setText(String.valueOf(pruebaChi.getR()));
 
    }
    
    @FXML
    private void handleButtonReiniciar(ActionEvent event) {
        btnProbar.setDisable(false);
        btnReiniciar.setDisable(true);
        txtCantIntervalos.setDisable(false);
        txtCantNros.setDisable(false);
        btnProbar.setDisable(false);

        txtCantNros.setText("");

        txtCantIntervalos.setText("");
        lblAceptaPrueba.setText("");
        lblSumatoriaChiCuadrado.setText("");
        
        pruebaChi = null;
        rndGenerator = null;
        //rnd = null;
        

        chrtFrecuenciaC.getData().clear();
        lnchrEsperadaC.getData().clear();
        
        txfSerieGenerada.getItems().clear();
        tblTabla.getItems().clear();
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
  
     public static class Row
    {

        private final SimpleStringProperty intervalo; 
        private final SimpleStringProperty frecuencia_obtenida;
        private final SimpleStringProperty frecuencia_esperada;
        private final SimpleStringProperty col3;
        private final SimpleStringProperty col4;

        private Row(String intervalo, String frecuencia_obtenida, String frecuencia_esperada, String col3, String col4)
        {
            this.intervalo = new SimpleStringProperty(intervalo);
            this.frecuencia_esperada = new SimpleStringProperty(frecuencia_esperada);
            this.frecuencia_obtenida = new SimpleStringProperty(frecuencia_obtenida);
            this.col3 =new SimpleStringProperty(col3);
            this.col4 =new SimpleStringProperty(col4);
        }

        public void setDesde(String asd)
        {
            intervalo.set(asd);
        }

        public void setFrec_esp(String asd)
        {
            frecuencia_esperada.set(asd);
        }

        public void setFrec_obt(String asd)
        {
            frecuencia_obtenida.set(asd);
        }

        public void setCol3(String asd)
        {
            col3.set(asd);
        }
        
        public void setCol4(String asd)
        {
            col4.set(asd);
        }

        public String getIntervalo()
        {
            return intervalo.get();
        }

        public String getFrecuencia_obtenida()
        {
            return frecuencia_obtenida.get();
        }

        public String getFrecuencia_esperada()
        {
            return frecuencia_esperada.get();
        }
        
        public String getCol3()
        {
            return col3.get();
        }
        public String getCol4()
        {
            return col4.get();
        }

    }

    
}
