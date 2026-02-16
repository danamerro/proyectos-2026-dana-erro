import { useState, useEffect } from "react";
import type { Note } from "../types/Note"
import * as noteService from "../services/noteService"

export function useNotes(){
    const [notes, setNotes] = useState<Note[]>([])
    const [editingNote, setEditingNote] = useState<Note | null>(null)

      useEffect(() => {
    const loadNotes = async () => {
      const data = await noteService.getNotes()
      setNotes(data)
    }
    loadNotes()
  }, [])

  const saveNote = async (noteData: Omit<Note,"id">) => {
    if(editingNote){
        const updated = await noteService.updateNote(editingNote.id,noteData)
        setNotes(notes.map(n => n.id === editingNote.id ? updated : n))

        setEditingNote(null)
    }else{
        const created = await noteService.createNote(noteData)
        setNotes([...notes, created])
    }
  }

  const deleteNote = async (id: number) => {
    await noteService.deleteNote(id)
    setNotes(notes.filter(n => n.id !== id))
  }

  return {
    notes, editingNote,setEditingNote,saveNote,deleteNote
  }
}
