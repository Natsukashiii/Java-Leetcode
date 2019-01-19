package Pro832.FlippinganImage;

public class Solution {
    public static void main(String[] args) {
        int[][] A=new int[][]{{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
        int[][] res=flipAndInvertImage(A);
        for (int[] a: A) {
            for (int b:a){
                System.out.println(b+" ");
            }
            System.out.println();
        }
    }
    public static int[][] flipAndInvertImage(int[][] A) {
        int len = A.length;
        int[][] tmp = new int[len][];
        for(int i = 0; i < len; i++){
            int len2 = A[i].length;
            tmp[i] = new int[len2];
            for(int j = 0; j < len2; j++){
                tmp[i][len2-j-1] = A[i][j] == 0?1:0;
            }
        }
        return tmp;
    }


}
