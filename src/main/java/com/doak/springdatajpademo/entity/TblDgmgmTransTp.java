package com.doak.springdatajpademo.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the tbl_dgmgm_trans_tp database table.
 * 
 */
@Entity
@Table(name="tbl_dgmgm_trans_tp")
@NamedQuery(name="TblDgmgmTransTp.findAll", query="SELECT t FROM TblDgmgmTransTp t")
@Data
public class TblDgmgmTransTp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trans_id")
	private int transId;

	@Column(name="trans_code")
	private String transCode;

	@Column(name="trans_name")
	private String transName;

	
	
	
	

}