package test.rocket.smyshliaiev.anton.rockettest.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Toxa on 07.05.2015.
 */

@Root(name="AUTH")
public class AuthReq {
    @Element(name="USR")
    private String usr;
    @Element(name="PASSWD")
    private String passwd;
    @Element(name="DEVICEID")
    private String deviceid;
    @Element(name="PCATEGORY")
    private String pcategory;
    @Element(name="APPMD5")
    private String appmd5;

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public void setPcategory(String pcategory) {
        this.pcategory = pcategory;
    }

    public void setAppmd5(String appmd5) {
        this.appmd5 = appmd5;
    }
}
