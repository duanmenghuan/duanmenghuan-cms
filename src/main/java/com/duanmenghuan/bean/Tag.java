package com.duanmenghuan.bean;

import java.io.Serializable;
import java.util.Objects;

public class Tag implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2053447229643474343L;

    private Integer id;

     private String displayname;

     private  String uniquename;

	public Tag() {
	}


	public Tag( String displayname) {

		this.displayname = displayname;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getUniquename() {
		return uniquename;
	}

	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tag tag = (Tag) o;
		return Objects.equals(id, tag.id) &&
				Objects.equals(displayname, tag.displayname) &&
				Objects.equals(uniquename, tag.uniquename);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, displayname, uniquename);
	}
}
