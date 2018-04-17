package mv.data;

 

import djf.components.AppDataComponent;

import java.util.ArrayList;

import java.util.HashMap;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;

import javafx.scene.paint.CycleMethod;

import javafx.scene.paint.LinearGradient;

import javafx.scene.paint.Stop;

import javafx.scene.shape.Cylinder;

import javafx.scene.shape.Polygon;

import javafx.scene.shape.Rectangle;

import mv.MapViewerApp;

import static mv.MapViewerPropertyType.MV_MAP_PANE;

import java.awt.event.*; 

import java.awt.event.MouseEvent;
import javafx.scene.layout.BorderPane;
import static mv.MapViewerPropertyType.MV_OUTER_MAP_PANE;
import mv.workspace.MapViewerWorkspace;

/**

 *

 * @author McKillaGorilla

 */

public class MapViewerData implements AppDataComponent {

    // THE APP ITSELF

    MapViewerApp app;

 

    // THE PANE WHERE WE'RE PUTTING ALL THE POLYGONS

    Pane map;
    BorderPane outermappane;
  

    // THE POLYGONS

    int subregionId;
    double tX, tY, sX, sY;
    HashMap<Integer, ObservableList<Polygon>> subregions;
    final double DEFAULT_LINE_THICKNESS = 1.0;
 double positionx;
  double positiony;
double size;


    // LINE THICKNESS AT SCALE 1.0



 

    /**

     * Constructor can only be called after the workspace

     * has been initialized because it retrieves the map pane.

     */

    public MapViewerData(MapViewerApp initApp) {

        app = initApp;
        subregions = new HashMap();
        map = (Pane)app.getGUIModule().getGUINode(MV_MAP_PANE);
       outermappane = (BorderPane)app.getGUIModule().getGUINode(MV_OUTER_MAP_PANE);
    }    

  

  

    

    public ObservableList<Polygon> getSubregion(int id) {
        return subregions.get(id);

    }

    public BorderPane getoutermappane() {
        return outermappane;
    }


    public double getScaleX(){
        return sX;

    }

    public double getScaleY(){
        return sY;

    }
    
    public Pane getmapPane() {
        return map;
    }

    public void translate(double initTranslateX, double initTranslateY){
        tX = initTranslateX;
        tY = initTranslateY;
        map.setTranslateX(tX);
        map.setTranslateY(tY);
        map.relocate(tX, tY);
    }
    
    public void scale(double initScalex, double initScaley) {
        sX = initScalex;
        sY = initScaley;
        map.setScaleX(sX);
        map.setScaleY(sY);
    }
    
        public double getTransLateX(){
        return tX;

    }

    public double getTransLateY(){
        return tY;

    }

    @Override

    public void reset() {

        // CLEAR THE DATA

        subregions.clear();

        subregionId = 0;

        

        // AND THE POLYGONS THEMSELVES

        Rectangle ocean = (Rectangle)map.getChildren().get(0);

        map.getChildren().clear();

        map.getChildren().add(ocean);

    }

 public int polygonsize() {
     return subregions.size();
 }

    /**

     * For adding polygons to the map.

    */

   public void addSubregion(ArrayList<ArrayList<Double>> rawPolygons) {
        ObservableList<Polygon> subregionPolygons = FXCollections.observableArrayList();
        for (int i = 0; i < rawPolygons.size(); i++) {
            ArrayList<Double> rawPolygonPoints = rawPolygons.get(i);
            Polygon polygonToAdd = new Polygon();
            ObservableList<Double> transformedPolygonPoints = polygonToAdd.getPoints();
            for (int j = 0; j < rawPolygonPoints.size(); j+=2) {
                double longX = rawPolygonPoints.get(j);
                double latY = rawPolygonPoints.get(j+1);
                double x = longToX(longX);
                double y = latToY(latY);
                transformedPolygonPoints.addAll(x, y);
            }
            subregionPolygons.add(polygonToAdd);
            polygonToAdd.setFill(Color.CHARTREUSE);
            polygonToAdd.setStroke(Color.BLACK);
            polygonToAdd.setStrokeWidth(DEFAULT_LINE_THICKNESS);
            polygonToAdd.setUserData(subregionId);
            map.getChildren().add(polygonToAdd);
            subregions.put(subregionId, subregionPolygons);
            subregionId++;
            polygonToAdd.getStyleClass().add("-fx-fill: linear-gradient(FORESTGREEN, GREENYELLOW);");
            polygonToAdd.setOnMouseMoved(e-> {
                positionx = e.getX();
                positiony = e.getY();
                for(int k = 1; k < map.getChildren().size(); k++) {
                     if(map.getChildren().get(k).contains(positionx, positiony)) {
                     map.getChildren().get(k).setStyle("-fx-fill: linear-gradient(PINK, RED);");
                     size = polygonToAdd.getPoints().size();
                }
                else {
                    map.getChildren().get(k).setStyle("-fx-fill: linear-gradient(FORESTGREEN, GREENYELLOW);");
                    size = 0;
                }
                }
            });
          
        }
        
   }

 

    

    /**

     * This calculates and returns the x pixel value that corresponds to the

     * xCoord longitude argument.

     */

    private double longToX(double longCoord) {

        double paneHeight = map.getHeight();

        double unitDegree = paneHeight/180;

        double newLongCoord = (longCoord + 180) * unitDegree;
double paneWidth = map.getWidth();
        return newLongCoord;

    }

    

    public double returnxtolong(double mapX){

        double paneHeight = map.getHeight();

        double unitDegree = paneHeight/180;

        double longcoord = mapX/unitDegree - 180;

        

        return longcoord;

    }

    

    

    

    /**

     * This calculates and returns the y pixel value that corresponds to the

     * yCoord latitude argument.

     */

    private double latToY(double latCoord) {

        // DEFAULT WILL SCALE TO THE HEIGHT OF THE MAP PANE

        double paneHeight = map.getHeight();

        // WE ONLY WANT POSITIVE COORDINATES, SO SHIFT BY 90
//
        double unitDegree = paneHeight/180;

        double newLatCoord = (latCoord + 90) * unitDegree;

        return paneHeight - newLatCoord;

    }

    

    public double returnytolong(double mapcorY){
        double paneHeight = map.getHeight();
           double unitDegree = paneHeight/180;
        double latcoord = (paneHeight - mapcorY)/unitDegree - 90;
        return latcoord;
    }

   
    

}

