package com.doak.springdatajpademo;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.doak.springdatajpademo.dao.EmpDao;
import com.doak.springdatajpademo.entity.Emp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DempTest {
	
	@Autowired
	EmpDao empDao;
	
	@Test
	public void testFIndById(){
		System.out.println(empDao.findByEmpno(7369));
		
	}
	
	@Test
	public void testsave(){
		Emp emp = new Emp();
		emp.setEmpno(8888);
		emp.setEname("tony");
		emp.setJob("haircut");
		emp.setSal(new BigDecimal("50000"));
		System.out.println(empDao.save(emp));
	}
	
	
	@Test
	public void testexistsByEmpno(){
		System.out.println("*************" + empDao.existsByEmpno(7935) + "*************" );
	}

	
	@Test
	public  void testfindAll(){
		Sort sort = new Sort(Sort.Direction.DESC,"sal");
		System.out.println(empDao.findAll(sort));
	}
	
	@Test
	public void testselectbyId(){
		System.out.println(empDao.selectbyId(7935));
	}
	
	@Transactional
	@Rollback(false)
	@Test
	public void testupdateById(){
		System.out.println(empDao.updateById("28855", 7935));
		
	}
	
	@Test
	public void testFindAll(){
		System.out.println(empDao.findAll());
	}

	@Test
	public void testfindBySalLessThanAndSalGreaterThan(){
		System.out.println(empDao.findBySalLessThanOrSalGreaterThan(new BigDecimal("500"), new BigDecimal("1000")));
	}
	
	
}
