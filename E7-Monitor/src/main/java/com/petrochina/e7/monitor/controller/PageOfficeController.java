package com.petrochina.e7.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.petrochina.e7.monitor.service.IDataService;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator
 */
/**
 * 在线编辑
 */
@RestController
@RequestMapping(value = "/project/pageOffice")
@Api(value = "在线编辑接口")
public class PageOfficeController {

	@Autowired
	private IDataService dataService;
/*	@Value("${file.save.path}")
	private String poSysPath;*/


/*	@RequestMapping("/hello")
	public String test() {
		System.out.println("hello run");
		return "Hello";
	}*/

	@RequestMapping(value="/index", method= RequestMethod.GET)
	public ModelAndView showIndex(){
		ModelAndView mv = new ModelAndView("Index");
		return mv;
	}

	String dataJson = null;
	@GetMapping(value="/word")
	@ApiOperation(value = "跳转在线编辑页面", notes = "跳转在线编辑页面根据id回显监测数据信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "监测数据ID", required = true, dataType = "String")
	public ModelAndView showWord(HttpServletRequest request, HttpServletResponse response,Map<String,Object> map,@RequestParam(value = "id",required = false) String id){
		System.out.println(id);
		response.setHeader("Content-Transfer-Encoding","binary");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");

		PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
		WordDocument doc = new WordDocument();
		poCtrl.setCaption("监测报告在线编辑");
		poCtrl.setServerPage("/monitor/poserver.zz");//设置服务页面
		poCtrl.addCustomToolButton("保存","Save",1);//添加自定义保存按钮
		poCtrl.addCustomToolButton("盖章","AddSeal",2);//添加自定义盖章按钮
		poCtrl.setSaveFilePage("/monitor/project/pageOffice/save");//设置处理文件保存的请求方法
		poCtrl.setTagId("PageOfficeCtrl1");//此行必需
		//*********************************
		//根据ID获取报告信息
		dataJson = dataService.findById(id);
        JSONObject jsonObjects = JSON.parseObject(dataJson);
        JSONObject objArray = (JSONObject) jsonObjects.get("data");

        JSONObject dataParam = (JSONObject) objArray.get("dataParam");

        JSONObject dataIndex = (JSONObject) objArray.get("dataIndex");

        Map mapobj = (Map) JSON.parse(String.valueOf(objArray));
        //第一种方式
		Map mapParam = (Map)JSON.parse(String.valueOf(dataParam));

		Map mapIndex = (Map)JSON.parse(String.valueOf(dataIndex));
		System.out.println(mapIndex);
			int i =-1;
			int y ;
			int i1=55;
			int y2;
			Iterator<String> keySetIterator = mapParam.keySet().iterator();
			Iterator<String> keySetIterators = mapIndex.keySet().iterator();
			while (keySetIterator.hasNext()) {
				i+=2;
				y=i+1;
				String key = keySetIterator.next();
				String value = (String) mapParam.get(key);
				DataRegion dataRegion1 = doc.openDataRegion("PO_NO"+i);
				dataRegion1.setValue((String) key);
				DataRegion dataRegion2 = doc.openDataRegion("PO_NO"+y);
				dataRegion2.setValue(value);
			}
			while (keySetIterators.hasNext()) {
				i1+=2;
				y2=i1+1;
				String key1 = keySetIterators.next();
				String value1 = (String) mapIndex.get(key1);
				DataRegion dataRegion3 = doc.openDataRegion("PO_NO"+i1);
				dataRegion3.setValue((String) key1);
				DataRegion dataRegion4 = doc.openDataRegion("PO_NO"+y2);
				dataRegion4.setValue(value1);
		}
		poCtrl.setWriter(doc);
		//打开word
		poCtrl.webOpen("D:\\红湾输油站1#加热炉测试报告.doc",OpenModeType.docAdmin,"张森");
		map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
		ModelAndView mv = new ModelAndView("Word");
		return mv;
	}



	@PostMapping(value = "/save")
	@ApiOperation(value = "保存报告", notes = "保存监测报告")
	public void saveFile(HttpServletRequest request, HttpServletResponse response){
		System.out.println("dataJson::>>>"+dataJson);
		System.out.println("++++++保存操作++++++++++");
		JSONObject jsonObjects = JSON.parseObject(dataJson);
		JSONObject objArray = (JSONObject) jsonObjects.get("data");
		Object createUserId = objArray.get("createUserId");
		System.out.println("createUserId:>>>"+createUserId);
		JSONObject dataParam = (JSONObject) objArray.get("dataParam");
		JSONObject dataIndex = (JSONObject) objArray.get("dataIndex");
		FileSaver fs = new FileSaver(request, response);
		fs.saveToFile("D:\\monitoreport\\" + fs.getFileName());
		fs.close();
	}

	
	/**
	 * 添加PageOffice的服务器端授权程序Servlet（必须）
	 * @return
	 */
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
		com.zhuozhengsoft.pageoffice.poserver.Server poserver = new com.zhuozhengsoft.pageoffice.poserver.Server();
		poserver.setSysPath("D:\\lic\\");//设置PageOffice注册成功后,license.lic文件存放的目录
		ServletRegistrationBean srb = new ServletRegistrationBean(poserver);
		srb.addUrlMappings("/poserver.zz");
		srb.addUrlMappings("/posetup.exe");
		srb.addUrlMappings("/pageoffice.js");
		srb.addUrlMappings("/jquery.min.js");
		srb.addUrlMappings("/pobstyle.css");
		srb.addUrlMappings("/sealsetup.exe");
        return srb;// 
    }


}
