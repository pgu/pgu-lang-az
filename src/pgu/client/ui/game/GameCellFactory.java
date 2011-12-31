package pgu.client.ui.game;

public class GameCellFactory {

    private final int width;
    private final int height;
    private final int outW;
    private final int outH;
    private final int medW;
    private final int medH;
    private final int inW;
    private final int inH;
    private final int subW;
    private final int subH;
    private final boolean isPortrait;

    public GameCellFactory(final int wAvailable, final int hAvailable, final boolean isPortrait) {
        final int w = wAvailable - 30;
        final int h = hAvailable - (isPortrait ? 30 : 10);
        width = w;
        height = h;
        outW = w - 2;
        outH = h - 2;
        medW = w - 4;
        medH = h - 4;
        inW = w - 6;
        inH = h - 6;
        subW = w - 7;
        subH = h - 7;
        this.isPortrait = isPortrait;
    }

    public int extW() {
        return width;
    }

    public int extH() {
        return height;
    }

    public int outW() {
        return outW;
    }

    public int outH() {
        return outH;
    }

    public int medW() {
        return medW;
    }

    public int medH() {
        return medH;
    }

    public int inW() {
        return inW;
    }

    public int inH() {
        return inH;
    }

    public int subW() {
        return subW;
    }

    public int subH() {
        return subH;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public boolean isPortrait() {
        return isPortrait;
    }

}
