package model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * {@link Grid} instances represent the grid in <i>The Game of Life</i>.
 */
public class Grid implements Iterable<Cell> {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final Cell[][] cells;

    /**
     * Creates a new {@code Grid} instance given the number of rows and columns.
     *
     * @param numberOfRows    the number of rows
     * @param numberOfColumns the number of columns
     * @throws IllegalArgumentException if {@code numberOfRows} or {@code numberOfColumns} are
     *                                  less than or equal to 0
     */
    public Grid(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cells = createCells();
    }

    /**
     * Returns an iterator over the cells in this {@code Grid}.
     *
     * @return an iterator over the cells in this {@code Grid}
     */

    @Override
    public Iterator<Cell> iterator() {
        return new GridIterator(this);
    }

    private Cell[][] createCells() {
        Cell[][] cells = new Cell[getNumberOfRows()][getNumberOfColumns()];
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
                cells[rowIndex][columnIndex] = new Cell();
            }
        }
        return cells;
    }

    /**
     * Returns the {@link Cell} at the given index.
     *
     * <p>Note that the index is wrapped around so that a {@link Cell} is always returned.
     *
     * @param rowIndex    the row index of the {@link Cell}
     * @param columnIndex the column index of the {@link Cell}
     * @return the {@link Cell} at the given row and column index
     */
    public Cell getCell(int rowIndex, int columnIndex) {
        return cells[getWrappedRowIndex(rowIndex)][getWrappedColumnIndex(columnIndex)];
    }

    private int getWrappedRowIndex(int rowIndex) {
        return (rowIndex + getNumberOfRows()) % getNumberOfRows();
    }

    private int getWrappedColumnIndex(int columnIndex) {
        return (columnIndex + getNumberOfColumns()) % getNumberOfColumns();
    }

    /**
     * Returns the number of rows in this {@code Grid}.
     *
     * @return the number of rows in this {@code Grid}
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Returns the number of columns in this {@code Grid}.
     *
     * @return the number of columns in this {@code Grid}
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }


    // TODO: Écrire une version correcte de cette méthode.
    public List<Cell> getNeighbors(int rowIndex, int columnIndex) {


        List<Cell> Nei=new ArrayList<>();

        Nei.add(getCell(rowIndex-1, columnIndex-1));
        Nei.add(getCell(rowIndex-1, columnIndex));
        Nei.add(getCell(rowIndex-1, columnIndex+1));
        Nei.add(getCell(rowIndex, columnIndex+1));
        Nei.add(getCell(rowIndex+1, columnIndex+1));
        Nei.add(getCell(rowIndex+1, columnIndex));
        Nei.add(getCell(rowIndex+1, columnIndex-1));
        Nei.add(getCell(rowIndex, columnIndex-1));






        return Nei;
    }

    // TODO: Écrire une version correcte de cette méthode.
    public int countAliveNeighbors(int rowIndex, int columnIndex) {
        List<Cell> Neib = getNeighbors(rowIndex, columnIndex);
        int i=0;
    for (Cell neib : Neib){

        if(neib.isAlive()==true){

            i++;
        }

    }


        return i;
    }

    // TODO: Écrire une version correcte de cette méthode.
    public CellState calculateNextState(int rowIndex, int columnIndex) {
int i=countAliveNeighbors(rowIndex, columnIndex);

        if (getCell(rowIndex, columnIndex).isAlive()==true){
            if (i== 2 || i== 3) {
                return CellState.ALIVE;
            }
            return CellState.DEAD;

        }
        if (i == 3) {
return CellState.ALIVE;
        }
        return  CellState.DEAD;
    }
    // TODO: Écrire une version correcte de cette méthode.
    public CellState[][] calculateNextStates() {
        CellState[][] nextCellState = new CellState[getNumberOfRows()][getNumberOfColumns()];
for(int i=0;i<getNumberOfRows();i++){
    for(int j=0;j<getNumberOfColumns();j++){
       nextCellState[i][j]=calculateNextState(i,j);
    }



}

        return nextCellState;
    }

    // TODO: Écrire une version correcte de cette méthode.
    public void updateStates(CellState[][] nextState) {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                getCell(i, j).setState(nextState[i][j]);

            }
        }
    }
    /**
     * Transitions all {@link Cell}s in this {@code Grid} to the next generation.
     *
     * <p>The following rules are applied:
     * <ul>
     * <li>Any live {@link Cell} with fewer than two live neighbours dies, i.e. underpopulation.</li>
     * <li>Any live {@link Cell} with two or three live neighbours lives on to the next
     * generation.</li>
     * <li>Any live {@link Cell} with more than three live neighbours dies, i.e. overpopulation.</li>
     * <li>Any dead {@link Cell} with exactly three live neighbours becomes a live cell, i.e.
     * reproduction.</li>
     * </ul>
     */
    // TODO: Écrire une version correcte de cette méthode.
    public void updateToNextGeneration() {

    }

    /**
     * Sets all {@link Cell}s in this {@code Grid} as dead.
     */
    // TODO: Écrire une version correcte de cette méthode.
    public void clear() {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                getCell(i, j).setState(CellState.DEAD);
            }
        }
    }
    /**
     * Goes through each {@link Cell} in this {@code Grid} and randomly sets its state as ALIVE or DEAD.
     *
     * @param random {@link Random} instance used to decide if each {@link Cell} is ALIVE or DEAD.
     * @throws NullPointerException if {@code random} is {@code null}.
     */
    // TODO: Écrire une version correcte de cette méthode.
    public void randomGeneration(Random random) {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                if(random.nextBoolean()) {
                    getCell(i, j).setState(CellState.ALIVE);
                }


            }
        }
    }
}