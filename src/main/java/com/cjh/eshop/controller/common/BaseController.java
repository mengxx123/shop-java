package com.cjh.eshop.controller.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cjh.eshop.common.Constant;
import com.cjh.eshop.common.SettingHelper;
import com.cjh.eshop.model.User;
import com.cjh.eshop.service.IUserService;
import com.cjh.eshop.util.FileUtil;
import com.cjh.eshop.util.IdGenerator;

public class BaseController {
	
	protected final int DEFAULT_PAGE_SIZE = 5;
	protected boolean isLogin = false;
	protected String loginUserId;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	protected void commonInit(HttpServletRequest request, ModelMap modelMap) {
		
		// TODO 模拟登陆
//		request.getSession().setAttribute(Constant.SESSION_UID, "1");
		// 设置网站数据
		modelMap.put("websiteName", SettingHelper.getInstance().getSetting().get(Constant.SETTING_WEBSITE_NAME));
		
		if (request.getSession().getAttribute(Constant.SESSION_UID) == null) {
			modelMap.put("isLogin", false);
			isLogin = false;
		} else {
			loginUserId = (String) request.getSession().getAttribute(Constant.SESSION_UID);
			User user = userService.getById(loginUserId);
			modelMap.put("isLogin", true);
			modelMap.put("loginUser", user);
			modelMap.put("userId", loginUserId);  
			modelMap.put("userName", user.getName());  
			isLogin = true;
		}
	}
	
	
	/**
	 * 
	 * @param pageNo 当前页数
	 * @param pageSize 每页数目
	 * @param totalSize 总数目
	 * @param modelMap
	 */
	protected void initPage(int pageNo, int pageSize, long totalSize, ModelMap modelMap) {
		long totalPage = (totalSize - 1) / pageSize + 1;
		modelMap.put("pageSize", pageSize);
		modelMap.put("totalSize", totalSize);
		modelMap.put("totalPage", totalPage);
		modelMap.put("currentPage", pageNo);
		long startPage = pageNo - 3;
		if (startPage < 1) {
			startPage = 1;
		}
		long endPage = pageNo + 3;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
	}
	
	protected String getManagerName(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(Constant.SESSION_MANAGER_ID);
	}
	
	protected String dealFile1(HttpServletRequest request, ModelMap modelMap, String path2) {
		
		try {
			CommonsMultipartResolver multipartResolver = 
	    		    new CommonsMultipartResolver(request.getSession().getServletContext()); 
	    		  //先判断request中是否包涵multipart类型的数据，
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 获得第1张图片（根据前台的name名称得到上传的文件）
				//MultipartFile imgFile1 = multipartRequest.getFile("imgFile");
				Iterator<String> iter = multipartRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multipartRequest.getFile((String) iter.next());
					if (file != null) {
						
						String fileName = file.getOriginalFilename(); // 上传文件的文件名
						System.out.println("文件名：" + fileName);
						
						// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
				        String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
				        // 对扩展名进行小写转换  
				        ext = ext.toLowerCase();  
				        System.out.println("格式是：" + ext); 
				        
				        // 定义一个数组，用于保存可上传的文件类型
						List<String> fileTypes = new ArrayList<String>();
						fileTypes.add("jpg");
						fileTypes.add("png");
						fileTypes.add("jpeg");
						fileTypes.add("gif");
				        if (!fileTypes.contains(ext)) {
				        	modelMap.put("result", "图片格式有误（仅支持jpg/jpeg、png和gif格式）");
				        	return null;
				        }
				        	
				        fileName = new Date().getTime() + "." + ext;  	
						//fileName = "123.txt";
						String path = request.getSession().getServletContext().getRealPath(path2);// TODO  
						System.out.println("path：" + path);
						File targetFile = new File(path, fileName);  
				        if(!targetFile.exists()){  
				            targetFile.mkdirs();  
				        }  
				        
						// 写文件到本地
						file.transferTo(targetFile);
						
						return fileName; // 这样的话只能传一个文件了
					}
				}
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getLoginUserId(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(Constant.SESSION_UID);
	}
	
	// 保存文件到本地，返回相对于网站根目录的文件路径
	public String saveFile(HttpServletRequest request, MultipartFile file) {
	    // TODO 商品图片重构
	    String path = request.getSession().getServletContext().getRealPath(Constant.PATH_GOODS_IMAGE); 
	    String ext = FileUtil.getExt(file.getOriginalFilename());
	    
	    StringBuilder builder = new StringBuilder();
	    //builder.append(File.separator);
        Calendar calendar = Calendar.getInstance();
        builder.append(calendar.get(Calendar.YEAR));
        builder.append(File.separator);
        builder.append(calendar.get(Calendar.MONTH) + 1);
        builder.append(File.separator);
        builder.append(calendar.get(Calendar.DATE));
        
        
        String fileName = IdGenerator.getId() + "." + ext;
        File targetFile = new File(path + File.separator + builder.toString(), fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        System.out.println("文件路径" + targetFile.getPath());
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;
        }  
        
        return Constant.PATH_GOODS_IMAGE + File.separator + builder.toString() + File.separator + fileName;
	}
	
	protected boolean isLogined(HttpServletRequest request) {
        return request.getSession().getAttribute(Constant.SESSION_UID) != null;
    }
}
