package pgu.client.gin;

import pgu.client.mvp.ActivityMapperApp;
import pgu.client.mvp.PlaceHistoryMapperApp;
import pgu.client.place.WelcomePlace;
import pgu.client.ui.game.GameView;
import pgu.client.ui.game.GameViewImpl;
import pgu.client.ui.welcome.WelcomeView;
import pgu.client.ui.welcome.WelcomeViewImpl;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

public class GameModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(ActivityMapper.class).to(ActivityMapperApp.class).in(Singleton.class);
        bind(PlaceHistoryMapperApp.class).in(Singleton.class);

        bind(WelcomeView.class).to(WelcomeViewImpl.class).in(Singleton.class);
        bind(GameView.class).to(GameViewImpl.class).in(Singleton.class);
    }

    @Singleton
    @Provides
    ActivityManager getActivityManager(final ActivityMapper activityMapper, final EventBus eventBus) {
        return new ActivityManager(activityMapper, eventBus);
    }

    @Provides
    @Singleton
    public PlaceController getPlaceController(final EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Singleton
    @Provides
    PlaceHistoryHandler getPlaceHistoryHandler(final PlaceHistoryMapperApp historyMapper, final EventBus eventBus,
            final PlaceController placeController) {
        final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new WelcomePlace());
        return historyHandler;
    }

}
