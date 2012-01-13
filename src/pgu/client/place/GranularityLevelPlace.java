package pgu.client.place;

import static pgu.client.place.PlaceHelper.GRANULARITY;
import static pgu.client.place.PlaceHelper.LANGUAGE;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GranularityLevelPlace extends Place {

    private final Language language;
    private final LanguageGranularity granularity;

    public GranularityLevelPlace(final Language language, final LanguageGranularity granularity) {
        this.language = language;
        this.granularity = granularity;
    }

    public static class Tokenizer implements PlaceTokenizer<GranularityLevelPlace> {

        @Override
        public String getToken(final GranularityLevelPlace place) {
            return new PlaceHelper() //
                    .put(LANGUAGE, place.language.label()) //
                    .put(GRANULARITY, place.granularity.label()) //
                    .getToken();
        }

        @Override
        public GranularityLevelPlace getPlace(final String token) {
            final PlaceHelper placeHelper = new PlaceHelper(token);

            return new GranularityLevelPlace( //
                    LabelHelper.fromLanguage(placeHelper.get(LANGUAGE)), //
                    LabelHelper.fromGranularity(placeHelper.get(GRANULARITY)) //
            );
        }

    }

    public Language getLanguage() {
        return language;
    }

    public LanguageGranularity getGranularity() {
        return granularity;
    }

}
