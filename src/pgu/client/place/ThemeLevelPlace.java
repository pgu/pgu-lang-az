package pgu.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ThemeLevelPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<ThemeLevelPlace> {

        @Override
        public String getToken(final ThemeLevelPlace place) {
            return "";
        }

        @Override
        public ThemeLevelPlace getPlace(final String token) {
            return new ThemeLevelPlace();
        }

    }

}
