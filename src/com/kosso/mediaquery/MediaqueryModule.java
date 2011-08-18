/**

MediaQuery Module for Appclerator Titanium : Android

Author  : Kosso
Date    : August 18, 2011

Description : 
Provides a method to get the file file path of an audio or video file recorded with the 
"android.provider.MediaStore.RECORD_SOUND" or "android.media.action.VIDEO_CAPTURE" actions.

These actions return a content://providerUri/contentId string in the app, so we pass the contentId
to the required providerUri using two methods depending on which intent activity is being used to return a String back to the app: 

String getAudioPath(String contentId) 
String getVideoPath(String contentId) 


I've probably done loads of things wrong here, but hey a) I'm not a Java programmer and b) IT WORKS! 

Cheers!
Kosso

**/

package com.kosso.mediaquery;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.KrollInvocation;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.TiActivity;
import org.appcelerator.titanium.util.TiActivityResultHandler;
import org.appcelerator.titanium.util.TiActivitySupport;
import org.appcelerator.titanium.util.TiActivitySupportHelper;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;
import android.net.Uri;
import android.provider.MediaStore;
import android.database.Cursor;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ContentUris;
import android.content.ContentResolver;

// I have propably imported more than I need  :p


@Kroll.module(name="Mediaquery", id="com.kosso.mediaquery")
public class MediaqueryModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "MediaqueryModule";
	private static final boolean DBG = TiConfig.LOGD;
	
	public MediaqueryModule(TiContext tiContext) {
		super(tiContext);
	}

	
	@Kroll.method
	public String getAudioPath(String contentId) {
		
		Uri theMediaUri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentId);
		String[] projection = { MediaStore.Audio.Media.DATA };
		
		// Do the query ...
        Cursor mCur = getTiContext().getActivity().getContentResolver().query(theMediaUri, projection, null, null, null);
        
        Log.d(LCAT, "########## CALLING getAudioPath ");
        
        int column_index = mCur.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
       	mCur.moveToFirst();
        return mCur.getString(column_index);
        
	}
	
	
	@Kroll.method
	public String getVideoPath(String contentId) {
		
		Uri theMediaUri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentId);
		String[] projection = { MediaStore.Video.Media.DATA };
		
		// Do the query ...
        Cursor mCur = getTiContext().getActivity().getContentResolver().query(theMediaUri, projection, null, null, null);
        
        Log.d(LCAT, "########## CALLING getVideoPath ");
        
        int column_index = mCur.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
       	mCur.moveToFirst();
        return mCur.getString(column_index);        
	}

}
