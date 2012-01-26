package com.rcc.opensourcecodesamplesronc.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/*
 * Presents a series of thumb nails as a gallery allowing the user to click for a larger
 * image view
 */
public class ThumbnailSampleActivity extends Activity {
//	private String TAG = ThumbnailSampleActivity.class.getSimpleName();
	
	Gallery gallery;
	ImageView imageView;
	
	private Integer[] images = {
    		R.drawable.owl_image1,
    		R.drawable.owl_image2,
    		R.drawable.owl_image3,
    		R.drawable.owl_image4,
    		R.drawable.owl_image5,
    		R.drawable.owl_image6,
    		R.drawable.owl_image7,
    		R.drawable.owl_image8,
    		R.drawable.owl_image9,
    		R.drawable.owl_image10,
    		R.drawable.owl_image11,
    		R.drawable.owl_image12,
    		R.drawable.owl_image13,
    		R.drawable.owl_image14
    	};
	
    /*
     * Allows gallery (thumbnail) choice to be shown as a larger picture
     * Called when the activity is first created. 
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thumbnailsample);
        
        imageView = (ImageView) findViewById(R.id.imageview1);
        
        gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter());
        gallery.setOnItemClickListener(new OnItemClickListener() {

        	/*
        	 * sets image view (larger view) to the thumb nail clicked
        	 * (non-Javadoc)
        	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
        	 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				imageView.setImageResource(images[position]);
			}
		});
    }
    
    /*
     * Class adapter used for gallery of thumbnails
     */
    private class ImageAdapter extends BaseAdapter{
    	
    	/*
    	 * Count for images to be displayed in Gallery
    	 * (non-Javadoc)
    	 * @see android.widget.Adapter#getCount()
    	 */
		@Override
		public int getCount() {
			return images.length;
		}
		
		/*
		 * return position of item chosen
		 * (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Object getItem(int position) {
			return position;
		}

		/*
		 * return position of item id chosen
		 * (non-Javadoc)
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		/*
		 * gets view parameters used in gallery of thumbnails
		 * (non-Javadoc)
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv = new ImageView(getBaseContext());
			iv.setLayoutParams(new Gallery.LayoutParams(convertDPToPix(100), convertDPToPix(100)));
			iv.setImageResource(images[position]);
			return iv;
		}
    	
    }
    
    /*
     * Convert from DPs to Pixels (per Android Developer Guide)
	 * @param dpInput - number of DPs 
	 * @return int - number of Pixels
	 */
	private int convertDPToPix(float dpInput) {

		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dpInput * scale + 0.5f);
	}
    
	/*
	 * inflates menu for help screen
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.thumbnailhelpmenu, menu);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.thumbnailhelp:
			intent = new Intent("com.rcc.opensourcecodesamplesronc.THUMBNAILHELP");
			startActivity(intent);
			break;
		}
		return false;
	}
}