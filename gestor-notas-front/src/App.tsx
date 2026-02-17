/*Components*/
import NoteForm from "./components/NoteForm"
import NoteList from "./components/NoteList"
import { useNotes } from "./hooks/useNotes"
import"./App.css"

function App() {
  /*estos son equivalentes a crear una lista vacia, solo que se renderiza solo*/
  const{notes,editingNote,setEditingNote,saveNote,deleteNote,loading,error} = useNotes()
 
      return (
  <div className="container">
    <h1>Gestor de Notas</h1>

    {loading && <p>Cargando...</p>}
    {error && <p style={{ color: "red" }}>{error}</p>}

    <NoteForm
      onSave={saveNote}
      editingNote={editingNote}
    />

    <NoteList
      notes={notes}
      onDelete={deleteNote}
      onEdit={setEditingNote}
    />
  </div>
)

  }


export default App
