package com.stedroids.framework.image;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.stedroids.framework.global.AbstractApplication;
import com.stedroids.framework.global.PlugableGlobalComponent;
import com.stedroids.framework.global.PlugableConstants;

/**
 * Created by gastonsanguinetti on 09/07/16.
 */
public class ImageLoaderHelper {

    //Support for image loader libraries in Stedroids
    public static void loadRemoteImage(ObservableField<Drawable> observableField, String imageUrl, Context context) {
        withLoader(context).loadImage(observableField, imageUrl, context);
    }

    public static void loadRemoteImage(ImageView imageView, String imageUrl, Drawable drawable, boolean offlineCacheOnError) {
        withLoader(imageView.getContext()).loadImage(imageView, imageUrl, drawable, offlineCacheOnError);
    }

    private static ImageLoader withLoader(Context context) {
        if(!(context.getApplicationContext() instanceof AbstractApplication)) {
            throw new IllegalStateException("Your app must extends AbstractApplication class");
        }

        AbstractApplication application = (AbstractApplication) context.getApplicationContext();
        PlugableGlobalComponent imageLoaderComponent = application.getPluggedComponent(PlugableConstants
                .IMAGE_LOADER_LIBRARY);
        if(imageLoaderComponent != null) {
            return (ImageLoader) imageLoaderComponent;
        } else {
            throw new IllegalStateException("You must plug into app some image loader library first");
        }
    }

}
