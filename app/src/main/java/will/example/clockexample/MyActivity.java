package will.example.clockexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;


/*
MyActivity will act as the controller for the app. In instantiates
the background thread and uses the Handler to control UI updates.
 */
public class MyActivity extends Activity {

    private Thread animationThread;
    private DialView dialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Task 1 - Instantiate a Dial View Object.
        dialView = new DialView(this);

        //Task 2 - Set the content of this activity to the dial view.
        setContentView(dialView);

        //Task 3 - Construct a thread to animate the dial
        animationThread = new Thread(runningAnimation);
        animationThread.start();
    }

    private Runnable runningAnimation = new Runnable() {

        private static final int DELAY = 200;

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(DELAY);
                    threadHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @SuppressLint("HandlerLeak")
    public Handler threadHandler = new Handler() {
        public void handleMessage(android.os.Message message) {
            dialView.update();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        threadHandler.removeCallbacks(runningAnimation);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
     */
}
