package test.rocket.smyshliaiev.anton.rockettest.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Toxa on 08.05.2015.
 */
@Root(name="AUTH")
public class AuthRespBad extends AuthResp{
    @Element(name="MESSAGES", required=false)
    private Msg messages;

    @Override
    public String toString() {
        return "AuthRespBad{" +
                "result='" + result + '\'' +
                ", messages=" + messages +
                '}';
    }
}
