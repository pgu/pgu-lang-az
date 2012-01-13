package pgu.client.enums;

import java.util.ArrayList;

import pgu.client.utils.guava.Lists;

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

    public static <E extends HasLabel> ArrayList<String> labels(final ArrayList<E> hasLabels) {
        return labels(hasLabels.toArray(new HasLabel[hasLabels.size()]));
    }

    public static <E extends HasLabel> ArrayList<String> labels(final E[] hasLabels) {
        final ArrayList<String> labels = Lists.newArrayList();
        for (final E hasLabel : hasLabels) {
            labels.add(hasLabel.label());
        }
        return labels;
    }

}
