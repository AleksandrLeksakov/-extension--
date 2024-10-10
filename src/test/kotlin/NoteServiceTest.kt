import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class NoteServiceTest {
    private lateinit var noteService: NoteService

    @Before
    fun setUp() {
        noteService = NoteService()
    }

    @Test
    fun `should create note and get it by id`() {
        val note = noteService.createNote("Test Note")
        val retrievedNote = noteService.getNoteById(note.id)

        assertNotNull(retrievedNote)
        assertEquals(note, retrievedNote)
    }

    @Test
    fun `should get all notes`() {
        val note1 = noteService.createNote("Note 1")
        val note2 = noteService.createNote("Note 2")
        val notes = noteService.getAllNotes()

        assertEquals(2, notes.size)
        assertTrue(notes.contains(note1))
        assertTrue(notes.contains(note2))
    }

    @Test
    fun `should update note`() {
        val note = noteService.createNote("Test Note")
        val updatedText = "Updated Note"
        assertTrue(noteService.updateNote(note.id, updatedText))
        val retrievedNote = noteService.getNoteById(note.id)

        assertNotNull(retrievedNote)
        assertEquals(updatedText, retrievedNote?.text)
    }

    @Test
    fun `should delete note`() {
        val note = noteService.createNote("Test Note")
        assertTrue(noteService.deleteNote(note.id))
        val retrievedNote = noteService.getNoteById(note.id)

        assertNull(retrievedNote)
    }

    @Test
    fun `should restore note`() {
        val note = noteService.createNote("Test Note")
        noteService.deleteNote(note.id)
        assertTrue(noteService.restoreNote(note.id))
        val retrievedNote = noteService.getNoteById(note.id)

        assertNotNull(retrievedNote)
        assertEquals("Test Note", retrievedNote?.text)
    }

    @Test
    fun `should create comment for note`() {
        val note = noteService.createNote("Test Note")
        val comment = noteService.createComment(note.id, "Test Comment")
        val comments = noteService.getCommentsByNoteId(note.id)

        assertEquals(1, comments.size)
        assertTrue(comments.contains(comment))
    }

    @Test(expected = NoteNotFoundException::class)
    fun `should throw exception when creating comment for non-existent note`() {
        noteService.createComment(100, "Test Comment")
    }

    @Test
    fun `should get comments by note id`() {
        val note = noteService.createNote("Test Note")
        val comment1 = noteService.createComment(note.id, "Comment 1")
        val comment2 = noteService.createComment(note.id, "Comment 2")
        val comments = noteService.getCommentsByNoteId(note.id)

        assertEquals(2, comments.size)
        assertTrue(comments.contains(comment1))
        assertTrue(comments.contains(comment2))
    }

    @Test
    fun `should update comment`() {
        val note = noteService.createNote("Test Note")
        val comment = noteService.createComment(note.id, "Test Comment")
        val updatedText = "Updated Comment"
        assertTrue(noteService.updateComment(comment.id, updatedText))
        val comments = noteService.getCommentsByNoteId(note.id)

        assertEquals(1, comments.size)
        assertEquals(updatedText, comments[0].text)
    }

    @Test
    fun `should delete comment`() {
        val note = noteService.createNote("Test Note")
        val comment = noteService.createComment(note.id, "Test Comment")
        assertTrue(noteService.deleteComment(comment.id))
        val comments = noteService.getCommentsByNoteId(note.id)

        assertTrue(comments.isEmpty())
    }

    @Test
    fun `should restore comment`() {
        val note = noteService.createNote("Test Note")
        val comment = noteService.createComment(note.id, "Test Comment")
        noteService.deleteComment(comment.id)
        assertTrue(noteService.restoreComment(comment.id))
        val comments = noteService.getCommentsByNoteId(note.id)

        assertEquals(1, comments.size)
        assertEquals("Test Comment", comments[0].text)
    }
}