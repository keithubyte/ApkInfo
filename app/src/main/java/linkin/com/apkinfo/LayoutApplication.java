package linkin.com.apkinfo;

import android.app.Application;

import com.linkin.tvlayout.LayoutRadio;

/**
 * @author keith
 * @since 2016-03-22
 */
public class LayoutApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LayoutRadio.initRadio(this, 1920, 1080);
    }

}
