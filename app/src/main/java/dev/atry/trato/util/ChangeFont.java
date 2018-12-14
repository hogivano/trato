package dev.atry.trato.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

public class ChangeFont {
    Context context;

    public ChangeFont(Context context){
        this.context = context;
    }

    public Typeface AleoBold(){
        AssetManager assetManager = context.getAssets();
        Typeface face = Typeface.createFromAsset(assetManager,  "fonts/Aleo-Bold.ttf");
        return face;
    }

    public Typeface AleoReguler(){
        AssetManager assetManager = context.getAssets();
        Typeface face = Typeface.createFromAsset(assetManager, "fonts/Aleo-Reguler.ttf");
        return face;
    }
}
