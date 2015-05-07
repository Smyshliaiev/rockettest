package test.rocket.smyshliaiev.anton.rockettest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;

import java.io.IOException;

import test.rocket.smyshliaiev.anton.rockettest.login.LoginTest;

public class MainActivity extends ActionBarActivity {

    private Button mTestButtonSoap;
    private Button mTestButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        mTestButtonLogin = (Button)findViewById(R.id.button_login);
        mTestButtonLogin.setOnClickListener(mTestClickListenerLogin);


        mTestButtonSoap = (Button)findViewById(R.id.button_soap);
        mTestButtonSoap.setOnClickListener(mTestClickListenerSoap);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener mTestClickListenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LoginTest loginTest = new LoginTest(getApplicationContext());
            loginTest.testLogin();
        }
    };

    View.OnClickListener mTestClickListenerSoap = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            SoapTest soapTest = new SoapTest();
            soapTest.doRequest();

        }
    };

//    public String httpPost(String method, String data)
//            throws ClientProtocolException, IOException {
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost(method);
//
//    /* headers */
//        httppost.setHeader("Accept", "application/xml");
//        httppost.setHeader("Content-Type", "application/xml");
//
//    /* authentication */
//        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
//                username + ":" + apiKey);
//        httpclient.getCredentialsProvider().setCredentials(
//                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
//                credentials);
//
//        StringEntity se = new StringEntity(data, HTTP.UTF_8);
//        se.setContentType("text/xml");
//        httppost.setEntity(se);
//
//    /* execute */
//        BasicHttpResponse httpResponse = null;
//        httpResponse = (BasicHttpResponse) httpclient.execute(httppost);
//    /* return response */
//
//
//        return TextHelper.GetText(httpResponse);
//    }


}
