package com.abapp.my_work_for_innofied.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Support implements Serializable
{

@SerializedName("url")
@Expose
private String url;
@SerializedName("text")
@Expose
private String text;
private final static long serialVersionUID = -2295963978261899351L;

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

}