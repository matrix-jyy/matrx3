package com.zkingsoft.actions.admin.sys;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkingsoft.anotations.RemoveRequestToken;
import com.zkingsoft.anotations.SaveRequestToken;
import com.zkingsoft.authority.Authority;
import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.model.sys.SysDbBackup;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.pojo.PaginationVO;
import com.zkingsoft.services.sys.SysDbBackupService;
import com.zkingsoft.util.MysqlUtils;
import com.zkingsoft.util.MysqlUtils.BackupInfo;
import com.zkingsoft.util.WebUtil;

/**
 * This field was generated by Zking.software.Codegen.
 * 
 * @date 2016-12-01 17:45
 */
@Controller
@RequestMapping(value = "admin/sysDbBackup")
public class SysDbBackupController extends BaseController {
	Logger log=Logger.getLogger(SysDbBackupController.class);
	@Resource(name="sysDbBackupService")
	private SysDbBackupService currentService;

	@Resource(name = "authorityAdapter")
	private Authority authorityAdapter;

	// 记录编辑前的值Before_Edit_Value
	public static final String BEV = "SysDbBackup_BEV";
	public static final String fnCode = "backup";
	public static final String search = fnCode + ":search";
	public static final String edit = fnCode + ":edit";
	public static final String del = fnCode + ":del";
	public static final String add = fnCode + ":add";
	public static final String database_backup = fnCode + ":database_backup";
	public static final String database_restore = fnCode + ":database_restore";

	/**
	 * 列表显示
	 */
	@RequestMapping(value = "/showList")
	public @ResponseBody AjaxResult showList(SysDbBackup sysDbBackup, PaginationVO pageVo) {
		authorityAdapter.isBtnPermitted(search);
		return showList(currentService, sysDbBackup, pageVo);
	}

	/**
	 * 新增或者修改页面
	 */
	@RemoveRequestToken
	@RequestMapping(value = "/addOrModify")
	public @ResponseBody AjaxResult addOrModify(SysDbBackup sysDbBackup) {
		if (sysDbBackup.getBuId() != null) {
			authorityAdapter.isBtnPermitted(edit);
			sysDbBackup.setBuId(((SysDbBackup) (WebUtil.getSessionAttribute(BEV))).getBuId());
			AjaxResult result = modify(currentService, WebUtil.getSessionAttribute(BEV), sysDbBackup, "sysDbBackup");
			WebUtil.removeSessionAttribute(BEV);
			return result;
		} else {
			authorityAdapter.isBtnPermitted(add);
			return add(currentService, sysDbBackup, "sysDbBackup");
		}
	}

	/**
	 * 进入修改界面
	 */
	@SaveRequestToken
	@RequestMapping(value = "/editForm")
	public String editForm(Long id) {
		authorityAdapter.isBtnPermitted(edit);
		SysDbBackup sysDbBackup;
		if (id != null) {
			sysDbBackup = currentService.findById(id);
			WebUtil.getRequest().setAttribute("obj", sysDbBackup);
			WebUtil.setSessionAttribute(BEV, sysDbBackup);
		}
		return "admin/sysDbBackup-form";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/del")
	public @ResponseBody AjaxResult del(String keys) {
		authorityAdapter.isBtnPermitted(del);
		return remove(currentService, keys);
	}

	/**
	 * 备份
	 */
	@RequestMapping(value = "/database_backup")
	public @ResponseBody AjaxResult database_backup(String keys) {
		authorityAdapter.isBtnPermitted(database_backup);

		ResourceBundle res = ResourceBundle.getBundle("config/db");
		final String USERNAME = res.getString("db.username"); // 数据库用户名
		final String PASSWORD = res.getString("db.password"); // 数据库用户密码
		final String IP = res.getString("db.host"); // 数据库ip地址
		final String PORT = res.getString("db.port"); // 数据库开放端口
		final String DATABASENAME = res.getString("db.database"); // 数据库名称
		final String mysqlpath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
				.replaceAll("%20", " ").trim() + "/../lib/"; // mysqldump工具所在目录的路径，通常为mysql安装路径的bin文件夹

		ResourceBundle sysRes = ResourceBundle.getBundle("config/system");
		final String savepath = sysRes.getString("db_backup_path");

		// 创建线程异步执行备份操作
		new Thread() {
			@Override
			public void run() {
				SysDbBackup sysDbBackup = new SysDbBackup();
				MysqlUtils config = new MysqlUtils(USERNAME, PASSWORD, IP, PORT, DATABASENAME, mysqlpath);
				BackupInfo info = new BackupInfo();
				try {
					info = MysqlUtils.backup(config, savepath);
				} catch (IOException e) {
					log.error("SysDbBackupController ", e);
				}
				sysDbBackup.setBuDbIp(IP);
				sysDbBackup.setBuDbPort(PORT);
				sysDbBackup.setBuDbName(DATABASENAME);
				sysDbBackup.setBuDbUname(USERNAME);
				sysDbBackup.setBuDbPwd(PASSWORD);
				sysDbBackup.setBuStatus(info.getStatus() ? "1" : "0");
				sysDbBackup.setBuErrMsg(info.getErrMsg());
				sysDbBackup.setBuPath(info.getPath());
				sysDbBackup.setBuSize(info.getSize());

				currentService.add(sysDbBackup);
			}
		}.start();

		return new AjaxResult(AjaxResult.STATUS_OK, null, "数据库备份中，请稍后查看...");
	}
}
