package pgu.client;

import pgu.client.gin.GameGinjector;
import pgu.client.ui.style.PguGameResources;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Pgu_lang_az implements EntryPoint {

    //        gameConfig.size(GameSize.BIG);
    //        gameConfig.granularity(LanguageGranularity.ALPHABET);
    //        gameConfig.theme(Theme.HIRAGANA);

    private final AppHelper h = new AppHelper();

    @Override
    public void onModuleLoad() {

        h.initGameSettings();

        PguGameResources.INSTANCE.style().ensureInjected();

        final GameGinjector ginjector = GWT.create(GameGinjector.class);

        final SimplePanel displayContent = ginjector.getWidget();
        //        final Style style = displayContent.getElement().getStyle();
        //        style.setPosition(Position.ABSOLUTE);
        //        style.setTop(0, Unit.PX);
        //        style.setBottom(0, Unit.PX);
        //        style.setLeft(0, Unit.PX);
        //        style.setRight(0, Unit.PX);

        ginjector.getActivityManager().setDisplay(displayContent);

        //        final VerticalPanel vp = new VerticalPanel();
        //        vp.add(displayContent);
        //        vp.setWidth("100%");
        //        vp.setHeight(Window.getClientHeight() + "px");
        //        Window.addResizeHandler(new ResizeHandler() {
        //
        //            @Override
        //            public void onResize(final ResizeEvent event) {
        //                final int height = event.getHeight();
        //                vp.setHeight(height + "px");
        //            }
        //        });
        //        RootPanel.get().add(vp);

        final RootPanel rootPanel = RootPanel.get();
        rootPanel.add(displayContent);

        ginjector.getPlaceHistoryHandler().handleCurrentHistory();
    }

}
