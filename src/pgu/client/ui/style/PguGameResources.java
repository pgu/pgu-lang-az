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

    }
}
