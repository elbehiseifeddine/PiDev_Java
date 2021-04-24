///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pidev_java.gui.evenement;
//
//import com.sothawo.mapjfx.Coordinate;
//import com.sothawo.mapjfx.MapView;
//import com.sothawo.mapjfx.Marker;
//import com.sothawo.mapjfx.event.MapViewEvent;
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.animation.Transition;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import javafx.stage.Window;
//import javafx.util.Duration;
//
///**
// * FXML Controller class
// *
// * @author ASUS
// */
//public class MapEController implements Initializable {
//
//    @FXML
//    private Button btnConf;
//    @FXML
//    private MapView map;
//
//    private AjoutEventController ej;
//      private Marker markerPlace;
//    private double lat=0;
//    private double lng=0;
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        map.initialize();
//
//        Coordinate center1 = new Coordinate(35.6262, 10.3989);
//        map.setCenter(center1);
//         markerPlace = Marker.createProvided(Marker.Provided.BLUE).setPosition(null).setVisible(
//                true);
//        
//
//        map.setZoom(7);
//        map.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
//            event.consume();
//            final Coordinate newPosition = event.getCoordinate();
//             if (markerPlace.getVisible()) {
//        final Coordinate oldPosition = markerPlace.getPosition();
//        if (oldPosition != null) {
//            animateClickMarker(oldPosition, newPosition);
//        } else {
//            markerPlace.setPosition(newPosition);
//            // adding can only be done after coordinate is set
//            map.addMarker(markerPlace);
//        }
//             }
//            
//            this.lat=newPosition.getLatitude();
//            this.lng=newPosition.getLongitude();
//            
//           
//          /*  Coordinate center2 = new Coordinate(35.6262, 10.3989);
//
//            Marker marke = Marker.createProvided(Marker.Provided.BLUE).setPosition(newPosition).setVisible(
//                    true);
//            System.out.println(newPosition);
//            map.addMarker(marke);*/
//      
//
//        });
//    }   
//    
//    public void init(AjoutEventController ajc){
//    this.ej=ajc;
//    
//}    
//
//private void animateClickMarker(Coordinate oldPosition, Coordinate newPosition) {
//    // animate the marker to the new position
//    final Transition transition = new Transition() {
//        private final Double oldPositionLongitude = oldPosition.getLongitude();
//        private final Double oldPositionLatitude = oldPosition.getLatitude();
//        private final double deltaLatitude = newPosition.getLatitude() - oldPositionLatitude;
//        private final double deltaLongitude = newPosition.getLongitude() - oldPositionLongitude;
//        {
//            setCycleDuration(Duration.seconds(1.0));
//            setOnFinished(evt -> markerPlace.setPosition(newPosition));
//        }
//        @Override
//        protected void interpolate(double v) {
//            final double latitude = oldPosition.getLatitude() + v * deltaLatitude;
//            final double longitude = oldPosition.getLongitude() + v * deltaLongitude;
//            markerPlace.setPosition(new Coordinate(latitude, longitude));
//        }
//    };
//    transition.play();
//}
//
//    @FXML
//    private void confirmerPlace(MouseEvent event) {
//        this.ej.setCoordnate(this.lat,this.lng);
//        
//         Window window =   ((Node)(event.getSource())).getScene().getWindow(); 
//            if (window instanceof Stage){
//                ((Stage) window).close();
//            }
//    }
//    
//}