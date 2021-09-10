package uz.medion.ui.base;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class InternetChecker extends AsyncTask<Void, Void, Boolean> {

    private final Consumer mConsumer;

    public InternetChecker(Consumer consumer) {
        mConsumer = consumer;
        execute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Socket sock = new Socket();
            sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean hasInternet) {
        mConsumer.accept(hasInternet);
    }

    public interface Consumer {
        void accept(Boolean hasInternet);
    }
}