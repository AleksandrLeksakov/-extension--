class NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var noteIdCounter = 1
    private var commentIdCounter = 1

    fun createNote(text: String): Note {
        val note = Note(noteIdCounter++, text)
        notes.add(note)
        return note
    }

    fun getAllNotes(): List<Note> {
        return notes.filter { !it.isDeleted }
    }

    fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id && !it.isDeleted }
    }

    fun updateNote(id: Int, text: String): Boolean {
        val note = notes.find { it.id == id && !it.isDeleted }
        if (note != null) {
            note.text = text
            return true
        }
        return false
    }

    fun deleteNote(id: Int): Boolean {
        val note = notes.find { it.id == id && !it.isDeleted }
        if (note != null) {
            note.isDeleted = true
            return true
        }
        return false
    }

    fun restoreNote(id: Int): Boolean {
        val note = notes.find { it.id == id && it.isDeleted }
        if (note != null) {
            note.isDeleted = false
            return true
        }
        return false
    }

    fun createComment(noteId: Int, text: String): Comment {
        val note = getNoteById(noteId)
        if (note != null) {
            val comment = Comment(commentIdCounter++, text, noteId)
            comments.add(comment)
            return comment
        } else {
            throw NoteNotFoundException("Note with id $noteId not found.")
        }
    }

    fun getCommentsByNoteId(noteId: Int): List<Comment> {
        return comments.filter { it.noteId == noteId && !it.isDeleted }
    }

    fun updateComment(id: Int, text: String): Boolean {
        val comment = comments.find { it.id == id && !it.isDeleted }
        if (comment != null) {
            comment.text = text
            return true
        }
        return false
    }

    fun deleteComment(id: Int): Boolean {
        val comment = comments.find { it.id == id && !it.isDeleted }
        if (comment != null) {
            comment.isDeleted = true
            return true
        }
        return false
    }

    fun restoreComment(id: Int): Boolean {
        val comment = comments.find { it.id == id && it.isDeleted }
        if (comment != null) {
            comment.isDeleted = false
            return true
        }
        return false
    }
}
