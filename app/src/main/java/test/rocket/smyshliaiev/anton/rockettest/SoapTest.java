
package test.rocket.smyshliaiev.anton.rockettest;


import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Toxa on 07.05.2015.
 */
public class SoapTest {
    private static final String NAMESPACE = "urn:xmethods-notam";
    private static final String URL_LINK = " https://apidev.rocketroute.com/notam/v1/service.wsdl";
    private static final String METHOD_NAME = "getNotam";
    private static final String SOAP_ACTION = "urn:xmethods-notam#getNotam";

//    private static final String[] sampleACTV = new String[] {
//            "android", "iphone", "blackberry"
//    };
    private static final String TAG = SoapTest.class.getSimpleName();

    public void doRequest() throws IOException {
 /*       String reqXML = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <SOAP-ENV:Body>\n" +
                "      <request><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                " <REQNOTAM>\n" +
                " <USR>user.name@gmail.com</USR>\n" +
                " <PASSWD>222222</PASSWD>\n" +
                " <ICAO>EGKA</ICAO>\n" +
                " <ICAO>LFAT</ICAO>\n" +
                " </REQNOTAM>]]></request>\n" +
                "   </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";


        String req = constuctSOAPRequest(reqXML);
        sendSOAPRequest("https://apidev.rocketroute.com/notam/v1/service.wsdl",req,"urn:xmethods-notam#getNotam");
        getSOAPResponse("https://apidev.rocketroute.com/notam/v1/service.wsdl",req,"urn:xmethods-notam#getNotam", "");
*/


        URL oURL = new URL("https://apidev.rocketroute.com/notam/v1/");
        HttpsURLConnection con = (HttpsURLConnection) oURL.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "text/xml; charset=utf-8");
        con.setRequestProperty("accept-charset", "UTF-8");
//        con.setRequestProperty("Accept-Encoding", "gzip");
        con.setRequestProperty("SOAPAction", "urn:xmethods-notam#getNotam");
        //con.setRequestProperty("Accept-Encoding", "gzip");

        con.setDoOutput(true);

        String reqXML = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <SOAP-ENV:Body>\n" +
                "      <request><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                " <REQNOTAM>\n" +
                " <USR>anton.emale@gmail.com</USR>\n" +
                " <PASSWD>ec118fa4b3679e3a2460a032ab300269</PASSWD>\n" +
                " <ICAO>EGKA</ICAO>\n" +
//              " <KEY>4aef59a8e958c87e89e98409d7c9ba7a</KEY>\n" +
                " </REQNOTAM>]]></request>\n" +
                "   </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";

        OutputStream reqStream = con.getOutputStream();
        reqStream.write(reqXML.getBytes());

//        InputStream resStream = con.getInputStream();
//        byte[] byteBuf = new byte[10240];
//        int len = resStream.read(byteBuf);

        StringBuffer sb = new StringBuffer();
        BufferedInputStream is = new BufferedInputStream(con.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while ((inputLine = br.readLine()) != null) {
            inputLine = java.net.URLDecoder.decode(inputLine, "UTF-8");
            sb.append(inputLine);
            Log.d("TAG", "inputLine: " + inputLine);
        }
        String result = sb.toString();
        dodo(result);
        Log.d("TAG","result: " + result);

    }

    public void dodo(String xml){
        //String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Body><response>&lt;?xml version=\"1.0\" encoding=\"utf-8\"?&gt;&lt;REQNOTAM&gt;&lt;RESULT&gt;0&lt;/RESULT&gt;&lt;NOTAMSET ICAO=\"EGKA\"&gt;&lt;NOTAM id=\"C1451/15\"&gt;&lt;ItemQ&gt;EGTT/QMRLL/IV/NBO /A /000/999/5050N00018W&lt;/ItemQ&gt;&lt;ItemA&gt;EGKA&lt;/ItemA&gt;&lt;ItemB&gt;15 MAR 2015 10:30&lt;/ItemB&gt;&lt;ItemC&gt;10 JUN 2015 19:00&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;GRASS RWY 07/25 DECLARED DISTANCES (IN METRES):RWY 07: TORA 682 TODA 682 ASDA 682 LDA 682RWY 25: TORA 682 TODA 682 ASDA 682 LDA 599RWY 07 THR DISPLACED BY 195M, INDICATED BY BLACK/WHITE BOARDS&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;/NOTAMSET&gt;&lt;/REQNOTAM&gt;</response></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        String line = xml;
        String pattern = "(?<=ItemE&gt;).*?(?=&lt;/ItemE)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
//            System.out.println("Found value: " + m.group(1) );
//            System.out.println("Found value: " + m.group(2) );
        } else {
            System.out.println("NO MATCH");
        }
    }


    public void doAsynJob(){
        MyTask myTask = new MyTask();
        myTask.execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                doRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    private static String getSOAPResponse(
            String SOAPUrl,
            String SOAPRequestXML,
            String SOAPAction,
            String resultTag) {

        String result = new String();
        String inString = new String();

        try {
            // Send a SOAP request to the webservice, return the HttpConnection used.
            HttpsURLConnection httpConn =
                    SoapTest.sendSOAPRequest(
                            SOAPUrl,
                            SOAPRequestXML,
                            SOAPAction);

            // If the connection has worked and is still active, read the response into an XML Document.
            if (httpConn != null) {
                BufferedReader in =
                        new BufferedReader(
     /*epic fail here */               new InputStreamReader(httpConn.getInputStream()));

                while ((inString = in.readLine()) != null) {

                    System.out.println(inString);
                }
                in.close();
            } else {
                result = "CSGERROR : Connection Issue ";
            }
        } catch (Exception e) {
            result = "CSGERROR : " + e.toString();
        }

        System.out.println("");
        System.out.println(result);
        return result;
    }

    /*
     * Send the XML Soap request to a web service.
     */
    private static HttpsURLConnection sendSOAPRequest(
            String SOAPUrl,
            String SOAPRequestXML,
            String SOAPAction) {

        HttpsURLConnection httpConn = null;

        try {
            // Create the connection where we're going to send the file.
            URL url = new URL(SOAPUrl);


            //       Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            // Trust always
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            // Trust always
                        }
                    }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            // Create empty HostnameVerifier
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            };

            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            URLConnection connection = url.openConnection();
            httpConn = (HttpsURLConnection) connection;

            // Copy the string to a byte array to retrieve length.
            // for the HTTP content length property.

            byte[] byteAry = SOAPRequestXML.getBytes();

            // Set the appropriate HTTP parameters.
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Host", "apidev.rocketroute.com");

            httpConn.setRequestProperty(
                    "Content-Type",
                    "text/xml; charset=utf-8");

            httpConn.setRequestProperty(
                    "Content-Length",
                    String.valueOf(byteAry.length));

            httpConn.setRequestProperty("SOAPAction", SOAPAction);

            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);

            //getConnectionInformation(httpConn);


            // Send the SOAP XML to the webservice.
            OutputStream out = httpConn.getOutputStream();
            out.write(byteAry);
            out.close();

        } catch (MalformedURLException e) {
            httpConn = null;
        } catch (IOException e) {
            httpConn = null;
        } catch (Exception e) {
            httpConn = null;
        }

        // Return the Http Connection
        //     return httpConn;
        return httpConn;
    }

    /*
     * Construct Soap request XML String.
     */
    private static String constuctSOAPRequest(
            String xmldata) {

        String SOAPRequestXML = new String();

//        SOAPRequestXML =
//                SOAPRequestXML.concat(
//                        "<?xml version=\"1.0\"?>");
//        SOAPRequestXML = SOAPRequestXML.concat("<soap:Envelope ");
//        SOAPRequestXML =
//                SOAPRequestXML.concat(
//                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
//        SOAPRequestXML =
//                SOAPRequestXML.concat(
//                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
//        SOAPRequestXML =
//                SOAPRequestXML.concat(
//                        "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
//        SOAPRequestXML = SOAPRequestXML.concat("<soap:Body>");

        SOAPRequestXML =
                SOAPRequestXML.concat(xmldata);

//        SOAPRequestXML =
//                SOAPRequestXML.concat("</ProcessMsg>");
//        SOAPRequestXML = SOAPRequestXML.concat("</soap:Body>");
//        SOAPRequestXML = SOAPRequestXML.concat("</soap:Envelope>");

        return SOAPRequestXML;
    }

}
