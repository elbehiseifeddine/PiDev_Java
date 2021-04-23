/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.demandeEmploi;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import pidev_java.entities.DemandeEmploi;
import pidev_java.entities.Freelancer;
import pidev_java.services.DemandeEmploiService;

/**
 * FXML Controller class
 *
 * @author ely
 */
public class AjoutDemandeEController implements Initializable {

    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDomaine;
    @FXML
    private TextField tfDiplome;
    @FXML
    private TextArea tflettre;
    @FXML
    private TextField tfSalaire;
    @FXML
    private Button btnEnvoyerDemande;
    @FXML
    private Button btnFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void AjoutDemandeE(ActionEvent event)throws IOException  {
        
        Freelancer f = Freelancer.getInstance();
        DemandeEmploiService sd = new DemandeEmploiService();
        DemandeEmploi d = new DemandeEmploi();
        d.setFreelancer_id(f.getId());
        d.setDate_creation(Date.valueOf(LocalDate.MAX));
        d.setDescription(tfDescription.getText());
        d.setDiplome(tfDiplome.getText());
        d.setDomaine(tfDomaine.getText());
        d.setLettre(tflettre.getText());
        String s = tfSalaire.getText();
        d.setSalaire(Float.parseFloat(s));
       
        sd.ajouter(d);
        tfDescription.setText("");
        tfDiplome.setText("");
        tfDomaine.setText("");
        tflettre.setText("");
        tfSalaire.setText("");
        JOptionPane.showMessageDialog(null, "Demande Envoyer !");
        
    }
    
}
