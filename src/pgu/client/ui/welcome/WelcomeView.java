package pgu.client.ui.welcome;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface WelcomeView extends IsWidget {

    public interface Presenter {
        void goTo(Place place);

    }

    void setPresenter(Presenter presenter);

    void setCurrentLevel(String currentLevel);

    void onStop();

}
