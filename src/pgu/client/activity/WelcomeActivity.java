package pgu.client.activity;

import java.util.ArrayList;

import pgu.client.enums.Language;
import pgu.client.language.japanese.Hiragana;
import pgu.client.mvp.HasPlace;
import pgu.client.place.GamePlace;
import pgu.client.place.WelcomePlace;
import pgu.client.ui.welcome.WelcomeView;
import pgu.client.utils.guava.Lists;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class WelcomeActivity extends AbstractActivity implements WelcomeView.Presenter, HasPlace {

    @Inject
    private WelcomeView       view;
    @Inject
    private PlaceController   placeController;

    private WelcomePlace      place;
    private Language          lg;
    private ArrayList<String> subSelections;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);

        lg = place.getLanguage();
        subSelections.addAll(place.getSubselections());

        if (lg == null //
                || subSelections.isEmpty()) {

            final String firstPartOfHiraga = Hiragana.INSTANCE.availableLevels().get(0);
            placeController.goTo(new WelcomePlace(Language.HIRAGANA, Lists.newArrayList(firstPartOfHiraga)));
            return;
        }

        final String currentLevel = "<div><p>" + lg + "</p><p class=\"ellipsis_for_long_text\">" + subSelections
                + "</p></div>";
        view.setCurrentLevel(currentLevel);

        panel.setWidget(view.asWidget());
    }

    @Override
    public void goToGame() {
        placeController.goTo(new GamePlace(lg, subSelections));
    }

    @Override
    public void onStop() {
        view.onStop();

        view = null;
        placeController = null;
        place = null;
    }

    @Override
    public Activity place(final Place place) {
        this.place = (WelcomePlace) place;
        return this;
    }

    @Override
    public Language getLanguage() {
        return lg;
    }

    @Override
    public ArrayList<String> getSubSelections() {
        return subSelections;
    }

    @Override
    public void setLanguage(final Language newLanguage) {
        lg = newLanguage;
    }

}
