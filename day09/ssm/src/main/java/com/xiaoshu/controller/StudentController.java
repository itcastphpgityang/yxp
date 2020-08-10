package com.xiaoshu.controller;

import java.io.IOException;
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
import com.xiaoshu.entity.Course;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.StudentService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	StudentService ss;

	@Autowired
	private OperationService operationService;

	@RequestMapping("studentIndex")
	public String index(HttpServletRequest request, Integer menuid) throws Exception {
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		return "student";
	}

	@RequestMapping(value = "studentList", method = RequestMethod.POST)
	public void userList(StudentVo sv, HttpServletRequest request, HttpServletResponse response, String offset,
			String limit) throws Exception {
		try {
			Integer pageSize = StringUtil.isEmpty(limit) ? ConfigUtil.getPageSize() : Integer.parseInt(limit);
			Integer pageNum = (Integer.parseInt(offset) / pageSize) + 1;
			PageInfo<StudentVo> userList = ss.findUserPage(sv, pageNum, pageSize);
			List<Course> cList = ss.findCourse();
			List<Student> sList = ss.findGrade();
			request.getSession().setAttribute("cList", cList);
			request.getSession().setAttribute("sList", sList);
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
	public void reserveUser(HttpServletRequest request, Student student, HttpServletResponse response) {
		Integer id = student.getId();
		JSONObject result = new JSONObject();
		try {
			if (id != null) { // userId不为空 说明是修改
				ss.update(student);
				result.put("success", true);

			} else { // 添加
				ss.add(student);
				result.put("success", true);

			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	// 新增或修改
		@RequestMapping("addCourse")
		public void addCourse(HttpServletRequest request,Course course, HttpServletResponse response) {
			JSONObject result = new JSONObject();
			try {
				 // 添加
					ss.addCourse(course);
					result.put("success", true);

				
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", true);
				result.put("errorMsg", "对不起，操作失败");
			}
			WriterUtil.write(response, result.toString());
		}
		
		@RequestMapping("exp")
		public void exp(HttpServletRequest request,HttpServletResponse response) throws Exception{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			
			String[] head={"学生id","学生编号","学生姓名","学生年龄","所选课程","所属年级","入校时间"};
			
			HSSFRow frow = sheet.createRow(0);
			for (int i = 0; i < head.length; i++) {
				frow.createCell(i).setCellValue(head[i]);
			}
			List<StudentVo> s = ss.exp();
			for (int i = 0; i < s.size(); i++) {
				StudentVo s2=s.get(i);
				HSSFRow r = sheet.createRow(i+1);
				r.createCell(0).setCellValue(s2.getId());
				r.createCell(1).setCellValue(s2.getCode());
				r.createCell(2).setCellValue(s2.getName());
				r.createCell(3).setCellValue(s2.getAge());
				r.createCell(4).setCellValue(s2.getCname());
				r.createCell(5).setCellValue(s2.getGrade());
				r.createCell(6).setCellValue(TimeUtil.formatTime(s2.getEntrytime(), "yyyy-MM-dd HH:mm:ss"));
			}
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("学生列表.xls", "UTF-8"));
			response.setHeader("Connection", "close");
			response.setHeader("Content-Type", "application/octet-stream");
	        wb.write(response.getOutputStream());
			wb.close();
		}
		@RequestMapping("getTj")
		public void getTj(HttpServletRequest request,HttpServletResponse response){
			List<StudentVo> tj=ss.getTj();
			System.out.println(tj);
			Object json = JSONObject.toJSON(tj);
			
			WriterUtil.write(response, json.toString());
		}
}
