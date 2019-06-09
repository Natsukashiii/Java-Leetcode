package Pro661.ImageSmoother;

/**
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
 * <p>
 * Example 1:
 * Input:
 * [[1,1,1],
 * [1,0,1],
 * [1,1,1]]
 * Output:
 * [[0, 0, 0],
 * [0, 0, 0],
 * [0, 0, 0]]
 * Explanation:
 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 */
public class Solution
{
    public static void main(String[] args)
    {
        int[][] M1 = {{1, 1, 1,}, {1, 0, 1}, {1, 1, 1}};

        int[][] N = imageSmoother(M1);

        for (int i = 0; i < N.length; i++)
        {
            for (int j = 0; j < N[i].length; j++)
            {
                System.out.print(N[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 计算周围所有元素的和并求平均值
     */
    public static int[][] imageSmoother(int[][] M)
    {
        if (M.length == 0 || M[0].length == 0)
    {
        return M;
    }
        int m = M.length;
        int n = M[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int avg = getAverage(M, i, j, m, n);
                res[i][j] = avg;
            }
        }
        return res;
    }

    public static int getAverage(int[][] M, int i, int j, int m, int n)
    {
        int sum = 0;
        sum += M[i][j];
        int count = 1;
        if (i - 1 >= 0 && j - 1 >= 0)
        {
            sum += M[i - 1][j - 1];
            count++;
        }
        if (i - 1 >= 0)
        {
            sum += M[i - 1][j];
            count++;
        }
        if (i - 1 >= 0 && j + 1 < n)
        {
            sum += M[i - 1][j + 1];
            count++;
        }
        if (j - 1 >= 0)
        {
            sum += M[i][j - 1];
            count++;
        }
        if (j + 1 < n)
        {
            sum += M[i][j + 1];
            count++;
        }
        if (i + 1 < m && j - 1 >= 0)
        {
            sum += M[i + 1][j - 1];
            count++;
        }
        if (i + 1 < m)
        {
            sum += M[i + 1][j];
            count++;
        }
        if (i + 1 < m && j + 1 < n)
        {
            sum += M[i + 1][j + 1];
            count++;
        }
        int res = (int) Math.floor((double) sum / count);
        return res;
    }
}
