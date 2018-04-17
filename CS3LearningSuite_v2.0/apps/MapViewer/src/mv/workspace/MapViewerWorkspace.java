package mv.workspace;

import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import java.awt.Point;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import properties_manager.PropertiesManager;
import mv.MapViewerApp;
import static mv.MapViewerPropertyType.MV_FOOLPROOF_SETTINGS;
import static mv.MapViewerPropertyType.MV_ITEMS_PANE;
import static mv.MapViewerPropertyType.MV_ITEM_BUTTONS_PANE;
import static mv.MapViewerPropertyType.MV_LABEL;
import static mv.MapViewerPropertyType.MV_MAPVIEW_PANE;
import static mv.MapViewerPropertyType.MV_MAP_PANE;
import static mv.MapViewerPropertyType.MV_MOVE_DOWN_BUTTON;
import static mv.MapViewerPropertyType.MV_MOVE_LEFT_BUTTON;
import static mv.MapViewerPropertyType.MV_MOVE_RIGHT_BUTTON;
import static mv.MapViewerPropertyType.MV_MOVE_UP_BUTTON;
import static mv.MapViewerPropertyType.MV_PANE;
import static mv.MapViewerPropertyType.MV_RESET_LOCATION_BUTTON;
import static mv.MapViewerPropertyType.MV_RESET_ZOOM_BUTTON;
import static mv.MapViewerPropertyType.MV_ZOOM_IN_BUTTON;
import static mv.MapViewerPropertyType.MV_ZOOM_OUT_BUTTON;

import static mv.workspace.style.MapViewerStyle.CLASS_MV_BOX;
import static mv.workspace.style.MapViewerStyle.CLASS_MV_BUTTON;


import static mv.workspace.style.MapViewerStyle.CLASS_MV_MAP_HEADER;
import static mv.workspace.style.MapViewerStyle.CLASS_MV_MAP_OCEAN;
import mv.workspace.foolproof.MapViewerFoolproofDesign;

/**
 *
 * @author McKillaGorilla
 */
public class MapViewerWorkspace extends AppWorkspaceComponent {
 double orgSceneX, orgSceneY;
    double orgX, orgY;
    double layoutx, layouty;
      private double ax =1;
      private double ay = 1;
      private double cx;
       private double cy;
       double mx;
       double my;
        Scale newmapscale = new Scale();
         private double positionx;
         private double positiony;
        private double size = 0;
 double maxx = 0;
         double scalezoom =0;
             double minx = 0;
             double maxy = 0;
             double miny = 0;
             BorderPane outerMapPane;
             double viewportx, viewporty;
    public MapViewerWorkspace(MapViewerApp app) {
        super(app);

        // LAYOUT THE APP
        initLayout();
        initFoolproofDesign();
                }
        
  
    // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
    private void initLayout() {
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Pane  mapPane = new Pane();
        
        
        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder workspaceBuilder = app.getGUIModule().getNodesBuilder();

	// THIS IS WHERE WE'LL DRAW THE MAP
        
        VBox toolPane = workspaceBuilder.buildVBox(MV_PANE,               null,           null,   CLASS_MV_BOX, HAS_KEY_HANDLER,             FOCUS_TRAVERSABLE,      ENABLED);
        Label toolLabel = workspaceBuilder.buildLabel(MV_LABEL, toolPane,   null,   CLASS_MV_MAP_HEADER, HAS_KEY_HANDLER,       FOCUS_TRAVERSABLE,      ENABLED);
        
        VBox itemsPane              = workspaceBuilder.buildVBox(MV_ITEMS_PANE,                 toolPane,       null,  null, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        HBox itemButtonsPane        = workspaceBuilder.buildHBox(MV_ITEM_BUTTONS_PANE,          itemsPane,          null,   CLASS_MV_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button ResetButton = workspaceBuilder.buildIconButton(MV_RESET_ZOOM_BUTTON, itemButtonsPane, null, CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button PolygonButton = workspaceBuilder.buildIconButton(MV_RESET_LOCATION_BUTTON, itemButtonsPane, null,  CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button ZoomInButton = workspaceBuilder.buildIconButton(MV_ZOOM_IN_BUTTON, itemButtonsPane, null,  CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button ZoomOutButton = workspaceBuilder.buildIconButton(MV_ZOOM_OUT_BUTTON, itemButtonsPane, null,  CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        VBox seconditemsPane              = workspaceBuilder.buildVBox(MV_ITEMS_PANE,                 toolPane,       null,  null, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        HBox seconditemButtonsPane        = workspaceBuilder.buildHBox(MV_ITEM_BUTTONS_PANE,          seconditemsPane,          null,   CLASS_MV_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button MoveLeftButton = workspaceBuilder.buildIconButton(MV_MOVE_LEFT_BUTTON, seconditemButtonsPane, null, CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button MoveRightButton = workspaceBuilder.buildIconButton(MV_MOVE_RIGHT_BUTTON, seconditemButtonsPane, null,  CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button MoveUpButton = workspaceBuilder.buildIconButton(MV_MOVE_UP_BUTTON, seconditemButtonsPane, null,  CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button MoveDownButton = workspaceBuilder.buildIconButton(MV_MOVE_DOWN_BUTTON, seconditemButtonsPane, null,  CLASS_MV_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
          VBox webPane = workspaceBuilder.buildVBox(MV_MAPVIEW_PANE, toolPane, null, null, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
           WebView browser = new WebView();
       WebEngine webEngine = browser.getEngine();
        // AND THIS WILL BE USED TO CLIP THE MAP SO WE CAN ZOOM
        outerMapPane = new BorderPane();
        Rectangle clippingRectangle = new Rectangle();
        outerMapPane.setClip(clippingRectangle);        
        Pane clippedPane = new Pane();
        outerMapPane.setCenter(clippedPane);
        clippedPane.getChildren().add(mapPane);
        Rectangle ocean = new Rectangle();
        mapPane.getChildren().add(ocean);
        ocean.getStyleClass().add(CLASS_MV_MAP_OCEAN);
        app.getGUIModule().addGUINode(MV_MAP_PANE, mapPane);
        mapPane.minWidthProperty().bind(outerMapPane.widthProperty());
        mapPane.maxWidthProperty().bind(outerMapPane.widthProperty());
        mapPane.minHeightProperty().bind(outerMapPane.heightProperty());
        mapPane.maxHeightProperty().bind(outerMapPane.heightProperty());
        outerMapPane.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            clippingRectangle.setWidth(newValue.getWidth());
            clippingRectangle.setHeight(newValue.getHeight());
            ocean.setWidth(newValue.getHeight()*2);
            ocean.setHeight(newValue.getHeight());
            
        });
app.getFileModule().markAsEdited(false);
        
        DialogPane dialog= new DialogPane();
         dialog.setMaxWidth(400);
        dialog.setMaxHeight(200);
        dialog.setStyle("-fx-background-color: white");
       
        dialog.setContent(browser);
        double xscale = mapPane.getScaleX();
        double yscale = mapPane.getScaleY();

        String HTML_STRING =
                "<table>"
                        +"<tr>"
                            +"<td><b>Scale:</b></td>"
                            +"<td>" + newmapscale.getX()+"</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Width:</b></td>"
                            +"<td>" +outerMapPane.getWidth()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Height:</b></td>"
                            +"<td>" +outerMapPane.getHeight()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Width:</b></td>"
                            +"<td>" +mapPane.getWidth()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Height:</b></td>"
                            +"<td>" +mapPane.getHeight()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Mouse X:</b></td>"
                            +"<td>" +positionx+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Mouse Y:</b></td>"
                
                            +"<td>" +positiony+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Mouse Percent X:</b></td>"
                            +"<td>" +clippedPane.getWidth()/100  + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Mouse Percent Y:</b></td>"
                            +"<td>" +clippedPane.getHeight()/100 + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World ViewportX:</b></td>"
                            +"<td>" + viewportx + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World ViewportY:</b></td>"
                            +"<td>" + viewporty + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b># of Polygon Points:</b></td>"
                            +"<td>"  + size + "</td>"
                        +"</tr>"
                +"</table>";
        webEngine.loadContent(HTML_STRING);
        webPane.getChildren().addAll(dialog);
        workspace = new BorderPane();
	((BorderPane)workspace).setCenter(outerMapPane);
        ((BorderPane)workspace).setLeft(toolPane);
        
        

        ResetButton.setOnAction(e-> {
           newmapscale.setX(1);
            newmapscale.setY(1);
            mapPane.setTranslateX(1);
             mapPane.setTranslateY(1);
             newmapscale.setPivotX(0);
               newmapscale.setPivotY(0);
               mapPane.setScaleX(1);
            mapPane.setScaleY(1);
            mapPane.resizeRelocate(0, 0, 0, 0);
            updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
            app.getFileModule().markAsEdited(true);
            reset();
        });
        
         PolygonButton.setOnAction(e-> {
            
        
        double maxx = 0;
             double minx = 10000;
             double maxy = 0;
             double miny = 10000;
         for(int i = 1; i < mapPane.getChildren().size(); i++){
              Polygon p = (Polygon)(mapPane.getChildren().get(i));
             for(int k = 0; k < p.getPoints().size(); k++) {
                 double comp = p.getPoints().get(k);
                 if(k % 2 == 0 && minx >= comp) {
                         minx = comp;
                 }
                     else if(k % 2 == 0 && maxx <= comp) {
                         maxx = comp;
                     }
                 
                 else if(k % 2 == 1 && miny >= comp) {
                    
                         miny = comp;
                     }
                     else if(k % 2 == 1 && maxy <= comp) {
                         maxy = comp;
                     }
                      
                 }
             }

        double newmapwidth = (maxx - minx);
             double newmapheight = (maxy - miny);
             if(newmapwidth < newmapheight) {
                 double scaleheight = clippedPane.getHeight() * 0.8 / newmapheight;
                 mapPane.setScaleX(scaleheight);
                  mapPane.setScaleY(scaleheight);
                  newmapscale.setX(scaleheight);
                  newmapscale.setY(scaleheight);
                  mapPane.setTranslateX((mapPane.getWidth()/2 - (minx + newmapwidth/2)) * scaleheight);
                 mapPane.setTranslateY((mapPane.getHeight()/2 - (miny + newmapheight/2)) * scaleheight);
             }
             else {
                 double scalewidth = clippedPane.getWidth() * 0.8 / newmapwidth;
                 mapPane.setScaleX(scalewidth);
                  mapPane.setScaleY(scalewidth);
                   newmapscale.setX(scalewidth);
                  newmapscale.setY(scalewidth);
                  mapPane.setTranslateX((mapPane.getWidth()/2 - (minx + newmapwidth/2)) * scalewidth);
                 mapPane.setTranslateY((mapPane.getHeight()/2 - (miny + newmapheight/2)) * scalewidth);
             }
             updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
             app.getFileModule().markAsEdited(true);
         reset();        
         });
         
        
        ZoomInButton.setOnAction(e->{ 
        ax = mapPane.getScaleX();
        ay = mapPane.getScaleY();
        if(ax > 2) {
            
        }
        else {
            double xzoomin = ax * 2;
        double yzoomin = ay * 2;
//        newmapscale.setX(xzoomin);
//        newmapscale.setY(yzoomin);

newmapscale = new Scale();       
newmapscale.setPivotX(orgX);
        newmapscale.setPivotY(orgY);
newmapscale.setX(xzoomin);
newmapscale.setY(yzoomin);
mapPane.setScaleX(xzoomin);
        mapPane.setScaleY(yzoomin);
         mapPane.getTransforms().add(newmapscale);
        updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
        app.getFileModule().markAsEdited(true);
        viewportx = (mapPane.getWidth() - mapPane.getWidth()/xzoomin)/2 - mapPane.getTranslateX()/xzoomin;
        viewporty = (mapPane.getHeight() - mapPane.getHeight()/yzoomin)/2 - mapPane.getTranslateY()/yzoomin;
        }
        reset();
        });
         ZoomOutButton.setOnAction(e->{ 
        ax = mapPane.getScaleX();
        ay = mapPane.getScaleY();
        if(ax < 0.5) {
            
        }
        else {
              double xzoomout = ax * 0.5;
        double yzoomout = ay * 0.5;
        newmapscale = new Scale();
newmapscale.setX(xzoomout);
newmapscale.setY(yzoomout);
//            newmapscale.setX(xzoomout);
//        newmapscale.setY(yzoomout);
        newmapscale.setPivotX(orgX);
        newmapscale.setPivotY(orgY);
         mapPane.setScaleX(xzoomout);
        mapPane.setScaleY(yzoomout);
         mapPane.getTransforms().add(newmapscale);
          viewportx = (mapPane.getWidth() - mapPane.getWidth()/xzoomout)/2 - mapPane.getTranslateX()/xzoomout;
        viewporty = (mapPane.getHeight() - mapPane.getHeight()/yzoomout)/2 - mapPane.getTranslateY()/yzoomout;
        updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
        app.getFileModule().markAsEdited(true);
        reset();
        }
      
        });
         MoveUpButton.setOnAction(e-> {
             cx = mapPane.getLayoutX();
             cy = mapPane.getLayoutY();
             double movedup = cy + 200;
             mapPane.relocate(cx, movedup);
             updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
             app.getFileModule().markAsEdited(true);
             reset();
         });
          MoveDownButton.setOnAction(e-> {
             cx = mapPane.getLayoutX();
             cy = mapPane.getLayoutY();
             double movedup = cy - 200;
             mapPane.relocate(cx, movedup);
             updateWebView(outerMapPane,mapPane,  webEngine, clippedPane);
             app.getFileModule().markAsEdited(true);
             reset();
         });
          
           MoveLeftButton.setOnAction(e-> {
             cx = mapPane.getLayoutX();
             cy = mapPane.getLayoutY();
             double movedup = cx + 200;
             mapPane.relocate(movedup, cy);
             updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
             app.getFileModule().markAsEdited(true);
             reset();
         });
           MoveRightButton.setOnAction(e-> {
              cx = mapPane.getLayoutX();
             cy = mapPane.getLayoutY();
             double movedup = cx - 200;
             mapPane.relocate(movedup, cy);
             updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
             app.getFileModule().markAsEdited(true);
             reset();
         });

            mapPane.setOnMousePressed(e-> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgX = e.getX();
            orgY = e.getY();
            updateWebView(outerMapPane,mapPane,  webEngine, clippedPane);
            reset();
        });
             mapPane.setOnMouseReleased(e-> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgX = e.getX();
            orgY = e.getY();
            updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
            reset();
        });


    
             
   mapPane.setOnMouseDragged(e-> {
        ((Node)(e.getSource())).setCursor(Cursor.MOVE);
       mx = e.getX();
         my = e.getY();
         layoutx = mapPane.getLayoutX();
         layouty = mapPane.getLayoutY();
        ax = mapPane.getScaleX();
         ay = mapPane.getScaleY();

        double finalx = layoutx + (mx-orgX)*ax;
         double finaly = layouty + (my-orgY)*ay;
         mapPane.relocate(finalx, finaly);
         viewportx = (mapPane.getWidth() - mapPane.getWidth()/finalx)/2 - mapPane.getTranslateX()/finalx;
         viewporty = (mapPane.getHeight() - mapPane.getHeight()/finaly)/2 - mapPane.getTranslateY()/finaly;
        updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
app.getFileModule().markAsEdited(true);
reset();
   });
   
   mapPane.setOnMouseReleased(e-> {
    ((Node)(e.getSource())).setCursor(Cursor.DEFAULT);
           updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
    });
   mapPane.setOnMouseMoved(e-> {
       positionx = e.getX();
            positiony = e.getY();
           for(int k = 1; k < mapPane.getChildren().size(); k++){
                if(mapPane.getChildren().get(k).isHover()){
                    Polygon p = (Polygon)(mapPane.getChildren().get(k));
                    size = p.getPoints().size()/2;
                }
           }
           
            updateWebView(outerMapPane,mapPane,  webEngine, clippedPane);
            app.getFileModule().markAsEdited(true);
            reset();
    });
   
//   clippedPane.setOnMouseMoved(e->{
//       mx = e.get();
//   });
   

   
    mapPane.setOnMouseEntered(e-> {
         positionx = e.getX();
            positiony = e.getY();
            updateWebView(outerMapPane,mapPane,  webEngine, clippedPane);
    });
   
   ocean.setOnMouseMoved(e-> {
       positionx = e.getX();
            positiony = e.getY();
            if(ocean.contains(positionx, positiony)) {
               for(int k = 1; k < mapPane.getChildren().size(); k++) {
                     mapPane.getChildren().get(k).setStyle("-fx-fill: linear-gradient(FORESTGREEN, GREENYELLOW);");
                    size = 0;
                } 
                
            }

            updateWebView(outerMapPane, mapPane, webEngine, clippedPane);
            app.getFileModule().markAsEdited(true);
   });
  
   
   

    mapPane.setOnScroll(e-> {
            double deltaY = e.getDeltaY();
            if(deltaY < 0) {
                scalezoom = 0.5;
            }else {
                 scalezoom = 2;
            }
            ax = mapPane.getScaleX();
            ay = mapPane.getScaleY();
            if(ax < 0.5 || ax > 2){
                
            }
            else {
            double xposition = e.getX();
            double yposition = e.getY();

            newmapscale = new Scale();
                    newmapscale.setX(ax * scalezoom);
            newmapscale.setY(ay * scalezoom);
            newmapscale.setPivotX(xposition);
            newmapscale.setPivotY(yposition);
            
            mapPane.getTransforms().add(newmapscale);
 viewportx = (mapPane.getWidth() - mapPane.getWidth()/ax * scalezoom)/2 - mapPane.getTranslateX()/ax * scalezoom;
        viewporty = (mapPane.getHeight() - mapPane.getHeight()/ay * scalezoom)/2 - mapPane.getTranslateY()/ay * scalezoom;
            updateWebView(outerMapPane,mapPane,  webEngine, clippedPane);
            app.getFileModule().markAsEdited(true);
            e.consume();
            reset();
            }
            });

    
    }
    
    
    
    
    

    
    public void updateWebView(Pane outermapPane, Pane mapPane, WebEngine webEngine, Pane clippedPane) {
        String Data_STRING =
                "<table>"
                        +"<tr>"
                            +"<td><b>Scale:</b></td>"
                            +"<td>" + newmapscale.getX()+"</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Width:</b></td>"
                            +"<td>" +outermapPane.getWidth()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Height:</b></td>"
                            +"<td>" +outermapPane.getHeight()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Width:</b></td>"
                            +"<td>" +mapPane.getWidth()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Height:</b></td>"
                            +"<td>" +mapPane.getHeight()+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Mouse X:</b></td>"
                            +"<td>" +positionx+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World Mouse Y:</b></td>"
                            +"<td>" +positiony+ "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Mouse Percent X:</b></td>"
                            +"<td>" +clippedPane.getWidth()/100  + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>Viewport Mouse Percent Y:</b></td>"
                            +"<td>" +clippedPane.getHeight()/100  + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World ViewportX:</b></td>"
                            +"<td>" + viewportx + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b>World ViewportY:</b></td>"
                            +"<td>" + viewporty + "</td>"
                        +"</tr>"
                        +"<tr>"
                            +"<td><b># of Polygon Points:</b></td>"
                            +"<td>" +size+ "</td>"
                        +"</tr>"
                +"</table>";
        webEngine.loadContent(Data_STRING);
    }
    
    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(MV_FOOLPROOF_SETTINGS, 
                new MapViewerFoolproofDesign((MapViewerApp)app));
    }
    
//    public double getPolygon(Pane mapPane) {
//        
//         return size;
//    }
    
   
//     public boolean isMapReset() {
//         updateWebView(mapPane, outerMapPane, webEngine);
//        return(mapPane.getScaleX() == 1 && mapPane.getScaleY() == 1);
//    }
//     public boolean zoominstop() {
//         updateWebView(mapPane, outerMapPane, webEngine);
//         return(newScale.getX() > 2);
//         
//     }
//   
    
//    public double getPolygon(Pane mapPane) {
//        for(int i = 1; i < mapPane.getChildren().size(); i++) {
//            Polygon polygontolook = (Polygon) mapPane.getChildren().get(i);
//        if(polygontolook.isHover()) {
//            size = polygontolook.getPoints().size();
//        }
//        else {
//            size = 0;
//        }
//        }
//        return size/2;
//    }
    
   public boolean reset() {
       return ax == 1 && ay == 1;
   }
   
    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
        System.out.println("WORKSPACE REPONSE TO " + ke.getCharacter());
    }
}