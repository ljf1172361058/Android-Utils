
import java.io.File;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/** 
 * SD卡相关的辅助类<br/>
 * 
 * 不要忘了在Android的manifest.xml文档中添加权限:
 * <!-- 在SDCard中创建与删除文件权限 -->
 * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
 * <!-- 往SDCard写入数据权限 -->  
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 * 
 * @author lizhihui<br/> 
 * @since  2016/06/27 下午2:29:40
 */  
public class SDCardUtils{  
	private static final String TAG="test";
	
    private SDCardUtils()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
  
    /** 
     * 判断SDCard是否可用 
     * 
     * 判断手机是否存在外部存储目录即SDCard(其实手机内部存储跟SD卡存储都是外部存储,真正的内部储存位置是data/data/包名) 
     * 
     * @return 
     */    
    public static boolean isSdCardExists()
    {  
        if (android.os.Environment.getExternalStorageState().equals(  
                   android.os.Environment.MEDIA_MOUNTED)) {  
               return true;  
           } else {  
               Log.e(TAG, "the Sdcard is not exists");  
              return false;  
        }  
    }
  
    /** 
     * 获取SD卡路径 
     *  
     * @return 
     */  
    public static String getSDCardPath()  
    {  
        if (isSdCardExists()){
            return Environment.getExternalStorageDirectory().getAbsolutePath()  
                + File.separator;//与系统有关的默认名称分隔符,为了方便,它被表示为一个字符串。此字符串只包含一个字符，即 separatorChar。  
        }
        return "";
    }  
    
    /**
     * 获取SD卡可用大小 单位:MB
     * 
     * 单位的转换也可通过下面的文件大小转换函数实现(可实现 MB-GB自动转换)
     * Formatter.formatFileSize(Context context, availableBlocks * blockSize);
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
	public static String getSdcardAvailbleSize() {
    	
        if (!isSdCardExists()) {  
            return "";
        }    
        File path = Environment.getExternalStorageDirectory();
        // 获得一个磁盘状态对象
        StatFs stat = new StatFs(path.getPath());
        // 获得一个扇区的大小 
        long blockSize = stat.getBlockSize();
        // 获得可用的扇区数量  
        long availableBlocks = stat.getAvailableBlocks();  
        return String.valueOf((availableBlocks * blockSize) / 1024 / 1024);  
    }  
    
    /**
     * 获取SD卡总大小 单位:MB
     * 
     * 单位的转换也可通过下面的文件大小转换函数(可实现 MB-GB自动转换)
     * Formatter.formatFileSize(Context context, totalBlocks * blockSize); 
     * @return
     */
    @SuppressWarnings("deprecation")
	public static String getSdCardTotalSize() {  
        if (!isSdCardExists()) { 
            return "";
        }    
        File path = Environment.getExternalStorageDirectory();
        // 获得一个磁盘状态对象
        StatFs stat = new StatFs(path.getPath());
        // 获得一个扇区的大小 
		long blockSize = stat.getBlockSize();
        // 获得可用的扇区数量
        long totalBlocks = stat.getBlockCount();  
        return String.valueOf((totalBlocks * blockSize) / 1024 / 1024);  
    }
    
    /** 
     * 获取系统存储路径 
     *  
     * @return 
     */  
    public static String getRootDirectoryPath()  
    {  
        return Environment.getRootDirectory().getAbsolutePath();  
    }
    
    /**
     * 获取根目录文件列表
     * 
     * @return 
     */
    @SuppressWarnings("static-access")
	public static File[] getRootFiles() {  
           if (getSDCardPath().equals("")){  
               return null;
           }
           // @SuppressWarnings("unused")
           // List<File> files = new ArrayList<File>();  
           File file = new File(getSDCardPath());  
           return file.listRoots();  
       }
    
    /**
   	 * 判断手机是否有内存卡且内存是否大于20MB
   	 * 
   	 * @return 
   	 */
	 @SuppressWarnings("deprecation")
   	 public Object storageState(){
   		//判断手机是否存在外部存储目录即SDCard(其实手机内部存储跟SD卡存储都是外部存储,真正的内部储存位置是data/data/包名)
   		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//没有插入SD卡也会返回true,此时拿到的是手机内存路径
   			//得到SD卡的路径 
   			String path=Environment.getExternalStorageDirectory().getPath();
   	        // 获得一个磁盘状态对象
   	        StatFs stat = new StatFs(path);
   	        // 获得一个扇区的大小 
   			long blockSize = stat.getBlockSize();
   	        // 获得可用的扇区数量
   	        long totalBlocks = stat.getBlockCount();
   	        if((totalBlocks * blockSize) / 1024 / 1024>20){//剩余内存大于20M
   	        	return path;
   	        }
   	        else{
   	        	//Toast.makeText(context,"检测到内存卡剩余空间不足,请清理内存后再进行下载更新",Toast.LENGTH_LONG).show();
   	        	return false;
   	        }
   		}
   		else{
   			//Toast.makeText(context,"检测到内存卡不存在,请重新插入内存卡再进行下载更新",Toast.LENGTH_LONG).show();
   			return false;
   		}
   	 }
}  