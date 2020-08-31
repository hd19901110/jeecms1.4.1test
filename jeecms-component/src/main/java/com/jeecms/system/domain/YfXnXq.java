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
@Table(name="yf_xnxq")
public class YfXnXq extends AbstractDomain<Integer>{
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**学年*/
    private String xn;
    /**学期*/
    private String xq;
	public YfXnXq() {
		
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@TableGenerator(name = "yf-xnxq", pkColumnValue = "yf-xnxq", initialValue = 0, allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "yf-xnxq")
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotBlank
	@Length(max=20)
	@Column(name="xn", nullable=false, length=20)
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	@NotBlank
	@Length(max=20)
	@Column(name="xq", nullable=false, length=20)
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	
    
    
}
