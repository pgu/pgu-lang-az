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

    private static String str_lgName;
    private static String str_lgSel;

    private boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public boolean areInvalidGameSettings() {
        return readLanguageOfGameSettings() == null //
                || readSubSelectionsOfGameSettings().isEmpty();
    }

    public native void console(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;

    private static final String KEY_LG = "pgu_lang_az_language";
    private static final String KEY_SS = "pgu_lang_az_sub_selections";

    public void initGameSettings() {

        if (areInvalidGameSettings()) {
            resetGameSettings();
        }

    }

    public void resetGameSettings() {
        final Language lg = Language.HIRAGANA;

        final ArrayList<String> subSelections = new ArrayList<String>();
        subSelections.add(Hiragana.INSTANCE.availableLevels().get(0));

        writeLanguageToGameSettings(lg);
        writeSubSelectionsToGameSettings(subSelections);
    }

    public void writeLanguageToGameSettings(final Language lg ) {

        final String str_lgName = lg.label();
        writeLanguageToGameSettingsInternal(str_lgName);
    }

    public void writeSubSelectionsToGameSettings(final ArrayList<String> subSelections) {

        final String str_lgSel = subSelections.toString();
        writeSubSelectionsToGameSettingsInternal(str_lgSel);
    }

    private void writeLanguageToGameSettingsInternal(final String str_lgName) {

        if (supportsLocalStorage()) {
            writeToLocalStorage(KEY_LG, str_lgName);

        } else if (Cookies.isCookieEnabled()) {
            Cookies.setCookie(KEY_LG, str_lgName);

        } else {
            AppHelper.str_lgName = str_lgName;
        }
    }

    private void writeSubSelectionsToGameSettingsInternal(final String str_lgSel) {

        if (supportsLocalStorage()) {
            writeToLocalStorage(KEY_SS, str_lgSel);

        } else if (Cookies.isCookieEnabled()) {
            Cookies.setCookie(KEY_SS, str_lgSel);

        } else {
            AppHelper.str_lgSel = str_lgSel;
        }
    }

    private native void writeToLocalStorage(final String key, final String value) /*-{
        $wnd.localStorage[key] = value;
    }-*/;

    private String readLanguageOfGameSettingsInternal() {

        if (supportsLocalStorage()) {
            return readFromLocalStorage(KEY_LG);

        } else if (Cookies.isCookieEnabled()) {
            return Cookies.getCookie(KEY_LG);

        } else {
            return str_lgName;
        }
    }

    private String readSubSelectionsOfGameSettingsInternal() {

        if (supportsLocalStorage()) {
            return readFromLocalStorage(KEY_SS);

        } else if (Cookies.isCookieEnabled()) {
            return Cookies.getCookie(KEY_SS);

        } else {
            return str_lgSel;
        }
    }

    public Language readLanguageOfGameSettings() {

        final String str_lgName = readLanguageOfGameSettingsInternal();

        for (final Language language : Language.values()) {
            if (language.label().equals(str_lgName)) {
                return language;
            }
        }
        return null;
    }

    /* visibility for tests */
    public ArrayList<String> readSubSelectionsOfGameSettings() {

        final String str_lgSel = readSubSelectionsOfGameSettingsInternal();

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
