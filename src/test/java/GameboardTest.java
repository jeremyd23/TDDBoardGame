import junit.framework.TestCase;

/**
 * Tests the different methods of the GameBoard class.
 */
public class GameboardTest extends TestCase
{
    /**
     * Asserts that one Cell exists when GameBoard is instantiated and getCellNumber() returns the correct number of Cells.
     * Asserts that getCellNumber() returns 2 when adding an additional Cell.
     */
    public void testAddCell()
    {
        GameBoard gameboard = new GameBoard();
        assertEquals(1, gameboard.getCellNumber());
        Cell cell = new Cell();
        gameboard.addCell(cell);
        assertEquals(2, gameboard.getCellNumber());
    }

    /**
     * Asserts first Cell in GameBoard is of type GoCell.
     */
    public void testFirstCell()
    {
        GameBoard gameboard = new GameBoard();
        Cell firstCell = gameboard.getCell(0);
        assertSame(GoCell.class, firstCell.getClass());
    }

    /**
     * Asserts that when a Cell is added to specifc index of GameBoard, getCellIndex(Cell) returns the correct index.
     * Asserts that getCellIndex returns -1 if a given Cell is not in GameBoard.
     */
    public void testGetCellIndex()
    {
        GameBoard gameBoard = new SimpleGameBoard();
        Cell blue2 = gameBoard.getCell(2);
        int index = gameBoard.getCellIndex(blue2);
        assertEquals(2, index);

        Cell notExist = new Cell();
        index = gameBoard.getCellIndex(notExist);
        assertEquals(-1, index);
    }
}
