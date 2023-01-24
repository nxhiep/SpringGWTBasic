package com.hnx.base.shared.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ConsumableOutput implements IBasic {
	/**
	 * 
	 */
	@Ignore private static final long serialVersionUID = 1L;
	@Id private Long id;
	@Index private Long type;
	private Double quantity;
	private Long unit;
	@Index private Long rootId;
	@Index private Long userId;
	@Index private Long beneficiaryUnit;
	@Index private Long beneficiaryUser;
	@Index private Long createDate;
	@Index private Long lastUpdate;
	
	public ConsumableOutput() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Long getUnit() {
		return unit;
	}

	public void setUnit(Long unit) {
		this.unit = unit;
	}

	public Long getRootId() {
		return rootId;
	}

	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBeneficiaryUnit() {
		return beneficiaryUnit;
	}

	public void setBeneficiaryUnit(Long beneficiaryUnit) {
		this.beneficiaryUnit = beneficiaryUnit;
	}

	public Long getBeneficiaryUser() {
		return beneficiaryUser;
	}

	public void setBeneficiaryUser(Long beneficiaryUser) {
		this.beneficiaryUser = beneficiaryUser;
	}

	@Override
	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	public Long getLastUpdate() {
		return lastUpdate;
	}
	
	public Long getCreateDate() {
		return createDate;
	}
}
