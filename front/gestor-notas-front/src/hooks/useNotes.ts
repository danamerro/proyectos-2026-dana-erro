import { useState, useEffect } from "react";
import type { Note } from "../types/Note"
import * as noteService from "../services/noteService"

export function useNotes(){
    const [notes, setNotes] = useState<Note[]>([])
    const [editingNote, setEditingNote] = useState<Note | null>(null)


    const [loading, setLoading] = useState(false)
    const [saving, setSaving] = useState(false)
    const [error, setError] = useState<string | null>(null)
    const [message, setMessage] = useState<string | null> (null)
       /* es como usar @Postconstruct, hace un fetch del get (me trae mis notas) */  
       
       //CARGAR NOTAS AL INICIO 
       useEffect(() => {
        const loadNotes = async () => {  
            try{
                setLoading(true)
                const data = await noteService.getNotes()
                //setError(null)
                setNotes(data)
            }catch(e){
                setError("Error cargando notas")
            }finally{
                setLoading(false)
            }  
        }
        loadNotes()
    }, [])
    
    
    const saveNote = async (noteData: Omit<Note, "id">, id?: number) => {
    try {
      setSaving(true)

      if (id) {
        const updated = await noteService.updateNote(id, noteData)

        setNotes(prev =>
          prev.map(n => n.id === id ? updated : n)
        )

        setEditingNote(null)
        setMessage("Nota actualizada ✔")

      } else {
        const created = await noteService.createNote(noteData)

        setNotes(prev => [...prev, created])
        setMessage("Nota creada ✔")
      }

      setTimeout(() => {
        setMessage(null)
      }, 2000)

    } catch (e) {
      setError("Error guardando nota")
    } finally {
      setSaving(false)
    }
  }

   
   //ELIMINAR CON CONFIRMACION 
   const deleteNote = async (id: number) => {

    const confirmed = window.confirm("¿Seguro que querés eliminar esta nota?")
    if (!confirmed) return

    try {
      await noteService.deleteNote(id)

      setNotes(prev => prev.filter(n => n.id !== id))
      setMessage("Nota eliminada ✔")

      setTimeout(() => {
        setMessage(null)
      }, 2000)

    } catch (e) {
      setError("Error eliminando nota")
    }
  } 

  return {
    notes, 
    editingNote,
    setEditingNote,
    saveNote,
    deleteNote,
    loading,
    saving,
    error,
    message
  }
}
