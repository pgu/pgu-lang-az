package pgu.client;

import pgu.client.enums.Language;
import pgu.client.language.japanese.Hiragana;



public class AppHelper {

    //    public native void unmask() /*-{
    //        $wnd.console.log('unmask');
    //		$doc.getElementById('mask').style.display = 'none';
    //    }-*/;

    //    public native void mask() /*-{
    //        $wnd.console.log('mask');
    //		$doc.getElementById('mask').style.display = 'inline';
    //    }-*/;

    public boolean areInvalidGameSettings() {
        return gc().language() == null //
                || gc().subselections().isEmpty();
    }

    public native void console(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;

    public GameConfig gc() {
        return Pgu_lang_az.gameConfig;
    }

    public void resetGameSettings() {
        final String firstPartOfHiraga = Hiragana.INSTANCE.availableLevels().get(0);
        gc().language(Language.HIRAGANA);
        gc().subselections(firstPartOfHiraga);
    }

}
