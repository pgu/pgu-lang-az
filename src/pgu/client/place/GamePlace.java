package pgu.client.place;

import java.util.ArrayList;

import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.utils.guava.Lists;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GamePlace extends Place {

    private Language                language;
    private final ArrayList<String> subselections = Lists.newArrayList();

    public GamePlace(final Language language, //
            final ArrayList<String> subselections) {
        this.language = language;
        this.subselections.addAll(subselections);
    }

    public GamePlace() {
    }

    public Language getLanguage() {
        return language;
    }

    public ArrayList<String> getSubselections() {
        return subselections;
    }

    public static class Tokenizer implements PlaceTokenizer<GamePlace> {

        @Override
        public String getToken(final GamePlace place) {
            return new PlaceHelper() //
            .put(PlaceHelper.LANGUAGE, place.language) //
            .put(PlaceHelper.SUBSELECTIONS, place.subselections) //
            .getToken();
        }

        @Override
        public GamePlace getPlace(final String token) {
            final PlaceHelper placeHelper = new PlaceHelper(token);

            return new GamePlace( //
                    LabelHelper.fromLanguage(placeHelper.get(PlaceHelper.LANGUAGE)) //
                    , placeHelper.list(PlaceHelper.SUBSELECTIONS) //
                    );
        }

    }

}
