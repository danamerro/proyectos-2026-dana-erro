/*Components*/
import NoteForm from "./components/NoteForm"
import NoteList from "./components/NoteList"
import { useNotes } from "./hooks/useNotes"

function App() {
  /*estos son equivalentes a crear una lista vacia, solo que se renderiza solo*/
  //const [notes, setNotes] = useState<Note[]>([])
  //const [editingNote, setEditingNote] = useState<Note | null>(null)
  const{notes,editingNote,setEditingNote,saveNote,deleteNote,loading,error} = useNotes()
  /* es como usar @Postconstruct, hace un fetch del get (me trae mis notas) */
  /*useEffect(() => {
    fetch("http://localhost:8080/api/notes/get")
      .then(response => response.json())
      .then(data => setNotes(data))
      .catch(error => console.error("Error:", error))
  }, [])*/
  /*useEffect(() => {
    const loadNotes = async () =>{
      const data = await noteService.getNotes()
      setNotes(data)
    }

    loadNotes()
  }, [])*/

    /*const handleSave = async (noteData: Omit<Note, "id">, id?: number) => {
      
        if (id) {

          const handleSave = await noteService.updateNote(id, noteData)

          setNotes(notes.map(n => n.id === id ? handleSave : n))
          setEditingNote(null)

          } else{
            const created = await noteService.createNote(noteData)
            setNotes([...notes, created])
          }

          const handleDelete = async (id: number) => {      
              await noteService.deleteNote(id)
              setNotes(notes.filter(n => n.id !== id))  
            }*/

      return (
            <div style={{
              display:"flex",
              justifyContent:" center",
              alignItems:"center",
              width: "100vw",
              height: "100vh"  
              }}>

              <div style={{width:"400px"}}>
                <h1>Gestor de notas</h1>
                {loading && <p>Cargando...</p>}
                {error && <p style={{color: "red"}}>{error}</p>}
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
          </div>  
      )
  }


export default App
