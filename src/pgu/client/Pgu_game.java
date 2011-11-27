package pgu.client;

import pgu.client.gin.GameGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class Pgu_game implements EntryPoint {

    // final GameBoard gameBoard = new GameBoard();
    // final ToolbarApp toolbarApp = new ToolbarApp();
    // final WestArea westArea = new WestArea();

    @Override
    public void onModuleLoad() {

        final GameGinjector ginjector = GWT.create(GameGinjector.class);

        final SimplePanel displayContent = ginjector.getWidget();
        ginjector.getActivityManager().setDisplay(displayContent);

        final RootPanel rootPanel = RootPanel.get();
        rootPanel.add(displayContent);

        ginjector.getPlaceHistoryHandler().handleCurrentHistory();
    }

    // ////////////////////////////////////////////////////////////////

    // RootPanel.get().add(toolbarApp);
    // RootPanel.get().add(westArea);
    // RootPanel.get().add(gameBoard);
    //
    // resize();
    //
    // Window.addResizeHandler(new ResizeHandler() {
    //
    // @Override
    // public void onResize(final ResizeEvent event) {
    // resize();
    // }
    // });

    // private void resize() {
    // final int w = Window.getClientWidth();
    // final int h = Window.getClientHeight() - toolbarApp.getHeight();
    //
    // final int gameBoardWidth = w * 70 / 100;
    // gameBoard.resize(gameBoardWidth, h);
    //
    // final int westAreaWidth = w * 30 / 100;
    // westArea.resize(westAreaWidth, h);
    //
    // final int toolbarWidth = w;
    // toolbarApp.resize(toolbarWidth);
    //
    // }
}
