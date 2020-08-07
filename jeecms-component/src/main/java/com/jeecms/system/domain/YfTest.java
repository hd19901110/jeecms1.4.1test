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

/**
 * @Description:测试项目数据流向
 * @author yf
 * @data 2020-08-06 18:39
 */
@Entity
@Table(name="yf_test")
public class YfTest extends AbstractDomain<Integer>{
	private static final long serialVersionUID = 1L;
	/**主键*/
	private Integer id;
	/**姓名*/
	private String name;
	/**年龄*/
	private Integer age;
	/**性别*/
	private String sex;
	/**身高*/
	private String  height; 
	/**体重*/
    private String  weight;
    
	public YfTest() {
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@TableGenerator(name = "yf-test", pkColumnValue = "yf-test", initialValue = 0, allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "yf-test")
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	@NotBlank
	@Length(max=100)
	@Column(name="name", nullable=false, length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotBlank
	@Column(name="age", nullable=false)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@NotBlank
	@Length(max=3)
	@Column(name="sex", nullable=false, length=3)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@NotBlank
	@Length(max=10)
	@Column(name="height", nullable=false, length=10)
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	@NotBlank
	@Length(max=10)
	@Column(name="weight", nullable=false, length=10)
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "YfTest [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", height=" + height
				+ ", weight=" + weight + "]";
	}
    
 	
}
