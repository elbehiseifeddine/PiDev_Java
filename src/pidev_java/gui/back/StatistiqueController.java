/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import pidev_java.utils.MaConnection;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class StatistiqueController implements Initializable {

        Connection cnx = MaConnection.getInstance().getCnx();

    @FXML
    private BarChart<?, ?> BarChartUser;
    @FXML
    private NumberAxis y_user;
    @FXML
    private CategoryAxis x_user;
    @FXML
    private LineChart<?, ?> LineChartReclamation;
    @FXML
    private NumberAxis y_reclamation;
    @FXML
    private CategoryAxis x_reclamation;
    @FXML
    private AreaChart<?, ?> AriaChartOffre;
    @FXML
    private NumberAxis y_offre;
    @FXML
    private CategoryAxis x_offre;
    @FXML
    private LineChart<?, ?> LineChartDemande;
    @FXML
    private NumberAxis y_demande;
    @FXML
    private CategoryAxis x_demande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                XYChart.Series freelancer = new XYChart.Series<>();
                XYChart.Series societe = new XYChart.Series<>();
                XYChart.Series reclamation = new XYChart.Series<>();
                XYChart.Series offreEmploi = new XYChart.Series<>();
                XYChart.Series offreStage = new XYChart.Series<>();
                XYChart.Series demandeEmploi = new XYChart.Series<>();
                XYChart.Series demandeStage = new XYChart.Series<>();
                
                String reqFreelancer = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count"
                        + " FROM freelancer "
                        + "GROUP BY mois ORDER BY mois ASC;";
                String reqSociete = "SELECT MONTH(date_creation) mois ,COUNT(*) count"
                        + " FROM societe "
                        + "GROUP BY mois ORDER BY mois ASC;";
                String reqReclamation = "SELECT DATE_FORMAT(date_reclamation,'%M') mois ,COUNT(*) count"
                        + " FROM reclamation "
                        + "GROUP BY mois ORDER BY mois ASC;";
                String reqOffreEmploi = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count"
                        + " FROM offre_emploi "
                        + "GROUP BY mois ORDER BY mois ASC;";
                String reqOffreStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count"
                        + " FROM offre_stage "
                        + "GROUP BY mois ORDER BY mois ASC;";
                String reqDemandeEmploi = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count"
                        + " FROM demande_emploi "
                        + "GROUP BY mois ORDER BY mois ASC;";
                String reqDemandeStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count"
                        + " FROM demande_stage "
                        + "GROUP BY mois ORDER BY mois ASC;";
               
                //Statestique reclamation dans LineChart
                Statement stReclamation = cnx.createStatement();
                ResultSet rsReclamation = stReclamation.executeQuery(reqReclamation);
                
                while(rsReclamation.next()){
                    reclamation.getData().add(new XYChart.Data<>(rsReclamation.getString("mois"), rsReclamation.getInt("count")));
                }
                LineChartReclamation.getData().addAll(reclamation);
                rsReclamation.close();
                
                
                //Statestique freelancer et societe dans Barchart
                Statement stFreelancer = cnx.createStatement();
                ResultSet rsFreelancer = stFreelancer.executeQuery(reqFreelancer);
                
                while(rsFreelancer.next()){
                    freelancer.getData().add(new XYChart.Data<>(rsFreelancer.getString("mois"), rsFreelancer.getInt("count")));
                }
                rsFreelancer.close();
                
                Statement stSociete = cnx.createStatement();
                ResultSet rsSociete = stSociete.executeQuery(reqSociete);
                
                while(rsSociete.next()){
                    societe.getData().add(new XYChart.Data<>(rsSociete.getString("mois"), rsSociete.getInt("count")));
                }
                rsSociete.close();
                BarChartUser.getData().addAll(freelancer,societe);
                
                //Statesstique offre emploi et stage dans areachart
                
                Statement stOffreEmploi = cnx.createStatement();
                ResultSet rsOffreEmploi = stOffreEmploi.executeQuery(reqOffreEmploi);
                
                while(rsOffreEmploi.next()){
                    offreEmploi.getData().add(new XYChart.Data<>(rsOffreEmploi.getString("mois"), rsOffreEmploi.getInt("count")));
                }
                rsOffreEmploi.close();
                
                Statement stOffreStage = cnx.createStatement();
                ResultSet rsOffreStage = stOffreStage.executeQuery(reqOffreStage);
                
                while(rsSociete.next()){
                    offreStage.getData().add(new XYChart.Data<>(rsOffreStage.getString("mois"), rsOffreStage.getInt("count")));
                }
                rsOffreStage.close();
                AriaChartOffre.getData().addAll(offreEmploi,offreStage);
                
                //Statesstique demande emploi et stage dans areachart
                
                Statement stDemandeEmploi = cnx.createStatement();
                ResultSet rsDemandeEmploi = stDemandeEmploi.executeQuery(reqDemandeEmploi);
                
                while(rsDemandeEmploi.next()){
                    demandeEmploi.getData().add(new XYChart.Data<>(rsDemandeEmploi.getString("mois"), rsDemandeEmploi.getInt("count")));
                }
                rsDemandeEmploi.close();
                
                Statement stDemandeStage = cnx.createStatement();
                ResultSet rsDemandeStage = stDemandeStage.executeQuery(reqDemandeStage);
                
                while(rsDemandeStage.next()){
                    demandeStage.getData().add(new XYChart.Data<>(rsDemandeStage.getString("mois"), rsDemandeStage.getInt("count")));
                }
                rsDemandeStage.close();
                LineChartDemande.getData().addAll(demandeEmploi,demandeStage);
                
                
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    
    
}
