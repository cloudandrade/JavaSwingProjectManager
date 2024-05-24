package model;

import java.sql.Timestamp;

public class ProjectModel {
	protected int id;
	protected String title;
	protected String description;
	protected int ownerId;
	protected String ownerName;
	protected String ownerPhone;
	protected int odsId;
	protected String odsName;
	protected Timestamp createdAt;
	protected String status;

	public ProjectModel() {

	}

	public ProjectModel(int id, String title, String description, int ownerId, String ownerName, String ownerPhone,
			int odsId, String odsName, Timestamp createdAt, String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.odsId = odsId;
		this.odsName = odsName;
		this.createdAt = createdAt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public int getOdsId() {
		return odsId;
	}

	public void setOdsId(int odsId) {
		this.odsId = odsId;
	}

	public String getOdsName() {
		return odsName;
	}

	public void setOdsName(String odsName) {
		this.odsName = odsName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
