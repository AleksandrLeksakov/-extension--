data class Comment(
    val id: Int,
    var text: String,
    val noteId: Int,
    var isDeleted: Boolean = false
)
