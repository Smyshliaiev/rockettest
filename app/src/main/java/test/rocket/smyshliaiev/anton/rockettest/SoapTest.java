
package test.rocket.smyshliaiev.anton.rockettest;


import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
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

    public void doRequest() throws IOException, XmlPullParserException {



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
                " <ICAO>LEIB</ICAO>\n" +
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


        //String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Body><response>&lt;?xml version=\"1.0\" encoding=\"utf-8\"?&gt;&lt;REQNOTAM&gt;&lt;RESULT&gt;0&lt;/RESULT&gt;&lt;NOTAMSET ICAO=\"LEIB\"&gt;&lt;NOTAM id=\"B3148/15\"&gt;&lt;ItemQ&gt;LECB/QMXXX/IV/M /A /000/999/3852N00122E&lt;/ItemQ&gt;&lt;ItemA&gt;LEIB&lt;/ItemA&gt;&lt;ItemB&gt;07 MAY 2015 18:07&lt;/ItemB&gt;&lt;ItemC&gt;15 MAY 2015 16:00&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;WIP TWY C IN SHOULDER CLOSE HOLDING POINT RWY06 AND CLOSE HOLDING POINT RWY24&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;NOTAM id=\"B1713/15\"&gt;&lt;ItemQ&gt;LECB/QPITT/I/BO /A /000/999/3852N00122E&lt;/ItemQ&gt;&lt;ItemA&gt;LEIB&lt;/ItemA&gt;&lt;ItemB&gt;30 APR 2015 00:00&lt;/ItemB&gt;&lt;ItemC&gt;13 MAY 2015 23:59&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;TRIGGER NOTAM - PERM AIP AIRAC AMDT 03/15 WEF 30-APR-15 MAGNETIC VARIATION, AIRPORT OPERATION HOURS, LOCAL REGULATIONS, FLIGHT PROCEDURES, MAGNETIC VARIATION, HEADINGS AND AMA UPDATE IN ALL THE CHARTS, NOTES IN STAR CHARTS, 4.3 DME IBA BEARING AT ACL IN IAC/1, 4.5 DME ILS AND 5.6 DME IBA BEARINGS AT ACL IN IAC/4, 1.8 DME IBA BEARING AT MAPT IN IAC/5, OBSTACLE REFERENCE NOTE IN VAC.&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;NOTAM id=\"B0768/15\"&gt;&lt;ItemQ&gt;LECB/QFAAH/IV/NBO /A /0/999/3852N00122E&lt;/ItemQ&gt;&lt;ItemA&gt;LEIB&lt;/ItemA&gt;&lt;ItemB&gt;10 FEB 2015 16:18&lt;/ItemB&gt;&lt;ItemC&gt;PERM&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;REF AD 2-LEIB ITEM 3. HOURS OF OPERATION, MODIFY AS FOLLOWS:-WHERE IT SAYS: AIRPORT: I: FROM NOV 1ST TIL MAR 31ST: 0630-2300-IT SHOULD SAY: AIRPORT: I: FROM NOV 1ST: 0630-2300&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;/NOTAMSET&gt;&lt;/REQNOTAM&gt;</response></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        //readXml(result);
        List<String> itemsq = extractResponseItemQ(result);
        List<String> itemse = extractResponseItemE(result);
        for(String req :itemsq){
            Log.d(TAG, "req itemq: " + req);
        }

        for(String req :itemse){
            Log.d(TAG, "req iteme: " + req);
        }

        Log.d("TAG","result: " + result);

    }

    public void readXml(String xml) throws XmlPullParserException, IOException {
        //String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Body><response>&lt;?xml version=\"1.0\" encoding=\"utf-8\"?&gt;&lt;REQNOTAM&gt;&lt;RESULT&gt;0&lt;/RESULT&gt;&lt;NOTAMSET ICAO=\"EGKA\"&gt;&lt;NOTAM id=\"C1451/15\"&gt;&lt;ItemQ&gt;EGTT/QMRLL/IV/NBO /A /000/999/5050N00018W&lt;/ItemQ&gt;&lt;ItemA&gt;EGKA&lt;/ItemA&gt;&lt;ItemB&gt;15 MAR 2015 10:30&lt;/ItemB&gt;&lt;ItemC&gt;10 JUN 2015 19:00&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;GRASS RWY 07/25 DECLARED DISTANCES (IN METRES):RWY 07: TORA 682 TODA 682 ASDA 682 LDA 682RWY 25: TORA 682 TODA 682 ASDA 682 LDA 599RWY 07 THR DISPLACED BY 195M, INDICATED BY BLACK/WHITE BOARDS&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;/NOTAMSET&gt;&lt;/REQNOTAM&gt;</response></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        String line = xml;
        //String pattern = "(?<=ItemE&gt;).*?(?=&lt;/ItemE)";
        String pattern = "(<response>).*?(</response>)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        String getResponseXml = null;

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find( )) {
            getResponseXml = m.group(0);
            System.out.println("Found value: " + getResponseXml );
//            System.out.println("Found value: " + m.group(1) );
//            System.out.println("Found value: " + m.group(2) );
        } else {
            System.out.println("NO MATCH");
        }

        String response = extractResponse(getResponseXml);

        InputStream stream = new ByteArrayInputStream(response.getBytes(Charset.forName("UTF-8")));

        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(stream, null);
        parser.nextTag();

        List<Reqnotam> list = readFeed(parser);

        for(Reqnotam req :list){
            Log.d(TAG, "req: " + req);
        }

    }

    public List<String> extractResponseItemQ(String xml){

        List<String> itemq = new ArrayList<>();

        String pattern = "ItemQ(.+?)/ItemQ";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);

        // Now create matcher object.
        Matcher m = r.matcher(xml);
        while (m.find( )) {
            itemq.add(m.group(1));
        }

        return itemq;
    }

    public List<String> extractResponseItemE(String xml){

        List<String> itemq = new ArrayList<>();

        String pattern = "ItemE(.+?)/ItemE";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern, Pattern.MULTILINE);

        // Now create matcher object.
        Matcher m = r.matcher(xml);
        while (m.find( )) {
            itemq.add(m.group(1));
        }

        return itemq;
    }


    public String extractResponse(String xml){
        //String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Body><response>&lt;?xml version=\"1.0\" encoding=\"utf-8\"?&gt;&lt;REQNOTAM&gt;&lt;RESULT&gt;0&lt;/RESULT&gt;&lt;NOTAMSET ICAO=\"LEIB\"&gt;&lt;NOTAM id=\"B3148/15\"&gt;&lt;ItemQ&gt;LECB/QMXXX/IV/M /A /000/999/3852N00122E&lt;/ItemQ&gt;&lt;ItemA&gt;LEIB&lt;/ItemA&gt;&lt;ItemB&gt;07 MAY 2015 18:07&lt;/ItemB&gt;&lt;ItemC&gt;15 MAY 2015 16:00&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;WIP TWY C IN SHOULDER CLOSE HOLDING POINT RWY06 AND CLOSE HOLDING POINT RWY24&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;NOTAM id=\"B1713/15\"&gt;&lt;ItemQ&gt;LECB/QPITT/I/BO /A /000/999/3852N00122E&lt;/ItemQ&gt;&lt;ItemA&gt;LEIB&lt;/ItemA&gt;&lt;ItemB&gt;30 APR 2015 00:00&lt;/ItemB&gt;&lt;ItemC&gt;13 MAY 2015 23:59&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;TRIGGER NOTAM - PERM AIP AIRAC AMDT 03/15 WEF 30-APR-15 MAGNETIC VARIATION, AIRPORT OPERATION HOURS, LOCAL REGULATIONS, FLIGHT PROCEDURES, MAGNETIC VARIATION, HEADINGS AND AMA UPDATE IN ALL THE CHARTS, NOTES IN STAR CHARTS, 4.3 DME IBA BEARING AT ACL IN IAC/1, 4.5 DME ILS AND 5.6 DME IBA BEARINGS AT ACL IN IAC/4, 1.8 DME IBA BEARING AT MAPT IN IAC/5, OBSTACLE REFERENCE NOTE IN VAC.&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;NOTAM id=\"B0768/15\"&gt;&lt;ItemQ&gt;LECB/QFAAH/IV/NBO /A /0/999/3852N00122E&lt;/ItemQ&gt;&lt;ItemA&gt;LEIB&lt;/ItemA&gt;&lt;ItemB&gt;10 FEB 2015 16:18&lt;/ItemB&gt;&lt;ItemC&gt;PERM&lt;/ItemC&gt;&lt;ItemD&gt;&lt;/ItemD&gt;&lt;ItemE&gt;REF AD 2-LEIB ITEM 3. HOURS OF OPERATION, MODIFY AS FOLLOWS:-WHERE IT SAYS: AIRPORT: I: FROM NOV 1ST TIL MAR 31ST: 0630-2300-IT SHOULD SAY: AIRPORT: I: FROM NOV 1ST: 0630-2300&lt;/ItemE&gt;&lt;/NOTAM&gt;&lt;/NOTAMSET&gt;&lt;/REQNOTAM&gt;</response></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        String line = xml;
        String pattern = "(<response>).*?(</response>)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);

        String response = "";
        while (m.find( )) {
            response = m.group(0);
                System.out.println("Found value " + response);

        }

        return response;
    }


    private List<Reqnotam> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, null, "response");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("entry")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Reqnotam readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "REQNOTAM");
        String title = null;
        String summary = null;
        String link = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("ITEMQ")) {
                title = readTitle(parser);
            } else {
                skip(parser);
            }
        }
        return new Reqnotam(title);
    }

    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "ITEMQ");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "ITEMQ");
        return title;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    public static class Reqnotam {
        public final String itemq;

        private Reqnotam(String itemq) {
            this.itemq = itemq;
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
            } catch (XmlPullParserException e) {
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
