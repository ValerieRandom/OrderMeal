package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    /*
        創建一個本店菜單，本店菜單需要使用集合設計 並在點開 App 時就要初始化
    */
    static List<Dish> dishList = new ArrayList<>();

    // 初始化模塊
    static {
        dishList.add(new Dish("佛跳牆", 30, "1"));
        dishList.add(new Dish("螞蟻上樹", 20, "2"));
        dishList.add(new Dish("東坡肉", 30, "3"));
        dishList.add(new Dish("白飯", 5, "4"));
        dishList.add(new Dish("醬牛肉", 40, "5"));
        dishList.add(new Dish("烤鴨", 20, "6"));
    }

    public static void main(String[] args) {
        // 第一步必須創建客人對象，意味著來客人了，來時沒有點菜所以 0 元
        Customer customer = new Customer(1, 2, 0);

        // 接著要展示主菜單
        showMainMenu();

        // 開始主菜單選項選擇
        Scanner s = new Scanner(System.in);

        while (true) {  // 進入主菜單循環
            String oneLevelComm = s.nextLine();
            switch (oneLevelComm) {
                case "1":
                    showDishs();
                    // 進行點菜行為 需要二級 Command
                    while (true) {
                        // 獲得二級命令
                        String secondLevelComm = s.nextLine();
                        if ("*".equals(secondLevelComm)) {
                            System.out.println("點菜完成，請輸入 # 字返回上一級菜單");
                            break;
                        }
                        // 二級命令取得菜單編號並使用方法三獲取菜品
                        Dish dish = getDish(secondLevelComm);
                        System.out.println("獲取菜品: " + dish);
                        // 加入到客人已點菜單集合中
                        customer.getDishes().add(dish);
                        System.out.println("菜品成功加入菜單");
                    }
                    break;

                case "2":
                    // 先顯示已點的菜品
                    List<Dish> orderedDishes = customer.getDishes();
                    System.out.println("您已點的菜單：");

                    for (int i = 0; i < orderedDishes.size(); i++) {
                        Dish dish = orderedDishes.get(i);
                        System.out.println(dish);  // 這裡會呼叫 Dish 類的 toString() 方法
                    }

                    handleSecondLevelCommand(s, customer, false);
                    break;

                case "3":
                    customer.calculateTotalPrice();
                    customer.checkout();
                    break;

                case "4":
                    System.out.println("退出");
                    return;  // 結束程式

                case "5":
                    System.out.println("幫助");
                    break;

                case "#":
                    showMainMenu();
                    break;

                default:
                    System.out.println("請輸入數字 1 ~ 5");
                    break;
            }
        }
    }

    // 方法一 顯示主菜單
    private static void showMainMenu() {
        System.out.println("+-------主菜單-------+");
        System.out.println("|    1. 點菜         |");
        System.out.println("|    2. 查看已點     |");
        System.out.println("|    3. 結帳         |");
        System.out.println("|    4. 退出         |");
        System.out.println("|    5. 幫助         |");
        System.out.println("+--------------------+");
    }

    // 方法二 顯示店內菜色
    private static void showDishs() {
        System.out.println("==========本店特色==========");
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
        System.out.println("==========================");
    }

    // 方法三 根據二級命令來獲取一個菜對象
    private static Dish getDish(String secondLevelComm) {
        Dish targetDish = null;
        if (secondLevelComm != null && !"".equals(secondLevelComm)) {
            for (Dish dish : dishList) {
                if (secondLevelComm.equals(dish.getDishNumber())) {
                    try {
                        // 克隆 菜單裡的菜品
                        targetDish = (Dish) dish.clone();
                        break;
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return targetDish;
    }

    private static void handleSecondLevelCommand(Scanner s, Customer customer, boolean isAdding) {
        while (true) {
            String secondLevelComm = s.nextLine();
            if (secondLevelComm.equals("*")) {
                if (isAdding) {
                    System.out.println("點菜完成，請輸入 # 字返回上一級菜單");
                } else {
                    System.out.println("刪菜完成，請輸入 # 字返回上一級菜單");
                }
                break;
            }

            Dish dish;
            if (secondLevelComm.startsWith("-") && !isAdding) {
                // 刪除菜品操作
                String dishId = secondLevelComm.substring(1);
                if (customer.removeDishByNumber(dishId)) {
                    System.out.println("菜品成功刪除");
                } else {
                    System.out.println("菜品不存在，刪除失敗");
                }
            } else if (isAdding) {
                // 點菜操作
                dish = getDish(secondLevelComm);
                if (dish != null) {
                    customer.getDishes().add(dish);
                    System.out.println("菜品成功加入菜單: " + dish);
                } else {
                    System.out.println("菜品不存在，無法加入菜單");
                }
            } else {
                System.out.println("無效的命令");
            }
        }
    }
}
