package pgu.client.ui.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import pgu.client.AppHelper;
import pgu.client.ui.game.GameCell.TuplePosition;
import pgu.client.ui.utils.AppCell;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameViewImpl extends Composite implements GameView {

    interface GameViewImplUiBinder extends UiBinder<Widget, GameViewImpl> {
    }

    private static GameViewImplUiBinder uiBinder                 = GWT.create(GameViewImplUiBinder.class);

    @UiField
    HTMLPanel                           containerGridArea;
    @UiField(provided = true)
    AppCell                             exitBtn, score, restartBtn;
    // @UiField
    // HTMLPanel menuArea, gridArea;

    // @UiField
    // HTMLPanel help, time, restart, exit;
    // @UiField
    // HTML helpText, timeText, restartText, exitText;
    HTMLPanel                           gridArea                 = new HTMLPanel("");

    private final List<Integer>         occupiedSlots            = Lists.newArrayList();
    private final List<Integer>         availableSlots           = Lists.newArrayList();
    private int                         counterFoundAssociations = 0;
    private int                         nbAssociations           = 0;
    private HashBiMap<String, String>   availableBiSymbols       = null;
    // private HashTriMap<String, String, String> availableTriSymbols = null;

    private GameCell                    firstCell                = null;
    private GameCell                    secondCell               = null;

    private long                        startInMs                = 0;

    private static final int            MENU_HEIGHT_PORTRAIT     = 100;
    private static final int            MENU_HEIGHT_LANDSCAPE    = 50;

    // private final static int W_HELP = 115;
    // private final static int W_RESTART = 195;
    // private final static int W_EXIT = 96;
    //
    // private final boolean isPortrait_old = true;
    // private boolean isPortrait = true;

    // private int gridW = 0;
    // private int gridH = 0;

    // private int nbRows = 0;
    // private int nbCellsByRow = 0;
    // private int nbCellsOnBoard = 0;
    // private int cellH = -10;
    // private int cellW = -10;
    // private GameCellFactory cellFactory;

    private final AppHelper             h                        = new AppHelper();

    public GameViewImpl() {

        score = new AppCell(AppCell.Skin.ICE);
        restartBtn = new AppCell(AppCell.Skin.FIRE);
        exitBtn = new AppCell(AppCell.Skin.FIRE);

        initWidget(uiBinder.createAndBindUi(this));

        score.setSize(720, 250);
        restartBtn.setSize(320, 150);
        exitBtn.setSize(320, 150);

        restartBtn.setHTML("RESTART");
        exitBtn.setHTML("EXIT");

        resize();

        //        final long start = new Date().getTime();
        //        h.console("start " + start);

        buildGridGame();

        //        h.console("stop " + (new Date().getTime() - start) + " ms");

        // Window.addResizeHandler(new ResizeHandler() {
        //
        // @Override
        // public void onResize(final ResizeEvent event) {
        // resize();
        // }
        // });

        onStop();

    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    // @UiHandler("exitText")
    // public void clickExit(final ClickEvent e) {
    // final GameConfig gc = Pgu_lang_az.gameConfig;
    // presenter.goTo(new WelcomePlace(gc.language(), gc.subselections()));
    // }

    // @UiHandler("restartText")
    // public void clickRestart(final ClickEvent e) {
    // fillGridWithSymbols();
    // }

    @Override
    public Widget asWidget() {
        return super.asWidget();
    }

    @UiHandler("exitBtn")
    public void exitBtn(final ClickEvent e) {
        presenter.goToWelcomePage();
    }

    @UiHandler("restartBtn")
    public void restartBtn(final ClickEvent e) {
        presenter.reload();
    }

    public boolean isPortrait() {
        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        final boolean isPortrait = w < h;
        return isPortrait;
    }

    @Override
    public void resize() {
        GWT.log("resize....");

        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        // final int hMenu = isPortrait() ? MENU_HEIGHT_PORTRAIT : MENU_HEIGHT_LANDSCAPE;
        // menuArea.setPixelSize(w, hMenu);
        // gridArea.getElement().getStyle().setTop(hMenu, Unit.PX);

        // final int gridW = w;
        // gridArea.setWidth(gridW + "px");
        //
        // final int gridH = h - hMenu;
        // gridArea.setHeight(gridH + "px");

        // final int btnTop = isPortrait ? 25 : 0;

        // final int hBtn = hMenu - 7 - btnTop;

        // help.setPixelSize(W_HELP, hBtn);
        // restart.setPixelSize(W_RESTART, hBtn);
        // exit.setPixelSize(W_EXIT, hBtn);
        //
        // time.setPixelSize(w - 20 - (W_HELP + W_RESTART + W_EXIT), hBtn);

        // final int marginTop = (hMenu - hBtn) / 2;
        // help.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        // restart.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        // exit.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        // time.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
    }

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

    // private int getNbCellsOnBoard( //
    // final boolean isPortrait //
    // final GameType gameType, //
    // final GameSize gameSize
    // ) {

    // if (GameType.BI == gameType) {

    // if (GameSize.BIG == gameSize) {
    // return isPortrait() ? NbCells.portrait_big.forBi : NbCells.landscape_big.forBi;

    // } else if (GameSize.MEDIUM == gameSize) {
    // return isPortrait ? NbCells.portrait_medium.forBi : NbCells.landscape_medium.forBi;
    //
    // } else if (GameSize.SMALL == gameSize) {
    // return isPortrait ? NbCells.portrait_small.forBi : NbCells.landscape_small.forBi;
    //
    // }

    // } else if (GameType.TRI == gameType) {
    //
    // if (GameSize.BIG == gameSize) {
    // return isPortrait ? NbCells.portrait_big.forTri : NbCells.landscape_big.forTri;
    //
    // } else if (GameSize.MEDIUM == gameSize) {
    // return isPortrait ? NbCells.portrait_medium.forTri : NbCells.landscape_medium.forTri;
    //
    // } else if (GameSize.SMALL == gameSize) {
    // return isPortrait ? NbCells.portrait_small.forTri : NbCells.landscape_small.forTri;
    //
    // }
    //
    // }

    // throw new UnsupportedOperationException("Undefined nb cells on board for portrait? " + isPortrait + ", type "
    // + gameType + ", size " + gameSize);
    // }

    @Override
    public void buildGridGame() {

        // retrieveGameInstanceAndSetGameType();

        // final int nbCellsOnBoard = isPortrait ? NbCells.portrait_small.forBi : NbCells.landscape_small.forBi;
        // final int nbCellsOnBoard = 4;
        // nbCellsOnBoard = getNbCellsOnBoard();

        // nbRows = isPortrait ? NB_ROWS_PORTRAIT : 4;
        // nbCellsByRow = isPortrait ? 4 : 8;

        // final int cellH = isPortrait ? 300 : 150;
        // final int cellW = isPortrait ? 150 : 300;

        int counterIdxCell = 0;

        final boolean isPortrait = isPortrait();
        final int nbCellsOnBoard = 20;
        final int cellH = 150;
        final int cellW = 200;
        final GameCellFactory cellFactory = new GameCellFactory(cellW, cellH, isPortrait, this);

        // if (0 == gridArea.getWidgetCount()) {
        // cells.clear();
        // for (int i = 0; i < nbRows; i++) {
        // gridArea.add(createRow());

        // gridArea.clear();

        final ArrayList<GameCell> cells = new ArrayList<GameCell>(nbCellsOnBoard);

        for (int i = 0; i < nbCellsOnBoard; i++) {
            counterIdxCell++;
            cells.add(createCell(cellFactory, counterIdxCell));
        }

        for (final GameCell gameCell : cells) {
            gridArea.add(gameCell);
        }

        containerGridArea.add(gridArea);
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

    private GameCell createCell(final GameCellFactory cellFactory, final int counterIdxCell) {
        final GameCell cell = new GameCell(cellFactory).index(counterIdxCell);
        cell.size();
        return cell;
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

    @Override
    public void fillGridWithSymbols() {
        GWT.log("generateGame...");

        gridArea.setVisible(true);

        for (int i = 0; i < gridArea.getWidgetCount(); i++) {
            availableSlots.add(i);
        }

        // fetchAvailableSymbols();

        availableBiSymbols = presenter.getAvailableSymbols();
        fillCellsWithSymbols();
    }

    private void fillCellsWithSymbols() {

        // if (null != availableBiSymbols) {
        final List<Entry<String, String>> availableEntries = Lists.newArrayList(availableBiSymbols.entrySet());

        final int availableEntriesSize = availableEntries.size();

        final int nbDuos = gridArea.getWidgetCount() / 2;
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
            cellLatin.ice().flagAsDefaultSkin();

            final GameCell cellForeign = (GameCell) gridArea.getWidget(indexForeign);
            cellForeign.setCharacter(foreign, TuplePosition.SECOND);
            cellForeign.green().flagAsDefaultSkin();
        }

        // } else if (null != availableTriSymbols) {
        //
        // final List<HashTriMap.Entry<String, String, String>> availableEntries = availableTriSymbols.entryList();
        //
        // final int availableEntriesSize = availableEntries.size();
        //
        // final int nbTrios = nbCellsOnBoard / 3;
        // nbAssociations = nbTrios;
        //
        // for (int i = 0; i < nbTrios; i++) {
        //
        // final int indexEntry = Random.nextInt(availableEntriesSize);
        // final HashTriMap.Entry<String, String, String> entry = availableEntries.get(indexEntry);
        //
        // final String latin = entry.first();
        // final String foreign = entry.second();
        // final String symbol = entry.third();
        //
        // final int indexLatin = getIndexSlot();
        // final int indexForeign = getIndexSlot();
        // final int indexSymbol = getIndexSlot();
        //
        // final GameCell cellLatin = (GameCell) gridArea.getWidget(indexLatin);
        // cellLatin.setCharacter(latin, TuplePosition.FIRST);
        // cellLatin.ice().setDefaultSkin();
        //
        // final GameCell cellForeign = (GameCell) gridArea.getWidget(indexForeign);
        // cellForeign.setCharacter(foreign, TuplePosition.SECOND);
        // cellForeign.green().setDefaultSkin();
        //
        // final GameCell cellSymbol = (GameCell) gridArea.getWidget(indexSymbol);
        // cellSymbol.setCharacter(symbol, TuplePosition.THIRD);
        // cellSymbol.violet().setDefaultSkin();
        // }
        // }
    }

    // private HasBiSymbols hasBiSymbols = null;
    // private HasTriSymbols hasTriSymbols = null;
    // private GameType gameType = GameType.BI;
    //
    // private enum GameType {
    // BI, TRI
    // }

    // private void retrieveGameInstanceAndSetGameType() {
    // hasBiSymbols = presenter.getAlphabet();
    // hasBiSymbols = null;
    // // hasTriSymbols = null;
    //
    // if (Theme.HIRAGANA == Pgu_lang_az.gameConfig.theme()) {
    // hasBiSymbols = Hiragana.INSTANCE;
    //
    // } else if (Theme.KATAKANA == Pgu_lang_az.gameConfig.theme()) {
    // hasBiSymbols = Katakana.INSTANCE;
    //
    // } else if (isRussianAlphabet()) {
    // hasBiSymbols = RussianAlphabet.INSTANCE;
    //
    // } else if (isGreekAlphabet()) {
    // hasBiSymbols = GreekAlphabet.INSTANCE;
    //
    // // } else if (isChineseWords()) {
    // // hasTriSymbols = ChineseWords.INSTANCE;
    // }
    //
    // if (null != hasBiSymbols) {
    // gameType = GameType.BI;
    //
    // // } else if (null != hasTriSymbols) {
    // // gameType = GameType.TRI;
    // //
    // // }
    // }

    // private void fetchAvailableSymbols() {
    // presenter.getAlphabet().availableSymbols(presenter);
    // .availableSymbols(Pgu_lang_az.gameConfig.subselections())
    //
    // // if (null != hasBiSymbols) {
    // availableBiSymbols = presenter.getAvailableSymbols();
    // availableTriSymbols = null;

    // } else if (null != hasTriSymbols) {
    // availableTriSymbols = hasTriSymbols.availableSymbols(Pgu_lang_az.gameConfig.subselections());
    // availableBiSymbols = null;
    // }
    // }

    // private boolean isChineseWords() {
    // return Language.CHINESE == Pgu_lang_az.gameConfig.language() //
    // && LanguageGranularity.WORD == Pgu_lang_az.gameConfig.granularity();
    // }

    // private boolean isGreekAlphabet() {
    // return Language.GREEK == Pgu_lang_az.gameConfig.language() //
    // && LanguageGranularity.ALPHABET == Pgu_lang_az.gameConfig.granularity();
    // }

    // private boolean isRussianAlphabet() {
    // return Language.RUSSIAN == Pgu_lang_az.gameConfig.language()//
    // && LanguageGranularity.ALPHABET == Pgu_lang_az.gameConfig.granularity();
    // }

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

    @Override
    public void clicksOn(final GameCell cell) {

        // if (GameType.BI == gameType) {
        handlesDuo(cell);

        // } else if (GameType.TRI == gameType) {
        // handlesTrio(cell);

        // }
    }

    // private void handlesTrio(final GameCell cell) {
    // if (null == firstCell) {
    // firstCell = cell;
    // return;
    // }
    //
    // if (null == secondCell) {
    // final String firstCharacter = firstCell.getCharacter();
    // final String secondCharacter = cell.getCharacter();
    //
    // if (firstCell.tuplePosition() == cell.tuplePosition()) {
    // resetClickedCells(cell);
    // return;
    // }
    //
    // final ArrayList<String> matchCharacters = Lists.newArrayList();
    //
    // if (TuplePosition.FIRST == firstCell.tuplePosition()) {
    //
    // if (TuplePosition.SECOND == cell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getSecondsFromFirst(firstCharacter));
    //
    // } else if (TuplePosition.THIRD == cell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getThirdsFromFirst(firstCharacter));
    //
    // }
    //
    // } else if (TuplePosition.SECOND == firstCell.tuplePosition()) {
    //
    // if (TuplePosition.FIRST == cell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getFirstsFromSecond(firstCharacter));
    //
    // } else if (TuplePosition.THIRD == cell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getThirdsFromSecond(firstCharacter));
    //
    // }
    //
    // } else if (TuplePosition.THIRD == firstCell.tuplePosition()) {
    //
    // if (TuplePosition.FIRST == cell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getFirstsFromThird(firstCharacter));
    //
    // } else if (TuplePosition.SECOND == cell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getSecondsFromThird(firstCharacter));
    //
    // }
    // }
    //
    // if (!matchCharacters.contains(secondCharacter)) {
    // resetClickedCells(cell);
    // return;
    // }
    //
    // secondCell = cell;
    // return;
    // }
    //
    // if (firstCell.tuplePosition() == cell.tuplePosition() //
    // || secondCell.tuplePosition() == cell.tuplePosition()) {
    // resetClickedCells(cell);
    // return;
    // }
    //
    // final String firstCharacter = firstCell.getCharacter();
    // final String secondCharacter = secondCell.getCharacter();
    // final String thirdCharacter = cell.getCharacter();
    // final ArrayList<String> matchCharacters = Lists.newArrayList();
    //
    // if (TuplePosition.FIRST == firstCell.tuplePosition() //
    // && TuplePosition.SECOND == secondCell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getThirdsFromFirstAndSecond(firstCharacter, secondCharacter));
    //
    // } else if (TuplePosition.SECOND == firstCell.tuplePosition() //
    // && TuplePosition.FIRST == secondCell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getThirdsFromFirstAndSecond(secondCharacter, firstCharacter));
    //
    // } else if (TuplePosition.FIRST == firstCell.tuplePosition() //
    // && TuplePosition.THIRD == secondCell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getSecondsFromFirstAndThird(firstCharacter, secondCharacter));
    //
    // } else if (TuplePosition.THIRD == firstCell.tuplePosition() //
    // && TuplePosition.FIRST == secondCell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getSecondsFromFirstAndThird(secondCharacter, firstCharacter));
    //
    // } else if (TuplePosition.SECOND == firstCell.tuplePosition() //
    // && TuplePosition.THIRD == secondCell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getFirstsFromSecondAndThird(firstCharacter, secondCharacter));
    //
    // } else if (TuplePosition.THIRD == firstCell.tuplePosition() //
    // && TuplePosition.SECOND == secondCell.tuplePosition()) {
    // matchCharacters.addAll(availableTriSymbols.getFirstsFromSecondAndThird(secondCharacter, firstCharacter));
    //
    // }
    //
    // if (!matchCharacters.contains(thirdCharacter)) {
    // resetClickedCells(cell);
    // return;
    // }
    //
    // firstCell = null;
    // secondCell = null;
    // counterFoundAssociations++;
    // if (nbAssociations == counterFoundAssociations) {
    // Window.alert("Congrat'!");
    // }
    //
    // }

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

            final long stopInMs = new Date().getTime();

            score.setHTML("<p>Congratulations!</p><p>" + fmtTime(stopInMs, startInMs) + "</p>");
            score.fadeIn();
            restartBtn.fadeIn();
        }
    }

    private static int msecPerMinute = 1000 * 60;
    private static int msecPerHour   = msecPerMinute * 60;
    private static int msecPerDay    = msecPerHour * 24;

    private String fmtTime(final long stopInMs, final long startInMs) {
        double interval = stopInMs - startInMs;

        final int days = (int) Math.floor(interval / msecPerDay);
        interval = interval - days * msecPerDay;

        final int hours = (int) Math.floor(interval / msecPerHour);
        interval = interval - hours * msecPerHour;

        final int minutes = (int) Math.floor(interval / msecPerMinute);
        interval = interval - minutes * msecPerMinute;

        final int seconds = (int) Math.floor(interval / 1000);

        String fmt_time = "";

        if (days != 0) {
            return "More than one day!";
        }
        if (hours != 0) {
            return "More than one hour!";
        }
        if (minutes != 0) {
            fmt_time += minutes + " minutes ";
        }
        if (seconds != 0) {
            fmt_time += seconds + " seconds ";
        }

        return fmt_time;
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

    @Override
    public void onStop() {
        startInMs = 0;

        restartBtn.setVisible(false);
        score.setVisible(false);

        score.setText("");

        occupiedSlots.clear();
        availableSlots.clear();
        counterFoundAssociations = 0;
        nbAssociations = 0;
        availableBiSymbols = null;

        gridArea.setVisible(false);

        for (int i = 0; i < gridArea.getWidgetCount(); i++) {
            final GameCell gameCell = (GameCell) gridArea.getWidget(i);
            gameCell.onStop();
        }

        firstCell = null;
        secondCell = null;
    }

    @Override
    public void startChrono() {
        startInMs = new Date().getTime();
    }

}
