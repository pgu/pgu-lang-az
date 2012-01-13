package pgu.client.place;

import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ThemeLevelPlace extends Place {

    public ThemeLevelPlace(final Language language, final LanguageGranularity fromGranularity, final Theme theme) {
    }

    public static class Tokenizer implements PlaceTokenizer<ThemeLevelPlace> {

        @Override
        public String getToken(final ThemeLevelPlace place) {
            return "";
        }

        @Override
        public ThemeLevelPlace getPlace(final String token) {
            return new ThemeLevelPlace(null, null, null);
        }

    }

}
