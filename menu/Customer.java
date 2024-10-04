package menu;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    // 客人屬性
    private int DeskNo; // 桌號
    private int Count; // 消費人數
    private int TotalPrice; // 消費總額

    // 複雜屬性 : 這桌客人點的菜 這段需要重新學習
    private List<Dish> dishes = new ArrayList<>(); // 注意這裡的變數名稱是 'dishes'

    // 新增 getter 方法以取得菜單列表
    public List<Dish> getDishes() {
        return dishes;
    }

    public Customer(int deskNo, int count, int totalPrice) {
        DeskNo = deskNo;
        Count = count;
        TotalPrice = totalPrice;
    }

    // 計算總價格的方法
    public int calculateTotalPrice() {
        int total = 0;
        for (Dish dish : dishes) {
            total += dish.getDishPrice(); // 獲取每個菜品的價格並累加
        }
        this.TotalPrice = total; // 更新客戶的總價格屬性
        return total;
    }

    public void checkout() {
        int totalPrice = calculateTotalPrice(); // 計算總價格
        System.out.println("您的消費總額為：" + totalPrice + " 元。");
    }

    // 新增根據菜品編號移除菜品的方法
    public boolean removeDishByNumber(String dishNumber) {
        return dishes.removeIf(dish -> dish.getDishNumber().equals(dishNumber));
    }
}
