package com.doak.springdatajpademo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.doak.springdatajpademo.dao.EmpDao;
import com.doak.springdatajpademo.entity.Emp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinallyTest {
	
	@Autowired
	EmpDao empDao;
	
	/**
	 * 1.查询工资大于1200的员工姓名和工资
	 */
	@Test
	public void test_findBySalGreaterThan(){
		List<Emp> list = empDao.findBySalGreaterThan(new BigDecimal("1200"));
		for(Emp  emp : list)
		System.out.println("*******name==" + emp.getEname() + ",,,sal===" + emp.getSal());
	}
	
	/**
	 * 2/查询员工号为7698的员工的姓名和部门号
	 */
	@Test
	public void test_findByEmpno(){
		Emp  emp =  empDao.findByEmpno(7698);
		 System.out.println("*******name==" + emp.getEname() + ",,,deptno===" + emp.getDeptno());
	}
	
	/**
	 * 3.选择工资不在500到1200的员工的姓名和工资
	 */
	@Test
	public void testfindBySalLessThanAndSalGreaterThan(){
		System.out.println(empDao.findBySalLessThanOrSalGreaterThan(new BigDecimal("500"), new BigDecimal("1000")));
	}

	/**
	 * 4.选择雇用时间在1981-02-01到1987-05-01之间的员工姓名，job_id和雇用时间
	 * @throws ParseException
	 */
	@Test
	public void test_findByHiredateBetween() throws ParseException{
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date start = dateFormat1.parse("1981-02-01");
		Date end = dateFormat1.parse("1987-05-01");
		List<Emp> list = empDao.findByHiredateBetween(start, end);
		for(Emp  emp : list)
		System.out.println("*******name==" + emp.getEname() + ",,,job_id===" + emp.getJob() +  ",,,Hiredate===" + emp.getHiredate());
	}
	
	/**
	 * 5.选择在20或30号部门工作的员工姓名和部门号
	 */
	@Test
	public void test_findByDeptnoOrDeptno(){
		 List<Emp> list = empDao.findByDeptnoOrDeptno(20,30);
		 for(Emp  emp : list)
			 System.out.println("*******name==" + emp.getEname() + ",,,deptno===" + emp.getDeptno());
			
	}
	
	/**
	 * 6.选择在1981年雇用的员工的姓名和雇用时间
	 * @throws ParseException
	 */
	@Test
	public  void test_findByHiredate() throws ParseException{
		/*DateFormat dateFormat1 = new SimpleDateFormat("yyyy");
		Date date = dateFormat1.parse("1981");*/
		 List<Object[]> list =  empDao.findByHiredate("1981");
		 for(Object[] object : list)
		System.out.println("*******name==" + object[0]  +  ",,,Hiredate===" + object[1] +",,,*******");
			
	}
	
	/**
	 * 7. 选择公司中没有管理者的员工姓名及job
	 */
	@Test
	public void test_findByMgrIsNull(){
		List<Object[]> list =   empDao.findByMgrIsNull();
		for(Object[] object : list)
		System.out.println("*******name==" + object[0]  +  ",,,job_id===" + object[1] +",,,*******");
			
	}
	
	/**
	 * 8. 选择公司中有奖金的员工姓名，工资和奖金级别
	 */
	@Test
	public void test_findByCommIsNotNull(){
		List<Object[]> list =  empDao.findByCommIsNotNull();
		for(Object[] object : list)
		System.out.println("*******name==" + object[0]  +  ",,,sal===" + object[1] + ",,,comm===" + object[2] +",,,*******");
			
	}
	
	/**
	 * 9. 选择员工姓名的第三个字母是a的员工姓名
	 */
	@Test
	public void test_findByEnameContains(){
		try {
			List<Object[]> list =  empDao.findByEnameContains("a");	
			for(Object[] object : list)
			System.out.println("*******name==" + object[0]   +",,,*******");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 11. 查询员工号，姓名，工资，以及工资提高百分之20%后的结果（new salary）
	 */
	@Test
	public void test_findBySal(){
		 List<Object[]> list = empDao.findBySal();
		 for(Object[] object : list)
		System.out.println("*******empno==" + object[0]  +  ",,,ename===" + object[1]  +  ",,,sal===" + object[2] +  ",,,RaiseSal===" + object[3]   +",,,*******");
	}
	
	/**
	 * 12. 将员工的姓名按首字母排序，并使用分页查询查出前5个记录。
	 */
	@Test
	public void test_findByEnameOrderbyEname(){
		Pageable pageable = new PageRequest(0,5);
		List<Object[]> list =  empDao.findByEnameOrderbyEname(pageable);
		for(Object[] object : list)
		System.out.println("*******empno==" + object[0]  +  ",,,ename===" + object[1]  +  ",,,sal===" + object[2]  +",,,*******");
		
	}
	
	/**
	 * 13. 查询公司员工工资的最大值，最小值，平均值，总和
	 */
	@Test
	public void testfindAllSal(){
		List<Object[]> list =  empDao.findAllSal();
		for(Object[] object : list)
		System.out.println("*******max(e.sal)==" + object[0]  +  ",,,min(e.sal)===" + object[1]  +  ",,,avg(e.sal)===" + object[2]  +  ",,,sum(e.sal)===" + object[3] +",,,*******");
			
	}
	
	/**
	 * 14. 查询各deptno的员工工资的最大值，最小值，平均值，总和
	 */
	@Test
	public void test_findAllDeptno(){
		List<Object[]>  list = empDao.findAllDeptno();
		for(Object[] object : list)
		System.out.println("*******deptno==" + object[0]   +  ",,,max(e.sal)==" + object[1]  +  ",,,min(e.sal)===" + object[2]  +  ",,,avg(e.sal)===" + object[3]  +  ",,,sum(e.sal)===" + object[4] +",,,*******");
				
	}
	
	/**
	 * 15. 查询各个deptno的员工人数
	 */
	@Test
	public void test_findEachDeptno(){
		List<Object[]>  list = empDao.findEachDeptno();
		for(Object[] object : list)
		System.out.println("*******deptno==" + object[0]   +  ",,,count(e.ename)==" + object[1]  +",,,*******");
			
	}
}
