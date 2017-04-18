package in.nrg.sampleapps.firebase.home.utils;

import android.text.TextUtils;

/**
 * Validation class for Sign In- Sign Up process
 *
 * @author Nayanesh Gupte
 */

public class ValidationUtils {

    private static final int PASSWORD_CHAR_LIMIT = 6;

    /**
     * Check if email is valid
     *
     * @param email
     * @return true if email is not empty and follows standard pattern for email
     */
    public static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Check if password is valid
     *
     * @param password
     * @return true if password contains more than 6 characters
     */
    public static boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() > PASSWORD_CHAR_LIMIT;
    }

}
