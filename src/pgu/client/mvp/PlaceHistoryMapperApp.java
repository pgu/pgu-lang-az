package pgu.client.mvp;

import pgu.client.place.GamePlace;
import pgu.client.place.GranularityLevelPlace;
import pgu.client.place.LanguageLevelPlace;
import pgu.client.place.SubselectionLevelPlace;
import pgu.client.place.ThemeLevelPlace;
import pgu.client.place.WelcomePlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ WelcomePlace.Tokenizer.class //
        , GamePlace.Tokenizer.class //
        , LanguageLevelPlace.Tokenizer.class //
        , GranularityLevelPlace.Tokenizer.class //
        , ThemeLevelPlace.Tokenizer.class //
        , SubselectionLevelPlace.Tokenizer.class //
})
public interface PlaceHistoryMapperApp extends PlaceHistoryMapper {

}
