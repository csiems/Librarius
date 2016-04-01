import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Guest on 4/1/16.
 */
public class LibrariusApplication extends Application {
    private static LibrariusApplication app;
    private Firebase mFirebaseRef;

    public static LibrariusApplication getAppInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase(this.getString(R.string.firebase_url));
    }
}
