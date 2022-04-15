package com.kartikeychoudhary.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kartikeychoudhary.implementations.JournalImplementations;
import com.kartikeychoudhary.modal.JournalItem;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/jounal")
public class JounalController {
	private final JournalImplementations journalImpl;
	
	@GetMapping
	ResponseEntity<List<JournalItem>> getTodo(@RequestParam String date){
		Date d = new Date(Long.parseLong(date));
		List<JournalItem> todo = journalImpl.getAllJournalItemForDay(d);
		return ResponseEntity.ok().body(todo);
	}
	
	@GetMapping("/all")
	ResponseEntity<List<JournalItem>> getAllTodo(){
		List<JournalItem> todo = journalImpl.getAll();
		return ResponseEntity.ok().body(todo);
	}
	
	@PostMapping
	ResponseEntity<JournalItem> saveTodo(@RequestBody JournalItem item){
		item.setArchived(false);
		return ResponseEntity.ok().body(journalImpl.saveJournalItem(item));
	}
	
	@PatchMapping
	ResponseEntity<JournalItem> updateTodo(@RequestBody JournalItem item){
		return ResponseEntity.ok().body(journalImpl.updateJournalItem(item));
	}
	
	@PatchMapping("/archive")
	ResponseEntity<JournalItem> archiveTodo(@RequestBody JournalItem item){
		if(item.getArchived() == null) {throw new RuntimeException("isArchived is null");}
		item.setArchived(!item.getArchived());
		return ResponseEntity.ok().body(journalImpl.archiveJournalItem(item));
	}
	
	@DeleteMapping
	ResponseEntity<?> deleteTodo(@RequestBody JournalItem item){
		journalImpl.deleteJournalItem(item);
		return ResponseEntity.ok().build();
	}
}
