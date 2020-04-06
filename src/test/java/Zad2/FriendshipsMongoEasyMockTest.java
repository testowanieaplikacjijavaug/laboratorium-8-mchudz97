package Zad2;

import Code.FriendsCollection;
import Code.FriendshipsMongo;
import Code.Person;
import org.assertj.core.api.Assertions;
import org.easymock.*;
import org.easymock.EasyMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(EasyMockExtension.class)
public class FriendshipsMongoEasyMockTest {

    @TestSubject
    FriendshipsMongo friendships = new FriendshipsMongo();

    //A nice mock expects recorded calls in any order and returning null for other calls
    @Mock(type = MockType.NICE)
    FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected(){
        Person joe = new Person("Joe");
        //Zapisanie zachowania - co sie ma stac
        expect(friends.findByName("Joe")).andReturn(joe);
        //Odpalenie obiektu do sprawdzenia zachowania
        EasyMock.replay(friends);
        Assertions.assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends(){
        Assertions.assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends(){
        List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
        Person joe = createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(expected);
        EasyMock.replay(friends);
        EasyMock.replay(joe);
        Assertions.assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
    }




}