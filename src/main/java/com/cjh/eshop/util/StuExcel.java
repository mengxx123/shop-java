package com.cjh.eshop.util;



/**导出Excel的学生类*/
public class StuExcel 
{
    private long	id;
    private String	name;
    private String 	sex;	//值为"男"或"女"
    private String	major;
    private String	political;
    private String	email;
    private String	address;
    private String	idCard;

    
    public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getPolitical() {
		return political;
	}


	public void setPolitical(String political) {
		this.political = political;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public StuExcel() {}
    

    public StuExcel(long id, String name, String idCard, String sex, String major, 
    		String address, String political, String email) 
    {
        super();
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.sex = sex;
        this.major = major;
        this.address = address;
        this.political = political;
        this.email = email;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

  
}
