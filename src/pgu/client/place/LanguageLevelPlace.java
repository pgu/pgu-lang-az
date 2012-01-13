package pgu.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LanguageLevelPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<LanguageLevelPlace> {

        @Override
        public String getToken(final LanguageLevelPlace place) {
            return "";
        }

        @Override
        public LanguageLevelPlace getPlace(final String token) {
            return new LanguageLevelPlace();
        }

    }

}
