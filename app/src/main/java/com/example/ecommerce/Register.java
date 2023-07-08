package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText name,email,pass,compass;
    Button regibtn;
    TextView logintxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.register_name);
        email=findViewById(R.id.register_email);
        pass=findViewById(R.id.register_password);
        compass=findViewById(R.id.register_confirm_password);
        regibtn=findViewById(R.id.register_button);
        logintxt=findViewById(R.id.login_text);
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        regibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reginame = name.getText().toString();
                String regiemail = email.getText().toString();
                String regipass = pass.getText().toString();
                String regicompass = compass.getText().toString();
                if (reginame.isEmpty()) {
                    name.setError("This field need to fill up");

                } else if (regiemail.isEmpty()) {
                    email.setError("This field need to fill up");
                } else if (regipass.isEmpty()) {
                    pass.setError("This field need to fill up");
                } else if (regicompass.isEmpty()) {
                    compass.setError("This field need to fill up");
                }
                else if (!regipass.equals(regicompass))
                {
                    compass.setError("Please Check Comform Password");
                }
                else
                {
                    Retro.callapi().REGISTER_CLASS_CALL(reginame,regiemail,regipass).enqueue(new Callback<RegisterClass>() {
                        @Override
                        public void onResponse(Call<RegisterClass> call, Response<RegisterClass> response) {
                            if (response.body().getConnection()==1)
                            {
                                if (response.body().getResult()==1)
                                {
                                    Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } else if (response.body().getResult()==2)
                                {
                                    Toast.makeText(Register.this, "Already Register", Toast.LENGTH_SHORT).show();
                                }
                                else if (response.body().getResult()==0)
                                {
                                    Toast.makeText(Register.this, "Not Register", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(Register.this, "Something went wrong...!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<RegisterClass> call, Throwable t) {

                        }
                    });                }
            }
        });
    }
}