package Zad4;

import Code.Note;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.verify;

public class VerifyTest {

    @Disabled
    @Test
    public void t(){

        Note n = EasyMock.createMock(Note.class);
        EasyMock.expect(n.getNote()).andReturn(3.0f);
        EasyMock.expect(n.getName()).andReturn("Hi");
        EasyMock.replay(n);


        verify(n);      // bedzie brak uzycia 2 stubow

    }
    @Test
    public void t1(){

        Note n = EasyMock.createMock(Note.class);

        EasyMock.expect(n.getName()).andReturn("Hi");
        EasyMock.replay(n);

        n.getName();


        verify(n);

    }


}
