package com.t3h.loadhttpbyjsoup;

public class ImageVSBG {
    private String name;
    private String urlPath;

    public ImageVSBG(String name, String urlPath) {
        this.name = name;
        this.urlPath = urlPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
