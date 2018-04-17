package mv.workspace.style;

import static mv.MapViewerPropertyType.MV_MAPVIEW_PANE;

/**
 * This class lists all CSS style types for this application. These
 * are used by JavaFX to apply style properties to controls like
 * buttons, labels, and panes.

 * @author Richard McKenna
 * @version 1.0
 */
public class MapViewerStyle {
    public static final String EMPTY_TEXT = "";
    public static final int BUTTON_TAG_WIDTH = 75;

    // THESE CONSTANTS ARE FOR TYING THE PRESENTATION STYLE OF
    // THIS M3Workspace'S COMPONENTS TO A STYLE SHEET THAT IT USES
    // NOTE THAT FOUR CLASS STYLES ALREADY EXIST:
    // top_toolbar, toolbar, toolbar_text_button, toolbar_icon_button

    public static final String CLASS_MV_MAP_OCEAN = "mv_map_ocean";
    public static final String CLASS_MV_MAP_HEADER    = "mv_map_header";
    public static final String CLASS_MV_MAP_BOX       = "mv_map_box"; 
    public static final String CLASS_MV_MAP_PANE = "mv_map_pane";
     public static final String CLASS_MV_MAP_MOUSE = "mv_map_mouse";
     public static final String CLASS_MV_MAP_EXIT = "mv_map_exit";
    public static final String CLASS_MV_MAPVIEW_PANE = "mv_mapview_pane";
    
    public static final String CLASS_MV_BOX       = "mv_box"; 
   public static final String  CLASS_MV_BUTTON ="mv_button";
   public static final String CLASS_MV_PANE = "mv_pane";
   public static final String CLASS_MV_OUTER_MAP_PANE = "mv_outer_map_pane";
}