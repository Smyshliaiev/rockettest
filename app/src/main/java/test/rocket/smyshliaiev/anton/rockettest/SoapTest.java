package test.rocket.smyshliaiev.anton.rockettest;

import android.util.Log;
import android.widget.AutoCompleteTextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Toxa on 07.05.2015.
 */
public class SoapTest {
    private static final String NAMESPACE = "urn:xmethods-notam";
    private static final String URL = " https://apidev.rocketroute.com/notam/v1/service.wsdl";
    private static final String METHOD_NAME = "getNotam";
    private static final String SOAP_ACTION = "getNotam";

//    private static final String[] sampleACTV = new String[] {
//            "android", "iphone", "blackberry"
//    };
    private static final String TAG = SoapTest.class.getSimpleName();

    public void doRequest(){
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
            Log.d(TAG, "Received :" + resultsRequestSOAP.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
