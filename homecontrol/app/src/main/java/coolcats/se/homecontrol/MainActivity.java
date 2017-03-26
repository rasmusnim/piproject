package coolcats.se.homecontrol;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText ip, port;
    ViewPager pager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = (EditText) findViewById(R.id.ip);
        port = (EditText) findViewById(R.id.port);
        pager = (ViewPager) findViewById(R.id.viewPager);

        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));

    }

    public int getPort(){
        return Integer.valueOf(port.getText().toString());
    }

    public String getIp(){
        return ip.getText().toString();
    }

}
