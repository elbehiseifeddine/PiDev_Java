/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author seifeddine
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String rootname = "ReclamationAdmin";
        //Parent root = FXMLLoader.load(getClass().getResource("/pidev_java.gui/Main.fxml"));        
        //
        if (rootname.equals("main")) {
            Image img = new Image("pidev_java/assets/Logo_Compact.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/utilisateur/Main.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/bases/BaseGuiSuperAdmin.fxml"));

            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.getIcons().add(img);
            primaryStage.setTitle("RightJob");
            primaryStage.show();
        }
        else if(rootname.equals("baseSuperAdmin")){
            Image img = new Image("pidev_java/assets/Logo_Compact.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/bases/BaseGuiSuperAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(img);
            primaryStage.setTitle("RightJob");
            primaryStage.show();
        }
        else if(rootname.equals("EventAdmin")){
            Image img = new Image("pidev_java/assets/Logo_Compact.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/bases/BaseGuiEventAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(img);
            primaryStage.setTitle("RightJob");
            primaryStage.show();
        }
        else if(rootname.equals("ReclamationAdmin")){
            Image img = new Image("pidev_java/assets/Logo_Compact.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/bases/BaseGuiReclamationAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(img);
            primaryStage.setTitle("RightJob");
            primaryStage.show();
        }
        else if(rootname.equals("OffreAdmin")){
            Image img = new Image("pidev_java/assets/Logo_Compact.png");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/bases/BaseGuiOffreAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(img);
            primaryStage.setTitle("RightJob");
            primaryStage.show();
        }
        
        
        //Image img = new Image("pidev_java/assets/Logo_Compact.png");
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/utilisateur/Main.fxml"));

        //Parent root = loader.load();
        //Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        //primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        //primaryStage.getIcons().add(img);
        //primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
