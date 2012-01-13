package pgu.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SubselectionLevelPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<SubselectionLevelPlace> {

        @Override
        public String getToken(final SubselectionLevelPlace place) {
            return "";
        }

        @Override
        public SubselectionLevelPlace getPlace(final String token) {
            return new SubselectionLevelPlace();
        }

    }

}
