package Testing.xxx;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("gm");
        synchronized (args){
            System.out.println("sir");
            try
            {
                args.wait();
            }catch (InterruptedException e){

            }
        }
        System.out.println("mam");
    }
}
