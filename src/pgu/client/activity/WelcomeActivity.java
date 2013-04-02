package pgu.client.activity;

import java.util.ArrayList;

import pgu.client.AppHelper;
import pgu.client.enums.Language;
import pgu.client.mvp.HasPlace;
import pgu.client.place.GamePlace;
import pgu.client.place.WelcomePlace;
import pgu.client.ui.welcome.WelcomeView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class WelcomeActivity extends AbstractActivity implements WelcomeView.Presenter, HasPlace {

    @Inject
    private WelcomeView     view;
    @Inject
    private PlaceController placeController;

    private final AppHelper h = new AppHelper();

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);

        if (h.areInvalidGameSettings()) {

            h.resetGameSettings();
            placeController.goTo(new WelcomePlace());
            return;
        }

        final String currentLevel = "<div><p>" + h.readLanguageOfGameSettings().label() + "</p><p class=\"ellipsis_for_long_text\">"
                + h.readSubSelectionsOfGameSettings() + "</p></div>";
        view.setCurrentLevel(currentLevel);

        panel.setWidget(view.asWidget());
    }

    @Override
    public void goToGame() {
        if (h.areInvalidGameSettings()) {
            return;
        }

        placeController.goTo(new GamePlace(h.readLanguageOfGameSettings(), h.readSubSelectionsOfGameSettings()));
    }

    @Override
    public void onStop() {
        view.onStop();

        view = null;
        placeController = null;
    }

    @Override
    public Activity place(final Place place) {
        return this;
    }

    @Override
    public boolean isCurrentLanguage(final Language otherLanguage) {
        return h.readLanguageOfGameSettings() == otherLanguage;
    }

    @Override
    public void fillRowOfSubSelections() {
        view.fillRowOfSubSelections(h.readLanguageOfGameSettings().getAlphabet().availableLevels(), h.readSubSelectionsOfGameSettings());
    }

    @Override
    public void selectNewLanguage(final Language newLanguage) {
        final Language oldLanguage = h.readLanguageOfGameSettings();
        view.deselectLanguage(oldLanguage);

        h.writeLanguageToGameSettings(newLanguage);
        h.writeSubSelectionsToGameSettings(new ArrayList<String>());

        view.selectLanguage(newLanguage);

        fillRowOfSubSelections();
    }

    @Override
    public void removeSubSelection(final String subSelection) {
        final ArrayList<String> newSubSelections = h.readSubSelectionsOfGameSettings();
        newSubSelections.remove(subSelection);

        h.writeSubSelectionsToGameSettings(newSubSelections);
    }

    @Override
    public void addSubSelection(final String subSelection) {
        final ArrayList<String> newSubSelections = h.readSubSelectionsOfGameSettings();
        newSubSelections.add(subSelection);

        h.writeSubSelectionsToGameSettings(newSubSelections);
    }

}
