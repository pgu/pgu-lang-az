package pgu.client.mvp;

import pgu.client.activity.GameActivity;
import pgu.client.activity.GranularityLevelActivity;
import pgu.client.activity.LanguageLevelActivity;
import pgu.client.activity.SubselectionLevelActivity;
import pgu.client.activity.ThemeLevelActivity;
import pgu.client.activity.WelcomeActivity;
import pgu.client.place.GamePlace;
import pgu.client.place.GranularityLevelPlace;
import pgu.client.place.LanguageLevelPlace;
import pgu.client.place.SubselectionLevelPlace;
import pgu.client.place.ThemeLevelPlace;
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
    @Inject
    private Provider<LanguageLevelActivity> languageLevelActivityProvider;
    @Inject
    private Provider<GranularityLevelActivity> granularityLevelActivityProvider;
    @Inject
    private Provider<ThemeLevelActivity> themeLevelActivityProvider;
    @Inject
    private Provider<SubselectionLevelActivity> subselectionLevelActivityProvider;

    @Override
    public Activity getActivity(final Place place) {

        if (place instanceof WelcomePlace) {
            return welcomeActivityProvider.get().place(place);
        }

        if (place instanceof GamePlace) {
            return gameActivityProvider.get();
        }

        if (place instanceof LanguageLevelPlace) {
            return languageLevelActivityProvider.get().place(place);
        }

        if (place instanceof GranularityLevelPlace) {
            return granularityLevelActivityProvider.get().place(place);
        }

        if (place instanceof ThemeLevelPlace) {
            return themeLevelActivityProvider.get().place(place);
        }

        if (place instanceof SubselectionLevelPlace) {
            return subselectionLevelActivityProvider.get().place(place);
        }

        throw new IllegalArgumentException("Unknown place : " + place);
    }

}
