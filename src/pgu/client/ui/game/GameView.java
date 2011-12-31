package pgu.client.ui.game;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface GameView extends IsWidget {

    public interface Presenter {
        void goTo(Place place);

    }

    void setPresenter(Presenter presenter);

    void clicksOn(GameCell gameCell);

    void generateGame();

    void resize();

}
