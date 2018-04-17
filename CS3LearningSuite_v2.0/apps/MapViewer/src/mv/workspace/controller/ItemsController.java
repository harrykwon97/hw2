package mv.workspace.controller;

import djf.ui.dialogs.AppDialogsFacade;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import mv.MapViewerApp;


/**
 *
 * @author McKillaGorilla
 */
public class ItemsController {
    MapViewerApp app;
    
    
    public ItemsController(MapViewerApp initApp) {
        app = initApp;
    }
    public void processMapMove() {
        
    }

    }
    
//    public void processRedoItems() {
//        ToDoData data = (ToDoData)app.getDataComponent();
//        if (data.isItemSelected() || data.areItemsSelected()) {
//            ArrayList<ToDoItemPrototype> itemsToRedo = new ArrayList(data.getSelectedItems());
//            RedoItem_Transaction transaction = new RedoItems_Transaction(app, itemsToRedo);
//            app.processTransaction(transaction);
//        }
//    }```````````````
//    
//    public void processUndoItems() {
//        ToDoData data = (ToDoData)app.getDataComponent();
//        if (data.isItemSelected() || data.areItemsSelected()) {
//            ArrayList<ToDoItemPrototype> itemsToUndo = new ArrayList(data.getSelectedItems());
//            UndoItem_Transaction transaction = new UndoItems_Transaction(app, itemsToUndo);
//            app.processTransaction(transaction);
//        }
//    }

