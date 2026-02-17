/**Responsabilidades de NoteForm:
 * Manejar estado interno del formulario (title y content)
 * Mostrar datos cuando estamos editando
 * Avisarle a App.tsx cuando se guarda
 * Resetear el formulario después de guardar
 */

import {useState, useEffect} from "react"
import type {Note} from "../types/Note"

interface NoteFormProps{
    onSave: (note: Omit<Note, "id">,id?: number) => void
    editingNote: Note | null
}

export default function NoteForm({ onSave, editingNote}: NoteFormProps){
    const [title, setTitle] = useState("")
    const [content, setContent] = useState("")

    // Si cambia la nota a editar, cargamos los datos en el formulario
    useEffect(()=>{
        if(editingNote){
            setTitle(editingNote.title)
            setContent(editingNote.content)
        }else{
            setTitle("")
            setContent("")
        }
    },[editingNote])

    const handleSubmit = () => {
        if(!title.trim() || !content.trim()) return
            if(editingNote){
                onSave({title, content}, editingNote?.id)
            }else{
                onSave({title,content})
            } 
        
        setTitle("")
        setContent("")
    }

    return (
    <div>
      <h2>{editingNote ? "Editar nota" : "Crear nota"}</h2>

      <input
        type="text"
        placeholder="Título"
        value={title}
        onChange={e => setTitle(e.target.value)}
      />

      <textarea
        placeholder="Contenido"
        value={content}
        onChange={e => setContent(e.target.value)}
      />

      <button className="primary" onClick={handleSubmit}>
        {editingNote ? "Actualizar" : "Crear"}
      </button>

      <hr />
    </div>
  )
      
}