package Pro01.Find;

//从左下角开始遍历，
//如果这个数大于目标，减少行数
//如果这个数小于目标，列数增加

public class Solution
{
    public boolean Find(int target, int[][] array)
    {
//        多少行
        int row = array.length;
//        多少列
        int col = array[0].length;
//        i用于遍历行坐标，j用于遍历列坐标
        int i = row - 1, j = 0;

        while (i >= 0 && j < col)
        {
//            从左下角开始寻找
            int number = array[i][j];
            if (target == number)
            {
                return true;
            }
            else if (number > target)
            {
                i--;
            }
            else
            {
                j++;
            }
        }
        return false;
    }


    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int[][] array = {{2, 4, 6, 7}, {4, 9, 10, 13}, {6, 10, 15, 20}};
        if (solution.Find(8, array))
        {
            System.out.println("存在");
        }
        else
        {
            System.out.println("不存在");
        }
    }
}

