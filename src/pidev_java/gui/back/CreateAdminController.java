/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pidev_java.entities.Admin;
import pidev_java.services.AdminService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CreateAdminController implements Initializable {

     ObservableList<String> ListType = FXCollections.
            observableArrayList("Admin des reclamations", "Admin des events", "Admin des emplois");
    ObservableList<String> ListEtat = FXCollections.
            observableArrayList("Active", "Inactive");
    
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField login;
    @FXML
    private TextField pass;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ChoiceBox<String> etat;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnReset;

    private boolean update;
    int id_admin;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setItems(ListType);
        etat.setValue("Active");
        etat.setItems(ListEtat);
    }    

    @FXML
    private void AjouterAdmin(ActionEvent event) {
        if(update == false){
        Admin a = new Admin();
        a.setNom(nom.getText());
        a.setPrenom(prenom.getText());
        a.setLogin(login.getText());
        a.setPass(pass.getText());
        a.setType(type.getSelectionModel().getSelectedItem());
        if(etat.getSelectionModel().getSelectedItem().equals("Active"))
        {
            a.setEtat(true);
        }else 
            a.setEtat(false);
        System.out.println("helooooooooooooooooooooo");
        AdminService service = new AdminService();
        service.add(a);
        
    
        }else {
            System.out.println("bbbbb "+id_admin);
            AdminService service = new AdminService();
            Admin a = service.find(id_admin);
            String OldType = a.getType();
            a.setId(id_admin);
            a.setNom(nom.getText());
        a.setPrenom(prenom.getText());
        a.setLogin(login.getText());
        a.setPass(pass.getText());
        a.setType(type.getSelectionModel().getSelectedItem());
        if(etat.getSelectionModel().getSelectedItem().equals("Active"))
        {
            a.setEtat(true);
        }else {
            a.setEtat(false);
        }
            System.out.println("aaaaaaa "+a);
            service.update(a, OldType);
            
            
        }
    }
    

    @FXML
    private void reset(ActionEvent event) {
        nom.setText("");
        prenom.setText("");
        login.setText("");
        pass.setText("");
        
        
    }
    
    void setTextField(Admin a ){
        System.out.println("aaaaaaaaa "+a.getId());
        id_admin=a.getId();
        nom.setText(a.getNom());
        prenom.setText(a.getPrenom());
        login.setText(a.getLogin());
        pass.setText(a.getPass());
        type.setValue(a.getType());
        if(a.isEtat() == true){
            etat.setValue("Active");
        }else etat.setValue("Inactive");
    }
    void setUpdate(boolean b) {
        this.update = b;

    }
}
