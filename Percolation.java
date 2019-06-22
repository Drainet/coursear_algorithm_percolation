public class Percolation {

    private final int[] grid;

    private int openSiteCount = 0;

    private final int topIndex;
    private final int bottomIndex;
    private final int gridWidth;

    public Percolation(int n) {
        // add extra 2 slots for top and bottom position
        int gridSize = n * n + 2;
        grid = new int[gridSize];
        gridWidth = n;
        topIndex = gridSize - 1;
        bottomIndex = gridSize - 2;
        for (int i = 0; i < gridSize; i++) {
            grid[i] = -1;
        }
        grid[topIndex] = topIndex;
        grid[bottomIndex] = bottomIndex;
    }

    public void open(int row, int col) {
        row--;
        col--;
        int currentIndex = row * gridWidth + col;
        if (grid[currentIndex] != -1) {
            return;
        }

        openSiteCount++;

        grid[currentIndex] = currentIndex;
        int aboveIndex = currentIndex - gridWidth;
        if (row > 0 && grid[aboveIndex] != -1) {
            connect(currentIndex, aboveIndex);
        }
        int underIndex = currentIndex + gridWidth;
        if (row < gridWidth - 1 && grid[currentIndex + gridWidth] != -1) {
            connect(currentIndex, underIndex);
        }
        int leftIndex = currentIndex - 1;
        if (col > 0 && grid[leftIndex] != -1) {
            connect(currentIndex, leftIndex);
        }
        int rightIndex = currentIndex + 1;
        if (col < gridWidth - 1 && grid[rightIndex] != -1) {
            connect(currentIndex, rightIndex);
        }
        if (row == 0) {
            connect(currentIndex, topIndex);
        }
        if (row == gridWidth - 1) {
            connect(currentIndex, bottomIndex);
        }
    }

    private void connect(int firstIndex, int secondIndex) {
        int firstRoot = getRoot(firstIndex);
        int secondRoot = getRoot(secondIndex);
        if (firstRoot != secondRoot) {
            grid[secondRoot] = firstRoot;
        }
    }

    private int getRoot(int index) {
        int result = index;
        while (grid[result] != result) {
            result = grid[result];
        }
        return result;
    }

    public boolean isOpen(int row, int col) {
        row--;
        col--;
        return grid[row * gridWidth + col] != -1;
    }

    public boolean isFull(int row, int col) {
        row--;
        col--;
        int currentIndex = row * gridWidth + col;
        if (grid[currentIndex] == -1) {
            return false;
        }
        return getRoot(currentIndex) == getRoot(topIndex);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return getRoot(topIndex) == getRoot(bottomIndex);
    }

    public static void main(String[] args) {
    }
}