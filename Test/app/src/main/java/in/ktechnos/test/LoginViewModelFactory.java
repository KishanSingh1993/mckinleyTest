package in.ktechnos.test;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;
    private LoginUser loginModel;



    public LoginViewModelFactory(LoginActivity loginActivity, LoginUser loginModel)
    {
        context= loginActivity;
        this.loginModel= loginModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(context,loginModel);
    }
}

