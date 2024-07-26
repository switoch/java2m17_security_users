package com.study.controller;

import com.study.data.entity.NoteEntity;
import com.study.data.service.NoteServiceDB;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RestController
public class NoteController {
    NoteEntity note;
    @Autowired
    NoteServiceDB noteService;

    @GetMapping(value = "/note/list")
    public ModelAndView getList(){
        ModelAndView mav = new ModelAndView("note/list");
        mav.addObject("list", noteService.listAll());
        return mav;
    }

    @PostMapping(value = "/note/create")
    public ModelAndView createNote(
            @RequestParam(value = "noteID") Long id,
            @RequestParam(value = "noteTitle") String title,
            @RequestParam(value = "noteContent") String content) {
        note = new NoteEntity();
        note.setTitle(title);
        note.setContent(content);
        note.setId(id);
        noteService.add(note);
        return new ModelAndView("redirect:list");

    }

    @PostMapping(value = "/note/delete")
    public ModelAndView deleteNoteById(@NotNull @RequestParam(value = "id") Long noteId, HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(302);
        noteService.deleteById(noteId);
        return new ModelAndView("redirect:list");
    }

    @GetMapping(value = "/note/delete")
    public ModelAndView deleteNote() {
        return new ModelAndView("/note/delete");
    }

    @GetMapping(value = "/note/edit")
    public ModelAndView editNote(@RequestParam(value = "id") Long noteId, String content, HttpServletResponse httpServletResponse) throws Exception {
        return editNoteById(noteId, content, httpServletResponse);
    }

    @PostMapping(value = "/note/edit")
    public ModelAndView editNoteById(@NotNull @RequestParam(value = "id") Long noteId, String content, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setStatus(302);
        noteService.update(noteId, content);
        return new ModelAndView("redirect:list");
    }
}
