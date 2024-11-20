package escencial;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * This class allows language management by keys.
 * @author calet
 */
public class LanguageManager {

    
        private static ResourceBundle messages;

    /**
     * Configure the language.(For example, "en" for english, 
     * "es" for spanish, "pt" for portuguese).
     * @param languageCode 
     */
        
    public static void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        messages = ResourceBundle.getBundle("messages", locale);
    }

    /**
     *Gets the translated text accordinf to the specific key associated with
     * the text
     * @param key 
     * @return 
     */
    public static String get(String key) {
        if (messages == null) {
            throw new IllegalStateException("Language not set. Call setLanguage() first.");
        }
        return messages.getString(key);
    }
}
