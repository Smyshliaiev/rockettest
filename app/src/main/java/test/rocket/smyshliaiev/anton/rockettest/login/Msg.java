package test.rocket.smyshliaiev.anton.rockettest.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Toxa on 08.05.2015.
 */
@Root
public class Msg {
    @ElementList(name="MSG")
    private ArrayList<String> msg;

    @Override
    public String toString() {
        return "Msg{" +
                "msg=" + msg +
                '}';
    }
}
