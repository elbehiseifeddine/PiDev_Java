/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.gui.back;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import pidev_java.entities.Commentaires;
import pidev_java.entities.Publications;
import pidev_java.services.CommentairesService;
import pidev_java.services.PublicationsService;

/**
 * FXML Controller class
 *
 * @author ksemt
 */
public class PublicationController implements Initializable {

    @FXML
    private TableView<Publications> table_pub;
    @FXML
    private TableColumn<Publications, Integer> col_id_pub;
    @FXML
    private TableColumn<Publications, String> col_desc_pub;
    @FXML
    private TableColumn<Publications, String> col_img_pub;
    @FXML
    private TableColumn<Publications, String> col_date_pub;
    @FXML
    private TableView<Commentaires> table_com;
    @FXML
    private TableColumn<Commentaires, Integer> col_id_com;
    @FXML
    private TableColumn<Commentaires, String> col_desc_com;
    @FXML
    private TableColumn<Commentaires, String> col_date_com;
    private TableColumn<Commentaires, Integer> col_pub_com;
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private BarChart<String, Integer> barchart2;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
        
        col_id_pub.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_desc_pub.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_img_pub.setCellValueFactory(new PropertyValueFactory<>("image"));
        col_date_pub.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
        
        col_id_com.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_desc_com.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_date_com.setCellValueFactory(new PropertyValueFactory<>("date_commentaire"));
        
        
        PublicationsService ser = new PublicationsService();
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        XYChart.Series<String,Integer> series2 = new XYChart.Series<>();
        ResultSet ps = ser.getNbCom();
        while(ps.next()){
            series.getData().add(new XYChart.Data<>(ps.getString(2), ps.getInt(1)));
        }
        barchart.getData().add(series);
        barchart.lookupAll(".default-color0.chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill: ff4d4d;"));
        ResultSet ps2 = ser.getNbPub();
        while(ps2.next()){
            series2.getData().add(new XYChart.Data<>(ps2.getString(2), ps2.getInt(1)));
        }
        barchart2.getData().add(series2);
        barchart2.lookupAll(".default-color0.chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill: ff4d4d;"));
        }
        catch (NullPointerException e){
            System.out.println("problem de connection = "+e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                table_pub.setItems(loadP());
                table_com.setItems(loadC());
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
    }   

    @FXML
    private void onEdit(MouseEvent event) {
    }


    @FXML
    private void SupprimerP() throws SQLException {
        Publications p = table_pub.getSelectionModel().getSelectedItem();
        
        PublicationsService ser = new PublicationsService();
        
        try{   
         ser.supprimer(p);
        }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};


            JOptionPane.showMessageDialog(null, "Publication suprimée !");

        loadP();
        
    }
    
    public ObservableList<Publications> loadP() throws SQLException {
         
        PublicationsService ser = new PublicationsService();
        ResultSet ps = ser.getPublication();
        ObservableList e = FXCollections.observableArrayList();
        
            while(ps.next()) {
            e.add(new Publications(
                  
                    ps.getInt("id"),
                    ps.getString("description"),
                    ps.getString("image"),
                    ps.getString("date_publication"),
                    ps.getInt("freelancer_id"),
                    ps.getInt("societe_id"),
                    ps.getString("esm"),
                    ps.getString("la9ab")
            ));
                    
        }
        table_pub.setItems(e);
        return e;
    }
    
    public ObservableList<Commentaires> loadC() throws SQLException {
         
        CommentairesService ser = new CommentairesService();
        ResultSet ps = ser.getComs();
        ObservableList e = FXCollections.observableArrayList();
        
            while(ps.next()) {
            e.add(new Commentaires(
                  
                    ps.getInt("id"),
                    ps.getString("description"),
                    ps.getString("date_com"),
                    ps.getInt("id_pub_id"),
                    ps.getInt("id_util_id"),
                    ps.getInt("societe_id"),
                    ps.getString("esm"),
                    ps.getString("la9ab")
                    
            ));
                    
        }
        table_com.setItems(e);
        return e;
    }

    @FXML
    private void supprimerC() throws SQLException {
        Commentaires c = table_com.getSelectionModel().getSelectedItem();
        
        CommentairesService ser = new CommentairesService();
        
     try{   
      ser.supprimer(c);
     }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);};
   
         
         JOptionPane.showMessageDialog(null, "commentaire suprimé !");
         
     loadC();
    }

    @FXML
    private void ExtrairePdf(ActionEvent event) throws DocumentException {
        Document doc = new Document();
        
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\ksemt\\Desktop\\Publications.pdf"));
            doc.open();
            
            Image img = Image.getInstance("C:\\Users\\ksemt\\Desktop\\rightjob\\PiDev_Java\\src\\pidev_java\\assets\\Logo complet (1).png");
            
            float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
            float documentHeight = doc.getPageSize().getHeight() - doc.topMargin() - doc.bottomMargin();
            img.scaleToFit(documentWidth, documentHeight);
            
            WritableImage image = barchart2.snapshot(new SnapshotParameters(), null);
            File file = new File("chart.png");
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            Image img2 = Image.getInstance("C:\\Users\\ksemt\\Desktop\\rightjob\\PiDev_Java\\chart.png");
            img2.scaleToFit(documentWidth, documentHeight);
            
            WritableImage image2 = barchart.snapshot(new SnapshotParameters(), null);
            File file2 = new File("chart2.png");
            ImageIO.write(SwingFXUtils.fromFXImage(image2, null), "png", file2);
            Image img3 = Image.getInstance("C:\\Users\\ksemt\\Desktop\\rightjob\\PiDev_Java\\chart2.png");
            img3.scaleToFit(documentWidth, documentHeight);

            doc.add(img);
            Font f=new Font(FontFamily.TIMES_ROMAN,50.0f,Font.UNDERLINE);
            
            Paragraph p = new Paragraph("Gestion des publications :",f); 
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            
            doc.add(img2);
            
            doc.newPage();
            
            doc.add(img3);
            
            
            doc.close();
            Desktop.getDesktop().open(new File ("C:\\Users\\ksemt\\Desktop\\Publications.pdf"));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
