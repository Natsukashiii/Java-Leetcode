package Pro709.ToLowerCase;

public class Solution
{
    public String toLowerCase(String str) {
        char[] charStr = str.toCharArray();
        for(int i = 0; i < charStr.length; i++){
            if ('A' <= charStr[i] && charStr[i] <= 'Z'){
                charStr[i] = (char) (charStr[i] - 'A' + 'a');
            }
        }
        return new String(charStr);
    }
}
