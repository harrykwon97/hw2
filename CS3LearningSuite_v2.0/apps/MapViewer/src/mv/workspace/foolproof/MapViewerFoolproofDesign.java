package mv.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.TextField;
import mv.MapViewerApp;
import static mv.MapViewerPropertyType.MV_RESET_LOCATION_BUTTON;
import static mv.MapViewerPropertyType.MV_RESET_ZOOM_BUTTON;
import static mv.MapViewerPropertyType.MV_ZOOM_IN_BUTTON;
import mv.data.MapViewerData;
import mv.files.MapViewerFiles;
import mv.workspace.MapViewerWorkspace;

/**
 *
 * @author McKillaGorilla
 */
public class MapViewerFoolproofDesign implements FoolproofDesign {
    MapViewerApp app;
//    MapViewerWorkspace work;
    
    public  MapViewerFoolproofDesign(MapViewerApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
       
        // CHECK AND SEE IF A TABLE ITEM IS SELECTED
        MapViewerData data = (MapViewerData)app.getDataComponent();
//        MapViewerWorkspace work = (MapViewerWorkspace)app.buildWorkspaceComponent(app);
        MapViewerFiles file = (MapViewerFiles)app.getFileComponent();
//        boolean mapIsreset = work.reset();
//        boolean zoominstop = data.zoominstop();
//           gui.getGUINode(MV_RESET_ZOOM_BUTTON).setDisable(mapIsreset);
//          gui.getGUINode(MV_ZOOM_IN_BUTTON).setDisable(!mapIsreset);
//        gui.getGUINode(MV_ZOOM_IN_BUTTON).setDisable(zoominstop);
//        gui.getGUINode(TDLM_MOVEUP_ITEM_BUTTON).setDisable(!(itemIsSelected || itemsAreSelected) || itemontop);
//        gui.getGUINode(TDLM_MOVEDOWN_ITEM_BUTTON).setDisable(!(itemIsSelected || itemsAreSelected) || itemonbottom);
//        ((TextField)gui.getGUINode(TDLM_OWNER_TEXT_FIELD)).setEditable(true);
    }
}