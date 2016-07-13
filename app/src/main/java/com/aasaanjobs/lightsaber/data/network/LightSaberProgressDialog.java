package com.aasaanjobs.lightsaber.data.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.aasaanjobs.lightsaber.root.di.customidentifiers.ActivityContext;

import javax.inject.Inject;

/**
 * Created by nazmuddinmavliwala on 25/05/16.
 */

public class LightSaberProgressDialog {

    private ProgressDialog progressDialog;


    @Inject
    public LightSaberProgressDialog(@ActivityContext Context context) {
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setMessage("Please wait...");
    }

    public void show() {
        progressDialog.show();
    }

    public void hide() {
        progressDialog.hide();
    }

    public void dismiss() {
        progressDialog.dismiss();
    }

    public boolean isShowing() {
        return progressDialog.isShowing();
    }
}
