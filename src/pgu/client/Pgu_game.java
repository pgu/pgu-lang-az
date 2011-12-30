package pgu.client;

import pgu.client.gin.GameGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Pgu_game implements EntryPoint {

    public static GameConfig gameConfig = new GameConfig();

    @Override
    public void onModuleLoad() {

        final GameGinjector ginjector = GWT.create(GameGinjector.class);

        final SimplePanel displayContent = ginjector.getWidget();
        ginjector.getActivityManager().setDisplay(displayContent);

        final RootPanel rootPanel = RootPanel.get();
        rootPanel.add(displayContent);

        ginjector.getPlaceHistoryHandler().handleCurrentHistory();
    }

}
