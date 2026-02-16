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
        }
    },[editingNote])

    const handleSubmit = () => {
        if(!title.trim() || !content.trim()) return 
        onSave({title, content}, editingNote?.id)
        setTitle("")
        setContent("")
    }

    return (
        <>
        <h2>{editingNote ? "Editar nota" : "Crear nota"}</h2>
        
        <input
            type="text"
            placeholder="Titulo"
            value={title}
            onChange={e => setTitle(e.target.value)}
            style={{width: "100%", marginBottom: "10px"}}
        />

        <textarea
            placeholder="Contenido"
            value={content}
            onChange={e => setContent(e.target.value)}
            style={{width: "100%", marginBottom:"10px"}}
        />

        <button onClick={handleSubmit}>
            {editingNote ? "Actualizar" : "Crear"}
        </button>

        <hr/>
       </> 
    )      
}