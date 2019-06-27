package com.ks.secondtest.bean;

import android.support.annotation.AttrRes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by F0519 on 2019/6/26.
 */
@Entity
public class Bean {
  @Id
    private Long id;
  @Property
  private String thumbnail;
    @Property
    private String title;
    @Generated(hash = 825642153)
    public Bean(Long id, String thumbnail, String title) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.title = title;
    }
    @Generated(hash = 80546095)
    public Bean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getThumbnail() {
        return this.thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
