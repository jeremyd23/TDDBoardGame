import junit.framework.TestCase;

/**
 * Tests the different methods of the GameMaster class.
 */
public class GameMasterTest extends TestCase
{
    GameMaster master;

    /**
     * Initial setup used before each test.
     */
    public void setup()
    {
        master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
    }

    /**
     * Asserts that GameMaster.instance() returns the GameMaster object
     * Asserts when assigning to a second variable, the first and second variable are the same GameMaster object
     */
    public void testSingleton()
    {
        GameMaster instance1 = GameMaster.instance();
        assertNotNull(instance1);

        GameMaster instance2 = GameMaster.instance();
        assertNotNull(instance2);

        assertSame(instance1, instance2);
    }

    /**
     * Asserts getNumberOfPlayers() returns the same number as the GameMaster was assigned.
     * Asserts that each player starts at Cell position 0 on the GameBoard.
     */
    public void testPlayerInit()
    {
        master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
        master.setNumberOfPlayers(6);
        assertEquals(6, master.getNumberOfPlayers());
        Cell go = master.getGameBoard().getCell(0);

        for(int i = 0; i < 6; i++)
        {
            Player player = master.getPlayer(i);
            assertSame(go, player.getPosition());
        }
    }

    /**
     * Asserts that after moving a Player from position 0, their new position matches the expected Cell.
     * Asserts that after moving the Player again, their new position matches the expected Cell.
     */
    public void testMovePlayerSimple()
    {
        master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
        master.setNumberOfPlayers(1);
        Player player = master.getPlayer(0);
        master.movePlayer(0, 2);
        assertEquals("Blue 2", player.getPosition().getName());

        master.movePlayer(0, 3);
        assertEquals("Green 2", player.getPosition().getName());
    }

    /**
     * Asserts that when a Players move puts them beyond the end of the list of Cells, it continues from the
     * beginning of the list of Cells again.
     */
    public void testMovePlayerCycle()
    {
        master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
        master.setNumberOfPlayers(1);
        Player player = master.getPlayer(0);
        master.movePlayer(0, 2);
        master.movePlayer(0, 5);
        assertEquals("Blue 1", player.getPosition().getName());
    }
}
