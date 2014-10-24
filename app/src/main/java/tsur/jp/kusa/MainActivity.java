package tsur.jp.kusa;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity {

    @InjectView(R.id.kusa_image)
    ImageView mKusaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        getKusa();
    }

    private void getKusa() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(getString(R.string.url_http));
        builder.authority(getString(R.string.url_authority));
        builder.path(getString(R.string.url_path));
        builder.appendQueryParameter("color", "green");

        final Picasso picasso = new Picasso.Builder(this)
                .build();
        picasso.setIndicatorsEnabled(true);
        picasso.load(builder.build()).into(mKusaImage);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}