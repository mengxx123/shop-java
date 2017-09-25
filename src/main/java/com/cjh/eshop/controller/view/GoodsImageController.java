package com.cjh.eshop.controller.view;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cjh.eshop.controller.common.BaseController;
import com.cjh.eshop.model.Goods;
import com.cjh.eshop.model.GoodsImage;
import com.cjh.eshop.service.IGoodsImageService;
import com.cjh.eshop.service.IGoodsService;
import com.cjh.eshop.util.FileUtil;
import com.cjh.eshop.util.JsonResult;
import com.cjh.eshop.util.StateCode;
import com.cjh.eshop.util.TextUtil;

/**
 * 商品图片相关页面控制器
 * @author 陈建杭
 *
 */
@Controller
public class GoodsImageController extends BaseController {
	
	@Resource(name = "goodsImageService")
	private IGoodsImageService goodsImageService;
	
	@Resource(name = "goodsService")
	private IGoodsService goodsService;
	
	@RequestMapping("set_goods_image")
	@ResponseBody
	public Object setGoodsImage(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "goods_id") String goodsId,
			@RequestParam(value = "image_id") String imageId) {
		if (goodsId == null && imageId == null) {
			return new JsonResult<String>(StateCode.ERROR, "参数id不能为空");
		}
		// TODO 安全验证

		try {
			GoodsImage goodsImage = goodsImageService.getById(imageId);
			Goods goods = goodsService.getById(goodsId);
			goods.setModifyTime(new Date());
			goods.setImage(goodsImage.getUrl());
			goodsService.update(goods);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "设置失败");
		}

	}

	// 管理员或用户删除商品图片接口
	@RequestMapping("goods_image_delete")
	@ResponseBody
	public Object deleteGoodsImage(HttpServletRequest request,
			ModelMap modelMap, @RequestParam(value = "id") String id) {
		if (id == null) {
			return new JsonResult<String>(StateCode.ERROR, "参数id不能为空");
		}
		// TODO 安全验证

		try {
			goodsImageService.deleteById(id);
			//logger.info("[管理员{}]删除商品{}", getManagerName(request), id);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}

	}

	// 用户添加商品图片接口
	@RequestMapping(value = "goods_images_add", method = RequestMethod.POST)
	@ResponseBody
	private Object goodsImagesAd(HttpServletRequest request, ModelMap modelMap, 
			@RequestParam("goods_id") String goodsId,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		
		commonInit(request, modelMap); // TODO 删除
		
		if (TextUtil.isEmpty(goodsId)) {
			return new JsonResult<String>(StateCode.ERROR, "商品ID不能为空");
		}
		
		if (file == null) {
		    return new JsonResult<String>(StateCode.ERROR, "上传文件不能为空");
		}
		
		String ext = FileUtil.getExt(file.getOriginalFilename());
		if (ext == null || !"jpg|jpeg|png".contains(ext)) {
		    return new JsonResult<String>(StateCode.ERROR, "仅支持上传jpg或png两种格式的图片");
		}
		
		if (file.getSize() > 3 * 1024 * 1024) {
		    return new JsonResult<String>(StateCode.ERROR, "上传文件大小不能超过3M");
		}
		
		if (!isLogin) {
			return new JsonResult<String>(StateCode.ERROR, "未登录，没有权限");
		}
		
		// TODO 安全验证
		
		Goods goods = goodsService.getById(goodsId);
		if (goods == null) {
			return new JsonResult<String>(StateCode.ERROR, "商品ID不正确");
		}
		
		String path = saveFile(request, file);
		if (path == null) {
		    return new JsonResult<String>(StateCode.ERROR, "服务器出错（保存图片出错）");
		}
		
        GoodsImage goodsImage = new GoodsImage();
        goodsImage.setGoodsId(goodsId);
        goodsImage.setUrl(path);
        
		try {
			goodsImageService.save(goodsImage);
			return new JsonResult<String>(StateCode.SUCCESS, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(StateCode.ERROR, "系统出错");
		}
	}
}