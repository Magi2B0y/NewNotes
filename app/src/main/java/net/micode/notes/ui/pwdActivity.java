package net.micode.notes.ui;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import net.micode.notes.R;
import net.micode.notes.util.ToastUtil;

public class pwdActivity extends NotesListActivity {

    public Button mBtnpwd;
    public EditText mEtpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);

        mBtnpwd = findViewById(R.id.btn_pwd);
        mEtpwd = findViewById(R.id.et_1);


        mBtnpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = mEtpwd.getText().toString();
                Intent intent=null;

                if(pwd.equals("123")){
                    intent = new Intent(pwdActivity.this,SafeFolderActivity.class);
                    startActivity(intent);
                }else{
                    ToastUtil.toastShort(pwdActivity.this,"ERROR");
                }

            }
        });


    }

}