package pgu.client.ui.level.granularity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class GranularityLevelViewImpl extends Composite implements GranularityLevelView {

    private static GranularityLevelViewImplUiBinder uiBinder = GWT.create(GranularityLevelViewImplUiBinder.class);

    interface GranularityLevelViewImplUiBinder extends UiBinder<Widget, GranularityLevelViewImpl> {
    }

    public GranularityLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(final Presenter presenter) {
    }

}
