package ProXX.Utils;



import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;

/**
 * 实现了一个FTPClient连接池
 * @author heaven
 */
public class FTPClientPool implements ObjectPool<FTPClient> {
    private static final int DEFAULT_POOL_SIZE = 5;
    private final BlockingQueue<FTPClient> pool;
    private final FTPClientFactory factory;

    /**
     * 初始化连接池，需要注入一个工厂来提供FTPClient实例
     * @param factory
     * @throws Exception
     */
    public FTPClientPool(FTPClientFactory factory) throws Exception{
        this(DEFAULT_POOL_SIZE, factory);
    }
    /**
     *
     * @param factory
     * @throws Exception
     */
    public FTPClientPool(int poolSize, FTPClientFactory factory) throws Exception {
        this.factory = factory;
        pool = new ArrayBlockingQueue<FTPClient>(poolSize*2);
        initPool(poolSize);
    }
    /**
     * 初始化连接池，需要注入一个工厂来提供FTPClient实例
     * @param maxPoolSize
     * @throws Exception
     */
    private void initPool(int maxPoolSize) throws Exception {
        for(int i=0;i<maxPoolSize;i++){
            //往池中添加对象
            addObject();
        }

    }
    /* (non-Javadoc)
     * @see org.apache.commons.pool.ObjectPool#borrowObject()
     */
    @Override
    public FTPClient borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
        FTPClient client = pool.take();
        if (client == null) {
            client = factory.create();
            addObject();
        }else if(!factory.validateObject(client)){//验证不通过
            //使对象在池中失效
            invalidateObject(client);
            //制造并添加新对象到池中
            client = factory.create();
            addObject();
        }
        return client;

    }

    /* (non-Javadoc)
     * @see org.apache.commons.pool.ObjectPool#returnObject(java.lang.Object)
     */
    @Override
    public void returnObject(FTPClient client) throws Exception {
        if ((client != null) && !pool.offer(client,3,TimeUnit.SECONDS)) {
            try {
                factory.destroyObject(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void invalidateObject(FTPClient client) throws Exception {
        //移除无效的客户端
        pool.remove(client);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.pool.ObjectPool#addObject()
     */
    @Override
    public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
        //插入对象到队列
        pool.offer(factory.create(),3,TimeUnit.SECONDS);
    }

    @Override
    public int getNumIdle() throws UnsupportedOperationException {
        return 0;
    }

    @Override
    public int getNumActive() throws UnsupportedOperationException {
        return 0;
    }

    @Override
    public void clear() throws Exception, UnsupportedOperationException {

    }

    /* (non-Javadoc)
     * @see org.apache.commons.pool.ObjectPool#close()
     */
    @Override
    public void close() {
        try {
            while(pool.iterator().hasNext()){
                FTPClient client = pool.take();
                factory.destroyObject(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFactory(BasePooledObjectFactory<FTPClient> factory) throws IllegalStateException, UnsupportedOperationException {

    }
}

