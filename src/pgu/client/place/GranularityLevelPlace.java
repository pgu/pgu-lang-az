package pgu.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GranularityLevelPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<GranularityLevelPlace> {

        @Override
        public String getToken(final GranularityLevelPlace place) {
            return "";
        }

        @Override
        public GranularityLevelPlace getPlace(final String token) {
            return new GranularityLevelPlace();
        }

    }

}
