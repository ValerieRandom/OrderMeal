package menu;

public class Dish implements Cloneable {

    public Dish(String dishName, int dishPrice, String dishNumber) {
        DishName = dishName;
        DishPrice = dishPrice;
        DishNumber = dishNumber;
    }

    private String DishName;
    private int DishPrice;
    private String DishNumber;
    public Dish() {

    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // 轉成文字喇靠邀
    @Override
    public String toString() {
        return  DishNumber + " 號 " + DishName + " " + DishPrice + " 元"   ;
    }


    public String getDishNumber() {
        return DishNumber;
    }

    public void setDishNumber(String dishNumber) {

        DishNumber = dishNumber;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public int getDishPrice() {
        return DishPrice;
    }

    public void setDishPrice(int dishPrice) {
        DishPrice = dishPrice;
    }



}
