import type {Note} from "../types/Note"

interface NoteListProps{
    notes: Note[]
    onDelete: (id: number) => void
    onEdit: (note: Note) => void
}

export default function NoteList({notes, onDelete, onEdit}: NoteListProps){
    return (
        <>
        {notes.map(note => (
            <div key={note.id}>
                <h3>{note.title}</h3>
                <p>{note.content}</p>

                <button onClick={() => onDelete(note.id)}>
                    Eliminar
                </button>

                <button onClick={() => onEdit(note)}>
                    Editar
                </button>

                <hr/>

            </div>
        ))}
        </>
    )
}