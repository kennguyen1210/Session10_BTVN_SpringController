package ra.academy.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class ProductRequest {

    private String productName;
    private List<MultipartFile> imageUrl;
    private String description;
    private double price;
    private boolean status;
    private String categoryId;
    private String oldImage;
    public ProductRequest() {
    }

    public ProductRequest( String categoryId, String productName, List<MultipartFile> imageUrl, String description, double price, boolean status) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public ProductRequest(String productName, List<MultipartFile> imageUrl, String description, double price, boolean status, String categoryId, String oldImage) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.oldImage = oldImage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<MultipartFile> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<MultipartFile> imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getOldImage() {
        return oldImage;
    }

    public void setOldImage(String oldImage) {
        this.oldImage = oldImage;
    }
}
