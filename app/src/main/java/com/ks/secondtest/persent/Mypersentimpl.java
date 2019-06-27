package com.ks.secondtest.persent;

import com.ks.secondtest.bean.Art;
import com.ks.secondtest.callback.Mycallbcak;
import com.ks.secondtest.model.Mymodel;
import com.ks.secondtest.view.Myview;

/**
 * Created by F0519 on 2019/6/26.
 */

public class Mypersentimpl implements Mypersent, Mycallbcak {
private Mymodel mymodel;
private Myview myview;

    public Mypersentimpl(Mymodel mymodel, Myview myview) {
        this.mymodel = mymodel;
        this.myview = myview;
    }

    @Override
    public void getData() {
        if(mymodel!=null){
            mymodel.getData(this);
        }
    }

    @Override
    public void OnSuccess(Art art) {
        if (myview != null) {
            myview.OnSuccess(art);
        }
    }
        @Override
        public void OnFail(String msg){
            if (myview != null) {
                myview.OnFail(msg);
            }
        }
}
