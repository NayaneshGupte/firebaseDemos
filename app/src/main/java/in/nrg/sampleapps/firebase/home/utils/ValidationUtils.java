package in.nrg.sampleapps.firebase.home.utils;

import android.text.TextUtils;

/**
 * @author Nayanesh Gupte
 */

public class ValidationUtils {

    private static final int PASSWORD_CHAR_LIMIT = 6;

    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() > PASSWORD_CHAR_LIMIT;
    }

}
