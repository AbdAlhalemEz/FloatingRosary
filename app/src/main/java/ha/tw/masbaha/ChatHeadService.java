package ha.tw.masbaha;


import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;



public class ChatHeadService extends Service {
	private WindowManager windowManager;
	private RelativeLayout chatheadView, removeView;
	private LinearLayout txtView, txt_linearlayout;
	private ImageView  removeImg;

	private TextView txt1;
	private TextView t1;


	private int x_init_cord, y_init_cord, x_init_margin, y_init_margin;
	private Point szWindow = new Point();
	private boolean isLeft = true;
	private String sMsg = "";
	public static int n1;
	public static int v1;
	public String co ,s1;
	public Double trans;
	public int index;
	private InterstitialAd mInterstitialAd;


	DBHelper DB;
	DBHelper2 DB2;


	@SuppressWarnings("deprecation")













	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(Utils.LogTag, "ChatHeadService.onCreate()");

		DB = new DBHelper(this);
		DB2 = new DBHelper2(this);


		Cursor res1 = DB2.getdata();
		res1.moveToLast();
		v1=Main.getV1();
		co = res1.getString(4).toString();
		s1=  String.valueOf(res1.getString(3));
		n1=Integer.parseInt(res1.getString(1));
		trans =Double.parseDouble(res1.getString(2));


		Cursor res98 = DB.getdata();
		res98.moveToLast();
		index =  Integer.parseInt(res98.getString(0));



/*

	String	n2 =String.valueOf(Main.getX());
       String co2= Main.getCo();
		String s12= Main.gets1();
		String trans2 =String.valueOf(Main.getw());
		DB2.updateuserdata("0", n2,trans2,s12,co2);

*/



		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-8157110153359056/2969084863");
		mInterstitialAd.loadAd(new AdRequest.Builder().build());
		mInterstitialAd.show();

		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		} else {
			Log.d("TAG", "The interstitial wasn't loaded yet.");
		}
		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);











	}
























	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void handleStart(){







		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

		removeView = (RelativeLayout)inflater.inflate(R.layout.remove, null);


		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {


			WindowManager.LayoutParams paramRemove = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_PHONE,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSLUCENT);
			paramRemove.gravity = Gravity.TOP | Gravity.LEFT;



			removeView.setVisibility(View.GONE);
			removeImg = (ImageView)removeView.findViewById(R.id.remove_img);
			windowManager.addView(removeView, paramRemove);



		}
  else {

			WindowManager.LayoutParams paramRemove = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
					PixelFormat.TRANSLUCENT);
			paramRemove.gravity = Gravity.TOP | Gravity.LEFT;



			removeView.setVisibility(View.GONE);
			removeImg = (ImageView)removeView.findViewById(R.id.remove_img);
			windowManager.addView(removeView, paramRemove);



		}


		chatheadView = (RelativeLayout) inflater.inflate(R.layout.chathead, null);

		t1 = (TextView) chatheadView.findViewById(R.id.textv);


	double alphaAmount = trans;
	t1.setAlpha((float) alphaAmount);








	int pass = Main.getPass();
	t1.setText("" + pass);


		if (n1<250){

			t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
			t1.setPadding(0,0,0,60);

		}
		else if(n1<400){
			t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
		t1.setPadding(0,0,0,90);
	}
		else if(n1<500) {
			t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 33);
			t1.setPadding(0,0,0,110);
		}
		else if(n1<601) {
			t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
t1.setPadding(0,0,0,130);
		}

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(n1, n1);
		t1.setLayoutParams(params2);



		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			windowManager.getDefaultDisplay().getSize(szWindow);
		} else {
			int w = windowManager.getDefaultDisplay().getWidth();
			int h = windowManager.getDefaultDisplay().getHeight();
			szWindow.set(w, h);

		}




if(s1.equals("s2")){


	if (n1<250){

		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
		t1.setPadding(0,0,0,60);

	}
	else if(n1<400){
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
		t1.setPadding(0,0,0,90);
	}
	else if(n1<500) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 33);
		t1.setPadding(0,0,0,110);
	}
	else if(n1<601) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
		t1.setPadding(0,0,0,130);
	}





		if(co.equals("green"))
			t1.setBackgroundResource(R.drawable.green);
		else if (co.equals("red"))
			t1.setBackgroundResource(R.drawable.red);
		else if 	(co.equals("pink"))
		t1.setBackgroundResource(R.drawable.pink);
		else if 	(co.equals("darkpink"))
		t1.setBackgroundResource(R.drawable.darkpink);
        else if (co.equals("yellow"))
		t1.setBackgroundResource(R.drawable.yellow);

		else
			t1.setBackgroundResource(R.drawable.bobo2);
}
else {
		if (n1<250){

		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
		t1.setPadding(0,0,0,110);
	}


	else if(n1<300){
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 19);
		t1.setPadding(0,0,0,130);
	}


	else if(n1<350) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 21);
		t1.setPadding(0,0,0,160);
	}
	else if(n1<400) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);
		t1.setPadding(0,0,0,190);
	}
else if(n1<450) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
		t1.setPadding(0,0,0,210);
	}
else if(n1<500) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 27);
		t1.setPadding(0,0,0,230);
	}
else if(n1<550) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 29);
		t1.setPadding(0,0,0,250);
	}


	else if(n1<601) {
		t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
		t1.setPadding(0,0,0,270);
	}




	if(co.equals("green"))
		t1.setBackgroundResource(R.drawable.g);
	else if (co.equals("red"))
		t1.setBackgroundResource(R.drawable.r);

	else if 	(co.equals("pink"))
		t1.setBackgroundResource(R.drawable.p);
	else if 	(co.equals( "darkpink"))
		t1.setBackgroundResource(R.drawable.pu);
	else if (co.equals("yellow"))
		t1.setBackgroundResource(R.drawable.y);
	else
		t1.setBackgroundResource(R.drawable.b);

}




		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {


			WindowManager.LayoutParams params = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_PHONE,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSLUCENT);
			params.gravity = Gravity.TOP | Gravity.LEFT;


			params.x = 0;
			params.y = 105;
			windowManager.addView(chatheadView, params);

		}
		else {

			WindowManager.LayoutParams params = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
					PixelFormat.TRANSLUCENT);

			params.gravity = Gravity.TOP | Gravity.LEFT;
			params.x = 0;
			params.y = 100;
			windowManager.addView(chatheadView, params);

		}








		chatheadView.setOnTouchListener(new View.OnTouchListener() {
			long time_start = 0, time_end = 0;
			boolean isLongclick = false, inBounded = false;
			int remove_img_width = 0, remove_img_height = 0;

			Handler handler_longClick = new Handler();
			Runnable runnable_longClick = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.d(Utils.LogTag, "Into runnable_longClick");

					isLongclick = true;
					removeView.setVisibility(View.VISIBLE);
					chathead_longclick();
				}
			};

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) chatheadView.getLayoutParams();

				int x_cord = (int) event.getRawX();
				int y_cord = (int) event.getRawY();
				int x_cord_Destination, y_cord_Destination;

				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						time_start = System.currentTimeMillis();
						handler_longClick.postDelayed(runnable_longClick, 600);

						remove_img_width = removeImg.getLayoutParams().width;
						remove_img_height = removeImg.getLayoutParams().height;

						x_init_cord = x_cord;
						y_init_cord = y_cord;

						x_init_margin = layoutParams.x;
						y_init_margin = layoutParams.y;

						if(txtView != null){
							txtView.setVisibility(View.GONE);
							myHandler.removeCallbacks(myRunnable);
						}
						break;
					case MotionEvent.ACTION_MOVE:
						int x_diff_move = x_cord - x_init_cord;
						int y_diff_move = y_cord - y_init_cord;

						x_cord_Destination = x_init_margin + x_diff_move;
						y_cord_Destination = y_init_margin + y_diff_move;

						if(isLongclick){
							int x_bound_left = szWindow.x / 2 - (int)(remove_img_width * 1.5);
							int x_bound_right = szWindow.x / 2 +  (int)(remove_img_width * 1.5);
							int y_bound_top = szWindow.y - (int)(remove_img_height * 1.5);

							if((x_cord >= x_bound_left && x_cord <= x_bound_right) && y_cord >= y_bound_top){
								inBounded = true;

								int x_cord_remove = (int) ((szWindow.x - (remove_img_height * 1.5)) / 2);
								int y_cord_remove = (int) (szWindow.y - ((remove_img_width * 1.5) + getStatusBarHeight() ));

								if(removeImg.getLayoutParams().height == remove_img_height){
									removeImg.getLayoutParams().height = (int) (remove_img_height * 1.5);
									removeImg.getLayoutParams().width = (int) (remove_img_width * 1.5);

									WindowManager.LayoutParams param_remove = (WindowManager.LayoutParams) removeView.getLayoutParams();
									param_remove.x = x_cord_remove;
									param_remove.y = y_cord_remove;

									windowManager.updateViewLayout(removeView, param_remove);
								}

								layoutParams.x = x_cord_remove + (Math.abs(removeView.getWidth() - chatheadView.getWidth())) / 2;
								layoutParams.y = y_cord_remove + (Math.abs(removeView.getHeight() - chatheadView.getHeight())) / 2 ;

								windowManager.updateViewLayout(chatheadView, layoutParams);
								break;
							}else{
								inBounded = false;
								removeImg.getLayoutParams().height = remove_img_height;
								removeImg.getLayoutParams().width = remove_img_width;

								WindowManager.LayoutParams param_remove = (WindowManager.LayoutParams) removeView.getLayoutParams();
								int x_cord_remove = (szWindow.x - removeView.getWidth()) / 2;
								int y_cord_remove = szWindow.y - (removeView.getHeight() + getStatusBarHeight() );

								param_remove.x = x_cord_remove;
								param_remove.y = y_cord_remove;

								windowManager.updateViewLayout(removeView, param_remove);
							}

						}


						layoutParams.x = x_cord_Destination;
						layoutParams.y = y_cord_Destination;

						windowManager.updateViewLayout(chatheadView, layoutParams);
						break;
					case MotionEvent.ACTION_UP:
						isLongclick = false;
						removeView.setVisibility(View.GONE);
						removeImg.getLayoutParams().height = remove_img_height;
						removeImg.getLayoutParams().width = remove_img_width;
						handler_longClick.removeCallbacks(runnable_longClick);

						if(inBounded){


							stopService(new Intent(ChatHeadService.this, ChatHeadService.class));
							inBounded = false;
							break;
						}


						int x_diff = x_cord - x_init_cord;
						int y_diff = y_cord - y_init_cord;

						if(Math.abs(x_diff) < 5 && Math.abs(y_diff) < 5){
							time_end = System.currentTimeMillis();
							if((time_end - time_start) < 300){
								chathead_click();
							}
						}

						y_cord_Destination = y_init_margin + y_diff;

						int BarHeight =  getStatusBarHeight();
						if (y_cord_Destination < 0) {
							y_cord_Destination = 0;
						} else if (y_cord_Destination + (chatheadView.getHeight() + BarHeight) > szWindow.y) {
							y_cord_Destination = szWindow.y - (chatheadView.getHeight() + BarHeight );
						}
						layoutParams.y = y_cord_Destination;

						inBounded = false;
						resetPosition(x_cord);

						break;
					default:
						Log.d(Utils.LogTag, "chatheadView.setOnTouchListener  -> event.getAction() : default");
						break;
				}
				return true;






            }
		});


		txtView = (LinearLayout)inflater.inflate(R.layout.txt, null);
		txt1 = (TextView) txtView.findViewById(R.id.txt1);
		txt_linearlayout = (LinearLayout)txtView.findViewById(R.id.txt_linearlayout);




		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {


			WindowManager.LayoutParams paramsTxt = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_PHONE,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
					PixelFormat.TRANSLUCENT);

			paramsTxt.gravity = Gravity.TOP | Gravity.LEFT;

			txtView.setVisibility(View.GONE);
			windowManager.addView(txtView, paramsTxt);


		}
		else {

			WindowManager.LayoutParams paramsTxt = new WindowManager.LayoutParams(
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.WRAP_CONTENT,
					WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
					WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
					PixelFormat.TRANSLUCENT);

			paramsTxt.gravity = Gravity.TOP | Gravity.LEFT;

			txtView.setVisibility(View.GONE);
			windowManager.addView(txtView, paramsTxt);



		}




	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

		if(windowManager == null)
			return;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            windowManager.getDefaultDisplay().getSize(szWindow);
        } else {
            int w = windowManager.getDefaultDisplay().getWidth();
            int h = windowManager.getDefaultDisplay().getHeight();
            szWindow.set(w, h);
        }
		
		WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) chatheadView.getLayoutParams();
				
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	Log.d(Utils.LogTag, "ChatHeadService.onConfigurationChanged -> landscap");
	    	
	    	if(txtView != null){
				txtView.setVisibility(View.GONE);
			}
	    	
	    	if(layoutParams.y + (chatheadView.getHeight() + getStatusBarHeight()) > szWindow.y){
	    		layoutParams.y = szWindow.y- (chatheadView.getHeight() + getStatusBarHeight());
	    		windowManager.updateViewLayout(chatheadView, layoutParams);
	    	}
	    		    	
	    	if(layoutParams.x != 0 && layoutParams.x < szWindow.x){
				resetPosition(szWindow.x);
			}
	    	
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	    	Log.d(Utils.LogTag, "ChatHeadService.onConfigurationChanged -> portrait");
	    	
	    	if(txtView != null){
				txtView.setVisibility(View.GONE);
			}
	    	
	    	if(layoutParams.x > szWindow.x){
				resetPosition(szWindow.x);
			}
	    	
	    }
		
	}
	
	private void resetPosition(int x_cord_now) {
		if(x_cord_now <= szWindow.x / 2){
			isLeft = true;
			moveToLeft(x_cord_now);

		} else {
			isLeft = false;
			moveToRight(x_cord_now);

		}

    }
	 private void moveToLeft(final int x_cord_now){
		 	final int x = szWindow.x - x_cord_now;

	        new CountDownTimer(500, 5) {
	        	WindowManager.LayoutParams mParams = (WindowManager.LayoutParams) chatheadView.getLayoutParams();
	            public void onTick(long t) {
	                long step = (500 - t)/5;
	                mParams.x = 0 - (int)(double)bounceValue(step, x );
	            }
	            public void onFinish() {
	            	mParams.x = 0;
	            }
	        }.start();
	 }

	 private  void moveToRight(final int x_cord_now){
	        new CountDownTimer(500, 5) {
	        	WindowManager.LayoutParams mParams = (WindowManager.LayoutParams) chatheadView.getLayoutParams();
	            public void onTick(long t) {
	                long step = (500 - t)/5;
	                mParams.x = szWindow.x + (int)(double)bounceValue(step, x_cord_now) - chatheadView.getWidth();
	                windowManager.updateViewLayout(chatheadView, mParams);
	            }
	            public void onFinish() {
	            	mParams.x = szWindow.x - chatheadView.getWidth();
	                windowManager.updateViewLayout(chatheadView, mParams);
	            }
	        }.start();
	    }
	 
	 private double bounceValue(long step, long scale){
	        double value = scale * java.lang.Math.exp(-5 * step) * java.lang.Math.cos(0 * step);
	        return value;
	    }
	 
	 private int getStatusBarHeight() {
		int statusBarHeight = (int) Math.ceil(25 * getApplicationContext().getResources().getDisplayMetrics().density);
	    return statusBarHeight;
	}
	int v=100;
	int ad=100;








	private void chathead_click(){
		Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(v1==1)
		vibe.vibrate(60);

		String num = t1.getText().toString();
		int number = Integer.parseInt(num);
        number++;



		DB.updateuserdata(index,number);




		while (number == ad ){
			mInterstitialAd.show();
			ad+=400;
		}




        if (custom.getPass()!=null) {
        	int zx = Integer.parseInt(custom.getPass());

			if (number == zx) {
				Toast t = Toast.makeText(getApplicationContext(), " أحسنت لقد اتممت " + number + " تسبيحة ", Toast.LENGTH_LONG);
				vibe.vibrate(1500);
				t.show();

			}

		}

        else {
        	while (number == v ){
			Toast t = Toast.makeText(getApplicationContext(), " أحسنت لقد اتممت " + number + " تسبيحة ", Toast.LENGTH_LONG);
			vibe.vibrate(1000);
			t.show();
			v+=100;

		}}




       t1.setText("" + number);
        Main.setPass(number);

		while (number == ad ){
			mInterstitialAd.show();
			ad+=500;

		}


		//database

		Cursor res = DB.getdata();
		res.moveToLast();
	int name=  Integer.parseInt(res.getString(0));

		DB.updateuserdata(name, number);

















	}






	
	private void chathead_longclick(){
		Log.d(Utils.LogTag, "Into ChatHeadService.chathead_longclick() ");
		
		WindowManager.LayoutParams param_remove = (WindowManager.LayoutParams) removeView.getLayoutParams();
		int x_cord_remove = (szWindow.x - removeView.getWidth()) / 2;
		int y_cord_remove = szWindow.y - (removeView.getHeight() + getStatusBarHeight() );
		
		param_remove.x = x_cord_remove;
		param_remove.y = y_cord_remove;
		
		windowManager.updateViewLayout(removeView, param_remove);















	}
	
	private void showMsg(String sMsg){
		if(txtView != null && chatheadView != null ){
			Log.d(Utils.LogTag, "ChatHeadService.showMsg -> sMsg=" + sMsg);
			txt1.setText(sMsg);
			myHandler.removeCallbacks(myRunnable);

			WindowManager.LayoutParams param_chathead = (WindowManager.LayoutParams) chatheadView.getLayoutParams();
			WindowManager.LayoutParams param_txt = (WindowManager.LayoutParams) txtView.getLayoutParams();

			txt_linearlayout.getLayoutParams().height = chatheadView.getHeight();
			txt_linearlayout.getLayoutParams().width = szWindow.x / 2;

			if(isLeft){
				param_txt.x = param_chathead.x + t1.getWidth();
				param_txt.y = param_chathead.y;

				txt_linearlayout.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			}else{
				param_txt.x = param_chathead.x - szWindow.x / 2;
				param_txt.y = param_chathead.y;

				txt_linearlayout.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
			}

			txtView.setVisibility(View.VISIBLE);
			windowManager.updateViewLayout(txtView, param_txt);

			myHandler.postDelayed(myRunnable, 600);

		}

	}
	
	Handler myHandler = new Handler();
	Runnable myRunnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(txtView != null){
				txtView.setVisibility(View.GONE);
			}
		}
	};
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(Utils.LogTag, "ChatHeadService.onStartCommand() -> startId=" + startId);

		if(intent != null){
			Bundle bd = intent.getExtras();

			if(bd != null)
				sMsg = bd.getString(Utils.EXTRA_MSG);

			if(sMsg != null && sMsg.length() > 0){
				if(startId == Service.START_STICKY){
					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							showMsg(sMsg);
						}
					}, 300);

				}else{
					showMsg(sMsg);
				}

			}

		}

		if(startId == Service.START_STICKY) {
			handleStart();
			return super.onStartCommand(intent, flags, startId);
		}else{
			return  Service.START_NOT_STICKY;
		}

	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(chatheadView != null){
			windowManager.removeView(chatheadView);
		}
		
		if(txtView != null){
			windowManager.removeView(txtView);
		}
		
		if(removeView != null){
			windowManager.removeView(removeView);
		}
		
	}
	


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(Utils.LogTag, "ChatHeadService.onBind()");
		return null;
	}










}
