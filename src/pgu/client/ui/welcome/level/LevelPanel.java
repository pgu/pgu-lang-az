package pgu.client.ui.welcome.level;

import static pgu.client.enums.LabelHelper.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pgu.client.Pgu_game;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.language.Hiragana;
import pgu.client.language.RussianAlphabet;

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

        String cellSelected();

    }

    @UiField
    LevelPanelStyle style;

    @UiField
    HorizontalPanel controls;
    @UiField
    HTMLPanel container, language, granularity, theme, subselection, overview;
    @UiField
    Button btnCancel, btnBack, btnOk;
    @UiField
    HTML ovLanguage, ovGranularity, ovTheme;

    private final PopupPanel popup;

    public LevelPanel(final PopupPanel popup) {
        this.popup = popup;

        initWidget(uiBinder.createAndBindUi(this));
        style.ensureInjected();

        int counter = 0;
        for (final String lg : LabelHelper.labels(Language.values())) {

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
                    ovLanguage.setText(cellLg.getText());
                    ovGranularity.setText("");
                    ovTheme.setText("");

                    setVisiblePanel(granularity);
                    setVisibleButton(btnBack);
                }

            });

            counter++;
        }

        for (final LanguageGranularity granularityValue : LanguageGranularity.values()) {

            final HTML cell = new HTML(granularityValue.label());
            cell.getElement().addClassName(style.cell());
            granularity.add(cell);

            cell.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    ovGranularity.setText(cell.getText());
                    ovTheme.setText("");

                    if (isRussianAlphabet()) {
                        showSubselection();

                    } else {
                        showTheme();
                    }

                }
            });
        }
    }

    private boolean isRussianAlphabet() {
        if (is(ovLanguage.getText(), Language.RUSSIAN)) {
            if (is(ovGranularity.getText(), LanguageGranularity.ALPHABET)) {
                return true;
            }
        }
        return false;
    }

    private void showTheme() {
        setVisiblePanel(theme);
        setVisibleButton(btnBack);
        fillPanelTheme();
    }

    private static final int NB_CELLS = 4;

    private static boolean isDivClear(final int counter) {
        return 0 != counter && counter % NB_CELLS == 0;
    }

    @UiHandler("btnOk")
    public void clickOk(final ClickEvent e) {
        popup.hide();
        Pgu_game.gameConfig //
                .language(ovLanguage.getText()) //
                .granularity(ovGranularity.getText()) //
                .theme(ovTheme.getText());

        Pgu_game.gameConfig.subselections().clear();
        Pgu_game.gameConfig.subselections().addAll(selectedLevels);

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

        ovLanguage.setText("");
        ovGranularity.setText("");
        ovTheme.setText("");
        selectedLevels.clear();
        theme.clear();
        subselection.clear();
        btnBack.setVisible(false);
        btnOk.setVisible(false);

        final int w = Window.getClientWidth() - 100;
        final int h = Window.getClientHeight() - 100;

        controls.setWidth(w + "px");
        overview.setWidth(w + "px");

        container.setPixelSize(w, h);
        popup.setPixelSize(w, h);

        final int cellW = w / NB_CELLS - 5;
        final int cellH = h / (Language.values().length % 4) - 30;

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
        final List<HTMLPanel> panels = Arrays.asList(language, granularity, theme, subselection);
        for (final HTMLPanel p : panels) {
            p.setVisible(p.equals(panel));
        }
    }

    private void fillPanelTheme() {
        final String languageLabel = ovLanguage.getText();
        final String granularityLabel = ovGranularity.getText();

        if (is(languageLabel, Language.JAPANESE)) {
            if (is(granularityLabel, LanguageGranularity.ALPHABET)) {

                for (final Theme t : Arrays.asList(Theme.HIRAGANA, Theme.KATAKANA)) {

                    final HTML cell = new HTML(t.label());
                    cell.getElement().addClassName(style.cell());
                    theme.add(cell);

                    cell.addClickHandler(new ClickHandler() {

                        @Override
                        public void onClick(final ClickEvent event) {
                            ovTheme.setText(cell.getText());
                            // /////////////////////////////////////

                            showSubselection();
                        }

                    });

                }
            }
        }
    }

    private void showSubselection() {
        setVisiblePanel(subselection);
        setVisibleButton(btnBack, btnOk);

        fillPanelSubselection();
    }

    private final Set<String> selectedLevels = new HashSet<String>();

    private void fillPanelSubselection() {
        selectedLevels.clear();
        final String themeLabel = ovTheme.getText();

        if (is(themeLabel, Theme.HIRAGANA)) {
            buildLevels(Hiragana.availableLevels());
            return;
        }

        if (isRussianAlphabet()) {
            buildLevels(RussianAlphabet.availableLevels());
            return;
        }

    }

    private void buildLevels(final List<String> availableLevels) {
        for (final String level : availableLevels) {
            final HTML cell = new HTML(level);
            cell.getElement().addClassName(style.cell());
            cell.setWidth("100px");
            subselection.add(cell);

            cell.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    cell.getElement().addClassName(style.cellSelected());
                    selectedLevels.add(cell.getText());
                }

            });

        }
    }

}
