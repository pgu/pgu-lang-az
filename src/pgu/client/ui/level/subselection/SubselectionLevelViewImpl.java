package pgu.client.ui.level.subselection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SubselectionLevelViewImpl extends Composite implements SubselectionLevelView {

    private static SubselectionLevelViewImplUiBinder uiBinder = GWT.create(SubselectionLevelViewImplUiBinder.class);

    interface SubselectionLevelViewImplUiBinder extends UiBinder<Widget, SubselectionLevelViewImpl> {
    }

    public SubselectionLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(final Presenter presenter) {
    }

}
