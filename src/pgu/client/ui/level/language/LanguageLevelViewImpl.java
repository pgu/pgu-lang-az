package pgu.client.ui.level.language;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LanguageLevelViewImpl extends Composite implements LanguageLevelView {

    private static LanguageLevelViewImplUiBinder uiBinder = GWT.create(LanguageLevelViewImplUiBinder.class);

    interface LanguageLevelViewImplUiBinder extends UiBinder<Widget, LanguageLevelViewImpl> {
    }

    public LanguageLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(final Presenter presenter) {
    }

}
