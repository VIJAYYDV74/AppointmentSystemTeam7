package com.team7.appointmentsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@DynamicUpdate
@Table(name = "business")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long businessid;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "businessname")
    private String businessName;

    @Column(columnDefinition = "text", nullable = false, name = "businessdescription")
    private String businessDescription;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "businesstitle")
    private String businessTitle;

    @Column(nullable = false, name = "businessnumber")
    private String businessMobileNumber;

    @Column(nullable = false, name = "businessemail")
    private String businessEmail;

    @Column(columnDefinition = "boolean default false", name = "cancellationavailable")
    private boolean cancellationAvailable;

    @Column(name = "slotduration")
    private int slotDuration;

    @Column(columnDefinition = "boolean default false", name = "emailverified")
    private boolean emailVerified;

    @Column(columnDefinition = "timestamp default current_timestamp", name = "createdtime")
    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "businesscategory")
    private Categories categories;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "genderid")
    private GenderCategories genderCategory;

    @OneToMany(mappedBy = "business", targetEntity = BusinessWorkingHours.class)
    private List<BusinessWorkingHours> workingHours;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "businessaddress")
    private BusinessAddress businessAddress;

    @OneToMany(mappedBy = "business", targetEntity = Services.class)
    private List<Services> services;

    @OneToMany(mappedBy = "business", targetEntity = Comments.class)
    private List<Comments> comments;

    @OneToMany(mappedBy = "business", targetEntity = BusinessImages.class)
    private List<BusinessImages> businessImages;

    @Column(name = "isblocked")
    private boolean isBlocked;

    public Business(){

    }

    public Business(String businessName, String businessDescription,
                    String businessTitle, String businessMobileNumber,
                    String businessEmail, boolean cancellationAvailable,
                    int slotDuration, GenderCategories genderCategory,
                    Categories category, Users users,
                    List<BusinessWorkingHours> workingHours,
                    BusinessAddress businessAddress, List<Services> services,
                    List<Comments> comments, List<BusinessImages> businessImages) {
        this.businessName = businessName;
        this.businessDescription = businessDescription;
        this.businessTitle = businessTitle;
        this.businessMobileNumber = businessMobileNumber;
        this.businessEmail = businessEmail;
        this.cancellationAvailable = cancellationAvailable;
        this.slotDuration = slotDuration;
        this.genderCategory = genderCategory;
        this.categories = category;
        this.users = users;
        this.workingHours = workingHours;
        this.businessAddress = businessAddress;
        this.services = services;
        this.comments = comments;
        this.businessImages = businessImages;
    }

    public long getBusinessid() {
        return businessid;
    }

    public void setBusinessid(long businessid) {
        this.businessid = businessid;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public String getBusinessTitle() {
        return businessTitle;
    }

    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

    public String getBusinessMobileNumber() {
        return businessMobileNumber;
    }

    public void setBusinessMobileNumber(String businessMobileNumber) { this.businessMobileNumber = businessMobileNumber; }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public boolean isCancellationAvailable() {
        return cancellationAvailable;
    }

    public void setCancellationAvailable(boolean cancellationAvailable) { this.cancellationAvailable = cancellationAvailable; }

    public int getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }

    public GenderCategories getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(GenderCategories genderCategory) {
        this.genderCategory = genderCategory;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<BusinessWorkingHours> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<BusinessWorkingHours> workingHours) {
        this.workingHours = workingHours;
    }

    public BusinessAddress getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(BusinessAddress businessAddress) {
        this.businessAddress = businessAddress;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<BusinessImages> getBusinessImages() {
        return businessImages;
    }

    public void setBusinessImages(List<BusinessImages> businessImages) {
        this.businessImages = businessImages;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessid=" + businessid +
                ", businessName='" + businessName + '\'' +
                ", businessDescription='" + businessDescription + '\'' +
                ", businessTitle='" + businessTitle + '\'' +
                ", businessMobileNumber='" + businessMobileNumber + '\'' +
                ", businessEmail='" + businessEmail + '\'' +
                ", cancellationAvailable=" + cancellationAvailable +
                ", slotDuration=" + slotDuration +
                ", genderCategory='" + genderCategory + '\'' +
                ", emailVerified=" + emailVerified +
                ", createdTime=" + createdTime +
                ", businessImages='" + businessImages + '\'' +
                ", categories=" + categories +
                ", users=" + users +
                ", workingHours=" + workingHours +
                ", businessAddress=" + businessAddress +
                ", services=" + services +
                ", comments=" + comments +
                ", businessImages=" + businessImages +
                ", isBlocked=" + isBlocked +
                '}';
    }

    public String getBusinessRating(){
        DecimalFormat df = new DecimalFormat("0.0");
        double rating;
        long temp = 0;
        if (comments.size()==0){
            return String.valueOf((double) 0.0);
        }
        for(Comments comments1: comments){
            temp = temp + comments1.getRating();
        }
        rating = (double) temp/comments.size();
        return df.format(rating);
    }

}
