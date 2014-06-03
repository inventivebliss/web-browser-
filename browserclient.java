package com.example.my;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class browserclient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView v,String url)
	{
		v.loadUrl(url);
		return true;
	}
}
