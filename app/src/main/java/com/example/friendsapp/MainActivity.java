package com.example.friendsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    AppCompatButton b1,b2;
    String apiUrl ="https://friendsapi-re5a.onrender.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        e1=(EditText) findViewById(R.id.name);
        e2=(EditText) findViewById(R.id.fname);
        e3=(EditText) findViewById(R.id.nickname);
        e4=(EditText) findViewById(R.id.description);
        b1=(AppCompatButton) findViewById(R.id.submitbtn);
        b2=(AppCompatButton) findViewById(R.id.viewbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=e1.getText().toString();
                String getFname=e2.getText().toString();
                String getNickname=e3.getText().toString();
                String getdescription=e4.getText().toString();

                //jason object creation
                JSONObject friend=new JSONObject();
                try {
                    friend.put("name",getName);
                    friend.put("friendName",getFname);
                    friend.put("friendNickName",getNickname);
                    friend.put("DescribeYourFriend",getdescription);
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e);
                }
//object request creation
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                e1.setText(" ");
                                e2.setText(" ");
                                e3.setText(" ");
                                e4.setText(" ");

                                Toast.makeText(getApplicationContext(), "successfully added", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                            }
                        }


                );
                //request queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),view.class);
                startActivity(i);
            }
        });
    }
}