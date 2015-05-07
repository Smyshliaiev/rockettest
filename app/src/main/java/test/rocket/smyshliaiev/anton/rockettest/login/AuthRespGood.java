package test.rocket.smyshliaiev.anton.rockettest.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Toxa on 07.05.2015.
 */
@Root(name="AUTH")
public class AuthRespGood {
    @Element(name="RESULT")
    private String result;
    @Element(name="KEY")
    private String key;
    @Element(name="ISSUBSCRIPT")
    private String issubscript;
    @Element(name="EXPDATE")
    private String expdate;

}
