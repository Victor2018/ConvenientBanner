package com.cherry.convenientbanner

import android.view.View
import com.cherry.library.convenientbanner.holder.Holder

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BannerImageHolderView
 * Author: Victor
 * Date: 2022/3/11 17:10
 * Description: 
 * -----------------------------------------------------------------
 */

class BannerHolderView(itemView: View?) : Holder<String>(itemView) {
    private var imageView: ShapedImageView? = null

    override fun initView(itemView: View?) {
        imageView = itemView?.findViewById(R.id.mIvImage)
    }

    override fun updateUI(data: String?) {
//        ImageUtils.instance.loadImage(itemView.context, imageView, data)
    }

}