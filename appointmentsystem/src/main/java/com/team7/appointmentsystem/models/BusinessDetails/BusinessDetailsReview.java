package com.team7.appointmentsystem.models.BusinessDetails;

import java.util.List;

public class BusinessDetailsReview {

    public List<Reviewer> commentedBy;
    public int overallRating;

    public BusinessDetailsReview() {
    }

    public BusinessDetailsReview(List<Reviewer> commentedBy, int overallRating) {
        this.commentedBy = commentedBy;
        this.overallRating = overallRating;
    }

    public List<Reviewer> getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(List<Reviewer> commentedBy) {
        this.commentedBy = commentedBy;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }
}
