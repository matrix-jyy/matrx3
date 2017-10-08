package com.zkingsoft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * mysql数据库的备份和还原操作
 * @author maoluguang
 *
 */
public class MysqlUtils {
	
	public static Log log = LogFactory.getLog(MysqlUtils.class);
	
	private String USERNAME;//数据库用户名  
	private String PASSWORD;//数据库用户密码  
	private String PORT;//数据库开放端口  
	private String IP;//数据库ip地址  
	private String DATABASENAME;//数据库名称  
	
	private File mysqldumpexe; //mysqldump.exe文件 
	private File mysqlexe; //mysql.exe文件 
	
	/**
	 * 初始化数据库参数
	 * @param username 数据库用户名
	 * @param password 数据库用户密码
	 * @param ip 数据库ip地址
	 * @param port 数据库开放端口
	 * @param databasename 数据库名称
	 * @param mysqlpath mysqldump工具所在目录的路径，通常为mysql安装路径的bin文件夹
	 */
	public MysqlUtils(String username, String password, 
			String ip, String port, String databasename, String mysqlpath) {
		this.USERNAME = username;
		this.PASSWORD = password;
		this.IP = ip;
		this.PORT = port;
		this.DATABASENAME = databasename;
		if(isWindows()){
			this.mysqldumpexe = new File(mysqlpath + "mysqldump.exe");
			this.mysqlexe = new File(mysqlpath + "mysql.exe");
        } else {
        	this.mysqldumpexe = new File(mysqlpath + "mysqldump");
        	this.mysqlexe = new File(mysqlpath + "mysql");
        }
	}
	
	/** 
	 * 根据数据库表名查找对应创建该表的sql语句脚本
	 * @param tableName 数据库表名 
	 * @return 创建该表的sql语句脚本 
	 * @throws IOException 文件读写错误 
	 * @author maoluguang
	 */
	public static String getTableSql(MysqlUtils config, String tableName) throws IOException {
		//File f = getMysqlDump();
		if(config.mysqldumpexe == null || !config.mysqldumpexe.exists()){
			throw new FileNotFoundException("在"+config.mysqldumpexe.getPath()+"路径下没有找到名为'mysqldump'的mysql工具,MysqlDumpUtil类的功能依赖此文件!");
		}
		
		//构建远程数据库命令字符串
		StringBuilder cmdStr = new StringBuilder();
		cmdStr.append(" -d --compact -u").append(config.USERNAME)
			.append(" -p").append(config.PASSWORD)
			.append(" -h").append(config.IP)
			.append(" -P").append(config.PORT)
			.append(" ").append(config.DATABASENAME)
			.append(" ").append(tableName);
		
		Runtime rt = Runtime.getRuntime();
		//数据库构建表的sql脚本 
		StringBuilder tableSql = new StringBuilder();
		//调用mysqldump的cmd命令
		Process p = null;
		if(isWindows()) {
			p = rt.exec(new String[]{"cmd","/c",config.mysqldumpexe.getPath() + cmdStr.toString()});
		} else {
			p = rt.exec(new String[]{"sh","-c","cd "+ config.mysqldumpexe.getParent() + " | mysqldump " + cmdStr.toString()});
		}
		// 把进程执行中的控制台输出信息写入.sql文件
		// 注:如果不对控制台信息进行读出，则会导致进程堵塞无法运行
		InputStream in = p.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
		
		for(String inStr = null; (inStr = br.readLine()) != null;) {
			tableSql.append(inStr).append("<br>");
		}
		
		in.close();
		br.close();
		
		//处理错误的信息
		String errMsg = getErrMsg(p);
		if (errMsg != null) {
			throw new IOException(errMsg);
		}
		
		return tableSql.toString();
	}
	
	/**
	 * 备份数据库
	 * @param config 数据库配置
	 * @param savePath 备份文件保存路径
	 * @return BackupInfo 备份信息
	 * @throws IOException 文件读写错误
	 * @author maoluguang
	 */
	public static BackupInfo backup(MysqlUtils config, String savePath) throws IOException {
		//File f = getMysqlDump();
		if(config.mysqldumpexe == null || !config.mysqldumpexe.exists()){
			throw new FileNotFoundException("在"+config.mysqldumpexe.getPath()+"路径下没有找到名为'mysqldump'的mysql工具,MysqlDumpUtil类的功能依赖此文件!");
		}
		
		//构建远程数据库命令字符串
		StringBuilder cmdStr = new StringBuilder();
		cmdStr.append(" -u").append(config.USERNAME)
			  .append(" -p").append(config.PASSWORD)
			  .append(" -h").append(config.IP)
			  .append(" -P").append(config.PORT)
			  .append("  --ignore-table=").append(config.DATABASENAME)
			  .append(".t_backup_database ")
			  .append(" ").append(config.DATABASENAME);
		
		//创建备份信息对象
		final BackupInfo bki = new BackupInfo();
		Runtime rt = Runtime.getRuntime();
		// 调用mysqldump的cmd命令
		final Process p;
		if(isWindows()){
			p = rt.exec(new String[]{"cmd","/c",config.mysqldumpexe.getPath() + cmdStr.toString()});
		} else {
			p = rt.exec(new String[]{"sh","-c","cd "+ config.mysqldumpexe.getParent() + " | mysqldump "+cmdStr.toString()});
		}
		//备份文件地址
		File dir = new File(savePath);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		final File path = new File(savePath + "/" + new Date().toString().replaceAll(" |:", "") + ".sql");
		log.info("备份文件地址 ：" + path);
//		//创建线程异步执行备份操作
//		new Thread(){
//			@Override
//			public void run(){
				try {
					//得到远程数据库备份的流文件
					InputStream in = p.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
					OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), "utf-8");
					//向指定地址写文件
					for(String str = null;(str = br.readLine()) != null;){
						writer.write(str);
						if(isWindows()) {
							writer.write("\r\n");
						} else {
							writer.write("\n");
						}
						writer.flush();
						//记录当前写入字节数
						bki.setSize(str.getBytes().length + bki.getSize());
					}
					bki.setPath(path.getPath());
					bki.setStatus(true);
					log.info("备份文件完成!");
					
					in.close();
					br.close();
					writer.close();
					
					//处理错误的信息
					String errMsg = getErrMsg(p);
					if(errMsg != null){
						bki.setStatus(false);  
						bki.setErrMsg(errMsg);  
						throw new IOException("备份过程中出现错误：" + errMsg);
					}
					
					log.info("备份方法结束!");
				} catch (IOException e) {
					log.error("IOException :" + e.getMessage());
					bki.setStatus(false);
					bki.setErrMsg(e.getMessage());
				} catch(Exception e) {
					log.error("Exception :" + e.getMessage());
				} finally {
					p.destroy();
				}
//			}
//		}.start();
		
		return bki;
	}
	
	/**
	 * 还原数据库
	 * @param config 数据库配置
	 * @param path 要还原的sql脚本文件路径
	 * @throws IOException 文件读写错误
	 * @author maoluguang
	 */
	public static void loadData(MysqlUtils config, String path) throws IOException {
		//File f = getMysql();
		if(config.mysqlexe == null || !config.mysqlexe.exists()){
			throw new FileNotFoundException("在"+config.mysqldumpexe.getPath()+"路径下没有找到名为'mysqldump'的mysql工具,MysqlDumpUtil类的功能依赖此文件!");
		}
		
		//构建远程数据库命令字符串
		StringBuilder cmdStr = new StringBuilder();
		cmdStr.append(" -u").append(config.USERNAME)
			  .append(" -p").append(config.PASSWORD)
			  .append(" -h").append(config.IP)
			  .append(" -P").append(config.PORT)
			  .append(" ").append(config.DATABASENAME).append("<").append(path);
		
		Runtime rt = Runtime.getRuntime();
		//调用mysqldump的cmd命令
		Process p = null;
		if(isWindows()){
			p = rt.exec(new String[]{"cmd","/c",config.mysqlexe.getPath() + cmdStr.toString()});
		} else {
			p = rt.exec(new String[]{"sh","-c","cd "+ config.mysqlexe.getParent() + " | mysql "+cmdStr.toString()});
		}
		
		//处理错误的信息
		String errMsg = getErrMsg(p);
		if (errMsg != null) {
			throw new IOException(errMsg);
		}
	}
	
	/**
	 * 获取命令行执行的错误信息
	 * @param p Process对象
	 * @return 错误信息字符串
	 * @throws IOException
	 */
	private static String getErrMsg(Process p) throws IOException {
		log.info("获取错误信息方法start!");
		StringBuilder errMsg = new StringBuilder();
		InputStream in = p.getErrorStream();
		log.info("获取错误信息InputStream end!");
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
		log.info("br end!");
		String inStr = null;
//		for(String inStr = null;(inStr = br.readLine()) != null;){log.info(inStr);
//			errMsg.append(inStr).append("<br>");
//		}
		while((inStr=br.readLine())!=null){
			System.out.println(inStr);
			errMsg.append(inStr).append("<br>");
		}
//		for (int i = 0; i < in.available(); i++) {
//            System.out.println("" + in.read());
//            errMsg.append(in.read()).append("<br>");
//         }
		log.info("写入InputStream end!");
		log.info(errMsg.length());log.info(errMsg);
		in.close();
		br.close();
		return errMsg.length() == 0 ? null : errMsg.toString();
	}
	
	/**
	 * 判断是否是Windows系统
	 * @return true代表是Windows系统
	 */
	private static boolean isWindows() {
		return System.getProperty("os.name").indexOf("Windows") != -1;
	}
	
	/**
	 * 封装了数据库备份时的状态信息
	 * @author maoluguang
	 */
	public static class BackupInfo {
		//备份是否完成  
		private boolean status;  
		//备份文件大小  
		private Integer size;
		//出错信息  
		private String errMsg;  
		//备份路径  
		private String path;
		
		public boolean getStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public String getErrMsg() {
			return errMsg;
		}
		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
		public Integer getSize() {
			return size == null ? 0 : size;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	}
	
}
