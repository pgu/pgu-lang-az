package pgu.client.ui.welcome;

import java.util.ArrayList;

import pgu.client.enums.Language;

import com.google.gwt.user.client.ui.IsWidget;

public interface WelcomeView extends IsWidget {

    public interface Presenter {
        void goToGame();

        Language getLanguage();

        ArrayList<String> getSubSelections();

        void setLanguage(Language newLanguage);

    }

    void setPresenter(Presenter presenter);

    void setCurrentLevel(String currentLevel);

    void onStop();

}
