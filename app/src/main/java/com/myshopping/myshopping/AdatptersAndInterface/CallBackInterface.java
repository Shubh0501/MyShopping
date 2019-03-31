package com.myshopping.myshopping.AdatptersAndInterface;

import android.content.Context;
import android.support.v4.app.Fragment;

public interface CallBackInterface {

    public void CustomerCallBack(int id);
    public void ShopOwnerCallBack(int id);
    public void MallOwnerCallBack(int id);
}
