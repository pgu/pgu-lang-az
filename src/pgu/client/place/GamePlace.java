package pgu.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GamePlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<GamePlace> {

        @Override
        public String getToken(final GamePlace place) {
            return "";
        }

        @Override
        public GamePlace getPlace(final String token) {
            return new GamePlace();
        }

    }

}
