package com.kartikeychoudhary.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kartikeychoudhary.dto.JournalItemDTO;
import com.kartikeychoudhary.exceptions.CustomGenericRuntimeException;
import com.kartikeychoudhary.modal.JournalItem;
import com.kartikeychoudhary.response.GenericResponse;
import com.kartikeychoudhary.services.JournalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/jounal")
public class JournalController {
	private final JournalService journalImpl;
	
	GenericResponse gr;
	Map<String, Object> payload;
	HttpStatus status;
	
	@GetMapping
	ResponseEntity<List<JournalItem>> getJournalItem(@RequestParam String date){
		Date d = new Date(Long.parseLong(date));
		List<JournalItem> journalItem = journalImpl.getAllJournalItemForDay(d);
		return ResponseEntity.ok().body(journalItem);
	}
	
	@GetMapping("/all")
	ResponseEntity<List<JournalItem>> getAllTodo(){
		List<JournalItem> journalItems = journalImpl.getAll();
		return ResponseEntity.ok().body(journalItems);
	}
	
	@PostMapping
	ResponseEntity<JournalItem> saveTodo(@RequestBody JournalItemDTO item){
		item.setArchived(false);
		return ResponseEntity.ok().body(journalImpl.saveUpdateJournalItem(item.convert()));
	}
	
	@PatchMapping
	ResponseEntity<JournalItem> updateTodo(@RequestBody JournalItemDTO item){
		return ResponseEntity.ok().body(journalImpl.saveUpdateJournalItem(item.convert()));
	}
	
	@PatchMapping("/archive")
	ResponseEntity<JournalItem> archiveTodo(@RequestBody JournalItemDTO item){
		if(item.getArchived() == null) {throw new CustomGenericRuntimeException("isArchived is null");}
		item.setArchived(!item.getArchived());
		return ResponseEntity.ok().body(journalImpl.archiveJournalItem(item.convert()));
	}
	
	@DeleteMapping
	ResponseEntity<GenericResponse> deleteTodo(@RequestBody JournalItemDTO item){
		journalImpl.deleteJournalItem(item.convert());
		return ResponseEntity.ok().build();
	}
}
