import type { Note } from "../types/Note"

const BASE_URL = "http://localhost:8080/api/notes"
/* Equivalente al Repository */
export const getNotes = async (): Promise<Note[]> => {
    const response = await fetch(`${BASE_URL}/get`)
    return response.json()
}

export const createNote = async (note: Omit<Note, "id">): Promise<Note> => {
  const response = await fetch(`${BASE_URL}/crear`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(note)
  })
  return response.json()
}

export const updateNote = async (id: number, note: Omit<Note, "id">): Promise<Note> => {
  const response = await fetch(`${BASE_URL}/put/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(note)
  })
  return response.json()
}

export const deleteNote = async (id: number): Promise<void> => {
  await fetch(`${BASE_URL}/eliminar/${id}`, {
    method: "DELETE"
  })
}