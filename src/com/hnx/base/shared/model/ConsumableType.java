package com.hnx.base.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.hnx.base.shared.Config;

@Entity
public class ConsumableType implements IBasic {
	
	@Ignore private static final long serialVersionUID = 1L;
	@Id private Long id ;
	@Index private String name = Config.TEXT_EMPTY;
	@Index private Long rootId = Config.NULL_ID;
	@Index private Long parentId = Config.NULL_ID;
	private String parentName = Config.TEXT_EMPTY;
	@Index private String codeId = Config.TEXT_EMPTY;
	@Index private String indexId = Config.TEXT_EMPTY;
	@Index private Integer order = 0;
	private List<String> hints = new ArrayList<String>();
	private String description = Config.TEXT_EMPTY;
	private Long unitId = Config.NULL_ID;
	private String unitName = Config.TEXT_EMPTY;
	@Index private long lastUpdate = Config.NULL_ID;
	@Index private long createDate = Config.NULL_ID;
	@Ignore List<ConsumableType> consumableTypes = new ArrayList<ConsumableType>();

	public ConsumableType() {
		this.lastUpdate = new Date().getTime();
	}
	
	public ConsumableType(String name, String codeId, String indexId, int order, long parent) {
		this();
		this.setName(name);
		this.setCodeId(codeId);
		this.setIndexId(indexId);
		this.setParentId(parent);
		this.setOrder(order);
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	public String getFullName() {
		if (parentName != null && !parentName.isEmpty()) {
			return parentName + " - " + this.getName();
		}
		else {
			return this.getName();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRootId() {
		return rootId;
	}

	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public List<ConsumableType> getConsumableTypes() {
		return consumableTypes;
	}
	
	public void setConsumableTypes(List<ConsumableType> consumableTypes) {
		this.consumableTypes = consumableTypes;
	}

	public List<String> getHints() {
		return hints;
	}

	public void setHints(List<String> hints) {
		this.hints = hints;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Override
	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	public long getCreateDate() {
		return createDate;
	}
}