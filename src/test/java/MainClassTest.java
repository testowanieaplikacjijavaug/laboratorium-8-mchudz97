import org.easymock.MockType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;

public class MainClassTest {


    private MainClass mcTest;

    @BeforeEach
    void reset(){

        mcTest = new MainClass();

    }


    @Test
    @DisplayName("EasyMock  mock")
    public void justMock(){

        ArrayList<Collaborator> collsMock = mock( ArrayList.class);
        Collaborator colMock1 = mock(Collaborator.class);
        Collaborator colMock2 = mock(Collaborator.class);

        expect(collsMock.get(3)).andReturn(colMock2); //*
        expect(collsMock.add(colMock2)).andReturn(true); //*
        expect(collsMock.add(colMock1)).andReturn(true); //*

        replay(collsMock);

        mcTest.setColls(collsMock);
        mcTest.putColl(colMock1);
        mcTest.putColl(colMock2);

        Assertions.assertEquals(colMock2, mcTest.getColl(3)); // po prostu piszemy co oczekujemy od okresloych metod
        verify(collsMock);                                          // bez wzgledu na jakakolwiek kolejnosc (*)

    }


    @Test
    @DisplayName("EasyMock  mock fails")
    public void justMock2(){

        ArrayList<Collaborator> collsMock = mock( ArrayList.class);
        Collaborator colMock1 = mock(Collaborator.class);
        Collaborator colMock2 = mock(Collaborator.class);

        expect(collsMock.get(3)).andReturn(colMock2);
        expect(collsMock.add(colMock2)).andReturn(true);
        expect(collsMock.add(colMock1)).andReturn(true);

        replay(collsMock);

        mcTest.setColls(collsMock);
        mcTest.putColl(colMock1);
        mcTest.putColl(colMock2);

        Assertions.assertEquals(colMock2, mcTest.getColl(5)); // nie okreslilismy co getColl(5) ma zwracac,
        verify(collsMock);  //bedzie blad asercji


    }



    @Test
    @DisplayName("EasyMock strict mock passes if expected methods were used in specific order")
    public void strictMockPasses(){

        ArrayList<Collaborator> collsMock = mock(MockType.STRICT, ArrayList.class);
        Collaborator colMock1 = mock(Collaborator.class);
        Collaborator colMock2 = mock(Collaborator.class);

        expect(collsMock.add(colMock1)).andReturn(true);
        expect(collsMock.add(colMock2)).andReturn(true);
        expect(collsMock.get(1)).andReturn(colMock2);
        replay(collsMock);

        mcTest.setColls(collsMock);
        mcTest.putColl(colMock1);
        mcTest.putColl(colMock2);

        Assertions.assertEquals(colMock2, mcTest.getColl(1));
        verify(collsMock);

    }
    @Test
    @DisplayName("EasyMock strict mock fails if expected methods weren't used in stricted order")
    public void strictMockFails(){

        ArrayList<Collaborator> collsMock = mock(MockType.STRICT, ArrayList.class);
        Collaborator colMock1 = mock(Collaborator.class);
        Collaborator colMock2 = mock(Collaborator.class);

        expect(collsMock.add(colMock2)).andReturn(true); // tutaj zostaly zamienione linie wzgledem pierwszego testu
        expect(collsMock.add(colMock1)).andReturn(true); // co wywola blad asercji
        expect(collsMock.get(1)).andReturn(colMock2);
        replay(collsMock);

        mcTest.setColls(collsMock);
        mcTest.putColl(colMock1); //gdyby ta linia zostala zamieniona z ponizsza
        mcTest.putColl(colMock2); //nie byloby bledu

        Assertions.assertEquals(colMock2, mcTest.getColl(1));
        verify(collsMock);//pokazuje brakujace metody

    }

    @Test
    @DisplayName("EasyMock nice mock")
    public void niceMock(){

        ArrayList<Collaborator> collsMock = mock(MockType.NICE, ArrayList.class);
        Collaborator colMock1 = mock(Collaborator.class);
        expect(collsMock.add(colMock1)).andReturn(true);
        replay(collsMock);

        mcTest.setColls(collsMock);
        mcTest.putColl(colMock1);


        Assertions.assertEquals(null, mcTest.getColl(0)); // dla mocka arraylisty nie zostalo okreslone
        verify(collsMock);  // zachowanie dla metody collsMock.get(0), ale przez to, ze jest to nice mock
                            // dla nieokreslonych metod zwracajacych obiekty zwraca null (dla liczbowych 0, boolowskich false)

    }

    @Test
    @DisplayName("EasyMock partial mock")
    public void partialMock(){

        Collaborator coll= partialMockBuilder(Collaborator.class)
                .addMockedMethod("returnSomething")
                .createMock();

        expect(coll.returnSomething(5)).andReturn(3);
        expect(coll.returnSomething(3)).andReturn(5);
        replay(coll);

        //metoda returnSomethingMore jest niezamockowana metoda, dlatego dziala dalej tak jak powinna


        Assertions.assertEquals(15, coll.returnSomethingMore(5, 3));



    }






}
