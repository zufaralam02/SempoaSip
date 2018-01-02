package com.iapps.libs.Drag;

import com.iapps.adapters.BaseBean;

/**
 * Created by marcelsantoso.
 * <p/>
 * 6/8/16
 */
public class BImage extends BaseBean {
    boolean hasImage;
    String  imgUrl, strId;

    public String getStrId() {
        return strId;
    }

    public BImage setStrId(String strId) {
        this.strId = strId;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public BImage setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public BImage setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
        return this;
    }
}
