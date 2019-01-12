package Pro929.UniqueEmailAddresses;

import java.util.Set;

public class Solution
{
    public int numUniqueEmails(String[] emails) {
        if (emails.length == 0 || emails == null){
            return 0;
        }
        Set<String> diffEmails = new HashSet<>();
        for(String email : emails){
            String[] arr = email.split("@");
            String diffEmail = arr[0];
            // ºöÂÔ+ºÅºóÃæµÄ×Ö·û
            int index = diffEmail.indexOf("+");
            if(index != -1){
                diffEmail = diffEmail.substring(0,index);
            }
            //Ìæ»»¶ººÅ
            diffEmail = diffEmail.replaceAll("\\.","");
            diffEmails.add(diffEmail + "@" + arr[1]);
        }
        return diffEmails.size();
    }
}
