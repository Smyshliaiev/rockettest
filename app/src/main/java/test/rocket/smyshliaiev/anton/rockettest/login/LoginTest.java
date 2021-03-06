package test.rocket.smyshliaiev.anton.rockettest.login;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Toxa on 07.05.2015.
 */
public class LoginTest {


    private static final String TAG = LoginTest.class.getSimpleName();
    private Context mContext;

    public LoginTest(Context mContext) {
        this.mContext = mContext;
    }

    public void testLogin()  {

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
                    String xmlQuery = mapLoginToXml();
                    sendHttpPostLogin(xmlQuery);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }

    private void sendHttpPostLogin(String xmlString){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("https://fly.rocketroute.com/remote/auth");


        try {
            StringEntity se = new StringEntity( xmlString, HTTP.UTF_8);
            se.setContentType("text/xml");
            httppost.setEntity(se);

            HttpResponse httpresponse = httpclient.execute(httppost);
            HttpEntity resEntity = httpresponse.getEntity();
            String answer = EntityUtils.toString(resEntity);
            Log.d(TAG, "EntityUtils.toString(resEntity): " + answer);
            Log.d(TAG, "answer: " + mapLoginFromXml(answer));

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String mapLoginToXml() throws Exception {

        Serializer serializer = new Persister();
        AuthReq authReq = new AuthReq();

        authReq.setUsr("anton.emale@gmail.com");
        authReq.setPasswd(getMd5("mordskerl"));
        //authReq.setDeviceid("1299f2aa8935b9ffabcd4a2cbcd16b8d45691629");
        authReq.setDeviceid(getDeviceId());
        authReq.setPcategory("RocketRoute");
        authReq.setAppmd5("0Q4eaueC>c2517yGT41PKYt");



        File result = new File(Environment.getExternalStorageDirectory(), "auth.xml");
        serializer.write(authReq, result);

        String ret = deserializeString(result);
        return ret;
    }

    private String mapLoginFromXml(String xml) throws Exception {
        Serializer serializer = new Persister();
//        File result = new File(Environment.getExternalStorageDirectory(), "auth.xml");
//        serializer.write(xml, result);

        AuthRespBad answer = serializer.read(AuthRespBad.class, new StringReader(xml));

        int a=0;
        a++;

        return answer.toString();
    }


    private String getDeviceId(){
        WifiManager wm = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getMacAddress();
    }


    public static String deserializeString(File file)
            throws IOException {
        int len;
        char[] chr = new char[4096];
        final StringBuffer buffer = new StringBuffer();
        final FileReader reader = new FileReader(file);
        try {
            while ((len = reader.read(chr)) > 0) {
                buffer.append(chr, 0, len);
            }
        } finally {
            reader.close();
        }
        return buffer.toString();
    }

    private String getMd5(String src) throws NoSuchAlgorithmException, IOException {

//        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(src);


            MessageDigest mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(src.getBytes(), 0, src.length());
            String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
            return md5;
    }

//    public String constructXml(String userName, String userPsw, String userKey){
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("<AUTH>");
//        sb.append("<USR>"+ userName +"</USR>");
//        sb.append("<PASSWD>"+ userPsw +"</PASSWD>");
//        sb.append("<DEVICEID>"+ "1299f2aa8935b9ffabcd4a2cbcd16b8d45691629" +"</DEVICEID>");
//        sb.append("<PCATEGORY>"+ "RocketRoute" +"</PCATEGORY>");
//        sb.append("<APPMD5>"+ userKey +"</APPMD5>");
//        sb.append("</AUTH>");
//
//        return sb.toString();
//    }
}
