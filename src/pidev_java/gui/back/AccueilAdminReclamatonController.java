/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev_java.entities.Admin;
import pidev_java.entities.Reclamation;
import pidev_java.services.AdminReclamationService;
import pidev_java.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AccueilAdminReclamatonController implements Initializable {

    @FXML
    private ListView<VBox> listView;
    @FXML
    private Label titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(Admin.getInstance().getId());
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        ReclamationService service = new ReclamationService();
        AdminReclamationService ars = new AdminReclamationService();
        listReclamation = service.getAllNonApprouve();

        for (Reclamation reclamation : listReclamation) {
            
            
            Label owner = new Label("Créer par : ");
            Label txt_owner = new Label(reclamation.getNom_utilisateur());
            HBox hboxowner = new HBox(owner,txt_owner);
            //hboxowner.getChildren().addAll(owner,txt_owner);
            
            
            Label type = new Label("Type de reclamation : ");
            Label txt_type = new Label(reclamation.getType());
            HBox hboxtype = new HBox(type,txt_type);
            //hboxtype.getChildren().addAll(type,txt_type);
            
            
            Label text = new Label("Texte reclamtion : ");
            Label txt_text = new Label(reclamation.getTexte_reclamation());
            HBox hboxtexte = new HBox(text,txt_text);
            //hboxtexte.getChildren().addAll(text,txt_text);
            
            
            Label date = new Label("Date reclamation : ");
            int year = Integer.parseInt(reclamation.getDate_reclamation().substring(0, 4));
            int month = Integer.parseInt(reclamation.getDate_reclamation().substring(5, 7));
            int day = Integer.parseInt(reclamation.getDate_reclamation().substring(8, 10));
            LocalDate l = LocalDate.of(year, month, day);
            Label txt_date = new Label();
            if(Period.between(l,LocalDate.now()).getDays()<30){
             txt_date.setText(Period.between(l,LocalDate.now()).getDays()+" jours");
            }
            else {
            
                txt_date.setText(reclamation.getDate_reclamation());
            }
            HBox hboxdate = new HBox(date,txt_date);
            //hboxdate.getChildren().addAll(date,txt_date);
            
            //Button approuve = new Button("Approuver");
            FontAwesomeIconView approuve = new FontAwesomeIconView(FontAwesomeIcon.CHECK_CIRCLE_ALT);
            FontAwesomeIconView deletebtn = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
            deletebtn.setStyle(
                        " -fx-cursor: hand ;"
                        + "-glyph-size:28px;"
                        + "-fx-fill:#ff1744;"
                );
            approuve.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
            approuve.setOnMouseClicked((event) -> {
                //ars.Activate(reclamation, Admin.getInstance());
            });
            
            HBox buttons = new HBox(approuve,deletebtn);
            buttons.setSpacing(20);
            buttons.setAlignment(Pos.CENTER);
            VBox vbox1 = new VBox(hboxowner,hboxtype,hboxtexte,hboxdate);
            VBox vbox2 = new VBox(approuve,deletebtn);
            
            //vbox.getChildren().addAll(hboxowner,hboxtype,hboxtexte,hboxdate);
            vbox1.setSpacing(20);
            vbox1.autosize();
            vbox2.setSpacing(20);
            vbox2.setPadding(new Insets(0, 20, 0, 20));
            HBox HBOXTotale = new HBox(vbox1,vbox2);
            VBox totale = new VBox(HBOXTotale);
            listView.getItems().add(totale);
            
        }

    }

}
