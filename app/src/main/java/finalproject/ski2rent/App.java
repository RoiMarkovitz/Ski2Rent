package finalproject.ski2rent;

import android.app.Application;

import finalproject.ski2rent.objects.Prices;
import finalproject.ski2rent.utils.FireBaseManager;
import finalproject.ski2rent.utils.MySignals;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySignals.init(this);
        FireBaseManager.init();
        Prices.init();

    }

} // App
