package com.jrh.appusage;

public class AppInfo {
	private String pkgname = "pkgname";
	private String label = "label";
	private int total = 1;
	
	public AppInfo(){}
	
	public AppInfo(String pkgname, String label,int total){
		this.pkgname = pkgname;
		this.label = label;
		this.total = total;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}