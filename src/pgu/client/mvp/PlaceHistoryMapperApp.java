package pgu.client.mvp;

import pgu.client.place.GamePlace;
import pgu.client.place.WelcomePlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ WelcomePlace.Tokenizer.class //
        , GamePlace.Tokenizer.class //
})
public interface PlaceHistoryMapperApp extends PlaceHistoryMapper {

}
