import { useState, useEffect } from "react";
import type { Note } from "../types/Note"
import * as noteService from "../services/noteService"

export function useNotes(){
    const [notes, setNotes] = useState<Note[]>([])
    const [editingNote, setEditingNote] = useState<Note | null>(null)
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState<string | null>(null)
      useEffect(() => {
        const loadNotes = async () => {
            setLoading(true)
        try{
            //await new Promise(resolve => setTimeout(resolve,5000))
            const data = await noteService.getNotes()
            setNotes(data)
        }catch(e){
            setError("Error cargando notas")
        }finally{
            setLoading(false)
        }  
        
        }
        loadNotes()
    }, [])

    const saveNote = async (noteData: Omit<Note, "id">) => {
    try {
        setLoading(true)

        //await new Promise(resolve => setTimeout(resolve, 2000)) 

        const created = await noteService.createNote(noteData)

        setNotes(prev => [...prev, created])

    } finally {
        setLoading(false)
    }
    }



  const deleteNote = async (id: number) => {
    
    try{
        setLoading(true)
        //await new Promise(resolve => setTimeout(resolve, 2000)) 
        await noteService.deleteNote(id)

        setNotes(prev => prev.filter(n => n.id !== id))
    }catch(e){
        setError("Error eliminando nota")
    }finally{
        setLoading(false)
    }
    
  }

  return {
    notes, editingNote,setEditingNote,saveNote,deleteNote,loading,error
  }
}
