/*Components*/
import {useState, useEffect} from "react"
import NoteForm from "./components/NoteForm"
import NoteList from "./components/NoteList"
import { useNotes } from "./hooks/useNotes"
import"./App.css"

function App() {
  /*estos son equivalentes a crear una lista vacia, solo que se renderiza solo*/
  const[theme, setTheme] = useState<"light" | "dark">("light")
  useEffect(()=> { /*aca cambia el body por dark o light */
    document.body.setAttribute("data-theme",theme)
  },[theme])

  const{
    notes,
    editingNote,
    setEditingNote,
    saveNote,
    deleteNote,
    loading,
    saving,
    error,
    message} = useNotes()
 
      return (
      <div className="container">

        <button
            className="theme-toggle"
            onClick={() => setTheme(theme === "light" ? "dark" : "light")}
            >
            {theme === "light" ? "☀ Light mode" : "🌙 Dark mode"}
        </button>



        <h1>Gestor de Notas</h1>

        {loading && <p>Cargando...</p>}

        {error && (
          <p style={{ color: "red" }}>
            {error}
          </p>
        )}

        {message && (
          <p style={{ color: "#22c55e" }}>
            {message}
          </p>
        )}

        <NoteForm
          onSave={saveNote}
          editingNote={editingNote}
          saving={saving}
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
