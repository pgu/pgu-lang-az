package pgu.client.place;

import static pgu.client.place.PlaceHelper.LANGUAGE;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LanguageLevelPlace extends Place {

    private final Language language;

    public LanguageLevelPlace(final Language language) {
        this.language = language;
    }

    public static class Tokenizer implements PlaceTokenizer<LanguageLevelPlace> {

        @Override
        public String getToken(final LanguageLevelPlace place) {
            return new PlaceHelper() //
                    .put(LANGUAGE, place.language.label()) //
                    .getToken();

        }

        @Override
        public LanguageLevelPlace getPlace(final String token) {
            final PlaceHelper placeHelper = new PlaceHelper(token);

            return new LanguageLevelPlace( //
                    LabelHelper.fromLanguage(placeHelper.get(LANGUAGE)) //
            );
        }

    }

    public Language getLanguage() {
        return language;
    }

}
