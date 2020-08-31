package com.jeecms.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.jeecms.common.base.domain.AbstractDomain;

@Entity
@Table(name="yf_bj")
public class YfBj extends AbstractDomain<Integer>{
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**年度*/
    private  String nd;
    /**班级名称*/
    private String bjmc;
    /**学段*/ 
    private String xd;
    public YfBj () {
    	
    }
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@TableGenerator(name = "yf-bj", pkColumnValue = "yf-bj", initialValue = 0, allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "yf-bj")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotBlank
	@Length(max=20)
	@Column(name="nd", nullable=false, length=20)
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	@NotBlank
	@Length(max=64)
	@Column(name="bjmc", nullable=false, length=64)
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	@NotBlank
	@Length(max=2)
	@Column(name="xd", nullable=false, length=2)
	public String getXd() {
		return xd;
	}
	public void setXd(String xd) {
		this.xd = xd;
	}
    

}
