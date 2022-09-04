package com.kartikeychoudhary.dto;

import java.sql.Date;

import com.kartikeychoudhary.modal.JournalItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
