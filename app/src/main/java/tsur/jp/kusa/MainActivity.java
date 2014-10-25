package tsur.jp.kusa;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements MainActivityListener{

    @InjectView(R.id.kusa_image)
    ImageView mKusaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void getKusa(String amount, String length, String color) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(getString(R.string.url_http));
        builder.authority(getString(R.string.url_authority));
        builder.path(getString(R.string.url_path));
        builder.appendQueryParameter("amount", amount);
        builder.appendQueryParameter("length", length);
        builder.appendQueryParameter("color", color);

        final Picasso picasso = new Picasso.Builder(this)
                .build();
//        picasso.setIndicatorsEnabled(true);
        picasso.load(builder.build())
                .into(mKusaImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_kusa) {
            DialogFragment dialogFragment = EditKusaFragment.newInstance();
            dialogFragment.show(getFragmentManager(), "fragment");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void createdKusa(String amount, String length, String color) {
        getKusa(amount, length, color);
    }
}

interface MainActivityListener {
    public void createdKusa(String amount, String length, String color);
}

