package com.ks.secondtest.bean;

/**
 * Created by F0519 on 2019/6/26.
 */

public class Aa {

    /**
     * code : 200
     * res : 上传文件成功
     * data : {"url":"http://yun918.cn/study/public/uploadfiles/aa/a2.png"}
     */

    private int code;
    private String res;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : http://yun918.cn/study/public/uploadfiles/aa/a2.png
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
