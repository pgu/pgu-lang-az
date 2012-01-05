package pgu.client;

import pgu.client.enums.Theme;
import pgu.client.gin.GameGinjector;
import pgu.client.language.Hiragana;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Pgu_game implements EntryPoint {

    public static GameConfig gameConfig = new GameConfig();

    static {
        gameConfig.language("japanese");
        gameConfig.theme(Theme.HIRAGANA);
        gameConfig.subselections().add(Hiragana.availableLevels().get(0));
    }

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
