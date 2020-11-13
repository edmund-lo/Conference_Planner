import org.junit.*;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.Assert.*;

public class EventTests{

    @Test(timeout=50)
    public void testCreation(){
        EventManager em = new EventManager(new HashMap<String, Event>());
        LocalDateTime startTime = LocalDateTime.parse("2015-02-20T06:30:00");
        LocalDateTime endTime = startTime.plusHours(1);
        em.createNewEvent("TestingConv",startTime, endTime);
    }
}