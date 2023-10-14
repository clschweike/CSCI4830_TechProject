package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// TODO: make sure name below is correct... in previous projects, it was employeeschweikert
@Table (name = "MyTableTechProject")

public class Product {
	
	@Id
	// Auto-generated
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "PROD_NAME")
	private String prodName;
	
	@Column(name = "PROD_SIZE")
	private String prodSize;
	
	@Column(name = "PROD_TIME")
	private Integer prodTime;
	
	@Column(name = "PROD_COST")
	private Integer prodCost;
	
	

	public Product(Integer id, String pname, String psize, Integer ptime, Integer pcost) {
		super();
		this.id = id;
		this.prodName = pname;
		this.prodSize = psize;
		this.prodTime = ptime;
		this.prodCost = pcost;
	}

	public Product(String pname, String psize, Integer ptime, Integer pcost) {
		super();
		this.prodName = pname;
		this.prodSize = psize;
		this.prodTime = ptime;
		this.prodCost = pcost;
	}
	
	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return prodName;
	}

	public void setName(String pname) {
		this.prodName = pname;
	}
	
	public String getSize() {
		return prodSize;
	}
	
	public void setSize(String psize) {
		this.prodSize = psize;
	}	

	public Integer getTime() {
		return prodTime;
	}

	public void setTime(Integer ptime) {
		this.prodTime = ptime;
	}
	
	public Integer getCost() {
		return prodCost;
	}

	public void setCost(Integer pcost) {
		this.prodCost = pcost;
	}

}
