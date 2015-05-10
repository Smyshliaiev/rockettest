package test.rocket.smyshliaiev.anton.rockettest.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Toxa on 10.05.2015.
 */
@Root(name="AUTH")
public class AuthResp {
    @Element(name="RESULT")
    protected String result;

    public String getResult() {
        return result;
    }

}
