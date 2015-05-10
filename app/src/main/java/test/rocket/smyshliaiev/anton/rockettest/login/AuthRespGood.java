package test.rocket.smyshliaiev.anton.rockettest.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Toxa on 07.05.2015.
 */
@Root(name="AUTH")
public class AuthRespGood extends AuthResp{

    @Element(name="KEY")
    private String key;
    @Element(name="ISSUBSCRIPT", required=false)
    private String issubscript;
    @Element(name="EXPDATE", required=false)
    private String expdate;
    @Element(name="FIRSTNAME", required=false)
    private String firstname;
    @Element(name="LASTNAME", required=false)
    private String lastname;
    @Element(name="EMAIL", required=false)
    private String email;
    @Element(name="CURRANT_PACKAGE_TITLE")
    private String currant_package_title;
    @Element(name="ENDDATE", required=false)
    private String enddate;


    public String getKey() {
        return key;
    }
}
