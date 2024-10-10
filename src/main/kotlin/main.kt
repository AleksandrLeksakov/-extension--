fun main() {
    val noteService = NoteService()

    // Создание заметки
    val note = noteService.createNote("Первая заметка")
    println("Создана заметка: ${note.text} (ID: ${note.id})")

    // Получение всех заметок
    val allNotes = noteService.getAllNotes()
    println("Все заметки:")
    allNotes.forEach { println(" ${it.text} (ID: ${it.id})") }

    // Обновление заметки
    val updatedNote = noteService.updateNote(note.id, "Обновленная заметка")
    if (updatedNote) {
        println("Заметка обновлена.")
    } else {
        println("Ошибка обновления заметки.")
    }

    // Удаление заметки
    val deletedNote = noteService.deleteNote(note.id)
    if (deletedNote) {
        println("Заметка удалена.")
    } else {
        println("Ошибка удаления заметки.")
    }

    // Восстановление заметки
    val restoredNote = noteService.restoreNote(note.id)
    if (restoredNote) {
        println("Заметка восстановлена.")
    } else {
        println("Ошибка восстановления заметки.")
    }

    // Создание комментария к заметке
    val comment = noteService.createComment(note.id, "Первый комментарий")
    println("Создан комментарий: ${comment.text} (ID: ${comment.id})")

    // Получение комментариев к заметке
    val comments = noteService.getCommentsByNoteId(note.id)
    println("Комментарии к заметке:")
    comments.forEach { println(" ${it.text} (ID: ${it.id})") }

    // Обновление комментария
    val updatedComment = noteService.updateComment(comment.id, "Обновленный комментарий")
    if (updatedComment) {
        println("Комментарий обновлен.")
    } else {
        println("Ошибка обновления комментария.")
    }

    // Удаление комментария
    val deletedComment = noteService.deleteComment(comment.id)
    if (deletedComment) {
        println("Комментарий удален.")
    } else {
        println("Ошибка удаления комментария.")
    }

    // Восстановление комментария
    val restoredComment = noteService.restoreComment(comment.id)
    if (restoredComment) {
        println("Комментарий восстановлен.")
    } else {
        println("Ошибка восстановления комментария.")
    }
}