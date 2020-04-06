package Zad2;

import Code.Car;
import org.easymock.EasyMock;
import org.easymock.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CarEasyTest {
    private Car myFerrari = EasyMock.createMock(Car.class);

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    @Test
    public void test_stubbing_mock(){
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        EasyMock.replay(myFerrari);
        assertTrue(myFerrari.needsFuel());

    }

    @Test
    public void test_exception(){
        EasyMock.expect(myFerrari.needsFuel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }

    @Test
    public void test_needsFuelFalse(){

        EasyMock.expect(myFerrari.needsFuel()).andReturn(false);
        EasyMock.replay(myFerrari);
        assertFalse(myFerrari.needsFuel());


    }

    @Test
    public void test_avg_fuel_usage(){

        EasyMock.expect(myFerrari.avgFuelUsage()).andReturn(12.2);
        EasyMock.replay(myFerrari);

        assertEquals(12.0, myFerrari.avgFuelUsage(),0.3);

    }

    @Test
    public void test_drive_to_exception1(){

        EasyMock.expect(myFerrari.driveTo("Wlochy")).andThrow(new IllegalAccessError());
        EasyMock.replay(myFerrari);
        assertThrows(IllegalAccessError.class, () -> {myFerrari.driveTo("Wlochy");});
    }

    @Test
    public void test_drive_to_exception2(){

        EasyMock.expect(myFerrari.driveTo("")).andThrow(new IllegalArgumentException());
        EasyMock.replay(myFerrari);
        assertThrows(IllegalArgumentException.class, () -> {myFerrari.driveTo("");});
    }
    @Test
    public void test_drive_to_exception3(){

        EasyMock.expect(myFerrari.driveTo(null)).andThrow(new IllegalArgumentException());
        EasyMock.replay(myFerrari);
        assertThrows(IllegalArgumentException.class, () -> {myFerrari.driveTo(null);});
    }

}