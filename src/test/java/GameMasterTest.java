import junit.framework.TestCase;

public class GameMasterTest extends TestCase
{
    GameMaster master;

    public void setup()
    {
        master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
    }
    public void testSingleton()
    {
        GameMaster instance1 = GameMaster.instance();
        assertNotNull(instance1);

        GameMaster instance2 = GameMaster.instance();
        assertNotNull(instance2);

        assertSame(instance1, instance2);
    }

    public void testPlayerInit()
    {
        GameMaster master = GameMaster.instance();
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

    public void testMovePlayerSimple()
    {
        GameMaster master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
        master.setNumberOfPlayers(1);
        Player player = master.getPlayer(0);
        master.movePlayer(0, 2);
        assertEquals("Blue 2", player.getPosition().getName());

        master.movePlayer(0, 3);
        assertEquals("Green 2", player.getPosition().getName());
    }

    public void testMovePlayerCycle()
    {
        GameMaster master = GameMaster.instance();
        master.setGameBoard(new SimpleGameBoard());
        master.setNumberOfPlayers(1);
        Player player = master.getPlayer(0);
        master.movePlayer(0, 2);
        master.movePlayer(0, 5);
        assertEquals("Blue 1", player.getPosition().getName());
    }
}
