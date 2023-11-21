package ra.academy.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description;
    private LocalDateTime createDate;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, LocalDateTime createDate) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.createDate = createDate;
    }

    public Category(String categoryName, String description, LocalDateTime createDate) {
        this.categoryName = categoryName;
        this.description = description;
        this.createDate = createDate;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public String formatTime(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(createDate);
    }

}
