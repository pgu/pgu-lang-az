package pgu.client.ui.level.granularity;

import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface GranularityLevelView extends IsWidget {

    public interface Presenter {
        void goTo(Place place);

    }

    void setPresenter(Presenter presenter);

    void displayGranularities(LanguageGranularity granularity, Language language);

}
