package com.gvozdev.notesapp

import com.gvozdev.notesapp.domain.Hashtag
import com.gvozdev.notesapp.domain.Note
import com.gvozdev.notesapp.service.NoteService
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat
import java.util.Locale.ENGLISH

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {
    companion object {
        const val MOCK_ID: Long = -1L
    }

    private val formatter = SimpleDateFormat("yyyy-MM-dd", ENGLISH)
    private val created = formatter.parse("1970-01-01")

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var noteService: NoteService

    @AfterEach
    fun cleanUp() {
        noteService.deleteNoteById(MOCK_ID)
    }

    @Test
    fun `should add new note`() {
        val noteTableRowsCountBefore = countRowsInTable(jdbcTemplate, "note")
        addMockNote().andExpect(status().isOk)

        val noteTableRowsCountAfter = countRowsInTable(jdbcTemplate, "note")

        assertEquals(noteTableRowsCountBefore, noteTableRowsCountAfter.minus(1))
        assertThat(noteService.getAllNotes()).contains(Note(MOCK_ID, "noteText", created, "", setOf()))
    }

    @Test
    fun `should add new note with name`() {
        val noteTableRowsCountBefore = countRowsInTable(jdbcTemplate, "note")
        addMockNoteWithName().andExpect(status().isOk)

        val noteTableRowsCountAfter = countRowsInTable(jdbcTemplate, "note")

        assertEquals(noteTableRowsCountBefore, noteTableRowsCountAfter.minus(1))
        assertThat(noteService.getAllNotes()).contains(Note(MOCK_ID, "noteText", created, "noteName", setOf()))
    }

    @Test
    fun `should add new note with hashtags`() {
        val noteTableRowsCountBefore = countRowsInTable(jdbcTemplate, "note")
        val hashtagTableRowsCountBefore = countRowsInTable(jdbcTemplate, "hashtag")
        val noteHashtagTableRowsCountBefore = countRowsInTable(jdbcTemplate, "note_hashtag")
        addMockNoteWithHashtags().andExpect(status().isOk)

        val noteTableRowsCountAfter = countRowsInTable(jdbcTemplate, "note")
        val hashtagTableRowsCountAfter = countRowsInTable(jdbcTemplate, "hashtag")
        val noteHashtagTableRowsCountAfter = countRowsInTable(jdbcTemplate, "note_hashtag")

        assertEquals(noteTableRowsCountBefore, noteTableRowsCountAfter.minus(1))
        assertEquals(hashtagTableRowsCountBefore, hashtagTableRowsCountAfter.minus(1))
        assertEquals(noteHashtagTableRowsCountBefore, noteHashtagTableRowsCountAfter.minus(1))
        assertThat(noteService.getAllNotes()).contains(Note(MOCK_ID, "noteText", created, "", setOf(Hashtag(MOCK_ID, "hashtagText"))))
    }

    @Test
    fun `should delete note`() {
        val noteTableRowsCountBefore = countRowsInTable(jdbcTemplate, "note")
        addMockNote().andExpect(status().isOk)
        deleteMockNote().andExpect(status().isOk)

        val noteTableRowsCountAfter = countRowsInTable(jdbcTemplate, "note")

        assertEquals(noteTableRowsCountBefore, noteTableRowsCountAfter)
    }

    @Test
    fun `should edit note`() {
        val noteTableRowsCountBefore = countRowsInTable(jdbcTemplate, "note")
        addMockNote().andExpect(status().isOk)
        editMockNote().andExpect(status().isOk)

        val noteTableRowsCountAfter = countRowsInTable(jdbcTemplate, "note")

        assertEquals(noteTableRowsCountBefore, noteTableRowsCountAfter.minus(1))
        assertThat(noteService.getAllNotes()).contains(Note(MOCK_ID, "noteText1", created, "", setOf()))
    }

    @Test
    fun `should edit note with name`() {
        val noteTableRowsCountBefore = countRowsInTable(jdbcTemplate, "note")
        addMockNote().andExpect(status().isOk)
        editMockNoteWithName().andExpect(status().isOk)

        val noteTableRowsCountAfter = countRowsInTable(jdbcTemplate, "note")

        assertEquals(noteTableRowsCountBefore, noteTableRowsCountAfter.minus(1))
        assertThat(noteService.getAllNotes()).contains(Note(MOCK_ID, "noteText1", created, "noteName1", setOf()))
    }

    @Test
    fun `should edit note with hashtags`() {
        addMockNote().andExpect(status().isOk)
        editMockNoteWithHashtags().andExpect(status().isOk)

        assertThat(noteService.getAllNotes()).contains(Note(MOCK_ID, "noteText1", created, "", setOf(Hashtag(MOCK_ID, "hashtagText1"))))
    }

    @Test
    fun `should get all notes`() {
        addMockNote().andExpect(status().isOk)
        getAllNotes().andExpect(status().isOk)
            .andExpect(
                content().string(containsString("""{"id":$MOCK_ID,"text":"noteText","created":"1970-01-01","name":"","hashtags":[]}"""))
            )
    }

    @Test
    fun `should find note by text`() {
        addMockNote().andExpect(status().isOk)
        findNoteByText().andExpect(status().isFound)
    }

    @Test
    fun `should find note by name`() {
        addMockNoteWithName().andExpect(status().isOk)
        findNoteByName().andExpect(status().isFound)
    }

    private fun addMockNote(): ResultActions {
        return mockMvc.perform(
            post("/note/add")
                .contentType(APPLICATION_JSON)
                .content("""{ "id": $MOCK_ID, "text": "noteText", "created": "1970-01-01" }""")
        )
    }

    private fun addMockNoteWithName(): ResultActions {
        return mockMvc.perform(
            post("/note/add")
                .contentType(APPLICATION_JSON)
                .content("""{ "id": $MOCK_ID, "text": "noteText", "created": "1970-01-01", "name": "noteName" }""")
        )
    }

    private fun addMockNoteWithHashtags(): ResultActions {
        return mockMvc.perform(
            post("/note/add")
                .contentType(APPLICATION_JSON)
                .content("""{ "id": $MOCK_ID, "text": "noteText", "created": "1970-01-01", "hashtags":[{"id": $MOCK_ID, "text": "hashtagText"}] }""")
        )
    }

    private fun deleteMockNote(): ResultActions {
        return mockMvc.perform(
            delete("/note/$MOCK_ID/delete")
        )
    }

    private fun editMockNote(): ResultActions {
        return mockMvc.perform(
            patch("/note/$MOCK_ID/edit")
                .contentType(APPLICATION_JSON)
                .content("""{ "id": $MOCK_ID, "text": "noteText1" }""")
        )
    }

    private fun editMockNoteWithName(): ResultActions {
        return mockMvc.perform(
            patch("/note/$MOCK_ID/edit")
                .contentType(APPLICATION_JSON)
                .content("""{ "id": $MOCK_ID, "text": "noteText1", "name": "noteName1" }""")
        )
    }

    private fun editMockNoteWithHashtags(): ResultActions {
        return mockMvc.perform(
            patch("/note/$MOCK_ID/edit")
                .contentType(APPLICATION_JSON)
                .content("""{ "id": $MOCK_ID, "text": "noteText1", "hashtags":[{"id": $MOCK_ID, "text": "hashtagText1"}] }""")
        )
    }

    private fun getAllNotes(): ResultActions {
        return mockMvc.perform(
            get("/notes")
        )
    }

    private fun findNoteByText(): ResultActions {
        return mockMvc.perform(
            get("/note/search?text=noteText")
        )
    }

    private fun findNoteByName(): ResultActions {
        return mockMvc.perform(
            get("/note/search?text=noteName")
        )
    }
}
