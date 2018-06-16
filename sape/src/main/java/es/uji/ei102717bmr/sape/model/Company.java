package es.uji.ei102717bmr.sape.model;

import java.util.List;

public class Company {
	private String cif;
	private String mail;
	private String name;
	private String address;
	private String telephone;
	private int vat;
	
	private List<String> offers;
	
	public Company(){
		super();
	}
	
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getVat() {
		return vat;
	}
	public void setVat(int vat) {
		this.vat = vat;
	}

	public List<String> getOffers() {
		return offers;
	}

	public void setOffers(List<String> offers) {
		this.offers = offers;
	}
	
	@Override
	public String toString() {
		return "Company [name=" + name + ", cif=" + cif + ", mail="
				+ mail + ", address=" + address + ", telefone="
				+ telephone + ", vat=" + vat +"]";
	} 
	
}
