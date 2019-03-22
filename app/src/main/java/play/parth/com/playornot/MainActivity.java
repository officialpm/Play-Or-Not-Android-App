package play.parth.com.playornot;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public String soutlook,shumidity,stemperature,swindy,o,t,h,w;
    private Button predict;
    TextView res;
    String URL = "http://parthdmaniar.pythonanywhere.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = (TextView)findViewById(R.id.res);
        predict=(Button)findViewById(R.id.button);
        Spinner outlook =  (Spinner) findViewById(R.id.spinner1);
        Spinner temps =  (Spinner) findViewById(R.id.spinner2);
        Spinner humi =  (Spinner) findViewById(R.id.spinner3);
        Spinner windy =  (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.outlooks, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        outlook.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.temperatures, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        temps.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.humiditys, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        humi.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.windys, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        windy.setAdapter(adapter4);


        outlook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               soutlook = adapterView.getItemAtPosition(i).toString();
                o = Integer.toString(i+1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        temps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               stemperature = adapterView.getItemAtPosition(i).toString();
                t = Integer.toString(i+1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        humi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                shumidity = adapterView.getItemAtPosition(i).toString();
                h = Integer.toString(i+1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        windy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                swindy = adapterView.getItemAtPosition(i).toString();
                w = Integer.toString(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(getApplicationContext(), "Select", Toast.LENGTH_SHORT).show();

            }
        });

predict.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


       String newurl = URL+"?outlook="+o+"&temperature="+t+"&humidity="+h+"&windy="+w;

        //Toast.makeText(getApplicationContext(), newurl, Toast.LENGTH_SHORT).show();


       StringRequest stringRequest=new StringRequest(Request.Method.POST, newurl,
               new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               try {

                   JSONObject jsonObject = new JSONObject(response);
                   String msg = jsonObject.getString("result");



                   if(msg.equals("[1]"))
                   {

                        res.setText("PLAY SAFELY \n ENJOY ;)");
                       res.setTextColor(Color.parseColor("#42f46b"));
                       Toast.makeText(MainActivity.this, "ENJOY",
                               Toast.LENGTH_SHORT).show();


                   }
                   else if(msg.equals("[0]"))
                   {
                       res.setText("ALERT: DONT'T PLAY");
                       res.setTextColor(Color.parseColor("#f44141"));
                       Toast.makeText(MainActivity.this, "ALERT",
                               Toast.LENGTH_SHORT).show();

                   }
                   else
                   {
                       //Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
                   }

               }
               catch(JSONException e)

               {

                   Toast.makeText(MainActivity.this, "Server Error",
                           Toast.LENGTH_SHORT).show();

               }




           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
               Toast.makeText(MainActivity.this, "Error in Logging In !"+error.toString(),
                       Toast.LENGTH_SHORT).show();

           }
       }) {
           @Override
           protected Map<String, String> getParams() {


               Map<String, String> params = new HashMap<>();
               params.put("outlook",o);
               params.put("temperature",t);
               params.put("humidity",h);
               params.put("windy",w);

               //  params.put("password",u_password);
               return params;


           }
       };
        RequestQueue requestQueue =  Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);


    }



});



    }
}
