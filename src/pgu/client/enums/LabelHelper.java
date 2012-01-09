package pgu.client.enums;

import java.util.ArrayList;
import java.util.List;

public class LabelHelper {

    private LabelHelper() {
        throw new UnsupportedOperationException();
    }

    public static boolean is(final String label, final HasLabel hasLabel) {
        if (null == label || "".equals(label)) {
            return false;
        }

        return hasLabel.label().equalsIgnoreCase(label);
    }

    public static Theme fromTheme(final String label) {
        return fromLabel(label, Theme.values());
    }

    public static Language fromLanguage(final String label) {
        return fromLabel(label, Language.values());
    }

    public static LanguageGranularity fromGranularity(final String label) {
        return fromLabel(label, LanguageGranularity.values());
    }

    private static <E extends HasLabel> E fromLabel(final String label, final E[] hasLabels) {

        for (final E hasLabel : hasLabels) {
            if (hasLabel.label().equalsIgnoreCase(label)) {
                return hasLabel;
            }
        }

        return null;
    }

    public static List<String> labels(final HasLabel[] hasLabels) {
        final ArrayList<String> labels = new ArrayList<String>();
        for (final HasLabel hasLabel : hasLabels) {
            labels.add(hasLabel.label());
        }
        return labels;
    }

}