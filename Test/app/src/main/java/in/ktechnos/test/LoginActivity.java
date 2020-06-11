package in.ktechnos.test;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import in.ktechnos.test.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG= LoginActivity.class.getSimpleName();
    ActivityLoginBinding loginBinding;
    LoginViewModel loginViewmodel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();



    }
    private void init()
    {

        loginBinding= DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewmodel= ViewModelProviders.of(this,new LoginViewModelFactory(this, new LoginUser())).get(LoginViewModel.class);
        loginBinding.setLoginviewModel(loginViewmodel);
        loginBinding.executePendingBindings();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


