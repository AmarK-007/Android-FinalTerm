package com.android.assignment1.shoecart.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Comment implements Parcelable {

    private int commentId;
    private int productId;
    private int userId;
    private int rating;
    private int imageId;
    private String imageUrl;
    private String comment;
    private Date postedOn;

    public Comment() {
    }

    public Comment(int commentId, int productId, int userId, int rating, int imageId, String imageUrl, String comment, Date postedOn) {
        this.commentId = commentId;
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.comment = comment;
        this.postedOn = postedOn;
    }

    protected Comment(Parcel in) {
        commentId = in.readInt();
        productId = in.readInt();
        userId = in.readInt();
        rating = in.readInt();
        imageId = in.readInt();
        imageUrl = in.readString();
        comment = in.readString();
        long tmpPostedOn = in.readLong();
        postedOn = tmpPostedOn != -1 ? new Date(tmpPostedOn) : null;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(commentId);
        parcel.writeInt(productId);
        parcel.writeInt(userId);
        parcel.writeInt(rating);
        parcel.writeInt(imageId);
        parcel.writeString(imageUrl);
        parcel.writeString(comment);
        parcel.writeLong(postedOn != null ? postedOn.getTime() : -1);
    }

    // Getters and setters

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }
}