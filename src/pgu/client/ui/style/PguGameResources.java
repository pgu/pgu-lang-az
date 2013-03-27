package pgu.client.ui.style;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface PguGameResources extends ClientBundle {

    public static final PguGameResources INSTANCE = GWT.create(PguGameResources.class);

    @Source("pgu_game_gwt.css")
    Style style();

    // @Source("Logo.jpg")
    // ImageResource logo();

    public interface Style extends CssResource {
        String container();

        String cell();

        String detail();

        String separator();

        String cellSelected();

        String app_cell_text();
        String app_cell_int1();
        String app_cell_int2();
        String app_cell_intbg();
        String app_cell_intbg_selected();

        // ice
        String app_cell_extx_ice();
        String app_cell_ext_ice();
        String app_cell_med_ice();
        String app_cell_int_ice();
        String app_cell_intx_ice();

        String app_cell_int2_ice();
        String app_cell_intbg_ice();

        // fire
        String app_cell_extx_fire();
        String app_cell_ext_fire();
        String app_cell_med_fire();
        String app_cell_int_fire();
        String app_cell_intx_fire();

        String app_cell_int2_fire();
        String app_cell_intbg_fire();

        // green
        String app_cell_extx_green();
        String app_cell_ext_green();
        String app_cell_med_green();
        String app_cell_int_green();
        String app_cell_intx_green();

        String app_cell_int2_green();
        String app_cell_intbg_green();

        // green
        String app_cell_extx_blue();
        String app_cell_ext_blue();
        String app_cell_med_blue();
        String app_cell_int_blue();
        String app_cell_intx_blue();

        String app_cell_int2_blue();
        String app_cell_intbg_blue();

    }
}
