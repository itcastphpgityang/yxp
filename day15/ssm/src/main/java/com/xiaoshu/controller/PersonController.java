package com.xiaoshu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Bank;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonVo;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.PersonService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("person")
public class PersonController {
	@Autowired
	private PersonService ps;

	@Autowired
	private OperationService operationService;

	@RequestMapping("personIndex")
	public String index(HttpServletRequest request, Integer menuid) throws Exception {
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		return "person";
	}

	@RequestMapping(value = "personList", method = RequestMethod.POST)
	public void userList(PersonVo pv, HttpServletRequest request, HttpServletResponse response, String offset,
			String limit) throws Exception {
		try {
			Integer pageSize = StringUtil.isEmpty(limit) ? ConfigUtil.getPageSize() : Integer.parseInt(limit);
			Integer pageNum = (Integer.parseInt(offset) / pageSize) + 1;
			PageInfo<PersonVo> userList = ps.findUserPage(pv, pageNum, pageSize);
			List<Bank> bList = ps.findBank();
			request.getSession().setAttribute("bList", bList);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total", userList.getTotal());
			jsonObj.put("rows", userList.getList());
			WriterUtil.write(response, jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// 新增或修改
	@RequestMapping("reserveUser")
	public void reserveUser(HttpServletRequest request, Person p, HttpServletResponse response) {
		Integer getpId = p.getpId();
		JSONObject result = new JSONObject();
		try {
			if (getpId != null) { // userId不为空 说明是修改
				ps.update(p);
				result.put("success", true);

			} else { // 添加
				ps.add(p);
				result.put("success", true);

			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}

	@RequestMapping("deleteUser")
	public void delUser(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
			String[] ids = request.getParameter("ids").split(",");
			for (String id : ids) {
				ps.deleteUser(Integer.parseInt(id));
			}
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}

	// 导出

	@RequestMapping("exp")
	public void exp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet();

		String[] head = { "人员编号", "人员姓名", "人员性别", "人员爱好", "存款金额", "人员年龄", "存款日期", "存款银行" };

		HSSFRow frow = sheet.createRow(0);
		for (int i = 0; i < head.length; i++) {
			frow.createCell(i).setCellValue(head[i]);
		}
		List<PersonVo> plist = ps.exp();

		for (int i = 0; i < plist.size(); i++) {
			HSSFRow r = sheet.createRow(i + 1);
			PersonVo p = plist.get(i);
			r.createCell(0).setCellValue(p.getpId());
			r.createCell(1).setCellValue(p.getpName());
			r.createCell(2).setCellValue(p.getpSex());
			r.createCell(3).setCellValue(p.getpLike());
			r.createCell(4).setCellValue(p.getpCount());
			r.createCell(5).setCellValue(p.getpAge());
			r.createCell(6).setCellValue(TimeUtil.formatTime(p.getCreatetime(), "yyyy-MM-dd"));
			r.createCell(7).setCellValue(p.getBname());
		}
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("存款信息.xls", "UTF-8"));
		response.setHeader("Connection", "close");
		response.setHeader("Content-Type", "application/octet-stream");
		wb.write(response.getOutputStream());
		wb.close();
	}

	// 新增银行
	@RequestMapping("addBank")
	public void addBank(HttpServletRequest request, Bank b, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
			
				ps.addBank(b);
				result.put("success", true);

			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping("getTj")
	public void getTj(HttpServletRequest request, Bank b, HttpServletResponse response){
		List<PersonVo> p = ps.getTj();
		Object json = JSONObject.toJSON(p);
		
		WriterUtil.write(response, json.toString());
	}
}
