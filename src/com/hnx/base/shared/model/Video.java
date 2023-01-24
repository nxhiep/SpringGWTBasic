package com.hnx.base.shared.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.hnx.base.shared.Config;

@Entity
public class Video implements IBasic {

	private static final long serialVersionUID = 1L;
	@Id private Long id;
	@Index private String name = Config.TEXT_EMPTY;
	private String description = Config.TEXT_EMPTY;
	private String url = Config.TEXT_EMPTY;
	private String image = Config.TEXT_EMPTY;
	@Index private String category = Config.TEXT_EMPTY;
	@Index private Long lastUpdate;
	@Index private Long createDate;
	
	public Video() {}
	
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getLastUpdate() {
		return lastUpdate;
	}
	
	@Override
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getCreateDate() {
		return createDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
}
