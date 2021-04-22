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

            String reqFreelancer = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM freelancer GROUP BY mois;";
            String reqSociete = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM societe GROUP BY mois;";
            String reqReclamation = "SELECT DATE_FORMAT(date_reclamation,'%M') mois ,COUNT(*) count FROM reclamation GROUP BY mois;";
            String reqOffreEmploi = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM offre_emploi GROUP BY mois;";
            String reqOffreStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM offre_stage GROUP BY mois;";
            String reqDemandeEmploi = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM demande_emploi GROUP BY mois;";
            String reqDemandeStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM demande_stage GROUP BY mois;";

            //Statestique reclamation dans LineChart
            Statement stReclamation = cnx.createStatement();
            ResultSet rsReclamation = stReclamation.executeQuery(reqReclamation); 

            int Reclamationjanvier = 0;
            int Reclamationfevrier = 0;
            int Reclamationmars = 0;
            int Reclamationavril = 0;
            int Reclamationmai = 0;
            int Reclamationjuin = 0;
            int Reclamationjuillet = 0;
            int Reclamationaout = 0;
            int Reclamationseptemebre = 0;
            int Reclamationoctobre = 0;
            int Reclamationnovembre = 0;
            int Reclamationdecembre = 0;
            while (rsReclamation.next()) {
                if (rsReclamation.getString("mois").equals("January")){
                    Reclamationjanvier = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("February")){
                    Reclamationfevrier = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("March")){
                    Reclamationmars = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("April")){
                    Reclamationavril = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("Mai")){
                    Reclamationmai = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("June")){
                    Reclamationjuin = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("July")){
                    Reclamationjuillet = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("August")){
                    Reclamationaout = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("September")){
                    Reclamationseptemebre = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("October")){
                    Reclamationoctobre = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("November")){
                    Reclamationnovembre = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("December")){
                    Reclamationdecembre = rsReclamation.getInt("count");
                }
            }
            reclamation.getData().add(new XYChart.Data<>("Janvier", Reclamationjanvier));
            reclamation.getData().add(new XYChart.Data<>("Fevrier", Reclamationfevrier));
            reclamation.getData().add(new XYChart.Data<>("Mars", Reclamationmars));
            reclamation.getData().add(new XYChart.Data<>("Avril", Reclamationavril));
            reclamation.getData().add(new XYChart.Data<>("Mai", Reclamationmai));
            reclamation.getData().add(new XYChart.Data<>("Juin", Reclamationjuin));
            reclamation.getData().add(new XYChart.Data<>("Juillet", Reclamationjuillet));
            reclamation.getData().add(new XYChart.Data<>("Aout", Reclamationaout));
            reclamation.getData().add(new XYChart.Data<>("Septembre", Reclamationseptemebre));
            reclamation.getData().add(new XYChart.Data<>("Octobre", Reclamationoctobre));
            reclamation.getData().add(new XYChart.Data<>("Novembre", Reclamationnovembre));
            reclamation.getData().add(new XYChart.Data<>("Decembre", Reclamationdecembre));

            LineChartReclamation.getData().addAll(reclamation);
            rsReclamation.close();

            //Statestique freelancer et societe dans Barchart
            Statement stFreelancer = cnx.createStatement();
            ResultSet rsFreelancer = stFreelancer.executeQuery(reqFreelancer);

            int Freelancerjanvier = 0;
            int Freelancerfevrier = 0;
            int Freelancermars = 0;
            int Freelanceravril = 0;
            int Freelancermai = 0;
            int Freelancerjuin = 0;
            int Freelancerjuillet = 0;
            int Freelanceraout = 0;
            int Freelancerseptemebre = 0;
            int Freelanceroctobre = 0;
            int Freelancernovembre = 0;
            int Freelancerdecembre = 0;
            while (rsFreelancer.next()) {
                if (rsFreelancer.getString("mois").equals("January")){
                    Freelancerjanvier = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("February")){
                    Freelancerfevrier = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("March")){
                    Freelancermars = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("April")){
                    Freelanceravril = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("Mai")){
                    Freelancermai = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("June")){
                    Freelancerjuin = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("July")){
                    Freelancerjuillet = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("August")){
                    Freelanceraout = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("September")){
                    Freelancerseptemebre = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("October")){
                    Freelanceroctobre = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("November")){
                    Freelancernovembre = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("December")){
                    Freelancerdecembre = rsFreelancer.getInt("count");
                }
            }
            freelancer.getData().add(new XYChart.Data<>("Janvier", Freelancerjanvier));
            freelancer.getData().add(new XYChart.Data<>("Fevrier", Freelancerfevrier));
            freelancer.getData().add(new XYChart.Data<>("Mars", Freelancermars));
            freelancer.getData().add(new XYChart.Data<>("Avril", Freelanceravril));
            freelancer.getData().add(new XYChart.Data<>("Mai", Freelancermai));
            freelancer.getData().add(new XYChart.Data<>("Juin", Freelancerjuin));
            freelancer.getData().add(new XYChart.Data<>("Juillet", Freelancerjuillet));
            freelancer.getData().add(new XYChart.Data<>("Aout", Freelanceraout));
            freelancer.getData().add(new XYChart.Data<>("Septembre", Freelancerseptemebre));
            freelancer.getData().add(new XYChart.Data<>("Octobre", Freelanceroctobre));
            freelancer.getData().add(new XYChart.Data<>("Novembre", Freelancernovembre));
            freelancer.getData().add(new XYChart.Data<>("Decembre", Freelancerdecembre));

            rsFreelancer.close();

            Statement stSociete = cnx.createStatement();
            ResultSet rsSociete = stSociete.executeQuery(reqSociete);

//            int Societejanvier = 0;
//            int Societefevrier = 0;
//            int Societemars = 0;
//            int Societeavril = 0;
//            int Societemai = 0;
//            int Societejuin = 0;
//            int Societejuillet = 0;
//            int Societeaout = 0;
//            int Societeseptemebre = 0;
//            int Societeoctobre = 0;
//            int Societenovembre = 0;
//            int Societedecembre = 0;
//            while (rsSociete.next()) {
//                if (rsSociete.getString("mois").equals("January")){
//                    Societejanvier = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("February")){
//                    Societefevrier = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("March")){
//                    Societemars = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("April")){
//                    Societeavril = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("Mai")){
//                    Societemai = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("June")){
//                    Societejuin = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("July")){
//                    Societejuillet = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("August")){
//                    Societeaout = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("September")){
//                    Societeseptemebre = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("October")){
//                    Societeoctobre = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("November")){
//                    Societenovembre = rsSociete.getInt("count");
//                }
//                if (rsSociete.getString("mois").equals("December")){
//                    Societedecembre = rsSociete.getInt("count");
//                }
//            }
//            societe.getData().add(new XYChart.Data<>("Janvier", Societejanvier));
//            societe.getData().add(new XYChart.Data<>("Fevrier", Societefevrier));
//            societe.getData().add(new XYChart.Data<>("Mars", Societemars));
//            societe.getData().add(new XYChart.Data<>("Avril", Societeavril));
//            societe.getData().add(new XYChart.Data<>("Mai", Societemai));
//            societe.getData().add(new XYChart.Data<>("Juin", Societejuin));
//            societe.getData().add(new XYChart.Data<>("Juillet", Societejuillet));
//            societe.getData().add(new XYChart.Data<>("Aout", Societeaout));
//            societe.getData().add(new XYChart.Data<>("Septembre", Societeseptemebre));
//            societe.getData().add(new XYChart.Data<>("Octobre", Societeoctobre));
//            societe.getData().add(new XYChart.Data<>("Novembre", Societenovembre));
//            societe.getData().add(new XYChart.Data<>("Decembre", Societedecembre));
//
//            rsSociete.close();
//            BarChartUser.getData().addAll(freelancer, societe);
            BarChartUser.getData().addAll(freelancer);

            //Statesstique offre emploi et stage dans areachart
            Statement stOffreEmploi = cnx.createStatement();
            ResultSet rsOffreEmploi = stOffreEmploi.executeQuery(reqOffreEmploi);

            int OffreEmploijanvier = 0;
            int OffreEmploifevrier = 0;
            int OffreEmploimars = 0;
            int OffreEmploiavril = 0;
            int OffreEmploimai = 0;
            int OffreEmploijuin = 0;
            int OffreEmploijuillet = 0;
            int OffreEmploiaout = 0;
            int OffreEmploiseptemebre = 0;
            int OffreEmploioctobre = 0;
            int OffreEmploinovembre = 0;
            int OffreEmploidecembre = 0;
            while (rsOffreEmploi.next()) {
                if (rsOffreEmploi.getString("mois").equals("January")){
                    OffreEmploijanvier = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("February")){
                    OffreEmploifevrier = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("March")){
                    OffreEmploimars = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("April")){
                    OffreEmploiavril = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("Mai")){
                    OffreEmploimai = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("June")){
                    OffreEmploijuin = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("July")){
                    OffreEmploijuillet = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("August")){
                    OffreEmploiaout = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("September")){
                    OffreEmploiseptemebre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("October")){
                    OffreEmploioctobre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("November")){
                    OffreEmploinovembre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("December")){
                    OffreEmploidecembre = rsOffreEmploi.getInt("count");
                }
            }
            offreEmploi.getData().add(new XYChart.Data<>("Janvier", OffreEmploijanvier));
            offreEmploi.getData().add(new XYChart.Data<>("Fevrier", OffreEmploifevrier));
            offreEmploi.getData().add(new XYChart.Data<>("Mars", OffreEmploimars));
            offreEmploi.getData().add(new XYChart.Data<>("Avril", OffreEmploiavril));
            offreEmploi.getData().add(new XYChart.Data<>("Mai", OffreEmploimai));
            offreEmploi.getData().add(new XYChart.Data<>("Juin", OffreEmploijuin));
            offreEmploi.getData().add(new XYChart.Data<>("Juillet", OffreEmploijuillet));
            offreEmploi.getData().add(new XYChart.Data<>("Aout", OffreEmploiaout));
            offreEmploi.getData().add(new XYChart.Data<>("Septembre", OffreEmploiseptemebre));
            offreEmploi.getData().add(new XYChart.Data<>("Octobre", OffreEmploioctobre));
            offreEmploi.getData().add(new XYChart.Data<>("Novembre", OffreEmploinovembre));
            offreEmploi.getData().add(new XYChart.Data<>("Decembre", OffreEmploidecembre));

            rsOffreEmploi.close();

            Statement stOffreStage = cnx.createStatement();
            ResultSet rsOffreStage = stOffreStage.executeQuery(reqOffreStage);

            int OffreStagejanvier = 0;
            int OffreStagefevrier = 0;
            int OffreStagemars = 0;
            int OffreStageavril = 0;
            int OffreStagemai = 0;
            int OffreStagejuin = 0;
            int OffreStagejuillet = 0;
            int OffreStageaout = 0;
            int OffreStageseptemebre = 0;
            int OffreStageoctobre = 0;
            int OffreStagenovembre = 0;
            int OffreStagedecembre = 0;
            while (rsOffreStage.next()) {
                if (rsOffreStage.getString("mois").equals("January")){
                    OffreStagejanvier = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("February")){
                    OffreStagefevrier = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("March")){
                    OffreStagemars = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("April")){
                    OffreStageavril = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("Mai")){
                    OffreStagemai = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("June")){
                    OffreStagejuin = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("July")){
                    OffreStagejuillet = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("August")){
                    OffreStageaout = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("September")){
                    OffreStageseptemebre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("October")){
                    OffreStageoctobre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("November")){
                    OffreStagenovembre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("December")){
                    OffreStagedecembre = rsOffreStage.getInt("count");
                }
            }
            offreStage.getData().add(new XYChart.Data<>("Janvier", OffreStagejanvier));
            offreStage.getData().add(new XYChart.Data<>("Fevrier", OffreStagefevrier));
            offreStage.getData().add(new XYChart.Data<>("Mars", OffreStagemars));
            offreStage.getData().add(new XYChart.Data<>("Avril", OffreStageavril));
            offreStage.getData().add(new XYChart.Data<>("Mai", OffreStagemai));
            offreStage.getData().add(new XYChart.Data<>("Juin", OffreStagejuin));
            offreStage.getData().add(new XYChart.Data<>("Juillet", OffreStagejuillet));
            offreStage.getData().add(new XYChart.Data<>("Aout", OffreStageaout));
            offreStage.getData().add(new XYChart.Data<>("Septembre", OffreStageseptemebre));
            offreStage.getData().add(new XYChart.Data<>("Octobre", OffreStageoctobre));
            offreStage.getData().add(new XYChart.Data<>("Novembre", OffreStagenovembre));
            offreStage.getData().add(new XYChart.Data<>("Decembre", OffreStagedecembre));

            rsOffreStage.close();
            AriaChartOffre.getData().addAll(offreEmploi, offreStage);

            //Statesstique demande emploi et stage dans LineChart
            Statement stDemandeEmploi = cnx.createStatement();
            ResultSet rsDemandeEmploi = stDemandeEmploi.executeQuery(reqDemandeEmploi);

           int DemandeEmploijanvier = 0;
            int DemandeEmploifevrier = 0;
            int DemandeEmploimars = 0;
            int DemandeEmploiavril = 0;
            int DemandeEmploimai = 0;
            int DemandeEmploijuin = 0;
            int DemandeEmploijuillet = 0;
            int DemandeEmploiaout = 0;
            int DemandeEmploiseptemebre = 0;
            int DemandeEmploioctobre = 0;
            int DemandeEmploinovembre = 0;
            int DemandeEmploidecembre = 0;
            while (rsDemandeEmploi.next()) {
                if (rsDemandeEmploi.getString("mois").equals("January")){
                    DemandeEmploijanvier = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("February")){
                    DemandeEmploifevrier = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("March")){
                    DemandeEmploimars = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("April")){
                    DemandeEmploiavril = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("Mai")){
                    DemandeEmploimai = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("June")){
                    DemandeEmploijuin = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("July")){
                    DemandeEmploijuillet = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("August")){
                    DemandeEmploiaout = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("September")){
                    DemandeEmploiseptemebre = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("October")){
                    DemandeEmploioctobre = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("November")){
                    DemandeEmploinovembre = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("December")){
                    DemandeEmploidecembre = rsDemandeEmploi.getInt("count");
                }
            }
            demandeEmploi.getData().add(new XYChart.Data<>("Janvier", DemandeEmploijanvier));
            demandeEmploi.getData().add(new XYChart.Data<>("Fevrier", DemandeEmploifevrier));
            demandeEmploi.getData().add(new XYChart.Data<>("Mars", DemandeEmploimars));
            demandeEmploi.getData().add(new XYChart.Data<>("Avril", DemandeEmploiavril));
            demandeEmploi.getData().add(new XYChart.Data<>("Mai", DemandeEmploimai));
            demandeEmploi.getData().add(new XYChart.Data<>("Juin", DemandeEmploijuin));
            demandeEmploi.getData().add(new XYChart.Data<>("Juillet", DemandeEmploijuillet));
            demandeEmploi.getData().add(new XYChart.Data<>("Aout", DemandeEmploiaout));
            demandeEmploi.getData().add(new XYChart.Data<>("Septembre", DemandeEmploiseptemebre));
            demandeEmploi.getData().add(new XYChart.Data<>("Octobre", DemandeEmploioctobre));
            demandeEmploi.getData().add(new XYChart.Data<>("Novembre", DemandeEmploinovembre));
            demandeEmploi.getData().add(new XYChart.Data<>("Decembre", DemandeEmploidecembre));

            rsDemandeEmploi.close();

            Statement stDemandeStage = cnx.createStatement();
            ResultSet rsDemandeStage = stDemandeStage.executeQuery(reqDemandeStage);

            int DemandeStagejanvier = 0;
            int DemandeStagefevrier = 0;
            int DemandeStagemars = 0;
            int DemandeStageavril = 0;
            int DemandeStagemai = 0;
            int DemandeStagejuin = 0;
            int DemandeStagejuillet = 0;
            int DemandeStageaout = 0;
            int DemandeStageseptemebre = 0;
            int DemandeStageoctobre = 0;
            int DemandeStagenovembre = 0;
            int DemandeStagedecembre = 0;
            while (rsDemandeStage.next()) {
                if (rsDemandeStage.getString("mois").equals("January")){
                    DemandeStagejanvier = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("February")){
                    DemandeStagefevrier = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("March")){
                    DemandeStagemars = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("April")){
                    DemandeStageavril = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("Mai")){
                    DemandeStagemai = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("June")){
                    DemandeStagejuin = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("July")){
                    DemandeStagejuillet = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("August")){
                    DemandeStageaout = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("September")){
                    DemandeStageseptemebre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("October")){
                    DemandeStageoctobre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("November")){
                    DemandeStagenovembre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("December")){
                    DemandeStagedecembre = rsDemandeStage.getInt("count");
                }
            }
            demandeStage.getData().add(new XYChart.Data<>("Janvier", DemandeStagejanvier));
            demandeStage.getData().add(new XYChart.Data<>("Fevrier", DemandeStagefevrier));
            demandeStage.getData().add(new XYChart.Data<>("Mars", DemandeStagemars));
            demandeStage.getData().add(new XYChart.Data<>("Avril", DemandeStageavril));
            demandeStage.getData().add(new XYChart.Data<>("Mai", DemandeStagemai));
            demandeStage.getData().add(new XYChart.Data<>("Juin", DemandeStagejuin));
            demandeStage.getData().add(new XYChart.Data<>("Juillet", DemandeStagejuillet));
            demandeStage.getData().add(new XYChart.Data<>("Aout", DemandeStageaout));
            demandeStage.getData().add(new XYChart.Data<>("Septembre", DemandeStageseptemebre));
            demandeStage.getData().add(new XYChart.Data<>("Octobre", DemandeStageoctobre));
            demandeStage.getData().add(new XYChart.Data<>("Novembre", DemandeStagenovembre));
            demandeStage.getData().add(new XYChart.Data<>("Decembre", DemandeStagedecembre));

            rsDemandeStage.close();
            LineChartDemande.getData().addAll(demandeEmploi, demandeStage);

        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
