package ZTest;

public class Storage
{
    int data1;
    float data2;

    Storage(int data1, int data2)
    {
        this.data1 = data1;
        this.data2 = data2;
    }

    void modify(int va)
    {
        this.data1 = this.data1 / va;
        this.data2 = this.data1 / va;
        va = va + 2;
    }
}
