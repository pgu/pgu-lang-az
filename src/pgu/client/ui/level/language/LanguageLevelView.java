package pgu.client.ui.level.language;

import pgu.client.enums.Language;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface LanguageLevelView extends IsWidget {

    public interface Presenter {
        void goTo(Place place);

    }

    void setPresenter(Presenter presenter);

    void displayLanguages(Language language);

}
