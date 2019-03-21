package Pro01.Find;


/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * Solution: 从左下角开始遍历, 如果这个数大于目标, 减少行数, 如果这个数小于目标, 增加列数
 */
public class Solution {

  public boolean Find(int target, int[][] array) {
//        多少行
    int row = array.length;
//        多少列
    int col = array[0].length;
//        i用于遍历行坐标，j用于遍历列坐标
    int i = row - 1, j = 0;

    while (i >= 0 && j < col) {
//            从左下角开始寻找
      int number = array[i][j];
      if (target == number) {
        return true;
      } else if (number > target) {
        i--;
      } else {
        j++;
      }
    }
    return false;
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] array = {{2, 4, 6, 7}, {4, 9, 10, 13}, {6, 10, 15, 20}};
    if (solution.Find(8, array)) {
      System.out.println("存在");
    } else {
      System.out.println("不存在");
    }
  }
}

