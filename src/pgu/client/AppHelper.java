package pgu.client;

import java.util.ArrayList;
import java.util.Arrays;

import pgu.client.enums.Language;
import pgu.client.language.japanese.Hiragana;

import com.google.gwt.user.client.Cookies;



public class AppHelper {

    //    public native void unmask() /*-{
    //        $wnd.console.log('unmask');
    //		$doc.getElementById('mask').style.display = 'none';
    //    }-*/;

    //    public native void mask() /*-{
    //        $wnd.console.log('mask');
    //		$doc.getElementById('mask').style.display = 'inline';
    //    }-*/;

    private static GameConfig gameConfig = new GameConfig();

    public boolean areInvalidGameSettings() {
        return gc().language() == null //
                || gc().subselections().isEmpty();
    }

    public native void console(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;

    public GameConfig gc() {
        return gameConfig;
    }

    private static final String KEY_LG = "pgu_lang_az_language";
    private static final String KEY_SS = "pgu_lang_az_sub_selections";

    public void initGameSettings() {

        final String str_lgName = readLanguageOfGameSettings();
        final String str_lgSel = readSubSelectionsOfGameSettings();

        final Language gameLanguage = findLanguageFromName(str_lgName);
        final ArrayList<String> gameSubSelections = findSubSelections(str_lgSel);

        if (gameLanguage == null //
                || gameSubSelections.isEmpty()) {
            resetGameSettings();
        }

    }

    public void resetGameSettings() {
        final Language lg = Language.HIRAGANA;

        final String firstPartOfHiraga = Hiragana.INSTANCE.availableLevels().get(0);
        final ArrayList<String> subSelections = new ArrayList<String>();
        subSelections.add(firstPartOfHiraga);

        final String str_lgName = lg.label();
        final String str_lgSel = subSelections.toString();

        writeLanguageToGameSettings(str_lgName);
        writeSubSelectionsToGameSettings(str_lgSel);
    }

    private void writeLanguageToGameSettings(final String str_lgName) {

        if (supportsLocalStorage()) {
            writeToLocalStorage(KEY_LG, str_lgName);

        } else if (Cookies.isCookieEnabled()) {
            Cookies.setCookie(KEY_LG, str_lgName);

        } else {
            gc().str_lgName(str_lgName);
        }
    }

    private void writeSubSelectionsToGameSettings(final String str_lgSel) {

        if (supportsLocalStorage()) {
            writeToLocalStorage(KEY_SS, str_lgSel);

        } else if (Cookies.isCookieEnabled()) {
            Cookies.setCookie(KEY_SS, str_lgSel);

        } else {
            gc().str_lgSel(str_lgSel);
        }
    }

    private native void writeToLocalStorage(final String key, final String value) /*-{
        $wnd.localStorage[key] = value;
    }-*/;

    private String readLanguageOfGameSettings() {

        if (supportsLocalStorage()) {
            return readFromLocalStorage(KEY_LG);

        } else if (Cookies.isCookieEnabled()) {
            return Cookies.getCookie(KEY_LG);

        } else {
            return gc().str_lgName();
        }
    }

    private String readSubSelectionsOfGameSettings() {

        if (supportsLocalStorage()) {
            return readFromLocalStorage(KEY_SS);

        } else if (Cookies.isCookieEnabled()) {
            return Cookies.getCookie(KEY_SS);

        } else {
            return gc().str_lgSel();
        }
    }

    private Language findLanguageFromName(final String lgName) {
        for (final Language language : Language.values()) {
            if (language.label().equals(lgName)) {
                return language;
            }
        }
        return null;
    }

    /* visibility for tests */
    public ArrayList<String> findSubSelections(final String str_lgSel) {
        if (str_lgSel == null || //
                !str_lgSel.startsWith("[") || //
                !str_lgSel.endsWith("]") || //
                str_lgSel.length() < 3 //
                ) {
            return new ArrayList<String>();
        }

        final String tmp = str_lgSel.substring(1, str_lgSel.length() - 1);
        final String[] parts = tmp.split(", ");

        return new ArrayList<String>(Arrays.asList(parts));
    }

    private native String readFromLocalStorage(String key) /*-{
        return $wnd.localStorage[key] || '';
    }-*/;

    private native boolean supportsLocalStorage() /*-{
        return ('localStorage' in $wnd) && $wnd['localStorage'] !== null;
    }-*/;

}
