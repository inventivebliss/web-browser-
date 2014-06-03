package com.example.my;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Browser extends Activity implements View.OnClickListener {

	EditText url;
	WebView browser;
	 ScaleGestureDetector mScaleDetector;
	 //priva ImageView img;
	   private Matrix matrix = new Matrix();
	   private float scale = 1f;
	   private ScaleGestureDetector SGD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		browser = (WebView) findViewById(R.id.wvbrowser);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);
		browser.setWebViewClient(new browserclient());
		try{
		browser.loadUrl("http://www.google.com");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Button go = (Button) findViewById(R.id.go);
		Button forward = (Button) findViewById(R.id.forward);
		Button refresh = (Button) findViewById(R.id.refresh);
		Button history = (Button) findViewById(R.id.history);
		Button back = (Button) findViewById(R.id.back);
		url = (EditText) findViewById(R.id.url);
		go.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		history.setOnClickListener(this);
		back.setOnClickListener(this);
		SGD = new ScaleGestureDetector(this,new ScaleListener());
		//mScaleDetector = new ScaleGestureDetector(context, new OnScaleGestureListener() {
		   
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
switch(v.getId())
{
case R.id.go:
	String website=url.getText().toString();
	browser.loadUrl(website);
	InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	im.hideSoftInputFromWindow(url.getWindowToken(),0);
	break;
case R.id.back:
	if(browser.canGoBack())
	browser.goBack();
	break;
case R.id.forward:
	if(browser.canGoForward())
	browser.goForward();
	break;
case R.id.refresh:
	browser.reload();
	break;
case R.id.history:
	browser.clearHistory();
	break;
}
	}

	  @Override
	   public boolean onTouchEvent(MotionEvent ev) {
	      SGD.onTouchEvent(ev);
	      return true;
	   }

	 private class ScaleListener extends ScaleGestureDetector.
	   SimpleOnScaleGestureListener {
	   @Override
	   public boolean onScale(ScaleGestureDetector detector) {
	      scale *= detector.getScaleFactor();
	      scale = Math.max(0.1f, Math.min(scale, 5.0f));
	      matrix.setScale(scale, scale);
	    //  img.setImageMatrix(matrix);
	      return true;
	   }
	}
}
