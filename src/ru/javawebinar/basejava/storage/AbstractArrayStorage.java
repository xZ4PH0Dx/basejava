package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    public static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected boolean isExist(Object key) {
        return (int) key >= 0;
    }

    @Override
    public List<Resume> getResumeList() {
        return Arrays.asList(storage);
    }

    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(int) key];
    }

    @Override
    protected void doSave(Resume resume, Object key) {
        if (size < STORAGE_LIMIT) {
            insertElement(resume, (int) key);
            size++;
        } else {
            throw new StorageException("Storage overflow", null);
        }
    }

    @Override
    public void doUpdate(Resume resume, Object key) {
        storage[(int) key] = resume;
    }

    @Override
    protected void doDelete(Object key) {
        fillDeletedElement((int) key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract Object getKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}