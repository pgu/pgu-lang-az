package pgu.client.ui.game;

import pgu.client.utils.guava.HashBiMap;

import com.google.gwt.user.client.ui.IsWidget;

public interface GameView extends IsWidget {

    public interface Presenter {

        HashBiMap<String, String> getAvailableSymbols();

        void goToWelcomePage();

        void reload();

    }

    void setPresenter(Presenter presenter);

    void clicksOn(GameCell gameCell);

    void fillGridWithSymbols();

    void resize();

    void buildGridGame();

    void onStop();

    void startChrono();

}
