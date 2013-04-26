package pgu.linker;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.EmittedArtifact;
import com.google.gwt.core.ext.linker.EmittedArtifact.Visibility;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.LinkerOrder.Order;

@LinkerOrder(Order.POST)
public class OfflineLinker extends AbstractLinker {

    @Override
    public String getDescription() {
        return "HTML5 Offline linker";
    }

    @Override
    public ArtifactSet link(final TreeLogger logger, final LinkerContext context, final ArtifactSet artifacts)
            throws UnableToCompleteException {

        final ArtifactSet artifactSet = new ArtifactSet(artifacts);

        final StringBuilder sb = new StringBuilder("CACHE MANIFEST\n");

        for (final EmittedArtifact emitted : artifacts.find(EmittedArtifact.class)) {
            if (emitted.getVisibility() == Visibility.Private) {
                continue;
            }

            final String partialPath = emitted.getPartialPath();
            if (partialPath.endsWith("symbolMap")) {
                continue;
            }

            sb.append(partialPath).append("\n");
        }

        sb.append("pgu_lang_az.nocache.js\n");
        sb.append("../Pgu_lang_az.css\n");
        sb.append("\n");
        sb.append("NETWORK:\n");
        sb.append("http://www.google-analytics.com/ga.js\n");

        final EmittedArtifact manifest = emitString(logger, sb.toString(), "offline.manifest");
        artifactSet.add(manifest);

        return artifactSet;
    }




}
