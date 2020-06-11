package in.ktechnos.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import in.ktechnos.test.profile.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {

    private static final String TAG= LoginViewModel.class.getSimpleName();
    public MutableLiveData<String> email= new MutableLiveData<>();
    public MutableLiveData<String> password= new MutableLiveData<>();
    public LoginUser loginModel;
    private Context context;
    private ProgressDialog progressDialog;


    public LoginViewModel(Context context, LoginUser loginModel) {
        this.context = context;
        this.loginModel = loginModel;
        progressDialog= new ProgressDialog(context);
        progressDialog.setMessage("Please wait ...");
    }


    public  void loginButton()
    {
        if(isNetworkConnected())
        {
            Log.d(TAG,"Email: "+email.getValue());
            loginModel.setEmail(email.getValue());
            loginModel.setPassword(password.getValue());
            if(loginModel.isValid())
            {
                userLogin(email.getValue(),password.getValue());
            }
            else
            {
                Toast.makeText(context,"invalid credentials",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {

            Toast.makeText(context,"Check Internet Connection!",Toast.LENGTH_LONG).show();
        }

    }





    private void userLogin(String email,String password){

        progressDialog.show();

        LoginUser loginUser = new LoginUser(email,password);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginRequest vInterface = retrofit.create(LoginRequest.class);
        Call<Root> serverCom = vInterface.loginUser(loginUser);
        serverCom.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                int code = response.code();

                if (code==403){
                    progressDialog.dismiss();
                    Toast.makeText(context, "User Not Exist", Toast.LENGTH_LONG).show();
                }
                else if (code==405){
                    progressDialog.dismiss();
                    Toast.makeText(context, "Password not match", Toast.LENGTH_LONG).show();
                }
                else if (code==406){
                    progressDialog.dismiss();
                    Toast.makeText(context, "Token not generated", Toast.LENGTH_LONG).show();
                }

                else {

                        if (code==200) {
                            progressDialog.dismiss();

                            context.startActivity(new Intent(context, MainActivity.class));

                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }

                }
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d("TAG", "Error message " + t.getMessage());
            }
        });
    }




    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }



}
