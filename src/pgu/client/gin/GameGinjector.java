package pgu.client.gin;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.SimplePanel;

@GinModules({ GameModule.class })
public interface GameGinjector extends Ginjector {

    SimplePanel getWidget();

    ActivityManager getActivityManager();

    PlaceHistoryHandler getPlaceHistoryHandler();

}
