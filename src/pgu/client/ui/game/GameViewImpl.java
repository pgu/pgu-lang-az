package pgu.client.ui.game;

import java.util.List;
import java.util.Map.Entry;

import pgu.client.Pgu_game;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.language.HasBiSymbols;
import pgu.client.language.HasTriSymbols;
import pgu.client.language.chinese.ChineseWords;
import pgu.client.language.greek.GreekAlphabet;
import pgu.client.language.japanese.Hiragana;
import pgu.client.language.japanese.Katakana;
import pgu.client.language.russian.RussianAlphabet;
import pgu.client.place.WelcomePlace;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.HashTriMap;
import pgu.client.utils.guava.Lists;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameViewImpl extends Composite implements GameView {

    interface GameViewImplUiBinder extends UiBinder<Widget, GameViewImpl> {
    }

    private static GameViewImplUiBinder uiBinder = GWT.create(GameViewImplUiBinder.class);

    @UiField
    HTMLPanel menuArea, gridArea;

    @UiField
    HTMLPanel help, time, restart, exit;
    @UiField
    HTML helpText, timeText, restartText, exitText;

    public GameViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                resize();
            }
        });

    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("exitText")
    public void clickExit(final ClickEvent e) {
        presenter.goTo(new WelcomePlace());
    }

    @UiHandler("restartText")
    public void clickRestart(final ClickEvent e) {
        generateGame();
    }

    @Override
    public Widget asWidget() {
        return super.asWidget();
    }

    private static final int MENU_HEIGHT_PORTRAIT = 100;
    private static final int MENU_HEIGHT_LANDSCAPE = 50;

    private final static int W_HELP = 115;
    private final static int W_RESTART = 195;
    private final static int W_EXIT = 96;

    private boolean isPortrait_old = true;
    private boolean isPortrait = true;

    private int gridW = 0;
    private int gridH = 0;

    @Override
    public void resize() {
        GWT.log("resize....");

        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        isPortrait = w < h;

        final int hMenu = isPortrait ? MENU_HEIGHT_PORTRAIT : MENU_HEIGHT_LANDSCAPE;
        menuArea.setPixelSize(w, hMenu);
        gridArea.getElement().getStyle().setTop(hMenu, Unit.PX);

        gridW = w;
        gridArea.setWidth(gridW + "px");

        gridH = h - hMenu;
        gridArea.setHeight(gridH + "px");

        final int btnTop = isPortrait ? 25 : 0;

        final int hBtn = hMenu - 7 - btnTop;

        help.setPixelSize(W_HELP, hBtn);
        restart.setPixelSize(W_RESTART, hBtn);
        exit.setPixelSize(W_EXIT, hBtn);

        time.setPixelSize(w - 20 - (W_HELP + W_RESTART + W_EXIT), hBtn);

        final int marginTop = (hMenu - hBtn) / 2;
        help.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        restart.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        exit.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        time.getElement().getStyle().setMarginTop(marginTop, Unit.PX);

        displayGame();
    }

    private int counterIdxCell = 0;
    private int nbRows = 0;
    private int nbCells = 0;
    private int cellH = -10;
    private int cellW = -10;
    private GameCellFactory cellFactory;

    private void displayGame() {

        counterIdxCell = 0;

        nbRows = isPortrait ? 8 : 4;
        nbCells = isPortrait ? 4 : 8;

        cellH = gridH / nbRows;
        cellW = gridW / nbCells;
        cellFactory = new GameCellFactory(cellW, cellH, isPortrait, this);

        if (0 == gridArea.getWidgetCount()) {
            cells.clear();
            for (int i = 0; i < nbRows; i++) {
                gridArea.add(createRow());
            }
            return;
        }

        if (isPortrait_old != isPortrait) {
            cells.clear();
            gridArea.clear();
            for (int i = 0; i < nbRows; i++) {
                gridArea.add(createRow());
            }
            isPortrait_old = isPortrait;
            return;
        }

    }

    private FlowPanel createRow() {
        final FlowPanel row = new FlowPanel();
        for (int i = 0; i < nbCells; i++) {
            row.add(createCell());
            if (0 == i) {
                final Style styleRow = row.getElement().getStyle();
                styleRow.setMarginTop(10, Unit.PX);
                styleRow.setMarginLeft(10, Unit.PX);
            }
        }

        // clear floating
        final HTML divClearingFloat = new HTML();
        divClearingFloat.getElement().getStyle().setProperty("clear", "both");
        row.add(divClearingFloat);
        return row;
    }

    private final List<GameCell> cells = Lists.newArrayList();

    private GameCell createCell() {
        counterIdxCell++;
        final GameCell cell = new GameCell(cellFactory).index(counterIdxCell);
        cell.size();
        cells.add(cell);
        return cell;
    }

    private final List<Integer> occupiedSlots = Lists.newArrayList();
    private final List<Integer> availableSlots = Lists.newArrayList();
    private int counterFoundAssociations = 0;
    private HashBiMap<String, String> availableBiSymbols = null;
    private HashTriMap<String, String, String> availableTriSymbols = null;

    @Override
    public void generateGame() {
        GWT.log("generateGame...");

        for (final GameCell cell : cells) {
            cell.deselect();
        }

        occupiedSlots.clear();
        availableSlots.clear();
        for (int i = 0; i < Pgu_game.gameConfig.size().nbCells(); i++) {
            availableSlots.add(i);
        }

        fetchAvailableSymbols();
        fillCellsWithSymbols();

        counterFoundAssociations = 0;
    }

    private void fillCellsWithSymbols() {

        if (null != availableBiSymbols) {
            final List<Entry<String, String>> symbols = Lists.newArrayList(availableBiSymbols.entrySet());

            final int symbolsSize = symbols.size();
            final int iter = Pgu_game.gameConfig.size().nbCells() / 2;

            for (int i = 0; i < iter; i++) {

                final int indexSymbol = Random.nextInt(symbolsSize);
                final Entry<String, String> latin2extr = symbols.get(indexSymbol);

                final String latin = latin2extr.getKey();
                final String extr = latin2extr.getValue();

                final int indexLatin = getIndexSlot();
                final int indexExtr = getIndexSlot();

                final GameCell cellLatin = cells.get(indexLatin);
                cellLatin.setCharacter(latin);
                cellLatin.ice().setDefaultSkin();

                final GameCell cellExtr = cells.get(indexExtr);
                cellExtr.setCharacter(extr);
                cellExtr.green().setDefaultSkin();
            }
        } else {
            final List<HashTriMap.Entry<String, String, String>> symbols = availableTriSymbols.entryList();

            final int symbolsSize = symbols.size();
            final int iter = Pgu_game.gameConfig.size().nbCells() / 3;

            GWT.log("" + symbols);
            GWT.log("" + symbolsSize);
            GWT.log("" + iter);

        }
    }

    private void fetchAvailableSymbols() {
        HasBiSymbols hasBiSymbols = null;

        if (Theme.HIRAGANA == Pgu_game.gameConfig.theme()) {
            hasBiSymbols = Hiragana.INSTANCE;

        } else if (Theme.KATAKANA == Pgu_game.gameConfig.theme()) {
            hasBiSymbols = Katakana.INSTANCE;

        } else if (isRussianAlphabet()) {
            hasBiSymbols = RussianAlphabet.INSTANCE;

        } else if (isGreekAlphabet()) {
            hasBiSymbols = GreekAlphabet.INSTANCE;
        }

        if (null != hasBiSymbols) {
            availableBiSymbols = hasBiSymbols.availableSymbols(Pgu_game.gameConfig.subselections());
            availableTriSymbols = null;
        }

        HasTriSymbols hasTriSymbols = null;

        if (isChineseWords()) {
            hasTriSymbols = ChineseWords.INSTANCE;
        }

        if (null != hasTriSymbols) {
            availableTriSymbols = hasTriSymbols.availableSymbols(Pgu_game.gameConfig.subselections());
            availableBiSymbols = null;
        }

    }

    private boolean isChineseWords() {
        return Language.CHINESE == Pgu_game.gameConfig.language() //
                && LanguageGranularity.WORD == Pgu_game.gameConfig.granularity();
    }

    private boolean isGreekAlphabet() {
        return Language.GREEK == Pgu_game.gameConfig.language() //
                && LanguageGranularity.ALPHABET == Pgu_game.gameConfig.granularity();
    }

    private boolean isRussianAlphabet() {
        return Language.RUSSIAN == Pgu_game.gameConfig.language()//
                && LanguageGranularity.ALPHABET == Pgu_game.gameConfig.granularity();
    }

    private int getIndexSlot() {

        final int size = availableSlots.size();
        int metaIdx = Random.nextInt(size);
        int slotIdx = availableSlots.get(metaIdx);

        while (occupiedSlots.contains(slotIdx)) {
            metaIdx = Random.nextInt(size);
            slotIdx = availableSlots.get(metaIdx);
        }

        availableSlots.remove(metaIdx);
        occupiedSlots.add(slotIdx);
        return slotIdx;
    }

    private GameCell firstCell = null;

    @Override
    public void clicksOn(final GameCell cell) {

        if (null == firstCell) {
            firstCell = cell;
            return;
        }

        final String firstCharacter = firstCell.getCharacter();
        final String secondCharacter = cell.getCharacter();

        String matchCharacter;
        if (availableBiSymbols.containsKey(firstCharacter)) {
            matchCharacter = availableBiSymbols.get(firstCharacter);
        } else {
            matchCharacter = availableBiSymbols.inverse().get(firstCharacter);
        }

        if (!secondCharacter.equals(matchCharacter)) {
            firstCell.deselect();
            cell.deselect();
            firstCell = null;
            return;
        }

        firstCell = null;
        counterFoundAssociations++;
        if (NB_ASSOCIATIONS == counterFoundAssociations) {
            Window.alert("Congrat'!");
        }

    }

}
