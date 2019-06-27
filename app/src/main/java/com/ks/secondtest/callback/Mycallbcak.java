package com.ks.secondtest.callback;

import com.ks.secondtest.bean.Art;

/**
 * Created by F0519 on 2019/6/26.
 */

public interface Mycallbcak {
    void OnSuccess(Art art);
    void OnFail(String msg);
}
