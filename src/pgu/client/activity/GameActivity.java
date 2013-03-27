package pgu.client.activity;

import java.util.ArrayList;

import pgu.client.enums.Language;
import pgu.client.language.japanese.Hiragana;
import pgu.client.mvp.HasPlace;
import pgu.client.place.GamePlace;
import pgu.client.place.WelcomePlace;
import pgu.client.ui.game.GameView;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class GameActivity extends AbstractActivity implements GameView.Presenter, HasPlace {

    @Inject
    private GameView view;
    @Inject
    private PlaceController placeController;

    private GamePlace place;
    private Language          lg;
    private ArrayList<String> subSelections = new ArrayList<String>();

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);

        lg = place.getLanguage();
        subSelections.addAll(place.getSubselections());

        if (areGameSettingsInvalid()) {

            console("game settings invalid");

            final String firstPartOfHiraga = Hiragana.INSTANCE.availableLevels().get(0);
            placeController.goTo(new WelcomePlace(Language.HIRAGANA, Lists.newArrayList(firstPartOfHiraga)));
            return;
        }

        console("game settings valid");

        panel.setWidget(view.asWidget());

        //        view.resize();
        //        view.buildGridGame();
        view.fillGridWithSymbols();
    }

    private boolean areGameSettingsInvalid() {
        return lg == null //
                || subSelections.isEmpty();
    }

    @Override
    public void goTo(final Place place) {
        placeController.goTo(place);
    }

    @Override
    public Activity place(final Place place) {
        this.place = (GamePlace) place;
        return this;
    }

    @Override
    public void onStop() {
        view.onStop();

        view = null;
        placeController = null;

        place = null;
        lg = null;
        subSelections = null;
    }

    @Override
    public HashBiMap<String, String> getAvailableSymbols() {
        return lg.getAlphabet().availableSymbols(subSelections);
    }

    private native void console(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;

}
