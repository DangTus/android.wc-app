package com.reddog.worldcup2022.model;

public class NewsVideo {
    private String title;
    private String linkThumbnail;
    private String linkVideo;

    public NewsVideo() {
    }

    public NewsVideo(String title, String linkThumbnail, String linkVideo) {
        this.title = title;
        this.linkThumbnail = linkThumbnail;
        this.linkVideo = linkVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkThumbnail() {
        return linkThumbnail;
    }

    public void setLinkThumbnail(String linkThumbnail) {
        this.linkThumbnail = linkThumbnail;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }
}
