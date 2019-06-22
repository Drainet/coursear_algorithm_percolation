public class Percolation {

    private static GridPosition topPosition = new GridPosition(-1, -1);
    private static GridPosition bottomPosition = new GridPosition(-2, -2);

    private static class GridPosition {
        final int row;
        final int col;

        GridPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private final GridPosition[][] grid;

    private int openSiteCount = 0;

    public Percolation(int n) {
        grid = new GridPosition[n][n];
    }

    public void open(int row, int col) {
        if (grid[row][col] != null) {
            return;
        }

        openSiteCount++;

        GridPosition currentPosition = new GridPosition(row, col);
        grid[row][col] = currentPosition;
        if (row > 0 && grid[row - 1][col] != null) {
            connect(currentPosition, new GridPosition(row - 1, col));
        }
        if (row < grid.length - 1 && grid[row + 1][col] != null) {
            connect(currentPosition, new GridPosition(row + 1, col));
        }
        if (col > 0 && grid[row][col - 1] != null) {
            connect(currentPosition, new GridPosition(row, col - 1));
        }
        if (col < grid[row].length - 1 && grid[row][col + 1] != null) {
            connect(currentPosition, new GridPosition(row, col + 1));
        }
        if (row == 0) {
            connect(currentPosition, topPosition);
        }
        if (row == grid.length - 1) {
            connect(currentPosition, bottomPosition);
        }
    }

    private void connect(GridPosition first, GridPosition second) {
        //TODO
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col] == null;
    }

    public boolean isFull(int row, int col) {
        if (grid[row][col] == null) {
            return false;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        // does the system percolate?
        return false;
    }

    public static void main(String[] args) {
        // test client (optional)
    }
}