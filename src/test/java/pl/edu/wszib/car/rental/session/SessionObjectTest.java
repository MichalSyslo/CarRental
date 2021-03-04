package pl.edu.wszib.car.rental.session;


import org.junit.Assert;
import org.junit.Test;


public class SessionObjectTest {

    @Test
    public void getInfoTest(){
        SessionObject sessionObject = new SessionObject();
        String info = "infoExample";

        Assert.assertNull(sessionObject.getInfo());
        sessionObject.setInfo("infoExample");
        Assert.assertEquals(info, sessionObject.getInfo());
        Assert.assertNull(sessionObject.getInfo());
    }
}
