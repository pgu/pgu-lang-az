package pgu.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WelcomePlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<WelcomePlace> {

        @Override
        public String getToken(final WelcomePlace place) {
            return "";
        }

        @Override
        public WelcomePlace getPlace(final String token) {
            return new WelcomePlace();
        }

    }
}
