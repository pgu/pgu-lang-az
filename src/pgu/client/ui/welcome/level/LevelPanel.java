package pgu.client.ui.welcome.level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pgu.client.Pgu_game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class LevelPanel extends Composite {

    private static LevelPanelUiBinder uiBinder = GWT.create(LevelPanelUiBinder.class);

    interface LevelPanelUiBinder extends UiBinder<Widget, LevelPanel> {
    }

    interface LevelPanelStyle extends CssResource {
        String cell();

        String detail();

        String separator();

    }

    @UiField
    LevelPanelStyle style;

    @UiField
    HorizontalPanel controls;
    @UiField
    HTMLPanel container, language, type, theme, subselection, overview;
    @UiField
    Button btnCancel, btnBack, btnOk;

    private final PopupPanel popup;

    private static final List<String> LANGUAGES = Arrays.asList("Japanese", "Chinese", "Russian", "Japanese2",
            "Chinese2", "Russian2");

    public LevelPanel(final PopupPanel popup) {
        this.popup = popup;

        initWidget(uiBinder.createAndBindUi(this));
        style.ensureInjected();

        int counter = 0;
        for (final String lg : LANGUAGES) {

            if (isDivClear(counter)) {
                final HTML divClear = new HTML();
                divClear.getElement().getStyle().setProperty("clear", "both");
                language.add(divClear);
                counter++;
            }

            final HTML cellLg = new HTML(lg);
            cellLg.getElement().addClassName(style.cell());
            language.add(cellLg);

            cellLg.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    overview.clear();
                    final HTML detailLg = new HTML(cellLg.getText());
                    detailLg.getElement().addClassName(style.detail());
                    overview.add(detailLg);

                    setVisiblePanel(type);
                    setVisibleButton(btnBack);
                }

            });

            counter++;
        }

        for (final String typeValue : TYPES) {

            final HTML cell = new HTML(typeValue);
            cell.getElement().addClassName(style.cell());
            type.add(cell);

            cell.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    final List<Widget> wToRemoves = new ArrayList<Widget>();
                    for (int i = 0; i < overview.getWidgetCount(); i++) {
                        if (i < IX_TYPE) {
                            continue;
                        }
                        wToRemoves.add(overview.getWidget(i));
                    }
                    for (final Widget wToRemove : wToRemoves) {
                        wToRemove.removeFromParent();
                    }
                    // /////////////////////////////////////

                    final HTML sep = new HTML(" > ");
                    sep.getElement().addClassName(style.separator());
                    overview.add(sep);

                    final HTML detailType = new HTML(cell.getText());
                    detailType.getElement().addClassName(style.detail());
                    overview.add(detailType);

                    setVisiblePanel(theme);
                    setVisibleButton(btnBack);

                    fillPanelTheme();
                }

            });
        }

    }

    private static final List<String> TYPES = Arrays.asList("Alphabet", "Words", "Sentences");
    private static final int NB_CELLS = 4;

    private static boolean isDivClear(final int counter) {
        return 0 != counter && counter % NB_CELLS == 0;
    }

    @UiHandler("btnOk")
    public void clickOk(final ClickEvent e) {
        popup.hide();
        Pgu_game.gameConfig //
                .language(((HTML) overview.getWidget(IX_LANGUAGE)).getText()) //
                .type(((HTML) overview.getWidget(IX_TYPE)).getText()) //
                .theme(((HTML) overview.getWidget(IX_THEME)).getText()) //
                .subselection(((HTML) overview.getWidget(IX_SUBSELECTION)).getText());
    }

    @UiHandler("btnCancel")
    public void clickCancel(final ClickEvent e) {
        popup.hide();
    }

    @UiHandler("btnBack")
    public void clickBack(final ClickEvent e) {
        popup.hide();
    }

    public void show() {
        final int w = Window.getClientWidth() - 100;
        final int h = Window.getClientHeight() - 100;

        controls.setWidth(w + "px");
        overview.setWidth(w + "px");

        container.setPixelSize(w, h);
        popup.setPixelSize(w, h);

        final int cellW = w / NB_CELLS - 5;
        final int cellH = h / (LANGUAGES.size() % 4) - 30;

        for (int i = 0; i < language.getWidgetCount(); i++) {
            if (isDivClear(i)) {
                continue;
            }

            language.getWidget(i).setPixelSize(cellW, cellH);
        }

        setVisiblePanel(language);

        popup.show();
        popup.center();
    }

    private void setVisibleButton(final Button... btns) {
        final List<Button> btnToShows = Arrays.asList(btns);

        final List<Button> btnToHides = new ArrayList<Button>();
        btnToHides.add(btnBack);
        btnToHides.add(btnOk);
        btnToHides.removeAll(btnToShows);

        for (final Button b : btnToHides) {
            b.setVisible(false);
        }
        for (final Button b : btnToShows) {
            b.setVisible(true);
        }
    }

    private void setVisiblePanel(final HTMLPanel panel) {
        final List<HTMLPanel> panels = Arrays.asList(language, type, theme, subselection);
        for (final HTMLPanel p : panels) {
            p.setVisible(p.equals(panel));
        }
    }

    private static final int IX_LANGUAGE = 0;
    private static final int IX_TYPE = 2;
    private static final int IX_THEME = 4;
    private static final int IX_SUBSELECTION = 6;

    private void fillPanelTheme() {
        final String language = ((HTML) overview.getWidget(IX_LANGUAGE)).getText();
        final String type = ((HTML) overview.getWidget(IX_TYPE)).getText();

        if ("japanese".equalsIgnoreCase(language)) {
            if ("alphabet".equalsIgnoreCase(type)) {
                final List<String> themes = Arrays.asList("Hiragana", "Katakana");

                for (final String t : themes) {

                    final HTML cell = new HTML(t);
                    cell.getElement().addClassName(style.cell());
                    theme.add(cell);

                    cell.addClickHandler(new ClickHandler() {

                        @Override
                        public void onClick(final ClickEvent event) {
                            final List<Widget> wToRemoves = new ArrayList<Widget>();
                            for (int i = 0; i < overview.getWidgetCount(); i++) {
                                if (i < IX_THEME) {
                                    continue;
                                }
                                wToRemoves.add(overview.getWidget(i));
                            }
                            for (final Widget wToRemove : wToRemoves) {
                                wToRemove.removeFromParent();
                            }
                            // /////////////////////////////////////

                            final HTML sep = new HTML(" > ");
                            sep.getElement().addClassName(style.separator());
                            overview.add(sep);

                            final HTML detail = new HTML(cell.getText());
                            detail.getElement().addClassName(style.detail());
                            overview.add(detail);

                            setVisiblePanel(subselection);
                            setVisibleButton(btnBack, btnOk);

                            fillPanelSubselection();
                        }

                    });

                }
            }
        }

    }

    private final Set<String> selectedLevels = new HashSet<String>();

    private void fillPanelSubselection() {
        selectedLevels.clear();
        final String theme = ((HTML) overview.getWidget(IX_THEME)).getText();
        if ("hiragana".equalsIgnoreCase(theme)) {
            final List<String> levels = Arrays.asList("0 -", "1 k", "2 s", "3 t", "4 n" //
                    , "5 h", "6 m", "7 y", "8 r", "9 w");

            for (final String level : levels) {
                final HTML cell = new HTML(level);
                cell.getElement().addClassName(style.cell());
                subselection.add(cell);

                cell.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(final ClickEvent event) {
                        selectedLevels.add(cell.getText());
                    }

                });

            }
        }
    }

}
