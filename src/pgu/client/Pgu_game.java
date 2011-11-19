package pgu.client;

import pgu.client.ui.GameBoard;
import pgu.client.ui.ToolbarApp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class Pgu_game implements EntryPoint {

    final GameBoard gameBoard = new GameBoard();
    final ToolbarApp toolbarApp = new ToolbarApp();

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(toolbarApp);
        RootPanel.get().add(gameBoard);

        // final int southMenuWidth = windowWidth;
        // final int southMenuHeight = windowHeight - gameBoard.getElement().getAbsoluteBottom();
        //
        // southMenu.setPixelSize(southMenuWidth, southMenuHeight);

        resize();

        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                resize();
            }
        });
    }

    private void resize() {
        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();
        final int gameBoardWidth = w;
        final int gameBoardHeight = h * 90 / 100;
        gameBoard.resize(gameBoardWidth, gameBoardHeight);

        final int toolbarWidth = w;
        final int toolbarHeight = h * 10 / 100;
        toolbarApp.resize(toolbarWidth, toolbarHeight);

    }
}
