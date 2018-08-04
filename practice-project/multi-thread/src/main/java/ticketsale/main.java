package ticketsale;

public class main
{
    public static void main(String[] args)
    {
        saleStation saleStation=new saleStation("Station");
        saleStation saleStation1=new saleStation("Station1");
        saleStation saleStation2=new saleStation("Station2");
        saleStation saleStation3=new saleStation("Station3");
        saleStation saleStation4=new saleStation("Station4");
        saleStation saleStation5=new saleStation("Station5");

        saleStation.start();
        saleStation1.start();
        saleStation2.start();
        saleStation3.start();
        saleStation4.start();
        saleStation5.start();

    }
}
