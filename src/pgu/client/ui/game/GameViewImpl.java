package pgu.client.ui.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import pgu.client.GameConfig;
import pgu.client.Pgu_lang_az;
import pgu.client.enums.GameSize;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.language.HasBiSymbols;
import pgu.client.language.HasTriSymbols;
import pgu.client.language.greek.GreekAlphabet;
import pgu.client.language.japanese.Hiragana;
import pgu.client.language.japanese.Katakana;
import pgu.client.language.russian.RussianAlphabet;
import pgu.client.place.WelcomePlace;
import pgu.client.ui.game.GameCell.TuplePosition;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.HashTriMap;
import pgu.client.utils.guava.Lists;

import com.google.gwt.core.client.GWT;
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
        final GameConfig gc = Pgu_lang_az.gameConfig;
        presenter.goTo(new WelcomePlace(gc.language(), gc.subselections()));
    }

    @UiHandler("restartText")
    public void clickRestart(final ClickEvent e) {
        fillGridWithSymbols();
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

    private final boolean isPortrait_old = true;
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
    }

    private int counterIdxCell = 0;
    // private int nbRows = 0;
    // private int nbCellsByRow = 0;
    private int nbCellsOnBoard = 0;
    private int cellH = -10;
    private int cellW = -10;
    private GameCellFactory cellFactory;

    private enum NbCells {
        // ______________________width * height
        portrait_big(36), // ________4 * 9
        portrait_medium(24), // _____3 * 8
        portrait_small(18), // ______2 * 9
        landscape_big(36), // _______9 * 4
        landscape_medium(24), // ____6 * 4
        landscape_small(20, 18); // _5 * 4 = 20 => 18

        private int forBi;
        private int forTri;

        private NbCells(final int nbCellsForBiSymbols, final int nbCellsForTriSymbols) {
            if (!isMultipleOf2(nbCellsForBiSymbols)) {
                throw new IllegalArgumentException("Expected a number multiple by 2 but we got '" + nbCellsForBiSymbols
                        + "'");
            }
            if (!isMultipleOf3(nbCellsForTriSymbols)) {
                throw new IllegalArgumentException("Expected a number multiple by 3 but we got '"
                        + nbCellsForTriSymbols + "'");
            }

            forBi = nbCellsForBiSymbols;
            forTri = nbCellsForTriSymbols;
        }

        private NbCells(final int nbCells) {
            this(nbCells, nbCells);
        }

        private static boolean isMultipleOf3(final int nb) {
            return 0 == nb % 3;
        }

        private static boolean isMultipleOf2(final int nb) {
            return 0 == nb % 2;
        }

    }

    private static int getNbCellsOnBoard( //
            final boolean isPortrait, //
            final GameType gameType, //
            final GameSize gameSize) {

        if (GameType.BI == gameType) {

            if (GameSize.BIG == gameSize) {
                return isPortrait ? NbCells.portrait_big.forBi : NbCells.landscape_big.forBi;

            } else if (GameSize.MEDIUM == gameSize) {
                return isPortrait ? NbCells.portrait_medium.forBi : NbCells.landscape_medium.forBi;

            } else if (GameSize.SMALL == gameSize) {
                return isPortrait ? NbCells.portrait_small.forBi : NbCells.landscape_small.forBi;

            }

        } else if (GameType.TRI == gameType) {

            if (GameSize.BIG == gameSize) {
                return isPortrait ? NbCells.portrait_big.forTri : NbCells.landscape_big.forTri;

            } else if (GameSize.MEDIUM == gameSize) {
                return isPortrait ? NbCells.portrait_medium.forTri : NbCells.landscape_medium.forTri;

            } else if (GameSize.SMALL == gameSize) {
                return isPortrait ? NbCells.portrait_small.forTri : NbCells.landscape_small.forTri;

            }

        }

        throw new UnsupportedOperationException("Undefined nb cells on board for portrait? " + isPortrait + ", type "
                + gameType + ", size " + gameSize);
    }

    @Override
    public void buildGridGame() {

        retrieveGameInstanceAndSetGameType();
        nbCellsOnBoard = getNbCellsOnBoard(isPortrait, gameType, Pgu_lang_az.gameConfig.size());

        // nbRows = isPortrait ? NB_ROWS_PORTRAIT : 4;
        // nbCellsByRow = isPortrait ? 4 : 8;

        counterIdxCell = 0;
        cellH = isPortrait ? 300 : 150;
        cellW = isPortrait ? 150 : 300;
        cellFactory = new GameCellFactory(cellW, cellH, isPortrait, this);

        // if (0 == gridArea.getWidgetCount()) {
        // cells.clear();
        // for (int i = 0; i < nbRows; i++) {
        // gridArea.add(createRow());

        gridArea.clear();
        for (int i = 0; i < nbCellsOnBoard; i++) {
            gridArea.add(createCell());
        }

        // return;
        // }

        // if (isPortrait_old != isPortrait) {
        // gridArea.clear();
        // for (int i = 0; i < nbRows; i++) {
        // gridArea.add(createRow());
        // }
        // isPortrait_old = isPortrait;
        // return;
        // }
        //
    }

    // private FlowPanel createRow() {
    // final FlowPanel row = new FlowPanel();
    // for (int i = 0; i < nbCellsByRow; i++) {
    // row.add(createCell());
    // if (0 == i) {
    // final Style styleRow = row.getElement().getStyle();
    // styleRow.setMarginTop(10, Unit.PX);
    // styleRow.setMarginLeft(10, Unit.PX);
    // }
    // }
    //
    // // clear floating
    // final HTML divClearingFloat = new HTML();
    // divClearingFloat.getElement().getStyle().setProperty("clear", "both");
    // row.add(divClearingFloat);
    // return row;
    // }

    // private final List<GameCell> cells = Lists.newArrayList();

    private GameCell createCell() {
        counterIdxCell++;
        final GameCell cell = new GameCell(cellFactory).index(counterIdxCell);
        cell.size();
        return cell;
    }

    private final List<Integer> occupiedSlots = Lists.newArrayList();
    private final List<Integer> availableSlots = Lists.newArrayList();
    private int counterFoundAssociations = 0;
    private int nbAssociations = 0;
    private HashBiMap<String, String> availableBiSymbols = null;
    private HashTriMap<String, String, String> availableTriSymbols = null;

    @Override
    public void fillGridWithSymbols() {
        GWT.log("generateGame...");

        for (int i = 0; i < nbCellsOnBoard; i++) {
            ((GameCell) gridArea.getWidget(i)).deselect();
        }

        counterFoundAssociations = 0;
        occupiedSlots.clear();
        availableSlots.clear();

        for (int i = 0; i < nbCellsOnBoard; i++) {
            availableSlots.add(i);
        }

        fetchAvailableSymbols();
        fillCellsWithSymbols();
    }

    private void fillCellsWithSymbols() {

        if (null != availableBiSymbols) {
            final List<Entry<String, String>> availableEntries = Lists.newArrayList(availableBiSymbols.entrySet());

            final int availableEntriesSize = availableEntries.size();

            final int nbDuos = nbCellsOnBoard / 2;
            nbAssociations = nbDuos;

            for (int i = 0; i < nbDuos; i++) {

                final int indexEntry = Random.nextInt(availableEntriesSize);
                final Entry<String, String> latin2foreign = availableEntries.get(indexEntry);

                final String latin = latin2foreign.getKey();
                final String foreign = latin2foreign.getValue();

                final int indexLatin = getIndexSlot();
                final int indexForeign = getIndexSlot();

                final GameCell cellLatin = (GameCell) gridArea.getWidget(indexLatin);
                cellLatin.setCharacter(latin, TuplePosition.FIRST);
                cellLatin.ice().setDefaultSkin();

                final GameCell cellForeign = (GameCell) gridArea.getWidget(indexForeign);
                cellForeign.setCharacter(foreign, TuplePosition.SECOND);
                cellForeign.green().setDefaultSkin();
            }

        } else if (null != availableTriSymbols) {

            final List<HashTriMap.Entry<String, String, String>> availableEntries = availableTriSymbols.entryList();

            final int availableEntriesSize = availableEntries.size();

            final int nbTrios = nbCellsOnBoard / 3;
            nbAssociations = nbTrios;

            for (int i = 0; i < nbTrios; i++) {

                final int indexEntry = Random.nextInt(availableEntriesSize);
                final HashTriMap.Entry<String, String, String> entry = availableEntries.get(indexEntry);

                final String latin = entry.first();
                final String foreign = entry.second();
                final String symbol = entry.third();

                final int indexLatin = getIndexSlot();
                final int indexForeign = getIndexSlot();
                final int indexSymbol = getIndexSlot();

                final GameCell cellLatin = (GameCell) gridArea.getWidget(indexLatin);
                cellLatin.setCharacter(latin, TuplePosition.FIRST);
                cellLatin.ice().setDefaultSkin();

                final GameCell cellForeign = (GameCell) gridArea.getWidget(indexForeign);
                cellForeign.setCharacter(foreign, TuplePosition.SECOND);
                cellForeign.green().setDefaultSkin();

                final GameCell cellSymbol = (GameCell) gridArea.getWidget(indexSymbol);
                cellSymbol.setCharacter(symbol, TuplePosition.THIRD);
                cellSymbol.violet().setDefaultSkin();
            }
        }
    }

    private HasBiSymbols hasBiSymbols = null;
    private HasTriSymbols hasTriSymbols = null;
    private GameType gameType = GameType.BI;

    private enum GameType {
        BI, TRI
    }

    private void retrieveGameInstanceAndSetGameType() {
        hasBiSymbols = null;
        hasTriSymbols = null;

        if (Theme.HIRAGANA == Pgu_lang_az.gameConfig.theme()) {
            hasBiSymbols = Hiragana.INSTANCE;

        } else if (Theme.KATAKANA == Pgu_lang_az.gameConfig.theme()) {
            hasBiSymbols = Katakana.INSTANCE;

        } else if (isRussianAlphabet()) {
            hasBiSymbols = RussianAlphabet.INSTANCE;

        } else if (isGreekAlphabet()) {
            hasBiSymbols = GreekAlphabet.INSTANCE;

            //        } else if (isChineseWords()) {
            //            hasTriSymbols = ChineseWords.INSTANCE;
        }

        if (null != hasBiSymbols) {
            gameType = GameType.BI;

        } else if (null != hasTriSymbols) {
            gameType = GameType.TRI;

        }
    }

    private void fetchAvailableSymbols() {

        if (null != hasBiSymbols) {
            availableBiSymbols = hasBiSymbols.availableSymbols(Pgu_lang_az.gameConfig.subselections());
            availableTriSymbols = null;

        } else if (null != hasTriSymbols) {
            availableTriSymbols = hasTriSymbols.availableSymbols(Pgu_lang_az.gameConfig.subselections());
            availableBiSymbols = null;
        }

    }

    //    private boolean isChineseWords() {
    //        return Language.CHINESE == Pgu_lang_az.gameConfig.language() //
    //                && LanguageGranularity.WORD == Pgu_lang_az.gameConfig.granularity();
    //    }

    private boolean isGreekAlphabet() {
        return Language.GREEK == Pgu_lang_az.gameConfig.language() //
                && LanguageGranularity.ALPHABET == Pgu_lang_az.gameConfig.granularity();
    }

    private boolean isRussianAlphabet() {
        return Language.RUSSIAN == Pgu_lang_az.gameConfig.language()//
                && LanguageGranularity.ALPHABET == Pgu_lang_az.gameConfig.granularity();
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
    private GameCell secondCell = null;

    @Override
    public void clicksOn(final GameCell cell) {

        if (GameType.BI == gameType) {
            handlesDuo(cell);

        } else if (GameType.TRI == gameType) {
            handlesTrio(cell);

        }
    }

    private void handlesTrio(final GameCell cell) {
        if (null == firstCell) {
            firstCell = cell;
            return;
        }

        if (null == secondCell) {
            final String firstCharacter = firstCell.getCharacter();
            final String secondCharacter = cell.getCharacter();

            if (firstCell.tuplePosition() == cell.tuplePosition()) {
                resetClickedCells(cell);
                return;
            }

            final ArrayList<String> matchCharacters = Lists.newArrayList();

            if (TuplePosition.FIRST == firstCell.tuplePosition()) {

                if (TuplePosition.SECOND == cell.tuplePosition()) {
                    matchCharacters.addAll(availableTriSymbols.getSecondsFromFirst(firstCharacter));

                } else if (TuplePosition.THIRD == cell.tuplePosition()) {
                    matchCharacters.addAll(availableTriSymbols.getThirdsFromFirst(firstCharacter));

                }

            } else if (TuplePosition.SECOND == firstCell.tuplePosition()) {

                if (TuplePosition.FIRST == cell.tuplePosition()) {
                    matchCharacters.addAll(availableTriSymbols.getFirstsFromSecond(firstCharacter));

                } else if (TuplePosition.THIRD == cell.tuplePosition()) {
                    matchCharacters.addAll(availableTriSymbols.getThirdsFromSecond(firstCharacter));

                }

            } else if (TuplePosition.THIRD == firstCell.tuplePosition()) {

                if (TuplePosition.FIRST == cell.tuplePosition()) {
                    matchCharacters.addAll(availableTriSymbols.getFirstsFromThird(firstCharacter));

                } else if (TuplePosition.SECOND == cell.tuplePosition()) {
                    matchCharacters.addAll(availableTriSymbols.getSecondsFromThird(firstCharacter));

                }
            }

            if (!matchCharacters.contains(secondCharacter)) {
                resetClickedCells(cell);
                return;
            }

            secondCell = cell;
            return;
        }

        if (firstCell.tuplePosition() == cell.tuplePosition() //
                || secondCell.tuplePosition() == cell.tuplePosition()) {
            resetClickedCells(cell);
            return;
        }

        final String firstCharacter = firstCell.getCharacter();
        final String secondCharacter = secondCell.getCharacter();
        final String thirdCharacter = cell.getCharacter();
        final ArrayList<String> matchCharacters = Lists.newArrayList();

        if (TuplePosition.FIRST == firstCell.tuplePosition() //
                && TuplePosition.SECOND == secondCell.tuplePosition()) {
            matchCharacters.addAll(availableTriSymbols.getThirdsFromFirstAndSecond(firstCharacter, secondCharacter));

        } else if (TuplePosition.SECOND == firstCell.tuplePosition() //
                && TuplePosition.FIRST == secondCell.tuplePosition()) {
            matchCharacters.addAll(availableTriSymbols.getThirdsFromFirstAndSecond(secondCharacter, firstCharacter));

        } else if (TuplePosition.FIRST == firstCell.tuplePosition() //
                && TuplePosition.THIRD == secondCell.tuplePosition()) {
            matchCharacters.addAll(availableTriSymbols.getSecondsFromFirstAndThird(firstCharacter, secondCharacter));

        } else if (TuplePosition.THIRD == firstCell.tuplePosition() //
                && TuplePosition.FIRST == secondCell.tuplePosition()) {
            matchCharacters.addAll(availableTriSymbols.getSecondsFromFirstAndThird(secondCharacter, firstCharacter));

        } else if (TuplePosition.SECOND == firstCell.tuplePosition() //
                && TuplePosition.THIRD == secondCell.tuplePosition()) {
            matchCharacters.addAll(availableTriSymbols.getFirstsFromSecondAndThird(firstCharacter, secondCharacter));

        } else if (TuplePosition.THIRD == firstCell.tuplePosition() //
                && TuplePosition.SECOND == secondCell.tuplePosition()) {
            matchCharacters.addAll(availableTriSymbols.getFirstsFromSecondAndThird(secondCharacter, firstCharacter));

        }

        if (!matchCharacters.contains(thirdCharacter)) {
            resetClickedCells(cell);
            return;
        }

        firstCell = null;
        secondCell = null;
        counterFoundAssociations++;
        if (nbAssociations == counterFoundAssociations) {
            Window.alert("Congrat'!");
        }

    }

    private void handlesDuo(final GameCell cell) {
        if (null == firstCell) {
            firstCell = cell;
            return;
        }

        if (firstCell.tuplePosition() == cell.tuplePosition()) {
            resetClickedCells(cell);
            return;
        }

        final String firstCharacter = firstCell.getCharacter();
        final String secondCharacter = cell.getCharacter();

        String matchCharacter;
        if (TuplePosition.FIRST == firstCell.tuplePosition()) {
            matchCharacter = availableBiSymbols.get(firstCharacter);

        } else {
            matchCharacter = availableBiSymbols.inverse().get(firstCharacter);

        }

        if (!secondCharacter.equals(matchCharacter)) {
            resetClickedCells(cell);
            return;
        }

        firstCell = null;
        counterFoundAssociations++;
        if (nbAssociations == counterFoundAssociations) {
            Window.alert("Congrat'!");
        }
    }

    private void resetClickedCells(final GameCell cell) {

        cell.deselect();

        if (null != firstCell) {
            firstCell.deselect();
            firstCell = null;
        }

        if (null != secondCell) {
            secondCell.deselect();
            secondCell = null;
        }
    }

}
