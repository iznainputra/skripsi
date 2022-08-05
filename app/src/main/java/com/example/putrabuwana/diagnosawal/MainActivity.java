package com.example.putrabuwana.diagnosawal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.putrabuwana.diagnosawal.Admin.MenuAdmin;
import com.example.putrabuwana.diagnosawal.Register.Register;
import com.example.putrabuwana.diagnosawal.User.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




public class MainActivity extends AppCompatActivity {
    TextView frgt_pass, mail, eno;
    EditText mTextuser;
    EditText mTextpass, emailing ;
    Button mButtonlog , btn_ok, btn_cancel;
    Button mButtonReg;
    String no;
    String nama;
    String no_tlp;
    String email;
    String tmp_lahir;
    String tgl_lahir;
    String alamat;
    String berat_badan;
    String username;
    String status;
    String stat;
    String getMail;
    ApiForgotPass ApiInterFP;

    ArrayList<DataForgotUser> DataM = null ;

    int success;
    ConnectivityManager conMgr;
    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreferencesadmin;
    Boolean session = false;
    Boolean sessionadmin = false;

    ProgressDialog pDialog;

    private static final String URL_LOGIN = "http://localhost/diagnosawal/api/RestAPI/login_user";
    private static final String URL_GET_MAIL =  "http://localhost/diagnosawal/api/RestAPI/datausbymail";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public final static String TAG_NO = "no";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_NOTLP = "no_tlp";
    public final static String TAG_TMPLAHIR = "tmp_lahir";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_TGLLAHIR = "tgl_lahir";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_BERATBADAN = "berat_badan";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_STATUS = "status";

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String my_shared_preferencesadmin = "my_shared_preferencesadmin";
    public static final String session_status = "session_status";
    public static final String session_statusadmin = "session_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        frgt_pass = (TextView) findViewById(R.id.frgt_pass);
        mTextuser = (EditText) findViewById(R.id.username);
        mTextpass = (EditText) findViewById(R.id.password);
        mButtonlog = (Button) findViewById(R.id.btn_login);
        mButtonReg = (Button) findViewById(R.id.btn_register);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        no = sharedpreferences.getString(TAG_NO, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        no_tlp = sharedpreferences.getString(TAG_NOTLP, null);
        email = sharedpreferences.getString(TAG_EMAIL, null);
        tmp_lahir = sharedpreferences.getString(TAG_TMPLAHIR, null);
        tgl_lahir = sharedpreferences.getString(TAG_TGLLAHIR, null);
        alamat = sharedpreferences.getString(TAG_ALAMAT, null);
        berat_badan = sharedpreferences.getString(TAG_BERATBADAN, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);
        status = sharedpreferences.getString(TAG_STATUS, null);

        if (session) {
            Intent intent = new Intent(MainActivity.this, Menu.class);
            intent.putExtra(TAG_NO, no);
            intent.putExtra(TAG_NAMA, nama);
            intent.putExtra(TAG_NOTLP, no_tlp);
            intent.putExtra(TAG_EMAIL, email);
            intent.putExtra(TAG_TMPLAHIR, tmp_lahir);
            intent.putExtra(TAG_TGLLAHIR, tgl_lahir);
            intent.putExtra(TAG_ALAMAT, alamat);
            intent.putExtra(TAG_BERATBADAN, berat_badan);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_STATUS, status);
            finish();
            startActivity(intent);
        }


        sharedpreferencesadmin = getSharedPreferences(my_shared_preferencesadmin, Context.MODE_PRIVATE);
        sessionadmin = sharedpreferencesadmin.getBoolean(session_statusadmin, false);
        no = sharedpreferencesadmin.getString(TAG_NO, null);
        username = sharedpreferencesadmin.getString(TAG_USERNAME, null);
        status = sharedpreferencesadmin.getString(TAG_STATUS, null);

        if (sessionadmin) {
            Intent intent = new Intent(MainActivity.this, MenuAdmin.class);
            intent.putExtra(TAG_NO, no);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_STATUS, status);
            finish();
            startActivity(intent);
        }

        frgt_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  Intent i = new Intent(getApplicationContext(),frgt_pass.class);
//                  startActivity(i);
//                Toast.makeText(getApplicationContext(), "Forgot Password", Toast.LENGTH_LONG).show();
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.alertdialog_fgtpass,null);
//                pass = (TextView) mView.findViewById(R.id.pass);
                emailing = (EditText) mView.findViewById(R.id.emailing);
                btn_ok = (Button) mView.findViewById(R.id.btn_ok);
                btn_cancel = (Button) mView.findViewById(R.id.btn_cancel);
                mbuilder.setView(mView);
                final AlertDialog dialogfrgt = mbuilder.create();



                dialogfrgt.show();
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMail = emailing.getText().toString();
                        if(!Patterns.EMAIL_ADDRESS.matcher(getMail).matches()){
                            emailing.setError("Fill in a valid email");
                            emailing.requestFocus();
                            return;
                        }
//                        Toast.makeText(MainActivity.this, String.valueOf(getMail), Toast.LENGTH_SHORT).show();
                        loadEmail();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogfrgt.dismiss();
                    }
                });

            }
        });

        mButtonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mTextuser.getText().toString();
                String password = mTextpass.getText().toString();

                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(username, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Column cannot be empty", Toast.LENGTH_LONG).show();
                }
            }
        });


        mButtonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                startActivity(registerIntent);
            }
        });
    }


        private void checkLogin(final String username, final String password) {
            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Logging in...");
            showDialog();

            StringRequest strReq = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "Login Response: " + response.toString());
                    hideDialog();

                    try {
                        JSONObject jOBj = new JSONObject(response);
                        success = jOBj.getInt(TAG_SUCCESS);
                        stat = jOBj.getString(TAG_STATUS);


                        if (success == 1 && stat.equals("0")) {
                            String username = jOBj.getString(TAG_USERNAME);
                            String no = jOBj.getString(TAG_NO);
                            String nama = jOBj.getString(TAG_NAMA);
                            String no_tlp = jOBj.getString(TAG_NOTLP);
                            String email = jOBj.getString(TAG_EMAIL);
                            String tmp_lahir = jOBj.getString(TAG_TMPLAHIR);
                            String tgl_lahir = jOBj.getString(TAG_TGLLAHIR);
                            String alamat = jOBj.getString(TAG_ALAMAT);
                            String berat_badan = jOBj.getString(TAG_BERATBADAN);

                            Log.e("Successfully Login!", jOBj.toString());
                            Toast.makeText(getApplicationContext(), jOBj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean(session_status, true);
                            editor.putString(TAG_NO, no);
                            editor.putString(TAG_NAMA, nama);
                            editor.putString(TAG_NOTLP, no_tlp);
                            editor.putString(TAG_EMAIL, email);
                            editor.putString(TAG_TMPLAHIR, tmp_lahir);
                            editor.putString(TAG_TGLLAHIR, tgl_lahir);
                            editor.putString(TAG_ALAMAT, alamat);
                            editor.putString(TAG_BERATBADAN, berat_badan);
                            editor.putString(TAG_USERNAME, username);
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, Menu.class);
                            intent.putExtra(TAG_NO, no);
                            intent.putExtra(TAG_NAMA, nama);
                            intent.putExtra(TAG_NOTLP, no_tlp);
                            intent.putExtra(TAG_EMAIL, email);
                            intent.putExtra(TAG_TMPLAHIR, tmp_lahir);
                            intent.putExtra(TAG_TGLLAHIR, tgl_lahir);
                            intent.putExtra(TAG_ALAMAT, alamat);
                            intent.putExtra(TAG_BERATBADAN, berat_badan);
                            intent.putExtra(TAG_USERNAME, username);
                            finish();
                            startActivity(intent);

                        } else if (success == 1 && stat.equals("1")) {
                            String username = jOBj.getString(TAG_USERNAME);
                            String no = jOBj.getString(TAG_NO);

                            Log.e("Successfully Login!", jOBj.toString());
                            Toast.makeText(getApplicationContext(), jOBj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                            SharedPreferences.Editor editor = sharedpreferencesadmin.edit();
                            editor.putBoolean(session_statusadmin, true);
                            editor.putString(TAG_NO, no);
                            editor.putString(TAG_USERNAME, username);
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, MenuAdmin.class);
                            intent.putExtra(TAG_NO, no);
                            intent.putExtra(TAG_USERNAME, username);
                            finish();
                            startActivity(intent);

                        } else if (success == 0 && stat.equals("")){
                            Toast.makeText(getApplicationContext(), jOBj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        }
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Login Error" + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                }

            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("password", password);

                    return params;
                }

            };

            // Adding request to request queue
            MySingleton.getInstance(this).addToRequestQueue(strReq);
        }

        private void showDialog(){
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog(){
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
////////////load mail for forgot password////////////////
        private void loadEmail() {
            StringRequest strReq = new StringRequest(Request.Method.POST, URL_GET_MAIL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "Login Response: " + response.toString());


                    try {
                        JSONObject jOBj = new JSONObject(response);
                        success = jOBj.getInt(TAG_SUCCESS);
                        stat = jOBj.getString(TAG_STATUS);


                        if (success == 1 ) {
                            String email = jOBj.getString(TAG_EMAIL);
                            Log.e("Email found!", jOBj.toString());
                            Toast.makeText(getApplicationContext(), jOBj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            emailing.setText("");
                            Intent intent = new Intent(getApplicationContext(),frgt_pass.class);
                            intent.putExtra(TAG_EMAIL, email);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.this, "Email not found!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error" + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                }

            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", getMail);

                    return params;
                }

            };

            // Adding request to request queue
            MySingleton.getInstance(this).addToRequestQueue(strReq);
        }
 ///////////////////////////////////////////////////////////////////////

    }