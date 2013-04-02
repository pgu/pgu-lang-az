package pgu.client.ui.welcome;

import java.util.ArrayList;

import pgu.client.enums.Language;

import com.google.gwt.user.client.ui.IsWidget;

public interface WelcomeView extends IsWidget {

    public interface Presenter {

        void goToGame();

        boolean isCurrentLanguage(Language otherLanguage);

        void fillRowOfSubSelections();

        void selectNewLanguage(Language newLanguage);

        void removeSubSelection(String subSelection);

        void addSubSelection(String subSelection);

    }

    void setPresenter(Presenter presenter);

    void setCurrentLevel(String currentLevel);

    void onStop();

    void fillRowOfSubSelections(ArrayList<String> availableLevels, ArrayList<String> subselections);

    void deselectLanguage(Language oldLanguage);

    void selectLanguage(Language newLanguage);

}
