package pgu.client.enums;

public enum GameSize {
    BIG(32), MEDIUM(16), LITTLE(8);

    private int nbCells;

    private GameSize(final int nbCells) {
        this.nbCells = nbCells;
    }
}
