package com.kartikeychoudhary.dto;

import java.sql.Date;

import com.kartikeychoudhary.modal.JournalItem;

public class JournalItemDTO {

	private Long id;
	private String description;
	private Date date;
	private Boolean archived;
	
	public JournalItem convert() {
		JournalItem journalItem = new JournalItem();
		journalItem.setId(this.id);
		journalItem.setDescription(this.description);
		journalItem.setDate(this.date);
		journalItem.setArchived(this.archived);

		return journalItem;
	}
	
	public JournalItemDTO() {
		// need empty constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
	
	
}
