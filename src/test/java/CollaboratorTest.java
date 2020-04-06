import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class CollaboratorTest {

    static Collaborator col;


    @BeforeAll
    static void setup(){

        col = new Collaborator();

    }

    @Test
    @DisplayName("1")
    public void returnSomethingTestxd(){

        Assertions.assertEquals(5, col.returnSomething(4));

    }

    @Test
    @DisplayName("2")
    public void returnSomethingMoreTestxd(){

        Assertions.assertEquals(21, col.returnSomethingMore(3,5));

    }



}
