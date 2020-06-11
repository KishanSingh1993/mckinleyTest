package in.ktechnos.test;

import android.text.TextUtils;
import android.util.Patterns;

public class LoginUser {

    private String email;
    private String password;


    public LoginUser() {
    }

    public LoginUser(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public boolean isValid() {

        if(this.email!=null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && this.password!=null && !TextUtils.isEmpty(password))
        {
            if (IsValidEmail()){
                return true;
            }

        }

            return false;
    }

    public boolean IsValidEmail()
    {
        if(email!=null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        else
            return false;
    }
}
