package pgu.client.ui.level.theme;

import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface ThemeLevelView extends IsWidget {

    public interface Presenter {
        void goTo(Place place);

    }

    void setPresenter(Presenter presenter);

    void displayGranularities(Theme theme, LanguageGranularity granularity, Language language);

}
