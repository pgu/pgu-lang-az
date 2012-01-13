package pgu.client.ui.level.theme;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ThemeLevelViewImpl extends Composite implements ThemeLevelView {

    private static ThemeLevelViewImplUiBinder uiBinder = GWT.create(ThemeLevelViewImplUiBinder.class);

    interface ThemeLevelViewImplUiBinder extends UiBinder<Widget, ThemeLevelViewImpl> {
    }

    public ThemeLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setPresenter(final Presenter presenter) {
    }
}
