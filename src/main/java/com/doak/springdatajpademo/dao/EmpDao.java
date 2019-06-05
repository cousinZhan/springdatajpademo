package com.doak.springdatajpademo.dao;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;


import com.doak.springdatajpademo.entity.Emp;



@Component
public interface EmpDao extends JpaRepository<Emp, Integer>{
	
	public Emp findByEmpno(int id);
	
	public Emp save(Emp emp);
	
	public boolean existsByEmpno(int id);
	
	public List<Emp> findAll(Sort sort);
	
	@Query("from Emp where empno = ?1")
	public Emp selectbyId(int empno);
	
	
	@Modifying
	@Query("update Emp e set e.ename = :ename where e.empno = :empno")
	public int updateById(@Param("ename") String ename, @Param("empno") Integer empno);
	
	public  List<Emp> findAll();
	
	//1.查询工资大于1200的员工姓名和工资
	public List<Emp> findBySalGreaterThan(BigDecimal sal);
	
	//2/查询员工号为7698的员工的姓名和部门号
	public Emp findByEmpno(Integer empno);
	
	//3.选择工资不在500到1200的员工的姓名和工资
	public  List<Emp> findBySalLessThanOrSalGreaterThan(BigDecimal min,BigDecimal max);
	
	//4.选择雇用时间在1981-02-01到1987-05-01之间的员工姓名，job_id和雇用时间
	public List<Emp> findByHiredateBetween(Date start, Date end);
	
	//5.选择在20或30号部门工作的员工姓名和部门号
	public List<Emp> findByDeptnoOrDeptno(Integer deptno1, Integer deptno2);
	
	//6.选择在1981年雇用的员工的姓名和雇用时间
	@Query("select e.ename , e.hiredate from Emp e where substring(cast(e.hiredate as string),1,4) = ?1")
	public List<Object[]> findByHiredate(String hiredate);
	
	//7. 选择公司中没有管理者的员工姓名及job
	@Query("select e.ename, e.job from Emp e where e.mgr is null")
	public List<Object[]> findByMgrIsNull();
	
	//8. 选择公司中有奖金的员工姓名，工资和奖金
	@Query("select e.ename, e.sal, e.comm from Emp e where e.comm <> 0 ")
	public List<Object[]> findByCommIsNotNull();
	
	//9. 选择员工姓名的第三个字母是a的员工姓名
	@Query("select e.ename from Emp e where substring(cast(e.ename as string),3,1) = ?1")
	public List<Object[]> findByEnameContains(String str);

	//10. 选择姓名中有字母a和e的员工姓名  --有点问题
//	@Query("select e.ename from Emp e where e.ename contains")
	
	//.11.查询员工号，姓名，工资，以及工资提高百分之20%后的结果（new salary）
	public List<Object[]> findBySal();
	
	//12. 将员工的姓名按首字母排序，并使用分页查询查出前5个记录。
	public List<Object[]> findByEnameOrderbyEname(Pageable pageable);
	
	//13. 查询公司员工工资的最大值，最小值，平均值，总和
	public List<Object[]> findAllSal();
	
	//14. 查询各deptno的员工工资的最大值，最小值，平均值，总和
	public List<Object[]>  findAllDeptno();
	
	//15. 查询各个deptno的员工人数
	public List<Object[]>  findEachDeptno();
	
}
