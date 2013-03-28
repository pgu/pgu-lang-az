package pgu.client;

import java.util.ArrayList;

import pgu.client.enums.Language;

public class AppHelper {

    //    public native void unmask() /*-{
    //        $wnd.console.log('unmask');
    //		$doc.getElementById('mask').style.display = 'none';
    //    }-*/;

    //    public native void mask() /*-{
    //        $wnd.console.log('mask');
    //		$doc.getElementById('mask').style.display = 'inline';
    //    }-*/;

    public boolean areGameSettingsInvalid(final Language lg, final ArrayList<String> subSelections) {
        return lg == null //
                || subSelections.isEmpty();
    }

    public native void console(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;


}
