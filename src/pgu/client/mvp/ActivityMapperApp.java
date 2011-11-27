package pgu.client.mvp;

import pgu.client.activity.GameActivity;
import pgu.client.activity.WelcomeActivity;
import pgu.client.place.GamePlace;
import pgu.client.place.WelcomePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ActivityMapperApp implements ActivityMapper {

    @Inject
    private Provider<WelcomeActivity> welcomeActivityProvider;
    @Inject
    private Provider<GameActivity> gameActivityProvider;

    @Override
    public Activity getActivity(final Place place) {

        if (place instanceof WelcomePlace) {
            return welcomeActivityProvider.get();
        }

        if (place instanceof GamePlace) {
            return gameActivityProvider.get();
        }

        throw new IllegalArgumentException("Unknown place : " + place);
    }

}
