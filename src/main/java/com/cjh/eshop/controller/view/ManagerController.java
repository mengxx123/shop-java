package com.cjh.eshop.controller.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Manager;
import com.cjh.eshop.service.IManagerService;
import com.cjh.eshop.util.CookieUtil;
import com.cjh.eshop.util.ExportExcel;
import com.cjh.eshop.util.IpUtil;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.PasswordUtil;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;
import com.cjh.eshop.util.excel.DataAdapter;
import com.cjh.eshop.util.excel.ExcelExportor;

/**
 * 管理员相关页面控制器
 * 
 * @author 陈建杭
 * 
 */
@Controller
public class ManagerController extends BaseController {

	private static Logger logger = LoggerFactory
			.getLogger(ManagerController.class);

	@Resource(name = "managerService")
	private IManagerService managerService;

	private static final String VIEW_ADMIN_MANAGER = "manager/admin_manager";
	private static final String VIEW_ADMIN_MANAGER_EDIT = "manager/manager_edit";
	private static final String VIEW_ADMIN_MANAGER_IMPORT = "manager/manager_import";
	private static final String VIEW_ADMIN_LOGIN = "admin/login";

	// 管理员列表页面
	@RequestMapping("admin/managers")
	public String manager(HttpSession session, HttpServletRequest request,
			ModelMap modelMap,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "keyword", required = false) String keyword) {

		modelMap.put("keyword", keyword);
		if (page == null) {
			page = 1;
		}

		PageInfo<Manager> pageInfo = managerService.getAllByPage(page, DEFAULT_PAGE_SIZE);
		modelMap.put("page", pageInfo);
		
		return VIEW_ADMIN_MANAGER;
	}

	// 管理员详情页面
	@RequestMapping("admin/manager/{id}")
	public String getManagerById(@PathVariable("id") String id,
			HttpServletRequest request, ModelMap modelMap) {
		
		Manager manager = managerService.getById(id);
		modelMap.put("manager", manager);
		return VIEW_ADMIN_MANAGER_EDIT;
	}

	// 管理员添加页面
	@RequestMapping("admin/manager_edit")
	public String brandEdit(HttpServletRequest request, ModelMap modelMap) {
		return VIEW_ADMIN_MANAGER_EDIT;
	}

	// 管理员添加/编辑页面
	@RequestMapping(value = "managers", method = RequestMethod.POST)
	public String saveOrUpdateBrand(HttpServletRequest request,
			Manager manager, ModelMap modelMap) {
		if (TextUtil.isEmpty(manager.getName())) {
			modelMap.put("result", "管理员姓名不能为空");
			return VIEW_ADMIN_MANAGER_EDIT;
		}
		if (TextUtil.isEmpty(manager.getAccount())) {
			modelMap.put("result", "管理员账号不能为空");
			return VIEW_ADMIN_MANAGER_EDIT;
		}
		if (TextUtil.isEmpty(manager.getPassword())) {
			modelMap.put("result", "管理员密码不能为空");
			return VIEW_ADMIN_MANAGER_EDIT;
		}

		if (manager.getId() == null) {
			manager.setAddTime(new Date());
			manager.setPassword(PasswordUtil.encode(manager.getPassword()));
			
			try {
				managerService.save(manager);
				
				modelMap.put("manager", manager);
				modelMap.put("result", "添加成功");
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("manager", manager);
				modelMap.put("result", "添加失败，系统出错");
			}
			
			
			return VIEW_ADMIN_MANAGER_EDIT;
		} else {
			Manager oldManager = managerService.getById(manager.getId());
			oldManager.setAccount(manager.getAccount());
			oldManager.setName(manager.getName());
			oldManager.setPassword(manager.getPassword());
			oldManager.setAddTime(new Date());
			oldManager.setNote(manager.getNote());

			try {
				managerService.update(oldManager);
			} catch (Exception e) {
				modelMap.put("result", "保存失败，可能账号已存在"); // TODO
				return VIEW_ADMIN_MANAGER_EDIT;
			}
			modelMap.put("manager", oldManager);
			modelMap.put("result", "保存成功");
			return VIEW_ADMIN_MANAGER_EDIT;
		}
	}

	// 后台删除管理员接口
	@RequestMapping("manager_delete")
	@ResponseBody
	public Object delete(HttpServletRequest request,
			@RequestParam(value = "id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "删除失败，参数id不能为空");
		}
		// TODO 安全验证
		try {
			managerService.deleteById(id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "删除失败");
		}

	}

	// 后台登陆页面
	@RequestMapping("loginin")
	public String lo(HttpSession session, HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			
		    String account = CookieUtil.getValue(cookies, Constant.COOKIE_ADMIN_ACCOUNT);
			System.out.println("账号："+account);
			
			String password = CookieUtil.getValue(cookies, Constant.COOKIE_ADMIN_PASSWORD);
			System.out.println("密码："+password);
			if (account != null && password != null) {
				if (managerService.login(account, password)) {
					
					Manager manager = managerService.getByAccount(account);
					
					// 更新管理员最近登录时间 // TODO 补充登录IP
					Manager exampleManager = new Manager();
					exampleManager.setId(manager.getId());
					exampleManager.setLatestLoginTime(new Date());
					managerService.updateBySelective(exampleManager);
					
					session.setAttribute(Constant.SESSION_MANAGER_ID, manager.getId());
					
					// 保存账号密码到cookie，方便自动登陆
					CookieUtil.add(response, Constant.COOKIE_ADMIN_ACCOUNT, account);
					CookieUtil.add(response, Constant.COOKIE_ADMIN_PASSWORD, password);
					//response.addCookie(new Cookie(Constant.COOKIE_ADMIN_ACCOUNT, account));
					//response.addCookie(new Cookie(Constant.COOKIE_ADMIN_PASSWORD, password));
					
					logger.info("[管理员{}]管理员{}登陆成功，IP地址：{}", 
							new Object[] {manager.getId(), account,  IpUtil.getIpAddr(request)});
					
					return "redirect:admin"; // TODO
				} else {
					System.out.println("cookie登陆失败");
				}
			}
			
		}
		
		return VIEW_ADMIN_LOGIN;
	}

	// 后台登陆接口
	@RequestMapping("adlogin")
	@ResponseBody
	public Object login(HttpSession session, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "password", required = false) String md5Password) {
		
		if (TextUtil.isEmpty(account)) {
			return new JsonResult<String>(StateCode.ERROR, "账号不能为空");
		}
		if (TextUtil.isEmpty(md5Password)) {
			return new JsonResult<String>(StateCode.ERROR, "密码不能为空");
		}

		if (managerService.login(account, md5Password)) {
			Manager manager = managerService.getByAccount(account);
			session.setAttribute(Constant.SESSION_MANAGER_ID, manager.getId());
			
			// TODO 代码和上面的重复了，需要重构
			Manager exampleManager = new Manager();
			exampleManager.setId(manager.getId());
			exampleManager.setLatestLoginTime(new Date());
			managerService.updateBySelective(exampleManager);
			
			// 把账号密码写入cookie
			CookieUtil.add(response, Constant.COOKIE_ADMIN_ACCOUNT, account, 7 * 24 * 60 * 60);
			CookieUtil.add(response, Constant.COOKIE_ADMIN_PASSWORD, md5Password);
			//Cookie accountCookie = new Cookie(Constant.COOKIE_ADMIN_ACCOUNT, account);
			//accountCookie.setMaxAge(7 * 24 * 60 * 60); // cookie保存一个星期
			//response.addCookie(accountCookie);
			//response.addCookie(new Cookie(Constant.COOKIE_ADMIN_PASSWORD, password));
			
			logger.info("[管理员{}]管理员{}登陆成功，IP地址：{}", 
					new Object[] {manager.getId(), account,  IpUtil.getIpAddr(request)});
			
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} else {
			logger.info("管理员{}登陆失败，加密密码{}", account, md5Password);
			
			return new JsonResult<String>(StateCode.ERROR, "账号不存在或密码错误");
		}
	}

	// 后台退出
	@RequestMapping(value = "adloginout", method = RequestMethod.GET)
	public String loginOut(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		String id = (String) session.getAttribute(Constant.SESSION_MANAGER_ID);
		
		CookieUtil.clear(response, Constant.COOKIE_ADMIN_ACCOUNT);
		CookieUtil.clear(response, Constant.COOKIE_ADMIN_PASSWORD);
		
		response.addCookie(new Cookie(Constant.COOKIE_ADMIN_ACCOUNT, null));
		response.addCookie(new Cookie(Constant.COOKIE_ADMIN_PASSWORD, null));
		
		logger.info("[管理员{}]退出登陆", id);
		
		session.setAttribute(Constant.SESSION_MANAGER_ID, null);
		
		return VIEW_ADMIN_LOGIN;
	}

	/** 导出所有管理员到excel文件 */
	@RequestMapping("admin/manager_export")
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("state", null);
		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		
		OutputStream fOut = null;
		
		
		try {
			String codedFileName = null;
			// 进行转码，使其支持中文文件名
						codedFileName = java.net.URLEncoder.encode("管理员名单", "UTF-8");
			response.setHeader("content-disposition", "attachment;filename="
					+ codedFileName + ".xls");
			
						
			fOut = response.getOutputStream();
			List<Manager> manager = managerService.getAllByPage(1, 1000).getResult(); // 不可能超过1000
			ExportExcel<Manager> exportExcel = new ExportExcel<Manager>();
			
			//exportExcel.exportExcel(title, manager, fOut);
			
			ExcelExportor exportor = new ExcelExportor(new ManagerAdapter(manager));
			exportor.export(fOut); // try
			
			
			
		} catch (UnsupportedEncodingException e1) {
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
			}
			//session.setAttribute("state", "open");
		}
		System.out.println("文件生成...");
	}

	@RequestMapping("check")
	public void check2(HttpServletRequest request, HttpServletResponse response) {
		try {
			if ("open".equals(request.getSession().getAttribute("state"))) {
				request.getSession().setAttribute("state", null);
				response.getWriter().write("true");
				response.getWriter().flush();
			} else {
				response.getWriter().write("false");
				response.getWriter().flush();
			}
		} catch (IOException e) {
		}
	}
	
	@RequestMapping("admin/manager_import")
	public String managerImport() {
		return VIEW_ADMIN_MANAGER_IMPORT;
	}
	
	@RequestMapping(value = "importBrandSort", method = RequestMethod.POST)
	public String importBrandSort(
			@RequestParam("filename") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
			throws Exception {
		String temp = request.getSession().getServletContext()
				.getRealPath(File.separator)
				+ "temp"; // 临时目录
		File tempFile = new File(temp);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(10 * 1024 * 1024); // 设置允许用户上传文件大小,单位:位
		fu.setSizeThreshold(4096); // 设置最多只允许在内存中存储的数据,单位:位
		fu.setRepositoryPath(temp); // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
		
		if (file == null)
			return null;
		logger.info(file.getOriginalFilename());

		String name = file.getOriginalFilename();// 获取上传文件名,包括路径
		// name = name.substring(name.lastIndexOf("\\") + 1);// 从全路径中提取文件名
		long size = file.getSize();
		if ((name == null || name.equals("")) && size == 0)
			return null;
		InputStream in = file.getInputStream();
		readBrandPeriodSorXls(modelMap, in);

		String strAlertMsg = "导入成功";
		
		request.getSession().setAttribute("msg", strAlertMsg);
		return VIEW_ADMIN_MANAGER_IMPORT;
	}

	private void readBrandPeriodSorXls(ModelMap modelMap, InputStream is) throws IOException,
			ParseException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			
			List<Manager> managers = new ArrayList<Manager>();
			
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				
				HSSFCell brandIdHSSFCell = hssfRow.getCell(0);
				String account = brandIdHSSFCell.getStringCellValue();
				
				HSSFCell nameCell = hssfRow.getCell(1);
				String name = nameCell.getStringCellValue();
				
				HSSFCell noteCell = hssfRow.getCell(2);
				String note = noteCell.getStringCellValue();
				
				final String DEFAULT_PWD = "123456";
				
				Manager manager = new Manager();
				manager.setAccount(account);
				manager.setName(name);
				manager.setNote(note);
				manager.setAddTime(new Date());
				manager.setPassword(DEFAULT_PWD);
				managers.add(manager);
			}
			
			try {
				managerService.save(managers);
				modelMap.put("result", "导入成功");
			} /*catch (MySQLIntegrityConstraintViolationException e) {
				request.getSession().setAttribute("result", "导入失败");
			} */catch (Exception e) {
				modelMap.put("result", "导入失败，请检查账号是否已存在");
			}
		}

	}

	/**
	 * 得到Excel表中的值
	 * 
	 * @param hssfCell
	 *            Excel中的每一个格子
	 * @return Excel中每一个格子中的值
	 */

	private static String getCellValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}

class ManagerAdapter extends DataAdapter {

	List<Manager> manager;
	public ManagerAdapter(List<Manager> manager) {
		this.manager = manager;
	}
	
	String title[] = {"账号", "姓名", "备注", "其他"};
	@Override
	public String getTitleString(int position) {
		return title[position];
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return manager.get(row).getAccount();
		case 1:
			return manager.get(row).getName();
		case 2:
			return manager.get(row).getNote();
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return manager.size();
	}
	
}