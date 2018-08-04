package ticketsale;

public class saleStation extends Thread
{
    public saleStation(String name)
    {
        super(name);
    }

    //票的个数
    static int tickets = 50;

    static Object object = "A";

    @Override
    public void run()
    {
        while (tickets > 0)
        {
            synchronized (object)
            {
                if (tickets > 0)
                {
                    System.out.println(getName() + "  sale:  " + tickets);
                    tickets--;
                }else {
                    System.out.println("running out...");
                }
            }

            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
