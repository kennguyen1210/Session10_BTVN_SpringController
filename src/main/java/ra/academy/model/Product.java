package ra.academy.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product {
    private int productId;
    private int categoryId;
    private String productName;
    private String imageUrl;
    private LocalDateTime createDate;
    private String description;
    private double price;
    private boolean status;

    public Product() {
    }

    public Product(int productId, int categoryId, String productName, String imageUrl, LocalDateTime createDate, String description, double price, boolean status) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public Product(int categoryId, String productName, String imageUrl, LocalDateTime createDate, String description, double price, boolean status) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String formatTime(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(createDate);
    }
}
