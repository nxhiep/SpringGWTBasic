package com.hnx.base.shared.model;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.hnx.base.shared.Config;

@Entity
public class ActivityInfo implements IBasic {
	@Ignore private static final long serialVersionUID = 1L;
	@Id private Long id;
	private String name = Config.TEXT_EMPTY;
	private String description = Config.TEXT_EMPTY;
	private double tm = Config.LONG_NULL;
	private double tp = Config.LONG_NULL;
	private List<Long> actionIds = new ArrayList<Long>();
	@Index private String userId = Config.TEXT_EMPTY;
	@Index private Long lastUpdate;
	@Index private Long createDate;
	
	public ActivityInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	public Long getCreateDate() {
		return createDate;
	}

	@Override
	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTm() {
		return tm;
	}

	public void setTm(double tm) {
		this.tm = tm;
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

	public List<Long> getActionIds() {
		return actionIds;
	}

	public void setActionIds(List<Long> actionIds) {
		this.actionIds = actionIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getLastUpdate() {
		return lastUpdate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
