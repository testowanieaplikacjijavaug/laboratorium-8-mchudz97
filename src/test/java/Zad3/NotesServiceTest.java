package Zad3;

import Code.Note;
import Code.NotesServiceImpl;
import Code.NotesStorage;
import Mocks.NotesStorageMock;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class NotesServiceTest {

    @TestSubject
    NotesServiceImpl serviceImpl;
    NotesStorage nsMock;
    Note note;



    @BeforeEach
    private void setup(){

        nsMock = EasyMock.createMock(NotesStorage.class);
        serviceImpl = NotesServiceImpl.createWith(nsMock);
        note = EasyMock.createMock(MockType.NICE, Note.class);

    }

    @Test
    @DisplayName("When added 1 time")
    public void noteServiceAdd(){

        List<Note> n = new ArrayList<Note>(0);
        serviceImpl.add(note);

        EasyMock.expectLastCall().andAnswer(()->{

                n.add((Note)EasyMock.getCurrentArguments()[0]);
                return null;

        }).times(1);

        EasyMock.replay(nsMock);

        serviceImpl.add(note);

        assertThat(n.size()).isEqualTo(1);

    }


    @Test
    @DisplayName("Checking clear method")
    void noteServiceClearTest(){

        List<Note> n = new ArrayList<Note>(0);
        n.add(note);
        serviceImpl.clear();
        EasyMock.expectLastCall().andAnswer(()->{

            n.clear();
            return null;

        }).times(1);

        EasyMock.replay(nsMock);

        serviceImpl.clear();

       // assertThat(n.size()).isEqualTo(0);

        EasyMock.verify();

    }

    @Test
    @DisplayName("Average test for same values")
    void noteServiceAverageTest1(){

        EasyMock.expect(note.getNote()).andReturn(3f);



        nsMock.getAllNotesOf("nan");
        EasyMock.expectLastCall().andAnswer(()->{

            ArrayList<Note> arr = new ArrayList<Note>(0);
            arr.add(note);
            return arr;

        }).times(1);

        EasyMock.replay(nsMock, note);



        assertThat(serviceImpl.averageOf("nan")).isEqualTo(3f);

    }

    @Test
    @DisplayName("Average test for 2 different names")
    void noteServiceAverageTest2(){

        Note note1 = EasyMock.createMock(Note.class);
        EasyMock.expect(note.getName()).andReturn("nan");
        EasyMock.expect(note.getNote()).andReturn(3f);
        EasyMock.expect(note1.getName()).andReturn("null");
        EasyMock.expect(note1.getNote()).andReturn(2f);

        ArrayList<Note> n = new ArrayList<Note>(0);
        n.add(note1);
        n.add(note);

        nsMock.getAllNotesOf("nan");
        EasyMock.expectLastCall().andAnswer(()->{

            ArrayList<Note> arr = new ArrayList<Note>(0);

            for(int i=0; i<n.size(); i++){

                if(n.get(i).getName() == (String)EasyMock.getCurrentArgument(0)){

                    arr.add(n.get(i));

                }

            }


            return arr;

        }).times(1);

        EasyMock.replay(nsMock, note, note1);

        assertThat(serviceImpl.averageOf("nan")).isEqualTo(3);

    }

    @Test
    @DisplayName("Average test for different values")
    void noteServiceAverageTest3(){

        Note note1 = EasyMock.createMock(Note.class);
        EasyMock.expect(note.getName()).andReturn("nan");
        EasyMock.expect(note.getNote()).andReturn(3f);
        EasyMock.expect(note1.getName()).andReturn("nan");
        EasyMock.expect(note1.getNote()).andReturn(2f);

        ArrayList<Note> n = new ArrayList<Note>(0);
        n.add(note1);
        n.add(note);

        nsMock.getAllNotesOf("nan");
        EasyMock.expectLastCall().andAnswer(()->{

            ArrayList<Note> arr = new ArrayList<Note>(0);

            for(int i=0; i<n.size(); i++){

                if(n.get(i).getName() == (String)EasyMock.getCurrentArgument(0)){

                    arr.add(n.get(i));

                }

            }


            return arr;

        }).times(1);

        EasyMock.replay(nsMock, note, note1);

        assertThat(serviceImpl.averageOf("nan")).isEqualTo((float)5/2);

    }




}