//package Pro000.FTPUtil;
//
//
//
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author natsukashii
// */
//
//public class FTPUtils
//{
////    private static final Logger logger = LoggerFactory.getLogger(FTPUtils.class);
//
////    //ftp服务器地址
////    public String hostname = "119.23.214.4";
////    //ftp服务器端口号默认为21
////    public Integer port = 21;
////    //ftp登录账号
////    public String username = "payegis-ftp";
////    //ftp登录密码
////    public String password = "123456";
////
////    public FTPClient ftpClient = null;
//
//
//    /**
//     * 日志对象
//     **/
//    private static final Logger LOGGER = LoggerFactory.getLogger(FtpUtil1.class);
//
//    /**
//     * FTP地址
//     **/
//    @Value("${ip}")
//    private String FTP_ADDRESS;
//
//    /**
//     * FTP端口
//     **/
//    @Value("${port}")
//    private int FTP_PORT;
//
//    /**
//     * FTP用户名
//     **/
//    @Value("${username}")
//    private String FTP_USERNAME;
//
//    /**
//     * FTP密码
//     **/
//    @Value("${password}")
//    private String FTP_PASSWORD;
//
//
//    /**
//     * 本地字符编码
//     **/
//    private static String localCharset = "GBK";
//
//    /**
//     * FTP协议里面，规定文件名编码为iso-8859-1
//     **/
//    private static String serverCharset = "ISO-8859-1";
//
//    /**
//     * UTF-8字符编码
//     **/
//    private static final String CHARSET_UTF8 = "UTF-8";
//
//    /**
//     * OPTS UTF8字符串常量
//     **/
//    private static final String OPTS_UTF8 = "OPTS UTF8";
//
//    /**
//     * 设置缓冲区大小4M
//     **/
//    private static final int BUFFER_SIZE = 1024 * 1024 * 4;
//
//    /**
//     * FTPClient对象
//     **/
//    private static FTPClient ftpClient = null;
//
//
//    /**
//     * 初始化ftp服务器
//     */
//    public void initFtpClient()
//    {
//        ftpClient = new FTPClient();
//        ftpClient.setControlEncoding("utf-8");
//        try
//        {
//            logger.info("connecting...ftp服务器:" + FTP_ADDRESS + ":" + FTP_PORT);
//            ftpClient.connect(FTP_ADDRESS, FTP_PORT); //连接ftp服务器
//            ftpClient.login(FTP_USERNAME, FTP_PASSWORD); //登录ftp服务器
//            ftpClient.setControlEncoding("GBK");
//            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
//            conf.setServerLanguageCode("zh");
//            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
//            if (!FTPReply.isPositiveCompletion(replyCode))
//            {
//                logger.info("connect failed...ftp服务器:" + FTP_ADDRESS + ":" + FTP_PORT + " 状态码：" + replyCode);
//            }
//            else
//            {
//                logger.info("connect successfu...ftp服务器:" + FTP_ADDRESS + ":" + FTP_PORT + " 状态码：" + replyCode);
//            }
//        }
//        catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 上传文件夹
//     *
//     * @param localPath 本地上传路径 ""
//     * @param ftpPath   FTP服务器中的路径 "upload/appName/2080730"
//     */
//    public boolean uploadLocalDirectory(String ftpPath, String localPath) throws IOException
//    {
//        initFtpClient();
//        boolean flag = false;
//        File localDir = new File(localPath);
//        File localDirList[] = localDir.listFiles();
//
//
//        ftpClient.setControlEncoding("GBK");
//        ftpClient.setFileType(2);
//        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
//        conf.setServerLanguageCode("zh");
//        ftpClient.configure(conf);
//
//
//
//        if (!localDir.exists())
//        {
//            logger.info("文件夹：" + localPath + " 不存在！请重新扫描APP.");
//        }
//        if (!ftpClient.changeWorkingDirectory(ftpPath))
//        {
//            createDirectorys(ftpPath);
//        }
//        if (localDirList.length != 0)
//        {
//            for (File file : localDirList)
//            {
//                if (file.isDirectory())
//                {
//                    //如果是文件夹，递归上传
//                    String parentDir[] = file.getPath().split("/");
//                    String remotePathCurrent = ftpPath + "/" + parentDir[parentDir.length - 1];
//                    flag = uploadLocalDirectory(remotePathCurrent, file.getAbsolutePath());
//                    logger.info("正在上传文件夹>>>>>>>>>>>>>>" + file.getAbsolutePath());
//                }
//                else if (file.isFile())
//                {
//                    //如果不是文件夹，获取当前路径并上传
//                    String sourceFile = file.getPath();
//                    sourceFile=new String(sourceFile.getBytes("GBK"),"GBK");
//                    flag = uploadLocalFile(ftpPath, sourceFile);
//                    logger.info("正在上传文件>>>>>>>>>>>>>>" + file);
//                }
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 删除整个文件夹里的文件
//     */
//    public boolean deleteAllDirectory(String ftpPath) throws IOException
//    {
//
//        Boolean flag = false;
//        initFtpClient();
//        ftpClient.setControlEncoding("GBK");
//        ftpClient.setFileType(2);
//        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
//        conf.setServerLanguageCode("zh");
//        ftpClient.configure(conf);
//        if (ftpClient != null)
//        {
//
//            FTPFile[] files = ftpClient.listFiles(ftpPath);
//            System.out.println(files.length);
//            String[] fs = ftpClient.listNames(ftpPath);
//            // 判断该目录下是否有文件
//            if (fs == null || fs.length == 0)
//            {
//                LOGGER.error(ftpPath + "该目录下没有文件");
//                return flag;
//            }
//            for (FTPFile ftpFile : files)
//            {
//                if (ftpFile.isFile())
//                {
//                    String filePath = ftpPath + "/" + ftpFile.getName();
//                    filePath = new String(filePath.getBytes("GBK"));
//                    String[] ftpFileList = filePath.split("/");
//                    ftpClient.deleteFile(ftpPath + "/" + ftpFileList[ftpFileList.length - 1]);
//                }
//                else if (ftpFile.isDirectory())
//                {
//                    String filePath = ftpPath + "/" + ftpFile.getName();
//                    filePath = new String(filePath.getBytes("GBK"));
//                    String[] ftpFileList = filePath.split("/");
//                    deleteAllDirectory(ftpPath + "/" + ftpFileList[ftpFileList.length - 1]);
//
//                    ftpClient.removeDirectory(ftpPath + "/" + ftpFileList[ftpFileList.length - 1]);
//
//                }
//            }
////            for (String ftpFile : fs)
////            {
////                if (ftpFile.contains("Activity"))
////                {
////                    ftpFile = new String(ftpFile.getBytes("GBK"));
////                    String[] ftpFileList = ftpFile.split("/");
////                    deleteAllDirectory(ftpPath + "/" + ftpFileList[ftpFileList.length - 1]);
////                }
////                else
////                {
////                    ftpFile = new String(ftpFile.getBytes("GBK"));
////                    String[] ftpFileList = ftpFile.split("/");
////                    ftpClient.deleteFile(ftpPath + "/" + ftpFileList[ftpFileList.length - 1]);
////                }
////            }
//            flag = true;
//        }
//        return flag;
//    }
//
//
//    /**
//     * 递归遍历出目录下面所有文件
//     *
//     * @param pathName 需要遍历的目录，必须以"/"开始和结束
//     * @throws IOException
//     */
//    public List List(String pathName) throws IOException
//    {
//        ArrayList<String> arFiles = null;
//        List list = new ArrayList();
////        if (pathName.startsWith("/") && pathName.endsWith("/"))
////        {
//        String directory = pathName;
//        //更换目录到当前目录
//        ftpClient.changeWorkingDirectory(directory);
//        FTPFile[] files = ftpClient.listFiles();
//        for (FTPFile file : files)
//        {
//            if (file.isFile())
//            {
//                arFiles.add(directory + file.getName());
//            }
//            else if (file.isDirectory())
//            {
//                list = List(directory + file.getName() + "/");
//            }
//        }
////        }
//        return list;
//    }
//
//
//    /**
//     * 上传本地文件
//     *
//     * @param ftpPath  ftp保存路径
//     * @param savePath 本地文件路径
//     */
//    public boolean uploadLocalFile(String ftpPath, String savePath)
//    {
//        String fileName = "";
//        // 登录
//        initFtpClient();
//        boolean flag = false;
//        if (ftpClient != null)
//        {
//            File file = new File(savePath);
//            fileName = file.getName();
//            FileInputStream fis = null;
//            try
//            {
//                fis = new FileInputStream(file);
//                ftpClient.setBufferSize(BUFFER_SIZE);
//                // 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
//                ftpClient.setControlEncoding(localCharset);
//                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
////                String path = changeEncoding(BASE_PATH + ftpPath);
//                String path = ftpPath;
////                 目录不存在，则递归创建
//                if (!ftpClient.changeWorkingDirectory(path))
//                {
//                    createDirectorys(path);
//                }
//                // 设置被动模式，开通一个端口来传输数据
//                ftpClient.enterLocalPassiveMode();
//                // 上传文件
////                flag = ftpClient.storeFile(new String(fileName.getBytes(CHARSET_UTF8), serverCharset), fis);
//                flag = ftpClient.storeFile(fileName, fis);
//                if (flag)
//                {
//                    logger.info("上传文件： " + fileName + " 到文件夹：" + path + "成功！！！");
//                }
//                else
//                {
//                    logger.info("上传文件： " + fileName + " 到文件夹：" + path + "失败");
//                }
//            }
//            catch (Exception e)
//            {
//                logger.error("本地文件上传FTP失败", e);
//            }
//            finally
//            {
//                IOUtils.closeQuietly(fis);
//                closeConnect();
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * FTP服务器路径编码转换
//     *
//     * @param ftpPath FTP服务器路径
//     * @return String
//     */
//    private static String changeEncoding(String ftpPath)
//    {
//        String directory = null;
//        try
//        {
//            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON")))
//            {
//                localCharset = CHARSET_UTF8;
//            }
//            directory = new String(ftpPath.getBytes(localCharset), serverCharset);
//        }
//        catch (Exception e)
//        {
//            LOGGER.error("路径编码转换失败", e);
//        }
//        return directory;
//    }
//
//
//    /**
//     * 在服务器上递归创建目录
//     *
//     * @param dirPath 上传目录路径
//     * @return
//     */
//    public void createDirectorys(String dirPath)
//    {
//        try
//        {
//            if (!dirPath.endsWith("/"))
//            {
//                dirPath += "/";
//            }
//            String directory = dirPath.substring(0, dirPath.lastIndexOf("/") + 1);
//            ftpClient.makeDirectory("/");
//            int start = 0;
//            int end = 0;
//            if (directory.startsWith("/"))
//            {
//                start = 1;
//            }
//            else
//            {
//                start = 0;
//            }
//            end = directory.indexOf("/", start);
//            while (true)
//            {
//                String subDirectory = new String(dirPath.substring(start, end));
//                if (!ftpClient.changeWorkingDirectory(subDirectory))
//                {
//                    if (ftpClient.makeDirectory(subDirectory))
//                    {
//                        ftpClient.changeWorkingDirectory(subDirectory);
//                    }
//                    else
//                    {
//                        LOGGER.info("创建目录失败");
//                        return;
//                    }
//                }
//                start = end + 1;
//                end = directory.indexOf("/", start);
//                //检查所有目录是否创建完毕
//                if (end <= start)
//                {
//                    break;
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            LOGGER.error("上传目录创建失败", e);
//        }
//    }
//
//    /**
//     * 关闭FTP连接
//     */
//    private void closeConnect()
//    {
//        if (ftpClient != null && ftpClient.isConnected())
//        {
//            try
//            {
//                ftpClient.logout();
//                ftpClient.disconnect();
//            }
//            catch (IOException e)
//            {
//                LOGGER.error("关闭FTP连接失败", e);
//            }
//        }
//    }
//
//
//    /**
//     * 远程文件上传到FTP服务器
//     *
//     * @param ftpPath    FTP服务器文件相对路径，例如：test/123
//     * @param remotePath 远程文件路径，例如：http://www.baidu.com/xxx/xxx.jpg
//     * @param fileName   上传到FTP服务的文件名，例如：test.jpg
//     * @return boolean 成功返回true，否则返回false
//     */
//
//    /**
//     * 改变目录路径
//     */
//    public boolean changeWorkingDirectory(String directory)
//    {
//        boolean flag = true;
//        try
//        {
//            flag = ftpClient.changeWorkingDirectory(directory);
//            if (flag)
//            {
//                logger.info("进入文件夹" + directory + " 成功！");
//            }
//            else
//            {
//                logger.info("进入文件夹" + directory + " 失败！开始创建文件夹");
//            }
//        }
//        catch (IOException ioe)
//        {
//            ioe.printStackTrace();
//        }
//        return flag;
//    }
//
//    /**
//     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
//     */
//    public boolean createDirecroty(String remotedDirectory, String workingDirectory) throws IOException
//    {
//        initFtpClient();
//        changeWorkingDirectory(workingDirectory);
//        boolean success = true;
//        String directory = remotedDirectory + "/";
//        // 如果远程目录不存在，则递归创建远程服务器目录
//        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory)))
//        {
//            int start = 0;
//            int end = 0;
//            if (directory.startsWith("/"))
//            {
//                start = 1;
//            }
//            else
//            {
//                start = 0;
//            }
//            end = directory.indexOf("/", start);
//            String path = "";
//            String paths = "";
//            while (true)
//            {
////                String subDirectory = new String(remotedDirectory.substring(start, end).getBytes("GBK"), "iso-8859-1");
//                String subDirectory = remotedDirectory;
//                path = path + "/" + subDirectory;
//                if (!existFile(path))
//                {
//                    if (makeDirectory(subDirectory))
//                    {
//                        changeWorkingDirectory(subDirectory);
//                    }
//                    else
//                    {
//                        logger.info("创建目录[" + subDirectory + "]失败");
//                        changeWorkingDirectory(subDirectory);
//                    }
//                }
//                else
//                {
//                    changeWorkingDirectory(subDirectory);
//                }
//
//                paths = paths + "/" + subDirectory;
//                start = end + 1;
//                end = directory.indexOf("/", start);
//                // 检查所有目录是否创建完毕
//                if (end <= start)
//                {
//                    break;
//                }
//            }
//        }
//        return success;
//    }
//
//    /**
//     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
//     */
//    public boolean createDirecroty(String workingDirectory) throws IOException
//    {
//        String[] dir = workingDirectory.split("/");
//        initFtpClient();
//        changeWorkingDirectory(workingDirectory);
//        boolean success = true;
//        String directory = dir[1] + "/";
//        // 如果远程目录不存在，则递归创建远程服务器目录
//        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory)))
//        {
//            int start = 0;
//            int end = 0;
//            if (directory.startsWith("/"))
//            {
//                start = 1;
//            }
//            else
//            {
//                start = 0;
//            }
//            end = directory.indexOf("/", start);
//            String path = "";
//            String paths = "";
//            while (true)
//            {
////                String subDirectory = new String(remotedDirectory.substring(start, end).getBytes("GBK"), "iso-8859-1");
//                String subDirectory = dir[2];
//                path = path + "/" + subDirectory;
//                if (!existFile(path))
//                {
//                    if (makeDirectory(subDirectory))
//                    {
//                        changeWorkingDirectory(subDirectory);
//                    }
//                    else
//                    {
//                        logger.info("创建目录[" + subDirectory + "]失败");
//                        changeWorkingDirectory(subDirectory);
//                    }
//                }
//                else
//                {
//                    changeWorkingDirectory(subDirectory);
//                }
//
//                paths = paths + "/" + subDirectory;
//                start = end + 1;
//                end = directory.indexOf("/", start);
//                // 检查所有目录是否创建完毕
//                if (end <= start)
//                {
//                    break;
//                }
//            }
//        }
//        return success;
//    }
//
//
//    /**
//     * 创建目录
//     */
//    public boolean makeDirectory(String dir)
//    {
//        boolean flag = true;
//        try
//        {
//            flag = ftpClient.makeDirectory(dir);
//            if (flag)
//            {
//                logger.info("创建文件夹" + dir + " 成功！");
//
//            }
//            else
//            {
//                logger.info("创建文件夹" + dir + " 失败！");
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//
//    /**
//     * 判断ftp服务器文件是否存在
//     */
//    public boolean existFile(String path) throws IOException
//    {
//        boolean flag = false;
//        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
//        if (ftpFileArr.length > 0)
//        {
//            flag = true;
//            logger.info("文件夹内已存在扫描文件！");
//        }
//        else
//        {
//            logger.info("文件夹为空！");
//        }
//        return flag;
//    }
//
//
////
////    /**
////     * @param file 上传的文件或文件夹
////     * @throws Exception
////     */
////    public void upload(File file) throws Exception
////    {
////        logger.info("正在上传>>>>>>>");
////        if (file.isDirectory())
////        {
////            ftpClient.makeDirectory(file.getName());
////            logger.info("file.getName():" + file.getName());
////            ftpClient.changeWorkingDirectory(file.getName());
////            String[] files = file.list();
////            for (int i = 0; i < files.length; i++)
////            {
////                File file1 = new File(file.getPath() + "\\" + files[i]);
////                if (file1.isDirectory())
////                {
////                    upload(file1);
////                    ftpClient.changeToParentDirectory();
////                }
////                else
////                {
////                    File file2 = new File(file.getPath() + "\\" + files[i]);
////                    FileInputStream input = new FileInputStream(file2);
////                    ftpClient.storeFile(file2.getName(), input);
////                    input.close();
////                }
////            }
////        }
////        else
////        {
////            File file2 = new File(file.getPath());
////            FileInputStream input = new FileInputStream(file2);
////            ftpClient.storeFile(file2.getName(), input);
////            input.close();
////        }
////    }
////
//
////
////    /**
////     * 下载文件 *
////     *
////     * @param pathname  FTP服务器文件目录 *
////     * @param filename  文件名称 *
////     * @param localpath 下载后的文件路径 *
////     * @return
////     */
////    public boolean downloadFile(String pathname, String filename, String localpath)
////    {
////        boolean flag = false;
////        OutputStream os = null;
////        try
////        {
////            logger.info("开始下载文件");
////            initFtpClient();
////            //切换FTP目录
////            ftpClient.changeWorkingDirectory(pathname);
////            FTPFile[] ftpFiles = ftpClient.listFiles();
////            for (FTPFile file : ftpFiles)
////            {
////                if (filename.equalsIgnoreCase(file.getName()))
////                {
////                    File localFile = new File(localpath + "/" + file.getName());
////                    os = new FileOutputStream(localFile);
////                    ftpClient.retrieveFile(file.getName(), os);
////                    os.close();
////                }
////            }
////            ftpClient.logout();
////            flag = true;
////            logger.info("下载文件成功");
////        }
////        catch (Exception e)
////        {
////            logger.info("下载文件失败");
////            e.printStackTrace();
////        }
////        finally
////        {
////            if (ftpClient.isConnected())
////            {
////                try
////                {
////                    ftpClient.disconnect();
////                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////            }
////            if (null != os)
////            {
////                try
////                {
////                    os.close();
////                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////            }
////        }
////        return flag;
////    }
////
////    /**
////     * 删除文件 *
////     *
////     * @param pathname FTP服务器保存目录 *
////     * @param filename 要删除的文件名称 *
////     * @return
////     */
////    public boolean deleteFile(String pathname, String filename)
////    {
////        boolean flag = false;
////        try
////        {
////            logger.info("开始删除文件");
////            initFtpClient();
////            //切换FTP目录
////            ftpClient.changeWorkingDirectory(pathname);
////            ftpClient.dele(filename);
////            ftpClient.logout();
////            flag = true;
////            logger.info("删除文件成功");
////        }
////        catch (Exception e)
////        {
////            logger.info("删除文件失败");
////            e.printStackTrace();
////        }
////        finally
////        {
////            if (ftpClient.isConnected())
////            {
////                try
////                {
////                    ftpClient.disconnect();
////                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////            }
////        }
////        return flag;
////    }
////
////
////    /**
////     * 删除文件夹下所有文件
////     */
////    public boolean deleteAllFile(String path)
////    {
////        logger.info("正在删除文件夹 " + path + " 所有文件>>>>>>>>>>");
////        boolean flag = false;
////        File file = new File(path);
////        if (!file.exists())
////        {
////            return flag;
////        }
////        if (!file.isDirectory())
////        {
////            return flag;
////        }
////        String[] tempList = file.list();
////        File temp = null;
////        for (int i = 0; i < tempList.length; i++)
////        {
////            if (path.endsWith(File.separator))
////            {
////                temp = new File(path + tempList[i]);
////            }
////            else
////            {
////                temp = new File(path + File.separator + tempList[i]);
////            }
////            if (temp.isFile())
////            {
////                temp.delete();
////            }
////            if (temp.isDirectory())
////            {
////                deleteAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
////                deleteFileFolder(path + "/" + tempList[i]);// 再删除空文件夹
////                flag = true;
////            }
////        }
////        return flag;
////    }
////
////    /***
////     * 删除文件夹
////     *
////     */
////    public void deleteFileFolder(String folderPath)
////    {
////        try
////        {
////            deleteAllFile(folderPath); // 删除完里面所有内容
////            String filePath = folderPath;
////            filePath = filePath.toString();
////            java.io.File myFilePath = new java.io.File(filePath);
////            myFilePath.delete(); // 删除空文件夹
////        }
////        catch (Exception e)
////        {
////            e.printStackTrace();
////        }
////    }
////
////
////    /**
////     * 上传文件
////     *
////     * @param pathname       ftp服务保存地址
////     * @param fileName       上传到ftp的文件名
////     * @param originfilename 待上传文件的名称（绝对地址） *
////     * @return
////     */
////    public boolean uploadFile(String pathname, String fileName, String originfilename, String workingdirectory)
////    {
////        boolean flag = false;
////        InputStream inputStream = null;
////        try
////        {
////            logger.info("开始上传文件>>>>>>>>>>>>>");
////            inputStream = new FileInputStream(new File(originfilename));
////            initFtpClient();
////            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
////            createDirecroty(pathname, workingdirectory);
////            ftpClient.makeDirectory(pathname);
////            ftpClient.changeWorkingDirectory(pathname);
////            ftpClient.storeFile(fileName, inputStream);
////            inputStream.close();
////            ftpClient.logout();
////            flag = true;
////            logger.info("上传文件成功");
////        }
////        catch (Exception e)
////        {
////            logger.info("上传文件失败");
////            e.printStackTrace();
////        }
////        finally
////        {
////            if (ftpClient.isConnected())
////            {
////                try
////                {
////                    ftpClient.disconnect();
////                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////            }
////            if (null != inputStream)
////            {
////                try
////                {
////                    inputStream.close();
////                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////            }
////        }
////        return true;
////    }
////
////    /**
////     * 上传文件
////     *
////     * @param pathname    ftp服务保存地址
////     * @param fileName    上传到ftp的文件名
////     * @param inputStream 输入文件流
////     * @return
////     */
////    public boolean uploadFile(String pathname, String fileName, InputStream inputStream, String workingdirectory)
////    {
////        boolean flag = false;
////        try
////        {
////            logger.info("开始上传文件>>>>>>>>>>>>>");
////            initFtpClient();
////            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
////            createDirecroty(pathname, workingdirectory);
////            ftpClient.makeDirectory(pathname);
////            ftpClient.changeWorkingDirectory(pathname);
////            ftpClient.storeFile(fileName, inputStream);
////            inputStream.close();
////            ftpClient.logout();
////            flag = true;
////            logger.info("上传文件成功");
////        }
////        catch (Exception e)
////        {
////            logger.info("上传文件失败");
////            e.printStackTrace();
////        }
////        finally
////        {
////            if (ftpClient.isConnected())
////            {
////                try
////                {
////                    ftpClient.disconnect();
////                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////            }
////            if (null != inputStream)
////            {
////                try
////                {
////                    inputStream.close();
////                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////            }
////        }
////        return true;
////    }
////
////    /**
////     * <p>Discription:[根据原目标路径进行递归,依次获取此路径下的文件上传]</p>
////     * Created on 2017年3月27日 下午3:10:41
////     *
////     * @param filePath   源文件路径的共有路径,不能为空
////     * @param remotePath 目标路径的共有路径,不能为空
////     * @return void
////     * @author:[全冉]
////     */
////    public void uploadFileList(String filePath, String remotePath) throws Exception
////    {
////        //获取原目标路径下的所有文件
////        File dir = new File(filePath);
////        File[] files = dir.listFiles();
////        if (files == null)
////        {
////            return;
////        }
////        for (int i = 0; i < files.length; i++)
////        {
////            if (files[i].isDirectory())
////            {
////                //是文件夹,则递归
////                uploadFileList(files[i].getAbsolutePath(), remotePath);//递归文件夹！！！
////            }
////            else
////            {
////                String sourceFile = files[i].getPath();
////                String excessivePath = sourceFile.substring(sourceFile.lastIndexOf(File.separator + remotePath + File.separator), sourceFile.length() - 1);
////                String remotePathCurrent = remotePath + excessivePath.substring(0, excessivePath.indexOf(files[i].getName()));
//////                String remotePathCurrent = remotePath + sourceFile.substring(filePath.length(),sourceFile.length()-1);
////                //是文件,则执行上传到FTP方法
////                uploadFile(remotePath, remotePathCurrent, remotePathCurrent, filePath);
////            }
////        }
////    }
////
////    /**
////     * <p>Discription:[将单文件上传到FTP服务器]</p>
////     * Created on 2017年3月27日 下午3:17:11
////     *
////     * @param sourceFile 本地文件绝对路径
////     * @param remotePath FTP服务器的存储绝对路径
////     * @return void
////     * @author:[全冉]
////     */
////    private void process(String sourceFile, String remotePath) throws Exception
////    {
////        //对remotePath参数不为空的判断
////        if (StringUtils.isBlank(remotePath))
////        {
////            File file = new File(sourceFile);
////            remotePath = file.getParent();
////        }
////        File file = new File(sourceFile);
////        if (!file.exists())
////        {
////            throw new IOException("文件不存在.");
////        }
////        FileInputStream is = new FileInputStream(file);
////        // 转移工作目录至指定目录下
////        logger.info("FTP服务器开始更改操作的目录...");
////        this.changeWorkingDirectory(remotePath);
////        logger.info("开始往FTP服务器上传文件...");
////        boolean isFinish = ftpClient.storeFile(new String(file.getName().getBytes("GBK"), "iso-8859-1"), is);
////        if (isFinish)
////        {
////            logger.info("FTP服务器文件[" + file.getName() + "]上传成功");
////        }
////        else
////        {
////            logger.info("FTP服务器文件[" + file.getName() + "]上传失败");
////        }
////        is.close();
////    }
//
//
//}
