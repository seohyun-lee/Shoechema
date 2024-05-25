package JAVA_Files.product;


import java.util.Date;


// 신발 관련 데이터 저장할 클래스
public class Shoes {
    private int shoesId; //shoes_id -> 신발 id
    private String shoesName; //shoes_name -> 신발 이름
    private int price; //price -> 신발 가격
    private Date releaseDate; //realease_date -> 신발 출시일
    private int shoesOptId; //shoes_option_id -> 신발 옵션 id
    private int quantity; //quantity -> 재고
    private int sizeNum; //size_number -> 사이즈 숫자


    //각 데이터 getter, setter 추가
    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
    }

    public String getShoesName() {
        return shoesName;
    }

    public void setShoesName(String shoesName) {
        this.shoesName = shoesName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getShoesOptId() {
        return shoesOptId;
    }

    public void setShoesOptId(int shoesOptId) {
        this.shoesOptId = shoesOptId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSizeNum() {
        return sizeNum;
    }

    public void setSizeNum(int sizeNum) {
        this.sizeNum = sizeNum;
    }

}
