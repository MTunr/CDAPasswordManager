/**
 * Logs the user in by searching for the right cdax file using the user's credentials.
 *
 * Author:  Jarret Jheng Ch'ng
 * Date:    14/12/2019
 */
public class LoginHandler {
    public static String findFile(String username, String password) {
        String interweave = "";

        for (int characterIndex = 0; characterIndex < username.length(); characterIndex++){
            interweave += CDA.shortMD5(username).charAt(characterIndex) + CDA.shortMD5(password).charAt(characterIndex);
        }

        return CDA.shortMD5(interweave) + ".cdax";
    }
}
