package com.jshvarts.conductormvp.data.room

import com.jshvarts.conductormvp.domain.NoteRepository
import com.jshvarts.conductormvp.domain.model.Note
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Room implementation of {@link NoteRepository}.
 */
class RoomNoteRepository(private val noteDao: RoomNoteDao,
                         private val mapper: RoomNoteModelMapper) : NoteRepository {

    override fun add(note: Note) = noteDao.add(mapper.toEntity(note))

    override fun update(note: Note) = noteDao.update(mapper.toEntity(note))

    override fun delete(note: Note) = noteDao.delete(mapper.toEntity(note))

    override fun findNoteById(id: Long): Maybe<Note> {
        return noteDao.findNoteById(id)
                .map { mapper.fromEntity(it) }
    }

    override fun getAllNotes(): Single<List<Note>> {
        return noteDao.getAllNotes()
                .map { it.map(mapper::fromEntity) }
    }
}