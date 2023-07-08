package com.example.ecommerce;

import static com.example.ecommerce.MainActivity.editor;
import static com.example.ecommerce.MainActivity.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText loginemail,loginpass;
    Button loginbtn;
    TextView registertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginemail=findViewById(R.id.loginTextEmail);
        loginpass=findViewById(R.id.loginTextPassword);
        loginbtn=findViewById(R.id.LoginButton);
        registertxt=findViewById(R.id.register_txt);
        registertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }

        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass;
                email=loginemail.getText().toString();
                pass=loginpass.getText().toString();
                if (email.isEmpty()) {
                    loginemail.setError("This field need to fill up");
                } else if (pass.isEmpty()) {
                    loginpass.setError("This field need to fill up");
                }
                else
                {
                    Retro.callapi().LOGIN_CLASS_CALL(email,pass).enqueue(new Callback<LoginClass>() {
                        @Override
                        public void onResponse(Call<LoginClass> call, Response<LoginClass> response) {
                            if (response.body().getConnection()==1)
                            {
                                if (response.body().getResult()==1) {
                                    editor.putBoolean("islogin",true);
                                    editor.commit();
                                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, Ecommerce.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Login.this, "You Hace Not Register", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginClass> call, Throwable t) {

                        }
                    });
                }

                }
        });
    }
}