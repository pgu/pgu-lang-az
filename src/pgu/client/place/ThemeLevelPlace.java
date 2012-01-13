package pgu.client.place;

import static pgu.client.place.PlaceHelper.GRANULARITY;
import static pgu.client.place.PlaceHelper.LANGUAGE;
import static pgu.client.place.PlaceHelper.THEME;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ThemeLevelPlace extends Place {

    private final Language language;
    private final LanguageGranularity granularity;
    private final Theme theme;

    public ThemeLevelPlace(final Language language, //
            final LanguageGranularity granularity, //
            final Theme theme) {
        this.language = language;
        this.granularity = granularity;
        this.theme = theme;
    }

    public static class Tokenizer implements PlaceTokenizer<ThemeLevelPlace> {

        @Override
        public String getToken(final ThemeLevelPlace place) {
            return new PlaceHelper() //
                    .put(LANGUAGE, place.language.label()) //
                    .put(GRANULARITY, place.granularity.label()) //
                    .put(THEME, place.theme.label()) //
                    .getToken();
        }

        @Override
        public ThemeLevelPlace getPlace(final String token) {
            final PlaceHelper placeHelper = new PlaceHelper(token);

            return new ThemeLevelPlace( //
                    LabelHelper.fromLanguage(placeHelper.get(LANGUAGE)), //
                    LabelHelper.fromGranularity(placeHelper.get(GRANULARITY)), //
                    LabelHelper.fromTheme(placeHelper.get(THEME)) //
            );
        }

    }

}
