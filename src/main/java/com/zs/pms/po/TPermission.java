package com.zs.pms.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限表
 * 
 * @author Administrator
 *
 */
public class TPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7512049132416900243L;

	private int id;
	private String pname;
	private int pid;
	private String url;
	private int lev;
	private int isleaf;
	private String icon;

	// 子权限集
	private List<TPermission> children=new ArrayList<>();
	
	//添加一个子权限
	public void addChlid(TPermission per) {
		children.add(per);
	}

	public List<TPermission> getChildren() {
		return children;
	}

	public void setChildren(List<TPermission> children) {
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}



}
