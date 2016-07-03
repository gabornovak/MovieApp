package hu.gabornovak.movieapp.logic.plugin;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by gnovak on 7/3/2016.
 */
public class DefaultConnectionPlugin implements ConnectionPlugin {
    private Context context;

    public DefaultConnectionPlugin(Context context) {
        this.context = context;
    }

    @Override
    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        System.out.println(cm.getActiveNetworkInfo() != null  && cm.getActiveNetworkInfo().isConnected());
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
